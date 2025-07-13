package com.telusko.SpringSecEx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.telusko.SpringSecEx.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findByAckNo(Long ackNo);
    List<Payment> findByTinNo(String tinNo);
}
