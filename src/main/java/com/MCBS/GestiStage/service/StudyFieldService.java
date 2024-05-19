package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.StudyFieldDto;
import com.MCBS.GestiStage.dtos.response.StudyFieldDtoResponse;

import java.util.List;

public interface StudyFieldService {
    void addNewStudyField(StudyFieldDto studyFieldDto);
    void updateStudyField(StudyFieldDto studyFieldDto, String id);
    StudyFieldDtoResponse getStudyFieldById(String id);
    public void deleteStudyField(String id);
    List<StudyFieldDtoResponse> getAllStudyFields();
}
