package by.vstu.department.model;

import by.vstu.department.model.enums.AnketaParameterStatusType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "emp_param")
@AttributeOverride(name = "id", column = @Column(name = "emp_id"))
public class EmployeeParameter extends PersistentEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "a_id")
    private Anketa anketa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "p_id")
    private Parameter parameter;

    @NotNull
    @Column(name = "emp_status")
    private AnketaParameterStatusType status;

    @NotNull
    @Column(name = "emp_count")
    private Long count;

    @NotNull
    @Column(name = "emp_created")
    private LocalDateTime created;
}
