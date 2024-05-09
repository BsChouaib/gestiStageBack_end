package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends AppUser{
    private String currentStudyLevel;
    private String currentInstitution;
    private String studyField;
    private Date EnrollmentYear;
}
