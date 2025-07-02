package com.telusko.SpringSecEx.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.SpringSecEx.model.InspectionDetail;

public interface InspectionDetailRepo extends JpaRepository<InspectionDetail, Long> {
    Optional<InspectionDetail> findByRegId(Long regId);
    Optional<InspectionDetail> findByAckNo(String ackNo);

}
