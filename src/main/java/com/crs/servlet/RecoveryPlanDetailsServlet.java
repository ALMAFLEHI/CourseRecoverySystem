package com.crs.servlet;

import com.crs.model.RecoveryMilestone;
import com.crs.model.RecoveryPlan;
import com.crs.model.RecoveryProgressLog;
import com.crs.model.StudentComponentResult;
import com.crs.service.RecoveryService;
import com.crs.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/recovery/plans/details")
public class RecoveryPlanDetailsServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();
    private final StudentService studentService = new StudentService();

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

        List<RecoveryMilestone> milestones = recoveryService.getMilestonesByPlanId(planId);
        List<RecoveryProgressLog> progressLogs = recoveryService.getProgressLogsByPlanId(planId);
        List<StudentComponentResult> failedComponentResults =
                studentService.getFailedComponentResultsByStudentId(plan.getStudentId());

        req.setAttribute("plan", plan);
        req.setAttribute("milestones", milestones);
        req.setAttribute("progressLogs", progressLogs);
        req.setAttribute("failedComponentResults", failedComponentResults);

        req.getRequestDispatcher("/WEB-INF/views/recovery/recovery-plan-details.jsp").forward(req, resp);
    }
}