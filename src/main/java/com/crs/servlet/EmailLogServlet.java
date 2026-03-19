package com.crs.servlet;

import com.crs.model.EmailLog;
import com.crs.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/logs/email")
public class EmailLogServlet extends HttpServlet {

    private final EmailService emailService = new EmailService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageType = req.getParameter("type");

        List<EmailLog> emailLogs;
        if (messageType != null && !messageType.trim().isEmpty()) {
            emailLogs = emailService.getEmailLogsByType(messageType);
            req.setAttribute("selectedType", messageType);
        } else {
            emailLogs = emailService.getAllEmailLogs();
        }

        req.setAttribute("emailLogs", emailLogs);
        req.getRequestDispatcher("/WEB-INF/views/logs/email-log.jsp").forward(req, resp);
    }
}
