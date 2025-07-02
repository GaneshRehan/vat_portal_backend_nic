package com.telusko.SpringSecEx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignInspectorResponseDto {
    private String ackNo;
    private String assignedTo;
}
