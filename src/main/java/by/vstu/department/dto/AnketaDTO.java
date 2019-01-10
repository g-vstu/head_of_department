package by.vstu.department.dto;

import by.vstu.department.model.enums.AnketaParameterStatusType;
import lombok.Data;

import java.util.Set;

@Data
public class AnketaDTO {

    private Long id;

    private String tabel;

    private AnketaParameterStatusType status;

    private Set<EmployeeParameterDTO> parameters;

    private String halfYear;
}
