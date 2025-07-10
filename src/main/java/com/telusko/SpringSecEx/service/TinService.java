package com.telusko.SpringSecEx.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class TinService {
    
    @PersistenceContext
    private final EntityManager em;

    private static final DateTimeFormatter DATE_FMT =
        DateTimeFormatter.ofPattern("yyMMdd");

    public TinService(EntityManager em) {
        this.em = em;
    }

    /**
     * Generate a TIN = YYMMDD + 4‑digit daily counter.
     * Counter is upserted & incremented atomically in a single SQL call.
     */
    @Transactional
    public String generateDailyResetTin() {
        String datePart = LocalDate.now().format(DATE_FMT);

        // 1) Insert new row with counter=1, or if exists, increment it
        Number result = (Number) em.createNativeQuery(
            "INSERT INTO tin_counter(date_key, counter) " +
            "VALUES (:dateKey, 1) " +
            "ON CONFLICT (date_key) DO UPDATE " +
            "  SET counter = tin_counter.counter + 1 " +
            "RETURNING counter")
                .setParameter("dateKey", datePart)
                .getSingleResult();

        long count = result.longValue();

        // 2) Zero‑pad to 5 digits (00001…99999)
        String cntPart = String.format("%05d", count);
        return datePart + "-" + cntPart;
    }
}
