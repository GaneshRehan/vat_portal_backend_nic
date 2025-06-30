package com.telusko.SpringSecEx.repo;

import com.telusko.SpringSecEx.model.CstDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CstDetailsRepo extends JpaRepository<CstDetails, String> {
    @Query("SELECT c FROM CstDetails c WHERE LOWER(c.ackNo) = LOWER(:ackNo)")
    Optional<CstDetails> findByAckNoIgnoreCase(@Param("ackNo") String ackNo);
}