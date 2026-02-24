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
public class CompanyProfileI18nPK implements Serializable {

    @Column(name = "company_profile_id", nullable = false)
    private UUID companyProfileId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
