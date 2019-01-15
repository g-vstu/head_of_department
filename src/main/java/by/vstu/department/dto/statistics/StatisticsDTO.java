package by.vstu.department.dto.statistics;

import by.vstu.department.model.enums.ParameterGroupType;
import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {

    private ParameterGroupType groupType;

    private List<EmployeeStatisticsDTO> userStatistics;
}
