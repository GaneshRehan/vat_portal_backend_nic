package com.telusko.SpringSecEx.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Data Transfer Object for manual_payments table
 */
@Data
public class PaymentDto {
    private Long paymentId;            // primary key
    private String tinNo;              // TIN (6-digit code)
    private String suspenseName;
    private String regType;
    private String paymentWith;
    private Long ackNo;
    private Double amountToBePaid;
    private String headOfAccount;
    private String paidTowards;
    private String mode;
    private String courtFeeNo;
    private LocalDateTime paymentDate;
    private String micrCode;
    private String remarks;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal penalty;
    private LocalDateTime createdAt;   // timestamp of creation
}
