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
public class CorporateStatusi18nKey implements Serializable {

    @Column(name = "corporate_status_id", nullable = false)
    private UUID corporateStatusId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
