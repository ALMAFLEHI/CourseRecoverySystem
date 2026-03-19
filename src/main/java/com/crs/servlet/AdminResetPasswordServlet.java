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

@WebServlet("/users/reset-password")
public class AdminResetPasswordServlet extends HttpServlet {

    private final PasswordResetService passwordResetService = new PasswordResetService();
    private final UserService userService = new UserService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/users");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/users");
            return;
        }

        User targetUser = userService.getUserById(userId);
        if (targetUser == null) {
            resp.sendRedirect(req.getContextPath() + "/users");
            return;
        }

        String temporaryPassword = passwordResetService.adminResetPassword(userId);

        if (temporaryPassword == null) {
            req.setAttribute("errorMessage", "Failed to reset password.");
            req.setAttribute("targetUser", targetUser);
            req.getRequestDispatcher("/WEB-INF/views/users/admin-reset-result.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        Integer actorUserId = session != null ? (Integer) session.getAttribute("userId") : null;

        emailService.logPasswordResetEmail(targetUser.getEmail(), targetUser.getUserId(), actorUserId);

        auditService.logAction(
                actorUserId,
                "ADMIN_RESET_PASSWORD",
                "USER",
                targetUser.getUserId(),
                "Admin reset password for username: " + targetUser.getUsername()
        );

        req.setAttribute("successMessage", "Temporary password generated successfully.");
        req.setAttribute("targetUser", targetUser);
        req.setAttribute("temporaryPassword", temporaryPassword);
        req.getRequestDispatcher("/WEB-INF/views/users/admin-reset-result.jsp").forward(req, resp);
    }
}