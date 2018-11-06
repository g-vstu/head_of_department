package by.vstu.department.service;

import by.vstu.department.dto.ExportEmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StubService {

    public List<ExportEmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        List<ExportEmployeeDTO> employees = new ArrayList<>();
        switch (tabel) {
            case "TABEL_ISAP":
                employees.add(new ExportEmployeeDTO("Казаков В.Е.", "зав. каф.", "1"));
                employees.add(new ExportEmployeeDTO("Бизюк А.Н.", "ст. преп.", "2"));
                employees.add(new ExportEmployeeDTO("Корнеенко А.А.", "ст. преп.", "3"));
                employees.add(new ExportEmployeeDTO("Шут В.Н.", "проф.", "4"));
                employees.add(new ExportEmployeeDTO("Науменко А.М.", "проф.", "5"));
                break;
            case "TABEL_MTF":
                employees.add(new ExportEmployeeDTO("Иванов И.И.", "ст. преп.", "11"));
                employees.add(new ExportEmployeeDTO("Петров П.П.", "ст. преп.", "12"));
                employees.add(new ExportEmployeeDTO("Сергеев С.С.", "ст. преп.", "13"));
                employees.add(new ExportEmployeeDTO("Репнев В.В.", "ст. преп.", "14"));
                break;
            default:
                employees.add(new ExportEmployeeDTO("head department", "ГЛАВА", "HEAD_TABEL"));
                break;
        }
        return employees;
    }

}
