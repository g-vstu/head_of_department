package com.vstu.department.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParameterDTO {

    Long id;

    String name;

    ParameterGroupDTO group;

    Double maxCoefficient;

    String tip;
}
