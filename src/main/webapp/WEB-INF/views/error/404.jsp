<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setAttribute("pageTitle", "Page Not Found - CRS");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-card" style="max-width: 760px;">
        <h1 class="crs-page-title mb-3">404 - Page Not Found</h1>
        <p class="crs-page-subtitle mb-4">
            The page you are trying to reach does not exist or may have been moved.
        </p>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/dashboard" class="btn btn-primary">Back to Dashboard</a>
            <a href="<%= request.getContextPath() %>/logout" class="btn btn-outline-secondary">Logout</a>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>