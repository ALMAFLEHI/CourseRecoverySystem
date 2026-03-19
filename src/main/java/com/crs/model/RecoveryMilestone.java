package com.crs.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class RecoveryMilestone {

    private int milestoneId;
    private int planId;
    private String weekLabel;
    private String taskTitle;
    private String description;
    private Date dueDate;
    private String status;
    private BigDecimal progressPercent;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public RecoveryMilestone() {
    }

    public RecoveryMilestone(int milestoneId, int planId, String weekLabel, String taskTitle,
                             String description, Date dueDate, String status,
                             BigDecimal progressPercent, Timestamp createdAt, Timestamp updatedAt) {
        this.milestoneId = milestoneId;
        this.planId = planId;
        this.weekLabel = weekLabel;
        this.taskTitle = taskTitle;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.progressPercent = progressPercent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getWeekLabel() {
        return weekLabel;
    }

    public void setWeekLabel(String weekLabel) {
        this.weekLabel = weekLabel;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(BigDecimal progressPercent) {
        this.progressPercent = progressPercent;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "RecoveryMilestone{" +
                "milestoneId=" + milestoneId +
                ", planId=" + planId +
                ", weekLabel='" + weekLabel + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", progressPercent=" + progressPercent +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}