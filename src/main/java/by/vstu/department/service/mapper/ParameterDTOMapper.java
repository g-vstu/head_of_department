package by.vstu.department.service.mapper;

import by.vstu.department.dto.ParameterDTO;
import by.vstu.department.model.Parameter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

@Service
public class ParameterDTOMapper implements EntityToDTOMapper<ParameterDTO, Parameter, ParameterDTO> {

    private final ModelMapper mapper = new ModelMapper();
    private final ParameterGroupDTOMapper groupDTOMapper;

    public ParameterDTOMapper(ParameterGroupDTOMapper groupDTOMapper) {
        this.groupDTOMapper = groupDTOMapper;

        mapper.addMappings(new PropertyMap<ParameterDTO, Parameter>() {
            protected void configure() {
                skip().setId(null);
                skip().setGroup(null);
            }
        });
    }

    @Override
    public ParameterDTO toDTO(Parameter entity, Object... args) {
        ParameterDTO dto = mapper.map(entity, ParameterDTO.class);
        dto.setGroup(groupDTOMapper.toDTO(entity.getGroup()));
        return dto;
    }

    @Override
    public Parameter toEntity(ParameterDTO dto, Object... args) {
        return null;
    }
}
