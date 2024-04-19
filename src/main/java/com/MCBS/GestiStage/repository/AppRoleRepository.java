package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Integer> {

    AppRole findByRoleName(String roleName);

}
