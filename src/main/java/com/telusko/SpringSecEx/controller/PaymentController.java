package com.telusko.SpringSecEx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringSecEx.dto.PaymentDto;
import com.telusko.SpringSecEx.model.Payment;
import com.telusko.SpringSecEx.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDto dto) {
        Payment created = service.createPayment(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> listPayments(
        @RequestParam(required = false) Long ackNo,
        @RequestParam(required = false) String tinNo
    ) {
        List<Payment> list;

        if (ackNo != null) {
            list = service.getByAckNo(ackNo);
        } else if (tinNo != null) {
            list = service.getByTinNo(tinNo);
        } else {
            list = service.getAllPayments();
        }

        return ResponseEntity.ok(list);
    }
}
