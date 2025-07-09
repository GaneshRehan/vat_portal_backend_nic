package com.telusko.SpringSecEx.controller;

import java.util.List;

import com.telusko.SpringSecEx.model.CstDetails;
import com.telusko.SpringSecEx.service.CstDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.telusko.SpringSecEx.dto.AcknowledgementDetailsDto;
import com.telusko.SpringSecEx.dto.ApprovalRequestDto;
import com.telusko.SpringSecEx.dto.InspectionRequestDto;
import com.telusko.SpringSecEx.dto.RegistrationDto;
import com.telusko.SpringSecEx.model.Registration;
import com.telusko.SpringSecEx.service.RegistrationService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CstDetailsService cstDetailsService;

    @GetMapping(params = { "assignedCheckerId", "status" })
    public List<Registration> getRegistrationsByCheckerAndStatus(
            @RequestParam Long assignedCheckerId,
            @RequestParam String status) {
        return registrationService.getRegistrationsByCheckerAndStatus(assignedCheckerId, status);
    }

    @GetMapping(params = "status")
    public List<Registration> getRegistrationsByStatus(@RequestParam String status) {
        return registrationService.getRegistrationsByStatus(status);
    }

    // 3.3 Submit Inspection (Checker)
    @PutMapping("/{ackNo}/inspection")
    public ResponseEntity<String> submitInspection(
            @PathVariable Long ackNo,
            @RequestBody InspectionRequestDto dto) {
        registrationService.submitInspection(ackNo, dto);
        return ResponseEntity.ok("Inspection submitted for ackNo: " + ackNo);
    }

    // 3.4 Get Full Registration + Inspection Details (Approver)
    @GetMapping("/{ackNo}")
    public ResponseEntity<RegistrationDto> getRegistrationWithInspection(
            @PathVariable Long ackNo) {
        RegistrationDto dto = registrationService.getRegistrationWithInspection(ackNo);
        return ResponseEntity.ok(dto);
    }

    // 3.5 Submit Final Approval
    @PutMapping("/{ackNo}/approval")
    public ResponseEntity<String> submitApproval(
            @PathVariable Long ackNo,
            @RequestBody ApprovalRequestDto dto) {
        String tin = registrationService.submitApproval(ackNo, dto);
        return ResponseEntity.ok("Approval processed. TIN: " + tin);
    }

    // CST details by ackNo
    @GetMapping("/{ackNo}/cst-details")
    public ResponseEntity<?> getCstDetails(@PathVariable Long ackNo) {
        try {
            CstDetails cstDetails = cstDetailsService.getCstDetailsByAckNo(ackNo);
            return ResponseEntity.ok(cstDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("CST details not found for AckNo: " + ackNo);
        }
    }
}
