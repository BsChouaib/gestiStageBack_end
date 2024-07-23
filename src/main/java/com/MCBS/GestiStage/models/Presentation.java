package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Long teacherId;
    private Long studentId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PresentationResult result;

    public Presentation() {
    }

    public Presentation(long presentationId, Date presentationDate, Date presentationStartTime, Date presentationEndTime, String location, String presentationTitle, boolean external, Long teacherId, Long studentId) {
        this.presentationId = presentationId;
        this.presentationDate = presentationDate;
        this.presentationStartTime = presentationStartTime;
        this.presentationEndTime = presentationEndTime;
        this.location = location;
        this.presentationTitle = presentationTitle;
        this.external = external;
        this.teacherId = teacherId;
        this.studentId = studentId;
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

    public Long getTeacherId() {
        return teacherId;
    }

    public Long getStudentId() {
        return studentId;
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

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
