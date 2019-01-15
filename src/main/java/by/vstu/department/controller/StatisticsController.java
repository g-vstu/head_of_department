package by.vstu.department.controller;

import by.vstu.department.dto.statistics.StatisticsDTO;
import by.vstu.department.model.enums.ParameterGroupType;
import by.vstu.department.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public StatisticsDTO get(@RequestParam String tabel, @RequestParam int type, @RequestParam String halfYear) {
        return statisticsService.getEmployeeParameterStats(tabel, ParameterGroupType.getByIndex(type), halfYear);
    }

}
