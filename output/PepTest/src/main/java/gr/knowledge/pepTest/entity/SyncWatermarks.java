package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "sync_watermarks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyncWatermarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "table_name", length = 100, unique = true, nullable = false)
    private String tableName;

    @Column(name = "last_sync_timestamp", nullable = false)
    private LocalDateTime lastSyncTimestamp;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
