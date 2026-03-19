package com.crs.servlet;

import com.crs.model.EligibilityCheck;
import com.crs.model.Student;
import com.crs.service.EligibilityService;
import com.crs.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/eligibility/ineligible")
public class IneligibleStudentsServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();
    private final EligibilityService eligibilityService = new EligibilityService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer checkedBy = session != null ? (Integer) session.getAttribute("userId") : null;

        List<Student> students = studentService.getAllStudents();
        List<Student> ineligibleStudents = new ArrayList<>();
        List<EligibilityCheck> ineligibleChecks = new ArrayList<>();

        if (checkedBy != null) {
            for (Student student : students) {
                EligibilityCheck check = eligibilityService.evaluateEligibility(student.getStudentId(), checkedBy);

                if (check != null && !check.isEligible()) {
                    ineligibleStudents.add(student);
                    ineligibleChecks.add(check);
                }
            }
        }

        req.setAttribute("ineligibleStudents", ineligibleStudents);
        req.setAttribute("ineligibleChecks", ineligibleChecks);
        req.getRequestDispatcher("/WEB-INF/views/eligibility/ineligible-students.jsp").forward(req, resp);
    }
}
