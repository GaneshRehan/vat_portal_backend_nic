package com.telusko.SpringSecEx.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inspection_details")
public class InspectionDetail {

    
    @Column(name = "ack_no")
    private String ackNo;
    
    @Column(name = "assigned_to")
    private String assignedTo;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String inspectionId;

    @Column(name = "reg_id", unique = true, nullable = false)
    private String regId;

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

    private LocalDateTime updatedAt = LocalDateTime.now();

}
