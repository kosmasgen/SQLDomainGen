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
public class Producti18nKey implements Serializable {

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

}
