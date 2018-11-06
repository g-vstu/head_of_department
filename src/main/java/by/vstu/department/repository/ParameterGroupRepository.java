package by.vstu.department.repository;

import by.vstu.department.model.ParameterGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParameterGroupRepository extends JpaRepository<ParameterGroup, UUID> {
}
