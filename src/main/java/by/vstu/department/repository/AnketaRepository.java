package by.vstu.department.repository;

import by.vstu.department.model.Anketa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnketaRepository extends JpaRepository<Anketa, Long> {

    Optional<Anketa> findByTabel(String tabel);

    boolean existsByTabel(String tabel);
}
