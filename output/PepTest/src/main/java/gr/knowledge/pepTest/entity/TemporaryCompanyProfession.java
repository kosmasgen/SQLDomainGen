package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "temporary_company_profession")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompanyProfession {

    @Id
    @Column(name = "id", precision = 19, nullable = false)
    private BigInteger id;

    @Column(name = "version", precision = 19, nullable = false)
    private BigInteger version;

    @Column(name = "company_id", precision = 19, nullable = false)
    private BigInteger companyId;

    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "profession_id", precision = 19, nullable = false)
    private BigInteger professionId;

    @Column(name = "profession_kind_id", precision = 19)
    private BigInteger professionKindId;

    @Column(name = "recdeleted", precision = 19, nullable = false)
    private BigInteger recdeleted;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @Column(name = "gemi_id", precision = 19)
    private BigInteger gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

}
