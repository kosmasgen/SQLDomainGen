package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "company_profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfile {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "address_city", length = 50)
    private String addressCity;

    @Column(name = "address_latitude")
    private String addressLatitude;

    @Column(name = "address_longitude")
    private String addressLongitude;

    @Column(name = "address_region", length = 50)
    private String addressRegion;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_street_number")
    private String addressStreetNumber;

    @Column(name = "address_zip_code", length = 12)
    private String addressZipCode;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "contact_mobile", length = 50)
    private String contactMobile;

    @Column(name = "contact_phone1", length = 20)
    private String contactPhone1;

    @Column(name = "contact_phone2", length = 20)
    private String contactPhone2;

    @Column(name = "contact_phone3", length = 20)
    private String contactPhone3;

    @Column(name = "contact_url", length = 100)
    private String contactUrl;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_location_id")
    private BusinessLocation businessLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "recdeleted")
    private Boolean recdeleted;

    @Column(name = "show_business_guide")
    private Boolean showBusinessGuide;

}
