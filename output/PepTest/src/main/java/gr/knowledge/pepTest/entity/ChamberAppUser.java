package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "chamber_app_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChamberAppUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_app_id", nullable = false)
    private UUID chamberAppId;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "rec_deleted", nullable = false)
    private Boolean recDeleted = false;

    @Column(name = "profile_id")
    private UUID profileId;

}
