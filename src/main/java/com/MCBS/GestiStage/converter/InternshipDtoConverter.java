package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.request.StudentDto;
import com.MCBS.GestiStage.dtos.response.InternshipDtoResponse;
import com.MCBS.GestiStage.dtos.response.StudentDtoResponse;
import com.MCBS.GestiStage.models.Internship;
import com.MCBS.GestiStage.models.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InternshipDtoConverter {

    private ModelMapper modelMapper;

    public InternshipDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InternshipDtoResponse convertToDto(Internship internship)
    {
        InternshipDtoResponse internshipDtoResponse = modelMapper.map(internship,InternshipDtoResponse.class);
        return internshipDtoResponse;
    }

//    public Internship convertDtoToInternship(StudentDto studentDto)
//    {
//        Student student = modelMapper.map(studentDto,Student.class);
//        return student;
//    }
}
