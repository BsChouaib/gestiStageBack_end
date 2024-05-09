package com.MCBS.GestiStage.Controllers;

import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.request.TeacherDto;
import com.MCBS.GestiStage.dtos.response.ApiDtoResponse;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;
import com.MCBS.GestiStage.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "Student end points", description = "Operations for the Student functionality")
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
    public ResponseEntity<ApiDtoResponse> getAllStudents() {
        List<StudentDtoResponse> students = studentService.getAllStudents();
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("all Teachers",
                students);
        return ResponseEntity.ok(apiDtoResponse);
    }


    @GetMapping("/{id}")
    @ApiOperation("get student by id authorized by All users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<StudentDtoResponse> getStudent(@PathVariable Long id) {
        StudentDtoResponse student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Create student authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> createStudent(@RequestBody StudentDto studentDto)
    {
        studentService.createStudent(studentDto);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Student added successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Update student authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id)
    {
        studentService.updateStudent(studentDto, id);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Student updated successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') ")
    @ApiOperation("Delete student authorized by admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization",
                    value = "Bearer access token",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    public ResponseEntity<ApiDtoResponse> deleteStudent(@PathVariable Long id)
    {
        studentService.deleteStudent(id);
        ApiDtoResponse apiDtoResponse = new ApiDtoResponse("Student deleted successfully!!",
                null);
        return ResponseEntity.ok(apiDtoResponse);
    }

}
