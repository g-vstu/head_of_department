package by.vstu.department.repository;

import by.vstu.department.model.EmployeeParameter;
import by.vstu.department.model.enums.ParameterGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeParameterRepository extends JpaRepository<EmployeeParameter, Long> {

    void removeAllByAnketaId(Long id);

    @Query("SELECT ep FROM EmployeeParameter ep WHERE ep.parameter.group.groupType = :groupType " +
            "AND ep.anketa.halfYear IN :halfYears " +
            "AND ep.anketa.tabel = :tabel")
    List<EmployeeParameter> getParametersBySearch(@Param("groupType") ParameterGroupType type,
                                                  @Param("halfYears") List<String> halfYears,
                                                  @Param("tabel") String tabels);
}
