package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.LoginRequest;
import com.MCBS.GestiStage.dtos.request.PresentationDtoRequest;
import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.HttpResponse;
import com.MCBS.GestiStage.service.PresentationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

import static org.springframework.http.HttpStatus.OK;

@Controller
@Api(tags = "Presentation end points", description = "Operations for the Presentation functionality")
@RequestMapping("/api/presentation")
public class PresentationController {



    private final PresentationService presentationService;

    public PresentationController(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @PostMapping("/create")
    //@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Create presentation authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> createPresentation(@RequestBody PresentationDtoRequest presentationDtoRequest)
    {
        presentationService.createPresentation(presentationDtoRequest);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("Presentation created successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }
}
