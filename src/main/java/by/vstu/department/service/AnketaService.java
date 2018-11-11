package by.vstu.department.service;

import by.vstu.department.dto.AnketaDTO;
import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.dto.EmployeeParameterDTO;
import by.vstu.department.dto.ExportEmployeeDTO;
import by.vstu.department.exception.BusinessException;
import by.vstu.department.model.Anketa;
import by.vstu.department.model.EmployeeParameter;
import by.vstu.department.model.enums.AnketaParameterStatusType;
import by.vstu.department.repository.AnketaRepository;
import by.vstu.department.service.mapper.AnketaDTOMapper;
import by.vstu.department.service.mapper.EmployeeParameterDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnketaService {

    private final AnketaRepository repository;
    private final ParameterService parameterService;
    private final EmployeeParameterService employeeParameterService;
    private final StubService stubService;
    private final AnketaDTOMapper anketaDTOMapper;
    private final EmployeeParameterDTOMapper parameterDTOMapper;

    public AnketaDTO get(Long id) {
        return anketaDTOMapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new BusinessException("Anketa with id " + id + " not found")));
    }

    public AnketaDTO getByTabel(String tabel) {
        Anketa anketa = repository.findByTabel(tabel)
                .orElseThrow(() -> new BusinessException("Anketa with tabel " + tabel + " not found"));
        return anketaDTOMapper.toDTO(anketa);
    }

    @Transactional
    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        List<EmployeeDTO> employees = new ArrayList<>();
        for (ExportEmployeeDTO exportEmployee : stubService.getEmployeeByHeadTabel(tabel)) {
            EmployeeDTO employee = new EmployeeDTO(exportEmployee);
            Anketa anketa = repository.findByTabel(exportEmployee.getTabel()).orElse(null);
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

    @Transactional
    public AnketaDTO updateAnketa(Long id, List<EmployeeParameterDTO> parameters) {
        Anketa anketa = findByIdNotNull(id);
        employeeParameterService.removeAllByAnketaId(id);
        Set<EmployeeParameter> employeeParameters = new HashSet<>();
        parameters.forEach(param -> employeeParameters.add(employeeParameterService.create(anketa, param)));
        anketa.setParameters(employeeParameters);
        return anketaDTOMapper.toDTO(repository.save(anketa));
    }

    public Anketa findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Anketa with id " + id + " not found"));
    }

    private Anketa createDefault(String tabel) {
        Anketa anketa = new Anketa();
        anketa.setTabel(tabel);
        anketa.setStatus(AnketaParameterStatusType.NOT_FILLED);
        return repository.save(anketa);
    }
}
