package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.PresentationDtoResponse;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;

import java.util.List;

public interface PresentationService {
    List<PresentationDtoResponse> getAllPresentations();
    PresentationDtoResponse getPresentationById(Long id);

    void createPresentation( PresentationDtoRequest presentationDtoRequest);
    void updatePresentation(PresentationDtoRequest presentationDtoRequest, Long id);
    void deletePresentation(Long id);
}
