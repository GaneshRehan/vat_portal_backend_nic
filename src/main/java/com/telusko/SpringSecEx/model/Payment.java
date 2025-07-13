package com.telusko.SpringSecEx.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "manual_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "tin_no")
    private String tinNo;

    @Column(name = "suspense_name")
    private String suspenseName;

    @Column(name = "reg_type")
    private String regType;

    @Column(name = "payment_with")
    private String paymentWith;

    @Column(name = "ack_no")
    private Long ackNo;

    @Column(name = "amount_to_be_paid")
    private Double amountToBePaid;

    @Column(name = "head_of_account")
    private String headOfAccount;

    @Column(name = "paid_towards")
    private String paidTowards;

    @Column(name = "mode")
    private String mode;

    @Column(name = "court_fee_no", length = 50)
    private String courtFeeNo;

    @Column(name = "court_fee_date")
    private LocalDateTime paymentDate;

    @Column(length = 20)
    private String micrCode;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "amount", precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "interest", precision = 12, scale = 2)
    private BigDecimal interest;

    @Column(name = "penalty", precision = 12, scale = 2)
    private BigDecimal penalty;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
