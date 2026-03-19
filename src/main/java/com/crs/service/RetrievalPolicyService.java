package com.crs.service;

import com.crs.dao.ComponentResultDao;
import com.crs.dao.CourseComponentDao;
import com.crs.dao.ResultDao;
import com.crs.dao.RetrievalPolicyComponentDao;
import com.crs.dao.RetrievalPolicyDecisionDao;
import com.crs.model.CourseComponent;
import com.crs.model.RetrievalPolicyComponent;
import com.crs.model.RetrievalPolicyDecision;
import com.crs.model.RetrievalPolicyEvaluationResult;
import com.crs.model.StudentComponentResult;
import com.crs.model.StudentCourseResult;

import java.util.ArrayList;
import java.util.List;

public class RetrievalPolicyService {

    private static final String POLICY_SECOND_ATTEMPT = "SECOND_ATTEMPT_FAILED_COMPONENTS_ONLY";
    private static final String POLICY_THIRD_ATTEMPT = "THIRD_ATTEMPT_ALL_COMPONENTS";
    private static final String POLICY_MAX_REACHED = "NOT_ALLOWED_MAX_ATTEMPTS_REACHED";

    private static final String SCOPE_FAILED_ONLY = "FAILED_ONLY";
    private static final String SCOPE_ALL_COMPONENTS = "ALL_COMPONENTS";
    private static final String SCOPE_NONE = "NONE";

    private final ResultDao resultDao = new ResultDao();
    private final ComponentResultDao componentResultDao = new ComponentResultDao();
    private final CourseComponentDao courseComponentDao = new CourseComponentDao();
    private final RetrievalPolicyDecisionDao retrievalPolicyDecisionDao = new RetrievalPolicyDecisionDao();
    private final RetrievalPolicyComponentDao retrievalPolicyComponentDao = new RetrievalPolicyComponentDao();

    public RetrievalPolicyEvaluationResult evaluatePolicy(String studentId, String courseId, Integer decidedBy) {
        RetrievalPolicyEvaluationResult evaluation = new RetrievalPolicyEvaluationResult();

        if (isBlank(studentId) || isBlank(courseId)) {
            evaluation.setDecision(buildBlockedDecision(
                    studentId, courseId, 0, 0, 0,
                    "INVALID_REQUEST",
                    SCOPE_NONE,
                    "Student ID and Course ID are required.",
                    decidedBy
            ));
            return evaluation;
        }

        StudentCourseResult latestResult = getLatestResult(studentId.trim(), courseId.trim());

        if (latestResult == null) {
            evaluation.setDecision(buildBlockedDecision(
                    studentId, courseId, 0, 0, 0,
                    "NO_RESULT_FOUND",
                    SCOPE_NONE,
                    "No course result found for this student and course.",
                    decidedBy
            ));
            return evaluation;
        }

        if (!"FAIL".equalsIgnoreCase(latestResult.getFinalStatus())) {
            evaluation.setDecision(buildBlockedDecision(
                    studentId, courseId, latestResult.getResultId(), latestResult.getAttemptNo(),
                    latestResult.getAttemptNo(),
                    "COURSE_ALREADY_PASSED",
                    SCOPE_NONE,
                    "Course retrieval is not allowed because the latest course result is not a fail.",
                    decidedBy
            ));
            return evaluation;
        }

        int currentAttemptNo = latestResult.getAttemptNo();

        if (currentAttemptNo >= 3) {
            evaluation.setDecision(buildBlockedDecision(
                    studentId, courseId, latestResult.getResultId(), currentAttemptNo, currentAttemptNo,
                    POLICY_MAX_REACHED,
                    SCOPE_NONE,
                    "Course retrieval is not allowed because the maximum of 3 attempts has been reached.",
                    decidedBy
            ));
            return evaluation;
        }

        if (currentAttemptNo == 1) {
            List<StudentComponentResult> failedComponents =
                    componentResultDao.findFailedByStudentCourseAndAttempt(studentId, courseId, 1);

            if (failedComponents.isEmpty()) {
                evaluation.setDecision(buildBlockedDecision(
                        studentId, courseId, latestResult.getResultId(), 1, 2,
                        POLICY_SECOND_ATTEMPT,
                        SCOPE_NONE,
                        "Second attempt rule applies, but no failed components were found for attempt 1.",
                        decidedBy
                ));
                return evaluation;
            }

            RetrievalPolicyDecision decision = buildAllowedDecision(
                    studentId, courseId, latestResult.getResultId(), 1, 2,
                    POLICY_SECOND_ATTEMPT,
                    SCOPE_FAILED_ONLY,
                    "Second attempt allowed: only failed component(s) may be retaken.",
                    decidedBy
            );

            evaluation.setDecision(decision);
            evaluation.setAllowedComponents(mapFailedResultsToPolicyComponents(failedComponents));
            return evaluation;
        }

        if (currentAttemptNo == 2) {
            List<CourseComponent> allCourseComponents = courseComponentDao.findByCourseId(courseId);

            if (allCourseComponents.isEmpty()) {
                evaluation.setDecision(buildBlockedDecision(
                        studentId, courseId, latestResult.getResultId(), 2, 3,
                        POLICY_THIRD_ATTEMPT,
                        SCOPE_NONE,
                        "Third attempt rule applies, but no course components were found.",
                        decidedBy
                ));
                return evaluation;
            }

            RetrievalPolicyDecision decision = buildAllowedDecision(
                    studentId, courseId, latestResult.getResultId(), 2, 3,
                    POLICY_THIRD_ATTEMPT,
                    SCOPE_ALL_COMPONENTS,
                    "Third attempt allowed: all assessment components must be taken again.",
                    decidedBy
            );

            evaluation.setDecision(decision);
            evaluation.setAllowedComponents(mapCourseComponentsToPolicyComponents(allCourseComponents));
            return evaluation;
        }

        evaluation.setDecision(buildBlockedDecision(
                studentId, courseId, latestResult.getResultId(), currentAttemptNo, currentAttemptNo,
                "UNSUPPORTED_ATTEMPT_STATE",
                SCOPE_NONE,
                "Unsupported retrieval attempt state.",
                decidedBy
        ));
        return evaluation;
    }

    public RetrievalPolicyEvaluationResult evaluateAndSavePolicy(String studentId, String courseId, Integer decidedBy) {
        RetrievalPolicyEvaluationResult evaluation = evaluatePolicy(studentId, courseId, decidedBy);

        if (evaluation == null || evaluation.getDecision() == null) {
            return evaluation;
        }

        RetrievalPolicyDecision decision = evaluation.getDecision();

        if (!isSavablePolicyType(decision.getPolicyType())) {
            return evaluation;
        }

        int decisionId = retrievalPolicyDecisionDao.insertAndReturnId(decision);

        if (decisionId > 0) {
            decision.setDecisionId(decisionId);

            for (RetrievalPolicyComponent component : evaluation.getAllowedComponents()) {
                component.setDecisionId(decisionId);
                retrievalPolicyComponentDao.insert(component);
            }
        }

        return evaluation;
    }

    public RetrievalPolicyDecision getLatestSavedDecision(String studentId, String courseId) {
        if (isBlank(studentId) || isBlank(courseId)) {
            return null;
        }
        return retrievalPolicyDecisionDao.findLatestByStudentAndCourse(studentId.trim(), courseId.trim());
    }

    public List<RetrievalPolicyDecision> getDecisionHistory(String studentId, String courseId) {
        if (isBlank(studentId) || isBlank(courseId)) {
            return List.of();
        }
        return retrievalPolicyDecisionDao.findByStudentAndCourse(studentId.trim(), courseId.trim());
    }

    public List<RetrievalPolicyComponent> getDecisionComponents(int decisionId) {
        if (decisionId <= 0) {
            return List.of();
        }
        return retrievalPolicyComponentDao.findByDecisionId(decisionId);
    }

    public String validateRecoveryPlanRequest(String studentId, String courseId, int basedOnResultId, int requestedAttemptNo) {
        RetrievalPolicyEvaluationResult evaluation = evaluatePolicy(studentId, courseId, null);

        if (evaluation == null || evaluation.getDecision() == null) {
            return "Unable to evaluate retrieval policy.";
        }

        RetrievalPolicyDecision decision = evaluation.getDecision();

        if (!decision.isAllowed()) {
            return decision.getDecisionNote();
        }

        if (decision.getBasedOnResultId() != basedOnResultId) {
            return "Recovery plan must be based on the latest failed course result.";
        }

        if (decision.getNextAttemptNo() != requestedAttemptNo) {
            return "Invalid retrieval attempt. Expected next attempt no: " + decision.getNextAttemptNo() + ".";
        }

        return null;
    }

    public boolean isComponentAllowedForRetake(RetrievalPolicyEvaluationResult evaluation, int componentId) {
        if (evaluation == null || evaluation.getAllowedComponents() == null) {
            return false;
        }

        for (RetrievalPolicyComponent component : evaluation.getAllowedComponents()) {
            if (component.getComponentId() == componentId
                    && "RETAKE_REQUIRED".equalsIgnoreCase(component.getComponentRule())) {
                return true;
            }
        }

        return false;
    }

    private StudentCourseResult getLatestResult(String studentId, String courseId) {
        List<StudentCourseResult> results = resultDao.findResultsByStudentAndCourseId(studentId, courseId);
        return results.isEmpty() ? null : results.get(0);
    }

    private RetrievalPolicyDecision buildAllowedDecision(String studentId,
                                                         String courseId,
                                                         int basedOnResultId,
                                                         int currentAttemptNo,
                                                         int nextAttemptNo,
                                                         String policyType,
                                                         String allowedScope,
                                                         String decisionNote,
                                                         Integer decidedBy) {
        RetrievalPolicyDecision decision = new RetrievalPolicyDecision();
        decision.setStudentId(studentId);
        decision.setCourseId(courseId);
        decision.setBasedOnResultId(basedOnResultId);
        decision.setCurrentAttemptNo(currentAttemptNo);
        decision.setNextAttemptNo(nextAttemptNo);
        decision.setPolicyType(policyType);
        decision.setAllowedScope(allowedScope);
        decision.setAllowed(true);
        decision.setDecisionNote(decisionNote);
        decision.setDecidedBy(decidedBy);
        return decision;
    }

    private RetrievalPolicyDecision buildBlockedDecision(String studentId,
                                                         String courseId,
                                                         int basedOnResultId,
                                                         int currentAttemptNo,
                                                         int nextAttemptNo,
                                                         String policyType,
                                                         String allowedScope,
                                                         String decisionNote,
                                                         Integer decidedBy) {
        RetrievalPolicyDecision decision = new RetrievalPolicyDecision();
        decision.setStudentId(studentId);
        decision.setCourseId(courseId);
        decision.setBasedOnResultId(basedOnResultId);
        decision.setCurrentAttemptNo(currentAttemptNo);
        decision.setNextAttemptNo(nextAttemptNo);
        decision.setPolicyType(policyType);
        decision.setAllowedScope(allowedScope);
        decision.setAllowed(false);
        decision.setDecisionNote(decisionNote);
        decision.setDecidedBy(decidedBy);
        return decision;
    }

    private List<RetrievalPolicyComponent> mapFailedResultsToPolicyComponents(List<StudentComponentResult> failedResults) {
        List<RetrievalPolicyComponent> components = new ArrayList<>();

        for (StudentComponentResult failedResult : failedResults) {
            RetrievalPolicyComponent component = new RetrievalPolicyComponent();
            component.setComponentId(failedResult.getComponentId());
            component.setComponentRule("RETAKE_REQUIRED");
            components.add(component);
        }

        return components;
    }

    private List<RetrievalPolicyComponent> mapCourseComponentsToPolicyComponents(List<CourseComponent> courseComponents) {
        List<RetrievalPolicyComponent> components = new ArrayList<>();

        for (CourseComponent courseComponent : courseComponents) {
            RetrievalPolicyComponent component = new RetrievalPolicyComponent();
            component.setComponentId(courseComponent.getComponentId());
            component.setComponentRule("RETAKE_REQUIRED");
            components.add(component);
        }

        return components;
    }

    private boolean isSavablePolicyType(String policyType) {
        return POLICY_SECOND_ATTEMPT.equals(policyType)
                || POLICY_THIRD_ATTEMPT.equals(policyType)
                || POLICY_MAX_REACHED.equals(policyType);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}