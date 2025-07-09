package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "acknowledgements")
public class Acknowledgement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ack_no")
    private Long ackNo;

    @Column(name = "ack_date", nullable = false)
    private LocalDate ackDate;

    @Column(name = "registration_type")
    private String registrationType;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "trade_name")
    private String tradeName;

    @Column(name = "pay_mode")
    private String payMode;

    @Column(name = "pay_no")
    private String payNo;

    @Column(name = "pay_date")
    private LocalDate payDate;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "total_amount", precision = 12, scale = 2)
    private BigDecimal totalAmount;

    // Foreign key to users table
    @ManyToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "user_id")
    private Users assignedTo;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
