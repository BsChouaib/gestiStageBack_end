package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.AppUser;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    AppUser findByEmail(String email);
    AppUser findById(Long id);
}
