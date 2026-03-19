package com.crs.servlet;

import com.crs.model.PerformanceReportLog;
import com.crs.model.Student;
import com.crs.model.StudentCourseResult;
import com.crs.service.AuditService;
import com.crs.service.EmailService;
import com.crs.service.ReportService;
import com.crs.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/reports/generate")
public class ReportGenerateServlet extends HttpServlet {

    private final ReportService reportService = new ReportService();
    private final StudentService studentService = new StudentService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String semester = req.getParameter("semester");
        String academicYear = req.getParameter("academicYear");

        HttpSession session = req.getSession(false);
        Integer generatedBy = session != null ? (Integer) session.getAttribute("userId") : null;

        List<Student> students = studentService.getAllStudents();
        req.setAttribute("students", students);

        if (studentId == null || studentId.trim().isEmpty()
                || semester == null || semester.trim().isEmpty()
                || academicYear == null || academicYear.trim().isEmpty()
                || generatedBy == null) {
            req.setAttribute("errorMessage", "Student, semester, and academic year are required.");
            req.getRequestDispatcher("/WEB-INF/views/reports/report-search.jsp").forward(req, resp);
            return;
        }

        Student student = reportService.getStudentById(studentId);
        if (student == null) {
            req.setAttribute("errorMessage", "Student not found.");
            req.getRequestDispatcher("/WEB-INF/views/reports/report-search.jsp").forward(req, resp);
            return;
        }

        List<StudentCourseResult> results = reportService.getFilteredResults(studentId, semester, academicYear);
        BigDecimal averageGradePoint = reportService.calculateAverageGradePoint(results);
        String summaryNote = reportService.buildSummaryNote(student, results);

        String errorMessage = reportService.saveReportLog(studentId, semester, academicYear, generatedBy, summaryNote);
        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/views/reports/report-search.jsp").forward(req, resp);
            return;
        }

        PerformanceReportLog latestLog = reportService.getLatestReportLog(studentId);

        if (latestLog != null) {
            emailService.logReportEmail(student.getEmail(), latestLog.getReportLogId(), generatedBy);

            auditService.logAction(
                    generatedBy,
                    "GENERATE_REPORT",
                    "PERFORMANCE_REPORT",
                    latestLog.getReportLogId(),
                    "Generated academic performance report for student " + studentId
            );
        }

        req.setAttribute("student", student);
        req.setAttribute("results", results);
        req.setAttribute("semester", semester);
        req.setAttribute("academicYear", academicYear);
        req.setAttribute("averageGradePoint", averageGradePoint);
        req.setAttribute("summaryNote", summaryNote);
        req.setAttribute("latestLog", latestLog);

        req.getRequestDispatcher("/WEB-INF/views/reports/report-view.jsp").forward(req, resp);
    }
}
