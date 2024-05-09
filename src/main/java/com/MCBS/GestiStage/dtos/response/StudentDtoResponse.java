package com.MCBS.GestiStage.dtos.response;

import lombok.Data;

@Data
public class StudentDtoResponse {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private Boolean isActive;
}
