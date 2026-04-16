package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.envers.Audited;
import org.hibernate.type.SqlTypes;

@Entity
@Audited
@Table(name = "data_staging", uniqueConstraints = @UniqueConstraint(columnNames = {"legacy_table_name", "legacy_record_id", "legacy_updated_at"}))
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

    @Column(name = "legacy_table_name", length = 100, nullable = false)
    private String legacyTableName;

    @Column(name = "legacy_record_id", nullable = false)
    private String legacyRecordId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_data", columnDefinition = "jsonb", nullable = false)
    private String rawData;

    @Column(name = "legacy_updated_at", nullable = false)
    private LocalDateTime legacyUpdatedAt;

    @Column(name = "pulled_at", nullable = false)
    private LocalDateTime pulledAt;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

}
