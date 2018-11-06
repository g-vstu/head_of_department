package by.vstu.department.repository;

import by.vstu.department.model.Anketa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnketaRepository extends JpaRepository<Anketa, UUID> {

    Anketa findByTabel(String tabel);

    boolean existsByTabel(String tabel);
}
