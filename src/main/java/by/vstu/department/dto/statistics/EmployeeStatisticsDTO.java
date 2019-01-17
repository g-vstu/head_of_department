package by.vstu.department.dto.statistics;

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

    @Data
    @AllArgsConstructor
    private class IncludeEmployeeParameterStats {

        private Long typeId;

        private Double sum;
    }

    public void addParam(Long typeId, Double sum) {
        this.params.add(new IncludeEmployeeParameterStats(typeId, sum));
        this.fullSum += sum;
    }
}
