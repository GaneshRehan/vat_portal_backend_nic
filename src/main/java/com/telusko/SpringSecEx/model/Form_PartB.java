package com.telusko.SpringSecEx.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for Part B – Addresses, Activities & VAT/COT Choices
 */

@Entity
@Table(name = "part_b")
@Data
@NoArgsConstructor
public class Form_PartB {
    
    @Id
    @Column(name = "ack_no", nullable = false)
    private Long ackNo;

    @Column(name = "tin_no", nullable = false)
    private String tinNo;

    // Residential Address
    @Column(name = "res_street")
    private String resStreet;

    @Column(name = "res_village_city")
    private String resVillageCity;

    @Column(name = "res_district")
    private String resDistrict;

    @Column(name = "res_state")
    private String resState;

    @Column(name = "res_pin_code")
    private String resPinCode;

    @Column(name = "res_country")
    private String resCountry;

    // Permanent Address
    @Column(name = "perm_street")
    private String permStreet;

    @Column(name = "perm_village_city")
    private String permVillageCity;

    @Column(name = "perm_district")
    private String permDistrict;

    @Column(name = "perm_state")
    private String permState;

    @Column(name = "perm_pin_code")
    private String permPinCode;

    @Column(name = "perm_country")
    private String permCountry;

    // Statutory Authority
    @Column(name = "stat_auth_name")
    private String statAuthName;

    @Column(name = "stat_auth_other")
    private String statAuthOther;

    // Economic Activity & Roles
    @Column(name = "econ_activity_code")
    private String econActivityCode;

    @Column(name = "is_manufacturer")
    private Boolean isManufacturer;

    @Column(name = "is_trader")
    private Boolean isTrader;

    @Column(name = "is_seller")
    private Boolean isSeller;

    @Column(name = "is_reseller")
    private Boolean isReseller;

    @Column(name = "is_importer")
    private Boolean isImporter;

    @Column(name = "is_exporter")
    private Boolean isExporter;

    // Commodity List
    @Column(name = "major_commodity")
    private String majorCommodity;

    @Column(name = "commodity_desc", columnDefinition = "TEXT")
    private String commodityDesc;

    // VAT/COT Registration Details
    @Column(name = "first_taxable_dt")
    private LocalDate firstTaxableDt;

    @Column(name = "register_for")
    private String registerFor;

    @Column(name = "cot_type")
    private String cotType;

    @Column(name = "est_turnover_12m", precision = 15, scale = 2)
    private BigDecimal estTurnover12m;

    @Column(name = "filing_frequency")
    private String filingFrequency;
}
