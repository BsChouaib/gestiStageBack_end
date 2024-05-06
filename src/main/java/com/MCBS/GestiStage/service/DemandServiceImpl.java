package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Demand;
import com.MCBS.GestiStage.models.Subject;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.DemandRepository;
import com.MCBS.GestiStage.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {
    private final DemandRepository demandRepository;
    private final AppUserRepository appUserRepository;

    private final SubjectRepository subjectRepository;

    public DemandServiceImpl(DemandRepository demandRepository, AppUserRepository appUserRepository, SubjectRepository subjectRepository) {
        this.demandRepository = demandRepository;
        this.appUserRepository = appUserRepository;
        this.subjectRepository = subjectRepository;

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
                .email(email)
                .subject(subject)
                .build()
                            );
    }
}
