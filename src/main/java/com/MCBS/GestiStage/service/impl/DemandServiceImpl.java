package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.DemandDtoConverter;
import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import com.MCBS.GestiStage.enumerations.InternshipType;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.*;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.DemandRepository;
import com.MCBS.GestiStage.repository.InternshipRepository;
import com.MCBS.GestiStage.repository.SubjectRepository;
import com.MCBS.GestiStage.service.DemandService;
import com.MCBS.GestiStage.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
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
        if(cv!= null && motivationLetter!=null )
        {
            demandRepository.save(
                    Demand.builder()
                            .dateDemande(new Date())
                            .status(Status.Pending)
                            .subject(subject)
                            .appUser(user)
                            .cv(FileUtils.compressFile(cv.getBytes()))
                            .motivationletter(FileUtils.compressFile(motivationLetter.getBytes()))
                            .build()
            );
        }
        else if (cv == null && motivationLetter!=null)
        {
            demandRepository.save(
                    Demand.builder()
                            .dateDemande(new Date())
                            .status(Status.Pending)
                            .subject(subject)
                            .appUser(user)
                            .motivationletter(FileUtils.compressFile(motivationLetter.getBytes()))
                            .build()
            );
        }
        else if (cv != null && motivationLetter == null)
        {
            demandRepository.save(
                    Demand.builder()
                            .dateDemande(new Date())
                            .status(Status.Pending)
                            .subject(subject)
                            .appUser(user)
                            .cv(FileUtils.compressFile(cv.getBytes()))
                            .build()
            );
        }
        else
        {
            demandRepository.save(
                    Demand.builder()
                            .dateDemande(new Date())
                            .status(Status.Pending)
                            .subject(subject)
                            .appUser(user)
                            .build()
            );
        }

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
                       // .teacherName(teacherFirstName+" " +teacherLastName)
                        //.studentName(studentFirstName+" " +studentLastName)
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
                        //.teacherName(teacherFirstName+" " +teacherLastName)
                        //.studentName(studentFirstName+" " +studentLastName)
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
                        //.teacherName(teacherFirstName+" " +teacherLastName)
                        //.studentName(studentFirstName+" " +studentLastName)
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
            List<Demand> demands = demandRepository.findDemandByAppUser(user);
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

    // a revoir
    @Override
    public void updateDemand(DemandDto demandDto, Long id) {
        Demand demand = demandRepository.findDemandByDemandtId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        /*if((demandDto.subjectId()!=null))
        {
            Subject subject = subjectRepository.findSubjectBySubjectId(demandDto.subjectId());
            if (subject == null)
            {
                throw new ApiRequestException("Subject dose not exist in DB!!!");
            }
            demand.setSubject(subject);
        }
        if((demandDto.cv()!=null))
        {
            //demand.setCv(demandDto.cv());
        }*/
        demandRepository.save(demand);
    }

    @Override
    public void deleteDemand(Long id) {
        Demand demand = demandRepository.findDemandByDemandtId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        demandRepository.delete(demand);

    }

    @Override
    public DemandDtoResponse getDemandById(Long id) {
        Demand demand = demandRepository.findDemandByDemandtId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
/*
        byte[] image= FileUtils.decompressFile(demand.getCv());
        System.out.println(image);
        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path("1")
                .toUriString();
       System.out.println(downloadURl);
        String cvLink = null;
        String motivationLink = null;
*/
        return demandDtoConverter.convertToDto(demand);
    }





    public byte[] downloadFile(Long id){
     Demand demand = demandRepository.findDemandByDemandtId(id);
        if (demand == null)
        {
            throw new ApiRequestException("Demand dose not exist in DB!!!");
        }
        byte[] file= FileUtils.decompressFile(demand.getCv());
        return file;
    }




}
