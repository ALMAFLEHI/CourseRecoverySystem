<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.AuditLog" %>
<%
    request.setAttribute("pageTitle", "Dashboard - CRS");
    List<AuditLog> recentAuditLogs = (List<AuditLog>) request.getAttribute("recentAuditLogs");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Dashboard</h1>
            <p class="crs-page-subtitle">Overview of users, students, recovery plans, eligibility, and logs.</p>
        </div>
    </div>

    <div class="crs-stats">
        <div class="crs-stat-card">
            <div class="crs-stat-label">Total Users</div>
            <div class="crs-stat-value"><%= request.getAttribute("totalUsers") %></div>
        </div>
        <div class="crs-stat-card">
            <div class="crs-stat-label">Total Students</div>
            <div class="crs-stat-value"><%= request.getAttribute("totalStudents") %></div>
        </div>
        <div class="crs-stat-card">
            <div class="crs-stat-label">Total Courses</div>
            <div class="crs-stat-value"><%= request.getAttribute("totalCourses") %></div>
        </div>
        <div class="crs-stat-card">
            <div class="crs-stat-label">Recovery Plans</div>
            <div class="crs-stat-value"><%= request.getAttribute("totalRecoveryPlans") %></div>
        </div>
        <div class="crs-stat-card">
            <div class="crs-stat-label">Ineligible Students</div>
            <div class="crs-stat-value"><%= request.getAttribute("totalIneligibleStudents") %></div>
        </div>
        <div class="crs-stat-card">
            <div class="crs-stat-label">Email Logs</div>
            <div class="crs-stat-value"><%= request.getAttribute("totalEmailLogs") %></div>
        </div>
    </div>

    <div class="crs-card">
        <h3 class="mb-3">Recent Audit Logs</h3>

        <div class="crs-table-wrap">
            <table class="crs-table">
                <tr>
                    <th>Action Type</th>
                    <th>Entity Type</th>
                    <th>Description</th>
                    <th>Created At</th>
                </tr>
                <%
                    if (recentAuditLogs == null || recentAuditLogs.isEmpty()) {
                %>
                    <tr>
                        <td colspan="4">No recent audit logs found.</td>
                    </tr>
                <%
                    } else {
                        for (AuditLog log : recentAuditLogs) {
                %>
                    <tr>
                        <td><%= log.getActionType() %></td>
                        <td><%= log.getEntityType() %></td>
                        <td><%= log.getDescription() %></td>
                        <td><%= log.getCreatedAt() %></td>
                    </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>