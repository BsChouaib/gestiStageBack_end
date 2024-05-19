package com.MCBS.GestiStage.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudyField {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid",strategy = "uuid2")
    private String id;
    private String fieldName;

    public StudyField() {
    }

    public StudyField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getId() {
        return id;
    }
}
