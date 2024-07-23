package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.PresentationResult;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;

public class PresentationDtoResponse {
    private long presentationId;
    private Date presentationDate;
    private Date presentationStartTime;
    private Date presentationEndTime;
    private String location;
    private String presentationTitle;

    private StudentDToRespI student;
    private TeacherDtoRespI teacher;
    private boolean external;
    private PresentationResult result;

    public PresentationDtoResponse() {
    }


    public PresentationDtoResponse(long presentationId, Date presentationDate, Date presentationStartTime, Date presentationEndTime, String location, String presentationTitle, StudentDToRespI student, TeacherDtoRespI teacher, boolean external, PresentationResult result) {
        this.presentationId = presentationId;
        this.presentationDate = presentationDate;
        this.presentationStartTime = presentationStartTime;
        this.presentationEndTime = presentationEndTime;
        this.location = location;
        this.presentationTitle = presentationTitle;
        this.student = student;
        this.teacher = teacher;
        this.external = external;
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

    public PresentationResult getResult() {
        return result;
    }

    public void setResult(PresentationResult result) {
        this.result = result;
    }

    public StudentDToRespI getStudent() {
        return student;
    }

    public TeacherDtoRespI getTeacher() {
        return teacher;
    }

    public void setStudent(StudentDToRespI student) {
        this.student = student;
    }

    public void setTeacher(TeacherDtoRespI teacher) {
        this.teacher = teacher;
    }
}
