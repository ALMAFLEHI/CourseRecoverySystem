package com.crs.servlet;

import com.crs.model.AuditLog;
import com.crs.service.AuditService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/logs/audit")
public class AuditLogServlet extends HttpServlet {

    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AuditLog> auditLogs = auditService.getAllAuditLogs();
        req.setAttribute("auditLogs", auditLogs);
        req.getRequestDispatcher("/WEB-INF/views/logs/audit-log.jsp").forward(req, resp);
    }
}
