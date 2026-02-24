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
public class Professioni18nPK implements Serializable {

    @Column(name = "profession_id", nullable = false)
    private UUID professionId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
