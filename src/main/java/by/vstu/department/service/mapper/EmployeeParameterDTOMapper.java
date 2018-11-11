package by.vstu.department.service.mapper;

import by.vstu.department.dto.EmployeeParameterDTO;
import by.vstu.department.model.EmployeeParameter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

@Service
public class EmployeeParameterDTOMapper implements EntityToDTOMapper<EmployeeParameterDTO, EmployeeParameter, EmployeeParameterDTO> {

    private final ModelMapper mapper = new ModelMapper();

    private final ParameterDTOMapper parameterDTOMapper;

    public EmployeeParameterDTOMapper(ParameterDTOMapper parameterDTOMapper) {
        this.parameterDTOMapper = parameterDTOMapper;

        mapper.addMappings(new PropertyMap<EmployeeParameterDTO, EmployeeParameter>() {
            protected void configure() {
                skip().setId(null);
                skip().setParameter(null);
                skip().setAnketa(null);
            }
        });
    }

    @Override
    public EmployeeParameterDTO toDTO(EmployeeParameter entity, Object... args) {
        EmployeeParameterDTO dto = mapper.map(entity, EmployeeParameterDTO.class);
        dto.setParameter(parameterDTOMapper.toDTO(entity.getParameter()));
        return dto;
    }

    @Override
    public EmployeeParameter toEntity(EmployeeParameterDTO dto, Object... args) {
        return mapper.map(dto, EmployeeParameter.class);
    }
}
