package by.vstu.department.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "param")
@AttributeOverride(name = "id", column = @Column(name = "p_id"))
public class Parameter extends PersistentEntity {

    @NotNull
    @Column(name = "p_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pg_id")
    private ParameterGroup group;
}
