package com.vstu.department.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vstu.department.dto.EmployeeDTO;
import com.vstu.department.dto.statistics.EmployeeStatisticsDTO;
import com.vstu.department.dto.statistics.GeneralEmployeStatisticsDTO;
import com.vstu.department.dto.statistics.StatisticsDTO;
import com.vstu.department.model.EmployeeParameter;
import com.vstu.department.model.enums.ParameterGroupType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final EmployeeParameterService parameterService;

    private final StubService stubService;

    public StatisticsDTO getEmployeeParameterStats(String headTabel, ParameterGroupType type, String halfYear) {
        final List<String> halfYears = new ArrayList<>(Arrays.asList(halfYear.split(",")));
        final List<String> tabels = stubService.getEmployeeByHeadTabel(headTabel).stream().map(EmployeeDTO::getTabel)
                .collect(Collectors.toList());
        final Map<String, List<EmployeeParameter>> parameters = parameterService.getParametersBySearch(type, halfYears,
                tabels);

        final StatisticsDTO stats = new StatisticsDTO(type);
        List<EmployeeStatisticsDTO> emps = new ArrayList<>();

        for (Map.Entry<String, List<EmployeeParameter>> entry : parameters.entrySet()) {
            EmployeeStatisticsDTO empStats = new EmployeeStatisticsDTO(entry.getKey());
            entry.getValue().forEach(param -> empStats.addParam(param.getParameter().getId(),
                    param.getParameter().getName(), param.getCount() * param.getCoefficient()));
            emps.add(empStats);
        }
        emps.sort((a, b) -> b.getFullSum().compareTo(a.getFullSum()));
        stats.setUserStatistics(emps);
        return stats;
    }

    public List<GeneralEmployeStatisticsDTO> getEmployeeGeneralStatistics(String department, String halfYear) {
        final List<String> halfYears = new ArrayList<>(Arrays.asList(halfYear.split(",")));
        final List<String> tabels = stubService.getEmployeeByHeadTabel(department).stream().map(EmployeeDTO::getTabel)
                .collect(Collectors.toList());
        return parameterService.getGeneralStatistics(halfYears, tabels);
    }
}
