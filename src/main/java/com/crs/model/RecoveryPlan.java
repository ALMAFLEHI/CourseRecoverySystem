package com.crs.model;

import java.sql.Timestamp;

public class RecoveryPlan {

    private int planId;
    private String studentId;
    private String courseId;
    private int basedOnResultId;
    private int attemptNo;
    private String recommendationText;
    private String planStatus;
    private int createdBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public RecoveryPlan() {
    }

    public RecoveryPlan(int planId, String studentId, String courseId, int basedOnResultId,
                        int attemptNo, String recommendationText, String planStatus,
                        int createdBy, Timestamp createdAt, Timestamp updatedAt) {
        this.planId = planId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.basedOnResultId = basedOnResultId;
        this.attemptNo = attemptNo;
        this.recommendationText = recommendationText;
        this.planStatus = planStatus;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getBasedOnResultId() {
        return basedOnResultId;
    }

    public void setBasedOnResultId(int basedOnResultId) {
        this.basedOnResultId = basedOnResultId;
    }

    public int getAttemptNo() {
        return attemptNo;
    }

    public void setAttemptNo(int attemptNo) {
        this.attemptNo = attemptNo;
    }

    public String getRecommendationText() {
        return recommendationText;
    }

    public void setRecommendationText(String recommendationText) {
        this.recommendationText = recommendationText;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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
        return "RecoveryPlan{" +
                "planId=" + planId +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", basedOnResultId=" + basedOnResultId +
                ", attemptNo=" + attemptNo +
                ", recommendationText='" + recommendationText + '\'' +
                ", planStatus='" + planStatus + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}