package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
