package com.telusko.SpringSecEx.controller;

import com.telusko.SpringSecEx.dto.AssignInspectorRequestDto;
import com.telusko.SpringSecEx.dto.AssignInspectorResponseDto;
import com.telusko.SpringSecEx.service.InspectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InspectionController {

    private final InspectionService inspectionService;

    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    @PutMapping("/assign/{ackNo}")
    public ResponseEntity<AssignInspectorResponseDto> assignInspector(
            @PathVariable Long ackNo,
            @RequestBody AssignInspectorRequestDto requestDto
    ) {
        AssignInspectorResponseDto response = inspectionService.assignInspector(ackNo, requestDto);
        return ResponseEntity.ok(response);
    }
}
