package com.crs.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class EligibilityCheck {

    private int checkId;
    private String studentId;
    private BigDecimal cgpa;
    private int failedCourseCount;
    private boolean eligible;
    private String remarks;
    private int checkedBy;
    private Timestamp checkedAt;

    public EligibilityCheck() {
    }

    public EligibilityCheck(int checkId, String studentId, BigDecimal cgpa, int failedCourseCount,
                            boolean eligible, String remarks, int checkedBy, Timestamp checkedAt) {
        this.checkId = checkId;
        this.studentId = studentId;
        this.cgpa = cgpa;
        this.failedCourseCount = failedCourseCount;
        this.eligible = eligible;
        this.remarks = remarks;
        this.checkedBy = checkedBy;
        this.checkedAt = checkedAt;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public void setCgpa(BigDecimal cgpa) {
        this.cgpa = cgpa;
    }

    public int getFailedCourseCount() {
        return failedCourseCount;
    }

    public void setFailedCourseCount(int failedCourseCount) {
        this.failedCourseCount = failedCourseCount;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(int checkedBy) {
        this.checkedBy = checkedBy;
    }

    public Timestamp getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(Timestamp checkedAt) {
        this.checkedAt = checkedAt;
    }

    @Override
    public String toString() {
        return "EligibilityCheck{" +
                "checkId=" + checkId +
                ", studentId='" + studentId + '\'' +
                ", cgpa=" + cgpa +
                ", failedCourseCount=" + failedCourseCount +
                ", eligible=" + eligible +
                ", remarks='" + remarks + '\'' +
                ", checkedBy=" + checkedBy +
                ", checkedAt=" + checkedAt +
                '}';
    }
}