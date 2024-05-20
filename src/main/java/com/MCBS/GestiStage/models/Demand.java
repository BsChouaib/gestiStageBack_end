package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.Status;
import lombok.Builder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long demandId;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Files resume;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Files motivationLetter;



    @Temporal(TemporalType.DATE)
    private Date demandeDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser student;

    public Demand() {
    }

    public Demand(long demandId, Files resume, Files motivationLetter, Date demandeDate, Status status, Subject subject, AppUser student) {
        this.demandId = demandId;
        this.resume = resume;
        this.motivationLetter = motivationLetter;
        this.demandeDate = demandeDate;
        this.status = status;
        this.subject = subject;
        this.student = student;
    }

    public long getDemandId() {
        return demandId;
    }

    public Files getResume() {
        return resume;
    }

    public Files getMotivationLetter() {
        return motivationLetter;
    }

    public Date getDemandeDate() {
        return demandeDate;
    }

    public Status getStatus() {
        return status;
    }

    public Subject getSubject() {
        return subject;
    }

    public AppUser getStudent() {
        return student;
    }

    public void setDemandId(long demandId) {
        this.demandId = demandId;
    }

    public void setResume(Files resume) {
        this.resume = resume;
    }

    public void setMotivationLetter(Files motivationLetter) {
        this.motivationLetter = motivationLetter;
    }

    public void setDemandeDate(Date demandeDate) {
        this.demandeDate = demandeDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setStudent(AppUser student) {
        this.student = student;
    }
}
