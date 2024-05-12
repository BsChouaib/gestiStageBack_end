package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.request.SubjectDtoRequest;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.models.Demand;
import com.MCBS.GestiStage.models.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DemandDtoConverter {
    private ModelMapper modelMapper;

    public DemandDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DemandDtoResponse convertToDto(Demand demand)
    {
        DemandDtoResponse demandDtoResponse = modelMapper.map(demand, DemandDtoResponse.class);
        return demandDtoResponse;
    }

    public Demand convertDtoToDemand(DemandDtoResponse demandDtoResponse)
    {
        Demand demand = modelMapper.map(demandDtoResponse,Demand.class);
        return demand;
    }
}
