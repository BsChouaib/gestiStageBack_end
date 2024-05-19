package com.MCBS.GestiStage.dtos.request;

import lombok.Builder;

@Builder
public class DemandDto {
    private Long subjectId;
    private byte[] cv;
    private byte[] motivationLetter;

    public DemandDto() {
    }

    public DemandDto(Long subjectId, byte[] cv, byte[] motivationLetter) {
        this.subjectId = subjectId;
        this.cv = cv;
        this.motivationLetter = motivationLetter;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public byte[] getCv() {
        return cv;
    }

    public byte[] getMotivationLetter() {
        return motivationLetter;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public void setMotivationLetter(byte[] motivationLetter) {
        this.motivationLetter = motivationLetter;
    }
}
