<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.crs.model.User" %>
<%
    request.setAttribute("pageTitle", "Admin Reset Password - CRS");
    User targetUser = (User) request.getAttribute("targetUser");
    String temporaryPassword = (String) request.getAttribute("temporaryPassword");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Admin Reset Password</h1>
            <p class="crs-page-subtitle">Temporary credentials generated for the selected user.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/users" class="btn btn-outline-secondary">Back to Users</a>
        </div>
    </div>

    <div class="crs-card" style="max-width: 760px;">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <% if (targetUser != null && temporaryPassword != null) { %>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">User ID</label>
                    <div class="form-control bg-light"><%= targetUser.getUserId() %></div>
                </div>

                <div>
                    <label class="form-label">Username</label>
                    <div class="form-control bg-light"><%= targetUser.getUsername() %></div>
                </div>

                <div class="crs-form-full">
                    <label class="form-label">Temporary Password</label>
                    <div class="form-control bg-light" style="font-weight: 700;"><%= temporaryPassword %></div>
                </div>
            </div>

            <div class="crs-footer-space">
                <div class="alert alert-warning mb-0">
                    Share this temporary password securely. The user should change it after login.
                </div>
            </div>
        <% } else { %>
            <p class="mb-0">No reset result available.</p>
        <% } %>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>