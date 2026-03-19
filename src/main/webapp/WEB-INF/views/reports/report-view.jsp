<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.crs.model.Student" %>
<%@ page import="com.crs.model.StudentCourseResult" %>
<%@ page import="com.crs.model.PerformanceReportLog" %>
<%
    request.setAttribute("pageTitle", "Report View - CRS");

    Student student = (Student) request.getAttribute("student");
    List<StudentCourseResult> results = (List<StudentCourseResult>) request.getAttribute("results");
    String semester = (String) request.getAttribute("semester");
    String academicYear = (String) request.getAttribute("academicYear");
    BigDecimal averageGradePoint = (BigDecimal) request.getAttribute("averageGradePoint");
    String summaryNote = (String) request.getAttribute("summaryNote");
    PerformanceReportLog latestLog = (PerformanceReportLog) request.getAttribute("latestLog");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Academic Performance Report</h1>
            <p class="crs-page-subtitle">Detailed report view for the selected student and academic period.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/reports" class="btn btn-outline-secondary">Back to Search</a>
            <a href="<%= request.getContextPath() %>/reports/print?studentId=<%= student != null ? student.getStudentId() : "" %>&semester=<%= semester %>&academicYear=<%= academicYear %>" class="btn btn-primary">Print View</a>
        </div>
    </div>

    <% if (student != null) { %>
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
    <% } %>

    <div class="crs-card mb-4">
        <h3 class="mb-3">Report Filters</h3>
        <div class="crs-form-grid">
            <div>
                <label class="form-label">Semester</label>
                <div class="form-control bg-light"><%= semester %></div>
            </div>
            <div>
                <label class="form-label">Academic Year</label>
                <div class="form-control bg-light"><%= academicYear %></div>
            </div>
        </div>
    </div>

    <div class="crs-card mb-4">
        <h3 class="mb-3">Results</h3>
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Attempt</th>
                    <th>Grade</th>
                    <th>Grade Point</th>
                    <th>Total Score</th>
                    <th>Status</th>
                    <th>Credits Earned</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (results == null || results.isEmpty()) {
                %>
                    <tr>
                        <td colspan="7">No academic results found for the selected filters.</td>
                    </tr>
                <%
                    } else {
                        for (StudentCourseResult result : results) {
                            boolean passed = "PASS".equalsIgnoreCase(result.getFinalStatus());
                %>
                    <tr>
                        <td><strong><%= result.getCourseId() %></strong></td>
                        <td><%= result.getAttemptNo() %></td>
                        <td><%= result.getGradeLetter() %></td>
                        <td><%= result.getGradePoint() %></td>
                        <td><%= result.getTotalScore() %></td>
                        <td>
                            <span class="badge <%= passed ? "text-bg-success" : "text-bg-danger" %>">
                                <%= result.getFinalStatus() %>
                            </span>
                        </td>
                        <td><%= result.getCreditsEarned() %></td>
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
        <h3 class="mb-3">Summary</h3>
        <div class="crs-form-grid">
            <div>
                <label class="form-label">Average Grade Point</label>
                <div class="form-control bg-light"><%= averageGradePoint %></div>
            </div>
            <div class="crs-form-full">
                <label class="form-label">Summary Note</label>
                <div class="form-control bg-light" style="min-height: 90px;"><%= summaryNote %></div>
            </div>
        </div>
    </div>

    <% if (latestLog != null) { %>
        <div class="crs-card">
            <h3 class="mb-3">Latest Report Log</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Report Log ID</label>
                    <div class="form-control bg-light"><%= latestLog.getReportLogId() %></div>
                </div>
                <div>
                    <label class="form-label">Generated By</label>
                    <div class="form-control bg-light"><%= latestLog.getGeneratedBy() %></div>
                </div>
                <div class="crs-form-full">
                    <label class="form-label">Generated At</label>
                    <div class="form-control bg-light"><%= latestLog.getGeneratedAt() %></div>
                </div>
            </div>
        </div>
    <% } %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>