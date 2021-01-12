package com.vstu.department.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vstu.department.dto.EmployeeDTO;
import com.vstu.department.exception.BusinessEntityNotFoundException;
import com.vstu.department.repository.DepartmentRepository;
import com.vstu.department.repository.EmployeeRepository;
import com.vstu.department.service.mapper.EmployeeDTOMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StubService {

    DepartmentRepository departmentRepository;

    EmployeeRepository employeeRepository;

    EmployeeDTOMapper employeeMapper;

    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        if (tabel.startsWith("rector")) {
            return employeeMapper.toDTOs(employeeRepository.findAll());
        } else {
            String department = tabel.replaceAll("\\d", "");
            return employeeMapper.toDTOs(departmentRepository.findByName(department)
                    .orElseThrow(() -> new BusinessEntityNotFoundException("Department not found")).getEmployees());
        }
    }

}
