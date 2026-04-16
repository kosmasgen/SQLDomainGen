package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "ch_app_user_contact")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChAppUserContact {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamber_app_user_id", nullable = false)
    private ChamberAppUser chamberAppUser;

    @Column(name = "phone1", length = 20)
    private String phone1;

    @Column(name = "phone2", length = 20)
    private String phone2;

    @Column(name = "mobile1", length = 20)
    private String mobile1;

    @Column(name = "mobile2", length = 20)
    private String mobile2;

    @Column(name = "email1", length = 100)
    private String email1;

    @Column(name = "email2", length = 100)
    private String email2;

    @Column(name = "url")
    private String url;

    @Column(name = "zip_code", length = 12)
    private String zipCode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "recdeleted", nullable = false)
    private Boolean recdeleted;

    @Column(name = "listing_url")
    private String listingUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile", length = 50)
    private String mobile;

}
