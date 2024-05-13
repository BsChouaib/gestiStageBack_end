package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.AppUser;
import com.MCBS.GestiStage.models.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandRepository extends JpaRepository<Demand, Long> {

    Demand findDemandByDemandtId(Long id);

    List<Demand> findDemandByAppUser(AppUser appUser);
}
