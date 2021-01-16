package com.vstu.department.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "param")
@AttributeOverride(name = "id", column = @Column(name = "p_id"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parameter extends PersistentEntity {

    @NotNull
    @Column(name = "p_name")
    String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pg_id")
    ParameterGroup group;

    @Column(name = "p_tip")
    String tip;

    /*
     * @OneToMany(mappedBy = "parameter") Set<EmployeeParameter> ankets;
     */

    @NotNull
    @Column(name = "p_max_coefficient")
    Double maxCoefficient;
}
