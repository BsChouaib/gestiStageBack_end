package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TEACHER")
public class Teacher extends  AppUser{
    private String subjectTaught;
    private String teachingLevel;
    private String experience;

    public Teacher() {
    }

    public Teacher(Long id, String firstname, String lastname, String password, String email, Date dateofbirth, String phonenumber, String postaladdress, String country, String city, String postalcode, String gender, String nationality, Boolean isActive, List<AppRole> appRoles, List<Claim> claims, String subjectTaught, String teachingLevel, String experience) {
        super(id, firstname, lastname, password, email, dateofbirth, phonenumber, postaladdress, country, city, postalcode, gender, nationality, isActive, appRoles, claims);
        this.subjectTaught = subjectTaught;
        this.teachingLevel = teachingLevel;
        this.experience = experience;
    }

    public String getSubjectTaught() {
        return subjectTaught;
    }

    public String getTeachingLevel() {
        return teachingLevel;
    }

    public String getExperience() {
        return experience;
    }

    public void setSubjectTaught(String subjectTaught) {
        this.subjectTaught = subjectTaught;
    }

    public void setTeachingLevel(String teachingLevel) {
        this.teachingLevel = teachingLevel;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
