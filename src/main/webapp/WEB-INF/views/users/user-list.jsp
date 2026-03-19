<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.User" %>
<%
    request.setAttribute("pageTitle", "User Management - CRS");
    List<User> users = (List<User>) request.getAttribute("users");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">User Management</h1>
            <p class="crs-page-subtitle">View, create, edit, and manage system user accounts.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
            <a href="<%= request.getContextPath() %>/users/create" class="btn btn-primary">Add User</a>
        </div>
    </div>

    <div class="crs-card">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th style="min-width: 220px;">Actions</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    if (users == null || users.isEmpty()) {
                %>
                    <tr>
                        <td colspan="7">No users found.</td>
                    </tr>
                <%
                    } else {
                        for (User user : users) {
                            boolean isActive = "ACTIVE".equalsIgnoreCase(user.getStatus());
                            boolean isAdminRole = "COURSE_ADMIN".equalsIgnoreCase(user.getRole());
                %>
                    <tr>
                        <td><%= user.getUserId() %></td>
                        <td><strong><%= user.getFullName() %></strong></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getUsername() %></td>
                        <td>
                            <span class="badge <%= isAdminRole ? "text-bg-primary" : "text-bg-info" %>">
                                <%= user.getRole() %>
                            </span>
                        </td>
                        <td>
                            <span class="badge <%= isActive ? "text-bg-success" : "text-bg-secondary" %>">
                                <%= user.getStatus() %>
                            </span>
                        </td>
                        <td>
                            <div class="crs-actions">
                                <a href="<%= request.getContextPath() %>/users/edit?id=<%= user.getUserId() %>">Edit</a>
                                <a href="<%= request.getContextPath() %>/users/reset-password?id=<%= user.getUserId() %>"
                                   onclick="return confirm('Generate a temporary password for this user?');">
                                    Reset Password
                                </a>
                                <a href="<%= request.getContextPath() %>/users/deactivate?id=<%= user.getUserId() %>"
                                   onclick="return confirm('Are you sure you want to deactivate this user?');">
                                    Deactivate
                                </a>
                            </div>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>