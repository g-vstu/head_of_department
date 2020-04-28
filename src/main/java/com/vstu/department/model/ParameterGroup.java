package com.vstu.department.model;

import com.vstu.department.model.enums.ParameterGroupType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "param_group")
@AttributeOverride(name = "id", column = @Column(name = "pg_id"))
public class ParameterGroup extends PersistentEntity {

    @NotNull
    @Column(name = "pg_name")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "pg_type")
    private ParameterGroupType groupType;
}
