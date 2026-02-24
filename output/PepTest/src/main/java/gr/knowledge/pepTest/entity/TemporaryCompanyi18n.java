package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "temporary_companyi18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompanyi18n {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version", nullable = false)
    private BigDecimal version;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "co_name", length = 255)
    private String coName;

    @Column(name = "company_id", nullable = false)
    private BigDecimal companyId;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "language_id", nullable = false)
    private BigDecimal languageId;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "mail_name", length = 255)
    private String mailName;

    @Column(name = "objective")
    private String objective;

    @Column(name = "rec_deleted", nullable = false)
    private BigDecimal recDeleted;

    @Column(name = "street", length = 255)
    private String street;

    @Column(name = "comments")
    private String comments;

    @Column(name = "gemi_id")
    private BigDecimal gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

    @Column(name = "gemi_city", length = 255)
    private String gemiCity;

    @Column(name = "article")
    private String article;

}
