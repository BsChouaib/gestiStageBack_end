package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.ClaimDtoResponse;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;

import java.util.List;

public interface DemandService
{
    void createDemand(DemandDto demand, String email);
    List<DemandDtoResponse> getAllDemands();

    DemandDtoResponse updateDemandState(Long id, Status newState);

    List<DemandDtoResponse> getUserDemands(String email);

}
