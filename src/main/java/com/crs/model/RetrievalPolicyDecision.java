package com.crs.model;

import java.sql.Timestamp;

public class RetrievalPolicyDecision {

    private int decisionId;
    private String studentId;
    private String courseId;
    private int basedOnResultId;
    private int currentAttemptNo;
    private int nextAttemptNo;
    private String policyType;
    private String allowedScope;
    private boolean allowed;
    private String decisionNote;
    private Integer decidedBy;
    private Timestamp decidedAt;

    public RetrievalPolicyDecision() {
    }

    public RetrievalPolicyDecision(int decisionId, String studentId, String courseId, int basedOnResultId,
                                   int currentAttemptNo, int nextAttemptNo, String policyType,
                                   String allowedScope, boolean allowed, String decisionNote,
                                   Integer decidedBy, Timestamp decidedAt) {
        this.decisionId = decisionId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.basedOnResultId = basedOnResultId;
        this.currentAttemptNo = currentAttemptNo;
        this.nextAttemptNo = nextAttemptNo;
        this.policyType = policyType;
        this.allowedScope = allowedScope;
        this.allowed = allowed;
        this.decisionNote = decisionNote;
        this.decidedBy = decidedBy;
        this.decidedAt = decidedAt;
    }

    public int getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(int decisionId) {
        this.decisionId = decisionId;
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

    public int getCurrentAttemptNo() {
        return currentAttemptNo;
    }

    public void setCurrentAttemptNo(int currentAttemptNo) {
        this.currentAttemptNo = currentAttemptNo;
    }

    public int getNextAttemptNo() {
        return nextAttemptNo;
    }

    public void setNextAttemptNo(int nextAttemptNo) {
        this.nextAttemptNo = nextAttemptNo;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getAllowedScope() {
        return allowedScope;
    }

    public void setAllowedScope(String allowedScope) {
        this.allowedScope = allowedScope;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public String getDecisionNote() {
        return decisionNote;
    }

    public void setDecisionNote(String decisionNote) {
        this.decisionNote = decisionNote;
    }

    public Integer getDecidedBy() {
        return decidedBy;
    }

    public void setDecidedBy(Integer decidedBy) {
        this.decidedBy = decidedBy;
    }

    public Timestamp getDecidedAt() {
        return decidedAt;
    }

    public void setDecidedAt(Timestamp decidedAt) {
        this.decidedAt = decidedAt;
    }

    @Override
    public String toString() {
        return "RetrievalPolicyDecision{" +
                "decisionId=" + decisionId +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", basedOnResultId=" + basedOnResultId +
                ", currentAttemptNo=" + currentAttemptNo +
                ", nextAttemptNo=" + nextAttemptNo +
                ", policyType='" + policyType + '\'' +
                ", allowedScope='" + allowedScope + '\'' +
                ", allowed=" + allowed +
                ", decisionNote='" + decisionNote + '\'' +
                ", decidedBy=" + decidedBy +
                ", decidedAt=" + decidedAt +
                '}';
    }
}