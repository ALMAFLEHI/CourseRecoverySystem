package com.crs.service;

import com.crs.dao.PerformanceReportLogDao;
import com.crs.dao.ResultDao;
import com.crs.dao.StudentDao;
import com.crs.model.PerformanceReportLog;
import com.crs.model.Student;
import com.crs.model.StudentCourseResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private final StudentDao studentDao = new StudentDao();
    private final ResultDao resultDao = new ResultDao();
    private final PerformanceReportLogDao performanceReportLogDao = new PerformanceReportLogDao();

    public Student getStudentById(String studentId) {
        if (isBlank(studentId)) {
            return null;
        }
        return studentDao.findById(studentId.trim());
    }

    public List<StudentCourseResult> getFilteredResults(String studentId, String semester, String academicYear) {
        if (isBlank(studentId)) {
            return List.of();
        }

        List<StudentCourseResult> allResults = resultDao.findResultsByStudentId(studentId.trim());
        List<StudentCourseResult> filteredResults = new ArrayList<>();

        for (StudentCourseResult result : allResults) {
            boolean semesterMatches = isBlank(semester) || semester.equalsIgnoreCase(result.getSemester());
            boolean yearMatches = isBlank(academicYear) || academicYear.equalsIgnoreCase(result.getAcademicYear());

            if (semesterMatches && yearMatches) {
                filteredResults.add(result);
            }
        }

        return filteredResults;
    }

    public BigDecimal calculateAverageGradePoint(List<StudentCourseResult> results) {
        if (results == null || results.isEmpty()) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal total = BigDecimal.ZERO;
        int count = 0;

        for (StudentCourseResult result : results) {
            if (result.getGradePoint() != null) {
                total = total.add(result.getGradePoint());
                count++;
            }
        }

        if (count == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return total.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
    }

    public String buildSummaryNote(Student student, List<StudentCourseResult> results) {
        if (student == null) {
            return "No student selected.";
        }

        if (results == null || results.isEmpty()) {
            return "No academic results found for the selected filters.";
        }

        BigDecimal avgGradePoint = calculateAverageGradePoint(results);
        return "Report generated for " + student.getFullName()
                + " with " + results.size()
                + " result record(s). Average grade point: " + avgGradePoint + ".";
    }

    public String saveReportLog(String studentId, String semester, String academicYear, int generatedBy, String summaryNote) {
        if (isBlank(studentId) || isBlank(semester) || isBlank(academicYear) || generatedBy <= 0) {
            return "Report log data is invalid.";
        }

        PerformanceReportLog log = new PerformanceReportLog();
        log.setStudentId(studentId.trim());
        log.setSemester(semester.trim());
        log.setAcademicYear(academicYear.trim());
        log.setGeneratedBy(generatedBy);
        log.setSummaryNote(summaryNote);

        boolean inserted = performanceReportLogDao.insert(log);
        return inserted ? null : "Failed to save report log.";
    }

    public List<PerformanceReportLog> getReportHistory(String studentId) {
        if (isBlank(studentId)) {
            return List.of();
        }
        return performanceReportLogDao.findByStudentId(studentId.trim());
    }

    public PerformanceReportLog getLatestReportLog(String studentId) {
        if (isBlank(studentId)) {
            return null;
        }
        return performanceReportLogDao.findLatestByStudentId(studentId.trim());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
