package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long demandtId;
    private String email;
    private String cv; // Chemin vers le CV
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Subject subject;

}
