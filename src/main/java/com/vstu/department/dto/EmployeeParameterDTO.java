package com.vstu.department.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vstu.department.model.enums.AnketaParameterStatusType;

import lombok.Data;

@Data
public class EmployeeParameterDTO {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long parameterId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ParameterDTO parameter;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AnketaParameterStatusType status = AnketaParameterStatusType.NOT_FILLED;

    @NotNull
    private Double count;

    @NotNull
    private Double coefficient;
}
