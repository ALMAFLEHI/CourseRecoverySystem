<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.crs.model.RecoveryPlan" %>
<%
    String recoveryFormMode = (String) request.getAttribute("formMode");
    if (recoveryFormMode == null) {
        recoveryFormMode = "create";
    }

    RecoveryPlan recoveryPlan = (RecoveryPlan) request.getAttribute("plan");
    if (recoveryPlan == null) {
        recoveryPlan = new RecoveryPlan();
    }

    boolean recoveryIsCreate = "create".equals(recoveryFormMode);
    boolean policyLocked = request.getAttribute("policyLocked") != null
            && Boolean.TRUE.equals(request.getAttribute("policyLocked"));
    String policyNote = (String) request.getAttribute("policyNote");

    request.setAttribute("pageTitle", recoveryIsCreate ? "Create Recovery Plan - CRS" : "Edit Recovery Plan - CRS");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title"><%= recoveryIsCreate ? "Create Recovery Plan" : "Edit Recovery Plan" %></h1>
            <p class="crs-page-subtitle">
                <%= recoveryIsCreate ? "Create a new recovery plan for a student and course."
                                     : "Update recommendation details and plan status." %>
            </p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/recovery/plans" class="btn btn-outline-secondary">Back to Recovery Plans</a>
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-dark">Dashboard</a>
        </div>
    </div>

    <div class="crs-card">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <% if (policyLocked && policyNote != null) { %>
            <div class="alert alert-info mb-4">
                <strong>Policy-linked creation:</strong> <%= policyNote %>
            </div>
        <% } %>

        <form action="<%= recoveryIsCreate ? request.getContextPath() + "/recovery/plans/create" : request.getContextPath() + "/recovery/plans/edit" %>" method="post">
            <% if (!recoveryIsCreate) { %>
                <input type="hidden" name="planId" value="<%= recoveryPlan.getPlanId() %>">
            <% } %>

            <div class="crs-form-grid">
                <% if (recoveryIsCreate) { %>
                    <% if (policyLocked) { %>
                        <input type="hidden" name="studentId" value="<%= recoveryPlan.getStudentId() %>">
                        <input type="hidden" name="courseId" value="<%= recoveryPlan.getCourseId() %>">
                        <input type="hidden" name="basedOnResultId" value="<%= recoveryPlan.getBasedOnResultId() %>">
                        <input type="hidden" name="attemptNo" value="<%= recoveryPlan.getAttemptNo() %>">

                        <div>
                            <label class="form-label">Student ID</label>
                            <div class="form-control bg-light"><%= recoveryPlan.getStudentId() %></div>
                        </div>

                        <div>
                            <label class="form-label">Course ID</label>
                            <div class="form-control bg-light"><%= recoveryPlan.getCourseId() %></div>
                        </div>

                        <div>
                            <label class="form-label">Based On Result ID</label>
                            <div class="form-control bg-light"><%= recoveryPlan.getBasedOnResultId() %></div>
                        </div>

                        <div>
                            <label class="form-label">Attempt No</label>
                            <div class="form-control bg-light"><%= recoveryPlan.getAttemptNo() %></div>
                        </div>
                    <% } else { %>
                        <div>
                            <label class="form-label">Student ID</label>
                            <input type="text" name="studentId" class="form-control"
                                   value="<%= recoveryPlan.getStudentId() != null ? recoveryPlan.getStudentId() : "" %>"
                                   placeholder="Enter student ID">
                        </div>

                        <div>
                            <label class="form-label">Course ID</label>
                            <input type="text" name="courseId" class="form-control"
                                   value="<%= recoveryPlan.getCourseId() != null ? recoveryPlan.getCourseId() : "" %>"
                                   placeholder="Enter course ID">
                        </div>

                        <div>
                            <label class="form-label">Based On Result ID</label>
                            <input type="number" name="basedOnResultId" class="form-control"
                                   value="<%= recoveryPlan.getBasedOnResultId() > 0 ? recoveryPlan.getBasedOnResultId() : "" %>"
                                   placeholder="Enter result ID">
                        </div>

                        <div>
                            <label class="form-label">Attempt No</label>
                            <input type="number" name="attemptNo" min="1" max="3" class="form-control"
                                   value="<%= recoveryPlan.getAttemptNo() > 0 ? recoveryPlan.getAttemptNo() : "" %>"
                                   placeholder="1, 2, or 3">
                        </div>
                    <% } %>
                <% } else { %>
                    <div>
                        <label class="form-label">Plan ID</label>
                        <div class="form-control bg-light"><%= recoveryPlan.getPlanId() %></div>
                    </div>

                    <div>
                        <label class="form-label">Student ID</label>
                        <div class="form-control bg-light"><%= recoveryPlan.getStudentId() %></div>
                    </div>

                    <div>
                        <label class="form-label">Course ID</label>
                        <div class="form-control bg-light"><%= recoveryPlan.getCourseId() %></div>
                    </div>

                    <div>
                        <label class="form-label">Attempt No</label>
                        <div class="form-control bg-light"><%= recoveryPlan.getAttemptNo() %></div>
                    </div>
                <% } %>

                <div class="crs-form-full">
                    <label class="form-label">Recommendation</label>
                    <textarea name="recommendationText" rows="5" class="form-control"
                              placeholder="Enter recommendation and action notes"><%= recoveryPlan.getRecommendationText() != null ? recoveryPlan.getRecommendationText() : "" %></textarea>
                </div>

                <div class="crs-form-full">
                    <label class="form-label">Plan Status</label>
                    <select name="planStatus" class="form-select">
                        <option value="">-- Select Status --</option>
                        <option value="DRAFT" <%= "DRAFT".equals(recoveryPlan.getPlanStatus()) ? "selected" : "" %>>DRAFT</option>
                        <option value="ACTIVE" <%= "ACTIVE".equals(recoveryPlan.getPlanStatus()) ? "selected" : "" %>>ACTIVE</option>
                        <option value="COMPLETED" <%= "COMPLETED".equals(recoveryPlan.getPlanStatus()) ? "selected" : "" %>>COMPLETED</option>
                        <option value="CLOSED" <%= "CLOSED".equals(recoveryPlan.getPlanStatus()) ? "selected" : "" %>>CLOSED</option>
                    </select>
                </div>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">
                    <%= recoveryIsCreate ? "Create Recovery Plan" : "Update Recovery Plan" %>
                </button>
                <a href="<%= request.getContextPath() %>/recovery/plans" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>