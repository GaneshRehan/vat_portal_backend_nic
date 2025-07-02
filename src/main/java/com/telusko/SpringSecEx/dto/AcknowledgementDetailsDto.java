package com.telusko.SpringSecEx.dto;

import java.time.LocalDateTime;

public class AcknowledgementDetailsDto {
    private String ackNo;
    private LocalDateTime createdAt;
    private String registrationType;
    private String applicantName;
    private String tradeName;
    private String paymentMode;
    private String paymentWith;
    private String paidTowards;
    private String headOfAccount;
    private Double amountToBePaid;
    private String assignedTo;

    // Constructor used in JPQL query
    public AcknowledgementDetailsDto(String ackNo, LocalDateTime createdAt, String registrationType,
                                     String applicantName, String tradeName, String paymentMode,
                                     String paymentWith, String paidTowards, String headOfAccount,
                                     Double amountToBePaid, String assignedTo) {
        this.ackNo = ackNo;
        this.createdAt = createdAt;
        this.registrationType = registrationType;
        this.applicantName = applicantName;
        this.tradeName = tradeName;
        this.paymentMode = paymentMode;
        this.paymentWith = paymentWith;
        this.paidTowards = paidTowards;
        this.headOfAccount = headOfAccount;
        this.amountToBePaid = amountToBePaid;
        this.assignedTo = assignedTo;
    }

    // Getters (very important for JSON serialization)
    public String getAckNo() { return ackNo; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getRegistrationType() { return registrationType; }
    public String getApplicantName() { return applicantName; }
    public String getTradeName() { return tradeName; }
    public String getPaymentMode() { return paymentMode; }
    public String getPaymentWith() { return paymentWith; }
    public String getPaidTowards() { return paidTowards; }
    public String getHeadOfAccount() { return headOfAccount; }
    public Double getAmountToBePaid() { return amountToBePaid; }
    public String getAssignedTo() { return assignedTo; }
}
