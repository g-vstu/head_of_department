package by.vstu.department.repository;

import by.vstu.department.model.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    Page<Parameter> findByGroupId(Long groupId, Pageable pageable);
}
