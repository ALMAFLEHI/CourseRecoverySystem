package com.crs.servlet;

import com.crs.model.User;
import com.crs.service.AuditService;
import com.crs.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/users/edit")
public class UserEditServlet extends HttpServlet {

    private final UserService userService = new UserService();
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

        User user = userService.getUserById(userId);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/users");
            return;
        }

        req.setAttribute("formMode", "edit");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/users/user-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("userId");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String role = req.getParameter("role");
        String status = req.getParameter("status");

        int userId;
        try {
            userId = Integer.parseInt(userIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/users");
            return;
        }

        User user = new User();
        user.setUserId(userId);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setUsername(username);
        user.setRole(role);
        user.setStatus(status);

        String errorMessage = userService.updateUser(user);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("formMode", "edit");
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/users/user-form.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        Integer actorUserId = session != null ? (Integer) session.getAttribute("userId") : null;

        auditService.logAction(
                actorUserId,
                "UPDATE_USER",
                "USER",
                userId,
                "Updated user account: " + username
        );

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
