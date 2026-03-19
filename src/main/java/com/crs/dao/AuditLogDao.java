package com.crs.dao;

import com.crs.model.AuditLog;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditLogDao {

    private static final String INSERT_SQL = """
            INSERT INTO audit_logs
            (actor_user_id, action_type, entity_type, entity_id, description)
            VALUES (?, ?, ?, ?, ?)
            """;

    private static final String FIND_ALL_SQL = """
            SELECT audit_log_id, actor_user_id, action_type, entity_type, entity_id, description, created_at
            FROM audit_logs
            ORDER BY audit_log_id DESC
            """;

    private static final String FIND_RECENT_SQL = """
            SELECT audit_log_id, actor_user_id, action_type, entity_type, entity_id, description, created_at
            FROM audit_logs
            ORDER BY audit_log_id DESC
            LIMIT ?
            """;

    public boolean insert(AuditLog auditLog) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            if (auditLog.getActorUserId() != null) {
                ps.setInt(1, auditLog.getActorUserId());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setString(2, auditLog.getActionType());
            ps.setString(3, auditLog.getEntityType());

            if (auditLog.getEntityId() != null) {
                ps.setInt(4, auditLog.getEntityId());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setString(5, auditLog.getDescription());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting audit log", e);
        }
    }

    public List<AuditLog> findAll() {
        List<AuditLog> logs = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                logs.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all audit logs", e);
        }

        return logs;
    }

    public List<AuditLog> findRecent(int limit) {
        List<AuditLog> logs = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_RECENT_SQL)) {

            ps.setInt(1, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    logs.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding recent audit logs", e);
        }

        return logs;
    }

    private AuditLog mapRow(ResultSet rs) throws SQLException {
        AuditLog log = new AuditLog();
        log.setAuditLogId(rs.getInt("audit_log_id"));

        int actorUserId = rs.getInt("actor_user_id");
        log.setActorUserId(rs.wasNull() ? null : actorUserId);

        log.setActionType(rs.getString("action_type"));
        log.setEntityType(rs.getString("entity_type"));

        int entityId = rs.getInt("entity_id");
        log.setEntityId(rs.wasNull() ? null : entityId);

        log.setDescription(rs.getString("description"));
        log.setCreatedAt(rs.getTimestamp("created_at"));
        return log;
    }
}
