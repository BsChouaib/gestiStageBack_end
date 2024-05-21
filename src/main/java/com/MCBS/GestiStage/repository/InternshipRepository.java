package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepository extends JpaRepository<Internship,Long> {

    Internship findInternshipByInternshipId(Long id);
}
