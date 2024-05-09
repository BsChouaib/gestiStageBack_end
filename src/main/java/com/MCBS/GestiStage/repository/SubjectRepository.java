package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Subject findSubjectBySubjectId(Long id);
}
