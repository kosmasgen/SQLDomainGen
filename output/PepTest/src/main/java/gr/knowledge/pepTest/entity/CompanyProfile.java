package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "company_profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "address_city", length = 255)
    private String addressCity;

    @Column(name = "address_latitude", length = 255)
    private String addressLatitude;

    @Column(name = "address_longitude", length = 255)
    private String addressLongitude;

    @Column(name = "address_region", length = 255)
    private String addressRegion;

    @Column(name = "address_street", length = 255)
    private String addressStreet;

    @Column(name = "address_street_number", length = 255)
    private String addressStreetNumber;

    @Column(name = "address_zip_code", length = 255)
    private String addressZipCode;

    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Column(name = "contact_mobile", length = 255)
    private String contactMobile;

    @Column(name = "contact_phone1", length = 255)
    private String contactPhone1;

    @Column(name = "contact_phone2", length = 255)
    private String contactPhone2;

    @Column(name = "contact_phone3", length = 255)
    private String contactPhone3;

    @Column(name = "contact_url", length = 255)
    private String contactUrl;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "business_location_id")
    private UUID businessLocationId;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "rec_deleted")
    private Boolean recDeleted = false;

    @Column(name = "show_business_guide")
    private Boolean showBusinessGuide;

}
