package com.vstu.department.service;

import com.vstu.department.dto.EmployeeDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StubService {

    private static final Map<String, List<EmployeeDTO>> employees;

    static {
        employees = new HashMap<>();
        List<EmployeeDTO> isapEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("Казаков В.Е.", "зав. каф.", "ИСАП", "isap1"),
                new EmployeeDTO("Бизюк А.Н.", "ст. преп.", "ИСАП", "isap2"),
                new EmployeeDTO("Корнеенко А.А.", "ст. преп.", "ИСААП", "isap3"),
                new EmployeeDTO("Шут В.Н.", "проф.", "ИСАП", "isap4"),
                new EmployeeDTO("Науменко А.М.", "проф.", "ИСАП", "isap5")
        ));

        List<EmployeeDTO> tompEmployees = new ArrayList<>(Arrays.asList(
                new EmployeeDTO("Иванов И.И.", "ст. преп.", "ТОМП", "tomp1"),
                new EmployeeDTO("Петров П.П.", "ст. преп.", "ТОМП", "tomp2"),
                new EmployeeDTO("Сергеев С.С.", "ст. преп.", "ТОМП", "tomp3"),
                new EmployeeDTO("Репнев В.В.", "ст. преп.", "ТОМП", "tomp4")
        ));

        employees.put("isap", isapEmployees);
        employees.put("tomp", tompEmployees);
    }

    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        if (tabel.startsWith("rector")) {
            return employees.values().stream().flatMap(List::stream).collect(Collectors.toList());
        } else {
            String department = tabel.substring(0, 4);
            return (CollectionUtils.isEmpty(employees.get(department))) ? new ArrayList<>() : employees.get(department);
        }
    }

}
