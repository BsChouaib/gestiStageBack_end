package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.ClaimDtoRequest;
import com.MCBS.GestiStage.dtos.response.ClaimDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.models.Claim;

import java.util.List;

public interface ClaimsService {
    void createClaim(ClaimDtoRequest claim, String email);

    ClaimDtoResponse getClaimById(Long id);

    void updateClaim(ClaimDtoRequest claim, Long id);

    void deleteClaim(Long id);
    List<ClaimDtoResponse> getAllClaims();
    ClaimDtoResponse updateClaimState(Long id, Status newState);
}
