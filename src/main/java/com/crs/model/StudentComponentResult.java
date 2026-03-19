package com.crs.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StudentComponentResult {

    private int componentResultId;
    private String studentId;
    private String courseId;
    private int componentId;
    private int attemptNo;
    private BigDecimal score;
    private String status;
    private Timestamp updatedAt;

    public StudentComponentResult() {
    }

    public StudentComponentResult(int componentResultId, String studentId, String courseId,
                                  int componentId, int attemptNo, BigDecimal score,
                                  String status, Timestamp updatedAt) {
        this.componentResultId = componentResultId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.componentId = componentId;
        this.attemptNo = attemptNo;
        this.score = score;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public int getComponentResultId() {
        return componentResultId;
    }

    public void setComponentResultId(int componentResultId) {
        this.componentResultId = componentResultId;
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

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public int getAttemptNo() {
        return attemptNo;
    }

    public void setAttemptNo(int attemptNo) {
        this.attemptNo = attemptNo;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "StudentComponentResult{" +
                "componentResultId=" + componentResultId +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", componentId=" + componentId +
                ", attemptNo=" + attemptNo +
                ", score=" + score +
                ", status='" + status + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}