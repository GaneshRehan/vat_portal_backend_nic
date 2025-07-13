package com.telusko.SpringSecEx.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "business_partners")
public class BusinessPartners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private Long id; // Maps to partner_id (primary key)


    @Column(name = "name")
    private String name; // Maps to personName

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "street")
    private String street; // Maps to presentAddress

    @Column(name = "area") // same DB column
    private String area; // Maps to areaLocality

    @Column(name = "place") // same DB column
    private String place; // Maps to villageTownCity

    @Column(name = "contact_number")
    private String telephone; // Maps to telNo

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth; // Maps to dob (⚠️ inaccurate)

    @Transient
    private LocalDate dateOfEntry;

    @Transient
    private LocalDate dateOfLeaving;

    @Column(name = "type")
    private String type; // Maps to partnerType

}
