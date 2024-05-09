package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findStudentById(Long id);
    Student findStudentByEmail(String email);
}
