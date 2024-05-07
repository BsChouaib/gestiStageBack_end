package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT t FROM Teacher t")
    List<Teacher> findAllTeaches();
}
