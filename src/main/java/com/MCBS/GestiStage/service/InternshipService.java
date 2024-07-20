package com.MCBS.GestiStage.service;


import com.MCBS.GestiStage.dtos.response.InternshipDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.models.Files;
import com.MCBS.GestiStage.models.Internship;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface InternshipService {

    void updateInternship(Long id,
                          LocalDateTime dateDebut,
                          LocalDateTime  dateFin,
                          String titre,
                          MultipartFile internshipReport,
                          MultipartFile internshipJournal,
                          presentationRequest state,
                          String email
                          ) throws IOException;

    // get All
    List<InternshipDtoResponse> getAllInternship(String userEmail);

    void validationInternship(Long id, presentationRequest newState);

}
