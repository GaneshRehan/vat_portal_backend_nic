package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "business_partners")
public class BusinessPartners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private Long id; // Maps to partner_id (primary key)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ack_no")
    private Acknowledgement acknowledgement; // Foreign key to acknowledgements table

    @Column(name = "name")
    private String name; // Maps to personName

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "address")
    private String street; // Maps to presentAddress

    @Column(name = "address") // same DB column
    private String area; // Maps to areaLocality

    @Column(name = "address") // same DB column
    private String place; // Maps to villageTownCity

    @Column(name = "contact_number")
    private String telephone; // Maps to telNo

    @Column(name = "aadhaar_number")
    private String dateOfBirth; // Maps to dob (⚠️ inaccurate)

    @Transient
    private String dateOfEntry;

    @Transient
    private String dateOfLeaving;

    @Column(name = "designation")
    private String type; // Maps to partnerType

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
