package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.models.Subject;
import lombok.Data;

import java.util.Date;


public class DemandDtoResponse {
    private long demandId;
    private Date demandeDate;
    private Status status;
    private Subject subject;

    public DemandDtoResponse() {
    }

    public DemandDtoResponse(long demandId, Date demandeDate, Status status, Subject subject) {
        this.demandId = demandId;
        this.demandeDate = demandeDate;
        this.status = status;
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

    public Subject getSubject() {
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

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
