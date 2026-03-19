package com.crs.model;

import java.sql.Timestamp;

public class RetrievalPolicyComponent {

    private int decisionComponentId;
    private int decisionId;
    private int componentId;
    private String componentRule;
    private Timestamp createdAt;

    public RetrievalPolicyComponent() {
    }

    public RetrievalPolicyComponent(int decisionComponentId, int decisionId, int componentId,
                                    String componentRule, Timestamp createdAt) {
        this.decisionComponentId = decisionComponentId;
        this.decisionId = decisionId;
        this.componentId = componentId;
        this.componentRule = componentRule;
        this.createdAt = createdAt;
    }

    public int getDecisionComponentId() {
        return decisionComponentId;
    }

    public void setDecisionComponentId(int decisionComponentId) {
        this.decisionComponentId = decisionComponentId;
    }

    public int getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(int decisionId) {
        this.decisionId = decisionId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getComponentRule() {
        return componentRule;
    }

    public void setComponentRule(String componentRule) {
        this.componentRule = componentRule;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RetrievalPolicyComponent{" +
                "decisionComponentId=" + decisionComponentId +
                ", decisionId=" + decisionId +
                ", componentId=" + componentId +
                ", componentRule='" + componentRule + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}