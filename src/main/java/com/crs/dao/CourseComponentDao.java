package com.crs.dao;

import com.crs.model.CourseComponent;
import com.crs.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseComponentDao {

    private static final String FIND_BY_COURSE_ID_SQL = """
            SELECT component_id, course_id, component_name, weightage, component_type,
                   pass_required, created_at
            FROM course_components
            WHERE course_id = ?
            ORDER BY component_id ASC
            """;

    public List<CourseComponent> findByCourseId(String courseId) {
        List<CourseComponent> components = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_COURSE_ID_SQL)) {

            ps.setString(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CourseComponent component = new CourseComponent();
                    component.setComponentId(rs.getInt("component_id"));
                    component.setCourseId(rs.getString("course_id"));
                    component.setComponentName(rs.getString("component_name"));
                    component.setWeightage(rs.getBigDecimal("weightage"));
                    component.setComponentType(rs.getString("component_type"));
                    component.setPassRequired(rs.getBoolean("pass_required"));
                    component.setCreatedAt(rs.getTimestamp("created_at"));
                    components.add(component);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding course components", e);
        }

        return components;
    }
}