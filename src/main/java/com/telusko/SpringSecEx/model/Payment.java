package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "tin")
    private String tin;

    @Column(name = "suspense_name")
    private String suspenseName;

    @Column(name = "reg_type")
    private String regType;

    @Column(name = "payment_with")
    private String paymentWith;

    @Column(name = "ack_no")
    private String ackNo;

    @Column(name = "amount_to_be_paid")
    private Double amountToBePaid;

    @Column(name = "head_of_account")
    private String headOfAccount;

    @Column(name = "paid_towards")
    private String paidTowards;

    @Column(name = "mode")
    private String mode;
}
