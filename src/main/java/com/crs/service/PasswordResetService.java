package com.crs.service;

import com.crs.dao.PasswordResetDao;
import com.crs.dao.UserDao;
import com.crs.model.PasswordResetRequest;
import com.crs.model.User;
import com.crs.util.PasswordUtil;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class PasswordResetService {

    private static final int RESET_TOKEN_EXPIRY_MINUTES = 30;
    private static final int TEMP_PASSWORD_LENGTH = 10;

    private final UserDao userDao = new UserDao();
    private final PasswordResetDao passwordResetDao = new PasswordResetDao();

    public User getUserByEmail(String email) {
        if (isBlank(email)) {
            return null;
        }
        return userDao.findByEmail(email.trim());
    }

    public PasswordResetRequest getResetRequestByToken(String token) {
        if (isBlank(token)) {
            return null;
        }
        return passwordResetDao.findByToken(token.trim());
    }

    public String createResetTokenForEmail(String email) {
        User user = getUserByEmail(email);
        if (user == null) {
            return null;
        }

        passwordResetDao.expireOldRequestsByUserId(user.getUserId());

        String token = UUID.randomUUID().toString();
        Timestamp expiresAt = Timestamp.from(
                Instant.now().plus(RESET_TOKEN_EXPIRY_MINUTES, ChronoUnit.MINUTES)
        );

        PasswordResetRequest request = new PasswordResetRequest();
        request.setUserId(user.getUserId());
        request.setResetToken(token);
        request.setRequestStatus("PENDING");
        request.setExpiresAt(expiresAt);

        boolean inserted = passwordResetDao.insert(request);
        return inserted ? token : null;
    }

    public boolean isResetTokenUsable(String token) {
        PasswordResetRequest request = getResetRequestByToken(token);

        if (request == null) {
            return false;
        }

        if (!"PENDING".equalsIgnoreCase(request.getRequestStatus())) {
            return false;
        }

        return request.getExpiresAt() != null
                && request.getExpiresAt().after(new Timestamp(System.currentTimeMillis()));
    }

    public String resetPasswordByToken(String token, String newPassword, String confirmPassword) {
        if (isBlank(token)) {
            return "Reset token is missing.";
        }

        if (isBlank(newPassword) || isBlank(confirmPassword)) {
            return "All password fields are required.";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "New password and confirm password do not match.";
        }

        String passwordValidationError = validateNewPassword(newPassword);
        if (passwordValidationError != null) {
            return passwordValidationError;
        }

        PasswordResetRequest request = getResetRequestByToken(token);
        if (request == null) {
            return "Invalid reset token.";
        }

        if (!"PENDING".equalsIgnoreCase(request.getRequestStatus())) {
            return "This reset request is no longer active.";
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (request.getExpiresAt() == null || !request.getExpiresAt().after(now)) {
            return "This reset token has expired.";
        }

        String passwordHash = PasswordUtil.hashPassword(newPassword);
        boolean updated = userDao.updatePassword(request.getUserId(), passwordHash);

        if (!updated) {
            return "Failed to update password.";
        }

        passwordResetDao.markUsed(request.getResetId());
        return null;
    }

    public String changePassword(int userId, String currentPassword, String newPassword, String confirmPassword) {
        if (userId <= 0) {
            return "Invalid user session.";
        }

        if (isBlank(currentPassword) || isBlank(newPassword) || isBlank(confirmPassword)) {
            return "All password fields are required.";
        }

        User user = userDao.findById(userId);
        if (user == null) {
            return "User account not found.";
        }

        if (!PasswordUtil.matches(currentPassword, user.getPasswordHash())) {
            return "Current password is incorrect.";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "New password and confirm password do not match.";
        }

        if (PasswordUtil.matches(newPassword, user.getPasswordHash())) {
            return "New password must be different from the current password.";
        }

        String passwordValidationError = validateNewPassword(newPassword);
        if (passwordValidationError != null) {
            return passwordValidationError;
        }

        String passwordHash = PasswordUtil.hashPassword(newPassword);
        boolean updated = userDao.updatePassword(userId, passwordHash);

        return updated ? null : "Failed to update password.";
    }

    public String adminResetPassword(int userId) {
        if (userId <= 0) {
            return null;
        }

        User user = userDao.findById(userId);
        if (user == null) {
            return null;
        }

        String temporaryPassword = generateTemporaryPassword();
        String passwordHash = PasswordUtil.hashPassword(temporaryPassword);

        boolean updated = userDao.updatePassword(userId, passwordHash);
        return updated ? temporaryPassword : null;
    }

    public String validateNewPassword(String password) {
        if (isBlank(password)) {
            return "Password is required.";
        }

        if (password.length() < 8) {
            return "Password must be at least 8 characters long.";
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }

        if (!hasUpper || !hasLower || !hasDigit) {
            return "Password must contain uppercase, lowercase, and a number.";
        }

        return null;
    }

    private String generateTemporaryPassword() {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789@#";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TEMP_PASSWORD_LENGTH; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}