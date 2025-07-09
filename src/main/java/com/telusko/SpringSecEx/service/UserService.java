package com.telusko.SpringSecEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.telusko.SpringSecEx.model.Users;
import com.telusko.SpringSecEx.repo.UserRepo;
import com.telusko.SpringSecEx.dto.InspectorDto;
import com.telusko.SpringSecEx.dto.LoginRequest;
import com.telusko.SpringSecEx.dto.LoginResponse;
import com.telusko.SpringSecEx.model.UserPrincipal;
import java.util.List;
import java.util.stream.Collectors;

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

    public LoginResponse verify(LoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            if (authentication.isAuthenticated()) {
                UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

                Users user = repo.findByUsername(principal.getUsername());
                if (user == null) {
                    throw new RuntimeException("User not found");
                }

                String token = jwtService.generateToken(principal.getUsername());

                return new LoginResponse(token, user.getRole(), user.getDesignation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InspectorDto> getAllInspectors() {
        List<Users> inspectors = repo.findByRoleIgnoreCase("INSPECTOR");
        return inspectors.stream()
                .map(user -> new InspectorDto(user.getUsername()))
                .collect(Collectors.toList());
    }

}
