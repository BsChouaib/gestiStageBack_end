package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.dtos.request.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto extends AppUserDto {

    private String subjectTaught;

    private String teachingLevel;

    private String experience;
}
