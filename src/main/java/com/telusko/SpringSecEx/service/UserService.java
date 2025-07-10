package com.telusko.SpringSecEx.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.SpringSecEx.dto.InspectorDto;
import com.telusko.SpringSecEx.dto.LoginRequest;
import com.telusko.SpringSecEx.dto.LoginResponse;
import com.telusko.SpringSecEx.model.Users;
import com.telusko.SpringSecEx.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtService jwtService;

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public LoginResponse verify(LoginRequest req) {
        Users user = repo.findByUsername(req.getUsername());
        if (user == null) {
            System.out.println("→ User not found: " + req.getUsername());
            return null;
        }

        String storedHash = user.getPassword();
        String raw = req.getPassword();
        
        //System.out.println("   Raw password in hash:       '" + encoder.encode(raw) + "'");

        if (!encoder.matches(raw, storedHash)) {
            return null;
        }

        String token = jwtService.generateToken(user.getUsername());
        return new LoginResponse(token, user.getRole(), user.getDesignation(), user.getUserId());
    }

    public List<InspectorDto> getAllInspectors() {
        List<Users> inspectors = repo.findByRoleIgnoreCase("INSPECTOR");
        return inspectors.stream()
                .map(u -> new InspectorDto(u.getUsername(), u.getDesignation()))
                .collect(Collectors.toList());
    }
}