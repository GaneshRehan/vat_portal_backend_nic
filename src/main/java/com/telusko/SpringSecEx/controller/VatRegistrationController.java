package com.example.vat.controller;

import com.example.vat.entity.VatRegistration;
import com.example.vat.service.VatRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VatRegistrationController {

    private final VatRegistrationService vatRegistrationService;

    @GetMapping("/vat-registration/{userId}")
    public ResponseEntity<VatRegistration> getVatRegistration(@PathVariable Integer userId) {
        VatRegistration registration = vatRegistrationService.getVatRegistrationByUserId(userId);
        return ResponseEntity.ok(registration);
    }
}