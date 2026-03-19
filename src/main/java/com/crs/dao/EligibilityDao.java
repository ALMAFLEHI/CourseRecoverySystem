package com.crs.dao;

import com.crs.model.EligibilityCheck;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EligibilityDao {

    private static final String INSERT_SQL = """
            INSERT INTO eligibility_checks
            (student_id, cgpa, failed_course_count, eligible, remarks, checked_by)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_STUDENT_ID_SQL = """
            SELECT check_id, student_id, cgpa, failed_course_count, eligible, remarks, checked_by, checked_at
            FROM eligibility_checks
            WHERE student_id = ?
            ORDER BY check_id DESC
            """;

    private static final String FIND_LATEST_BY_STUDENT_ID_SQL = """
            SELECT check_id, student_id, cgpa, failed_course_count, eligible, remarks, checked_by, checked_at
            FROM eligibility_checks
            WHERE student_id = ?
            ORDER BY check_id DESC
            LIMIT 1
            """;

    public boolean insert(EligibilityCheck check) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setString(1, check.getStudentId());
            ps.setBigDecimal(2, check.getCgpa());
            ps.setInt(3, check.getFailedCourseCount());
            ps.setBoolean(4, check.isEligible());
            ps.setString(5, check.getRemarks());
            ps.setInt(6, check.getCheckedBy());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting eligibility check", e);
        }
    }

    public List<EligibilityCheck> findByStudentId(String studentId) {
        List<EligibilityCheck> checks = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_STUDENT_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    checks.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding eligibility checks by student id", e);
        }

        return checks;
    }

    public EligibilityCheck findLatestByStudentId(String studentId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_LATEST_BY_STUDENT_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding latest eligibility check", e);
        }

        return null;
    }

    private EligibilityCheck mapRow(ResultSet rs) throws SQLException {
        EligibilityCheck check = new EligibilityCheck();
        check.setCheckId(rs.getInt("check_id"));
        check.setStudentId(rs.getString("student_id"));
        check.setCgpa(rs.getBigDecimal("cgpa"));
        check.setFailedCourseCount(rs.getInt("failed_course_count"));
        check.setEligible(rs.getBoolean("eligible"));
        check.setRemarks(rs.getString("remarks"));
        check.setCheckedBy(rs.getInt("checked_by"));
        check.setCheckedAt(rs.getTimestamp("checked_at"));
        return check;
    }
}
