package by.vstu.department.repository;

import by.vstu.department.model.Anketa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnketaRepository extends JpaRepository<Anketa, Long> {

    Anketa findByTabel(String tabel);

    boolean existsByTabel(String tabel);
}
