package com.crs.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StudentCourseResult {

    private int resultId;
    private String studentId;
    private String courseId;
    private int attemptNo;
    private String semester;
    private String academicYear;
    private String gradeLetter;
    private BigDecimal gradePoint;
    private BigDecimal totalScore;
    private String finalStatus;
    private int creditsEarned;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public StudentCourseResult() {
    }

    public StudentCourseResult(int resultId, String studentId, String courseId, int attemptNo,
                               String semester, String academicYear, String gradeLetter,
                               BigDecimal gradePoint, BigDecimal totalScore, String finalStatus,
                               int creditsEarned, Timestamp createdAt, Timestamp updatedAt) {
        this.resultId = resultId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.attemptNo = attemptNo;
        this.semester = semester;
        this.academicYear = academicYear;
        this.gradeLetter = gradeLetter;
        this.gradePoint = gradePoint;
        this.totalScore = totalScore;
        this.finalStatus = finalStatus;
        this.creditsEarned = creditsEarned;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
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

    public int getAttemptNo() {
        return attemptNo;
    }

    public void setAttemptNo(int attemptNo) {
        this.attemptNo = attemptNo;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getGradeLetter() {
        return gradeLetter;
    }

    public void setGradeLetter(String gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    public BigDecimal getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(BigDecimal gradePoint) {
        this.gradePoint = gradePoint;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    public int getCreditsEarned() {
        return creditsEarned;
    }

    public void setCreditsEarned(int creditsEarned) {
        this.creditsEarned = creditsEarned;
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
        return "StudentCourseResult{" +
                "resultId=" + resultId +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", attemptNo=" + attemptNo +
                ", semester='" + semester + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", gradeLetter='" + gradeLetter + '\'' +
                ", gradePoint=" + gradePoint +
                ", totalScore=" + totalScore +
                ", finalStatus='" + finalStatus + '\'' +
                ", creditsEarned=" + creditsEarned +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}