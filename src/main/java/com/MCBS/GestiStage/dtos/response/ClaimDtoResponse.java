package com.MCBS.GestiStage.dtos.response;


import com.MCBS.GestiStage.enumerations.Status;
import lombok.Data;

@Data
public class ClaimDtoResponse {
    private long claimId;
    private String Description;
    private Status Statut;
    private String emailSender;

}
