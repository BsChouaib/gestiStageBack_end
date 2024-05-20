package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.DemandDtoConverter;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.enumerations.InternshipType;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.*;
import com.MCBS.GestiStage.repository.*;
import com.MCBS.GestiStage.service.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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

    private  final FilesRepository filesRepository;

    public DemandServiceImpl(DemandRepository demandRepository, AppUserRepository appUserRepository, SubjectRepository subjectRepository, DemandDtoConverter demandDtoConverter, InternshipRepository internshipRepository, FilesRepository filesRepository) {
        this.demandRepository = demandRepository;
        this.appUserRepository = appUserRepository;
        this.subjectRepository = subjectRepository;
        this.demandDtoConverter = demandDtoConverter;
        this.internshipRepository = internshipRepository;
        this.filesRepository = filesRepository;
    }

    @Override
    public void createDemand(Long subjectId, String email, MultipartFile cv, MultipartFile motivationLetter) throws IOException
    {
        AppUser user = appUserRepository.findByEmail(email);
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        Subject subject = subjectRepository.findSubjectBySubjectId(subjectId);
        if (subject == null)
        {
            throw new ApiRequestException("Subject dose not exist in DB!!!");
        }
        //
        String resumeName = StringUtils.cleanPath(cv.getOriginalFilename());
            if (resumeName.contains("..")) {
                throw new ApiRequestException("resumeName contains invalid path sequence "
                        + resumeName);
            }
            Files resume = Files.builder()
                    .fileType(cv.getContentType())
                    .fileName(resumeName)
                    .data(cv.getBytes())
                    .build();
            filesRepository.save(resume);
            //
            String letterName = StringUtils.cleanPath(motivationLetter.getOriginalFilename());
                if (letterName.contains("..")) {
                    throw new ApiRequestException("letterName contains invalid path sequence "
                            + letterName);
                }
                Files letter = Files.builder()
                        .fileType(motivationLetter.getContentType())
                        .fileName(letterName)
                        .data(motivationLetter.getBytes())
                        .build();
                filesRepository.save(letter);
                //
                demandRepository.save(Demand.builder()
                        .demandeDate(new Date())
                        .status(Status.Pending)
                        .subject(subject)
                        .student(user)
                        .resume(resume)
                        .motivationLetter(letter)
                        .build());
            }

    @Override
    public Files downloadResume(Long id) {
        Demand demand = demandRepository.findDemandByDemandId(id);
        if (demand == null || demand.getResume().getData() == null) {
            throw new ApiRequestException("Resume not found for demand " + id);
        }
        return demand.getResume();
    }

    @Override
    public Files downloadLetter(Long id) {
        Demand demand = demandRepository.findDemandByDemandId(id);
        if (demand == null || demand.getMotivationLetter().getData() == null) {
            throw new ApiRequestException("Resume not found for demand " + id);
        }
        return demand.getMotivationLetter();
    }

    @Override
    public DemandDtoResponse updateDemandState(Long id, Status newState) {
        Demand demand = demandRepository.findDemandByDemandId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        LocalDateTime currentDate = LocalDateTime.now();
        boolean started = false;
        boolean approved = false;
        if(newState.equals(Status.Approved))
        {
            String title = demand.getSubject().getTitle();
            Subject subject = demand.getSubject();
            AppUser teacher = subject.getTeacher();
            AppUser student = demand.getStudent();
            if (demand.getSubject().getInternshipType().equals(InternshipType.EndOfStudiesProject))
            {
                internshipRepository.save( Internship.builder()
                        .titre(title)
                        .state(presentationRequest.InProgress)
                        .dateDebut(currentDate)
                                        .student(student)
                                        .teacher(teacher)
                                .demand(demand)
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
                        .student(student)
                        .teacher(teacher)
                        .demand(demand)
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
                        .student(student)
                        .teacher(teacher)
                        .demand(demand)
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
    public List<DemandDtoResponse> getDemands(String email) {
        AppUser user = appUserRepository.findByEmail(email);
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        if((user instanceof Student)||(user instanceof Teacher))
        {
            List<Demand> demands = demandRepository.findDemandByStudent(user);
            List<DemandDtoResponse> demandDtoResponses = demands.stream()
                    .map(demand-> demandDtoConverter.convertToDto(demand))
                    .collect(Collectors.toList());
            return demandDtoResponses;
        }
        List<Demand> demands = demandRepository.findAll();
        List<DemandDtoResponse> demandDtoResponses = demands.stream()
                .map(demand-> demandDtoConverter.convertToDto(demand))
                .collect(Collectors.toList());
        return demandDtoResponses;
    }

    @Override
    public void deleteDemand(Long id) {
        Demand demand = demandRepository.findDemandByDemandId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        //
        Files resumeFile = filesRepository.findFileById(demand.getResume().getId());
        filesRepository.delete(resumeFile);
        //
        Files letterFile = filesRepository.findFileById(demand.getMotivationLetter().getId());
        filesRepository.delete(letterFile);
        //
        demandRepository.delete(demand);
    }

    @Override
    public DemandDtoResponse getDemandById(Long id) {
        Demand demand = demandRepository.findDemandByDemandId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        return demandDtoConverter.convertToDto(demand);
    }

    @Override
    public void updateDemand(Long demandId, Long subjectId, String email, MultipartFile cv, MultipartFile motivationLetter) throws IOException
    {

        Demand demand = demandRepository.findDemandByDemandId(demandId);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        if( subjectId !=null )
        {
            Subject subject = subjectRepository.findSubjectBySubjectId(subjectId);
            if (subject == null)
            {
                throw new ApiRequestException("Subject dose not exist in DB!!!");
            }
            demand.setSubject(subject);
        }
        if(email != null)
        {
            AppUser user = appUserRepository.findByEmail(email);
            if (user == null)
            {
                throw new ApiRequestException("User dose not exist in DB!!!");
            }
            demand.setStudent(user);
        }
        if(cv != null)
        {
            Files file = filesRepository.findFileById(demand.getResume().getId());
            //
            String resumeName = StringUtils.cleanPath(cv.getOriginalFilename());
            if (resumeName.contains("..")) {
                throw new ApiRequestException("resumeName contains invalid path sequence "
                        + resumeName);
            }
            file.setFileName(resumeName);
            file.setFileType(cv.getContentType());
            file.setData(cv.getBytes());
            filesRepository.save(file);
        }
        if( motivationLetter != null)
        {
            Files file = filesRepository.findFileById(demand.getMotivationLetter().getId());
            //
            String letterName = StringUtils.cleanPath(motivationLetter.getOriginalFilename());
            if (letterName.contains("..")) {
                throw new ApiRequestException("letterName contains invalid path sequence "
                        + letterName);
            }
            file.setFileName(letterName);
            file.setFileType(motivationLetter.getContentType());
            file.setData(motivationLetter.getBytes());
            filesRepository.save(file);
        }
        demandRepository.save(demand);
    }

}
