package com.crs.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecoveryProgressLog {

    private int progressLogId;
    private int planId;
    private Integer milestoneId;
    private int updatedBy;
    private String note;
    private BigDecimal gradeEntry;
    private String statusAfterUpdate;
    private Timestamp createdAt;

    public RecoveryProgressLog() {
    }

    public RecoveryProgressLog(int progressLogId, int planId, Integer milestoneId, int updatedBy,
                               String note, BigDecimal gradeEntry, String statusAfterUpdate,
                               Timestamp createdAt) {
        this.progressLogId = progressLogId;
        this.planId = planId;
        this.milestoneId = milestoneId;
        this.updatedBy = updatedBy;
        this.note = note;
        this.gradeEntry = gradeEntry;
        this.statusAfterUpdate = statusAfterUpdate;
        this.createdAt = createdAt;
    }

    public int getProgressLogId() {
        return progressLogId;
    }

    public void setProgressLogId(int progressLogId) {
        this.progressLogId = progressLogId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public Integer getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Integer milestoneId) {
        this.milestoneId = milestoneId;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getGradeEntry() {
        return gradeEntry;
    }

    public void setGradeEntry(BigDecimal gradeEntry) {
        this.gradeEntry = gradeEntry;
    }

    public String getStatusAfterUpdate() {
        return statusAfterUpdate;
    }

    public void setStatusAfterUpdate(String statusAfterUpdate) {
        this.statusAfterUpdate = statusAfterUpdate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RecoveryProgressLog{" +
                "progressLogId=" + progressLogId +
                ", planId=" + planId +
                ", milestoneId=" + milestoneId +
                ", updatedBy=" + updatedBy +
                ", note='" + note + '\'' +
                ", gradeEntry=" + gradeEntry +
                ", statusAfterUpdate='" + statusAfterUpdate + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}