package com.crs.servlet;

import com.crs.model.Course;
import com.crs.model.CourseComponent;
import com.crs.service.CourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/courses/details")
public class CourseDetailsServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("id");

        if (courseId == null || courseId.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/courses");
            return;
        }

        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            resp.sendRedirect(req.getContextPath() + "/courses");
            return;
        }

        List<CourseComponent> components = courseService.getCourseComponents(courseId);

        req.setAttribute("course", course);
        req.setAttribute("components", components);

        req.getRequestDispatcher("/WEB-INF/views/courses/course-details.jsp").forward(req, resp);
    }
}