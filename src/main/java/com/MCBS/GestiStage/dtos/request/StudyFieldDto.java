package com.MCBS.GestiStage.dtos.request;

public class StudyFieldDto {
    private String fieldName;

    public StudyFieldDto() {
    }

    public StudyFieldDto(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
