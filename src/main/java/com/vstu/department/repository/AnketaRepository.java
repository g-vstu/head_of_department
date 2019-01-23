package com.vstu.department.repository;

import com.vstu.department.model.Anketa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnketaRepository extends JpaRepository<Anketa, Long> {

    Optional<Anketa> findByTabelAndHalfYear(String tabel, String halfYear);

    boolean existsByTabelAndHalfYear(String tabel, String halfYear);
}
