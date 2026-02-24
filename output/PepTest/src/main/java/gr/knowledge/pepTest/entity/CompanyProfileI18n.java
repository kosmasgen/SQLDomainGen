package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "company_profile_i18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfileI18n {

    @EmbeddedId
    private CompanyProfileI18nPK id;

    @MapsId("companyProfileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_profile_id", nullable = false)
    private CompanyProfile companyProfile;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "rec_deleted", nullable = false)
    private Boolean recDeleted;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "address_city", length = 255)
    private String addressCity;

    @Column(name = "address_region", length = 255)
    private String addressRegion;

    @Column(name = "address_street", length = 255)
    private String addressStreet;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "objective")
    private String objective;

}
