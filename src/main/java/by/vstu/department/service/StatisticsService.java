package by.vstu.department.service;

import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.dto.statistics.EmployeeStatisticsDTO;
import by.vstu.department.dto.statistics.StatisticsDTO;
import by.vstu.department.model.EmployeeParameter;
import by.vstu.department.model.enums.ParameterGroupType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final EmployeeParameterService parameterService;

    private final StubService stubService;

    public StatisticsDTO getEmployeeParameterStats(String headTabel, ParameterGroupType type, String halfYear) {
        final List<String> halfYears = new ArrayList<>(Arrays.asList(halfYear.split(",")));
        final List<String> tabels = stubService.getEmployeeByHeadTabel(headTabel).stream().map(EmployeeDTO::getTabel).collect(Collectors.toList());
        final List<EmployeeParameter> parameters = parameterService.getParametersBySearch(type, halfYears, tabels);

        final StatisticsDTO stats = new StatisticsDTO();
        stats.setGroupType(type);
        List<EmployeeStatisticsDTO> emps = new ArrayList<>();
        for (EmployeeParameter empParam : parameters) {
            EmployeeStatisticsDTO empStats = new EmployeeStatisticsDTO(empParam.getAnketa().getTabel());
            empStats.addParam(empParam.getParameter().getId(), empParam.getCount());
            emps.add(empStats);
        }
        stats.setUserStatistics(emps);
        return stats;
    }
}
