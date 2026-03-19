package com.crs.servlet;

import com.crs.model.RecoveryMilestone;
import com.crs.service.AuditService;
import com.crs.service.RecoveryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/recovery/milestones/delete")
public class MilestoneDeleteServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer milestoneId = recoveryService.parseInteger(req.getParameter("id"));
        Integer planId = recoveryService.parseInteger(req.getParameter("planId"));

        RecoveryMilestone existingMilestone = null;
        if (milestoneId != null && milestoneId > 0) {
            existingMilestone = recoveryService.getMilestoneById(milestoneId);
            recoveryService.deleteMilestone(milestoneId);
        }

        HttpSession session = req.getSession(false);
        Integer actorUserId = session != null ? (Integer) session.getAttribute("userId") : null;

        if (existingMilestone != null) {
            auditService.logAction(
                    actorUserId,
                    "DELETE_MILESTONE",
                    "RECOVERY_MILESTONE",
                    existingMilestone.getMilestoneId(),
                    "Deleted milestone from recovery plan ID " + existingMilestone.getPlanId()
            );
        }

        if (planId != null && planId > 0) {
            resp.sendRedirect(req.getContextPath() + "/recovery/plans/details?id=" + planId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/recovery/plans");
        }
    }
}
