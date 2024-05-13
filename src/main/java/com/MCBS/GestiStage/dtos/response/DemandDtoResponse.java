package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Subject;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class DemandDtoResponse {
    private long demandtId;
    private String cv;
    private Date dateDemande;
    private Status status;
}
