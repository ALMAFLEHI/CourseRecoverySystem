package com.crs.servlet;

import com.crs.model.User;
import com.crs.service.AuditService;
import com.crs.service.EmailService;
import com.crs.service.PasswordResetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

    private final PasswordResetService passwordResetService = new PasswordResetService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Email is required.");
            req.getRequestDispatcher("/WEB-INF/views/auth/forgot-password.jsp").forward(req, resp);
            return;
        }

        User user = passwordResetService.getUserByEmail(email);

        if (user == null) {
            req.setAttribute("successMessage", "If the email exists, a reset request has been generated.");
            req.getRequestDispatcher("/WEB-INF/views/auth/forgot-password.jsp").forward(req, resp);
            return;
        }

        String token = passwordResetService.createResetTokenForEmail(email);

        if (token == null) {
            req.setAttribute("errorMessage", "Failed to create reset request.");
            req.getRequestDispatcher("/WEB-INF/views/auth/forgot-password.jsp").forward(req, resp);
            return;
        }

        String baseUrl = req.getScheme() + "://" + req.getServerName()
                + ((req.getServerPort() == 80 || req.getServerPort() == 443) ? "" : ":" + req.getServerPort());

        String resetLink = baseUrl + req.getContextPath() + "/reset-password?token=" + token;

        emailService.logPasswordResetEmail(user.getEmail(), user.getUserId(), null);

        auditService.logAction(
                user.getUserId(),
                "REQUEST_PASSWORD_RESET",
                "USER",
                user.getUserId(),
                "Password reset requested for username: " + user.getUsername()
        );

        req.setAttribute("successMessage", "Reset request created. For demo mode, use the link below.");
        req.setAttribute("demoResetLink", resetLink);
        req.setAttribute("submittedEmail", email);
        req.getRequestDispatcher("/WEB-INF/views/auth/forgot-password.jsp").forward(req, resp);
    }
}