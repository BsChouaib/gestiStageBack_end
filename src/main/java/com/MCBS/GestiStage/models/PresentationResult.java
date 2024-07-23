package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.PresentationResultStatus;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
public class PresentationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String passMark;
    private String rating;

    private PresentationResultStatus status;

    public PresentationResult() {
    }

    public PresentationResult(Long id, String passMark, String rating, PresentationResultStatus status) {
        this.id = id;
        this.passMark = passMark;
        this.rating = rating;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getPassMark() {
        return passMark;
    }

    public String getRating() {
        return rating;
    }

    public PresentationResultStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassMark(String passMark) {
        this.passMark = passMark;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStatus(PresentationResultStatus status) {
        this.status = status;
    }
}
