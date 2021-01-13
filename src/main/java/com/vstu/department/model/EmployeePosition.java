package com.vstu.department.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "positions")
@AttributeOverride(name = "id", column = @Column(name = "position_id"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeePosition extends PersistentEntity {

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Employee> employees;

    @NotNull
    @Column(name = "employee_position_name")
    String name;

}
