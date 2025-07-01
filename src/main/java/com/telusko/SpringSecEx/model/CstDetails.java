package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "acknowledgements")
public class CstDetails {
    @Id
    @NotBlank(message = "ackNo is required")
    @Column(name = "ack_no")
    private String ackNo;

    @NotBlank(message = "cstNumber is required")
    @Column(name = "cst_number")
    private String cstNumber;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "registrationDate must be in YYYY-MM-DD format")
    @Column(name = "registration_date")
    private String registrationDate;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "validity must be in YYYY-MM-DD format")
    private String validity;

    @NotBlank(message = "state is required")
    private String state;

    @NotBlank(message = "status is required")
    private String status;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;
}