package by.vstu.department.repository;

import by.vstu.department.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParameterRepository extends JpaRepository<Parameter, UUID> {
}
