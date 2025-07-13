package com.telusko.SpringSecEx.service;

import java.util.List;

import com.telusko.SpringSecEx.dto.PaymentDto;
import com.telusko.SpringSecEx.model.Payment;

public interface PaymentService {
    Payment createPayment(PaymentDto dto);
    List<Payment> getAllPayments();
    List<Payment> getByAckNo(Long ackNo);
    List<Payment> getByTinNo(String tinNo);
}
