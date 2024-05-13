package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PresentationRepository extends PagingAndSortingRepository<Presentation,Long> {
}
