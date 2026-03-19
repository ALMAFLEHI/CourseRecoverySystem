package com.crs.model;

import java.sql.Timestamp;

public class PerformanceReportLog {

    private int reportLogId;
    private String studentId;
    private String semester;
    private String academicYear;
    private int generatedBy;
    private String summaryNote;
    private Timestamp generatedAt;

    public PerformanceReportLog() {
    }

    public PerformanceReportLog(int reportLogId, String studentId, String semester,
                                String academicYear, int generatedBy,
                                String summaryNote, Timestamp generatedAt) {
        this.reportLogId = reportLogId;
        this.studentId = studentId;
        this.semester = semester;
        this.academicYear = academicYear;
        this.generatedBy = generatedBy;
        this.summaryNote = summaryNote;
        this.generatedAt = generatedAt;
    }

    public int getReportLogId() {
        return reportLogId;
    }

    public void setReportLogId(int reportLogId) {
        this.reportLogId = reportLogId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public int getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(int generatedBy) {
        this.generatedBy = generatedBy;
    }

    public String getSummaryNote() {
        return summaryNote;
    }

    public void setSummaryNote(String summaryNote) {
        this.summaryNote = summaryNote;
    }

    public Timestamp getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Timestamp generatedAt) {
        this.generatedAt = generatedAt;
    }

    @Override
    public String toString() {
        return "PerformanceReportLog{" +
                "reportLogId=" + reportLogId +
                ", studentId='" + studentId + '\'' +
                ", semester='" + semester + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", generatedBy=" + generatedBy +
                ", summaryNote='" + summaryNote + '\'' +
                ", generatedAt=" + generatedAt +
                '}';
    }
}