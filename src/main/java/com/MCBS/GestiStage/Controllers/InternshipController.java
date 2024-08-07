package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.InternshipDto;
import com.MCBS.GestiStage.dtos.response.*;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.service.InternshipService;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@Api(tags = "Internship end points", description = "Operations for the Internship functionality")
@RequestMapping("/api/internship")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    // modification data ect
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('SCOPE_TEACHER') or hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Update internship authorized by (teacher or admin)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> updateInternship( @ModelAttribute InternshipDto formData )
    {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Long id = formData.getId();
            String dateDebut = formData.getDateDebut();
            String dateFin = formData.getDateFin();
            String titre = formData.getTitre();
            MultipartFile internshipReport = formData.getInternshipReport();
            MultipartFile internshipJournal = formData.getInternshipJournal();
            presentationRequest newState = formData.getNewState();

            String userEmail = authentication.getName();
            internshipService.updateInternship( id,
                    dateDebut,
                    dateFin,
                    titre,
                    internshipReport,
                    internshipJournal,
                    newState,
                    userEmail
            );
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(new Date().toString())
                            .message("Internship updated successfully")
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

    @GetMapping("/all")
    @ApiOperation("Get all internship by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> getAllInternship() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        List<InternshipDtoResponse> subjects = internshipService.getAllInternship(userEmail);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("success",
                subjects);
        return ResponseEntity.ok(apiDtoResponse);
    }


    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('SCOPE_TEACHER') or hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Validate internship authorized by (teacher or admin)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> validateInternship(@PathVariable Long id, @RequestParam presentationRequest newState)
    {

        internshipService.validationInternship(id, newState);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("Internship "+ newState)
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Delete internship authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> deleteInternship(@PathVariable Long id)
    {
        internshipService.deleteInternship(id);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Internship deleted successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_TEACHER') or hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Get internship by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> getInternshipById(@PathVariable Long id) {

        InternshipDtoResponse internshipDtoResponse = internshipService.getInternshipById(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .data(Map.of("internship",internshipDtoResponse))
                        .message("Internship retried")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
