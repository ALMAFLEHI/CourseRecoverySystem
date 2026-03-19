<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.Student" %>
<%
    request.setAttribute("pageTitle", "Academic Reports - CRS");
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Academic Reports</h1>
            <p class="crs-page-subtitle">Generate academic performance reports by student, semester, and academic year.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
        </div>
    </div>

    <div class="crs-card">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <form action="<%= request.getContextPath() %>/reports/generate" method="post">
            <div class="crs-form-grid">
                <div class="crs-form-full">
                    <label class="form-label">Select Student</label>
                    <select name="studentId" class="form-select">
                        <option value="">-- Select Student --</option>
                        <%
                            if (students != null) {
                                for (Student s : students) {
                        %>
                            <option value="<%= s.getStudentId() %>">
                                <%= s.getStudentId() %> - <%= s.getFullName() %>
                            </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div>
                    <label class="form-label">Semester</label>
                    <select name="semester" class="form-select">
                        <option value="">-- Select Semester --</option>
                        <option value="Spring">Spring</option>
                        <option value="Summer">Summer</option>
                        <option value="Fall">Fall</option>
                    </select>
                </div>

                <div>
                    <label class="form-label">Academic Year</label>
                    <input type="text" name="academicYear" class="form-control" placeholder="e.g. 2025">
                </div>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">Generate Report</button>
                <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>