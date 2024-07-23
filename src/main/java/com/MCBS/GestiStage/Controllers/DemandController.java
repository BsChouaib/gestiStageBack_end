package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.response.DemandDtoResponse;
import com.MCBS.GestiStage.dtos.response.HttpResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.models.Files;
import com.MCBS.GestiStage.service.DemandService;
import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
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

    public DemandController(DemandService demandService)
    {
        this.demandService = demandService;
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT')")
    @ApiOperation("Create demand authorized by student")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> createDemand(@RequestParam("subjectId")Long subjectId,
                                                     @RequestParam(value = "resume", required = false)MultipartFile resume,
                                                     @RequestParam(value = "coverLetter", required = false)MultipartFile coverLetter)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        try{
            demandService.createDemand(subjectId, userEmail, resume, coverLetter);
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


    @GetMapping("/download/resume/{demandId}")
    @ApiOperation("download file")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<byte[]> downloadResume(@PathVariable Long demandId) {
        Files Resume = demandService.downloadResume(demandId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Resume.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + Resume.getFileName()
                                + "\"")
                .body(Resume.getData());
    }


    @GetMapping("/download/letter/{demandId}")
    @ApiOperation("download file")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<byte[]> downloadMotivationLetter(@PathVariable Long demandId) {
        Files letter = demandService.downloadLetter(demandId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(letter.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + letter.getFileName()
                                + "\"")
                .body(letter.getData());
    }


    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Update status of a demand authorized only by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> approveDemand(@PathVariable Long id, @RequestParam Status newState)
    {

        demandService.updateDemandState(id, newState);
        return ResponseEntity.ok().body(
            HttpResponse.builder()
                    .timeStamp(new Date().toString())
                    .message("Demand "+ newState)
                    .status(OK)
                    .statusCode(OK.value())
                    .build());
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


    // a revoir avec
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("get demand by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> getDemandById(@PathVariable Long id) {

        DemandDtoResponse demand = demandService.getDemandById(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .data(Map.of("demand",demand))
                        .message("Demand retried")
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
    public ResponseEntity<HttpResponse> updateDemand(   @PathVariable Long id,
                                                        @RequestParam(value = "subjectId", required = false)Long subjectId,
                                                        @RequestParam(value = "resume", required = false)MultipartFile resume,
                                                        @RequestParam(value = "coverLetter", required = false)MultipartFile motivationLetter
                                                    ) {
        try {
            demandService.updateDemand( id,
                                        subjectId,
                                        resume,
                                        motivationLetter
                                        );
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(new Date().toString())
                            .message("Demand updated successfully")
                            .status(OK)
                            .statusCode(OK.value())
                            .build());
        }
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

}
