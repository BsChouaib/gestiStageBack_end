package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.HttpResponse;
import com.MCBS.GestiStage.dtos.response.InternshipDtoResponse;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.enumerations.Status;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.exceptions.ApiRequestException;
import com.MCBS.GestiStage.models.*;
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
import java.util.stream.Collectors;

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
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_TEACHER') or hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Internship Demand authorized by (teacher or admin)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> updateInternship(
                                                     @PathVariable Long id,
                                                     @RequestParam(value = "dateDebut", required = false) LocalDateTime dateDebut,
                                                     @RequestParam(value = "dateFin", required = false)LocalDateTime dateFin,
                                                     @RequestParam(value = "titre", required = false) String titre,
                                                     @RequestParam(value = "internshipReport", required = false)MultipartFile internshipReport,
                                                     @RequestParam(value = "internshipJournal", required = false) MultipartFile internshipJournal,
                                                     @RequestParam(value = "motivationLetter", required = false) presentationRequest newState
                                                        )
    {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
    @ApiOperation("get Internship by id authorized by All users")
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
    @ApiOperation("Update status of a demand Internship authorized by (teacher or admin)")
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
                        .message("Demand "+ newState)
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
}
