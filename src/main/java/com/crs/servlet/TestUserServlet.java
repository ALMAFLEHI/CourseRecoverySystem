package com.crs.servlet;

import com.crs.dao.UserDao;
import com.crs.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test-user")
public class TestUserServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        try {
            User user = userDao.findByUsername("admin");

            if (user != null) {
                out.println("DB connection successful.");
                out.println("User found:");
                out.println("ID: " + user.getUserId());
                out.println("Name: " + user.getFullName());
                out.println("Username: " + user.getUsername());
                out.println("Role: " + user.getRole());
                out.println("Status: " + user.getStatus());
            } else {
                out.println("DB connection successful, but user not found.");
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            e.printStackTrace(out);
        }
    }
}