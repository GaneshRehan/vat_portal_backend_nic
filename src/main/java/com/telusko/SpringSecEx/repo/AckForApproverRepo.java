package com.telusko.SpringSecEx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telusko.SpringSecEx.dto.AckListItem;
import com.telusko.SpringSecEx.model.Acknowledgement;

public interface AckForApproverRepo extends JpaRepository<Acknowledgement, Long> {

    @Query("""
      select new com.telusko.SpringSecEx.dto.AckListItem(
          ack.ackNo,
          reg.tinNumber,
          ack.ackDate,
          reg.applicantName,
          reg.tradeName,
          reg.status   
      )
      from Acknowledgement ack
      join Registration reg  on reg.ackNo = ack.ackNo
      join Assignments appr     on appr.ackNo = ack.ackNo
      where appr.assignedBy = :approverId
      order by appr.assignedAt desc
    """)

    List<AckListItem> findForApprover(@Param("approverId") Long approverId);
}
