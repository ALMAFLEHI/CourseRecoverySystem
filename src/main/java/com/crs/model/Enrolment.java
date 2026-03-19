package com.crs.model;

import java.sql.Timestamp;

public class Enrolment {

    private int enrolmentId;
    private String studentId;
    private int eligibilityCheckId;
    private int approvedBy;
    private String enrolmentStatus;
    private String remarks;
    private Timestamp approvedAt;

    public Enrolment() {
    }

    public Enrolment(int enrolmentId, String studentId, int eligibilityCheckId,
                     int approvedBy, String enrolmentStatus, String remarks,
                     Timestamp approvedAt) {
        this.enrolmentId = enrolmentId;
        this.studentId = studentId;
        this.eligibilityCheckId = eligibilityCheckId;
        this.approvedBy = approvedBy;
        this.enrolmentStatus = enrolmentStatus;
        this.remarks = remarks;
        this.approvedAt = approvedAt;
    }

    public int getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(int enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getEligibilityCheckId() {
        return eligibilityCheckId;
    }

    public void setEligibilityCheckId(int eligibilityCheckId) {
        this.eligibilityCheckId = eligibilityCheckId;
    }

    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getEnrolmentStatus() {
        return enrolmentStatus;
    }

    public void setEnrolmentStatus(String enrolmentStatus) {
        this.enrolmentStatus = enrolmentStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Timestamp approvedAt) {
        this.approvedAt = approvedAt;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "enrolmentId=" + enrolmentId +
                ", studentId='" + studentId + '\'' +
                ", eligibilityCheckId=" + eligibilityCheckId +
                ", approvedBy=" + approvedBy +
                ", enrolmentStatus='" + enrolmentStatus + '\'' +
                ", remarks='" + remarks + '\'' +
                ", approvedAt=" + approvedAt +
                '}';
    }
}