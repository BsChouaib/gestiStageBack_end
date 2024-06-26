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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Files internshipReport;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Files internshipJournal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser student;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser teacher;
    private LocalDateTime dateDebut;
    private LocalDateTime  dateFin;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Demand demand;
    @Enumerated(EnumType.STRING)
    private presentationRequest state;

    public Internship() {
    }

    public Internship(Long internshipId, String titre, Files internshipReport, Files internshipJournal, AppUser student, AppUser teacher, LocalDateTime dateDebut, LocalDateTime dateFin, Demand demand, presentationRequest state) {
        this.internshipId = internshipId;
        this.titre = titre;
        this.internshipReport = internshipReport;
        this.internshipJournal = internshipJournal;
        this.student = student;
        this.teacher = teacher;
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

    public Files getInternshipReport() {
        return internshipReport;
    }

    public Files getInternshipJournal() {
        return internshipJournal;
    }

    public AppUser getStudent() {
        return student;
    }

    public AppUser getTeacher() {
        return teacher;
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

    public void setInternshipReport(Files internshipReport) {
        this.internshipReport = internshipReport;
    }

    public void setInternshipJournal(Files internshipJournal) {
        this.internshipJournal = internshipJournal;
    }

    public void setStudent(AppUser student) {
        this.student = student;
    }

    public void setTeacher(AppUser teacher) {
        this.teacher = teacher;
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
