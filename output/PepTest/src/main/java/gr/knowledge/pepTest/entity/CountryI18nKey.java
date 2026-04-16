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
public class CountryI18nKey implements Serializable {

    @Column(name = "country_id", nullable = false)
    private UUID countryId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
