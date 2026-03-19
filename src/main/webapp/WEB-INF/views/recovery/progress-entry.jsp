<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.RecoveryMilestone" %>
<%
    request.setAttribute("pageTitle", "Add Progress Entry - CRS");
    Integer planId = (Integer) request.getAttribute("planId");
    List<RecoveryMilestone> milestones = (List<RecoveryMilestone>) request.getAttribute("milestones");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Add Progress Entry</h1>
            <p class="crs-page-subtitle">Record progress notes, grades, and status updates for the selected plan.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/recovery/plans/details?id=<%= planId != null ? planId : 0 %>" class="btn btn-outline-secondary">Back to Plan Details</a>
        </div>
    </div>

    <div class="crs-card">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <form action="<%= request.getContextPath() %>/recovery/progress/add" method="post">
            <input type="hidden" name="planId" value="<%= planId != null ? planId : 0 %>">

            <div class="crs-form-grid">
                <div class="crs-form-full">
                    <label class="form-label">Milestone</label>
                    <select name="milestoneId" class="form-select">
                        <option value="">-- Optional Milestone --</option>
                        <%
                            if (milestones != null) {
                                for (RecoveryMilestone milestone : milestones) {
                        %>
                            <option value="<%= milestone.getMilestoneId() %>">
                                <%= milestone.getMilestoneId() %> - <%= milestone.getTaskTitle() %>
                            </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="crs-form-full">
                    <label class="form-label">Note</label>
                    <textarea name="note" rows="4" class="form-control"
                              placeholder="Enter progress note"></textarea>
                </div>

                <div>
                    <label class="form-label">Grade Entry</label>
                    <input type="number" step="0.01" name="gradeEntry" class="form-control"
                           placeholder="Enter grade value">
                </div>

                <div>
                    <label class="form-label">Status After Update</label>
                    <select name="statusAfterUpdate" class="form-select">
                        <option value="">-- Select Status --</option>
                        <option value="PENDING">PENDING</option>
                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                        <option value="COMPLETED">COMPLETED</option>
                        <option value="OVERDUE">OVERDUE</option>
                    </select>
                </div>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">Add Progress Entry</button>
                <a href="<%= request.getContextPath() %>/recovery/plans/details?id=<%= planId != null ? planId : 0 %>" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>