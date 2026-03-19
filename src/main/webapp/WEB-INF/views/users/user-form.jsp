<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.crs.model.User" %>
<%
    String formMode = (String) request.getAttribute("formMode");
    if (formMode == null) {
        formMode = "create";
    }

    User user = (User) request.getAttribute("user");
    if (user == null) {
        user = new User();
    }

    boolean isCreate = "create".equals(formMode);
    String pageTitle = isCreate ? "Add User - CRS" : "Edit User - CRS";
    request.setAttribute("pageTitle", pageTitle);
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title"><%= isCreate ? "Add User" : "Edit User" %></h1>
            <p class="crs-page-subtitle">
                <%= isCreate ? "Create a new system account for an administrator or academic officer."
                             : "Update account details, role, and status." %>
            </p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/users" class="btn btn-outline-secondary">Back to Users</a>
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-outline-dark">Dashboard</a>
        </div>
    </div>

    <div class="crs-card">
        <%@ include file="/WEB-INF/views/common/alerts.jspf" %>

        <form action="<%= isCreate ? request.getContextPath() + "/users/create" : request.getContextPath() + "/users/edit" %>" method="post">
            <% if (!isCreate) { %>
                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
            <% } %>

            <div class="crs-form-grid">
                <div>
                    <label class="form-label">Full Name</label>
                    <input type="text" name="fullName" class="form-control"
                           value="<%= user.getFullName() != null ? user.getFullName() : "" %>"
                           placeholder="Enter full name">
                </div>

                <div>
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control"
                           value="<%= user.getEmail() != null ? user.getEmail() : "" %>"
                           placeholder="Enter email address">
                </div>

                <div>
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-control"
                           value="<%= user.getUsername() != null ? user.getUsername() : "" %>"
                           placeholder="Enter username">
                </div>

                <div>
                    <label class="form-label">Role</label>
                    <select name="role" class="form-select">
                        <option value="">-- Select Role --</option>
                        <option value="COURSE_ADMIN" <%= "COURSE_ADMIN".equals(user.getRole()) ? "selected" : "" %>>
                            COURSE_ADMIN
                        </option>
                        <option value="ACADEMIC_OFFICER" <%= "ACADEMIC_OFFICER".equals(user.getRole()) ? "selected" : "" %>>
                            ACADEMIC_OFFICER
                        </option>
                    </select>
                </div>

                <% if (isCreate) { %>
                    <div class="crs-form-full">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control"
                               placeholder="Enter temporary password">
                    </div>
                <% } else { %>
                    <div class="crs-form-full">
                        <label class="form-label">Status</label>
                        <select name="status" class="form-select">
                            <option value="ACTIVE" <%= "ACTIVE".equals(user.getStatus()) ? "selected" : "" %>>ACTIVE</option>
                            <option value="INACTIVE" <%= "INACTIVE".equals(user.getStatus()) ? "selected" : "" %>>INACTIVE</option>
                        </select>
                    </div>
                <% } %>
            </div>

            <div class="crs-footer-space d-flex gap-2 flex-wrap">
                <button type="submit" class="btn btn-primary">
                    <%= isCreate ? "Create User" : "Update User" %>
                </button>
                <a href="<%= request.getContextPath() %>/users" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>