package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.dtos.request.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class TeacherDto extends AppUserDto {

    private String subjectTaught;

    private String teachingLevel;

    private String experience;

    public TeacherDto() {
    }

    public TeacherDto(String firstname,
                      String lastname,
                      String password,
                      String confirmPassword,
                      String email,
                      Date dateofbirth,
                      String phonenumber,
                      String postaladdress,
                      String country,
                      String city,
                      String postalcode,
                      String gender,
                      String Nationality,
                      String role,
                      String subjectTaught,
                      String teachingLevel,
                      String experience) {
        super(firstname,
                lastname,
                password,
                confirmPassword,
                email,
                dateofbirth,
                phonenumber,
                postaladdress,
                country,
                city,
                postalcode,
                gender,
                Nationality,
                role);
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
