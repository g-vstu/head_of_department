package com.vstu.department.model;

import com.vstu.department.model.enums.AnketaParameterStatusType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "anketa")
@AttributeOverride(name = "id", column = @Column(name = "a_id"))
public class Anketa extends PersistentEntity {

    @NotNull
    @Column(name = "a_tabel")
    private String tabel;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "a_status")
    private AnketaParameterStatusType status;

    @OneToMany(mappedBy = "anketa", cascade = CascadeType.ALL)
    private Set<EmployeeParameter> parameters;

    @NotNull
    @Size(min = 6, max = 6)
    @Column(name = "a_half_year")
    private String halfYear;
}
