package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.StudyFieldConverter;
import com.MCBS.GestiStage.dtos.request.StudyFieldDto;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import com.MCBS.GestiStage.dtos.response.StudyFieldDtoResponse;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.Student;
import com.MCBS.GestiStage.models.StudyField;
import com.MCBS.GestiStage.repository.StudyFieldRepository;
import com.MCBS.GestiStage.service.StudyFieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudyFieldServiceImpl implements StudyFieldService {

    private final StudyFieldRepository studyFieldRepository;

    private final StudyFieldConverter studyFieldConverter;

    public StudyFieldServiceImpl(StudyFieldRepository studyFieldRepository, StudyFieldConverter studyFieldConverter) {
        this.studyFieldRepository = studyFieldRepository;
        this.studyFieldConverter = studyFieldConverter;
    }

    @Override
    public void addNewStudyField(StudyFieldDto studyFieldDto) {

        studyFieldRepository.save(studyFieldConverter.convertDtoToObject(studyFieldDto));

    }
    @Override
    public void updateStudyField(StudyFieldDto studyFieldDto, String id) {
        StudyField studyField = studyFieldRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("StudyField does not exist in DB!!!"));
        studyField.setFieldName(studyFieldDto.getFieldName());
        studyFieldRepository.save(studyField);
    }

    @Override
    public StudyFieldDtoResponse getStudyFieldById(String id) {
        StudyField studyField = studyFieldRepository
                                .findById(id)
                                .orElseThrow(() -> new ApiRequestException("StudyField does not exist in DB!!!"));
        return studyFieldConverter.convertToDto(studyField);
    }

    @Override
    public void deleteStudyField(String id) {
        StudyField studyField = studyFieldRepository
                .findById(id)
                .orElseThrow(() -> new ApiRequestException("StudyField does not exist in DB!!!"));
        studyFieldRepository.delete(studyField);
    }

    @Override
    public List<StudyFieldDtoResponse> getAllStudyFields() {
        List<StudyField> studyFields = studyFieldRepository.findAll();
        List<StudyFieldDtoResponse> studyFieldDtoResponses = studyFields.stream()
                .map(StudyField-> studyFieldConverter.convertToDto(StudyField))
                .collect(Collectors.toList());
        return studyFieldDtoResponses;
    }
}
