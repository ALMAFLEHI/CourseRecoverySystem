<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.EmailLog" %>
<%
    request.setAttribute("pageTitle", "Email Logs - CRS");
    List<EmailLog> emailLogs = (List<EmailLog>) request.getAttribute("emailLogs");
    String selectedType = (String) request.getAttribute("selectedType");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Email Logs</h1>
            <p class="crs-page-subtitle">Track system email activity and filter by message type.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
        </div>
    </div>

    <div class="crs-card mb-4">
        <form action="<%= request.getContextPath() %>/logs/email" method="get">
            <div class="crs-form-grid">
                <div class="crs-form-full">
                    <label class="form-label">Filter by Type</label>
                    <select name="type" class="form-select">
                        <option value="">-- All Types --</option>
                        <option value="ACCOUNT_CREATED" <%= "ACCOUNT_CREATED".equals(selectedType) ? "selected" : "" %>>ACCOUNT_CREATED</option>
                        <option value="PASSWORD_RESET" <%= "PASSWORD_RESET".equals(selectedType) ? "selected" : "" %>>PASSWORD_RESET</option>
                        <option value="RECOVERY_PLAN" <%= "RECOVERY_PLAN".equals(selectedType) ? "selected" : "" %>>RECOVERY_PLAN</option>
                        <option value="MILESTONE_REMINDER" <%= "MILESTONE_REMINDER".equals(selectedType) ? "selected" : "" %>>MILESTONE_REMINDER</option>
                        <option value="REPORT" <%= "REPORT".equals(selectedType) ? "selected" : "" %>>REPORT</option>
                    </select>
                </div>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">Filter</button>
                <a href="<%= request.getContextPath() %>/logs/email" class="btn btn-outline-secondary">Clear</a>
            </div>
        </form>
    </div>

    <div class="crs-card">
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Email Log ID</th>
                    <th>Recipient Email</th>
                    <th>Subject</th>
                    <th>Message Type</th>
                    <th>Related Entity Type</th>
                    <th>Related Entity ID</th>
                    <th>Send Status</th>
                    <th>Sent By User ID</th>
                    <th>Created At</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (emailLogs == null || emailLogs.isEmpty()) {
                %>
                    <tr>
                        <td colspan="9">No email logs found.</td>
                    </tr>
                <%
                    } else {
                        for (EmailLog log : emailLogs) {
                %>
                    <tr>
                        <td><strong><%= log.getEmailLogId() %></strong></td>
                        <td><%= log.getRecipientEmail() %></td>
                        <td><%= log.getSubject() %></td>
                        <td><%= log.getMessageType() %></td>
                        <td><%= log.getRelatedEntityType() %></td>
                        <td><%= log.getRelatedEntityId() %></td>
                        <td>
                            <span class="badge <%= "SENT".equalsIgnoreCase(log.getSendStatus()) ? "text-bg-success" : "text-bg-secondary" %>">
                                <%= log.getSendStatus() %>
                            </span>
                        </td>
                        <td><%= log.getSentByUserId() %></td>
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