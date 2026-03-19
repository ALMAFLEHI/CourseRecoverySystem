package com.crs.servlet;

import com.crs.model.User;
import com.crs.service.AuditService;
import com.crs.service.EmailService;
import com.crs.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("formMode", "create");
        req.getRequestDispatcher("/WEB-INF/views/users/user-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String role = req.getParameter("role");
        String password = req.getParameter("password");

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setUsername(username);
        user.setRole(role);

        String errorMessage = userService.createUser(user, password);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("formMode", "create");
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/views/users/user-form.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        Integer actorUserId = session != null ? (Integer) session.getAttribute("userId") : null;

        emailService.logAccountCreatedEmail(email, null, actorUserId);

        auditService.logAction(
                actorUserId,
                "CREATE_USER",
                "USER",
                null,
                "Created new user account for username: " + username
        );

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
