package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "chamber_app_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChamberAppUser {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    // TODO: Foreign key 'chamber_id' was not resolved to a generated entity relationship.
    // Keep it as a scalar field until the target entity becomes available.
    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    // TODO: Foreign key 'chamber_app_id' was not resolved to a generated entity relationship.
    // Keep it as a scalar field until the target entity becomes available.
    @Column(name = "chamber_app_id", nullable = false)
    private UUID chamberAppId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "profile_id")
    private UUID profileId;

    @Column(name = "person_id")
    private UUID personId;

}
