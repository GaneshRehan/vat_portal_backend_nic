package com.telusko.SpringSecEx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.SpringSecEx.model.Registration;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, Long> {
    
    // 3.1: For Checker Dashboard - Get registrations by assigned checker ID and pending status
    List<Registration> findByEnteredBy_UserIdAndStatus(
        Long userId, String status
    );

    // 3.2: For Approver Dashboard - Get registrations by status
    List<Registration> findByStatus(String status);
}
