package com.MCBS.GestiStage.models;

import com.MCBS.GestiStage.enumerations.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private Status Statut;
    private String emailSender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser user;


}
