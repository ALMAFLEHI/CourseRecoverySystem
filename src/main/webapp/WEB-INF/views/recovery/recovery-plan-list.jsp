<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.RecoveryPlan" %>
<%
    request.setAttribute("pageTitle", "Recovery Plans - CRS");
    List<RecoveryPlan> plans = (List<RecoveryPlan>) request.getAttribute("plans");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Recovery Plans</h1>
            <p class="crs-page-subtitle">Manage course recovery plans, recommendations, and follow-up actions.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
            <a href="<%= request.getContextPath() %>/recovery/plans/create" class="btn btn-primary">Create Recovery Plan</a>
        </div>
    </div>

    <div class="crs-card">
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Plan ID</th>
                    <th>Student ID</th>
                    <th>Course ID</th>
                    <th>Result ID</th>
                    <th>Attempt</th>
                    <th>Status</th>
                    <th>Created By</th>
                    <th style="min-width: 180px;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (plans == null || plans.isEmpty()) {
                %>
                    <tr>
                        <td colspan="8">No recovery plans found.</td>
                    </tr>
                <%
                    } else {
                        for (RecoveryPlan plan : plans) {
                            String badgeClass = "text-bg-secondary";
                            if ("ACTIVE".equalsIgnoreCase(plan.getPlanStatus())) {
                                badgeClass = "text-bg-primary";
                            } else if ("COMPLETED".equalsIgnoreCase(plan.getPlanStatus())) {
                                badgeClass = "text-bg-success";
                            } else if ("CLOSED".equalsIgnoreCase(plan.getPlanStatus())) {
                                badgeClass = "text-bg-dark";
                            }
                %>
                    <tr>
                        <td><strong><%= plan.getPlanId() %></strong></td>
                        <td><%= plan.getStudentId() %></td>
                        <td><%= plan.getCourseId() %></td>
                        <td><%= plan.getBasedOnResultId() %></td>
                        <td><%= plan.getAttemptNo() %></td>
                        <td>
                            <span class="badge <%= badgeClass %>"><%= plan.getPlanStatus() %></span>
                        </td>
                        <td><%= plan.getCreatedBy() %></td>
                        <td>
                            <div class="crs-actions">
                                <a href="<%= request.getContextPath() %>/recovery/plans/details?id=<%= plan.getPlanId() %>">Details</a>
                                <a href="<%= request.getContextPath() %>/recovery/plans/edit?id=<%= plan.getPlanId() %>">Edit</a>
                                <a href="<%= request.getContextPath() %>/recovery/plans/delete?id=<%= plan.getPlanId() %>"
                                   onclick="return confirm('Are you sure you want to delete this recovery plan?');">Delete</a>
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