package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.PresentationDtoConverter;
import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.response.InternshipDtoResponse;
import com.MCBS.GestiStage.dtos.response.PresentationDtoResponse;
import com.MCBS.GestiStage.enumerations.PresentationResultStatus;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.*;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.NotificationRepository;
import com.MCBS.GestiStage.repository.PresentationRepository;
import com.MCBS.GestiStage.repository.PresentationResultRepository;
import com.MCBS.GestiStage.service.PresentationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PresentationServiceImpl implements PresentationService {

    private final PresentationRepository presentationRepository;
    private final PresentationResultRepository presentationResultRepository;
    private  final PresentationDtoConverter presentationDtoConverter;
    private final NotificationRepository notificationRepository;
    private final AppUserRepository appUserRepository;

    public PresentationServiceImpl(PresentationRepository presentationRepository, PresentationResultRepository presentationResultRepository, PresentationDtoConverter presentationDtoConverter, NotificationRepository notificationRepository, AppUserRepository appUserRepository) {
        this.presentationRepository = presentationRepository;
        this.presentationResultRepository = presentationResultRepository;
        this.presentationDtoConverter = presentationDtoConverter;
        this.notificationRepository = notificationRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<PresentationDtoResponse> getAllPresentations(String userEmail)
    {
        AppUser user = appUserRepository.findByEmail(userEmail);
        if (user == null) {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        if (user instanceof Admin)
        {
            List<Presentation> presentations = presentationRepository.findAll();
            List<PresentationDtoResponse> presentationDtoResponses = presentations.stream()
                    .map(presentation -> presentationDtoConverter.convertToDto(presentation))
                    .collect(Collectors.toList());
            return presentationDtoResponses;
        }
        else if (user instanceof Student)
        {
            List<Presentation> presentations = presentationRepository.findPresentationByStudentId(user.getId());
            List<PresentationDtoResponse> presentationDtoResponses = presentations.stream()
                    .map(presentation -> presentationDtoConverter.convertToDto(presentation))
                    .collect(Collectors.toList());
            return presentationDtoResponses;
        }
        else
        {
          List<Presentation> presentations = presentationRepository.findPresentationByTeacherId(user.getId());
            List<PresentationDtoResponse> presentationDtoResponses = presentations.stream()
                    .map(presentation -> presentationDtoConverter.convertToDto(presentation))
                    .collect(Collectors.toList());
            return presentationDtoResponses;
        }
    }

    @Override
    public PresentationDtoResponse getPresentationById(Long id) {
        Presentation presentation = presentationRepository.findPresentationByPresentationId(id);
        if (presentation == null)
        {
            throw new ApiRequestException("Presentation dose not exist in DB!!!");
        }
        PresentationDtoResponse presentationDtoResponse = presentationDtoConverter.convertToDto(presentation);
        return presentationDtoResponse;
    }

    @Override
    public void createPresentation(Long notificationId, PresentationDtoRequest presentationDtoRequest)
    {

        try
        {
            Notification notification = notificationRepository.findNotificationById(notificationId);
            if (notification == null)
            {
                throw new ApiRequestException("Notification dose not exist in DB!!!");
            }
            AppUser student = appUserRepository.findById(notification.getStudentId());
            if (student == null) {
                throw new ApiRequestException("student dose not exist in DB!!!");
            }
            AppUser teacher = appUserRepository.findById(notification.getTeacherId());
            if (teacher == null) {
                throw new ApiRequestException("teacher dose not exist in DB!!!");
            }
            Presentation presentation = presentationDtoConverter.convertDtoToPresentation(presentationDtoRequest);
            presentation.setPresentationTitle(notification.getTitle());
            presentation.setStudent(student);
            presentation.setTeacher(teacher);
            PresentationResult result = PresentationResult.builder()
                    .passMark("-")
                    .passMark("-")
                    .status(PresentationResultStatus.InProgress)
                    .build();
            presentation.setResult(result);
            presentationRepository.save(presentation);
            notificationRepository.delete(notification);
        }
        catch (Exception e)
        {
            throw new ApiRequestException("Error in the creation of the presentation:"
                    + e.getMessage());
        }


    }

    @Override
    public void updatePresentation(PresentationDtoRequest presentationDtoRequest, Long id) {
        Presentation presentation = presentationRepository.findPresentationByPresentationId(id);
        if (presentation == null)
        {
            throw new ApiRequestException("Presentation dose not exist in DB!!!");
        }
        Presentation newPresentation = presentationDtoConverter.convertDtoToPresentation(presentationDtoRequest);

        //title
        if(newPresentation.getPresentationTitle()!=null)
        {
            presentation.setPresentationTitle(newPresentation.getPresentationTitle());
        }
        //presentationDate
        if(newPresentation.getPresentationDate()!=null)
        {
            presentation.setPresentationDate(newPresentation.getPresentationDate());
        }
        //presentationStartTime
        if(newPresentation.getPresentationStartTime()!=null)
        {
            presentation.setPresentationStartTime(newPresentation.getPresentationStartTime());
        }
        //presentationEndTime
        if(newPresentation.getPresentationEndTime()!=null)
        {
            presentation.setPresentationEndTime(newPresentation.getPresentationEndTime());
        }
        //location
        if(newPresentation.getLocation()!=null)
        {
            presentation.setLocation(newPresentation.getLocation());
        }
        //External
        if(newPresentation.isExternal())
        {
            presentation.setExternal(newPresentation.isExternal());
        }
        presentationRepository.save(presentation);

    }

    @Override
    public void deletePresentation(Long id) {
        Presentation presentation = presentationRepository.findPresentationByPresentationId(id);
        if (presentation == null)
        {
            throw new ApiRequestException("Presentation dose not exist in DB!!!");
        }
        presentationRepository.delete(presentation);
    }

    @Override
    public void validationPresentation(Long id, PresentationResultStatus newState) {
        Presentation presentation = presentationRepository.findPresentationByPresentationId(id);
        if (presentation == null)
        {
            throw new ApiRequestException("Presentation dose not exist in DB!!!");
        }
        PresentationResult result = presentationResultRepository.findPresentationResultById(presentation.getResult().getId());
        result.setStatus(newState);
        presentation.setResult(result);
        presentationRepository.save(presentation);

    }
}
