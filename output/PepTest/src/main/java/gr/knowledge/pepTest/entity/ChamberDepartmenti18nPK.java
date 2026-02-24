package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChamberDepartmenti18nPK implements Serializable {

    @Column(name = "department_id", nullable = false)
    private UUID departmentId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
