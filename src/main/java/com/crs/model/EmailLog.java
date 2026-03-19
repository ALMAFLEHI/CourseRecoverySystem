package com.crs.model;

import java.sql.Timestamp;

public class EmailLog {

    private int emailLogId;
    private String recipientEmail;
    private String subject;
    private String messageType;
    private String relatedEntityType;
    private Integer relatedEntityId;
    private String sendStatus;
    private Integer sentByUserId;
    private Timestamp createdAt;

    public EmailLog() {
    }

    public EmailLog(int emailLogId, String recipientEmail, String subject,
                    String messageType, String relatedEntityType,
                    Integer relatedEntityId, String sendStatus,
                    Integer sentByUserId, Timestamp createdAt) {
        this.emailLogId = emailLogId;
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.messageType = messageType;
        this.relatedEntityType = relatedEntityType;
        this.relatedEntityId = relatedEntityId;
        this.sendStatus = sendStatus;
        this.sentByUserId = sentByUserId;
        this.createdAt = createdAt;
    }

    public int getEmailLogId() {
        return emailLogId;
    }

    public void setEmailLogId(int emailLogId) {
        this.emailLogId = emailLogId;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getRelatedEntityType() {
        return relatedEntityType;
    }

    public void setRelatedEntityType(String relatedEntityType) {
        this.relatedEntityType = relatedEntityType;
    }

    public Integer getRelatedEntityId() {
        return relatedEntityId;
    }

    public void setRelatedEntityId(Integer relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getSentByUserId() {
        return sentByUserId;
    }

    public void setSentByUserId(Integer sentByUserId) {
        this.sentByUserId = sentByUserId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "EmailLog{" +
                "emailLogId=" + emailLogId +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", messageType='" + messageType + '\'' +
                ", relatedEntityType='" + relatedEntityType + '\'' +
                ", relatedEntityId=" + relatedEntityId +
                ", sendStatus='" + sendStatus + '\'' +
                ", sentByUserId=" + sentByUserId +
                ", createdAt=" + createdAt +
                '}';
    }
}