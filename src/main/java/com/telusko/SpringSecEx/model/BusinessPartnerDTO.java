package com.telusko.SpringSecEx.model;

import lombok.Data;

@Data
public class BusinessPartnerDTO {
    private String id; // Maps to slNo
    private String name; // Maps to personName
    private String fatherName;
    private String street; // Maps to presentAddress
    private String area; // Maps to areaLocality
    private String place; // Maps to villageTownCity
    private String telephone; // Maps to telNo
    private String dateOfBirth; // Maps to dob
    private String dateOfEntry;
    private String dateOfLeaving;
    private String type; // Maps to partnerType
}