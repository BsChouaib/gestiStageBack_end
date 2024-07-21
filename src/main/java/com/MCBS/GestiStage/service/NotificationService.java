package com.MCBS.GestiStage.service;

import com.MCBS.GestiStage.dtos.response.TeacherDtoResponse;
import com.MCBS.GestiStage.models.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotification();
}
