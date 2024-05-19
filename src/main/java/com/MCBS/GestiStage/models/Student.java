package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends AppUser{
    private String currentStudyLevel;
    private String currentInstitution;
    @OneToOne(cascade = CascadeType.ALL)
    private StudyField studyField;
    private Date EnrollmentYear;

    public Student() {
    }

    public Student(Long id, String firstname, String lastname, String password, String email, Date dateofbirth, String phonenumber, String postaladdress, String country, String city, String postalcode, String gender, String nationality, Boolean isActive, List<AppRole> appRoles, List<Claim> claims, String currentStudyLevel, String currentInstitution, StudyField studyField, Date enrollmentYear) {
        super(id, firstname, lastname, password, email, dateofbirth, phonenumber, postaladdress, country, city, postalcode, gender, nationality, isActive, appRoles, claims);
        this.currentStudyLevel = currentStudyLevel;
        this.currentInstitution = currentInstitution;
        this.studyField = studyField;
        EnrollmentYear = enrollmentYear;
    }

    public String getCurrentStudyLevel() {
        return currentStudyLevel;
    }

    public String getCurrentInstitution() {
        return currentInstitution;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public Date getEnrollmentYear() {
        return EnrollmentYear;
    }

    public void setCurrentStudyLevel(String currentStudyLevel) {
        this.currentStudyLevel = currentStudyLevel;
    }

    public void setCurrentInstitution(String currentInstitution) {
        this.currentInstitution = currentInstitution;
    }

    public void setStudyField(StudyField studyField) {
        this.studyField = studyField;
    }

    public void setEnrollmentYear(Date enrollmentYear) {
        EnrollmentYear = enrollmentYear;
    }
}
