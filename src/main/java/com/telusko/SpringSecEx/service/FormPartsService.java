package com.telusko.SpringSecEx.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.telusko.SpringSecEx.model.Form_PartA;
import com.telusko.SpringSecEx.model.Form_PartB;
import com.telusko.SpringSecEx.repo.Form_PartARepo;
import com.telusko.SpringSecEx.repo.Form_PartBRepo;

@Service
public class FormPartsService {
    
    private final Form_PartARepo partARepo;
    private final Form_PartBRepo partBRepo;

    public FormPartsService(Form_PartARepo partARepo, Form_PartBRepo partBRepo) {
        this.partARepo = partARepo;
        this.partBRepo = partBRepo;
    }

     // Part A by ackNo
    public Optional<Form_PartA> getPartAByAck(Long ackNo) {
        return partARepo.findById(ackNo);
    }

    // Part A by tinNo
    public Optional<Form_PartA> getPartAByTin(String tinNo) {
        return partARepo.findByTinNo(tinNo);
    }

    // Part B by ackNo
    public Optional<Form_PartB> getPartBByAck(Long ackNo) {
        return partBRepo.findById(ackNo);
    }

    // Part B by tinNo
    public Optional<Form_PartB> getPartBByTin(String tinNo) {
        return partBRepo.findByTinNo(tinNo);
    }
}
