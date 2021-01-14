package com.vstu.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vstu.department.dto.EmployeeDTO;
import com.vstu.department.exception.BusinessEntityNotFoundException;
import com.vstu.department.model.Department;
import com.vstu.department.repository.DepartmentRepository;
import com.vstu.department.repository.EmployeeRepository;
import com.vstu.department.repository.FacultyRepository;
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

    FacultyRepository facultyRepository;

    EmployeeDTOMapper employeeMapper;

    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {

        if (tabel.startsWith("rector"))
            return employeeMapper.toDTOs(employeeRepository.findAll());
        else if (facultyRepository.existsByName(tabel)) {
            List<EmployeeDTO> employees = new ArrayList();
            List<Department> departments = facultyRepository.findByName(tabel)
                    .orElseThrow(() -> new BusinessEntityNotFoundException("Faculty not found")).getDepartments()
                    .stream().collect(Collectors.toList());
            departments.stream()
                    .forEach(department -> employees.addAll(employeeMapper.toDTOs(department.getEmployees())));
            return employees;
        } else {
            String department = tabel.replaceAll("\\d", "");
            return employeeMapper.toDTOs(departmentRepository.findByName(department)
                    .orElseThrow(() -> new BusinessEntityNotFoundException("Department not found")).getEmployees());
        }
    }

}
