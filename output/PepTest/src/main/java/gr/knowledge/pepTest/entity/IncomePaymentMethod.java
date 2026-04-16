package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "income_payment_method", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_pay_method_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomePaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_pay_method_id", nullable = false)
    private Integer chamberPayMethodId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted")
    private Integer recdeleted;

}
