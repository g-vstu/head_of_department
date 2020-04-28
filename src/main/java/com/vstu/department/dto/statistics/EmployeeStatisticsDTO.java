package com.vstu.department.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeStatisticsDTO {

    private String tabel;

    private List<IncludeEmployeeParameterStats> params = new ArrayList<>();

    private Double fullSum = 0.0;

    public EmployeeStatisticsDTO(String tabel) {
        this.tabel = tabel;
    }

    public void addParam(Long parameterId, String parameterName, Double sum) {
        this.params.add(new IncludeEmployeeParameterStats(parameterId, parameterName, sum));
        this.fullSum += sum;
    }

    @Data
    @AllArgsConstructor
    private class IncludeEmployeeParameterStats {

        private Long parameterId;

        private String parameterName;

        private Double sum;
    }
}
