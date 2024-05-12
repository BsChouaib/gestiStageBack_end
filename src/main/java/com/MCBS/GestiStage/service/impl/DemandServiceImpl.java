package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.DemandDtoConverter;
import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.enumerations.InternshipType;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Demand;
import com.MCBS.GestiStage.models.Internship;
import com.MCBS.GestiStage.models.Subject;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.DemandRepository;
import com.MCBS.GestiStage.repository.SubjectRepository;
import com.MCBS.GestiStage.service.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {
    private final DemandRepository demandRepository;
    private final AppUserRepository appUserRepository;

    private final SubjectRepository subjectRepository;

    private final DemandDtoConverter demandDtoConverter;

    public DemandServiceImpl(DemandRepository demandRepository, AppUserRepository appUserRepository, SubjectRepository subjectRepository, DemandDtoConverter demandDtoConverter) {
        this.demandRepository = demandRepository;
        this.appUserRepository = appUserRepository;
        this.subjectRepository = subjectRepository;

        this.demandDtoConverter = demandDtoConverter;
    }

    @Override
    public void createDemand(DemandDto demand, String email)
    {
        AppUser user = appUserRepository.findByEmail(email);
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        Subject subject = subjectRepository.findSubjectBySubjectId(demand.subjectId());
        if (subject == null)
        {
            throw new ApiRequestException("Subject dose not exist in DB!!!");
        }
        demandRepository.save(
                Demand.builder()
                .dateDemande(new Date())
                .cv(demand.cv())
                .status(Status.Pending)
                .subject(subject)
                        .appUser(user)
                .build()
                            );
    }

    @Override
    public List<DemandDtoResponse> getAllDemands() {
        return null;
    }

    @Override
    public DemandDtoResponse updateDemandState(Long id, Status newState) {
        Demand demand = demandRepository.findDemandByDemandtId(id);

        System.out.println(demand.getSubject().getTitle());

        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        if(newState.equals(Status.Approved))
        {
//            Subject subject =  subjectRepository.findSubjectBySubjectId(demand.)
//            System.out.println(subject);
            /*
            Internship.builder()
                    .titre(demand.getSubject())
                    .state(presentationRequest.InProgress)
                    .dateDebut(new Date())
                    .teacherName(demand.get)*/

        }
        demand.setStatus(newState);
        demandRepository.save(demand);
        return demandDtoConverter.convertToDto(demand);
    }
}
