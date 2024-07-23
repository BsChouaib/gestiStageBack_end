package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.ClaimDtoRequest;
import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.ClaimDtoResponse;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.models.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DemandService
{
    void createDemand(Long subjectId, String email, MultipartFile cv, MultipartFile motivationLetter) throws IOException;
    Files downloadResume(Long id);
    Files downloadLetter(Long id);
    void updateDemandState(Long id, Status newState);
    List<DemandDtoResponse> getDemands(String email);
    void deleteDemand(Long id);
    DemandDtoResponse getDemandById(Long id);
    void updateDemand(Long demandId, Long subjectId, MultipartFile cv, MultipartFile motivationLetter) throws IOException;

/*

    void updateDemand(DemandDto demandDto, Long id);

*/
}
