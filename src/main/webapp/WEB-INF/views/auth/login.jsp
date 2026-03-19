<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setAttribute("pageTitle", "Login - CRS");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getAttribute("pageTitle") %></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/assets/css/app.css" rel="stylesheet">

    <style>
        .login-page {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background:
                radial-gradient(circle at top right, rgba(37,99,235,0.18), transparent 28%),
                radial-gradient(circle at bottom left, rgba(15,23,42,0.12), transparent 30%),
                #f5f7fb;
            padding: 24px;
        }

        .login-shell {
            width: 100%;
            max-width: 1100px;
            display: grid;
            grid-template-columns: 1.05fr 0.95fr;
            background: #ffffff;
            border-radius: 24px;
            overflow: hidden;
            box-shadow: 0 25px 60px rgba(15, 23, 42, 0.12);
            border: 1px solid #e5e7eb;
        }

        .login-brand {
            background: linear-gradient(135deg, #0f172a 0%, #1e3a8a 100%);
            color: #fff;
            padding: 48px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .login-brand-logo {
            width: 64px;
            height: 64px;
            border-radius: 18px;
            background: rgba(255,255,255,0.14);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
            font-weight: 700;
            margin-bottom: 18px;
        }

        .login-brand h1 {
            font-size: 36px;
            line-height: 1.15;
            margin-bottom: 14px;
            font-weight: 700;
        }

        .login-brand p {
            color: #dbeafe;
            font-size: 15px;
            max-width: 460px;
            margin-bottom: 0;
        }

        .login-points {
            margin-top: 32px;
            padding-left: 18px;
            color: #dbeafe;
        }

        .login-points li {
            margin-bottom: 10px;
        }

        .login-form-wrap {
            padding: 48px;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #fff;
        }

        .login-card {
            width: 100%;
            max-width: 420px;
        }

        .login-title {
            font-size: 30px;
            font-weight: 700;
            color: #111827;
            margin-bottom: 8px;
        }

        .login-subtitle {
            color: #6b7280;
            margin-bottom: 28px;
        }

        .form-label {
            font-weight: 600;
            color: #374151;
        }

        .form-control {
            border-radius: 14px;
            padding: 12px 14px;
            border: 1px solid #d1d5db;
        }

        .form-control:focus {
            box-shadow: 0 0 0 0.2rem rgba(37,99,235,0.15);
            border-color: #2563eb;
        }

        .btn-login {
            width: 100%;
            border: none;
            border-radius: 14px;
            padding: 12px 16px;
            font-weight: 700;
            background: #2563eb;
            color: #fff;
        }

        .btn-login:hover {
            background: #1d4ed8;
        }

        .login-helper {
            margin-top: 18px;
            font-size: 13px;
            color: #6b7280;
            text-align: center;
        }

        .login-links {
            margin-top: 14px;
            text-align: center;
        }

        .login-links a {
            color: #2563eb;
            text-decoration: none;
            font-weight: 600;
            font-size: 14px;
        }

        @media (max-width: 900px) {
            .login-shell {
                grid-template-columns: 1fr;
            }

            .login-brand,
            .login-form-wrap {
                padding: 32px 24px;
            }

            .login-brand h1 {
                font-size: 28px;
            }
        }
    </style>
</head>
<body>
<div class="login-page">
    <div class="login-shell">
        <div class="login-brand">
            <div>
                <div class="login-brand-logo">CRS</div>
                <h1>Course Recovery System</h1>
                <p>
                    A modern academic portal for recovery planning, eligibility checks,
                    reporting, and student progress management.
                </p>

                <ul class="login-points">
                    <li>Secure role-based access</li>
                    <li>Recovery and milestone tracking</li>
                    <li>Eligibility and academic reporting</li>
                </ul>
            </div>

            <div style="font-size:13px; color:#bfdbfe;">
                Sign in to continue to your academic dashboard.
            </div>
        </div>

        <div class="login-form-wrap">
            <div class="login-card">
                <div class="login-title">Welcome back</div>
                <div class="login-subtitle">Login to access your CRS workspace.</div>

                <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

                <form action="<%= request.getContextPath() %>/login" method="post">
                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input type="text" name="username" class="form-control" placeholder="Enter your username">
                    </div>

                    <div class="mb-4">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Enter your password">
                    </div>

                    <button type="submit" class="btn btn-login">Login</button>
                </form>

                <div class="login-links">
                    <a href="<%= request.getContextPath() %>/forgot-password">Forgot password?</a>
                </div>

                <div class="login-helper">
                    Course Recovery System · Secure academic access
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>