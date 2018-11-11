package by.vstu.department.dto;

import by.vstu.department.model.enums.ParameterGroupType;
import lombok.Data;

@Data
public class ParameterGroupDTO {

    private Long id;

    private String name;

    private ParameterGroupType groupType;
}
