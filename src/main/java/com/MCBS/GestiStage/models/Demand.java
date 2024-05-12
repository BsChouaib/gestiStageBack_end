package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long demandtId;
    private String cv; // Chemin vers le CV
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

    public Demand() {
    }

    public Demand(long demandtId, String cv, Date dateDemande, Status status, Subject subject, AppUser appUser) {
        this.demandtId = demandtId;
        this.cv = cv;
        this.dateDemande = dateDemande;
        this.status = status;
        this.subject = subject;
        this.appUser = appUser;
    }

    public long getDemandtId() {
        return demandtId;
    }

    public String getCv() {
        return cv;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public Status getStatus() {
        return status;
    }

    public Subject getSubject() {
        return subject;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setDemandtId(long demandtId) {
        this.demandtId = demandtId;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
