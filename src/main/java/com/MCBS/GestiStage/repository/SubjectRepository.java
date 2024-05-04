package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Subject findSubjectBySubjectId(Long id);
}
