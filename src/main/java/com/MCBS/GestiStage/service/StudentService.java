package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import java.util.List;

public interface StudentService {
    List<StudentDtoResponse> getAllStudents();
    StudentDtoResponse getStudentById(Long id);

    void createStudent(StudentDto studentDto);
    void updateStudent(StudentDto studentDto, Long id);
    void deleteStudent(Long id);
}
