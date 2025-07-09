package com.telusko.SpringSecEx.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class AcknowledgementDetailsDto {

    private Long ackNo;
    private OffsetDateTime createdAt;
    private LocalDate ackDate;
    private String registrationType;
    private String applicantName;
    private String tradeName;
    private String payMode;
    private String payNo;
    private LocalDate payDate;
    private String bankName;
    private BigDecimal totalAmount;
    private String assignedTo;

    // Constructor for JPQL projection
    public AcknowledgementDetailsDto(Long ackNo, OffsetDateTime createdAt, LocalDate ackDate,
                                     String registrationType, String applicantName, String tradeName,
                                     String payMode, String payNo, LocalDate payDate,
                                     String bankName, BigDecimal totalAmount, String assignedTo) {
        this.ackNo = ackNo;
        this.createdAt = createdAt;
        this.ackDate = ackDate;
        this.registrationType = registrationType;
        this.applicantName = applicantName;
        this.tradeName = tradeName;
        this.payMode = payMode;
        this.payNo = payNo;
        this.payDate = payDate;
        this.bankName = bankName;
        this.totalAmount = totalAmount;
        this.assignedTo = assignedTo;
    }

    // Getters (required for JSON serialization)
    public Long getAckNo() { return ackNo; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public LocalDate getAckDate() { return ackDate; }
    public String getRegistrationType() { return registrationType; }
    public String getApplicantName() { return applicantName; }
    public String getTradeName() { return tradeName; }
    public String getPayMode() { return payMode; }
    public String getPayNo() { return payNo; }
    public LocalDate getPayDate() { return payDate; }
    public String getBankName() { return bankName; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getAssignedTo() { return assignedTo; }
}
