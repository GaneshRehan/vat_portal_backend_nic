package com.telusko.SpringSecEx.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.SpringSecEx.model.InspectionDetail;

public interface InspectionDetailRepo extends JpaRepository<InspectionDetail, String> {
    Optional<InspectionDetail> findByRegId(String regId);
    Optional<InspectionDetail> findByAckNo(String ackNo);

}
