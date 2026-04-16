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
public class CompanyTitlei18nKey implements Serializable {

    @Column(name = "company_title_id", nullable = false)
    private UUID companyTitleId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

    @Column(name = "chamber_i18n_id", nullable = false)
    private Integer chamberI18nId;

}
