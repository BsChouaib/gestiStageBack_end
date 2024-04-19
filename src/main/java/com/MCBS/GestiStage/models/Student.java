package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends AppUser{

    @Column(name = "current_study_level")
    private String currentStudyLevel;
    @Column(name = "current_institution")
    private String currentInstitution;
    @Column(name = "study_field")
    private String studyField;
    @Column(name = "enrollment_year")
    private int EnrollmentYear;
}
