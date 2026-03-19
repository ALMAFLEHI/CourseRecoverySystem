package com.crs.service;

import com.crs.constants.StatusConstants;
import com.crs.dao.UserDao;
import com.crs.model.User;
import com.crs.util.PasswordUtil;

public class AuthService {

    private final UserDao userDao = new UserDao();

    public User login(String username, String plainPassword) {
        User user = userDao.findByUsername(username);

        if (user == null) {
            return null;
        }

        if (!StatusConstants.ACTIVE.equalsIgnoreCase(user.getStatus())) {
            return null;
        }

        if (!PasswordUtil.matches(plainPassword, user.getPasswordHash())) {
            return null;
        }

        userDao.updateLastLogin(user.getUserId());
        return user;
    }
}