package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.StudyFieldDto;
import com.MCBS.GestiStage.dtos.response.HttpResponse;
import com.MCBS.GestiStage.dtos.response.StudyFieldDtoResponse;
import com.MCBS.GestiStage.service.StudyFieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Controller
@Api(tags = "StudyField end points", description = "Operations for the StudyField functionality")
@RequestMapping("/api/StudyField")
public class StudyFieldController {

    private final StudyFieldService studyFieldService;

    public StudyFieldController(StudyFieldService studyFieldService) {
        this.studyFieldService = studyFieldService;
    }

    @GetMapping("/all")
    @ApiOperation("get students all authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> getAllStudyField() {
        List<StudyFieldDtoResponse> allStudents = studyFieldService.getAllStudyFields();
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .data(Map.of("StudyField",allStudents))
                        .message("StudyFields retried")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Get StudyField authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> getStudyFieldById(@PathVariable String id) {
        StudyFieldDtoResponse studyFieldDtoResponse = studyFieldService.getStudyFieldById(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .data(Map.of("StudyField",studyFieldDtoResponse))
                        .message("StudyField retried")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Create StudyField authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> createStudyField(@RequestBody StudyFieldDto studentDto)
    {
        studyFieldService.addNewStudyField(studentDto);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("StudyField created successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Update StudyField authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> updateStudyField(@PathVariable String id, @RequestBody StudyFieldDto studyFieldDto) {
        studyFieldService.updateStudyField(studyFieldDto, id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("StudyField updated successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiOperation("Delete StudyField authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<HttpResponse> deleteStudyField(@PathVariable String id) {
        studyFieldService.deleteStudyField(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(new Date().toString())
                        .message("StudyField deleted successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
