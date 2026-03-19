package com.crs.dao;

import com.crs.model.RecoveryPlan;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecoveryPlanDao {

    private static final String FIND_ALL_SQL = """
            SELECT plan_id, student_id, course_id, based_on_result_id, attempt_no,
                   recommendation_text, plan_status, created_by, created_at, updated_at
            FROM recovery_plans
            ORDER BY plan_id ASC
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT plan_id, student_id, course_id, based_on_result_id, attempt_no,
                   recommendation_text, plan_status, created_by, created_at, updated_at
            FROM recovery_plans
            WHERE plan_id = ?
            """;

    private static final String FIND_BY_STUDENT_ID_SQL = """
            SELECT plan_id, student_id, course_id, based_on_result_id, attempt_no,
                   recommendation_text, plan_status, created_by, created_at, updated_at
            FROM recovery_plans
            WHERE student_id = ?
            ORDER BY plan_id ASC
            """;

    private static final String INSERT_SQL = """
            INSERT INTO recovery_plans
            (student_id, course_id, based_on_result_id, attempt_no, recommendation_text, plan_status, created_by)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE recovery_plans
            SET recommendation_text = ?, plan_status = ?
            WHERE plan_id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM recovery_plans
            WHERE plan_id = ?
            """;

    private static final String EXISTS_BY_STUDENT_COURSE_ATTEMPT_SQL = """
            SELECT COUNT(*)
            FROM recovery_plans
            WHERE student_id = ? AND course_id = ? AND attempt_no = ?
            """;

    public List<RecoveryPlan> findAll() {
        List<RecoveryPlan> plans = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                plans.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all recovery plans", e);
        }

        return plans;
    }

    public RecoveryPlan findById(int planId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, planId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding recovery plan by id", e);
        }

        return null;
    }

    public List<RecoveryPlan> findByStudentId(String studentId) {
        List<RecoveryPlan> plans = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_STUDENT_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    plans.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding recovery plans by student id", e);
        }

        return plans;
    }

    public boolean insert(RecoveryPlan plan) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setString(1, plan.getStudentId());
            ps.setString(2, plan.getCourseId());
            ps.setInt(3, plan.getBasedOnResultId());
            ps.setInt(4, plan.getAttemptNo());
            ps.setString(5, plan.getRecommendationText());
            ps.setString(6, plan.getPlanStatus());
            ps.setInt(7, plan.getCreatedBy());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting recovery plan", e);
        }
    }

    public boolean update(RecoveryPlan plan) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, plan.getRecommendationText());
            ps.setString(2, plan.getPlanStatus());
            ps.setInt(3, plan.getPlanId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating recovery plan", e);
        }
    }

    public boolean delete(int planId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, planId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting recovery plan", e);
        }
    }

    public boolean existsByStudentCourseAttempt(String studentId, String courseId, int attemptNo) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(EXISTS_BY_STUDENT_COURSE_ATTEMPT_SQL)) {

            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ps.setInt(3, attemptNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking existing recovery plan by attempt", e);
        }

        return false;
    }

    private RecoveryPlan mapRow(ResultSet rs) throws SQLException {
        RecoveryPlan plan = new RecoveryPlan();
        plan.setPlanId(rs.getInt("plan_id"));
        plan.setStudentId(rs.getString("student_id"));
        plan.setCourseId(rs.getString("course_id"));
        plan.setBasedOnResultId(rs.getInt("based_on_result_id"));
        plan.setAttemptNo(rs.getInt("attempt_no"));
        plan.setRecommendationText(rs.getString("recommendation_text"));
        plan.setPlanStatus(rs.getString("plan_status"));
        plan.setCreatedBy(rs.getInt("created_by"));
        plan.setCreatedAt(rs.getTimestamp("created_at"));
        plan.setUpdatedAt(rs.getTimestamp("updated_at"));
        return plan;
    }
}