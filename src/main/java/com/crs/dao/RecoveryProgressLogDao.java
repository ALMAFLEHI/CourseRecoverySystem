package com.crs.dao;

import com.crs.model.RecoveryProgressLog;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecoveryProgressLogDao {

    private static final String FIND_BY_PLAN_ID_SQL = """
            SELECT progress_log_id, plan_id, milestone_id, updated_by, note,
                   grade_entry, status_after_update, created_at
            FROM recovery_progress_logs
            WHERE plan_id = ?
            ORDER BY progress_log_id ASC
            """;

    private static final String INSERT_SQL = """
            INSERT INTO recovery_progress_logs
            (plan_id, milestone_id, updated_by, note, grade_entry, status_after_update)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    public List<RecoveryProgressLog> findByPlanId(int planId) {
        List<RecoveryProgressLog> logs = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_PLAN_ID_SQL)) {

            ps.setInt(1, planId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RecoveryProgressLog log = new RecoveryProgressLog();
                    log.setProgressLogId(rs.getInt("progress_log_id"));
                    log.setPlanId(rs.getInt("plan_id"));

                    int milestoneId = rs.getInt("milestone_id");
                    log.setMilestoneId(rs.wasNull() ? null : milestoneId);

                    log.setUpdatedBy(rs.getInt("updated_by"));
                    log.setNote(rs.getString("note"));
                    log.setGradeEntry(rs.getBigDecimal("grade_entry"));
                    log.setStatusAfterUpdate(rs.getString("status_after_update"));
                    log.setCreatedAt(rs.getTimestamp("created_at"));
                    logs.add(log);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding progress logs by plan id", e);
        }

        return logs;
    }

    public boolean insert(RecoveryProgressLog log) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, log.getPlanId());

            if (log.getMilestoneId() != null) {
                ps.setInt(2, log.getMilestoneId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, log.getUpdatedBy());
            ps.setString(4, log.getNote());

            if (log.getGradeEntry() != null) {
                ps.setBigDecimal(5, log.getGradeEntry());
            } else {
                ps.setNull(5, Types.DECIMAL);
            }

            ps.setString(6, log.getStatusAfterUpdate());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting recovery progress log", e);
        }
    }
}