package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "business_contacts")
public class BusinessContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tin_no", nullable = false)
    private String tinNo;

    @Column(name = "sl_no")
    private String slNo;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Column(name = "partner_type")
    private String partnerType;

    @Column(name = "educational_qualification")
    private String educationalQualification;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "present_address", columnDefinition = "TEXT")
    private String presentAddress;

    @Column(name = "area_locality")
    private String areaLocality;

    @Column(name = "village_town_city")
    private String villageTownCity;

    @Column(name = "permanent_address", columnDefinition = "TEXT")
    private String permanentAddress;

    @Column(name = "tel_no")
    private String telNo;

    @Column(name = "fax_no")
    private String faxNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "extent_of_interest")
    private String extentOfInterest;

    @Column(name = "date_of_entry")
    private LocalDate dateOfEntry;

    @Column(name = "date_of_leaving")
    private LocalDate dateOfLeaving;

    @Column(name = "voters_id")
    private String votersId;

    @Column(name = "residential_cert_no")
    private String residentialCertNo;


}
