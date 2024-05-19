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
    @OneToOne(cascade = CascadeType.ALL)
    private Student studentName;
    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacherName;
    private LocalDateTime dateDebut;
    private LocalDateTime  dateFin;
    @OneToOne(cascade = CascadeType.ALL)
    private Demand demand;
    @Enumerated(EnumType.STRING)
    private presentationRequest state;

    public Internship() {
    }

    public Internship(Long internshipId, String titre, Student studentName, Teacher teacherName, LocalDateTime dateDebut, LocalDateTime dateFin, Demand demand, presentationRequest state) {
        this.internshipId = internshipId;
        this.titre = titre;
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.demand = demand;
        this.state = state;
    }

    public Long getInternshipId() {
        return internshipId;
    }

    public String getTitre() {
        return titre;
    }

    public Student getStudentName() {
        return studentName;
    }

    public Teacher getTeacherName() {
        return teacherName;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public Demand getDemand() {
        return demand;
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

    public void setStudentName(Student studentName) {
        this.studentName = studentName;
    }

    public void setTeacherName(Teacher teacherName) {
        this.teacherName = teacherName;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public void setState(presentationRequest state) {
        this.state = state;
    }
}
