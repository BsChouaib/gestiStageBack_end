package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.enumerations.InternshipType;


public record SubjectDtoRequest(String title, String description, String teacherEmail, InternshipType internshipType) {
}
