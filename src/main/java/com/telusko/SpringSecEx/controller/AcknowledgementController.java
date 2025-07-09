package com.telusko.SpringSecEx.controller;

import com.telusko.SpringSecEx.dto.AcknowledgementDetailsDto;
import com.telusko.SpringSecEx.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AcknowledgementController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/acknowledgements/{ackNo}")
    public ResponseEntity<AcknowledgementDetailsDto> getAcknowledgementDetails(@PathVariable Long ackNo) {
        AcknowledgementDetailsDto dto = registrationService.getAcknowledgementDetails(ackNo);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
