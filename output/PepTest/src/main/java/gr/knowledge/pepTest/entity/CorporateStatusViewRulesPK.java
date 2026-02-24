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
public class CorporateStatusViewRulesPK implements Serializable {

    @Column(name = "corporate_status_id", nullable = false)
    private UUID corporateStatusId;

    @Column(name = "company_view_rules_id", nullable = false)
    private UUID companyViewRulesId;

}
