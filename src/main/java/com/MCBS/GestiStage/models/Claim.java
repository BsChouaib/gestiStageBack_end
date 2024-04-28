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
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long claimId;
    private Date claimDate;
    private String Description;
    private Status Statut;
    @OneToOne(fetch = FetchType.EAGER)
    private AppUser user;


}
