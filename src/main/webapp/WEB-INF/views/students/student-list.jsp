<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.Student" %>
<%
    request.setAttribute("pageTitle", "Students - CRS");
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Students</h1>
            <p class="crs-page-subtitle">Browse all students and view their academic records.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-secondary">Dashboard</a>
        </div>
    </div>

    <div class="crs-card">
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Full Name</th>
                    <th>Major</th>
                    <th>Study Year</th>
                    <th>Email</th>
                    <th style="min-width: 120px;">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (students == null || students.isEmpty()) {
                %>
                    <tr>
                        <td colspan="6">No students found.</td>
                    </tr>
                <%
                    } else {
                        for (Student student : students) {
                %>
                    <tr>
                        <td><strong><%= student.getStudentId() %></strong></td>
                        <td><%= student.getFullName() %></td>
                        <td><%= student.getMajor() %></td>
                        <td>
                            <span class="badge text-bg-light border"><%= student.getStudyYear() %></span>
                        </td>
                        <td><%= student.getEmail() %></td>
                        <td>
                            <div class="crs-actions">
                                <a href="<%= request.getContextPath() %>/students/details?id=<%= student.getStudentId() %>">
                                    View Details
                                </a>
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
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>