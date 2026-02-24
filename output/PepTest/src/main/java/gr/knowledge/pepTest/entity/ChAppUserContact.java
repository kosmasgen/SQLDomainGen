package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ch_app_user_contact")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChAppUserContact {

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

    @Column(name = "chamber_app_user_id", nullable = false)
    private UUID chamberAppUserId;

    @Column(name = "phone1", length = 255)
    private String phone1;

    @Column(name = "phone2", length = 255)
    private String phone2;

    @Column(name = "mobile1", length = 255)
    private String mobile1;

    @Column(name = "mobile2", length = 255)
    private String mobile2;

    @Column(name = "email1", length = 255)
    private String email1;

    @Column(name = "email2", length = 255)
    private String email2;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "zip_code", length = 255)
    private String zipCode;

    @Column(name = "latitude", length = 255)
    private String latitude;

    @Column(name = "longitude", length = 255)
    private String longitude;

    @Column(name = "street_number", length = 255)
    private String streetNumber;

    @Column(name = "rec_deleted", nullable = false)
    private Boolean recDeleted = false;

    @Column(name = "listing_url", length = 255)
    private String listingUrl;

}
