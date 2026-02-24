package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "temporary_company_title")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompanyTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version", nullable = false)
    private BigDecimal version;

    @Column(name = "company_id")
    private BigDecimal companyId;

    @Column(name = "company_preregistration_id")
    private BigDecimal companyPreregistrationId;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "order_seq", nullable = false)
    private BigDecimal orderSeq;

    @Column(name = "rec_deleted", nullable = false)
    private BigDecimal recDeleted;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "title_latin", length = 255)
    private String titleLatin;

    @Column(name = "title_nrm", length = 255)
    private String titleNrm;

    @Column(name = "title_status_id")
    private BigDecimal titleStatusId;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @Column(name = "gemi_id")
    private BigDecimal gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

}
