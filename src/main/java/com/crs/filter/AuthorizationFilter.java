package com.crs.filter;

import com.crs.constants.RoleConstants;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter extends HttpFilter implements Filter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        String path = uri.substring(contextPath.length());

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("role") == null) {
            chain.doFilter(req, resp);
            return;
        }

        String role = String.valueOf(session.getAttribute("role"));

        boolean adminOnlyPath =
                path.startsWith("/users");

        boolean officerOnlyPath =
                path.startsWith("/recovery") ||
                path.startsWith("/eligibility");

        if (adminOnlyPath && !RoleConstants.COURSE_ADMIN.equals(role)) {
            resp.sendRedirect(contextPath + "/access-denied");
            return;
        }

        if (officerOnlyPath && !RoleConstants.ACADEMIC_OFFICER.equals(role)) {
            resp.sendRedirect(contextPath + "/access-denied");
            return;
        }

        chain.doFilter(req, resp);
    }
}