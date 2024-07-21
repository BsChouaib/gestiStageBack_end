package com.MCBS.GestiStage.service.impl;

import com.MCBS.GestiStage.models.Notification;
import com.MCBS.GestiStage.repository.NotificationRepository;
import com.MCBS.GestiStage.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotification() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications;
    }
}
