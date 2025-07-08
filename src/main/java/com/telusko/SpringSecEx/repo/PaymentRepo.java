package com.telusko.SpringSecEx.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.telusko.SpringSecEx.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, String> {
    Payment findByAckNo(String ackNo);
}
