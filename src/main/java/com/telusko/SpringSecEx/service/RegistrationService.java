package com.telusko.SpringSecEx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.SpringSecEx.model.Registration;
import com.telusko.SpringSecEx.repo.RegistrationRepo;

@Service
public class RegistrationService {
    
    @Autowired
    private RegistrationRepo registrationRepo;

    public List<Registration> getRegistrationsByCheckerAndStatus(Long checkerId, String status) {
        return registrationRepo.findByEnteredBy_UserIdAndStatus(checkerId, status);
    }

    public List<Registration> getRegistrationsByStatus(String status) {
        return registrationRepo.findByStatus(status);
    }
}
