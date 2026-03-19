package com.crs.model;

import java.util.ArrayList;
import java.util.List;

public class RetrievalPolicyEvaluationResult {

    private RetrievalPolicyDecision decision;
    private List<RetrievalPolicyComponent> allowedComponents = new ArrayList<>();

    public RetrievalPolicyDecision getDecision() {
        return decision;
    }

    public void setDecision(RetrievalPolicyDecision decision) {
        this.decision = decision;
    }

    public List<RetrievalPolicyComponent> getAllowedComponents() {
        return allowedComponents;
    }

    public void setAllowedComponents(List<RetrievalPolicyComponent> allowedComponents) {
        this.allowedComponents = allowedComponents;
    }

    public boolean isAllowed() {
        return decision != null && decision.isAllowed();
    }
}