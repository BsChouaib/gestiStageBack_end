package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.response.ClaimDtoResponse;
import com.MCBS.GestiStage.models.Claim;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClaimDtoConverter {
    private ModelMapper modelMapper;

    public ClaimDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClaimDtoResponse convertToDto(Claim claim)
    {
        ClaimDtoResponse claimDto = modelMapper.map(claim,ClaimDtoResponse.class);
        return claimDto;
    }

}
