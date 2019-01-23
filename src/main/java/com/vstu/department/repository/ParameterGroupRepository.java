package com.vstu.department.repository;

import com.vstu.department.model.ParameterGroup;
import com.vstu.department.model.enums.ParameterGroupType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParameterGroupRepository extends JpaRepository<ParameterGroup, Long> {

    List<ParameterGroup> findByGroupType(ParameterGroupType groupType);
}
