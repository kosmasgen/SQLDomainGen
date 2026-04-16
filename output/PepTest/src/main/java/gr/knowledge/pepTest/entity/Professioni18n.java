package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "professioni18n", uniqueConstraints = @UniqueConstraint(columnNames = {"profession_id", "chamber_i18n_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professioni18n {

    @EmbeddedId
    private Professioni18nKey id;

    @MapsId("professionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "chamber_i18n_id")
    private Integer chamberI18nId;

}
