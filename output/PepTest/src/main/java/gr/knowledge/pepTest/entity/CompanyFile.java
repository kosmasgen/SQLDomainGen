package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "company_file")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id")
    private Integer chamberId;

    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    @Column(name = "blob_uri", length = 255, nullable = false)
    private String blobUri;

    @Column(name = "order_seq", nullable = false)
    private Integer orderSeq;

    @Column(name = "rec_deleted")
    private Boolean recDeleted;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "language_id")
    private UUID languageId;

    @Column(name = "is_logo")
    private Boolean isLogo;

    @Column(name = "is_background")
    private Boolean isBackground;

    @Column(name = "company_profile_id")
    private UUID companyProfileId;

    @Column(name = "is_embedded", nullable = false)
    private Boolean isEmbedded = false;

}
