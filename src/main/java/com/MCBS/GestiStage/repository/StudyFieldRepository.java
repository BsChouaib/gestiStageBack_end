package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.StudyField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyFieldRepository extends JpaRepository<StudyField,String> {
    StudyField findStudyFieldById(String id);
}
