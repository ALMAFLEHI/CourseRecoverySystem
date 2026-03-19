<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.RecoveryPlan" %>
<%@ page import="com.crs.model.RecoveryMilestone" %>
<%@ page import="com.crs.model.RecoveryProgressLog" %>
<%@ page import="com.crs.model.StudentComponentResult" %>
<%
    request.setAttribute("pageTitle", "Recovery Plan Details - CRS");

    RecoveryPlan plan = (RecoveryPlan) request.getAttribute("plan");
    List<RecoveryMilestone> milestones = (List<RecoveryMilestone>) request.getAttribute("milestones");
    List<RecoveryProgressLog> progressLogs = (List<RecoveryProgressLog>) request.getAttribute("progressLogs");
    List<StudentComponentResult> failedComponentResults =
            (List<StudentComponentResult>) request.getAttribute("failedComponentResults");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Recovery Plan Details</h1>
            <p class="crs-page-subtitle">Review the plan, milestones, failed components, and progress history.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/recovery/plans" class="btn btn-outline-secondary">Back to Recovery Plans</a>
            <a href="<%= request.getContextPath() %>/recovery/milestones/save?planId=<%= plan != null ? plan.getPlanId() : 0 %>" class="btn btn-primary">Add Milestone</a>
            <a href="<%= request.getContextPath() %>/recovery/progress/add?planId=<%= plan != null ? plan.getPlanId() : 0 %>" class="btn btn-outline-primary">Add Progress</a>
        </div>
    </div>

    <% if (plan != null) { %>
        <div class="crs-card mb-4">
            <h3 class="mb-3">Plan Information</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Plan ID</label>
                    <div class="form-control bg-light"><%= plan.getPlanId() %></div>
                </div>
                <div>
                    <label class="form-label">Student ID</label>
                    <div class="form-control bg-light"><%= plan.getStudentId() %></div>
                </div>
                <div>
                    <label class="form-label">Course ID</label>
                    <div class="form-control bg-light"><%= plan.getCourseId() %></div>
                </div>
                <div>
                    <label class="form-label">Based On Result ID</label>
                    <div class="form-control bg-light"><%= plan.getBasedOnResultId() %></div>
                </div>
                <div>
                    <label class="form-label">Attempt No</label>
                    <div class="form-control bg-light"><%= plan.getAttemptNo() %></div>
                </div>
                <div>
                    <label class="form-label">Status</label>
                    <div class="form-control bg-light"><%= plan.getPlanStatus() %></div>
                </div>
                <div class="crs-form-full">
                    <label class="form-label">Recommendation</label>
                    <div class="form-control bg-light" style="min-height: 110px;"><%= plan.getRecommendationText() %></div>
                </div>
            </div>
        </div>
    <% } %>

    <div class="crs-card mb-4">
        <h3 class="mb-3">Failed Component Results</h3>
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Component ID</th>
                    <th>Attempt</th>
                    <th>Score</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (failedComponentResults == null || failedComponentResults.isEmpty()) {
                %>
                    <tr>
                        <td colspan="5">No failed component results found.</td>
                    </tr>
                <%
                    } else {
                        for (StudentComponentResult item : failedComponentResults) {
                %>
                    <tr>
                        <td><strong><%= item.getCourseId() %></strong></td>
                        <td><%= item.getComponentId() %></td>
                        <td><%= item.getAttemptNo() %></td>
                        <td><%= item.getScore() %></td>
                        <td><span class="badge text-bg-danger"><%= item.getStatus() %></span></td>
                    </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>

    <div class="crs-card mb-4">
        <h3 class="mb-3">Milestones</h3>
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Week Label</th>
                    <th>Task Title</th>
                    <th>Description</th>
                    <th>Due Date</th>
                    <th>Status</th>
                    <th>Progress %</th>
                    <th style="min-width: 150px;">Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (milestones == null || milestones.isEmpty()) {
                %>
                    <tr>
                        <td colspan="8">No milestones found.</td>
                    </tr>
                <%
                    } else {
                        for (RecoveryMilestone milestone : milestones) {
                %>
                    <tr>
                        <td><strong><%= milestone.getMilestoneId() %></strong></td>
                        <td><%= milestone.getWeekLabel() %></td>
                        <td><%= milestone.getTaskTitle() %></td>
                        <td><%= milestone.getDescription() %></td>
                        <td><%= milestone.getDueDate() %></td>
                        <td><%= milestone.getStatus() %></td>
                        <td><%= milestone.getProgressPercent() %></td>
                        <td>
                            <div class="crs-actions">
                                <a href="<%= request.getContextPath() %>/recovery/milestones/save?planId=<%= milestone.getPlanId() %>&id=<%= milestone.getMilestoneId() %>">Edit</a>
                                <a href="<%= request.getContextPath() %>/recovery/milestones/delete?planId=<%= milestone.getPlanId() %>&id=<%= milestone.getMilestoneId() %>"
                                   onclick="return confirm('Are you sure you want to delete this milestone?');">Delete</a>
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

    <div class="crs-card">
        <h3 class="mb-3">Progress Logs</h3>
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Log ID</th>
                    <th>Milestone ID</th>
                    <th>Updated By</th>
                    <th>Note</th>
                    <th>Grade Entry</th>
                    <th>Status After Update</th>
                    <th>Created At</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (progressLogs == null || progressLogs.isEmpty()) {
                %>
                    <tr>
                        <td colspan="7">No progress logs found.</td>
                    </tr>
                <%
                    } else {
                        for (RecoveryProgressLog log : progressLogs) {
                %>
                    <tr>
                        <td><strong><%= log.getProgressLogId() %></strong></td>
                        <td><%= log.getMilestoneId() %></td>
                        <td><%= log.getUpdatedBy() %></td>
                        <td><%= log.getNote() %></td>
                        <td><%= log.getGradeEntry() %></td>
                        <td><%= log.getStatusAfterUpdate() %></td>
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