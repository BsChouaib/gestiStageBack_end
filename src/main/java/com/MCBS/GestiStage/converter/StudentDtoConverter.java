package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import com.MCBS.GestiStage.models.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoConverter {
    private ModelMapper modelMapper;

    public StudentDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentDtoResponse convertToDto(Student student)
    {
        StudentDtoResponse studentDtoResponse = modelMapper.map(student,StudentDtoResponse.class);
        return studentDtoResponse;
    }

    public Student convertDtoToStudent(StudentDto studentDto)
    {
        Student student = modelMapper.map(studentDto,Student.class);
        return student;
    }
}
