package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.ClaimDtoRequest;
import com.MCBS.GestiStage.dtos.request.TeacherDto;
import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.SubjectDtoResponse;
import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;
import com.MCBS.GestiStage.service.TeacherService;
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
@Api(tags = "Teacher end points", description = "Operations for the Teacher functionality")
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    @ApiOperation("get teachers all authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> getAllTeachers() {
        List<TeacherDtoResponse> subjects = teacherService.getAllTeachers();
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("all Teachers",
                subjects);
        return ResponseEntity.ok(apiDtoResponse);
    }
    //
    @GetMapping("/{id}")
    @ApiOperation("get teachers by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<TeacherDtoResponse> getTeacher(@PathVariable Long id) {
        TeacherDtoResponse teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }
    //
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Create teacher authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> createTeacher(@RequestBody TeacherDto teacher)
    {

        teacherService.createTeacher(teacher);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Teacher added successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }
    //
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Create teacher authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> updateTeacher(@RequestBody TeacherDto teacher)
    {

        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Teacher added successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Create teacher authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> deleteTeacher(@RequestBody TeacherDto teacher)
    {

        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Teacher added successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }
}
