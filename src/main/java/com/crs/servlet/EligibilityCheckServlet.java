package com.crs.servlet;

import com.crs.model.EligibilityCheck;
import com.crs.model.Student;
import com.crs.service.AuditService;
import com.crs.service.EligibilityService;
import com.crs.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/eligibility/check")
public class EligibilityCheckServlet extends HttpServlet {

    private final EligibilityService eligibilityService = new EligibilityService();
    private final StudentService studentService = new StudentService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/WEB-INF/views/eligibility/eligibility-check.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");

        HttpSession session = req.getSession(false);
        Integer checkedBy = session != null ? (Integer) session.getAttribute("userId") : null;

        List<Student> students = studentService.getAllStudents();
        req.setAttribute("students", students);

        if (studentId == null || studentId.trim().isEmpty() || checkedBy == null) {
            req.setAttribute("errorMessage", "Student and session information are required.");
            req.getRequestDispatcher("/WEB-INF/views/eligibility/eligibility-check.jsp").forward(req, resp);
            return;
        }

        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            req.setAttribute("errorMessage", "Student not found.");
            req.getRequestDispatcher("/WEB-INF/views/eligibility/eligibility-check.jsp").forward(req, resp);
            return;
        }

        EligibilityCheck check = eligibilityService.evaluateEligibility(studentId, checkedBy);

        if (check == null) {
            req.setAttribute("errorMessage", "Failed to evaluate eligibility.");
            req.getRequestDispatcher("/WEB-INF/views/eligibility/eligibility-check.jsp").forward(req, resp);
            return;
        }

        String errorMessage = eligibilityService.saveEligibilityCheck(check);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/views/eligibility/eligibility-check.jsp").forward(req, resp);
            return;
        }

        auditService.logAction(
                checkedBy,
                "CHECK_ELIGIBILITY",
                "ELIGIBILITY_CHECK",
                null,
                "Performed eligibility check for student " + studentId
        );

        req.setAttribute("student", student);
        req.setAttribute("eligibilityCheck", check);
        req.getRequestDispatcher("/WEB-INF/views/eligibility/eligibility-check.jsp").forward(req, resp);
    }
}
