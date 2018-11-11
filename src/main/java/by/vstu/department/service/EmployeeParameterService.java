package by.vstu.department.service;

import by.vstu.department.dto.EmployeeParameterDTO;
import by.vstu.department.model.Anketa;
import by.vstu.department.model.EmployeeParameter;
import by.vstu.department.repository.EmployeeParameterRepository;
import by.vstu.department.service.mapper.EmployeeParameterDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeParameterService {

    private final EmployeeParameterRepository repository;
    private final ParameterService parameterService;
    private final EmployeeParameterDTOMapper mapper;

    public EmployeeParameter create(Anketa anketa, EmployeeParameterDTO employeeParameterDTO) {
        EmployeeParameter employeeParameter = mapper.toEntity(employeeParameterDTO);
        employeeParameter.setAnketa(anketa);
        employeeParameter.setCreated(LocalDateTime.now());
        employeeParameter.setParameter(parameterService.findByIdNotNull(employeeParameterDTO.getParameterId()));
        return repository.save(employeeParameter);
    }

    public void removeAllByAnketaId(Long anketaId) {
        repository.removeAllByAnketaId(anketaId);
    }
}
