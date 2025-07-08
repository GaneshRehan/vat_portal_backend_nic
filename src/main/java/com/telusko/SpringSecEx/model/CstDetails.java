package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "cst_details")
public class CstDetails {

    @Id
    @Column(name = "ack_no")
    private String ackNo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ack_no")
    @JsonIgnore
    private Acknowledgement acknowledgement;

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
