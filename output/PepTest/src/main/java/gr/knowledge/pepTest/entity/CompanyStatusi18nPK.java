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
public class CompanyStatusi18nPK implements Serializable {

    @Column(name = "company_status_id", nullable = false)
    private UUID companyStatusId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
