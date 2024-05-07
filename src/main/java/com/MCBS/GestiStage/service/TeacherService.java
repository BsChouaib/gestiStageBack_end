package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;

import java.util.List;

public interface TeacherService {

    List<TeacherDtoResponse> getAllTeachers();
}
