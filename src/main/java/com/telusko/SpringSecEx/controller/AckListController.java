package com.telusko.SpringSecEx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringSecEx.dto.AckListItem;
import com.telusko.SpringSecEx.service.AckListService;

@RestController
@RequestMapping("/api/acks")
public class AckListController {
    
    @Autowired private AckListService service;

    @GetMapping("/checker/{userId}")
    public List<AckListItem> forChecker(@PathVariable Long userId) {
        return service.listForChecker(userId);
    }

    @GetMapping("/approver/{userId}")
    public List<AckListItem> forApprover(@PathVariable Long userId) {
        return service.listForApprover(userId);
    }
}
