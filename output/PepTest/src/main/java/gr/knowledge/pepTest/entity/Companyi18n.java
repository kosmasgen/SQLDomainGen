package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "companyi18n")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Companyi18n {

    @EmbeddedId
    private Companyi18nPK id;

    @MapsId("companyId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @MapsId("languageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages language;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "co_name", length = 255)
    private String coName;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "objective")
    private String objective;

    @Column(name = "rec_deleted", nullable = false)
    private Boolean recDeleted = false;

    @Column(name = "street", length = 255)
    private String street;

    @Column(name = "responsible_name", length = 255)
    private String responsibleName;

}
