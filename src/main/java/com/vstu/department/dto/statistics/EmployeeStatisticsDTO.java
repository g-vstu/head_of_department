package com.vstu.department.dto.statistics;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
public class EmployeeStatisticsDTO {

    private String tabel;

    private List<IncludeEmployeeParameterStats> params = new ArrayList<>();

    private Double fullSum = 0.0;

    public EmployeeStatisticsDTO(String tabel) {
        this.tabel = tabel;
    }

    public void addParam(Long parameterId, String parameterName, Double sum) {
        this.params.add(new IncludeEmployeeParameterStats(parameterId, parameterName, sum));
        if (parameterId > 0) {
            this.fullSum += sum;
        }
    }

    public EmployeeStatisticsDTO(String tabel, Double fullSum) {
        super();
        this.tabel = tabel;
        this.fullSum = fullSum;
    }

    @Data
    public static class IncludeEmployeeParameterStats {

        private Long parameterId;

        private String parameterName;

        private Double sum;

        public IncludeEmployeeParameterStats(Long parameterId, String parameterName, Double sum) {
            this.parameterId = parameterId;
            this.parameterName = parameterName;
            this.sum = sum;
        }

        public IncludeEmployeeParameterStats() {
        }
    }

    public EmployeeStatisticsDTO(String tabel, List<IncludeEmployeeParameterStats> params, Double fullSum) {
        this.tabel = tabel;
        this.params = params;
        this.fullSum = fullSum;
    }

    public EmployeeStatisticsDTO() {
    }
}
