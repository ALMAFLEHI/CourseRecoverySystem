package com.crs.servlet;

import com.crs.model.Course;
import com.crs.service.CourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseListServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseService.getAllCourses();
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/WEB-INF/views/courses/course-list.jsp").forward(req, resp);
    }
}