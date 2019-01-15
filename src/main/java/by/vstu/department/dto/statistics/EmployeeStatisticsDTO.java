package by.vstu.department.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeStatisticsDTO {

    private String tabel;

    private List<IncludeEmployeeParameterStats> params = new ArrayList<>();

    private Long fullSum = 0L;

    public EmployeeStatisticsDTO(String tabel) {
        this.tabel = tabel;
    }

    @Data
    @AllArgsConstructor
    private class IncludeEmployeeParameterStats {

        private Long typeId;

        private Long count;
    }

    public void addParam(Long typeId, Long count) {
        this.params.add(new IncludeEmployeeParameterStats(typeId, count));
        this.fullSum += count;
    }
}
