<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.crs.model.Student" %>
<%@ page import="com.crs.model.Course" %>
<%@ page import="com.crs.model.CourseComponent" %>
<%@ page import="com.crs.model.RetrievalPolicyDecision" %>
<%@ page import="com.crs.model.RetrievalPolicyEvaluationResult" %>
<%
    request.setAttribute("pageTitle", "Retrieval Policy Preview - CRS");

    Student previewStudent = (Student) request.getAttribute("policyStudent");
    Course previewCourse = (Course) request.getAttribute("policyCourse");
    RetrievalPolicyEvaluationResult previewEvaluation =
            (RetrievalPolicyEvaluationResult) request.getAttribute("policyEvaluation");
    List<CourseComponent> previewCourseComponents =
            (List<CourseComponent>) request.getAttribute("policyCourseComponents");
    Set<Integer> currentAllowedComponentIds =
            (Set<Integer>) request.getAttribute("currentAllowedComponentIds");

    List<RetrievalPolicyDecision> savedPolicyHistory =
            (List<RetrievalPolicyDecision>) request.getAttribute("savedPolicyHistory");
    RetrievalPolicyDecision latestSavedDecision =
            (RetrievalPolicyDecision) request.getAttribute("latestSavedDecision");
    Set<Integer> latestSavedAllowedComponentIds =
            (Set<Integer>) request.getAttribute("latestSavedAllowedComponentIds");

    RetrievalPolicyDecision previewDecision =
            previewEvaluation != null ? previewEvaluation.getDecision() : null;
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Retrieval Policy Preview</h1>
            <p class="crs-page-subtitle">Review the allowed retrieval scope and saved policy history before creating a recovery plan.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/students/details?id=<%= previewStudent != null ? previewStudent.getStudentId() : "" %>"
               class="btn btn-outline-secondary">Back to Student</a>
            <a href="<%= request.getContextPath() %>/recovery/plans"
               class="btn btn-outline-dark">Recovery Plans</a>
        </div>
    </div>

    <% if (previewStudent != null && previewCourse != null && previewDecision != null) { %>
        <div class="crs-card mb-4">
            <h3 class="mb-3">Student and Course</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Student ID</label>
                    <div class="form-control bg-light"><%= previewStudent.getStudentId() %></div>
                </div>
                <div>
                    <label class="form-label">Student Name</label>
                    <div class="form-control bg-light"><%= previewStudent.getFullName() %></div>
                </div>
                <div>
                    <label class="form-label">Course ID</label>
                    <div class="form-control bg-light"><%= previewCourse.getCourseId() %></div>
                </div>
                <div>
                    <label class="form-label">Course Name</label>
                    <div class="form-control bg-light"><%= previewCourse.getCourseName() %></div>
                </div>
            </div>
        </div>

        <div class="crs-card mb-4">
            <h3 class="mb-3">Current Policy Evaluation</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Current Attempt</label>
                    <div class="form-control bg-light"><%= previewDecision.getCurrentAttemptNo() %></div>
                </div>
                <div>
                    <label class="form-label">Next Attempt</label>
                    <div class="form-control bg-light"><%= previewDecision.getNextAttemptNo() %></div>
                </div>
                <div>
                    <label class="form-label">Policy Type</label>
                    <div class="form-control bg-light"><%= previewDecision.getPolicyType() %></div>
                </div>
                <div>
                    <label class="form-label">Allowed Scope</label>
                    <div class="form-control bg-light"><%= previewDecision.getAllowedScope() %></div>
                </div>
                <div>
                    <label class="form-label">Allowed</label>
                    <div class="form-control bg-light">
                        <span class="badge <%= previewDecision.isAllowed() ? "text-bg-success" : "text-bg-danger" %>">
                            <%= previewDecision.isAllowed() ? "ALLOWED" : "NOT ALLOWED" %>
                        </span>
                    </div>
                </div>
                <div class="crs-form-full">
                    <label class="form-label">Decision Note</label>
                    <div class="form-control bg-light" style="min-height: 90px;"><%= previewDecision.getDecisionNote() %></div>
                </div>
            </div>

            <% if (previewDecision.isAllowed()) { %>
                <div class="crs-footer-space">
                    <a href="<%= request.getContextPath() %>/recovery/plans/create?studentId=<%= previewStudent.getStudentId() %>&courseId=<%= previewCourse.getCourseId() %>&basedOnResultId=<%= previewDecision.getBasedOnResultId() %>&attemptNo=<%= previewDecision.getNextAttemptNo() %>&policyType=<%= previewDecision.getPolicyType() %>"
                       class="btn btn-primary">
                        Create Recovery Plan Using This Policy
                    </a>
                </div>
            <% } %>
        </div>

        <div class="crs-card mb-4">
            <h3 class="mb-3">Current Component Scope</h3>
            <div class="crs-table-wrap">
                <table class="crs-table">
                    <thead>
                    <tr>
                        <th>Component ID</th>
                        <th>Component Name</th>
                        <th>Type</th>
                        <th>Weightage</th>
                        <th>Retake Rule</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if (previewCourseComponents == null || previewCourseComponents.isEmpty()) {
                    %>
                        <tr>
                            <td colspan="5">No course components found.</td>
                        </tr>
                    <%
                        } else {
                            for (CourseComponent courseComponent : previewCourseComponents) {
                                boolean retakeRequired = currentAllowedComponentIds != null
                                        && currentAllowedComponentIds.contains(courseComponent.getComponentId());
                    %>
                        <tr>
                            <td><strong><%= courseComponent.getComponentId() %></strong></td>
                            <td><%= courseComponent.getComponentName() %></td>
                            <td><%= courseComponent.getComponentType() %></td>
                            <td><%= courseComponent.getWeightage() %></td>
                            <td>
                                <span class="badge <%= retakeRequired ? "text-bg-primary" : "text-bg-secondary" %>">
                                    <%= retakeRequired ? "RETAKE REQUIRED" : "NOT REQUIRED" %>
                                </span>
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

        <div class="crs-card mb-4">
            <h3 class="mb-3">Saved Policy History</h3>
            <div class="crs-table-wrap">
                <table class="crs-table">
                    <thead>
                    <tr>
                        <th>Decision ID</th>
                        <th>Current Attempt</th>
                        <th>Next Attempt</th>
                        <th>Policy Type</th>
                        <th>Scope</th>
                        <th>Allowed</th>
                        <th>Decided By</th>
                        <th>Decided At</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if (savedPolicyHistory == null || savedPolicyHistory.isEmpty()) {
                    %>
                        <tr>
                            <td colspan="8">No saved policy history found yet. It will appear after recovery creation is enforced and saved.</td>
                        </tr>
                    <%
                        } else {
                            for (RetrievalPolicyDecision savedDecision : savedPolicyHistory) {
                    %>
                        <tr>
                            <td><strong><%= savedDecision.getDecisionId() %></strong></td>
                            <td><%= savedDecision.getCurrentAttemptNo() %></td>
                            <td><%= savedDecision.getNextAttemptNo() %></td>
                            <td><%= savedDecision.getPolicyType() %></td>
                            <td><%= savedDecision.getAllowedScope() %></td>
                            <td>
                                <span class="badge <%= savedDecision.isAllowed() ? "text-bg-success" : "text-bg-danger" %>">
                                    <%= savedDecision.isAllowed() ? "YES" : "NO" %>
                                </span>
                            </td>
                            <td><%= savedDecision.getDecidedBy() %></td>
                            <td><%= savedDecision.getDecidedAt() %></td>
                        </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>

        <% if (latestSavedDecision != null) { %>
            <div class="crs-card">
                <h3 class="mb-3">Latest Saved Component Scope</h3>
                <p class="crs-page-subtitle mb-3">
                    Latest saved decision ID: <strong><%= latestSavedDecision.getDecisionId() %></strong>
                </p>

                <div class="crs-table-wrap">
                    <table class="crs-table">
                        <thead>
                        <tr>
                            <th>Component ID</th>
                            <th>Component Name</th>
                            <th>Type</th>
                            <th>Weightage</th>
                            <th>Saved Rule</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            if (previewCourseComponents == null || previewCourseComponents.isEmpty()) {
                        %>
                            <tr>
                                <td colspan="5">No course components found.</td>
                            </tr>
                        <%
                            } else {
                                for (CourseComponent courseComponent : previewCourseComponents) {
                                    boolean savedRetakeRequired = latestSavedAllowedComponentIds != null
                                            && latestSavedAllowedComponentIds.contains(courseComponent.getComponentId());
                        %>
                            <tr>
                                <td><strong><%= courseComponent.getComponentId() %></strong></td>
                                <td><%= courseComponent.getComponentName() %></td>
                                <td><%= courseComponent.getComponentType() %></td>
                                <td><%= courseComponent.getWeightage() %></td>
                                <td>
                                    <span class="badge <%= savedRetakeRequired ? "text-bg-primary" : "text-bg-secondary" %>">
                                        <%= savedRetakeRequired ? "RETAKE REQUIRED" : "NOT REQUIRED" %>
                                    </span>
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
        <% } %>
    <% } else { %>
        <div class="crs-card">
            <p class="mb-0">Unable to load retrieval policy preview.</p>
        </div>
    <% } %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>