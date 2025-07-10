package com.telusko.SpringSecEx.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringSecEx.model.Form_PartA;
import com.telusko.SpringSecEx.model.Form_PartB;
import com.telusko.SpringSecEx.service.FormPartsService;

@RestController
@RequestMapping("/api/forms")
public class FormPartsController {
    
    private final FormPartsService formPartsService;

    public FormPartsController(FormPartsService formPartsService) {
        this.formPartsService = formPartsService;
    }

    // === fetch for Part A ===
    @GetMapping("/partA/ack/{ackNo}")
    public ResponseEntity<Form_PartA> getPartByAck(@PathVariable Long ackNo) {
        Optional<Form_PartA> partA = formPartsService.getPartAByAck(ackNo);
        return partA.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

     @GetMapping("/partA/tin/{tinNo}")
    public ResponseEntity<Form_PartA> getPartAByTin(@PathVariable String tinNo) {
        Optional<Form_PartA> partA = formPartsService.getPartAByTin(tinNo);
        return partA.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // === Fetch Part B ===
    @GetMapping("/partB/ack/{ackNo}")
    public ResponseEntity<Form_PartB> getPartBByAck(@PathVariable Long ackNo) {
        Optional<Form_PartB> partB = formPartsService.getPartBByAck(ackNo);
        return partB.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/partB/tin/{tinNo}")
    public ResponseEntity<Form_PartB> getPartBByTin(@PathVariable String tinNo) {
        Optional<Form_PartB> partB = formPartsService.getPartBByTin(tinNo);
        return partB.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
