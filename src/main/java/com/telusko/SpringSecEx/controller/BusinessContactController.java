package com.telusko.SpringSecEx.controller;

import com.telusko.SpringSecEx.model.BusinessContact;
import com.telusko.SpringSecEx.model.BusinessPartnerDTO;
import com.telusko.SpringSecEx.service.BusinessContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessContactController {

    @Autowired
    private BusinessContactService businessContactService;

    @GetMapping("/businessContact/{tinNo}")
    public ResponseEntity<List<BusinessContact>> getBusinessContact(@PathVariable String tinNo) {
        List<BusinessContact> contacts = businessContactService.getBusinessContactsByTinNo(tinNo);
        return ResponseEntity.ok(contacts);
    }

    @PostMapping("/businessContact")
    public ResponseEntity<?> createBusinessContact(@Valid @RequestBody BusinessContact contact) {
        try {
            BusinessContact createdContact = businessContactService.createBusinessContact(contact);
            return ResponseEntity.status(201).body(createdContact);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/businessPartners/{TinNo}")
    public ResponseEntity<List<BusinessPartnerDTO>> getBusinessPartners(@PathVariable String TinNo) {
        List<BusinessPartnerDTO> partners = businessContactService.getBusinessPartnersByTinNo(TinNo);
        return ResponseEntity.ok(partners);
    }
}