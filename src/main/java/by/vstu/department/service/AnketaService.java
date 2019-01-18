package by.vstu.department.service;

import by.vstu.department.dto.AnketaDTO;
import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.dto.EmployeeParameterDTO;
import by.vstu.department.exception.BusinessException;
import by.vstu.department.model.Anketa;
import by.vstu.department.model.EmployeeParameter;
import by.vstu.department.model.enums.AnketaParameterStatusType;
import by.vstu.department.repository.AnketaRepository;
import by.vstu.department.service.mapper.AnketaDTOMapper;
import by.vstu.department.util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnketaService {

    private final AnketaRepository repository;
    private final EmployeeParameterService employeeParameterService;
    private final StubService stubService;
    private final AnketaDTOMapper anketaDTOMapper;

    @Transactional(readOnly = true)
    public AnketaDTO get(Long id) {
        return anketaDTOMapper.toDTO(findByIdNotNull(id));
    }

    @Transactional(readOnly = true)
    public AnketaDTO getByTabelAndHalfYear(String tabel, String halfYear) {
        Anketa anketa = repository.findByTabelAndHalfYear(tabel, halfYear)
                .orElseThrow(() -> new BusinessException("Anketa with tabel: " + tabel + " and half-year: " + halfYear + " not found"));
        return anketaDTOMapper.toDTO(anketa);
    }

    @Transactional
    public List<EmployeeDTO> getEmployeeByHeadTabel(String tabel) {
        List<EmployeeDTO> exportEmployee = stubService.getEmployeeByHeadTabel(tabel);
        exportEmployee.stream()
                .filter(emp -> !repository.existsByTabelAndHalfYear(emp.getTabel(), UtilService.getDefaultHalfYear()))
                .forEach(emp -> createDefault(emp.getTabel()));

        return exportEmployee;
    }

    @Transactional
    public AnketaDTO updateAnketa(Long id, List<EmployeeParameterDTO> parameters) {
        Anketa anketa = findByIdNotNull(id);
        if (anketa.getStatus().equals(AnketaParameterStatusType.APPROVED)) {
            throw new BusinessException("Anketa can't be updated. Check status");
        }
        employeeParameterService.removeAllByAnketaId(id);
        Set<EmployeeParameter> employeeParameters = new HashSet<>();
        parameters.forEach(param -> employeeParameters.add(employeeParameterService.create(anketa, param)));
        anketa.setParameters(employeeParameters);
        return anketaDTOMapper.toDTO(repository.save(anketa));
    }

    private Anketa findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Anketa with id " + id + " not found"));
    }

    private void createDefault(String tabel) {
        Anketa anketa = new Anketa();
        anketa.setTabel(tabel);
        anketa.setHalfYear(UtilService.getDefaultHalfYear());
        anketa.setStatus(AnketaParameterStatusType.NOT_FILLED);
        repository.save(anketa);
    }
}
