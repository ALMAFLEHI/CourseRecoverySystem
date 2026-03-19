<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password - CRS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/assets/css/app.css" rel="stylesheet">
</head>
<body class="crs-body">
<div class="container min-vh-100 d-flex align-items-center justify-content-center py-4">
    <div class="crs-card w-100" style="max-width: 560px;">
        <h1 class="crs-page-title mb-2">Forgot Password</h1>
        <p class="crs-page-subtitle mb-4">Enter your account email to generate a reset link.</p>

        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <form action="<%= request.getContextPath() %>/forgot-password" method="post">
            <div class="mb-3">
                <label class="form-label">Email Address</label>
                <input type="email" name="email" class="form-control"
                       value="<%= request.getAttribute("submittedEmail") != null ? request.getAttribute("submittedEmail") : "" %>"
                       placeholder="Enter your email">
            </div>

            <div class="d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">Generate Reset Link</button>
                <a href="<%= request.getContextPath() %>/login" class="btn btn-outline-secondary">Back to Login</a>
            </div>
        </form>

        <%
            String demoResetLink = (String) request.getAttribute("demoResetLink");
            if (demoResetLink != null) {
        %>
            <div class="mt-4">
                <label class="form-label">Demo Reset Link</label>
                <div class="form-control bg-light" style="min-height: 72px; word-break: break-all;">
                    <a href="<%= demoResetLink %>"><%= demoResetLink %></a>
                </div>
            </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>