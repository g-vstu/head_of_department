package com.vstu.department.service;

import com.vstu.department.dto.ParameterDTO;
import com.vstu.department.exception.BusinessException;
import com.vstu.department.model.Parameter;
import com.vstu.department.model.enums.ParameterGroupType;
import com.vstu.department.repository.ParameterRepository;
import com.vstu.department.service.mapper.ParameterDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterService {

    private final ParameterRepository repository;
    private final ParameterDTOMapper mapper;

    public Page<ParameterDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public List<ParameterDTO> findByGroup(Long id) {
        return mapper.toDTOs(repository.findByGroupId(id));
    }

    public List<ParameterDTO> findByGroupType(ParameterGroupType groupType) {
        return mapper.toDTOs(repository.findByGroupGroupType(groupType));
    }

    public Parameter findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Parameter with id " + id + " not found"));
    }
}
