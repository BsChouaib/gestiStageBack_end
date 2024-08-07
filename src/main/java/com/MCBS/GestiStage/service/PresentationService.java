package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.PresentationDtoResponse;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import com.MCBS.GestiStage.enumerations.PresentationResultStatus;
import com.MCBS.GestiStage.enumerations.presentationRequest;

import java.util.List;

public interface PresentationService {
    List<PresentationDtoResponse> getAllPresentations(String userEmail);
    PresentationDtoResponse getPresentationById(Long id);

    void createPresentation(Long notificationId, PresentationDtoRequest presentationDtoRequest);
    void updatePresentation(PresentationDtoRequest presentationDtoRequest, Long id);
    void deletePresentation(Long id);

    void validationPresentation(Long id, PresentationResultStatus newState);
}
