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
public class Teacher extends  AppUser{

    @Column(name = "subject_taught")
    private String subjectTaught;
    @Column(name = "teaching_level")
    private String teachingLevel;
    @Column(name = "experience")
    private String experience;
}
