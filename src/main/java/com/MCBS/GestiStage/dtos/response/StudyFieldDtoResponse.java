package com.MCBS.GestiStage.dtos.response;

public class StudyFieldDtoResponse {
    private String id;
    private String fieldName;

    public StudyFieldDtoResponse() {
    }

    public StudyFieldDtoResponse(String id, String fieldName) {
        this.id = id;
        this.fieldName = fieldName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
