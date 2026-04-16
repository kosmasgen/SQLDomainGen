package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "company_yp_article_i18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyYpArticleI18n {

    @EmbeddedId
    private CompanyYpArticleI18nKey id;

    @MapsId("companyArticleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_article_id", nullable = false)
    private CompanyYpArticle companyArticle;

    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "html", columnDefinition = "text")
    private String html;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

}
