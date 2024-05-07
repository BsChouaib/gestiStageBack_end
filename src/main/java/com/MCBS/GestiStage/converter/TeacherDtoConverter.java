package com.MCBS.GestiStage.converter;

import com.MCBS.GestiStage.dtos.request.TeacherDto;
import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;
import com.MCBS.GestiStage.models.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class TeacherDtoConverter {

    private ModelMapper modelMapper;

    public TeacherDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TeacherDtoResponse convertToDto(Teacher teacher)
    {
        TeacherDtoResponse teacherDtoResponse = modelMapper.map(teacher, TeacherDtoResponse.class);
        return teacherDtoResponse;
    }

    public Teacher convertDtoToTeacher(TeacherDto teacherDto)
    {
        Teacher teacher = modelMapper.map(teacherDto,Teacher.class);
        return teacher;
    }
}
