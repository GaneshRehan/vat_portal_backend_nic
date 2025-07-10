package com.telusko.SpringSecEx.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.telusko.SpringSecEx.model.Form_PartA;

public interface Form_PartARepo extends JpaRepository<Form_PartA, Long> {
    Optional<Form_PartA> findByTinNo(String tinNo);
}
