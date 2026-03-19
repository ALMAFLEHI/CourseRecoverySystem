package com.crs.service;

import com.crs.dao.EmailLogDao;
import com.crs.model.EmailLog;

import java.util.List;

public class EmailService {

    private final EmailLogDao emailLogDao = new EmailLogDao();

    public String logEmail(String recipientEmail,
                           String subject,
                           String messageType,
                           String relatedEntityType,
                           Integer relatedEntityId,
                           String sendStatus,
                           Integer sentByUserId) {

        if (isBlank(recipientEmail) || isBlank(subject) || isBlank(messageType) || isBlank(sendStatus)) {
            return "Email log data is invalid.";
        }

        EmailLog emailLog = new EmailLog();
        emailLog.setRecipientEmail(recipientEmail.trim());
        emailLog.setSubject(subject.trim());
        emailLog.setMessageType(messageType.trim());
        emailLog.setRelatedEntityType(relatedEntityType);
        emailLog.setRelatedEntityId(relatedEntityId);
        emailLog.setSendStatus(sendStatus.trim());
        emailLog.setSentByUserId(sentByUserId);

        boolean inserted = emailLogDao.insert(emailLog);
        return inserted ? null : "Failed to save email log.";
    }

    public String logAccountCreatedEmail(String recipientEmail, Integer relatedUserId, Integer sentByUserId) {
        return logEmail(
                recipientEmail,
                "Your CRS account has been created",
                "ACCOUNT_CREATED",
                "USER",
                relatedUserId,
                "SENT",
                sentByUserId
        );
    }

    public String logPasswordResetEmail(String recipientEmail, Integer relatedUserId, Integer sentByUserId) {
        return logEmail(
                recipientEmail,
                "Your CRS password has been reset",
                "PASSWORD_RESET",
                "USER",
                relatedUserId,
                "SENT",
                sentByUserId
        );
    }

    public String logRecoveryPlanEmail(String recipientEmail, Integer planId, Integer sentByUserId) {
        return logEmail(
                recipientEmail,
                "Recovery plan has been created or updated",
                "RECOVERY_PLAN",
                "RECOVERY_PLAN",
                planId,
                "SENT",
                sentByUserId
        );
    }

    public String logMilestoneReminderEmail(String recipientEmail, Integer milestoneId, Integer sentByUserId) {
        return logEmail(
                recipientEmail,
                "Recovery milestone reminder",
                "MILESTONE_REMINDER",
                "RECOVERY_MILESTONE",
                milestoneId,
                "SENT",
                sentByUserId
        );
    }

    public String logReportEmail(String recipientEmail, Integer reportLogId, Integer sentByUserId) {
        return logEmail(
                recipientEmail,
                "Academic performance report generated",
                "REPORT",
                "PERFORMANCE_REPORT",
                reportLogId,
                "SENT",
                sentByUserId
        );
    }

    public List<EmailLog> getAllEmailLogs() {
        return emailLogDao.findAll();
    }

    public List<EmailLog> getEmailLogsByType(String messageType) {
        if (isBlank(messageType)) {
            return List.of();
        }
        return emailLogDao.findByType(messageType.trim());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
