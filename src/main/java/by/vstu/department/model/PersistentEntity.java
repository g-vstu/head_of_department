package by.vstu.department.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public class PersistentEntity {

    @Id
    private UUID id = UUID.randomUUID();
}
