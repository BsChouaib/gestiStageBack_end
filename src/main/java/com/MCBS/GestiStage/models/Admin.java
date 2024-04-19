package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin extends AppUser{

    @Column(name = "account_creation_date")
    private String accountcreationdate;
    @Column(name = "access_level")
    private String accesslevel;
}
