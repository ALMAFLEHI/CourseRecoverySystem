package com.crs.service;

import com.crs.dao.ComponentResultDao;
import com.crs.dao.ResultDao;
import com.crs.dao.StudentDao;
import com.crs.model.Student;
import com.crs.model.StudentComponentResult;
import com.crs.model.StudentCourseResult;

import java.util.List;

public class StudentService {

    private final StudentDao studentDao = new StudentDao();
    private final ResultDao resultDao = new ResultDao();
    private final ComponentResultDao componentResultDao = new ComponentResultDao();

    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public Student getStudentById(String studentId) {
        if (isBlank(studentId)) {
            return null;
        }
        return studentDao.findById(studentId.trim());
    }

    public List<StudentCourseResult> getResultsByStudentId(String studentId) {
        if (isBlank(studentId)) {
            return List.of();
        }
        return resultDao.findResultsByStudentId(studentId.trim());
    }

    public List<StudentComponentResult> getAllComponentResultsByStudentId(String studentId) {
        if (isBlank(studentId)) {
            return List.of();
        }
        return componentResultDao.findByStudentId(studentId.trim());
    }

    public List<StudentComponentResult> getFailedComponentResultsByStudentId(String studentId) {
        if (isBlank(studentId)) {
            return List.of();
        }
        return componentResultDao.findFailedByStudentId(studentId.trim());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}