package com.crs.servlet;

import com.crs.service.AuditService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            String fullName = (String) session.getAttribute("fullName");

            auditService.logAction(
                    userId,
                    "LOGOUT",
                    "USER",
                    userId,
                    (fullName != null ? fullName : "User") + " logged out of the system."
            );

            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
