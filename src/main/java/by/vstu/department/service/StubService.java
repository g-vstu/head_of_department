package by.vstu.department.service;

import by.vstu.department.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StubService {

    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        List<EmployeeDTO> employees = new ArrayList<>();
        switch (tabel) {
            case "isap1":
                employees.add(new EmployeeDTO("Казаков В.Е.", "зав. каф.", "ИСАП", "isap1"));
                employees.add(new EmployeeDTO("Бизюк А.Н.", "ст. преп.", "ИСАП", "isap2"));
                employees.add(new EmployeeDTO("Корнеенко А.А.", "ст. преп.", "ИСААП", "isap3"));
                employees.add(new EmployeeDTO("Шут В.Н.", "проф.", "ИСАП", "isap4"));
                employees.add(new EmployeeDTO("Науменко А.М.", "проф.", "ИСАП", "isap5"));
                break;
            case "tomp1":
                employees.add(new EmployeeDTO("Иванов И.И.", "ст. преп.", "ТОМП", "tomp1"));
                employees.add(new EmployeeDTO("Петров П.П.", "ст. преп.", "ТОМП", "tomp2"));
                employees.add(new EmployeeDTO("Сергеев С.С.", "ст. преп.", "ТОМП", "tomp3"));
                employees.add(new EmployeeDTO("Репнев В.В.", "ст. преп.", "ТОМП", "tomp4"));
                break;
            case "rector":
                employees.add(new EmployeeDTO("Казаков В.Е.", "зав. каф.", "ИСАП", "isap1"));
                employees.add(new EmployeeDTO("Бизюк А.Н.", "ст. преп.", "ИСАП", "isap2"));
                employees.add(new EmployeeDTO("Корнеенко А.А.", "ст. преп.", "ИСААП", "isap3"));
                employees.add(new EmployeeDTO("Шут В.Н.", "проф.", "ИСАП", "isap4"));
                employees.add(new EmployeeDTO("Науменко А.М.", "проф.", "ИСАП", "isap5"));
                employees.add(new EmployeeDTO("Иванов И.И.", "ст. преп.", "ТОМП", "tomp1"));
                employees.add(new EmployeeDTO("Петров П.П.", "ст. преп.", "ТОМП", "tomp2"));
                employees.add(new EmployeeDTO("Сергеев С.С.", "ст. преп.", "ТОМП", "tomp3"));
                employees.add(new EmployeeDTO("Репнев В.В.", "ст. преп.", "ТОМП", "tomp4"));
                break;
            default:
                employees.add(new EmployeeDTO("head department", "ГЛАВА", "ТЕСТ", "012351"));
                break;
        }
        return employees;
    }

}
