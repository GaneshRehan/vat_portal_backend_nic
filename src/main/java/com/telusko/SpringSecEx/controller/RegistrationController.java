package com.telusko.SpringSecEx.controller;

import com.telusko.SpringSecEx.model.CstDetails;
import com.telusko.SpringSecEx.service.CstDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private CstDetailsService cstDetailsService;

    @GetMapping("/{ackNo}/cst-details")
    public ResponseEntity<?> getCstDetails(@PathVariable String ackNo) {
        try {
            CstDetails cstDetails = cstDetailsService.getCstDetailsByAckNo(ackNo);
            return ResponseEntity.ok(cstDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("CST details not found for AckNo: " + ackNo);
        }
    }
}