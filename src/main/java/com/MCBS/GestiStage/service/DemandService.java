package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;

import java.util.List;

public interface DemandService
{
    void createDemand(DemandDto demand, String email);
    List<DemandDtoResponse> getAllDemands();

}
