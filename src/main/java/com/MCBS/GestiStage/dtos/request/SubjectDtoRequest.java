package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.enumerations.InternshipType;
import com.MCBS.GestiStage.models.StudyField;


public record SubjectDtoRequest(
            String title,
            String description,
            String teacherEmail,
            InternshipType internshipType,
            Long StudyFieldId
) {
}
