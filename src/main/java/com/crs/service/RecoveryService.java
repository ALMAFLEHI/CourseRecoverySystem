package com.crs.service;

import com.crs.dao.RecoveryMilestoneDao;
import com.crs.dao.RecoveryPlanDao;
import com.crs.dao.RecoveryProgressLogDao;
import com.crs.model.RecoveryMilestone;
import com.crs.model.RecoveryPlan;
import com.crs.model.RecoveryProgressLog;
import com.crs.model.RetrievalPolicyEvaluationResult;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class RecoveryService {

    private final RecoveryPlanDao recoveryPlanDao = new RecoveryPlanDao();
    private final RecoveryMilestoneDao recoveryMilestoneDao = new RecoveryMilestoneDao();
    private final RecoveryProgressLogDao recoveryProgressLogDao = new RecoveryProgressLogDao();
    private final RetrievalPolicyService retrievalPolicyService = new RetrievalPolicyService();

    public List<RecoveryPlan> getAllRecoveryPlans() {
        return recoveryPlanDao.findAll();
    }

    public RecoveryPlan getRecoveryPlanById(int planId) {
        if (planId <= 0) {
            return null;
        }
        return recoveryPlanDao.findById(planId);
    }

    public List<RecoveryPlan> getRecoveryPlansByStudentId(String studentId) {
        if (isBlank(studentId)) {
            return List.of();
        }
        return recoveryPlanDao.findByStudentId(studentId.trim());
    }

    public List<RecoveryMilestone> getMilestonesByPlanId(int planId) {
        if (planId <= 0) {
            return List.of();
        }
        return recoveryMilestoneDao.findByPlanId(planId);
    }

    public List<RecoveryProgressLog> getProgressLogsByPlanId(int planId) {
        if (planId <= 0) {
            return List.of();
        }
        return recoveryProgressLogDao.findByPlanId(planId);
    }

    public String createRecoveryPlan(RecoveryPlan plan) {
        if (plan == null) {
            return "Recovery plan data is missing.";
        }

        if (isBlank(plan.getStudentId()) || isBlank(plan.getCourseId())
                || plan.getBasedOnResultId() <= 0 || plan.getAttemptNo() <= 0
                || isBlank(plan.getRecommendationText()) || isBlank(plan.getPlanStatus())
                || plan.getCreatedBy() <= 0) {
            return "All recovery plan fields are required.";
        }

        String policyValidationError = retrievalPolicyService.validateRecoveryPlanRequest(
                plan.getStudentId(),
                plan.getCourseId(),
                plan.getBasedOnResultId(),
                plan.getAttemptNo()
        );

        if (policyValidationError != null) {
            return policyValidationError;
        }

        if (recoveryPlanDao.existsByStudentCourseAttempt(
                plan.getStudentId(),
                plan.getCourseId(),
                plan.getAttemptNo())) {
            return "A recovery plan already exists for this student, course, and attempt.";
        }

        boolean inserted = recoveryPlanDao.insert(plan);
        if (!inserted) {
            return "Failed to create recovery plan.";
        }

        RetrievalPolicyEvaluationResult evaluation =
                retrievalPolicyService.evaluateAndSavePolicy(
                        plan.getStudentId(),
                        plan.getCourseId(),
                        plan.getCreatedBy()
                );

        if (evaluation == null || evaluation.getDecision() == null) {
            return null;
        }

        return null;
    }

    public String updateRecoveryPlan(RecoveryPlan plan) {
        if (plan == null || plan.getPlanId() <= 0
                || isBlank(plan.getRecommendationText()) || isBlank(plan.getPlanStatus())) {
            return "Recovery plan update data is invalid.";
        }

        boolean updated = recoveryPlanDao.update(plan);
        return updated ? null : "Failed to update recovery plan.";
    }

    public boolean deleteRecoveryPlan(int planId) {
        if (planId <= 0) {
            return false;
        }
        return recoveryPlanDao.delete(planId);
    }

    public RecoveryMilestone getMilestoneById(int milestoneId) {
        if (milestoneId <= 0) {
            return null;
        }
        return recoveryMilestoneDao.findById(milestoneId);
    }

    public String saveMilestone(RecoveryMilestone milestone) {
        if (milestone == null || milestone.getPlanId() <= 0
                || isBlank(milestone.getWeekLabel()) || isBlank(milestone.getTaskTitle())
                || milestone.getDueDate() == null || isBlank(milestone.getStatus())
                || milestone.getProgressPercent() == null) {
            return "All milestone fields are required.";
        }

        if (milestone.getProgressPercent().compareTo(BigDecimal.ZERO) < 0
                || milestone.getProgressPercent().compareTo(new BigDecimal("100")) > 0) {
            return "Progress percent must be between 0 and 100.";
        }

        boolean success;
        if (milestone.getMilestoneId() > 0) {
            success = recoveryMilestoneDao.update(milestone);
            return success ? null : "Failed to update milestone.";
        } else {
            success = recoveryMilestoneDao.insert(milestone);
            return success ? null : "Failed to add milestone.";
        }
    }

    public boolean deleteMilestone(int milestoneId) {
        if (milestoneId <= 0) {
            return false;
        }
        return recoveryMilestoneDao.delete(milestoneId);
    }

    public String addProgressLog(RecoveryProgressLog log) {
        if (log == null || log.getPlanId() <= 0 || log.getUpdatedBy() <= 0
                || isBlank(log.getNote())) {
            return "Progress log data is invalid.";
        }

        boolean inserted = recoveryProgressLogDao.insert(log);
        return inserted ? null : "Failed to add progress log.";
    }

    public Date parseSqlDate(String value) {
        try {
            return (value == null || value.trim().isEmpty()) ? null : Date.valueOf(value.trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public BigDecimal parseBigDecimal(String value) {
        try {
            return (value == null || value.trim().isEmpty()) ? null : new BigDecimal(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Integer parseInteger(String value) {
        try {
            return (value == null || value.trim().isEmpty()) ? null : Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}