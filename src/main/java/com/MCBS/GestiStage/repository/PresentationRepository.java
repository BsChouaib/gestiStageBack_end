package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation,Long> {
    Presentation findPresentationByPresentationId(Long id);
}
