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
public class MunicipalityI18nKey implements Serializable {

    @Column(name = "municipality_id", nullable = false)
    private UUID municipalityId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
