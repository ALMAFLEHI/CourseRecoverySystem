package com.crs.service;

import com.crs.dao.AuditLogDao;
import com.crs.model.AuditLog;

import java.util.List;

public class AuditService {

    private final AuditLogDao auditLogDao = new AuditLogDao();

    public String logAction(Integer actorUserId,
                            String actionType,
                            String entityType,
                            Integer entityId,
                            String description) {

        if (isBlank(actionType) || isBlank(entityType)) {
            return "Audit log data is invalid.";
        }

        AuditLog log = new AuditLog();
        log.setActorUserId(actorUserId);
        log.setActionType(actionType.trim());
        log.setEntityType(entityType.trim());
        log.setEntityId(entityId);
        log.setDescription(description);

        boolean inserted = auditLogDao.insert(log);
        return inserted ? null : "Failed to save audit log.";
    }

    public List<AuditLog> getAllAuditLogs() {
        return auditLogDao.findAll();
    }

    public List<AuditLog> getRecentAuditLogs(int limit) {
        if (limit <= 0) {
            limit = 5;
        }
        return auditLogDao.findRecent(limit);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
