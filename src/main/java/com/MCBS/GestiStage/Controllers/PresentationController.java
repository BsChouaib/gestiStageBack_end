package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.HttpResponse;
import com.MCBS.GestiStage.dtos.response.InternshipDtoResponse;
import com.MCBS.GestiStage.dtos.response.PresentationDtoResponse;
import com.MCBS.GestiStage.enumerations.presentationRequest;
import com.MCBS.GestiStage.service.PresentationService;
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

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@Api(tags = "Presentation end points", description = "Operations for the Presentation functionality")
@RequestMapping("/api/presentation")
public class PresentationController {



    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @PostMapping("/create/{notificationId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Create presentation authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> createPresentation(@PathVariable Long notificationId, @RequestBody PresentationDtoRequest presentationDtoRequest)
    {
        presentationService.createPresentation(notificationId,presentationDtoRequest);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("Presentation created successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Internship Demand authorized by (teacher or admin)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> updatePresentation(
            @PathVariable Long id,
            PresentationDtoRequest presentationDtoRequest
    )
    {
        try {
            presentationService.updatePresentation(presentationDtoRequest, id);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(new Date().toString())
                            .message("Presentation updated successfully")
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
    @ApiOperation("get Presentation by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> getAllPresentation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        List<PresentationDtoResponse> presentations = presentationService.getAllPresentations(userEmail);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("success",
                presentations);
        return ResponseEntity.ok(apiDtoResponse);
    }
}
