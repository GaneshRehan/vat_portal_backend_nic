package com.telusko.SpringSecEx.service;

import com.telusko.SpringSecEx.model.CstDetails;
import com.telusko.SpringSecEx.repo.CstDetailsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CstDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CstDetailsService.class);

    @Autowired
    private CstDetailsRepo cstDetailsRepo;

    public CstDetails getCstDetailsByAckNo(String ackNo) {
        logger.info("Fetching CST details for ackNo: {}", ackNo);
        CstDetails cstDetails = cstDetailsRepo.findByAckNoIgnoreCase(ackNo)
                .orElseThrow(() -> {
                    logger.error("CST details not found for ackNo: {}", ackNo);
                    return new RuntimeException("CST details not found for AckNo: " + ackNo);
                });
        logger.info("Found CST details: {}", cstDetails);
        return cstDetails;
    }
}