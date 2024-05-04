package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.enumerations.TypeInternship;
import lombok.Data;


public record SubjectDtoRequest(String title, String description, TypeInternship internshipType) {
}
