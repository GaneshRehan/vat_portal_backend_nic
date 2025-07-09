package com.telusko.SpringSecEx.service;

import com.telusko.SpringSecEx.dto.AssignInspectorRequestDto;
import com.telusko.SpringSecEx.dto.AssignInspectorResponseDto;
import com.telusko.SpringSecEx.model.InspectionDetail;
import com.telusko.SpringSecEx.repo.InspectionDetailRepo;
import org.springframework.stereotype.Service;

@Service
public class InspectionService {

    private final InspectionDetailRepo inspectionDetailRepo;

    public InspectionService(InspectionDetailRepo inspectionDetailRepo) {
        this.inspectionDetailRepo = inspectionDetailRepo;
    }

    public AssignInspectorResponseDto assignInspector(Long ackNo, AssignInspectorRequestDto requestDto) {
        InspectionDetail detail = inspectionDetailRepo.findByAckNo(ackNo)
                .orElseThrow(() -> new RuntimeException("Acknowledgement number not found: " + ackNo));

        detail.setAssignedTo(requestDto.getInspectorName());
        inspectionDetailRepo.save(detail);

        return new AssignInspectorResponseDto(ackNo, requestDto.getInspectorName());
    }
}
