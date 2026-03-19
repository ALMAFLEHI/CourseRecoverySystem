<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.crs.model.RecoveryMilestone" %>
<%
    RecoveryMilestone milestone = (RecoveryMilestone) request.getAttribute("milestone");
    if (milestone == null) {
        milestone = new RecoveryMilestone();
    }

    boolean isEdit = milestone.getMilestoneId() > 0;
    request.setAttribute("pageTitle", isEdit ? "Edit Milestone - CRS" : "Add Milestone - CRS");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title"><%= isEdit ? "Edit Milestone" : "Add Milestone" %></h1>
            <p class="crs-page-subtitle">Create or update milestone tasks and progress targets.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/recovery/plans/details?id=<%= milestone.getPlanId() %>" class="btn btn-outline-secondary">Back to Plan Details</a>
        </div>
    </div>

    <div class="crs-card">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <form action="<%= request.getContextPath() %>/recovery/milestones/save" method="post">
            <input type="hidden" name="milestoneId" value="<%= milestone.getMilestoneId() %>">
            <input type="hidden" name="planId" value="<%= milestone.getPlanId() %>">

            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Week Label</label>
                    <input type="text" name="weekLabel" class="form-control"
                           value="<%= milestone.getWeekLabel() != null ? milestone.getWeekLabel() : "" %>"
                           placeholder="e.g. Week 1">
                </div>

                <div>
                    <label class="form-label">Task Title</label>
                    <input type="text" name="taskTitle" class="form-control"
                           value="<%= milestone.getTaskTitle() != null ? milestone.getTaskTitle() : "" %>"
                           placeholder="Enter task title">
                </div>

                <div class="crs-form-full">
                    <label class="form-label">Description</label>
                    <textarea name="description" rows="4" class="form-control"
                              placeholder="Enter task description"><%= milestone.getDescription() != null ? milestone.getDescription() : "" %></textarea>
                </div>

                <div>
                    <label class="form-label">Due Date</label>
                    <input type="date" name="dueDate" class="form-control"
                           value="<%= milestone.getDueDate() != null ? milestone.getDueDate().toString() : "" %>">
                </div>

                <div>
                    <label class="form-label">Status</label>
                    <select name="status" class="form-select">
                        <option value="">-- Select Status --</option>
                        <option value="PENDING" <%= "PENDING".equals(milestone.getStatus()) ? "selected" : "" %>>PENDING</option>
                        <option value="IN_PROGRESS" <%= "IN_PROGRESS".equals(milestone.getStatus()) ? "selected" : "" %>>IN_PROGRESS</option>
                        <option value="COMPLETED" <%= "COMPLETED".equals(milestone.getStatus()) ? "selected" : "" %>>COMPLETED</option>
                        <option value="OVERDUE" <%= "OVERDUE".equals(milestone.getStatus()) ? "selected" : "" %>>OVERDUE</option>
                    </select>
                </div>

                <div class="crs-form-full">
                    <label class="form-label">Progress Percent</label>
                    <input type="number" step="0.01" min="0" max="100" name="progressPercent" class="form-control"
                           value="<%= milestone.getProgressPercent() != null ? milestone.getProgressPercent() : "" %>"
                           placeholder="Enter progress percentage">
                </div>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary"><%= isEdit ? "Update Milestone" : "Add Milestone" %></button>
                <a href="<%= request.getContextPath() %>/recovery/plans/details?id=<%= milestone.getPlanId() %>" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>