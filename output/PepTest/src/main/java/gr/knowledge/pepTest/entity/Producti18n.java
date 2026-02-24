package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "producti18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producti18n {

    @EmbeddedId
    private Producti18nPK id;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "chamber_i18n_id", nullable = false)
    private Long chamberI18nId;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "short_description", length = 255)
    private String shortDescription;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "rec_deleted", nullable = false)
    private Boolean recDeleted = false;

}
