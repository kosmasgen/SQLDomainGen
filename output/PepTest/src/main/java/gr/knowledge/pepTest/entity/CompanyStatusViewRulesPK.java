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
public class CompanyStatusViewRulesPK implements Serializable {

    @Column(name = "company_status_id", nullable = false)
    private UUID companyStatusId;

    @Column(name = "company_view_rules_id", nullable = false)
    private UUID companyViewRulesId;

}
