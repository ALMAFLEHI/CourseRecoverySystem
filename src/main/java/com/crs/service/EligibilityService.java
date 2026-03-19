package com.crs.service;

import com.crs.constants.StatusConstants;
import com.crs.dao.CourseDao;
import com.crs.dao.EligibilityDao;
import com.crs.dao.EnrolmentDao;
import com.crs.dao.ResultDao;
import com.crs.model.Course;
import com.crs.model.EligibilityCheck;
import com.crs.model.Enrolment;
import com.crs.model.StudentCourseResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EligibilityService {

    private static final BigDecimal MIN_CGPA = new BigDecimal("2.00");
    private static final int MAX_FAILED_COURSES = 3;

    private final ResultDao resultDao = new ResultDao();
    private final CourseDao courseDao = new CourseDao();
    private final EligibilityDao eligibilityDao = new EligibilityDao();
    private final EnrolmentDao enrolmentDao = new EnrolmentDao();

    public EligibilityCheck evaluateEligibility(String studentId, int checkedBy) {
        if (isBlank(studentId) || checkedBy <= 0) {
            return null;
        }

        List<StudentCourseResult> allResults = resultDao.findResultsByStudentId(studentId.trim());
        Map<String, StudentCourseResult> latestResultsByCourse = getLatestResultsByCourse(allResults);

        BigDecimal cgpa = calculateCgpa(latestResultsByCourse);
        int failedCourseCount = countFailedCourses(latestResultsByCourse);
        boolean eligible = cgpa.compareTo(MIN_CGPA) >= 0 && failedCourseCount <= MAX_FAILED_COURSES;

        EligibilityCheck check = new EligibilityCheck();
        check.setStudentId(studentId.trim());
        check.setCgpa(cgpa);
        check.setFailedCourseCount(failedCourseCount);
        check.setEligible(eligible);
        check.setRemarks(buildRemarks(cgpa, failedCourseCount, eligible));
        check.setCheckedBy(checkedBy);

        return check;
    }

    public String saveEligibilityCheck(EligibilityCheck check) {
        if (check == null || isBlank(check.getStudentId()) || check.getCgpa() == null || check.getCheckedBy() <= 0) {
            return "Eligibility check data is invalid.";
        }

        boolean inserted = eligibilityDao.insert(check);
        return inserted ? null : "Failed to save eligibility check.";
    }

    public EligibilityCheck getLatestEligibilityCheck(String studentId) {
        if (isBlank(studentId)) {
            return null;
        }
        return eligibilityDao.findLatestByStudentId(studentId.trim());
    }

    public List<EligibilityCheck> getEligibilityHistory(String studentId) {
        if (isBlank(studentId)) {
            return List.of();
        }
        return eligibilityDao.findByStudentId(studentId.trim());
    }

    public String saveEnrolmentDecision(String studentId, int approvedBy, String enrolmentStatus, String remarks) {
        if (isBlank(studentId) || approvedBy <= 0 || isBlank(enrolmentStatus)) {
            return "Enrolment data is invalid.";
        }

        EligibilityCheck latestCheck = getLatestEligibilityCheck(studentId);
        if (latestCheck == null) {
            return "No eligibility check found for this student.";
        }

        Enrolment enrolment = new Enrolment();
        enrolment.setStudentId(studentId.trim());
        enrolment.setEligibilityCheckId(latestCheck.getCheckId());
        enrolment.setApprovedBy(approvedBy);
        enrolment.setEnrolmentStatus(enrolmentStatus.trim());
        enrolment.setRemarks(remarks);
        enrolment.setApprovedAt(new Timestamp(System.currentTimeMillis()));

        boolean inserted = enrolmentDao.insert(enrolment);
        return inserted ? null : "Failed to save enrolment decision.";
    }

    public Enrolment getLatestEnrolment(String studentId) {
        if (isBlank(studentId)) {
            return null;
        }
        return enrolmentDao.findLatestByStudentId(studentId.trim());
    }

    public List<Enrolment> getEnrolmentHistory(String studentId) {
        if (isBlank(studentId)) {
            return List.of();
        }
        return enrolmentDao.findByStudentId(studentId.trim());
    }

    private Map<String, StudentCourseResult> getLatestResultsByCourse(List<StudentCourseResult> allResults) {
        Map<String, StudentCourseResult> latestResults = new LinkedHashMap<>();

        for (StudentCourseResult result : allResults) {
            StudentCourseResult existing = latestResults.get(result.getCourseId());

            if (existing == null
                    || result.getAttemptNo() > existing.getAttemptNo()
                    || (result.getAttemptNo() == existing.getAttemptNo()
                        && result.getResultId() > existing.getResultId())) {
                latestResults.put(result.getCourseId(), result);
            }
        }

        return latestResults;
    }

    private BigDecimal calculateCgpa(Map<String, StudentCourseResult> latestResultsByCourse) {
        BigDecimal totalGradePoints = BigDecimal.ZERO;
        int totalCredits = 0;

        for (StudentCourseResult result : latestResultsByCourse.values()) {
            Course course = courseDao.findById(result.getCourseId());
            if (course == null || result.getGradePoint() == null) {
                continue;
            }

            int credits = course.getCredits();
            totalGradePoints = totalGradePoints.add(result.getGradePoint().multiply(BigDecimal.valueOf(credits)));
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return totalGradePoints.divide(BigDecimal.valueOf(totalCredits), 2, RoundingMode.HALF_UP);
    }

    private int countFailedCourses(Map<String, StudentCourseResult> latestResultsByCourse) {
        int count = 0;

        for (StudentCourseResult result : latestResultsByCourse.values()) {
            if (StatusConstants.FAIL.equalsIgnoreCase(result.getFinalStatus())) {
                count++;
            }
        }

        return count;
    }

    private String buildRemarks(BigDecimal cgpa, int failedCourseCount, boolean eligible) {
        if (eligible) {
            return "Eligible: CGPA is at least 2.0 and failed courses do not exceed 3.";
        }

        boolean lowCgpa = cgpa.compareTo(MIN_CGPA) < 0;
        boolean tooManyFails = failedCourseCount > MAX_FAILED_COURSES;

        if (lowCgpa && tooManyFails) {
            return "Not eligible: CGPA is below 2.0 and failed courses exceed 3.";
        }

        if (lowCgpa) {
            return "Not eligible: CGPA is below 2.0.";
        }

        return "Not eligible: failed courses exceed 3.";
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
