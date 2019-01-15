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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final EmployeeParameterService parameterService;

    private final StubService stubService;

    public StatisticsDTO getEmployeeParameterStats(String headTabel, ParameterGroupType type, String halfYear) {
        final List<String> halfYears = new ArrayList<>(Arrays.asList(halfYear.split(",")));
        final List<String> tabels = stubService.getEmployeeByHeadTabel(headTabel).stream().map(EmployeeDTO::getTabel).collect(Collectors.toList());
        final Map<String, List<EmployeeParameter>> parameters = parameterService.getParametersBySearch(type, halfYears, tabels);

        final StatisticsDTO stats = new StatisticsDTO(type);
        List<EmployeeStatisticsDTO> emps = new ArrayList<>();

        for (Map.Entry<String, List<EmployeeParameter>> entry : parameters.entrySet()) {
            EmployeeStatisticsDTO empStats = new EmployeeStatisticsDTO(entry.getKey());
            entry.getValue().forEach(param -> empStats.addParam(param.getParameter().getId(), param.getCount()));
            emps.add(empStats);
        }
        stats.setUserStatistics(emps);
        return stats;
    }
}
