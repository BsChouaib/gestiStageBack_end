package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.presentationRequest;
import lombok.Builder;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Builder
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internshipId;
    private String titre;
    private String studentName;
    private String teacherName;
    private LocalDateTime dateDebut;
    private LocalDateTime  dateFin;
    @Enumerated(EnumType.STRING)
    private presentationRequest state;

    public Internship() {
    }

    public Internship(Long internshipId, String titre, String studentName, String teacherName, LocalDateTime dateDebut, LocalDateTime dateFin, presentationRequest state) {
        this.internshipId = internshipId;
        this.titre = titre;
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.state = state;
    }

    public Long getInternshipId() {
        return internshipId;
    }

    public String getTitre() {
        return titre;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public presentationRequest getState() {
        return state;
    }

    public void setInternshipId(Long internshipId) {
        this.internshipId = internshipId;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setState(presentationRequest state) {
        this.state = state;
    }
}
