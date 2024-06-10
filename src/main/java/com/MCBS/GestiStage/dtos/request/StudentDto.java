package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.dtos.request.AppUserDto;
import com.MCBS.GestiStage.models.StudyField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class StudentDto extends AppUserDto {
    private String currentStudyLevel;

    private String currentInstitution;

    private Long studyFieldId;

    private Date EnrollmentYear;

    public StudentDto() {
    }

    public StudentDto(String firstname, String lastname, String password, String confirmPassword, String email, Date dateofbirth, String phonenumber, String postaladdress, String country, String city, String postalcode, String gender, String Nationality, String role, String currentStudyLevel, String currentInstitution, Long studyFieldId, Date enrollmentYear) {
        super(firstname, lastname, password, confirmPassword, email, dateofbirth, phonenumber, postaladdress, country, city, postalcode, gender, Nationality, role);
        this.currentStudyLevel = currentStudyLevel;
        this.currentInstitution = currentInstitution;
        this.studyFieldId = studyFieldId;
        EnrollmentYear = enrollmentYear;
    }

    public void setCurrentStudyLevel(String currentStudyLevel) {
        this.currentStudyLevel = currentStudyLevel;
    }

    public void setCurrentInstitution(String currentInstitution) {
        this.currentInstitution = currentInstitution;
    }

    public void setStudyFieldId(Long studyFieldId) {
        this.studyFieldId = studyFieldId;
    }

    public void setEnrollmentYear(Date enrollmentYear) {
        EnrollmentYear = enrollmentYear;
    }

    public String getCurrentStudyLevel() {
        return currentStudyLevel;
    }

    public String getCurrentInstitution() {
        return currentInstitution;
    }

    public Long getStudyFieldId() {
        return studyFieldId;
    }

    public Date getEnrollmentYear() {
        return EnrollmentYear;
    }
}
