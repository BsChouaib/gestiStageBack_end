package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.Admin;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Files;
import com.MCBS.GestiStage.models.Internship;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.FilesRepository;
import com.MCBS.GestiStage.repository.InternshipRepository;
import com.MCBS.GestiStage.service.InternshipService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
@Service
@Transactional
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final FilesRepository filesRepository;

    private final AppUserRepository appUserRepository;

    public InternshipServiceImpl(InternshipRepository internshipRepository, FilesRepository filesRepository, AppUserRepository appUserRepository) {
        this.internshipRepository = internshipRepository;
        this.filesRepository = filesRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void updateInternship(Long id,
                                 LocalDateTime dateDebut,
                                 LocalDateTime dateFin,
                                 String titre,
                                 MultipartFile internshipReport,
                                 MultipartFile internshipJournal,
                                 presentationRequest state,
                                 String email) throws IOException {

        AppUser user = appUserRepository.findByEmail(email);
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        Internship internship = internshipRepository.findInternshipByInternshipId(id);
        if(user instanceof Admin)
        {
            if(dateDebut!=null)
            {
                internship.setDateDebut(dateDebut);
            }
            if(dateFin!=null)
            {
                internship.setDateFin(dateFin);
            }
            if(titre!=null)
            {
                internship.setTitre(titre);
            }
            if(internshipReport!=null)
            {
                Files file = filesRepository.findFileById(internship.getInternshipReport().getId());
                String internshipReportName = StringUtils.cleanPath(internshipReport.getOriginalFilename());
                if (internshipReportName.contains(".."))
                {
                    throw new ApiRequestException("resumeName contains invalid path sequence "
                                + internshipReportName);
                }
                file.setFileName(internshipReportName);
                file.setFileType(internshipReport.getContentType());
                file.setData(internshipReport.getBytes());
                internship.setInternshipReport(file);
                filesRepository.save(file);
            }
            if(internshipJournal!=null)
            {
                Files file = filesRepository.findFileById(internship.getInternshipJournal().getId());
                String internshipJournalName = StringUtils.cleanPath(internshipJournal.getOriginalFilename());
                if (internshipJournalName.contains(".."))
                {
                    throw new ApiRequestException("resumeName contains invalid path sequence "
                            + internshipJournalName);
                }
                file.setFileName(internshipJournalName);
                file.setFileType(internshipReport.getContentType());
                file.setData(internshipReport.getBytes());
                internship.setInternshipJournal(file);
                filesRepository.save(file);
            }
            if(state!=null)
            {
                internship.setState(state);
            }
        }
        else
        {
            internship.setState(state);
        }
        internshipRepository.save(internship);
    }
}
