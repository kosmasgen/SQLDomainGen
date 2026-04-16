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
public class BusinessLocationI18nKey implements Serializable {

    @Column(name = "business_location_id", nullable = false)
    private UUID businessLocationId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
