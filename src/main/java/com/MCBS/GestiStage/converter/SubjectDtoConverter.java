package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.request.SubjectDtoRequest;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.models.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SubjectDtoConverter {
    private ModelMapper modelMapper;

    public SubjectDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SubjectDtoResponse convertToDto(Subject subject)
    {
        SubjectDtoResponse subjectDtoResponse = modelMapper.map(subject, SubjectDtoResponse.class);
        return subjectDtoResponse;
    }

    public Subject convertDtoToSubject(SubjectDtoRequest subjectDtoRequest)
    {
        Subject subject = modelMapper.map(subjectDtoRequest,Subject.class);
        return subject;
    }
}
