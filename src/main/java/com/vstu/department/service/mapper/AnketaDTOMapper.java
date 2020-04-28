package com.vstu.department.service.mapper;

import com.vstu.department.dto.AnketaDTO;
import com.vstu.department.model.Anketa;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AnketaDTOMapper implements EntityToDTOMapper<AnketaDTO, Anketa, AnketaDTO> {

    private final ModelMapper mapper = new ModelMapper();
    private final EmployeeParameterDTOMapper employeeParameterDTOMapper;

    public AnketaDTOMapper(EmployeeParameterDTOMapper employeeParameterDTOMapper) {
        this.employeeParameterDTOMapper = employeeParameterDTOMapper;

        mapper.addMappings(new PropertyMap<AnketaDTO, Anketa>() {
            protected void configure() {
                skip().setId(null);
                skip().setParameters(null);
            }
        });
    }

    @Override
    public AnketaDTO toDTO(Anketa entity, Object... args) {
        AnketaDTO dto = mapper.map(entity, AnketaDTO.class);
        dto.setParameters(entity.getParameters().stream().map(employeeParameterDTOMapper::toDTO).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public Anketa toEntity(AnketaDTO dto, Object... args) {
        return null;
    }
}
