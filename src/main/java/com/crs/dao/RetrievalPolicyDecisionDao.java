package com.crs.dao;

import com.crs.model.RetrievalPolicyDecision;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetrievalPolicyDecisionDao {

    private static final String INSERT_SQL = """
            INSERT INTO retrieval_policy_decisions
            (student_id, course_id, based_on_result_id, current_attempt_no, next_attempt_no,
             policy_type, allowed_scope, is_allowed, decision_note, decided_by)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT decision_id, student_id, course_id, based_on_result_id, current_attempt_no,
                   next_attempt_no, policy_type, allowed_scope, is_allowed, decision_note,
                   decided_by, decided_at
            FROM retrieval_policy_decisions
            WHERE decision_id = ?
            """;

    private static final String FIND_BY_STUDENT_AND_COURSE_SQL = """
            SELECT decision_id, student_id, course_id, based_on_result_id, current_attempt_no,
                   next_attempt_no, policy_type, allowed_scope, is_allowed, decision_note,
                   decided_by, decided_at
            FROM retrieval_policy_decisions
            WHERE student_id = ? AND course_id = ?
            ORDER BY decision_id DESC
            """;

    private static final String FIND_LATEST_BY_STUDENT_AND_COURSE_SQL = """
            SELECT decision_id, student_id, course_id, based_on_result_id, current_attempt_no,
                   next_attempt_no, policy_type, allowed_scope, is_allowed, decision_note,
                   decided_by, decided_at
            FROM retrieval_policy_decisions
            WHERE student_id = ? AND course_id = ?
            ORDER BY decision_id DESC
            LIMIT 1
            """;

    public int insertAndReturnId(RetrievalPolicyDecision decision) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, decision.getStudentId());
            ps.setString(2, decision.getCourseId());
            ps.setInt(3, decision.getBasedOnResultId());
            ps.setInt(4, decision.getCurrentAttemptNo());
            ps.setInt(5, decision.getNextAttemptNo());
            ps.setString(6, decision.getPolicyType());
            ps.setString(7, decision.getAllowedScope());
            ps.setBoolean(8, decision.isAllowed());
            ps.setString(9, decision.getDecisionNote());

            if (decision.getDecidedBy() != null) {
                ps.setInt(10, decision.getDecidedBy());
            } else {
                ps.setNull(10, Types.INTEGER);
            }

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return 0;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting retrieval policy decision", e);
        }

        return 0;
    }

    public RetrievalPolicyDecision findById(int decisionId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, decisionId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding retrieval policy decision by id", e);
        }

        return null;
    }

    public RetrievalPolicyDecision findLatestByStudentAndCourse(String studentId, String courseId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_LATEST_BY_STUDENT_AND_COURSE_SQL)) {

            ps.setString(1, studentId);
            ps.setString(2, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding latest retrieval policy decision", e);
        }

        return null;
    }

    public List<RetrievalPolicyDecision> findByStudentAndCourse(String studentId, String courseId) {
        List<RetrievalPolicyDecision> decisions = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_STUDENT_AND_COURSE_SQL)) {

            ps.setString(1, studentId);
            ps.setString(2, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    decisions.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding retrieval policy decisions by student and course", e);
        }

        return decisions;
    }

    private RetrievalPolicyDecision mapRow(ResultSet rs) throws SQLException {
        RetrievalPolicyDecision decision = new RetrievalPolicyDecision();
        decision.setDecisionId(rs.getInt("decision_id"));
        decision.setStudentId(rs.getString("student_id"));
        decision.setCourseId(rs.getString("course_id"));
        decision.setBasedOnResultId(rs.getInt("based_on_result_id"));
        decision.setCurrentAttemptNo(rs.getInt("current_attempt_no"));
        decision.setNextAttemptNo(rs.getInt("next_attempt_no"));
        decision.setPolicyType(rs.getString("policy_type"));
        decision.setAllowedScope(rs.getString("allowed_scope"));
        decision.setAllowed(rs.getBoolean("is_allowed"));
        decision.setDecisionNote(rs.getString("decision_note"));

        int decidedBy = rs.getInt("decided_by");
        decision.setDecidedBy(rs.wasNull() ? null : decidedBy);

        decision.setDecidedAt(rs.getTimestamp("decided_at"));
        return decision;
    }
}