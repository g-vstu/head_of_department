package com.vstu.department.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.vstu.department.dto.EmployeeDTO;
import com.vstu.department.model.Employee;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeDTOMapper implements EntityToDTOMapper<EmployeeDTO, Employee, EmployeeDTO> {

    ModelMapper mapper = new ModelMapper();

    @Override
    public EmployeeDTO toDTO(Employee entity, Object... args) {
        EmployeeDTO dto = mapper.map(entity, EmployeeDTO.class);
        dto.setDepartment(entity.getDepartmentDiscription());
        return dto;
    }

    /**
     * TODO: fix it
     */
    @Override
    public Employee toEntity(EmployeeDTO dto, Object... args) {
        return null;
    }
}
