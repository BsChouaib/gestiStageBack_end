package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Api(tags = "Presentation end points", description = "Operations for the Presentation functionality")
@RequestMapping("/api/presentation")
public class PresentationController {



    @PostMapping("/create/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Create demand authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> createPresentation(@PathVariable Long id, @RequestParam String subjectId, @RequestParam String cv)
    {

        return null;
    }
}
