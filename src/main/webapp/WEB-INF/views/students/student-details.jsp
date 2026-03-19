<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.Student" %>
<%@ page import="com.crs.model.StudentCourseResult" %>
<%@ page import="com.crs.model.StudentComponentResult" %>
<%
    request.setAttribute("pageTitle", "Student Details - CRS");

    Student detailStudent = (Student) request.getAttribute("student");
    List<StudentCourseResult> detailCourseResults =
            (List<StudentCourseResult>) request.getAttribute("courseResults");
    List<StudentComponentResult> detailFailedComponentResults =
            (List<StudentComponentResult>) request.getAttribute("failedComponentResults");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Student Details</h1>
            <p class="crs-page-subtitle">Academic profile, results, failed components, and retrieval policy access.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/students" class="btn btn-outline-secondary">Back to Students</a>
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-dark">Dashboard</a>
        </div>
    </div>

    <% if (detailStudent != null) { %>
        <div class="crs-card mb-4">
            <h3 class="mb-3">Basic Information</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Student ID</label>
                    <div class="form-control bg-light"><%= detailStudent.getStudentId() %></div>
                </div>
                <div>
                    <label class="form-label">Full Name</label>
                    <div class="form-control bg-light"><%= detailStudent.getFullName() %></div>
                </div>
                <div>
                    <label class="form-label">Major</label>
                    <div class="form-control bg-light"><%= detailStudent.getMajor() %></div>
                </div>
                <div>
                    <label class="form-label">Study Year</label>
                    <div class="form-control bg-light"><%= detailStudent.getStudyYear() %></div>
                </div>
                <div class="crs-form-full">
                    <label class="form-label">Email</label>
                    <div class="form-control bg-light"><%= detailStudent.getEmail() %></div>
                </div>
            </div>
        </div>
    <% } %>

    <div class="crs-card mb-4">
        <h3 class="mb-3">Course Results</h3>
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Attempt</th>
                    <th>Semester</th>
                    <th>Academic Year</th>
                    <th>Grade</th>
                    <th>Grade Point</th>
                    <th>Total Score</th>
                    <th>Status</th>
                    <th>Credits Earned</th>
                    <th style="min-width: 150px;">Retrieval Policy</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (detailCourseResults == null || detailCourseResults.isEmpty()) {
                %>
                    <tr>
                        <td colspan="10">No course results found.</td>
                    </tr>
                <%
                    } else {
                        for (StudentCourseResult result : detailCourseResults) {
                            boolean passed = "PASS".equalsIgnoreCase(result.getFinalStatus());
                %>
                    <tr>
                        <td><strong><%= result.getCourseId() %></strong></td>
                        <td><%= result.getAttemptNo() %></td>
                        <td><%= result.getSemester() %></td>
                        <td><%= result.getAcademicYear() %></td>
                        <td><%= result.getGradeLetter() %></td>
                        <td><%= result.getGradePoint() %></td>
                        <td><%= result.getTotalScore() %></td>
                        <td>
                            <span class="badge <%= passed ? "text-bg-success" : "text-bg-danger" %>">
                                <%= result.getFinalStatus() %>
                            </span>
                        </td>
                        <td><%= result.getCreditsEarned() %></td>
                        <td>
                            <%
                                if (!passed && detailStudent != null) {
                            %>
                                <a href="<%= request.getContextPath() %>/recovery/policy?studentId=<%= detailStudent.getStudentId() %>&courseId=<%= result.getCourseId() %>">
                                    View Policy
                                </a>
                            <%
                                } else {
                            %>
                                <span class="text-muted">—</span>
                            <%
                                }
                            %>
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
                    if (detailFailedComponentResults == null || detailFailedComponentResults.isEmpty()) {
                %>
                    <tr>
                        <td colspan="5">No failed component results found.</td>
                    </tr>
                <%
                    } else {
                        for (StudentComponentResult componentResult : detailFailedComponentResults) {
                %>
                    <tr>
                        <td><strong><%= componentResult.getCourseId() %></strong></td>
                        <td><%= componentResult.getComponentId() %></td>
                        <td><%= componentResult.getAttemptNo() %></td>
                        <td><%= componentResult.getScore() %></td>
                        <td><span class="badge text-bg-danger"><%= componentResult.getStatus() %></span></td>
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