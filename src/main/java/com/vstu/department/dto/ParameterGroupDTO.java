package com.vstu.department.dto;

import com.vstu.department.model.enums.ParameterGroupType;
import lombok.Data;

@Data
public class ParameterGroupDTO {

    private Long id;

    private String name;

    private ParameterGroupType groupType;
}
