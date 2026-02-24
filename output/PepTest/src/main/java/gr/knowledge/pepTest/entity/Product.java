package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id")
    private Integer chamberId;

    @Column(name = "chamber_product_id", nullable = false)
    private Long chamberProductId;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "cd", length = 255, nullable = false)
    private String cd;

    @Column(name = "cd_gemi", length = 255)
    private String cdGemi;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "parent_product_id")
    private Long parentProductId;

    @Column(name = "rec_deleted", nullable = false)
    private Boolean recDeleted = false;

}
