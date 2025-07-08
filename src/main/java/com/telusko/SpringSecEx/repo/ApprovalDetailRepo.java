package com.telusko.SpringSecEx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.SpringSecEx.model.ApprovalDetail;

public interface ApprovalDetailRepo extends JpaRepository<ApprovalDetail, String> {
    // No custom methods required for now
}
