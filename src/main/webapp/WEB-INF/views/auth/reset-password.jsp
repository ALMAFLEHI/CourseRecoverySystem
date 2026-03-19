<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String token = (String) request.getAttribute("token");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password - CRS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/assets/css/app.css" rel="stylesheet">
</head>
<body class="crs-body">
<div class="container min-vh-100 d-flex align-items-center justify-content-center py-4">
    <div class="crs-card w-100" style="max-width: 560px;">
        <h1 class="crs-page-title mb-2">Reset Password</h1>
        <p class="crs-page-subtitle mb-4">Set a new password for your account.</p>

        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <% if (token != null) { %>
            <form action="<%= request.getContextPath() %>/reset-password" method="post">
                <input type="hidden" name="token" value="<%= token %>">

                <div class="mb-3">
                    <label class="form-label">New Password</label>
                    <input type="password" name="newPassword" class="form-control" placeholder="Enter new password">
                </div>

                <div class="mb-3">
                    <label class="form-label">Confirm Password</label>
                    <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm new password">
                </div>

                <div class="d-flex gap-2 flex-wrap">
                    <button type="submit" class="btn btn-primary">Reset Password</button>
                    <a href="<%= request.getContextPath() %>/login" class="btn btn-outline-secondary">Back to Login</a>
                </div>
            </form>
        <% } else { %>
            <div class="d-flex gap-2 flex-wrap">
                <a href="<%= request.getContextPath() %>/forgot-password" class="btn btn-primary">Back to Forgot Password</a>
                <a href="<%= request.getContextPath() %>/login" class="btn btn-outline-secondary">Back to Login</a>
            </div>
        <% } %>
    </div>
</div>
</body>
</html>