package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.converter.ClaimDtoConverter;
import com.MCBS.GestiStage.dtos.request.ClaimDtoRequest;
import com.MCBS.GestiStage.dtos.response.ClaimDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Claim;
import com.MCBS.GestiStage.repository.AppUserRepository;
import com.MCBS.GestiStage.repository.ClaimRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClaimsServiceImpl implements ClaimsService {


    private final ClaimRepository claimRepository;
    private final AppUserRepository appUserRepository;

    private  final ClaimDtoConverter claimDtoConverter;

    public ClaimsServiceImpl(ClaimRepository claimRepository, AppUserRepository appUserRepository, ClaimDtoConverter claimDtoConverter) {
        this.claimRepository = claimRepository;
        this.appUserRepository = appUserRepository;
        this.claimDtoConverter = claimDtoConverter;
    }

    @Override
    public void createClaim(ClaimDtoRequest claim, String email) {
        AppUser user = appUserRepository.findByEmail(email);
        if (user == null)
        {
            throw new ApiRequestException("User dose not exist in DB!!!");
        }
        try
        {
            claimRepository.save(   Claim.builder()
                                    .claimDate(new Date())
                                    .Statut(Status.Pending)
                                    .Description(claim.description())
                                    .user(user)
                                    .emailSender(email)
                                    .build());

        }
        catch (Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Override
    public ClaimDtoResponse getClaimById(Long id) {
        Claim claim = claimRepository.findClaimByClaimId(id);
        if( (claim == null))
        {
            throw new ApiRequestException("Claim not exist in DB!!!");
        }
       return claimDtoConverter.convertToDto(claim);
    }

    @Override
    public void updateClaim(ClaimDtoRequest claim, Long id) {
        Claim oldClaim = claimRepository.findClaimByClaimId(id);
        if (oldClaim == null)
        {
            throw new ApiRequestException("Claim dose not exist in DB!!!");
        }
        oldClaim.setDescription(claim.description());
        claimRepository.save(oldClaim);

    }

    @Override
    public void deleteClaim(Long id) {
        Claim claim = claimRepository.findClaimByClaimId(id);
        if (claim == null)
        {
            throw new ApiRequestException("Claim dose not exist in DB!!!");
        }
        claimRepository.delete(claim);
    }

    @Override
    public List<ClaimDtoResponse> getAllClaims() {
        List<Claim> claims = claimRepository.findAll();
        List<ClaimDtoResponse> claimDtos = claims.stream()
                .map(claim -> claimDtoConverter.convertToDto(claim))
                .collect(Collectors.toList());
        return claimDtos;
    }

    @Override
    public ClaimDtoResponse updateClaimState(Long id,Status newState) {
        Claim claim = claimRepository.findClaimByClaimId(id);
        if (claim == null)
        {
            throw new ApiRequestException("Claim dose not exist in DB!!!");
        }
        claim.setStatut(newState);
        claimRepository.save(claim);
        return claimDtoConverter.convertToDto(claim);
    }
}
