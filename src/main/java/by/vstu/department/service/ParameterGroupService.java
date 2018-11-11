package by.vstu.department.service;

import by.vstu.department.dto.ParameterGroupDTO;
import by.vstu.department.model.enums.ParameterGroupType;
import by.vstu.department.repository.ParameterGroupRepository;
import by.vstu.department.service.mapper.ParameterGroupDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParameterGroupService {

    private final ParameterGroupRepository repository;
    private final ParameterGroupDTOMapper mapper;

    public Page<ParameterGroupDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public List<ParameterGroupDTO> findByType(ParameterGroupType groupType) {
        return repository.findByGroupType(groupType)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
