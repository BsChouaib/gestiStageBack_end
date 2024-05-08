package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Teacher findTeacherById(Long id);

    Teacher findTeacherByEmail(String email);


}
