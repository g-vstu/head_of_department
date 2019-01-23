package com.vstu.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private String fio;

    private String position;

    private String department;

    private String tabel;
}
