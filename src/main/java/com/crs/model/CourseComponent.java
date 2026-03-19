package com.crs.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CourseComponent {

    private int componentId;
    private String courseId;
    private String componentName;
    private BigDecimal weightage;
    private String componentType;
    private boolean passRequired;
    private Timestamp createdAt;

    public CourseComponent() {
    }

    public CourseComponent(int componentId, String courseId, String componentName,
                           BigDecimal weightage, String componentType,
                           boolean passRequired, Timestamp createdAt) {
        this.componentId = componentId;
        this.courseId = courseId;
        this.componentName = componentName;
        this.weightage = weightage;
        this.componentType = componentType;
        this.passRequired = passRequired;
        this.createdAt = createdAt;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public BigDecimal getWeightage() {
        return weightage;
    }

    public void setWeightage(BigDecimal weightage) {
        this.weightage = weightage;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public boolean isPassRequired() {
        return passRequired;
    }

    public void setPassRequired(boolean passRequired) {
        this.passRequired = passRequired;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CourseComponent{" +
                "componentId=" + componentId +
                ", courseId='" + courseId + '\'' +
                ", componentName='" + componentName + '\'' +
                ", weightage=" + weightage +
                ", componentType='" + componentType + '\'' +
                ", passRequired=" + passRequired +
                ", createdAt=" + createdAt +
                '}';
    }
}