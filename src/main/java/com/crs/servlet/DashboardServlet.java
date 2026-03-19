package com.crs.servlet;

import com.crs.model.AuditLog;
import com.crs.service.DashboardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private final DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("totalUsers", dashboardService.getTotalUsers());
        req.setAttribute("totalStudents", dashboardService.getTotalStudents());
        req.setAttribute("totalCourses", dashboardService.getTotalCourses());
        req.setAttribute("totalRecoveryPlans", dashboardService.getTotalRecoveryPlans());
        req.setAttribute("totalIneligibleStudents", dashboardService.getTotalIneligibleStudents());
        req.setAttribute("totalEmailLogs", dashboardService.getTotalEmailLogs());

        List<AuditLog> recentAuditLogs = dashboardService.getRecentAuditLogs(5);
        req.setAttribute("recentAuditLogs", recentAuditLogs);

        req.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard.jsp").forward(req, resp);
    }
}
