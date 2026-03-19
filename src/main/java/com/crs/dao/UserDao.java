package com.crs.dao;

import com.crs.constants.StatusConstants;
import com.crs.model.User;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String FIND_BY_USERNAME_SQL = """
            SELECT user_id, full_name, email, username, password_hash, role, status,
                   created_at, updated_at, last_login_at
            FROM users
            WHERE username = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT user_id, full_name, email, username, password_hash, role, status,
                   created_at, updated_at, last_login_at
            FROM users
            WHERE user_id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT user_id, full_name, email, username, password_hash, role, status,
                   created_at, updated_at, last_login_at
            FROM users
            ORDER BY user_id ASC
            """;

    private static final String INSERT_SQL = """
            INSERT INTO users (full_name, email, username, password_hash, role, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE users
            SET full_name = ?, email = ?, username = ?, role = ?, status = ?
            WHERE user_id = ?
            """;

    private static final String DEACTIVATE_SQL = """
            UPDATE users
            SET status = ?
            WHERE user_id = ?
            """;

    private static final String UPDATE_LAST_LOGIN_SQL = """
            UPDATE users
            SET last_login_at = CURRENT_TIMESTAMP
            WHERE user_id = ?
            """;

    private static final String EXISTS_BY_USERNAME_SQL = """
            SELECT COUNT(*) FROM users WHERE username = ?
            """;

    private static final String EXISTS_BY_EMAIL_SQL = """
            SELECT COUNT(*) FROM users WHERE email = ?
            """;

    private static final String EXISTS_BY_USERNAME_EXCEPT_ID_SQL = """
            SELECT COUNT(*) FROM users WHERE username = ? AND user_id <> ?
            """;

    private static final String EXISTS_BY_EMAIL_EXCEPT_ID_SQL = """
            SELECT COUNT(*) FROM users WHERE email = ? AND user_id <> ?
            """;
    
    private static final String FIND_BY_EMAIL_SQL = """
            SELECT user_id, full_name, email, username, password_hash, role, status,
                   created_at, updated_at, last_login_at
            FROM users
            WHERE email = ?
            """;

    private static final String UPDATE_PASSWORD_SQL = """
            UPDATE users
            SET password_hash = ?, updated_at = CURRENT_TIMESTAMP
            WHERE user_id = ?
            """;

    public User findByUsername(String username) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME_SQL)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by username", e);
        }

        return null;
    }

    public User findById(int userId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by id", e);
        }

        return null;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all users", e);
        }

        return users;
    }

    public boolean insert(User user) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user", e);
        }
    }

    public boolean update(User user) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getStatus());
            ps.setInt(6, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    public boolean deactivate(int userId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(DEACTIVATE_SQL)) {

            ps.setString(1, StatusConstants.INACTIVE);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deactivating user", e);
        }
    }

    public boolean updateLastLogin(int userId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_LAST_LOGIN_SQL)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating last login", e);
        }
    }

    public boolean existsByUsername(String username) {
        return countBySingleParam(EXISTS_BY_USERNAME_SQL, username) > 0;
    }

    public boolean existsByEmail(String email) {
        return countBySingleParam(EXISTS_BY_EMAIL_SQL, email) > 0;
    }

    public boolean existsByUsernameExceptId(String username, int userId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(EXISTS_BY_USERNAME_EXCEPT_ID_SQL)) {

            ps.setString(1, username);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking username uniqueness", e);
        }

        return false;
    }

    public boolean existsByEmailExceptId(String email, int userId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(EXISTS_BY_EMAIL_EXCEPT_ID_SQL)) {

            ps.setString(1, email);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking email uniqueness", e);
        }

        return false;
    }

    private int countBySingleParam(String sql, String value) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, value);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error counting records", e);
        }

        return 0;
    }

    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getString("status"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));
        user.setLastLoginAt(rs.getTimestamp("last_login_at"));
        return user;
    }
    
    public User findByEmail(String email) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by email", e);
        }

        return null;
    }

    public boolean updatePassword(int userId, String passwordHash) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {

            ps.setString(1, passwordHash);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating password", e);
        }
    }
}