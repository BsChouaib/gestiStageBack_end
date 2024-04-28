package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.models.Claim;

import java.util.List;

public interface ClaimsService {
    void createClaim(Claim claim);

    Claim getClaimById(int id);

    void updateClaim(Claim claim);

    void deleteClaim(int id);
    List<Claim> getAllClaimById(int id);
}
