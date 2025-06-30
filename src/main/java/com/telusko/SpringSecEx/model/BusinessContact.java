package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "business_contacts")
public class BusinessContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "tinNo is required")
    private String tinNo;

    @NotBlank(message = "applicantName is required")
    private String applicantName;

    private String slNo;

    @NotBlank(message = "personName is required")
    private String personName;

    private String fatherName;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "dob must be in YYYY-MM-DD format")
    private String dob;

    @NotBlank(message = "partnerType is required")
    private String partnerType;

    private String educationalQualification;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "panNo must be a valid PAN number")
    private String panNo;

    private String presentAddress;
    private String areaLocality;
    private String villageTownCity;
    private String permanentAddress;
    private String telNo;
    private String faxNo;

    @Email(message = "emailId must be a valid email address")
    private String emailId;

    private String extentOfInterest;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "dateOfEntry must be in YYYY-MM-DD format")
    private String dateOfEntry;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}|", message = "dateOfLeaving must be in YYYY-MM-DD format or empty")
    private String dateOfLeaving;

    private String votersId;
    private String residentialCertNo;
}