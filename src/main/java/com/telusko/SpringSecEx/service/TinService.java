package com.telusko.SpringSecEx.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TinService {
  
    @PersistenceContext
    private EntityManager em;

    /**
     * Pulls the next value from the tin_seq DB sequence,
     * zero‑pads to 6 digits, prefixes "TIN", and returns.
     * Guaranteed unique (per sequence), no collisions.
     */
    @Transactional(readOnly = true)
    public String generateDailyResetTin() {
        // native query returns BigInteger or Long depending on driver
        Object result = em.createNativeQuery("SELECT nextval('tin_seq')").getSingleResult();
        long seq = (result instanceof Number) ? ((Number) result).longValue() : Long.parseLong(result.toString());

        // format to exactly six digits, e.g. "000001"
        String suffix = String.format("%06d", seq);
        return "TIN" + suffix;
    }
}
