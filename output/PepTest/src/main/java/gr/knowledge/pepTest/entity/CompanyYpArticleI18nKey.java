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
public class CompanyYpArticleI18nKey implements Serializable {

    @Column(name = "company_article_id", nullable = false)
    private UUID companyArticleId;

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

}
