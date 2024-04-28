package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.LoginRequest;
import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.AuthResponseDTO;
import com.MCBS.GestiStage.dtos.response.RegisterResponseDTO;
import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Claim;
import com.MCBS.GestiStage.service.ClaimsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/calim")
public class ClaimController {

    private final ClaimsService claimService;

    public ClaimController(ClaimsService claimService) {
        this.claimService = claimService;
    }

    // Endpoint pour créer une réclamation
    @PostMapping
    public void createClaim(@RequestBody Claim claim) {
        claimService.createClaim(claim);
    }

    // Endpoint pour récupérer une réclamation par son ID
    @GetMapping("/{id}")
    public Claim getClaimById(@PathVariable int id) {
        return claimService.getClaimById(id);
    }

    // Endpoint pour mettre à jour une réclamation existante
    @PutMapping("/{id}")
    public void updateClaim(@PathVariable int id, @RequestBody Claim claim) {
        claim.setClaimId(id); // Assure que l'ID passé dans le corps de la demande est utilisé
        claimService.updateClaim(claim);
    }

    // Endpoint pour supprimer une réclamation
    @DeleteMapping("/{id}")
    public void deleteClaim(@PathVariable int id) {
        claimService.deleteClaim(id);
    }


}
