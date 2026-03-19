<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.crs.model.Student" %>
<%@ page import="com.crs.model.EligibilityCheck" %>
<%
    request.setAttribute("pageTitle", "Ineligible Students - CRS");

    List<Student> ineligibleStudents = (List<Student>) request.getAttribute("ineligibleStudents");
    List<EligibilityCheck> ineligibleChecks = (List<EligibilityCheck>) request.getAttribute("ineligibleChecks");
%>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/sidebar.jspf" %>

<div class="crs-main">
    <div class="crs-topbar">
        <div>
            <h1 class="crs-page-title">Ineligible Students</h1>
            <p class="crs-page-subtitle">Students currently not meeting the progression requirements.</p>
        </div>

        <div class="d-flex gap-2 flex-wrap">
            <a href="<%= request.getContextPath() %>/eligibility/check" class="btn btn-outline-primary">Eligibility Check</a>
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
                    <th>CGPA</th>
                    <th>Failed Courses</th>
                    <th>Remarks</th>
                    <th style="min-width: 120px;">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (ineligibleStudents == null || ineligibleStudents.isEmpty()) {
                %>
                    <tr>
                        <td colspan="8">No ineligible students found.</td>
                    </tr>
                <%
                    } else {
                        for (int i = 0; i < ineligibleStudents.size(); i++) {
                            Student s = ineligibleStudents.get(i);
                            EligibilityCheck c = ineligibleChecks.get(i);
                %>
                    <tr>
                        <td><strong><%= s.getStudentId() %></strong></td>
                        <td><%= s.getFullName() %></td>
                        <td><%= s.getMajor() %></td>
                        <td><span class="badge text-bg-light border"><%= s.getStudyYear() %></span></td>
                        <td><%= c.getCgpa() %></td>
                        <td><%= c.getFailedCourseCount() %></td>
                        <td><%= c.getRemarks() %></td>
                        <td>
                            <div class="crs-actions">
                                <a href="<%= request.getContextPath() %>/eligibility/enrolment?studentId=<%= s.getStudentId() %>">
                                    View Decision
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