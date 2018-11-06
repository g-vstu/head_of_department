package by.vstu.department.service;

import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.dto.ExportEmployeeDTO;
import by.vstu.department.model.Anketa;
import by.vstu.department.model.enums.AnketaParameterStatusType;
import by.vstu.department.repository.AnketaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnketaService {

    private final AnketaRepository repository;
    private final StubService stubService;

    @Transactional
    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        List<EmployeeDTO> employees = new ArrayList<>();
        for (ExportEmployeeDTO exportEmployee : stubService.getEmployeeByHeadTabel(tabel)) {
            EmployeeDTO employee = new EmployeeDTO(exportEmployee);
            Anketa anketa = repository.findByTabel(exportEmployee.getTabel());
            if (Objects.isNull(anketa)) {
                createDefault(exportEmployee.getTabel());
                employee.setStatus(AnketaParameterStatusType.NOT_FILLED);
            } else {
                employee.setStatus(anketa.getStatus());
            }
            employees.add(employee);
        }
        return employees;
    }

    private Anketa createDefault(String tabel) {
        Anketa anketa = new Anketa();
        anketa.setTabel(tabel);
        anketa.setStatus(AnketaParameterStatusType.NOT_FILLED);
        return repository.save(anketa);
    }
}
