package by.vstu.department.service;

import by.vstu.department.dto.EmployeeParameterDTO;
import by.vstu.department.exception.BusinessException;
import by.vstu.department.model.Anketa;
import by.vstu.department.model.EmployeeParameter;
import by.vstu.department.model.Parameter;
import by.vstu.department.model.enums.ParameterGroupType;
import by.vstu.department.repository.EmployeeParameterRepository;
import by.vstu.department.service.mapper.EmployeeParameterDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeParameterService {

    private final EmployeeParameterRepository repository;
    private final ParameterService parameterService;
    private final EmployeeParameterDTOMapper mapper;

    public EmployeeParameter create(Anketa anketa, EmployeeParameterDTO employeeParameterDTO) {
        Parameter param = parameterService.findByIdNotNull(employeeParameterDTO.getParameterId());
        if (param.getMaxCoefficient() < employeeParameterDTO.getCoefficient()) {
            throw new BusinessException("Coefficient of param should be less than max coefficient. "
                    + employeeParameterDTO.getCoefficient() + " greater than " + param.getMaxCoefficient());
        }
        EmployeeParameter employeeParameter = mapper.toEntity(employeeParameterDTO);
        employeeParameter.setAnketa(anketa);
        employeeParameter.setParameter(param);
        employeeParameter.setCount(employeeParameterDTO.getCount());
        return repository.save(employeeParameter);
    }

    public void removeAllByAnketaId(Long anketaId) {
        repository.removeAllByAnketaId(anketaId);
    }

    public Map<String, List<EmployeeParameter>> getParametersBySearch(ParameterGroupType type, List<String> halfYears, List<String> tabels) {
        final Map<String, List<EmployeeParameter>> result = new HashMap<>();
        tabels.forEach(tabel -> result.put(tabel, repository.getParametersBySearch(type, halfYears, tabel)));
        return result;
    }
}
