package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand, Long> {
}
