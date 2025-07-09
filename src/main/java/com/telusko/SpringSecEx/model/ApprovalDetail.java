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
@Table(name = "approvals")
public class ApprovalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long approvalId;

    @Column(nullable = false)
    private Long ackNo;

    @Column(nullable = false)
    private Integer approverId;

    private Boolean allYes;
    private String approverComments;
    private String approvalStatus; // Approved or Rejected
    private String approvalIn;
    private LocalDateTime approvedAt = LocalDateTime.now();
    private String tinAssigned;

}
