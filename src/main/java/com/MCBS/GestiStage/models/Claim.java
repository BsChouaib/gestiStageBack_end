package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long claimId;
    private Date claimDate;
    private String Description;
    @Enumerated(EnumType.STRING)
    private Status Statut;
    private String emailSender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser user;

    public Claim() {
    }

    public Claim(long claimId, Date claimDate, String description, Status statut, String emailSender, AppUser user) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        Description = description;
        Statut = statut;
        this.emailSender = emailSender;
        this.user = user;
    }

    public long getClaimId() {
        return claimId;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public String getDescription() {
        return Description;
    }

    public Status getStatut() {
        return Statut;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public AppUser getUser() {
        return user;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setStatut(Status statut) {
        Statut = statut;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
