package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.models.Files;
import com.MCBS.GestiStage.models.Subject;
import lombok.Data;

import java.util.Date;


public class DemandDtoResponse {
    private long demandId;
    private Date demandeDate;
    private Status status;

    private Files resume;

    private Files motivationLetter;
    private SubjectDtoResponse subject;

    public DemandDtoResponse() {
    }

    public DemandDtoResponse(long demandId, Date demandeDate, Status status, Files resume, Files motivationLetter, SubjectDtoResponse subject) {
        this.demandId = demandId;
        this.demandeDate = demandeDate;
        this.status = status;
        this.resume = resume;
        this.motivationLetter = motivationLetter;
        this.subject = subject;
    }

    public long getDemandId() {
        return demandId;
    }

    public Date getDemandeDate() {
        return demandeDate;
    }

    public Status getStatus() {
        return status;
    }

    public Files getResume() {
        return resume;
    }

    public Files getMotivationLetter() {
        return motivationLetter;
    }

    public SubjectDtoResponse getSubject() {
        return subject;
    }

    public void setDemandId(long demandId) {
        this.demandId = demandId;
    }

    public void setDemandeDate(Date demandeDate) {
        this.demandeDate = demandeDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setResume(Files resume) {
        this.resume = resume;
    }

    public void setMotivationLetter(Files motivationLetter) {
        this.motivationLetter = motivationLetter;
    }

    public void setSubject(SubjectDtoResponse subject) {
        this.subject = subject;
    }
}
