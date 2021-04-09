package com.vstu.department.service;

import com.vstu.department.dto.EmployeeParameterDTO;
import com.vstu.department.dto.statistics.GeneralEmployeStatisticsDTO;
import com.vstu.department.exception.BusinessException;
import com.vstu.department.model.Anketa;
import com.vstu.department.model.EmployeeParameter;
import com.vstu.department.model.Parameter;
import com.vstu.department.model.enums.ParameterGroupType;
import com.vstu.department.repository.EmployeeParameterRepository;
import com.vstu.department.service.mapper.EmployeeParameterDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<GeneralEmployeStatisticsDTO> getGeneralStatistics(List<String> halfYears, List<String> tabels) {
        List<GeneralEmployeStatisticsDTO> generalEmployeStatisticsDTOS = new ArrayList<>();
        tabels.stream().forEach(tabel -> generalEmployeStatisticsDTOS.add(
                new GeneralEmployeStatisticsDTO(tabel, repository.getGeneralStatisticsTemp(halfYears, tabel), repository.getSummEmployeeParametersByAnketaTabelAndAnketaHalfYears(tabel, halfYears))));
        return generalEmployeStatisticsDTOS;

        /*return tabels.stream()
                .map(tabel -> new GeneralEmployeStatisticsDTO(tabel, repository.getGeneralStatisticsTemp(halfYears, tabel)))
                .collect(Collectors.toList());*/
    }
}
