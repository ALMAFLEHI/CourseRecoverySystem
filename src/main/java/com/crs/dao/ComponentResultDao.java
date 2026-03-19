package com.crs.dao;

import com.crs.model.StudentComponentResult;
import com.crs.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponentResultDao {

    private static final String FIND_BY_STUDENT_SQL = """
            SELECT component_result_id, student_id, course_id, component_id, attempt_no, score, status, updated_at
            FROM student_component_results
            WHERE student_id = ?
            ORDER BY component_result_id ASC
            """;

    private static final String FIND_FAILED_BY_STUDENT_SQL = """
            SELECT component_result_id, student_id, course_id, component_id, attempt_no, score, status, updated_at
            FROM student_component_results
            WHERE student_id = ? AND status = 'FAIL'
            ORDER BY component_result_id ASC
            """;

    private static final String FIND_FAILED_BY_STUDENT_COURSE_AND_ATTEMPT_SQL = """
            SELECT component_result_id, student_id, course_id, component_id, attempt_no, score, status, updated_at
            FROM student_component_results
            WHERE student_id = ? AND course_id = ? AND attempt_no = ? AND status = 'FAIL'
            ORDER BY component_result_id ASC
            """;

    public List<StudentComponentResult> findByStudentId(String studentId) {
        return executeSingleParamQuery(FIND_BY_STUDENT_SQL, studentId);
    }

    public List<StudentComponentResult> findFailedByStudentId(String studentId) {
        return executeSingleParamQuery(FIND_FAILED_BY_STUDENT_SQL, studentId);
    }

    public List<StudentComponentResult> findFailedByStudentCourseAndAttempt(String studentId, String courseId, int attemptNo) {
        List<StudentComponentResult> results = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_FAILED_BY_STUDENT_COURSE_AND_ATTEMPT_SQL)) {

            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ps.setInt(3, attemptNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding failed component results by student, course, and attempt", e);
        }

        return results;
    }

    private List<StudentComponentResult> executeSingleParamQuery(String sql, String studentId) {
        List<StudentComponentResult> results = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding component results", e);
        }

        return results;
    }

    private StudentComponentResult mapRow(ResultSet rs) throws SQLException {
        StudentComponentResult result = new StudentComponentResult();
        result.setComponentResultId(rs.getInt("component_result_id"));
        result.setStudentId(rs.getString("student_id"));
        result.setCourseId(rs.getString("course_id"));
        result.setComponentId(rs.getInt("component_id"));
        result.setAttemptNo(rs.getInt("attempt_no"));
        result.setScore(rs.getBigDecimal("score"));
        result.setStatus(rs.getString("status"));
        result.setUpdatedAt(rs.getTimestamp("updated_at"));
        return result;
    }
}