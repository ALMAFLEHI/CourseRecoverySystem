package com.crs.servlet;

import com.crs.model.RecoveryPlan;
import com.crs.model.Student;
import com.crs.service.AuditService;
import com.crs.service.EmailService;
import com.crs.service.RecoveryService;
import com.crs.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/recovery/plans/create")
public class RecoveryPlanCreateServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();
    private final StudentService studentService = new StudentService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("formMode", "create");

        RecoveryPlan plan = new RecoveryPlan();

        String studentId = req.getParameter("studentId");
        String courseId = req.getParameter("courseId");
        Integer basedOnResultId = recoveryService.parseInteger(req.getParameter("basedOnResultId"));
        Integer attemptNo = recoveryService.parseInteger(req.getParameter("attemptNo"));
        String policyType = req.getParameter("policyType");

        boolean policyLocked = studentId != null && !studentId.trim().isEmpty()
                && courseId != null && !courseId.trim().isEmpty()
                && basedOnResultId != null && basedOnResultId > 0
                && attemptNo != null && attemptNo > 0;

        if (policyLocked) {
            plan.setStudentId(studentId);
            plan.setCourseId(courseId);
            plan.setBasedOnResultId(basedOnResultId);
            plan.setAttemptNo(attemptNo);

            req.setAttribute("plan", plan);
            req.setAttribute("policyLocked", true);
            req.setAttribute("policyNote", buildPolicyNote(policyType));
        }

        req.getRequestDispatcher("/WEB-INF/views/recovery/recovery-plan-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String courseId = req.getParameter("courseId");
        Integer basedOnResultId = recoveryService.parseInteger(req.getParameter("basedOnResultId"));
        Integer attemptNo = recoveryService.parseInteger(req.getParameter("attemptNo"));
        String recommendationText = req.getParameter("recommendationText");
        String planStatus = req.getParameter("planStatus");

        HttpSession session = req.getSession(false);
        Integer createdBy = session != null ? (Integer) session.getAttribute("userId") : null;

        RecoveryPlan plan = new RecoveryPlan();
        plan.setStudentId(studentId);
        plan.setCourseId(courseId);
        plan.setBasedOnResultId(basedOnResultId != null ? basedOnResultId : 0);
        plan.setAttemptNo(attemptNo != null ? attemptNo : 0);
        plan.setRecommendationText(recommendationText);
        plan.setPlanStatus(planStatus);
        plan.setCreatedBy(createdBy != null ? createdBy : 0);

        String errorMessage = recoveryService.createRecoveryPlan(plan);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("formMode", "create");
            req.setAttribute("plan", plan);
            req.setAttribute("policyLocked", true);
            req.setAttribute("policyNote", "Server-side policy validation is active for this request.");
            req.getRequestDispatcher("/WEB-INF/views/recovery/recovery-plan-form.jsp").forward(req, resp);
            return;
        }

        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            emailService.logRecoveryPlanEmail(student.getEmail(), null, createdBy);
        }

        auditService.logAction(
                createdBy,
                "CREATE_RECOVERY_PLAN",
                "RECOVERY_PLAN",
                null,
                "Created recovery plan for student " + studentId + ", course " + courseId
        );

        resp.sendRedirect(req.getContextPath() + "/recovery/plans");
    }

    private String buildPolicyNote(String policyType) {
        if ("SECOND_ATTEMPT_FAILED_COMPONENTS_ONLY".equals(policyType)) {
            return "Policy applied: Second attempt allows only failed component(s) to be retaken.";
        }

        if ("THIRD_ATTEMPT_ALL_COMPONENTS".equals(policyType)) {
            return "Policy applied: Third attempt requires all course components to be taken again.";
        }

        if ("NOT_ALLOWED_MAX_ATTEMPTS_REACHED".equals(policyType)) {
            return "Policy applied: Maximum attempts reached. Recovery plan creation should not proceed.";
        }

        return "Policy-linked recovery creation is enabled for this request.";
    }
}