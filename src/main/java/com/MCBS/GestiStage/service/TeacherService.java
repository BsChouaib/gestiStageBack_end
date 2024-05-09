package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.request.TeacherDto;
import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;

import java.util.List;

public interface TeacherService {

    List<TeacherDtoResponse> getAllTeachers();
    TeacherDtoResponse getTeacherById(Long id);

    void createTeacher(TeacherDto teacherDto);
    void updateTeacher(TeacherDto teacherDto, Long id);
    void deleteTeacher(Long id);
}
