package com.crs.servlet;

import com.crs.model.Student;
import com.crs.model.StudentComponentResult;
import com.crs.model.StudentCourseResult;
import com.crs.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/students/details")
public class StudentDetailsServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("id");

        if (studentId == null || studentId.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/students");
            return;
        }

        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            resp.sendRedirect(req.getContextPath() + "/students");
            return;
        }

        List<StudentCourseResult> courseResults = studentService.getResultsByStudentId(studentId);
        List<StudentComponentResult> failedComponentResults = studentService.getFailedComponentResultsByStudentId(studentId);

        req.setAttribute("student", student);
        req.setAttribute("courseResults", courseResults);
        req.setAttribute("failedComponentResults", failedComponentResults);

        req.getRequestDispatcher("/WEB-INF/views/students/student-details.jsp").forward(req, resp);
    }
}