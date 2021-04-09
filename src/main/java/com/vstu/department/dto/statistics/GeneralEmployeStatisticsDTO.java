package com.vstu.department.dto.statistics;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GeneralEmployeStatisticsDTO {

    private String tabel;

    private Double sum;

    private List<GeneralEmployeeStatisticsParamsDTO> params = new ArrayList<>();

    public  GeneralEmployeStatisticsDTO (String tabel, Double sum){
        this.tabel = tabel;
        this.sum = (Math.ceil(sum * 100) / 100);
    }

    public GeneralEmployeStatisticsDTO (String tabel, Double sum, List<GeneralEmployeeStatisticsParamsDTO> params){
        this.tabel = tabel;
        this.sum = (Math.ceil(sum * 100) / 100);
        this.params = params;
    }

}

