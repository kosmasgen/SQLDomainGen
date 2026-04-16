package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "profession_kindi18n", uniqueConstraints = @UniqueConstraint(columnNames = {"profession_kind_id", "chamber_i18n_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionKindi18n {

    @EmbeddedId
    private ProfessionKindi18nKey id;

    @MapsId("professionKindId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_kind_id", nullable = false)
    private ProfessionKind professionKind;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "description", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "chamber_i18n_id")
    private Integer chamberI18nId;

}
