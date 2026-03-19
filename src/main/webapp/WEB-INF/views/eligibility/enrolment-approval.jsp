<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.crs.model.Student" %>
<%@ page import="com.crs.model.EligibilityCheck" %>
<%@ page import="com.crs.model.Enrolment" %>
<%
    request.setAttribute("pageTitle", "Enrolment Approval - CRS");

    Student student = (Student) request.getAttribute("student");
    EligibilityCheck latestCheck = (EligibilityCheck) request.getAttribute("latestCheck");
    Enrolment latestEnrolment = (Enrolment) request.getAttribute("latestEnrolment");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Enrolment Approval</h1>
            <p class="crs-page-subtitle">Review eligibility and save the latest enrolment decision.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/eligibility/check" class="btn btn-outline-primary">Eligibility Check</a>
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
        </div>
    </div>

    <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

    <% if (student != null && latestCheck != null) {
           boolean eligible = latestCheck.isEligible();
    %>
        <div class="crs-card mb-4">
            <h3 class="mb-3">Student Information</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Student ID</label>
                    <div class="form-control bg-light"><%= student.getStudentId() %></div>
                </div>

                <div>
                    <label class="form-label">Full Name</label>
                    <div class="form-control bg-light"><%= student.getFullName() %></div>
                </div>

                <div>
                    <label class="form-label">Major</label>
                    <div class="form-control bg-light"><%= student.getMajor() %></div>
                </div>

                <div>
                    <label class="form-label">Study Year</label>
                    <div class="form-control bg-light"><%= student.getStudyYear() %></div>
                </div>
            </div>
        </div>

        <div class="crs-card mb-4">
            <h3 class="mb-3">Latest Eligibility Check</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">CGPA</label>
                    <div class="form-control bg-light"><%= latestCheck.getCgpa() %></div>
                </div>

                <div>
                    <label class="form-label">Failed Course Count</label>
                    <div class="form-control bg-light"><%= latestCheck.getFailedCourseCount() %></div>
                </div>

                <div>
                    <label class="form-label">Eligibility Status</label>
                    <div class="form-control bg-light">
                        <span class="badge <%= eligible ? "text-bg-success" : "text-bg-danger" %>">
                            <%= eligible ? "ELIGIBLE" : "NOT ELIGIBLE" %>
                        </span>
                    </div>
                </div>

                <div class="crs-form-full">
                    <label class="form-label">Remarks</label>
                    <div class="form-control bg-light" style="min-height: 90px;"><%= latestCheck.getRemarks() %></div>
                </div>
            </div>
        </div>

        <% if (latestEnrolment != null) { %>
            <div class="crs-card mb-4">
                <h3 class="mb-3">Latest Enrolment Decision</h3>
                <div class="crs-form-grid">
                    <div>
                        <label class="form-label">Status</label>
                        <div class="form-control bg-light"><%= latestEnrolment.getEnrolmentStatus() %></div>
                    </div>

                    <div>
                        <label class="form-label">Approved At</label>
                        <div class="form-control bg-light"><%= latestEnrolment.getApprovedAt() %></div>
                    </div>

                    <div class="crs-form-full">
                        <label class="form-label">Remarks</label>
                        <div class="form-control bg-light" style="min-height: 90px;"><%= latestEnrolment.getRemarks() %></div>
                    </div>
                </div>
            </div>
        <% } %>

        <div class="crs-card">
            <h3 class="mb-3">Save New Enrolment Decision</h3>

            <form action="<%= request.getContextPath() %>/eligibility/enrolment" method="post">
                <input type="hidden" name="studentId" value="<%= student.getStudentId() %>">

                <div class="crs-form-grid">
                    <div class="crs-form-full">
                        <label class="form-label">Enrolment Status</label>
                        <select name="enrolmentStatus" class="form-select">
                            <option value="">-- Select Status --</option>
                            <option value="APPROVED">APPROVED</option>
                            <option value="REJECTED">REJECTED</option>
                            <option value="PENDING">PENDING</option>
                        </select>
                    </div>

                    <div class="crs-form-full">
                        <label class="form-label">Remarks</label>
                        <textarea name="remarks" rows="4" class="form-control"
                                  placeholder="Enter decision remarks"></textarea>
                    </div>
                </div>

                <div class="crs-footer-space d-flex gap-2 flex-wrap">
                    <button type="submit" class="btn btn-primary">Save Decision</button>
                    <a href="<%= request.getContextPath() %>/eligibility/check" class="btn btn-outline-secondary">Cancel</a>
                </div>
            </form>
        </div>
    <% } else { %>
        <div class="crs-card">
            <p class="mb-0">No student or eligibility data found.</p>
        </div>
    <% } %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>