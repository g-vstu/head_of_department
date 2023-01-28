package com.vstu.department.dto.statistics;

import com.vstu.department.model.enums.ParameterGroupType;
import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {

    private ParameterGroupType groupType;

    private List<EmployeeStatisticsDTO> userStatistics;

    public StatisticsDTO(ParameterGroupType type) {
        this.groupType = type;
    }

    public StatisticsDTO(ParameterGroupType groupType, List<EmployeeStatisticsDTO> userStatistics) {
        this.groupType = groupType;
        this.userStatistics = userStatistics;
    }

    public StatisticsDTO(List<EmployeeStatisticsDTO> userStatistics) {
        this.userStatistics = userStatistics;
    }

    public StatisticsDTO() {
    }
}
