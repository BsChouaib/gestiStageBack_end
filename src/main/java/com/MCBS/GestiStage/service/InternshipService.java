package com.MCBS.GestiStage.service;


import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.models.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

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
    // get by id

}
