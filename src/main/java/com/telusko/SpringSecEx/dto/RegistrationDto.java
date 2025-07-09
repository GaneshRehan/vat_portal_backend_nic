package com.telusko.SpringSecEx.dto;

import java.time.LocalDateTime;
import java.time.LocalDate;

import lombok.Data;

// API 3.4 Get Full Registration + Inspection
@Data
public class RegistrationDto {
    
    private Long ackNo;
    private String registrationType;
    private String applicantName;
    private String tradeName;
    private String status;
    private String tinNumber;
    private LocalDateTime createdAt;

    // You can nest inspection data here too
    private InspectionDto inspection;

    @Data
    public static class InspectionDto {
        private LocalDate dateOfVisit;
        private String natureOfBusiness;
        private LocalDate datePurchases;
        private Double amountPurchases;
        private LocalDate dateSales;
        private Double amountSales;
        private Double capitalInvested;
        private Double stockHeld;
        private String booksMaintained;
        private String verificationRemarks;
        private String otherInfo;
        private Double gpsLongitude;
        private Double gpsLatitude;
        private Double securityDeposit;
        private String inspectorComments;
        private String reportFileUrl;
        private String updatedStatus;
    }
}
