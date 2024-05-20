package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files,Long> {

    Files findFileById(long id);
}
