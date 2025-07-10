package com.telusko.SpringSecEx.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for Part A – Applicant & Business Basics + Contact
 */

@Entity
@Table(name = "part_a")
@Data
@NoArgsConstructor
public class Form_PartA {
    
    @Id
    @Column(name = "ack_no", nullable = false)
    private Long ackNo;

    @Column(name = "tin_no", nullable = false)
    private String tinNo;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "sex")
    private String sex;

    @Column(name = "trading_name")
    private String tradingName;

    @Column(name = "pan")
    private String pan;

    @Column(name = "premises_no")
    private String premisesNo;

    @Column(name = "area_locality")
    private String areaLocality;

    @Column(name = "village_city")
    private String villageCity;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "occupancy_status")
    private String occupancyStatus;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;
}
