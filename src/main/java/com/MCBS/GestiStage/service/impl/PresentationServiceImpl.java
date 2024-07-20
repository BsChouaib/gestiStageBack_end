package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.PresentationDtoConverter;
import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.response.PresentationDtoResponse;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.Notification;
import com.MCBS.GestiStage.models.Presentation;
import com.MCBS.GestiStage.repository.NotificationRepository;
import com.MCBS.GestiStage.repository.PresentationRepository;
import com.MCBS.GestiStage.service.PresentationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PresentationServiceImpl implements PresentationService {

    private final PresentationRepository presentationRepository;
    private  final PresentationDtoConverter presentationDtoConverter;
    private final NotificationRepository notificationRepository;

    public PresentationServiceImpl(PresentationRepository presentationRepository, PresentationDtoConverter presentationDtoConverter, NotificationRepository notificationRepository) {
        this.presentationRepository = presentationRepository;
        this.presentationDtoConverter = presentationDtoConverter;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<PresentationDtoResponse> getAllPresentations() {
        return null;
    }

    @Override
    public PresentationDtoResponse getPresentationById(Long id) {
        return null;
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
            Presentation presentation = presentationDtoConverter.convertDtoToPresentation(presentationDtoRequest);
            presentation.setPresentationTitle(notification.getTitle());
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

    }

    @Override
    public void deletePresentation(Long id) {

    }
}
