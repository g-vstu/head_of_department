package by.vstu.department.dto;

import lombok.Data;

@Data
public class ParameterDTO {

    private Long id;

    private String name;

    private ParameterGroupDTO group;

    private Double maxCoefficient;
}
