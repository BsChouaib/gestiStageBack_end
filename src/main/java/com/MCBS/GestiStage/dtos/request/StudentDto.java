package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.dtos.request.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


public class StudentDto extends AppUserDto {
    private String currentStudyLevel;

    private String currentInstitution;

    private String studyField;

    private Date EnrollmentYear;

    public StudentDto() {
    }

    public StudentDto(String firstname, String lastname, String password, String confirmPassword, String email, Date dateofbirth, String phonenumber, String postaladdress, String country, String city, String postalcode, String gender, String Nationality, String role, String currentStudyLevel, String currentInstitution, String studyField, Date enrollmentYear) {
        super(firstname, lastname, password, confirmPassword, email, dateofbirth, phonenumber, postaladdress, country, city, postalcode, gender, Nationality, role);
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

    public String getStudyField() {
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

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public void setEnrollmentYear(Date enrollmentYear) {
        EnrollmentYear = enrollmentYear;
    }
}
