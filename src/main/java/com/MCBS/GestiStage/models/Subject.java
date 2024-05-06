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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private AppUser teacher;
    @Enumerated(EnumType.STRING)
    private InternshipType internshipType;
    @OneToMany(mappedBy = "subject")
    private List<Demand> demands = new ArrayList<>();
}
