package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "temporary_company_titlei18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompanyTitlei18n {

    @Id
    @Column(name = "id", precision = 19, nullable = false)
    private BigInteger id;

    @Column(name = "version", precision = 19, nullable = false)
    private BigInteger version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_title_id", nullable = false)
    private TemporaryCompanyTitle companyTitle;

    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted", precision = 19, nullable = false)
    private BigInteger recdeleted;

    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "gemi_id", precision = 19)
    private BigInteger gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

}
