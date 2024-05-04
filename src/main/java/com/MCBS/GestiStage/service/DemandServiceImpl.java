package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.DemandDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {
    @Override
    public void createDemand(DemandDto demand, String email)
    {

    }
}
