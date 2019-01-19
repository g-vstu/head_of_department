package by.vstu.department.repository;

import by.vstu.department.model.Parameter;
import by.vstu.department.model.enums.ParameterGroupType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    List<Parameter> findByGroupId(Long groupId);

    List<Parameter> findByGroupGroupType(ParameterGroupType groupType);
}
