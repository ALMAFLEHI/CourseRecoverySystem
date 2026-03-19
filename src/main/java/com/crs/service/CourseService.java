package com.crs.service;

import com.crs.dao.CourseComponentDao;
import com.crs.dao.CourseDao;
import com.crs.model.Course;
import com.crs.model.CourseComponent;

import java.util.List;

public class CourseService {

    private final CourseDao courseDao = new CourseDao();
    private final CourseComponentDao courseComponentDao = new CourseComponentDao();

    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    public Course getCourseById(String courseId) {
        if (isBlank(courseId)) {
            return null;
        }
        return courseDao.findById(courseId.trim());
    }

    public List<CourseComponent> getCourseComponents(String courseId) {
        if (isBlank(courseId)) {
            return List.of();
        }
        return courseComponentDao.findByCourseId(courseId.trim());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}