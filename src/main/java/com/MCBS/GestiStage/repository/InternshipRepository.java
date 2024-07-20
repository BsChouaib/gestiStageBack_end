package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship,Long> {

    Internship findInternshipByInternshipId(Long id);
    List<Internship> findInternshipByStudent(AppUser student);

    List<Internship> findInternshipByTeacher(AppUser Teacher);
}
