package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "assignments")
public class Assignments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @Column(name = "ack_no", nullable = false)
    private Long ackNo;

    @Column(name = "assigned_by", nullable = false)
    private Long assignedBy;

    @Column(name = "checker_id", nullable = false)
    private Long checkerId;

    @Column(name = "assigned_at", columnDefinition = "TIMESTAMPTZ", nullable = false)
    private OffsetDateTime assignedAt = OffsetDateTime.now();
}
