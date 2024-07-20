package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Demand;
import com.MCBS.GestiStage.models.Files;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class InternshipDtoResponse {
    private Long internshipId;
    private String titre;
    private Files internshipReport;
    private  Files internshipJournal;
    private StudentDToRespI student;
    private TeacherDtoRespI teacher;
    private LocalDateTime dateDebut;
    private LocalDateTime  dateFin;
    private presentationRequest state;
}
