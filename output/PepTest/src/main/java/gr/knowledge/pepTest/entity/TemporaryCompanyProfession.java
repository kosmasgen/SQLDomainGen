package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "temporary_company_profession")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompanyProfession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version", nullable = false)
    private BigDecimal version;

    @Column(name = "company_id", nullable = false)
    private BigDecimal companyId;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "profession_id", nullable = false)
    private BigDecimal professionId;

    @Column(name = "profession_kind_id")
    private BigDecimal professionKindId;

    @Column(name = "rec_eleted", nullable = false)
    private BigDecimal recEleted;

    @Column(name = "to_date")
    private LocalDateTime toDate;

    @Column(name = "gemi_id")
    private BigDecimal gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

}
