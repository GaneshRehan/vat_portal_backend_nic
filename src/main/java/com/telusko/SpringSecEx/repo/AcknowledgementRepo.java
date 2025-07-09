package com.telusko.SpringSecEx.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusko.SpringSecEx.dto.AcknowledgementDetailsDto;
import com.telusko.SpringSecEx.model.Acknowledgement;

@Repository
public interface AcknowledgementRepo extends JpaRepository<Acknowledgement, Long> {

    @Query("SELECT new com.telusko.SpringSecEx.dto.AcknowledgementDetailsDto(" +
           "a.ackNo, a.createdAt, a.ackDate, a.registrationType, a.applicantName, a.tradeName, " +
           "a.payMode, a.payNo, a.payDate, a.bankName, a.totalAmount, a.assignedTo.username) " +
           "FROM Acknowledgement a WHERE a.ackNo = :ackNo")
    AcknowledgementDetailsDto getAcknowledgementDetails(@Param("ackNo") Long ackNo);
}

