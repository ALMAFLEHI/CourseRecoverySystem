<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.Student" %>
<%@ page import="com.crs.model.EligibilityCheck" %>
<%
    request.setAttribute("pageTitle", "Eligibility Check - CRS");

    List<Student> students = (List<Student>) request.getAttribute("students");
    Student student = (Student) request.getAttribute("student");
    EligibilityCheck eligibilityCheck = (EligibilityCheck) request.getAttribute("eligibilityCheck");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Eligibility Check</h1>
            <p class="crs-page-subtitle">Evaluate student progression based on CGPA and failed course limits.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/eligibility/ineligible" class="btn btn-outline-primary">Ineligible Students</a>
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
        </div>
    </div>

    <div class="crs-card mb-4">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <form action="<%= request.getContextPath() %>/eligibility/check" method="post">
            <div class="crs-form-grid">
                <div class="crs-form-full">
                    <label class="form-label">Select Student</label>
                    <select name="studentId" class="form-select">
                        <option value="">-- Select Student --</option>
                        <%
                            if (students != null) {
                                for (Student s : students) {
                                    boolean selected = student != null && s.getStudentId().equals(student.getStudentId());
                        %>
                            <option value="<%= s.getStudentId() %>" <%= selected ? "selected" : "" %>>
                                <%= s.getStudentId() %> - <%= s.getFullName() %>
                            </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">Check Eligibility</button>
                <a href="<%= request.getContextPath() %>/eligibility/ineligible" class="btn btn-outline-secondary">View Ineligible Students</a>
            </div>
        </form>
    </div>

    <% if (student != null && eligibilityCheck != null) {
           boolean eligible = eligibilityCheck.isEligible();
    %>
        <div class="crs-card">
            <h3 class="mb-3">Eligibility Result</h3>

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
                    <label class="form-label">CGPA</label>
                    <div class="form-control bg-light"><%= eligibilityCheck.getCgpa() %></div>
                </div>

                <div>
                    <label class="form-label">Failed Course Count</label>
                    <div class="form-control bg-light"><%= eligibilityCheck.getFailedCourseCount() %></div>
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
                    <div class="form-control bg-light" style="min-height: 90px;"><%= eligibilityCheck.getRemarks() %></div>
                </div>
            </div>

            <div class="crs-footer-space">
                <a href="<%= request.getContextPath() %>/eligibility/enrolment?studentId=<%= student.getStudentId() %>" class="btn btn-primary">
                    Go to Enrolment Decision
                </a>
            </div>
        </div>
    <% } %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>