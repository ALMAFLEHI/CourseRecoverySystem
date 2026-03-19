package com.crs.dao;

import com.crs.model.Course;
import com.crs.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    private static final String FIND_ALL_SQL = """
            SELECT course_id, course_name, credits, semester, instructor, capacity
            FROM courses
            ORDER BY course_id ASC
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT course_id, course_name, credits, semester, instructor, capacity
            FROM courses
            WHERE course_id = ?
            """;

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                courses.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all courses", e);
        }

        return courses;
    }

    public Course findById(String courseId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setString(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding course by id", e);
        }

        return null;
    }

    private Course mapRow(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getString("course_id"));
        course.setCourseName(rs.getString("course_name"));
        course.setCredits(rs.getInt("credits"));
        course.setSemester(rs.getString("semester"));
        course.setInstructor(rs.getString("instructor"));
        course.setCapacity(rs.getInt("capacity"));
        return course;
    }
}