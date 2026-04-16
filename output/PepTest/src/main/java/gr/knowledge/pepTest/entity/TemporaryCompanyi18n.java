package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "temporary_companyi18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompanyi18n {

    @Id
    @Column(name = "id", precision = 19, nullable = false)
    private BigInteger id;

    @Column(name = "version", precision = 19, nullable = false)
    private BigInteger version;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "co_name", length = 1000)
    private String coName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private TemporaryCompany company;

    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "mail_name", length = 60)
    private String mailName;

    @Column(name = "objective", columnDefinition = "text")
    private String objective;

    @Column(name = "recdeleted", precision = 19, nullable = false)
    private BigInteger recdeleted;

    @Column(name = "street", length = 60)
    private String street;

    @Column(name = "comments", columnDefinition = "text")
    private String comments;

    @Column(name = "gemi_id", precision = 19)
    private BigInteger gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

    @Column(name = "gemi_city", length = 24)
    private String gemiCity;

    @Column(name = "article", columnDefinition = "text")
    private String article;

}
