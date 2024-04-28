package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.models.Claim;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.ClaimRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClaimsServiceImpl implements ClaimsService {


    private final ClaimRepository claimRepository;
    private final AppUserRepository appUserRepository;

    public ClaimsServiceImpl(ClaimRepository claimRepository, AppUserRepository appUserRepository) {
        this.claimRepository = claimRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void createClaim(Claim claim) {

    }

    @Override
    public Claim getClaimById(int id) {
        return null;
    }

    @Override
    public void updateClaim(Claim claim) {

    }

    @Override
    public void deleteClaim(int id) {

    }

    @Override
    public List<Claim> getAllClaimById(int id) {
        return null;
    }
}
