package com.vstu.department.service;

import com.vstu.department.dto.ParameterGroupDTO;
import com.vstu.department.model.enums.ParameterGroupType;
import com.vstu.department.repository.ParameterGroupRepository;
import com.vstu.department.service.mapper.ParameterGroupDTOMapper;
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
