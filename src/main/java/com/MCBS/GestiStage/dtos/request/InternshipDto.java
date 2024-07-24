package com.MCBS.GestiStage.dtos.request;

import com.MCBS.GestiStage.enumerations.presentationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
public class InternshipDto {
    private Long id;
    private String dateDebut;
    private String dateFin;
    private String titre;
    private MultipartFile internshipReport;
    private MultipartFile internshipJournal;
    private presentationRequest newState;

    public InternshipDto() {
    }

    public InternshipDto(Long id, String dateDebut, String dateFin, String titre, MultipartFile internshipReport, MultipartFile internshipJournal, presentationRequest newState) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.titre = titre;
        this.internshipReport = internshipReport;
        this.internshipJournal = internshipJournal;
        this.newState = newState;
    }

    public Long getId() {
        return id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getTitre() {
        return titre;
    }

    public MultipartFile getInternshipReport() {
        return internshipReport;
    }

    public MultipartFile getInternshipJournal() {
        return internshipJournal;
    }

    public presentationRequest getNewState() {
        return newState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setInternshipReport(MultipartFile internshipReport) {
        this.internshipReport = internshipReport;
    }

    public void setInternshipJournal(MultipartFile internshipJournal) {
        this.internshipJournal = internshipJournal;
    }

    public void setNewState(presentationRequest newState) {
        this.newState = newState;
    }
}
