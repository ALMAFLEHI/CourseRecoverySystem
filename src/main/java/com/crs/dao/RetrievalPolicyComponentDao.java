package com.crs.dao;

import com.crs.model.RetrievalPolicyComponent;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetrievalPolicyComponentDao {

    private static final String INSERT_SQL = """
            INSERT INTO retrieval_policy_components
            (decision_id, component_id, component_rule)
            VALUES (?, ?, ?)
            """;

    private static final String FIND_BY_DECISION_ID_SQL = """
            SELECT decision_component_id, decision_id, component_id, component_rule, created_at
            FROM retrieval_policy_components
            WHERE decision_id = ?
            ORDER BY decision_component_id ASC
            """;

    private static final String DELETE_BY_DECISION_ID_SQL = """
            DELETE FROM retrieval_policy_components
            WHERE decision_id = ?
            """;

    public boolean insert(RetrievalPolicyComponent component) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, component.getDecisionId());
            ps.setInt(2, component.getComponentId());
            ps.setString(3, component.getComponentRule());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting retrieval policy component", e);
        }
    }

    public List<RetrievalPolicyComponent> findByDecisionId(int decisionId) {
        List<RetrievalPolicyComponent> components = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_DECISION_ID_SQL)) {

            ps.setInt(1, decisionId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    components.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding retrieval policy components by decision id", e);
        }

        return components;
    }

    public boolean deleteByDecisionId(int decisionId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_BY_DECISION_ID_SQL)) {

            ps.setInt(1, decisionId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting retrieval policy components by decision id", e);
        }
    }

    private RetrievalPolicyComponent mapRow(ResultSet rs) throws SQLException {
        RetrievalPolicyComponent component = new RetrievalPolicyComponent();
        component.setDecisionComponentId(rs.getInt("decision_component_id"));
        component.setDecisionId(rs.getInt("decision_id"));
        component.setComponentId(rs.getInt("component_id"));
        component.setComponentRule(rs.getString("component_rule"));
        component.setCreatedAt(rs.getTimestamp("created_at"));
        return component;
    }
}