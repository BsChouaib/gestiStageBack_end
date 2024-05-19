package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.request.StudyFieldDto;
import com.MCBS.GestiStage.dtos.response.StudyFieldDtoResponse;
import com.MCBS.GestiStage.models.StudyField;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudyFieldConverter {

    private ModelMapper modelMapper;

    public StudyFieldConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudyFieldDtoResponse convertToDto(StudyField studyField)
    {
        StudyFieldDtoResponse studyFieldDtoResponse = modelMapper.map(studyField, StudyFieldDtoResponse.class);
        return studyFieldDtoResponse;
    }

    public StudyField convertDtoToObject(StudyFieldDto studyFieldDto)
    {
        StudyField studyField = modelMapper.map(studyFieldDto,StudyField.class);
        return studyField;
    }
}
