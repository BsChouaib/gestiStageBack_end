package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation,Long> {
    Presentation findPresentationByPresentationId(Long id);
    List<Presentation> findPresentationByStudentId(Long id);

    List<Presentation> findPresentationByTeacherId(Long id);
}
