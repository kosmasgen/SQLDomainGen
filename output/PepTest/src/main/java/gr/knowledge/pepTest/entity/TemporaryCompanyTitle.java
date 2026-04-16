package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "temporary_company_title")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompanyTitle {

    @Id
    @Column(name = "id", precision = 19, nullable = false)
    private BigInteger id;

    @Column(name = "version", precision = 19, nullable = false)
    private BigInteger version;

    @Column(name = "company_id", precision = 19)
    private BigInteger companyId;

    @Column(name = "company_preregistration_id", precision = 19)
    private BigInteger companyPreregistrationId;

    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "order_seq", nullable = false)
    private BigInteger orderSeq;

    @Column(name = "recdeleted", precision = 19, nullable = false)
    private BigInteger recdeleted;

    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "title_latin")
    private String titleLatin;

    @Column(name = "title_nrm", length = 1000)
    private String titleNrm;

    @Column(name = "title_status_id", precision = 19)
    private BigInteger titleStatusId;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @Column(name = "gemi_id", precision = 19)
    private BigInteger gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

}
