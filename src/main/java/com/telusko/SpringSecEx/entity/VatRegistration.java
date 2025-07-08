package com.example.vat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "vat_registrations")
public class VatRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vatRegId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    private String regdExcise;
    private String tradeLicenseBy;
    private LocalDate certIssueDate;
    private LocalDate certRenewDate;
    private String language;
    private String accountingYearFrom;
    private String accountingYearTo;

    @Column(precision = 12, scale = 2)
    private BigDecimal salesLastQuarter;

    @Column(precision = 12, scale = 2)
    private BigDecimal salesLastYear;

    private String licenseShopsNo;
    private LocalDate licenseShopsDate;
    private String foodLicenseNo;
    private LocalDate foodLicenseDate;
    private Boolean citizen;
    private String declarerName;
    private String designation;
    private String chairmanSignatory;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}