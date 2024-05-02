package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.ClaimDtoRequest;
import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.ClaimDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.models.Claim;
import com.MCBS.GestiStage.service.ClaimsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "Claim end points", description = "Operations for the claim functionality")
@RequestMapping("/api/claim")
public class ClaimController {

    private final ClaimsService claimService;

    public ClaimController(ClaimsService claimService) {
        this.claimService = claimService;
    }

    // Endpoint pour créer une réclamation
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT') or hasAuthority('SCOPE_TEACHER')")
    @ApiOperation("Create claim authorized by (student or teacher)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                                value = "Bearer access token",
                                required = true,
                                dataType = "string",
                                paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> createClaim(@RequestBody ClaimDtoRequest claim)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String useremail = authentication.getName();
        claimService.createClaim(claim,useremail);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Claim added successfully!!",
                                                                null);
        return ResponseEntity.ok(apiDtoResponse);
    }
    //
    @GetMapping("/{id}")
    @ApiOperation("get claim by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ClaimDtoResponse> getClaimById(@PathVariable Long id) {
        ClaimDtoResponse claim = claimService.getClaimById(id);
        return ResponseEntity.ok(claim);
    }
    //
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT') or hasAuthority('SCOPE_TEACHER')")
    @ApiOperation("Update claim authorized by (student or teacher)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> updateClaim(@PathVariable Long id, @RequestBody ClaimDtoRequest claim) {
        claimService.updateClaim(claim, id);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Claim updated successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

    // Endpoint pour supprimer une réclamation
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT') or hasAuthority('SCOPE_TEACHER')")
    @ApiOperation("Delete claim authorized by (student or teacher)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Claim deleted successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Get all claims authorized only by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> getAllClaims() {
        List<ClaimDtoResponse> claims = claimService.getAllClaims();
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("success",
                claims);
        return ResponseEntity.ok(apiDtoResponse);
    }
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Update status of a claim authorized only by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ClaimDtoResponse> approveClaims(@PathVariable Long id, @RequestParam Status newState)
    {
        ClaimDtoResponse claimDtoResponse = claimService.updateClaimState(id,newState);
        return ResponseEntity.ok(claimDtoResponse);
    }

}


