package com.crs.servlet;

import com.crs.model.RecoveryMilestone;
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
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/recovery/milestones/save")
public class MilestoneSaveServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();
    private final StudentService studentService = new StudentService();
    private final EmailService emailService = new EmailService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer planId = recoveryService.parseInteger(req.getParameter("planId"));
        Integer milestoneId = recoveryService.parseInteger(req.getParameter("id"));

        if (planId == null || planId <= 0) {
            resp.sendRedirect(req.getContextPath() + "/recovery/plans");
            return;
        }

        RecoveryMilestone milestone = new RecoveryMilestone();
        milestone.setPlanId(planId);

        if (milestoneId != null && milestoneId > 0) {
            RecoveryMilestone existing = recoveryService.getMilestoneById(milestoneId);
            if (existing != null) {
                milestone = existing;
            }
        }

        req.setAttribute("milestone", milestone);
        req.getRequestDispatcher("/WEB-INF/views/recovery/milestone-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer milestoneId = recoveryService.parseInteger(req.getParameter("milestoneId"));
        Integer planId = recoveryService.parseInteger(req.getParameter("planId"));
        String weekLabel = req.getParameter("weekLabel");
        String taskTitle = req.getParameter("taskTitle");
        String description = req.getParameter("description");
        Date dueDate = recoveryService.parseSqlDate(req.getParameter("dueDate"));
        String status = req.getParameter("status");
        BigDecimal progressPercent = recoveryService.parseBigDecimal(req.getParameter("progressPercent"));

        boolean isEdit = milestoneId != null && milestoneId > 0;

        RecoveryMilestone milestone = new RecoveryMilestone();
        milestone.setMilestoneId(isEdit ? milestoneId : 0);
        milestone.setPlanId(planId != null ? planId : 0);
        milestone.setWeekLabel(weekLabel);
        milestone.setTaskTitle(taskTitle);
        milestone.setDescription(description);
        milestone.setDueDate(dueDate);
        milestone.setStatus(status);
        milestone.setProgressPercent(progressPercent);

        String errorMessage = recoveryService.saveMilestone(milestone);

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("milestone", milestone);
            req.getRequestDispatcher("/WEB-INF/views/recovery/milestone-form.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        Integer actorUserId = session != null ? (Integer) session.getAttribute("userId") : null;

        RecoveryPlan plan = recoveryService.getRecoveryPlanById(milestone.getPlanId());
        if (plan != null) {
            Student student = studentService.getStudentById(plan.getStudentId());
            if (student != null) {
                Integer relatedMilestoneId = isEdit ? milestone.getMilestoneId() : null;
                emailService.logMilestoneReminderEmail(student.getEmail(), relatedMilestoneId, actorUserId);
            }

            auditService.logAction(
                    actorUserId,
                    isEdit ? "UPDATE_MILESTONE" : "ADD_MILESTONE",
                    "RECOVERY_MILESTONE",
                    isEdit ? milestone.getMilestoneId() : null,
                    (isEdit ? "Updated" : "Added") + " milestone for recovery plan ID " + milestone.getPlanId()
            );
        }

        resp.sendRedirect(req.getContextPath() + "/recovery/plans/details?id=" + milestone.getPlanId());
    }
}
