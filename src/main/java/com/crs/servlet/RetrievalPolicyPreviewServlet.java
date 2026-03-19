package com.crs.servlet;

import com.crs.model.Course;
import com.crs.model.CourseComponent;
import com.crs.model.RetrievalPolicyComponent;
import com.crs.model.RetrievalPolicyDecision;
import com.crs.model.RetrievalPolicyEvaluationResult;
import com.crs.model.Student;
import com.crs.service.AuditService;
import com.crs.service.CourseService;
import com.crs.service.RetrievalPolicyService;
import com.crs.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/recovery/policy")
public class RetrievalPolicyPreviewServlet extends HttpServlet {

    private final RetrievalPolicyService retrievalPolicyService = new RetrievalPolicyService();
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final AuditService auditService = new AuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String courseId = req.getParameter("courseId");

        if (studentId == null || studentId.trim().isEmpty()
                || courseId == null || courseId.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/students");
            return;
        }

        Student policyStudent = studentService.getStudentById(studentId);
        Course policyCourse = courseService.getCourseById(courseId);

        if (policyStudent == null || policyCourse == null) {
            resp.sendRedirect(req.getContextPath() + "/students");
            return;
        }

        HttpSession session = req.getSession(false);
        Integer decidedBy = session != null ? (Integer) session.getAttribute("userId") : null;

        RetrievalPolicyEvaluationResult policyEvaluation =
                retrievalPolicyService.evaluatePolicy(studentId, courseId, decidedBy);

        List<CourseComponent> policyCourseComponents = courseService.getCourseComponents(courseId);
        Set<Integer> currentAllowedComponentIds = new LinkedHashSet<>();

        if (policyEvaluation != null && policyEvaluation.getAllowedComponents() != null) {
            for (RetrievalPolicyComponent component : policyEvaluation.getAllowedComponents()) {
                currentAllowedComponentIds.add(component.getComponentId());
            }
        }

        List<RetrievalPolicyDecision> savedPolicyHistory =
                retrievalPolicyService.getDecisionHistory(studentId, courseId);

        RetrievalPolicyDecision latestSavedDecision = null;
        List<RetrievalPolicyComponent> latestSavedDecisionComponents = List.of();
        Set<Integer> latestSavedAllowedComponentIds = new LinkedHashSet<>();

        if (savedPolicyHistory != null && !savedPolicyHistory.isEmpty()) {
            latestSavedDecision = savedPolicyHistory.get(0);
            latestSavedDecisionComponents =
                    retrievalPolicyService.getDecisionComponents(latestSavedDecision.getDecisionId());

            for (RetrievalPolicyComponent component : latestSavedDecisionComponents) {
                latestSavedAllowedComponentIds.add(component.getComponentId());
            }
        }

        auditService.logAction(
                decidedBy,
                "PREVIEW_RETRIEVAL_POLICY",
                "RETRIEVAL_POLICY",
                latestSavedDecision != null ? latestSavedDecision.getDecisionId() : null,
                "Previewed retrieval policy for student " + studentId + " and course " + courseId
        );

        req.setAttribute("policyStudent", policyStudent);
        req.setAttribute("policyCourse", policyCourse);
        req.setAttribute("policyEvaluation", policyEvaluation);
        req.setAttribute("policyCourseComponents", policyCourseComponents);
        req.setAttribute("currentAllowedComponentIds", currentAllowedComponentIds);

        req.setAttribute("savedPolicyHistory", savedPolicyHistory);
        req.setAttribute("latestSavedDecision", latestSavedDecision);
        req.setAttribute("latestSavedDecisionComponents", latestSavedDecisionComponents);
        req.setAttribute("latestSavedAllowedComponentIds", latestSavedAllowedComponentIds);

        req.getRequestDispatcher("/WEB-INF/views/recovery/retrieval-policy-preview.jsp").forward(req, resp);
    }
}