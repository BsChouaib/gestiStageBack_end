package com.MCBS.GestiStage.dtos.response;

import com.MCBS.GestiStage.enumerations.Status;
import lombok.Data;

import java.util.Date;

@Data
public class DemandDtoResponse {
    private long demandId;
    private Date demandeDate;
    private Status status;
}
