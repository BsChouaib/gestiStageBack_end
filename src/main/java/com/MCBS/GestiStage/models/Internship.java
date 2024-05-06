package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.presentationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internshipId;
    private String titre;
    private String studentName;
    private String teacherName;
    private Date dateDebut;
    private Date dateFin;
    @Enumerated(EnumType.STRING)
    private presentationRequest state;
}
