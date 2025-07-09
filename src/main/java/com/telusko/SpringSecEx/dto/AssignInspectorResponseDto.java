package com.telusko.SpringSecEx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignInspectorResponseDto {
    private Long ackNo;
    private String assignedTo;
}
