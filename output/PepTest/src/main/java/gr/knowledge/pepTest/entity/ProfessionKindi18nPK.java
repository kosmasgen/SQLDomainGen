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
public class ProfessionKindi18nPK implements Serializable {

    @Column(name = "profession_kind_id", nullable = false)
    private UUID professionKindId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
