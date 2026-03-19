package com.crs.servlet;

import com.crs.model.Student;
import com.crs.model.StudentCourseResult;
import com.crs.service.ReportService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/reports/print")
public class ReportPrintServlet extends HttpServlet {

    private final ReportService reportService = new ReportService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String semester = req.getParameter("semester");
        String academicYear = req.getParameter("academicYear");

        if (studentId == null || studentId.trim().isEmpty()
                || semester == null || semester.trim().isEmpty()
                || academicYear == null || academicYear.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/reports");
            return;
        }

        Student student = reportService.getStudentById(studentId);
        if (student == null) {
            resp.sendRedirect(req.getContextPath() + "/reports");
            return;
        }

        List<StudentCourseResult> results = reportService.getFilteredResults(studentId, semester, academicYear);
        BigDecimal averageGradePoint = reportService.calculateAverageGradePoint(results);
        String summaryNote = reportService.buildSummaryNote(student, results);

        req.setAttribute("student", student);
        req.setAttribute("results", results);
        req.setAttribute("semester", semester);
        req.setAttribute("academicYear", academicYear);
        req.setAttribute("averageGradePoint", averageGradePoint);
        req.setAttribute("summaryNote", summaryNote);

        req.getRequestDispatcher("/WEB-INF/views/reports/report-print.jsp").forward(req, resp);
    }
}
