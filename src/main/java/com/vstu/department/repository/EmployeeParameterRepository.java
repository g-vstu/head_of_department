package com.vstu.department.repository;

import com.vstu.department.dto.statistics.GeneralEmployeStatisticsDTO;
import com.vstu.department.dto.statistics.GeneralEmployeeStatisticsParamsDTO;
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
            "new com.vstu.department.dto.statistics.GeneralEmployeStatisticsDTO(a.tabel, COALESCE(sum(ep.coefficient * ep.count),0)) " +
            "FROM Anketa a LEFT JOIN a.parameters ep " +
            "WHERE a.halfYear IN :halfYears AND a.tabel = :tabels " +
            "GROUP BY a.tabel")
    List<GeneralEmployeStatisticsDTO> getGeneralStatistics(@Param("halfYears") List<String> halfYears, @Param("tabels") List<String> tabels);

    @Query("SELECT COALESCE(sum(ep.coefficient * ep.count),0) " +
            "FROM EmployeeParameter ep " +
            "WHERE ep.anketa.halfYear IN :halfYears AND ep.anketa.tabel = :tabel ")
    double getGeneralStatisticsTemp(@Param("halfYears") List<String> halfYears, @Param("tabel") String tabel);
    
    @Query ("SELECT ep FROM EmployeeParameter ep LEFT JOIN ep.anketa a WHERE a.tabel = :tabel AND a.halfYear = :halfYears")
    List<EmployeeParameter> getEmployeeParameterByAnketaTabelAndAnketaHalfYear(@Param("tabel") String tabel, @Param("halfYears") List<String> halfYears);

    @Query("SELECT new com.vstu.department.dto.statistics.GeneralEmployeeStatisticsParamsDTO(ep.parameter.id, sum(ep.coefficient * ep.count) as rez) FROM EmployeeParameter ep LEFT JOIN ep.anketa a " +
            "WHERE a.tabel = :tabel AND a.halfYear = :halfYears GROUP BY ep.parameter.id")
    List<GeneralEmployeeStatisticsParamsDTO> getSummEmployeeParametersByAnketaTabelAndAnketaHalfYears(@Param("tabel") String tabel, @Param("halfYears") List<String> halfYears);

}
