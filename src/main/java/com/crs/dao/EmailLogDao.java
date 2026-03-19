package com.crs.dao;

import com.crs.model.EmailLog;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailLogDao {

    private static final String INSERT_SQL = """
            INSERT INTO email_logs
            (recipient_email, subject, message_type, related_entity_type, related_entity_id, send_status, sent_by_user_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_ALL_SQL = """
            SELECT email_log_id, recipient_email, subject, message_type, related_entity_type,
                   related_entity_id, send_status, sent_by_user_id, created_at
            FROM email_logs
            ORDER BY email_log_id DESC
            """;

    private static final String FIND_BY_TYPE_SQL = """
            SELECT email_log_id, recipient_email, subject, message_type, related_entity_type,
                   related_entity_id, send_status, sent_by_user_id, created_at
            FROM email_logs
            WHERE message_type = ?
            ORDER BY email_log_id DESC
            """;

    public boolean insert(EmailLog emailLog) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setString(1, emailLog.getRecipientEmail());
            ps.setString(2, emailLog.getSubject());
            ps.setString(3, emailLog.getMessageType());
            ps.setString(4, emailLog.getRelatedEntityType());

            if (emailLog.getRelatedEntityId() != null) {
                ps.setInt(5, emailLog.getRelatedEntityId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, emailLog.getSendStatus());

            if (emailLog.getSentByUserId() != null) {
                ps.setInt(7, emailLog.getSentByUserId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting email log", e);
        }
    }

    public List<EmailLog> findAll() {
        List<EmailLog> logs = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                logs.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all email logs", e);
        }

        return logs;
    }

    public List<EmailLog> findByType(String messageType) {
        List<EmailLog> logs = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_TYPE_SQL)) {

            ps.setString(1, messageType);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    logs.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding email logs by type", e);
        }

        return logs;
    }

    private EmailLog mapRow(ResultSet rs) throws SQLException {
        EmailLog log = new EmailLog();
        log.setEmailLogId(rs.getInt("email_log_id"));
        log.setRecipientEmail(rs.getString("recipient_email"));
        log.setSubject(rs.getString("subject"));
        log.setMessageType(rs.getString("message_type"));
        log.setRelatedEntityType(rs.getString("related_entity_type"));

        int relatedEntityId = rs.getInt("related_entity_id");
        log.setRelatedEntityId(rs.wasNull() ? null : relatedEntityId);

        log.setSendStatus(rs.getString("send_status"));

        int sentByUserId = rs.getInt("sent_by_user_id");
        log.setSentByUserId(rs.wasNull() ? null : sentByUserId);

        log.setCreatedAt(rs.getTimestamp("created_at"));
        return log;
    }
}
