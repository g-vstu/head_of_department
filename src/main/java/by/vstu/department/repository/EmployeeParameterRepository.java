package by.vstu.department.repository;

import by.vstu.department.model.EmployeeParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeParameterRepository extends JpaRepository<EmployeeParameter, Long> {

    void removeAllByAnketaId(Long id);
}
