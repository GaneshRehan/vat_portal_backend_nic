package com.telusko.SpringSecEx.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telusko.SpringSecEx.model.CstDetails;

public interface CstDetailsRepo extends JpaRepository<CstDetails, Long> {
    @Query("SELECT c FROM CstDetails c WHERE c.ackNo = :ackNo")
    Optional<CstDetails> findByAckNoIgnoreCase(@Param("ackNo") Long ackNo);
}