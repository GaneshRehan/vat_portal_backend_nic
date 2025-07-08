package com.telusko.SpringSecEx.model;

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
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String regId;

    @Column(nullable = false, unique = true)
    private String ackNo;

    @Column(nullable = false)
    private String enteredBy; // FK to users(user_id)

    @Column(nullable = false)
    private String registrationType;

    @Column(nullable = false)
    private String applicantName;

    private String tradeName;

    @Column(nullable = false)
    private String status = "Pending";

    private String tinNumber;

    private LocalDateTime createdAt = LocalDateTime.now();

}
