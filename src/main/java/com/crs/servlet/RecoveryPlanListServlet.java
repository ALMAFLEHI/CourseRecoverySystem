package com.crs.servlet;

import com.crs.model.RecoveryPlan;
import com.crs.service.RecoveryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/recovery/plans")
public class RecoveryPlanListServlet extends HttpServlet {

    private final RecoveryService recoveryService = new RecoveryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RecoveryPlan> plans = recoveryService.getAllRecoveryPlans();
        req.setAttribute("plans", plans);
        req.getRequestDispatcher("/WEB-INF/views/recovery/recovery-plan-list.jsp").forward(req, resp);
    }
}