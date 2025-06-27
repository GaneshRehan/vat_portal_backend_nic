package com.telusko.SpringSecEx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringSecEx.model.Registration;
import com.telusko.SpringSecEx.service.RegistrationService;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

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
}
