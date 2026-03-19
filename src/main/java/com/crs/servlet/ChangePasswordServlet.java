package com.crs.servlet;

import com.crs.model.User;
import com.crs.service.AuditService;
import com.crs.service.EmailService;
import com.crs.service.PasswordResetService;
import com.crs.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/account/change-password")
public class ChangePasswordServlet extends HttpServlet {

    private final PasswordResetService passwordResetService = new PasswordResetService();
    private final UserService userService = new UserService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/account/change-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer userId = session != null ? (Integer) session.getAttribute("userId") : null;

        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        String errorMessage = passwordResetService.changePassword(userId, currentPassword, newPassword, confirmPassword);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/views/account/change-password.jsp").forward(req, resp);
            return;
        }

        User user = userService.getUserById(userId);
        if (user != null) {
            emailService.logPasswordResetEmail(user.getEmail(), userId, userId);
        }

        auditService.logAction(
                userId,
                "CHANGE_PASSWORD",
                "USER",
                userId,
                "User changed their password."
        );

        req.setAttribute("successMessage", "Password changed successfully.");
        req.getRequestDispatcher("/WEB-INF/views/account/change-password.jsp").forward(req, resp);
    }
}