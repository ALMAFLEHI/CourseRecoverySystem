<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.Course" %>
<%@ page import="com.crs.model.CourseComponent" %>
<%
    request.setAttribute("pageTitle", "Course Details - CRS");

    Course course = (Course) request.getAttribute("course");
    List<CourseComponent> components = (List<CourseComponent>) request.getAttribute("components");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Course Details</h1>
            <p class="crs-page-subtitle">View course information and assessment components.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/courses" class="btn btn-outline-secondary">Back to Courses</a>
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-dark">Dashboard</a>
        </div>
    </div>

    <% if (course != null) { %>
        <div class="crs-card mb-4">
            <h3 class="mb-3">Course Information</h3>
            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Course ID</label>
                    <div class="form-control bg-light"><%= course.getCourseId() %></div>
                </div>
                <div>
                    <label class="form-label">Course Name</label>
                    <div class="form-control bg-light"><%= course.getCourseName() %></div>
                </div>
                <div>
                    <label class="form-label">Credits</label>
                    <div class="form-control bg-light"><%= course.getCredits() %></div>
                </div>
                <div>
                    <label class="form-label">Semester</label>
                    <div class="form-control bg-light"><%= course.getSemester() %></div>
                </div>
                <div>
                    <label class="form-label">Instructor</label>
                    <div class="form-control bg-light"><%= course.getInstructor() %></div>
                </div>
                <div>
                    <label class="form-label">Capacity</label>
                    <div class="form-control bg-light"><%= course.getCapacity() %></div>
                </div>
            </div>
        </div>
    <% } %>

    <div class="crs-card">
        <h3 class="mb-3">Course Components</h3>
        <div class="crs-table-wrap">
            <table class="crs-table">
                <thead>
                <tr>
                    <th>Component ID</th>
                    <th>Component Name</th>
                    <th>Weightage</th>
                    <th>Component Type</th>
                    <th>Pass Required</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (components == null || components.isEmpty()) {
                %>
                    <tr>
                        <td colspan="5">No course components found.</td>
                    </tr>
                <%
                    } else {
                        for (CourseComponent component : components) {
                %>
                    <tr>
                        <td><%= component.getComponentId() %></td>
                        <td><strong><%= component.getComponentName() %></strong></td>
                        <td><%= component.getWeightage() %></td>
                        <td><%= component.getComponentType() %></td>
                        <td>
                            <span class="badge <%= component.isPassRequired() ? "text-bg-success" : "text-bg-secondary" %>">
                                <%= component.isPassRequired() ? "Yes" : "No" %>
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
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>