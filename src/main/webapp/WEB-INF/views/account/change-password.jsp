<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setAttribute("pageTitle", "Change Password - CRS");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Change Password</h1>
            <p class="crs-page-subtitle">Update your current password securely.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
        </div>
    </div>

    <div class="crs-card" style="max-width: 760px;">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <form action="<%= request.getContextPath() %>/account/change-password" method="post">
            <div class="crs-form-grid">
                <div class="crs-form-full">
                    <label class="form-label">Current Password</label>
                    <input type="password" name="currentPassword" class="form-control" placeholder="Enter current password">
                </div>

                <div>
                    <label class="form-label">New Password</label>
                    <input type="password" name="newPassword" class="form-control" placeholder="Enter new password">
                </div>

                <div>
                    <label class="form-label">Confirm New Password</label>
                    <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm new password">
                </div>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">Change Password</button>
                <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>