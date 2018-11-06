package by.vstu.department.model;

import by.vstu.department.model.enums.ParameterGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
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
