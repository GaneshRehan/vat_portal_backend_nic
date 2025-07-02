package com.telusko.SpringSecEx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringSecEx.dto.InspectorDto;
import com.telusko.SpringSecEx.model.Users;
import com.telusko.SpringSecEx.service.UserService;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")  // applies only to inspector endpoints
public class InspectorController {

    private final UserService userService;

    public InspectorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inspectors")
    public List<InspectorDto> getInspectors() {
        return userService.getAllInspectors();
    }
}
