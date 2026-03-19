package com.crs.servlet;

import com.crs.model.RecoveryMilestone;
import com.crs.model.RecoveryProgressLog;
import com.crs.service.AuditService;
import com.crs.service.RecoveryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/recovery/progress/add")
public class ProgressEntryServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer planId = recoveryService.parseInteger(req.getParameter("planId"));

        if (planId == null || planId <= 0) {
            resp.sendRedirect(req.getContextPath() + "/recovery/plans");
            return;
        }

        List<RecoveryMilestone> milestones = recoveryService.getMilestonesByPlanId(planId);
        req.setAttribute("planId", planId);
        req.setAttribute("milestones", milestones);
        req.getRequestDispatcher("/WEB-INF/views/recovery/progress-entry.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer planId = recoveryService.parseInteger(req.getParameter("planId"));
        Integer milestoneId = recoveryService.parseInteger(req.getParameter("milestoneId"));
        String note = req.getParameter("note");
        BigDecimal gradeEntry = recoveryService.parseBigDecimal(req.getParameter("gradeEntry"));
        String statusAfterUpdate = req.getParameter("statusAfterUpdate");

        HttpSession session = req.getSession(false);
        Integer updatedBy = session != null ? (Integer) session.getAttribute("userId") : null;

        RecoveryProgressLog log = new RecoveryProgressLog();
        log.setPlanId(planId != null ? planId : 0);
        log.setMilestoneId(milestoneId);
        log.setUpdatedBy(updatedBy != null ? updatedBy : 0);
        log.setNote(note);
        log.setGradeEntry(gradeEntry);
        log.setStatusAfterUpdate(statusAfterUpdate);

        String errorMessage = recoveryService.addProgressLog(log);

        if (errorMessage != null) {
            List<RecoveryMilestone> milestones = recoveryService.getMilestonesByPlanId(log.getPlanId());
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("planId", log.getPlanId());
            req.setAttribute("milestones", milestones);
            req.getRequestDispatcher("/WEB-INF/views/recovery/progress-entry.jsp").forward(req, resp);
            return;
        }

        auditService.logAction(
                updatedBy,
                "ADD_PROGRESS_LOG",
                "RECOVERY_PROGRESS_LOG",
                null,
                "Added progress entry for recovery plan ID " + log.getPlanId()
        );

        resp.sendRedirect(req.getContextPath() + "/recovery/plans/details?id=" + log.getPlanId());
    }
}
