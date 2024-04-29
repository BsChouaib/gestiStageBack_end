package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.models.Claim;
import com.MCBS.GestiStage.service.ClaimsService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
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
