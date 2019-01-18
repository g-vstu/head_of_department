package by.vstu.department.model;

import by.vstu.department.model.enums.AnketaParameterStatusType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "emp_param")
@AttributeOverride(name = "id", column = @Column(name = "emp_param_id"))
public class EmployeeParameter extends PersistentEntity {

    @ManyToOne
    @JoinColumn(name = "a_id")
    private Anketa anketa;

    @ManyToOne
    @JoinColumn(name = "p_id")
    private Parameter parameter;

    @NotNull
    @Column(name = "emp_status")
    private AnketaParameterStatusType status;

    @NotNull
    @Column(name = "emp_count")
    private Long count;

    @NotNull
    @Column(name = "emp_coefficient")
    private Double coefficient;
}
