package com.telusko.SpringSecEx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.telusko.SpringSecEx.dto.PaymentDto;
import com.telusko.SpringSecEx.model.Payment;
import com.telusko.SpringSecEx.repo.PaymentRepo;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo repository;

    public PaymentServiceImpl(PaymentRepo repository) {
        this.repository = repository;
    }

    @Override
    public Payment createPayment(PaymentDto dto) {
        Payment p = new Payment();
        // Map DTO fields to entity
        p.setTinNo(dto.getTinNo());
        p.setSuspenseName(dto.getSuspenseName());
        p.setRegType(dto.getRegType());
        p.setPaymentWith(dto.getPaymentWith());
        p.setAckNo(dto.getAckNo());
        p.setAmountToBePaid(dto.getAmountToBePaid().doubleValue());
        p.setHeadOfAccount(dto.getHeadOfAccount());
        p.setPaidTowards(dto.getPaidTowards());
        p.setMode(dto.getMode());
        p.setCourtFeeNo(dto.getCourtFeeNo());
        p.setPaymentDate(dto.getPaymentDate());
        p.setMicrCode(dto.getMicrCode());
        p.setRemarks(dto.getRemarks());
        p.setAmount(dto.getAmount());
        p.setInterest(dto.getInterest());
        p.setPenalty(dto.getPenalty());
        
        // createdAt is auto-populated via @CreationTimestamp
        return repository.save(p);
    }

    @Override
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    @Override
    public List<Payment> getByAckNo(Long ackNo) {
        return repository.findByAckNo(ackNo);
    }

    @Override
    public List<Payment> getByTinNo(String tinNo) {
        return repository.findByTinNo(tinNo);
    }
}
