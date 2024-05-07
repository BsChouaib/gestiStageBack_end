package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;
import com.MCBS.GestiStage.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Api(tags = "Teacher end points", description = "Operations for the Teacher functionality")
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    @ApiOperation("get teachers all authorized by All admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> getAllSubjects() {
        List<TeacherDtoResponse> subjects = teacherService.getAllTeachers();
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("success",
                subjects);
        return ResponseEntity.ok(apiDtoResponse);
    }
}
