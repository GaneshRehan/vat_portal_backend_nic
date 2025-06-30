package com.telusko.SpringSecEx.repo;

import com.telusko.SpringSecEx.model.BusinessContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusinessContactRepo extends JpaRepository<BusinessContact, Integer> {
    @Query("SELECT b FROM BusinessContact b WHERE LOWER(b.tinNo) = LOWER(:tinNo)")
    Optional<BusinessContact> findByTinNoIgnoreCase(@Param("tinNo") String tinNo);

    @Query("SELECT b FROM BusinessContact b WHERE LOWER(b.tinNo) = LOWER(:tinNo)")
    List<BusinessContact> findAllByTinNoIgnoreCase(@Param("tinNo") String tinNo);

    boolean existsByTinNoAndSlNo(@Param("tinNo") String tinNo, @Param("slNo") String slNo);
}