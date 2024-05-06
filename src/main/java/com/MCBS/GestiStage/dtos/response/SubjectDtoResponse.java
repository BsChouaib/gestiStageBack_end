package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.enumerations.InternshipType;
import lombok.Data;

@Data
public class SubjectDtoResponse {
    private Long subjectId;
    private String title;
    private String description;
    private InternshipType internshipType;
}
