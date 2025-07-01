package com.telusko.SpringSecEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.SpringSecEx.model.Users;
import com.telusko.SpringSecEx.repo.UserRepo;
import com.telusko.SpringSecEx.model.UserPrincipal;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user) {
        try {
            // Step 1: Authenticate
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
    
            // Step 2: Generate token using authenticated principal
            if (authentication.isAuthenticated()) {
                UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
                return jwtService.generateToken(principal.getUsername());
            }
    
        } catch (Exception e) {
            e.printStackTrace(); // Optional: log this properly
        }
    
        return "Authentication failed";
    }

}
   
