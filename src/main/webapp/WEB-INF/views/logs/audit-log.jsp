<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.AuditLog" %>
<%
    request.setAttribute("pageTitle", "Audit Logs - CRS");
    List<AuditLog> auditLogs = (List<AuditLog>) request.getAttribute("auditLogs");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Audit Logs</h1>
            <p class="crs-page-subtitle">Track important system actions and user activity across modules.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
        </div>
    </div>

    <div class="crs-card">
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Audit Log ID</th>
                    <th>Actor User ID</th>
                    <th>Action Type</th>
                    <th>Entity Type</th>
                    <th>Entity ID</th>
                    <th>Description</th>
                    <th>Created At</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (auditLogs == null || auditLogs.isEmpty()) {
                %>
                    <tr>
                        <td colspan="7">No audit logs found.</td>
                    </tr>
                <%
                    } else {
                        for (AuditLog log : auditLogs) {
                %>
                    <tr>
                        <td><strong><%= log.getAuditLogId() %></strong></td>
                        <td><%= log.getActorUserId() %></td>
                        <td><%= log.getActionType() %></td>
                        <td><%= log.getEntityType() %></td>
                        <td><%= log.getEntityId() %></td>
                        <td><%= log.getDescription() %></td>
                        <td><%= log.getCreatedAt() %></td>
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