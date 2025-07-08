package com.example.vat.repository;

import com.example.vat.entity.VatRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VatRegistrationRepository extends JpaRepository<VatRegistration, Integer> {
    Optional<VatRegistration> findByUserId(Integer userId);
}