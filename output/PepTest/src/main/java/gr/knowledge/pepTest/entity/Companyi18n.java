package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "companyi18n", uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "chamber_i18n_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Companyi18n {

    @EmbeddedId
    private Companyi18nKey id;

    @MapsId("companyId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "co_name", length = 1000)
    private String coName;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "objective", columnDefinition = "text")
    private String objective;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "street", length = 60)
    private String street;

    @Column(name = "responsible_name")
    private String responsibleName;

}
