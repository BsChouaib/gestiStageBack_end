package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.SubjectDtoRequest;
import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.service.SubjectService;
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
@Api(tags = "Subjects end points", description = "Operations for the Subjects functionality")
@RequestMapping("/api/subject")

public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }



    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Create subject authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> createSubject(@RequestBody SubjectDtoRequest subject)
    {
        subjectService.createSubject(subject);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Subject added successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Create subject authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> updateSubject(@PathVariable Long id, @RequestBody SubjectDtoRequest subject) {
        subjectService.updateSubject(subject,id);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("subject updated successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Create subject authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> deleteDelete(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Subject deleted successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

    @GetMapping("/{id}")
    @ApiOperation("get subject by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<SubjectDtoResponse> getSubjectById(@PathVariable Long id) {
        SubjectDtoResponse subject = subjectService.getSubjectId(id);
        return ResponseEntity.ok(subject);
    }

    @GetMapping("/all")
    @ApiOperation("get subjects by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> getAllSubjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        List<SubjectDtoResponse> subjects = subjectService.getAllSubjects(userEmail);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("success",
                subjects);
        return ResponseEntity.ok(apiDtoResponse);
    }
}
