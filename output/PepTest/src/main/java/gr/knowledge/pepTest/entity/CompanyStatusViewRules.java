package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "company_status_view_rules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStatusViewRules {

    @EmbeddedId
    private CompanyStatusViewRulesPK id;

    @MapsId("companyStatusId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_status_id", nullable = false)
    private CompanyStatus companyStatus;

    @MapsId("companyViewRulesId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_view_rules_id", nullable = false)
    private CompanyViewRules companyViewRules;

    @Column(name = "exclude_companies")
    private Boolean excludeCompanies;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

}
