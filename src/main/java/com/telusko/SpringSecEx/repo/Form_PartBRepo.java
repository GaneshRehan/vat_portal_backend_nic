package com.telusko.SpringSecEx.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.telusko.SpringSecEx.model.Form_PartB;

public interface Form_PartBRepo extends JpaRepository<Form_PartB, Long> {
    Optional<Form_PartB> findByTinNo(String tinNo);
}
