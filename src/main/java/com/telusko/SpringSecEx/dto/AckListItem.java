package com.telusko.SpringSecEx.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AckListItem {
    
    private Long ackNo;
    private String tinNumber;
    private LocalDate ackDate;
    private String applicantName;
    private String tradeName;
    private String status;
    // you can add more fields: totalAmount, payMode, etc.

    // Constructor used in JPQL query
    public AckListItem(Long ackNo, String tinNumber, LocalDate ackDate,
                       String applicantName, String tradeName, String status) {
        this.ackNo = ackNo;
        this.tinNumber = tinNumber;
        this.ackDate = ackDate;
        this.applicantName = applicantName;
        this.tradeName = tradeName;
        this.status = status;
    }
}
