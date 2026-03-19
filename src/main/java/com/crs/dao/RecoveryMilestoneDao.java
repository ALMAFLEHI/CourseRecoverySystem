package com.crs.dao;

import com.crs.model.RecoveryMilestone;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecoveryMilestoneDao {

    private static final String FIND_BY_PLAN_ID_SQL = """
            SELECT milestone_id, plan_id, week_label, task_title, description,
                   due_date, status, progress_percent, created_at, updated_at
            FROM recovery_milestones
            WHERE plan_id = ?
            ORDER BY milestone_id ASC
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT milestone_id, plan_id, week_label, task_title, description,
                   due_date, status, progress_percent, created_at, updated_at
            FROM recovery_milestones
            WHERE milestone_id = ?
            """;

    private static final String INSERT_SQL = """
            INSERT INTO recovery_milestones
            (plan_id, week_label, task_title, description, due_date, status, progress_percent)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE recovery_milestones
            SET week_label = ?, task_title = ?, description = ?, due_date = ?, status = ?, progress_percent = ?
            WHERE milestone_id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM recovery_milestones
            WHERE milestone_id = ?
            """;

    public List<RecoveryMilestone> findByPlanId(int planId) {
        List<RecoveryMilestone> milestones = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_PLAN_ID_SQL)) {

            ps.setInt(1, planId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    milestones.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding milestones by plan id", e);
        }

        return milestones;
    }

    public RecoveryMilestone findById(int milestoneId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, milestoneId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding milestone by id", e);
        }

        return null;
    }

    public boolean insert(RecoveryMilestone milestone) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, milestone.getPlanId());
            ps.setString(2, milestone.getWeekLabel());
            ps.setString(3, milestone.getTaskTitle());
            ps.setString(4, milestone.getDescription());
            ps.setDate(5, milestone.getDueDate());
            ps.setString(6, milestone.getStatus());
            ps.setBigDecimal(7, milestone.getProgressPercent());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting recovery milestone", e);
        }
    }

    public boolean update(RecoveryMilestone milestone) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, milestone.getWeekLabel());
            ps.setString(2, milestone.getTaskTitle());
            ps.setString(3, milestone.getDescription());
            ps.setDate(4, milestone.getDueDate());
            ps.setString(5, milestone.getStatus());
            ps.setBigDecimal(6, milestone.getProgressPercent());
            ps.setInt(7, milestone.getMilestoneId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating recovery milestone", e);
        }
    }

    public boolean delete(int milestoneId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, milestoneId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting recovery milestone", e);
        }
    }

    private RecoveryMilestone mapRow(ResultSet rs) throws SQLException {
        RecoveryMilestone milestone = new RecoveryMilestone();
        milestone.setMilestoneId(rs.getInt("milestone_id"));
        milestone.setPlanId(rs.getInt("plan_id"));
        milestone.setWeekLabel(rs.getString("week_label"));
        milestone.setTaskTitle(rs.getString("task_title"));
        milestone.setDescription(rs.getString("description"));
        milestone.setDueDate(rs.getDate("due_date"));
        milestone.setStatus(rs.getString("status"));
        milestone.setProgressPercent(rs.getBigDecimal("progress_percent"));
        milestone.setCreatedAt(rs.getTimestamp("created_at"));
        milestone.setUpdatedAt(rs.getTimestamp("updated_at"));
        return milestone;
    }
}