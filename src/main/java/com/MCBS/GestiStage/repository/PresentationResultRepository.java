package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.PresentationResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationResultRepository extends JpaRepository<PresentationResult, Long> {
    PresentationResult findPresentationResultById(Long id);
}
