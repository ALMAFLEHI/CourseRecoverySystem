package com.crs.filter;

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
public class AuthenticationFilter extends HttpFilter implements Filter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        String path = uri.substring(contextPath.length());

        boolean isLoginRequest = path.equals("/login");
        boolean isLogoutRequest = path.equals("/logout");
        boolean isForgotPasswordRequest = path.equals("/forgot-password");
        boolean isResetPasswordRequest = path.equals("/reset-password");
        boolean isTestRequest = path.equals("/test-user");
        boolean isAssetRequest = path.startsWith("/assets/");
        boolean isRootRequest = path.equals("/") || path.isEmpty();

        HttpSession session = req.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("loggedInUser") != null);

        if (isRootRequest) {
            if (isLoggedIn) {
                resp.sendRedirect(contextPath + "/dashboard");
            } else {
                resp.sendRedirect(contextPath + "/login");
            }
            return;
        }

        if (isLoginRequest || isLogoutRequest || isForgotPasswordRequest || isResetPasswordRequest
                || isTestRequest || isAssetRequest) {
            chain.doFilter(req, resp);
            return;
        }

        if (!isLoggedIn) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        chain.doFilter(req, resp);
    }
}