package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id")
    private Integer chamberId;

    @Column(name = "chamber_product_id", nullable = false)
    private Long chamberProductId;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "cd", length = 20, nullable = false)
    private String cd;

    @Column(name = "cd_gemi")
    private String cdGemi;

    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "parent_product_id")
    private Long parentProductId;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

}
