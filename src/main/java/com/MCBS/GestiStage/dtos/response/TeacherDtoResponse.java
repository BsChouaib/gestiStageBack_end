package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.enumerations.InternshipType;
import lombok.Data;

@Data
public class TeacherDtoResponse {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private Boolean isActive;
}
