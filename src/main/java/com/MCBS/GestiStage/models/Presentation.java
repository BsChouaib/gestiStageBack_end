package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data
@Builder
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long presentationId;
    private Date presentationDate;
    private Date presentationStartTime;
    private Date presentationEndTime;
    private String location;
    private String presentationTitle;
    private boolean external;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser student;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser teacher;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PresentationResult result;

    public Presentation() {
    }

    public Presentation(long presentationId, Date presentationDate, Date presentationStartTime, Date presentationEndTime, String location, String presentationTitle, boolean external, AppUser student, AppUser teacher, PresentationResult result) {
        this.presentationId = presentationId;
        this.presentationDate = presentationDate;
        this.presentationStartTime = presentationStartTime;
        this.presentationEndTime = presentationEndTime;
        this.location = location;
        this.presentationTitle = presentationTitle;
        this.external = external;
        this.student = student;
        this.teacher = teacher;
        this.result = result;
    }

    public long getPresentationId() {
        return presentationId;
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

    public String getPresentationTitle() {
        return presentationTitle;
    }

    public boolean isExternal() {
        return external;
    }

    public AppUser getStudent() {
        return student;
    }

    public AppUser getTeacher() {
        return teacher;
    }

    public PresentationResult getResult() {
        return result;
    }

    public void setPresentationId(long presentationId) {
        this.presentationId = presentationId;
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

    public void setPresentationTitle(String presentationTitle) {
        this.presentationTitle = presentationTitle;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public void setStudent(AppUser student) {
        this.student = student;
    }

    public void setTeacher(AppUser teacher) {
        this.teacher = teacher;
    }

    public void setResult(PresentationResult result) {
        this.result = result;
    }
}
