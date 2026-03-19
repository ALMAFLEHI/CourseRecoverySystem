package com.crs.service;

import com.crs.constants.RoleConstants;
import com.crs.constants.StatusConstants;
import com.crs.dao.UserDao;
import com.crs.model.User;
import com.crs.util.PasswordUtil;

import java.util.List;

public class UserService {

    private final UserDao userDao = new UserDao();

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(int userId) {
        return userDao.findById(userId);
    }

    public String createUser(User user, String plainPassword) {
        if (isBlank(user.getFullName()) || isBlank(user.getEmail()) || isBlank(user.getUsername())
                || isBlank(user.getRole()) || isBlank(plainPassword)) {
            return "All fields are required.";
        }

        if (!isValidRole(user.getRole())) {
            return "Invalid role selected.";
        }

        if (userDao.existsByUsername(user.getUsername())) {
            return "Username already exists.";
        }

        if (userDao.existsByEmail(user.getEmail())) {
            return "Email already exists.";
        }

        user.setPasswordHash(PasswordUtil.hashPassword(plainPassword));
        user.setStatus(StatusConstants.ACTIVE);

        boolean inserted = userDao.insert(user);
        return inserted ? null : "Failed to create user.";
    }

    public String updateUser(User user) {
        if (user.getUserId() <= 0 || isBlank(user.getFullName()) || isBlank(user.getEmail())
                || isBlank(user.getUsername()) || isBlank(user.getRole()) || isBlank(user.getStatus())) {
            return "All fields are required.";
        }

        if (!isValidRole(user.getRole())) {
            return "Invalid role selected.";
        }

        if (userDao.existsByUsernameExceptId(user.getUsername(), user.getUserId())) {
            return "Username already exists.";
        }

        if (userDao.existsByEmailExceptId(user.getEmail(), user.getUserId())) {
            return "Email already exists.";
        }

        boolean updated = userDao.update(user);
        return updated ? null : "Failed to update user.";
    }

    public boolean deactivateUser(int userId) {
        return userDao.deactivate(userId);
    }

    private boolean isValidRole(String role) {
        return RoleConstants.COURSE_ADMIN.equals(role)
                || RoleConstants.ACADEMIC_OFFICER.equals(role);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}