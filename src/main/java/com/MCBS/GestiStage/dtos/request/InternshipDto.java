package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.enumerations.presentationRequest;

import java.time.LocalDateTime;
public class InternshipDto {
    private String titre;
    private LocalDateTime dateDebut;
    private LocalDateTime  dateFin;
    private presentationRequest state;

    public InternshipDto() {
    }

    public InternshipDto(String titre, LocalDateTime dateDebut, LocalDateTime dateFin, presentationRequest state) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.state = state;
    }

    public String getTitre() {
        return titre;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public presentationRequest getState() {
        return state;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public void setState(presentationRequest state) {
        this.state = state;
    }
}
