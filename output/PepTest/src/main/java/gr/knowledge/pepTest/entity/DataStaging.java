package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_staging")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataStaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "legacy_table_name", length = 255, nullable = false)
    private String legacyTableName;

    @Column(name = "legacy_record_id", length = 255, nullable = false)
    private String legacyRecordId;

    @Column(name = "raw_data", nullable = false)
    private String rawData;

    @Column(name = "legacy_updated_at", nullable = false)
    private LocalDateTime legacyUpdatedAt;

    @Column(name = "pulled_at", nullable = false)
    private LocalDateTime pulledAt;

    @Column(name = "status", length = 255, nullable = false)
    private String status;

}
