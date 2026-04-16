package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "company_file")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFile {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id")
    private Integer chamberId;

    @Column(name = "file_name", length = 100, nullable = false)
    private String fileName;

    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    @Column(name = "blob_uri", length = 2000, nullable = false)
    private String blobUri;

    @Column(name = "order_seq", nullable = false)
    private Integer orderSeq;

    @Column(name = "recdeleted")
    private Boolean recdeleted;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Languages language;

    @Column(name = "is_logo")
    private Boolean isLogo;

    @Column(name = "is_background")
    private Boolean isBackground;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_profile_id")
    private CompanyProfile companyProfile;

    @Column(name = "is_embedded", nullable = false)
    private Boolean isEmbedded;

}
