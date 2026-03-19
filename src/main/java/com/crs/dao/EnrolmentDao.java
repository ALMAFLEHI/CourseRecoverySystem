package com.crs.dao;

import com.crs.model.Enrolment;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrolmentDao {

    private static final String INSERT_SQL = """
            INSERT INTO enrolments
            (student_id, eligibility_check_id, approved_by, enrolment_status, remarks, approved_at)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_STUDENT_ID_SQL = """
            SELECT enrolment_id, student_id, eligibility_check_id, approved_by,
                   enrolment_status, remarks, approved_at
            FROM enrolments
            WHERE student_id = ?
            ORDER BY enrolment_id DESC
            """;

    private static final String FIND_LATEST_BY_STUDENT_ID_SQL = """
            SELECT enrolment_id, student_id, eligibility_check_id, approved_by,
                   enrolment_status, remarks, approved_at
            FROM enrolments
            WHERE student_id = ?
            ORDER BY enrolment_id DESC
            LIMIT 1
            """;

    public boolean insert(Enrolment enrolment) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setString(1, enrolment.getStudentId());
            ps.setInt(2, enrolment.getEligibilityCheckId());
            ps.setInt(3, enrolment.getApprovedBy());
            ps.setString(4, enrolment.getEnrolmentStatus());
            ps.setString(5, enrolment.getRemarks());

            if (enrolment.getApprovedAt() != null) {
                ps.setTimestamp(6, enrolment.getApprovedAt());
            } else {
                ps.setNull(6, Types.TIMESTAMP);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting enrolment", e);
        }
    }

    public List<Enrolment> findByStudentId(String studentId) {
        List<Enrolment> enrolments = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_STUDENT_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    enrolments.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding enrolments by student id", e);
        }

        return enrolments;
    }

    public Enrolment findLatestByStudentId(String studentId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_LATEST_BY_STUDENT_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding latest enrolment", e);
        }

        return null;
    }

    private Enrolment mapRow(ResultSet rs) throws SQLException {
        Enrolment enrolment = new Enrolment();
        enrolment.setEnrolmentId(rs.getInt("enrolment_id"));
        enrolment.setStudentId(rs.getString("student_id"));
        enrolment.setEligibilityCheckId(rs.getInt("eligibility_check_id"));
        enrolment.setApprovedBy(rs.getInt("approved_by"));
        enrolment.setEnrolmentStatus(rs.getString("enrolment_status"));
        enrolment.setRemarks(rs.getString("remarks"));
        enrolment.setApprovedAt(rs.getTimestamp("approved_at"));
        return enrolment;
    }
}
