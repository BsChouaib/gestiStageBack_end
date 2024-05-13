package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.DemandDtoConverter;
import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
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
import com.MCBS.GestiStage.repository.InternshipRepository;
import com.MCBS.GestiStage.repository.SubjectRepository;
import com.MCBS.GestiStage.service.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {
    private final DemandRepository demandRepository;
    private final AppUserRepository appUserRepository;

    private final SubjectRepository subjectRepository;

    private final DemandDtoConverter demandDtoConverter;

    private  final InternshipRepository internshipRepository;

    public DemandServiceImpl(DemandRepository demandRepository, AppUserRepository appUserRepository, SubjectRepository subjectRepository, DemandDtoConverter demandDtoConverter, InternshipRepository internshipRepository) {
        this.demandRepository = demandRepository;
        this.appUserRepository = appUserRepository;
        this.subjectRepository = subjectRepository;
        this.demandDtoConverter = demandDtoConverter;
        this.internshipRepository = internshipRepository;
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

        List<Demand> demands = demandRepository.findAll();

        List<DemandDtoResponse> demandDtoResponses = demands.stream()
                .map(demand-> demandDtoConverter.convertToDto(demand))
                .collect(Collectors.toList());
        return demandDtoResponses;
    }

    @Override
    public DemandDtoResponse updateDemandState(Long id, Status newState) {
        Demand demand = demandRepository.findDemandByDemandtId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        //
        String title = demand.getSubject().getTitle();
        String teacherFirstName = demand.getSubject().getTeacher().getFirstname();
        String teacherLastName = demand.getSubject().getTeacher().getLastname();
        String studentFirstName = demand.getAppUser().getFirstname();
        String studentLastName = demand.getAppUser().getFirstname();
        //format date(pattern)
        LocalDateTime currentDate = LocalDateTime.now();
        boolean started = false;
        boolean approved = false;
        if(newState.equals(Status.Approved))
        {
            if (demand.getSubject().getInternshipType().equals(InternshipType.EndOfStudiesProject))
            {
                internshipRepository.save( Internship.builder()
                        .titre(title)
                        .state(presentationRequest.InProgress)
                        .dateDebut(currentDate)
                        .teacherName(teacherFirstName+" " +teacherLastName)
                        .studentName(studentFirstName+" " +studentLastName)
                        .dateFin(currentDate.plusMonths(6))
                        .build());
                started = true;
                approved = true;
            }
          else if(demand.getSubject().getInternshipType().equals(InternshipType.perfectionnementInternship))
            {
                internshipRepository.save( Internship.builder()
                        .titre(title)
                        .state(presentationRequest.InProgress)
                        .dateDebut(currentDate)
                        .teacherName(teacherFirstName+" " +teacherLastName)
                        .studentName(studentFirstName+" " +studentLastName)
                        .dateFin(currentDate.plusMonths(2))
                        .build());
                started = true;
                approved = true;
            }
            else
            {
                internshipRepository.save( Internship.builder()
                        .titre(title)
                        .state(presentationRequest.InProgress)
                        .dateDebut(currentDate)
                        .teacherName(teacherFirstName+" " +teacherLastName)
                        .studentName(studentFirstName+" " +studentLastName)
                        .dateFin(currentDate.plusMonths(1))
                        .build());
                started = true;
                approved = true;
            }
        }
        if(started&&approved)
        {
            demand.setStatus(newState);
        }
        else if(!started)
        {
            demand.setStatus(newState);
        }
        demandRepository.save(demand);
        return demandDtoConverter.convertToDto(demand);
    }

    @Override
    public List<DemandDtoResponse> getUserDemands(String email) {

        AppUser user = appUserRepository.findByEmail(email);
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }

        List<Demand> demands = demandRepository.findDemandByAppUser(user);


        System.out.println(demands);

        List<DemandDtoResponse> demandDtoResponses = demands.stream()
                .map(demand-> demandDtoConverter.convertToDto(demand))
                .collect(Collectors.toList());
        return demandDtoResponses;
    }
}
