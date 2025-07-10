package com.telusko.SpringSecEx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telusko.SpringSecEx.dto.AckListItem;
import com.telusko.SpringSecEx.model.Acknowledgement;

public interface AckForCheckerRepo extends JpaRepository<Acknowledgement, Long> {
    
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
            join Registration reg       on reg.ackNo = ack.ackNo
            join Assignments assign      on assign.ackNo = ack.ackNo

            where assign.checkerId = :checkerId
            order by ack.ackDate desc
    """)

    List<AckListItem> findForChecker(@Param("checkerId") Long checkerId);
}
