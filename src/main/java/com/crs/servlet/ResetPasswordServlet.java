package com.crs.servlet;

import com.crs.model.PasswordResetRequest;
import com.crs.service.AuditService;
import com.crs.service.PasswordResetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {

    private final PasswordResetService passwordResetService = new PasswordResetService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");

        if (token == null || token.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Reset token is missing.");
            req.getRequestDispatcher("/WEB-INF/views/auth/reset-password.jsp").forward(req, resp);
            return;
        }

        if (!passwordResetService.isResetTokenUsable(token)) {
            req.setAttribute("errorMessage", "This reset token is invalid or expired.");
            req.getRequestDispatcher("/WEB-INF/views/auth/reset-password.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("token", token);
        req.getRequestDispatcher("/WEB-INF/views/auth/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        PasswordResetRequest resetRequest = passwordResetService.getResetRequestByToken(token);

        String errorMessage = passwordResetService.resetPasswordByToken(token, newPassword, confirmPassword);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("token", token);
            req.getRequestDispatcher("/WEB-INF/views/auth/reset-password.jsp").forward(req, resp);
            return;
        }

        if (resetRequest != null) {
            auditService.logAction(
                    resetRequest.getUserId(),
                    "RESET_PASSWORD",
                    "USER",
                    resetRequest.getUserId(),
                    "Password was reset using token-based recovery."
            );
        }

        req.setAttribute("successMessage", "Password reset successful. You can now log in.");
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }
}