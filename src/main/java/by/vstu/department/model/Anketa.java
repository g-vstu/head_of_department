package by.vstu.department.model;

import by.vstu.department.model.enums.AnketaParameterStatusType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
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

    @OneToMany(mappedBy = "anketa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<EmployeeParameter> parameters;
}
