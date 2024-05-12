package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.InternshipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;
    private String title;
    private String description;
    private String tEmail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private AppUser teacher;
    @Enumerated(EnumType.STRING)
    private InternshipType internshipType;
    @OneToMany(mappedBy = "subject")
    private List<Demand> demands = new ArrayList<>();

    public Subject() {
    }

    public Subject(long subjectId, String title, String description, String tEmail, AppUser teacher, InternshipType internshipType, List<Demand> demands) {
        this.subjectId = subjectId;
        this.title = title;
        this.description = description;
        this.tEmail = tEmail;
        this.teacher = teacher;
        this.internshipType = internshipType;
        this.demands = demands;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String gettEmail() {
        return tEmail;
    }

    public AppUser getTeacher() {
        return teacher;
    }

    public InternshipType getInternshipType() {
        return internshipType;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public void setTeacher(AppUser teacher) {
        this.teacher = teacher;
    }

    public void setInternshipType(InternshipType internshipType) {
        this.internshipType = internshipType;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }
}
