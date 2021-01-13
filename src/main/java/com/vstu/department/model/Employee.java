package com.vstu.department.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "employees")
@AttributeOverride(name = "id", column = @Column(name = "employee_id"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends PersistentEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "d_id")
    Department department;

    @NotNull
    @Column(name = "employee_fio")
    String fio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    EmployeePosition position;

    @NotNull
    @Column(name = "employee_tabel")
    String tabel;

}
