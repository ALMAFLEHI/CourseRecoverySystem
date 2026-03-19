<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.Course" %>
<%
    request.setAttribute("pageTitle", "Courses - CRS");
    List<Course> courses = (List<Course>) request.getAttribute("courses");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Courses</h1>
            <p class="crs-page-subtitle">Browse all available courses and inspect component details.</p>
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
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Credits</th>
                    <th>Semester</th>
                    <th>Instructor</th>
                    <th>Capacity</th>
                    <th style="min-width: 120px;">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (courses == null || courses.isEmpty()) {
                %>
                    <tr>
                        <td colspan="7">No courses found.</td>
                    </tr>
                <%
                    } else {
                        for (Course course : courses) {
                %>
                    <tr>
                        <td><strong><%= course.getCourseId() %></strong></td>
                        <td><%= course.getCourseName() %></td>
                        <td><%= course.getCredits() %></td>
                        <td>
                            <span class="badge text-bg-light border"><%= course.getSemester() %></span>
                        </td>
                        <td><%= course.getInstructor() %></td>
                        <td><%= course.getCapacity() %></td>
                        <td>
                            <div class="crs-actions">
                                <a href="<%= request.getContextPath() %>/courses/details?id=<%= course.getCourseId() %>">
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