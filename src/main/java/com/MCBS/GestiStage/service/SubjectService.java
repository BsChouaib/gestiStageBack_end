package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.SubjectDtoRequest;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;


import java.util.List;

public interface SubjectService
{
    void createSubject(SubjectDtoRequest subject);
    void updateSubject(SubjectDtoRequest subject, Long id);
    SubjectDtoResponse getSubjectId(Long id);
    public void deleteSubject(Long id);
    List<SubjectDtoResponse> getAllSubjects();
}
