package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.ClaimDtoRequest;
import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.ClaimDtoResponse;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.service.DemandService;
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

import java.io.File;

@Controller
@Api(tags = "Demands end points", description = "Operations for the Internship demands functionality")
@RequestMapping("/api/demand")
public class DemandController {

    private  final DemandService demandService;

    public DemandController(DemandService demandService)
    {
        this.demandService = demandService;
    }
    @PostMapping("/create/{id}")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT')")
    @ApiOperation("Create demand authorized by student")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> createDemand(@RequestBody DemandDto demandDto)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String useremail = authentication.getName();
        demandService.createDemand(demandDto, useremail);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Demand added successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
//        return null;
    }


    @PutMapping("/{id}/status")
//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Update status of a claim authorized only by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<DemandDtoResponse> approveDemand(@PathVariable Long id, @RequestParam Status newState)
    {
        DemandDtoResponse demandDtoResponse = demandService.updateDemandState(id,newState);
        return ResponseEntity.ok(demandDtoResponse);
    }
}
