package com.telusko.SpringSecEx.dto;

import java.time.LocalDate;

import lombok.Data;

// API 3.3 Submit Inspection
@Data
public class InspectionRequestDto {
    
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
