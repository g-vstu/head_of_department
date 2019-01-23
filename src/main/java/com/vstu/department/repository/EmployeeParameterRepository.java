package com.vstu.department.repository;

import com.vstu.department.dto.statistics.GeneralEmployeStatisticsDTO;
import com.vstu.department.model.EmployeeParameter;
import com.vstu.department.model.enums.ParameterGroupType;
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

    @Query("SELECT " +
            "new com.vstu.department.dto.statistics.GeneralEmployeStatisticsDTO(ep.anketa.tabel, sum(ep.coefficient * ep.count)) " +
            "FROM EmployeeParameter ep " +
            "WHERE ep.anketa.halfYear IN :halfYears AND ep.anketa.tabel IN :tabels " +
            "GROUP BY ep.anketa.tabel")
    List<GeneralEmployeStatisticsDTO> getGeneralStatistics(@Param("halfYears") List<String> halfYears, @Param("tabels") List<String> tabels);
}
