package com.vstu.department.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "param")
@AttributeOverride(name = "id", column = @Column(name = "p_id"))
public class Parameter extends PersistentEntity {

    @NotNull
    @Column(name = "p_name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pg_id")
    private ParameterGroup group;

    /*@OneToMany(mappedBy = "parameter")
    Set<EmployeeParameter> ankets;*/

    @NotNull
    @Column(name = "p_max_coefficient")
    private Double maxCoefficient;
}
