package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Notification findNotificationById(Long id);
}
