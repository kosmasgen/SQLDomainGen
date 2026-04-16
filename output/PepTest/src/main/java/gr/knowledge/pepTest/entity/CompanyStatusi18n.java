package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "company_statusi18n", uniqueConstraints = @UniqueConstraint(columnNames = {"company_status_id", "chamber_i18n_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStatusi18n {

    @EmbeddedId
    private CompanyStatusi18nKey id;

    @MapsId("companyStatusId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_status_id", nullable = false)
    private CompanyStatus companyStatus;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "chamber_i18n_id")
    private Integer chamberI18nId;

}
