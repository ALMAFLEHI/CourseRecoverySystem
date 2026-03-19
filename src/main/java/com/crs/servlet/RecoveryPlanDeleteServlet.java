package com.crs.servlet;

import com.crs.model.RecoveryPlan;
import com.crs.service.AuditService;
import com.crs.service.RecoveryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/recovery/plans/delete")
public class RecoveryPlanDeleteServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer planId = recoveryService.parseInteger(req.getParameter("id"));

        RecoveryPlan existingPlan = null;
        if (planId != null && planId > 0) {
            existingPlan = recoveryService.getRecoveryPlanById(planId);
            recoveryService.deleteRecoveryPlan(planId);
        }

        HttpSession session = req.getSession(false);
        Integer actorUserId = session != null ? (Integer) session.getAttribute("userId") : null;

        if (existingPlan != null) {
            auditService.logAction(
                    actorUserId,
                    "DELETE_RECOVERY_PLAN",
                    "RECOVERY_PLAN",
                    existingPlan.getPlanId(),
                    "Deleted recovery plan for student " + existingPlan.getStudentId() + ", course " + existingPlan.getCourseId()
            );
        }

        resp.sendRedirect(req.getContextPath() + "/recovery/plans");
    }
}
