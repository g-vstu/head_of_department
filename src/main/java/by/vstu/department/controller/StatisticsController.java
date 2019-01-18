package by.vstu.department.controller;

import by.vstu.department.dto.statistics.StatisticsDTO;
import by.vstu.department.model.enums.ParameterGroupType;
import by.vstu.department.service.StatisticsService;
import by.vstu.department.util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('DEP_HEAD')")
    public StatisticsDTO get(@RequestParam int type, @RequestParam String halfYear) {
        String tabelHead = (String) UtilService.getFieldFromAuthentificationDetails("tabel");
        return statisticsService.getEmployeeParameterStats(tabelHead, ParameterGroupType.getByIndex(type), halfYear);
    }

}
