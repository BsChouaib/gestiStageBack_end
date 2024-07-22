package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.converter.InternshipDtoConverter;
import com.MCBS.GestiStage.dtos.response.InternshipDtoResponse;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.*;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.FilesRepository;
import com.MCBS.GestiStage.repository.InternshipRepository;
import com.MCBS.GestiStage.repository.NotificationRepository;
import com.MCBS.GestiStage.service.InternshipService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InternshipServiceImpl implements InternshipService
{

    private final InternshipRepository internshipRepository;
    private final FilesRepository filesRepository;

    private final AppUserRepository appUserRepository;
    private final InternshipDtoConverter internshipDtoConverter;

    private final NotificationRepository notificationRepository;
    public InternshipServiceImpl(InternshipRepository internshipRepository, FilesRepository filesRepository, AppUserRepository appUserRepository, InternshipDtoConverter internshipDtoConverter, NotificationRepository notificationRepository) {
        this.internshipRepository = internshipRepository;
        this.filesRepository = filesRepository;
        this.appUserRepository = appUserRepository;
        this.internshipDtoConverter = internshipDtoConverter;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void updateInternship(Long id,
                                 LocalDateTime dateDebut,
                                 LocalDateTime dateFin,
                                 String titre,
                                 MultipartFile internshipReport,
                                 MultipartFile internshipJournal,
                                 presentationRequest state,
                                 String email) throws IOException
    {
        AppUser user = appUserRepository.findByEmail(email);
        if (user == null) {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        Internship internship = internshipRepository.findInternshipByInternshipId(id);
        if (user instanceof Admin) {
            if (dateDebut != null) {
                internship.setDateDebut(dateDebut);
            }
            if (dateFin != null) {
                internship.setDateFin(dateFin);
            }
            if (titre != null) {
                internship.setTitre(titre);
            }
            if (internshipReport != null) {
                Files file = filesRepository.findFileById(internship.getInternshipReport().getId());
                String internshipReportName = StringUtils.cleanPath(internshipReport.getOriginalFilename());
                if (internshipReportName.contains("..")) {
                    throw new ApiRequestException("resumeName contains invalid path sequence "
                            + internshipReportName);
                }
                file.setFileName(internshipReportName);
                file.setFileType(internshipReport.getContentType());
                file.setData(internshipReport.getBytes());
                internship.setInternshipReport(file);
                filesRepository.save(file);
            }
            if (internshipJournal != null) {
                Files file = filesRepository.findFileById(internship.getInternshipJournal().getId());
                String internshipJournalName = StringUtils.cleanPath(internshipJournal.getOriginalFilename());
                if (internshipJournalName.contains("..")) {
                    throw new ApiRequestException("resumeName contains invalid path sequence "
                            + internshipJournalName);
                }
                file.setFileName(internshipJournalName);
                file.setFileType(internshipReport.getContentType());
                file.setData(internshipReport.getBytes());
                internship.setInternshipJournal(file);
                filesRepository.save(file);
            }
            if (state != null) {
                internship.setState(state);
            }
        } else {
            internship.setState(state);
        }
        internshipRepository.save(internship);
    }

    @Override
    public List<InternshipDtoResponse> getAllInternship(String userEmail) {
        AppUser user = appUserRepository.findByEmail(userEmail);
        if (user == null) {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        if (user instanceof Admin) {
            List<Internship> internships = internshipRepository.findAll();
            List<InternshipDtoResponse> internshipDtoResponseList = internships.stream()
                    .map(subject -> internshipDtoConverter.convertToDto(subject))
                    .collect(Collectors.toList());
            return internshipDtoResponseList;
        }
        else if (user instanceof Student)
        {
            List<Internship> internships = internshipRepository.findInternshipByStudent(user);
            System.out.println(internships.toString());
            List<InternshipDtoResponse> internshipDtoResponseList = internships.stream()
                    .map(subject -> internshipDtoConverter.convertToDto(subject))
                    .collect(Collectors.toList());
            return internshipDtoResponseList;
        }
        else
        {
            List<Internship> internships = internshipRepository.findInternshipByTeacher(user);
            List<InternshipDtoResponse> internshipDtoResponseList = internships.stream()
                    .map(subject -> internshipDtoConverter.convertToDto(subject))
                    .collect(Collectors.toList());
            return internshipDtoResponseList;
        }
    }

    @Override
    public void validationInternship(Long id, presentationRequest newState)
    {
        Internship internship = internshipRepository.findInternshipByInternshipId(id);
        if (internship == null)
        {
            throw new ApiRequestException("Internship dose not exist in DB!!!");
        }
        internship.setState(newState);
        if(newState.equals(presentationRequest.Done))
        {
            String title = internship.getTitre();
            Long internshipId = internship.getInternshipId();
            String studentName = internship.getStudent().getFirstname();
            String teacherName = internship.getTeacher().getFirstname();
            notificationRepository.save( Notification.builder()
                    .InternshipId(internshipId)
                    .Title(title)
                    .TeacherName(teacherName)
                    .StudentName(studentName)
                            .teacherId(internship.getTeacher().getId())
                            .studentId(internship.getStudent().getId())
                    .build());
        }




    }

    @Override
    public void deleteInternship(Long id) {
        Internship internship = internshipRepository.findInternshipByInternshipId(id);
        if(internship==null)
        {
            throw new ApiRequestException("Internship dose not exist in DB!!!");
        }
        internshipRepository.delete(internship);
    }

    @Override
    public InternshipDtoResponse getInternshipById(Long id) {
        Internship internship = internshipRepository.findInternshipByInternshipId(id);
        if(internship==null)
        {
            throw new ApiRequestException("Internship dose not exist in DB!!!");
        }
        InternshipDtoResponse internshipDtoResponse = internshipDtoConverter.convertToDto(internship);
        return internshipDtoResponse;
    }

}
