package com.crs.dao;

import com.crs.model.PasswordResetRequest;
import com.crs.util.DBConnectionUtil;

import java.sql.*;

public class PasswordResetDao {

    private static final String INSERT_SQL = """
            INSERT INTO password_reset_requests
            (user_id, reset_token, request_status, expires_at)
            VALUES (?, ?, ?, ?)
            """;

    private static final String FIND_BY_TOKEN_SQL = """
            SELECT reset_id, user_id, reset_token, request_status, expires_at, used_at, created_at
            FROM password_reset_requests
            WHERE reset_token = ?
            """;

    private static final String EXPIRE_OLD_REQUESTS_SQL = """
            UPDATE password_reset_requests
            SET request_status = 'EXPIRED'
            WHERE user_id = ?
              AND request_status = 'PENDING'
            """;

    private static final String MARK_USED_SQL = """
            UPDATE password_reset_requests
            SET request_status = 'USED',
                used_at = CURRENT_TIMESTAMP
            WHERE reset_id = ?
            """;

    public boolean insert(PasswordResetRequest request) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, request.getUserId());
            ps.setString(2, request.getResetToken());
            ps.setString(3, request.getRequestStatus());
            ps.setTimestamp(4, request.getExpiresAt());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting password reset request", e);
        }
    }

    public PasswordResetRequest findByToken(String token) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_TOKEN_SQL)) {

            ps.setString(1, token);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding password reset request by token", e);
        }

        return null;
    }

    public boolean expireOldRequestsByUserId(int userId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(EXPIRE_OLD_REQUESTS_SQL)) {

            ps.setInt(1, userId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error expiring old password reset requests", e);
        }
    }

    public boolean markUsed(int resetId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(MARK_USED_SQL)) {

            ps.setInt(1, resetId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error marking password reset request as used", e);
        }
    }

    private PasswordResetRequest mapRow(ResultSet rs) throws SQLException {
        PasswordResetRequest request = new PasswordResetRequest();
        request.setResetId(rs.getInt("reset_id"));
        request.setUserId(rs.getInt("user_id"));
        request.setResetToken(rs.getString("reset_token"));
        request.setRequestStatus(rs.getString("request_status"));
        request.setExpiresAt(rs.getTimestamp("expires_at"));
        request.setUsedAt(rs.getTimestamp("used_at"));
        request.setCreatedAt(rs.getTimestamp("created_at"));
        return request;
    }
}