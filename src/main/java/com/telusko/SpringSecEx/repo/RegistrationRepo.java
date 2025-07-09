package com.telusko.SpringSecEx.repo;

import com.telusko.SpringSecEx.dto.AcknowledgementDetailsDto;
import com.telusko.SpringSecEx.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, Long> {

    // 3.1: For Checker Dashboard - Get registrations by checker ID and status
    List<Registration> findByEnteredByAndStatus(Long userId, String status);

    // 3.2: For Approver Dashboard - Get registrations by status
    List<Registration> findByStatus(String status);

    // 3.4 - Get registration by ackNo
    Optional<Registration> findByAckNo(Long ackNo);

    
@Query("""
    SELECT new com.telusko.SpringSecEx.dto.AcknowledgementDetailsDto(
        r.ackNo,
        r.createdAt,
        r.registrationType,
        r.applicantName,
        r.tradeName,
        p.mode,
        p.paymentWith,
        p.suspenseName,
        p.headOfAccount,
        p.amountToBePaid,
        i.assignedTo
    )
    FROM Registration r
    LEFT JOIN com.telusko.SpringSecEx.model.Payment p ON r.ackNo = p.ackNo
    LEFT JOIN com.telusko.SpringSecEx.model.InspectionDetail i ON r.ackNo = i.ackNo
    WHERE r.ackNo = :ackNo
""")
AcknowledgementDetailsDto getAcknowledgementDetails(@Param("ackNo") Long ackNo);


}
