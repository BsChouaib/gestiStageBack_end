package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.response.PresentationDtoResponse;
import com.MCBS.GestiStage.models.Presentation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PresentationDtoConverter {
    private ModelMapper modelMapper;

    public PresentationDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PresentationDtoResponse convertToDto(Presentation presentation)
    {
        PresentationDtoResponse presentationDtoResponse = modelMapper.map(presentation, PresentationDtoResponse.class);
        return presentationDtoResponse;
    }

    public Presentation convertDtoToPresentation(PresentationDtoRequest presentationDtoRequest)
    {
        Presentation presentation = modelMapper.map(presentationDtoRequest, Presentation.class);
        return presentation;
    }
}
