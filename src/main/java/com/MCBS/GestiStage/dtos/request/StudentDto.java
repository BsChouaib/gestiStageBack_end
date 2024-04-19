package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.dtos.request.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends AppUserDto {
    private String currentStudyLevel;

    private String currentInstitution;

    private String studyField;

    private int EnrollmentYear;
}
