package by.vstu.department.dto;

import by.vstu.department.model.enums.AnketaParameterStatusType;
import lombok.Data;

@Data
public class EmployeeDTO {

    private String fio;

    private String position;

    private String tabel;

    private AnketaParameterStatusType status;

    public EmployeeDTO(ExportEmployeeDTO employeeDTO) {
        setFio(employeeDTO.getFio());
        setPosition(employeeDTO.getPosition());
        setTabel(employeeDTO.getTabel());
    }
}
