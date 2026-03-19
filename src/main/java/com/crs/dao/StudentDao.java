package com.crs.dao;

import com.crs.model.Student;
import com.crs.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private static final String FIND_ALL_SQL = """
            SELECT student_id, first_name, last_name, major, study_year, email
            FROM students
            ORDER BY student_id ASC
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT student_id, first_name, last_name, major, study_year, email
            FROM students
            WHERE student_id = ?
            """;

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                students.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all students", e);
        }

        return students;
    }

    public Student findById(String studentId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding student by id", e);
        }

        return null;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getString("student_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setMajor(rs.getString("major"));
        student.setStudyYear(rs.getString("study_year"));
        student.setEmail(rs.getString("email"));
        return student;
    }
}