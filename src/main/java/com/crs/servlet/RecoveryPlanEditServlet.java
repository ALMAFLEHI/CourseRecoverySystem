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

@WebServlet("/recovery/plans/edit")
public class RecoveryPlanEditServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();
    private final StudentService studentService = new StudentService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer planId = recoveryService.parseInteger(req.getParameter("id"));

        if (planId == null || planId <= 0) {
            resp.sendRedirect(req.getContextPath() + "/recovery/plans");
            return;
        }

        RecoveryPlan plan = recoveryService.getRecoveryPlanById(planId);
        if (plan == null) {
            resp.sendRedirect(req.getContextPath() + "/recovery/plans");
            return;
        }

        req.setAttribute("formMode", "edit");
        req.setAttribute("plan", plan);
        req.getRequestDispatcher("/WEB-INF/views/recovery/recovery-plan-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer planId = recoveryService.parseInteger(req.getParameter("planId"));
        String recommendationText = req.getParameter("recommendationText");
        String planStatus = req.getParameter("planStatus");

        RecoveryPlan existingPlan = recoveryService.getRecoveryPlanById(planId != null ? planId : 0);

        RecoveryPlan plan = new RecoveryPlan();
        plan.setPlanId(planId != null ? planId : 0);
        plan.setRecommendationText(recommendationText);
        plan.setPlanStatus(planStatus);

        String errorMessage = recoveryService.updateRecoveryPlan(plan);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("formMode", "edit");
            req.setAttribute("plan", plan);
            req.getRequestDispatcher("/WEB-INF/views/recovery/recovery-plan-form.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        Integer actorUserId = session != null ? (Integer) session.getAttribute("userId") : null;

        if (existingPlan != null) {
            Student student = studentService.getStudentById(existingPlan.getStudentId());
            if (student != null) {
                emailService.logRecoveryPlanEmail(student.getEmail(), existingPlan.getPlanId(), actorUserId);
            }

            auditService.logAction(
                    actorUserId,
                    "UPDATE_RECOVERY_PLAN",
                    "RECOVERY_PLAN",
                    existingPlan.getPlanId(),
                    "Updated recovery plan for student " + existingPlan.getStudentId() + ", course " + existingPlan.getCourseId()
            );
        }

        resp.sendRedirect(req.getContextPath() + "/recovery/plans");
    }
}
