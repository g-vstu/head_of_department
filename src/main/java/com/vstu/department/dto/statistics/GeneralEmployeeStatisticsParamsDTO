package com.vstu.department.dto.statistics;

import lombok.Data;

@Data
public class GeneralEmployeeStatisticsParamsDTO {

    private Long paramId;
    private Double sum;

    public GeneralEmployeeStatisticsParamsDTO (Long paramId, Double sum){
        this.paramId = paramId;
        this.sum = (Math.ceil(sum * 100) / 100);
    }

}