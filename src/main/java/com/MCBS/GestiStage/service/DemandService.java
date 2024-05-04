package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.DemandDto;

public interface DemandService
{
    void createDemand(DemandDto demand, String email);

}
