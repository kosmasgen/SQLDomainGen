package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "corporate_statusi18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorporateStatusi18n {

    @EmbeddedId
    private CorporateStatusi18nPK id;

    @MapsId("corporateStatusId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corporate_status_id", nullable = false)
    private CorporateStatus corporateStatus;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "rec_Deleted", nullable = false)
    private Boolean recDeleted = false;

    @Column(name = "chamber_i18n_id")
    private Integer chamberI18nId;

    @Column(name = "grouped_description", length = 255)
    private String groupedDescription;

}
