package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "company_titlei18n", uniqueConstraints = @UniqueConstraint(columnNames = {"company_title_id", "chamber_i18n_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyTitlei18n {

    @EmbeddedId
    private CompanyTitlei18nKey id;

    @MapsId("companyTitleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_title_id", nullable = false)
    private CompanyTitle companyTitle;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

}
