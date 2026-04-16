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
public class ChAppUserContactI18nKey implements Serializable {

    @Column(name = "ch_app_user_contact_id", nullable = false)
    private UUID chAppUserContactId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
