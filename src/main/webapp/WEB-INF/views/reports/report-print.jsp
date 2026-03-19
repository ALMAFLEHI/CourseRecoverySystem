<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.crs.model.Student" %>
<%@ page import="com.crs.model.StudentCourseResult" %>
<%
    Student student = (Student) request.getAttribute("student");
    List<StudentCourseResult> results = (List<StudentCourseResult>) request.getAttribute("results");
    String semester = (String) request.getAttribute("semester");
    String academicYear = (String) request.getAttribute("academicYear");
    BigDecimal averageGradePoint = (BigDecimal) request.getAttribute("averageGradePoint");
    String summaryNote = (String) request.getAttribute("summaryNote");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Print Report - CRS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            color: #111827;
            margin: 24px;
        }
        h1, h2, h3 {
            margin-bottom: 12px;
        }
        .section {
            margin-bottom: 24px;
        }
        .info-grid {
            display: grid;
            grid-template-columns: repeat(2, minmax(240px, 1fr));
            gap: 12px;
            margin-bottom: 16px;
        }
        .info-box {
            border: 1px solid #d1d5db;
            padding: 10px 12px;
            border-radius: 8px;
            background: #f9fafb;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #d1d5db;
            padding: 10px;
            text-align: left;
        }
        th {
            background: #f3f4f6;
        }
        @media print {
            body {
                margin: 0;
            }
        }
    </style>
</head>
<body>
    <h1>Academic Performance Report</h1>

    <% if (student != null) { %>
        <div class="section">
            <h3>Student Information</h3>
            <div class="info-grid">
                <div class="info-box"><strong>Student ID:</strong> <%= student.getStudentId() %></div>
                <div class="info-box"><strong>Full Name:</strong> <%= student.getFullName() %></div>
                <div class="info-box"><strong>Major:</strong> <%= student.getMajor() %></div>
                <div class="info-box"><strong>Study Year:</strong> <%= student.getStudyYear() %></div>
            </div>
        </div>
    <% } %>

    <div class="section">
        <h3>Filters</h3>
        <div class="info-grid">
            <div class="info-box"><strong>Semester:</strong> <%= semester %></div>
            <div class="info-box"><strong>Academic Year:</strong> <%= academicYear %></div>
        </div>
    </div>

    <div class="section">
        <h3>Results</h3>
        <table>
            <tr>
                <th>Course ID</th>
                <th>Attempt</th>
                <th>Grade</th>
                <th>Grade Point</th>
                <th>Total Score</th>
                <th>Status</th>
                <th>Credits Earned</th>
            </tr>
            <%
                if (results == null || results.isEmpty()) {
            %>
                <tr>
                    <td colspan="7">No academic results found for the selected filters.</td>
                </tr>
            <%
                } else {
                    for (StudentCourseResult result : results) {
            %>
                <tr>
                    <td><%= result.getCourseId() %></td>
                    <td><%= result.getAttemptNo() %></td>
                    <td><%= result.getGradeLetter() %></td>
                    <td><%= result.getGradePoint() %></td>
                    <td><%= result.getTotalScore() %></td>
                    <td><%= result.getFinalStatus() %></td>
                    <td><%= result.getCreditsEarned() %></td>
                </tr>
            <%
                    }
                }
            %>
        </table>
    </div>

    <div class="section">
        <h3>Summary</h3>
        <div class="info-grid">
            <div class="info-box"><strong>Average Grade Point:</strong> <%= averageGradePoint %></div>
            <div class="info-box"><strong>Summary Note:</strong> <%= summaryNote %></div>
        </div>
    </div>

    <script>
        window.print();
    </script>
</body>
</html>