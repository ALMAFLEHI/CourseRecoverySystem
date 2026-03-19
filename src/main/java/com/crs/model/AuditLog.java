package com.crs.model;

import java.sql.Timestamp;

public class AuditLog {

    private int auditLogId;
    private Integer actorUserId;
    private String actionType;
    private String entityType;
    private Integer entityId;
    private String description;
    private Timestamp createdAt;

    public AuditLog() {
    }

    public AuditLog(int auditLogId, Integer actorUserId, String actionType,
                    String entityType, Integer entityId, String description,
                    Timestamp createdAt) {
        this.auditLogId = auditLogId;
        this.actorUserId = actorUserId;
        this.actionType = actionType;
        this.entityType = entityType;
        this.entityId = entityId;
        this.description = description;
        this.createdAt = createdAt;
    }

    public int getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(int auditLogId) {
        this.auditLogId = auditLogId;
    }

    public Integer getActorUserId() {
        return actorUserId;
    }

    public void setActorUserId(Integer actorUserId) {
        this.actorUserId = actorUserId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "auditLogId=" + auditLogId +
                ", actorUserId=" + actorUserId +
                ", actionType='" + actionType + '\'' +
                ", entityType='" + entityType + '\'' +
                ", entityId=" + entityId +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}