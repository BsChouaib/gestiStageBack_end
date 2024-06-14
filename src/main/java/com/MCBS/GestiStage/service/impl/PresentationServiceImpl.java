package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.PresentationDtoConverter;
import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.response.PresentationDtoResponse;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.Presentation;
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

    public PresentationServiceImpl(PresentationRepository presentationRepository, PresentationDtoConverter presentationDtoConverter) {
        this.presentationRepository = presentationRepository;
        this.presentationDtoConverter = presentationDtoConverter;
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
    public void createPresentation(PresentationDtoRequest presentationDtoRequest)
    {
        try
        {
            Presentation presentation = presentationDtoConverter.convertDtoToPresentation(presentationDtoRequest);
            presentationRepository.save(presentation);
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
