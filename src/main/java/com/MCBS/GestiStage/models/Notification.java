package com.MCBS.GestiStage.models;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long InternshipId;
    private String Title;
    private String StudentName;
    private String TeacherName;
    private Long teacherId;
    private Long studentId;

    public Notification() {
    }

    public Notification(Long id, Long internshipId, String title, String studentName, String teacherName, Long teacherId, Long studentId) {
        this.id = id;
        InternshipId = internshipId;
        Title = title;
        StudentName = studentName;
        TeacherName = teacherName;
        this.teacherId = teacherId;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public Long getInternshipId() {
        return InternshipId;
    }

    public String getTitle() {
        return Title;
    }

    public String getStudentName() {
        return StudentName;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInternshipId(Long internshipId) {
        InternshipId = internshipId;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
