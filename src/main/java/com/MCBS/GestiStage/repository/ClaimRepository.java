package com.MCBS.GestiStage.repository;

import com.MCBS.GestiStage.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim,Long> {
    Claim findClaimByClaimId(Long id);

    List<Claim> findClaimByEmailSender(String email);
    @Query("SELECT c FROM Claim c JOIN FETCH c.user WHERE c.claimId = :id")
    Claim findClaimByClaimIdWithUser(@Param("id") Long id);

}
