package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "company", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_company_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "afm", length = 9)
    private String afm;

    @Column(name = "am")
    private BigInteger am;

    @Column(name = "gemi_id")
    private BigInteger gemiId;

    @Column(name = "co_name", length = 1000, nullable = false)
    private String coName;

    @Column(name = "chamber_company_id")
    private BigInteger chamberCompanyId;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "date_interruption")
    private LocalDateTime dateInterruption;

    @Column(name = "member")
    private BigInteger member;

    @Column(name = "rec_type", length = 1)
    private String recType;

    @Column(name = "recdeleted")
    private Boolean recdeleted;

    @Column(name = "address_city", length = 50)
    private String addressCity;

    @Column(name = "address_latitude")
    private String addressLatitude;

    @Column(name = "address_longitude")
    private String addressLongitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_municipality_pri_id")
    private Municipality addressMunicipalityPri;

    @Column(name = "address_region", length = 50)
    private String addressRegion;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_street_number")
    private String addressStreetNumber;

    @Column(name = "address_zip_code", length = 12)
    private String addressZipCode;

    @Column(name = "branch_type_id")
    private BigInteger branchTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamber_department_id")
    private ChamberDepartment chamberDepartment;

    @Column(name = "chamber_gemi_responsible_id")
    private BigInteger chamberGemiResponsibleId;

    @Column(name = "co_name_nrm", length = 1000)
    private String coNameNrm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_status_id")
    private CompanyStatus companyStatus;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "contact_mobile", length = 50)
    private String contactMobile;

    @Column(name = "contact_phone1", length = 20)
    private String contactPhone1;

    @Column(name = "contact_phone2", length = 20)
    private String contactPhone2;

    @Column(name = "contact_url", length = 100)
    private String contactUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corporate_status_id")
    private CorporateStatus corporateStatus;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "date_incorporated")
    private LocalDateTime dateIncorporated;

    @Column(name = "date_registered")
    private LocalDateTime dateRegistered;

    @Column(name = "gemi_number", length = 12)
    private String gemiNumber;

    @Column(name = "objective", columnDefinition = "text")
    private String objective;

    @Column(name = "receive_newsletter")
    private Boolean receiveNewsletter;

    @Column(name = "is_chamber_company", nullable = false)
    private Boolean isChamberCompany;

    @Column(name = "is_trades_company", nullable = false)
    private Boolean isTradesCompany;

    @Column(name = "show_business_guide", nullable = false)
    private Boolean showBusinessGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_location_id")
    private BusinessLocation businessLocation;

    @Column(name = "has_active_profiles")
    private Boolean hasActiveProfiles;

    @Column(name = "is_proteas_data")
    private Boolean isProteasData;

    @Column(name = "responsible_name")
    private String responsibleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_country_id")
    private Country addressCountry;

    @Column(name = "address_zoom_level")
    private BigInteger addressZoomLevel;

    @Column(name = "contact_phone3", length = 20)
    private String contactPhone3;

    @Column(name = "date_profession_started")
    private LocalDateTime dateProfessionStarted;

    @Column(name = "foundation_date")
    private LocalDateTime foundationDate;

    @Column(name = "me_criteria1_id")
    private BigInteger meCriteria1Id;

    @Column(name = "me_criteria2_id")
    private BigInteger meCriteria2Id;

    @Column(name = "me_criteria3_id")
    private BigInteger meCriteria3Id;

    @Column(name = "member_dues")
    private LocalDateTime memberDues;

    @Column(name = "jb_uuid")
    private UUID jbUuid;

    @Column(name = "jb_description", columnDefinition = "text")
    private String jbDescription;

    @Column(name = "jb_number_employees")
    private Long jbNumberEmployees;

    @Column(name = "jb_motto")
    private String jbMotto;

    @Column(name = "jb_email")
    private String jbEmail;

    @Column(name = "jb_linked_in_url")
    private String jbLinkedInUrl;

    @Column(name = "jb_facebook_url")
    private String jbFacebookUrl;

    @Column(name = "jb_registration_status")
    private String jbRegistrationStatus;

    @Column(name = "jb_logo_id")
    private String jbLogoId;

    @Column(name = "jb_cover_id")
    private String jbCoverId;

    // TODO: Foreign key 'jb_location_id' was not resolved to a generated entity relationship.
    // Keep it as a scalar field until the target entity becomes available.
    @Column(name = "jb_location_id")
    private Integer jbLocationId;

    // TODO: Foreign key 'jb_industry_id' was not resolved to a generated entity relationship.
    // Keep it as a scalar field until the target entity becomes available.
    @Column(name = "jb_industry_id")
    private Integer jbIndustryId;

    @Column(name = "jb_isvalid")
    private Boolean jbIsvalid;

    @Column(name = "jb_activation_status")
    private String jbActivationStatus;

}
