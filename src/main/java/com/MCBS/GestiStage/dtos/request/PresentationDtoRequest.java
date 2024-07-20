package com.MCBS.GestiStage.dtos.request;

import java.util.Date;

public class PresentationDtoRequest {
    private Date presentationDate;
    private Date presentationStartTime;
    private Date presentationEndTime;
    private String location;
    private boolean external;

    public PresentationDtoRequest() {
    }

    public PresentationDtoRequest(Date presentationDate, Date presentationStartTime, Date presentationEndTime, String location, boolean external) {
        this.presentationDate = presentationDate;
        this.presentationStartTime = presentationStartTime;
        this.presentationEndTime = presentationEndTime;
        this.location = location;
        this.external = external;
    }

    public Date getPresentationDate() {
        return presentationDate;
    }

    public Date getPresentationStartTime() {
        return presentationStartTime;
    }

    public Date getPresentationEndTime() {
        return presentationEndTime;
    }

    public String getLocation() {
        return location;
    }

    public boolean isExternal() {
        return external;
    }

    public void setPresentationDate(Date presentationDate) {
        this.presentationDate = presentationDate;
    }

    public void setPresentationStartTime(Date presentationStartTime) {
        this.presentationStartTime = presentationStartTime;
    }

    public void setPresentationEndTime(Date presentationEndTime) {
        this.presentationEndTime = presentationEndTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }
}
