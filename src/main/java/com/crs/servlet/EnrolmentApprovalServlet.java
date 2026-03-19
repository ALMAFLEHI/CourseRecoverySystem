package com.crs.servlet;

import com.crs.model.EligibilityCheck;
import com.crs.model.Enrolment;
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

@WebServlet("/eligibility/enrolment")
public class EnrolmentApprovalServlet extends HttpServlet {

    private final EligibilityService eligibilityService = new EligibilityService();
    private final StudentService studentService = new StudentService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");

        if (studentId == null || studentId.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/eligibility/check");
            return;
        }

        Student student = studentService.getStudentById(studentId);
        EligibilityCheck latestCheck = eligibilityService.getLatestEligibilityCheck(studentId);
        Enrolment latestEnrolment = eligibilityService.getLatestEnrolment(studentId);

        if (student == null || latestCheck == null) {
            resp.sendRedirect(req.getContextPath() + "/eligibility/check");
            return;
        }

        req.setAttribute("student", student);
        req.setAttribute("latestCheck", latestCheck);
        req.setAttribute("latestEnrolment", latestEnrolment);
        req.getRequestDispatcher("/WEB-INF/views/eligibility/enrolment-approval.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String enrolmentStatus = req.getParameter("enrolmentStatus");
        String remarks = req.getParameter("remarks");

        HttpSession session = req.getSession(false);
        Integer approvedBy = session != null ? (Integer) session.getAttribute("userId") : null;

        if (studentId == null || studentId.trim().isEmpty() || approvedBy == null) {
            resp.sendRedirect(req.getContextPath() + "/eligibility/check");
            return;
        }

        String errorMessage = eligibilityService.saveEnrolmentDecision(studentId, approvedBy, enrolmentStatus, remarks);

        if (errorMessage != null) {
            Student student = studentService.getStudentById(studentId);
            EligibilityCheck latestCheck = eligibilityService.getLatestEligibilityCheck(studentId);
            Enrolment latestEnrolment = eligibilityService.getLatestEnrolment(studentId);

            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("student", student);
            req.setAttribute("latestCheck", latestCheck);
            req.setAttribute("latestEnrolment", latestEnrolment);
            req.getRequestDispatcher("/WEB-INF/views/eligibility/enrolment-approval.jsp").forward(req, resp);
            return;
        }

        auditService.logAction(
                approvedBy,
                "SAVE_ENROLMENT_DECISION",
                "ENROLMENT",
                null,
                "Saved enrolment decision for student " + studentId + " with status " + enrolmentStatus
        );

        resp.sendRedirect(req.getContextPath() + "/eligibility/enrolment?studentId=" + studentId);
    }
}
