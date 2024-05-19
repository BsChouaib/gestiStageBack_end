package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.DemandDto;
import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.dtos.response.HttpResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.Demand;
import com.MCBS.GestiStage.repository.DemandRepository;
import com.MCBS.GestiStage.service.DemandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@Api(tags = "Demands end points", description = "Operations for the Internship demands functionality")
@RequestMapping("/api/demand")
public class DemandController {

    private  final DemandService demandService;
    private  final DemandRepository demandRepository;

    public DemandController(DemandService demandService, DemandRepository demandRepository)
    {
        this.demandService = demandService;
        this.demandRepository = demandRepository;
    }
    @PostMapping("/create/")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT')")
    @ApiOperation("Create demand authorized by student")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> createDemand(@RequestParam("subjectId")Long subjectId, @RequestParam("cv")MultipartFile cv,@RequestParam("motivationLetter")MultipartFile motivationLetter)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String useremail = authentication.getName();
        try{
            demandService.createDemand(subjectId, useremail, cv, motivationLetter);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(new Date().toString())
                            .message("Demand created successfully")
                            .status(OK)
                            .statusCode(OK.value())
                            .build());}
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(
                    HttpResponse.builder()
                            .timeStamp(new Date().toString())
                            .message("BAD_REQUEST: " + e.getMessage())
                            .status(BAD_REQUEST)
                            .statusCode(BAD_REQUEST.value())
                            .build());
        }
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
    public ResponseEntity<DemandDtoResponse> approveDemand(@PathVariable Long id, @RequestParam Status newState)
    {
        DemandDtoResponse demandDtoResponse = demandService.updateDemandState(id,newState);
        return ResponseEntity.ok(demandDtoResponse);
    }

    @GetMapping("/all")
    @ApiOperation("get demands for the connected user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> getUserDemands() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        List<DemandDtoResponse> demands = demandService.getDemands(userEmail);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .data(Map.of("demands",demands))
                        .message("Demands retried")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT') or hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Delete demand authorized by (student or admin)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> deleteDemand(@PathVariable Long id) {
        demandService.deleteDemand(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("Demand deleted successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{id}")
    @ApiOperation("get demand by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> getDemandById(@PathVariable Long id) {
        DemandDtoResponse demandDtoResponse = demandService.getDemandById(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("Demand deleted successfully")
                        .data(Map.of("Demand", demandDtoResponse))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT') or hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Update Demand authorized by (student or admin)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> updateDemand(@PathVariable Long id, @RequestBody DemandDto demandDto) {
        demandService.updateDemand(demandDto, id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("Demand updated successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }



    @GetMapping("/download/{id}")
    @ApiOperation("download file")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<?> download(@PathVariable Long id) {
        byte[] imageData=demandService.downloadFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageData);
    }

    @GetMapping("/download/cv/{demandId}")
    @ApiOperation("download file")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<Resource> downloadCv(@PathVariable Long demandId) {
        Demand demand = demandRepository.findDemandByDemandtId(demandId);
        if (demand == null || demand.getCv() == null) {
            throw new ApiRequestException("Cv not found for demand " + demandId);
        }
        ByteArrayResource resource = new ByteArrayResource(demand.getCv());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv_" + demandId + ".pdf")
                .body(resource);
    }
}
