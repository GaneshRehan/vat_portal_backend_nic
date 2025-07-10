package com.telusko.SpringSecEx.service;

import com.telusko.SpringSecEx.repo.AckForCheckerRepo;
import com.telusko.SpringSecEx.repo.AckForApproverRepo;
import com.telusko.SpringSecEx.dto.AckListItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AckListService {

    private final AckForCheckerRepo checkerRepo;
    private final AckForApproverRepo approverRepo;

    public AckListService(AckForCheckerRepo checkerRepo, AckForApproverRepo approverRepo) {
        this.checkerRepo = checkerRepo;
        this.approverRepo = approverRepo;
    }

    public List<AckListItem> listForChecker(Long checkerId) {
        return checkerRepo.findForChecker(checkerId);
    }

    public List<AckListItem> listForApprover(Long approverId) {
        return approverRepo.findForApprover(approverId);
    }
}
