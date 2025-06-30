package com.telusko.SpringSecEx.dto;

import lombok.Data;

// API 3.5 Submit Approval
@Data
public class ApprovalRequestDto {
    
    private Integer approverId;
    private Boolean allYes;
    private String approverComments;
    private String approvalStatus;
    private String approvalIn;
}
