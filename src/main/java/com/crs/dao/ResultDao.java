package com.crs.dao;

import com.crs.model.StudentCourseResult;
import com.crs.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDao {

    private static final String FIND_RESULTS_BY_STUDENT_SQL = """
            SELECT result_id, student_id, course_id, attempt_no, semester, academic_year,
                   grade_letter, grade_point, total_score, final_status, credits_earned,
                   created_at, updated_at
            FROM student_course_results
            WHERE student_id = ?
            ORDER BY result_id ASC
            """;

    private static final String FIND_RESULTS_BY_STUDENT_AND_COURSE_SQL = """
            SELECT result_id, student_id, course_id, attempt_no, semester, academic_year,
                   grade_letter, grade_point, total_score, final_status, credits_earned,
                   created_at, updated_at
            FROM student_course_results
            WHERE student_id = ? AND course_id = ?
            ORDER BY attempt_no DESC, result_id DESC
            """;

    public List<StudentCourseResult> findResultsByStudentId(String studentId) {
        List<StudentCourseResult> results = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_RESULTS_BY_STUDENT_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding results by student id", e);
        }

        return results;
    }

    public List<StudentCourseResult> findResultsByStudentAndCourseId(String studentId, String courseId) {
        List<StudentCourseResult> results = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_RESULTS_BY_STUDENT_AND_COURSE_SQL)) {

            ps.setString(1, studentId);
            ps.setString(2, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding results by student and course", e);
        }

        return results;
    }

    private StudentCourseResult mapRow(ResultSet rs) throws SQLException {
        StudentCourseResult result = new StudentCourseResult();
        result.setResultId(rs.getInt("result_id"));
        result.setStudentId(rs.getString("student_id"));
        result.setCourseId(rs.getString("course_id"));
        result.setAttemptNo(rs.getInt("attempt_no"));
        result.setSemester(rs.getString("semester"));
        result.setAcademicYear(rs.getString("academic_year"));
        result.setGradeLetter(rs.getString("grade_letter"));
        result.setGradePoint(rs.getBigDecimal("grade_point"));
        result.setTotalScore(rs.getBigDecimal("total_score"));
        result.setFinalStatus(rs.getString("final_status"));
        result.setCreditsEarned(rs.getInt("credits_earned"));
        result.setCreatedAt(rs.getTimestamp("created_at"));
        result.setUpdatedAt(rs.getTimestamp("updated_at"));
        return result;
    }
}