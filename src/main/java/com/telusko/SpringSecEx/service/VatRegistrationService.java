package com.example.vat.service;

import com.example.vat.entity.VatRegistration;
import com.example.vat.exception.ResourceNotFoundException;
import com.example.vat.repository.VatRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VatRegistrationService {

    private final VatRegistrationRepository vatRegistrationRepository;

    public VatRegistration getVatRegistrationByUserId(Integer userId) {
        return vatRegistrationRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("VAT Registration not found for user ID: " + userId));
    }
}