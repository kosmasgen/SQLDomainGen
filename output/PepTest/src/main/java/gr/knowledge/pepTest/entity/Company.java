package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "company")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "afm", length = 255)
    private String afm;

    @Column(name = "am")
    private BigDecimal am;

    @Column(name = "gemi_id")
    private BigDecimal gemiId;

    @Column(name = "co_name", length = 255, nullable = false)
    private String coName;

    @Column(name = "chamber_company_id")
    private BigDecimal chamberCompanyId;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "date_interruption")
    private LocalDateTime dateInterruption;

    @Column(name = "member")
    private BigDecimal member;

    @Column(name = "rec_type", length = 255)
    private String recType;

    @Column(name = "rec_deleted")
    private Boolean recDeleted = false;

    @Column(name = "address_city", length = 255)
    private String addressCity;

    @Column(name = "address_latitude", length = 255)
    private String addressLatitude;

    @Column(name = "address_longitude", length = 255)
    private String addressLongitude;

    @Column(name = "address_municipality_pri_id")
    private UUID addressMunicipalityPriId;

    @Column(name = "address_region", length = 255)
    private String addressRegion;

    @Column(name = "address_street", length = 255)
    private String addressStreet;

    @Column(name = "address_street_number", length = 255)
    private String addressStreetNumber;

    @Column(name = "address_zip_code", length = 255)
    private String addressZipCode;

    @Column(name = "branch_type_id")
    private BigDecimal branchTypeId;

    @Column(name = "chamber_department_id")
    private UUID chamberDepartmentId;

    @Column(name = "chamber_gemi_responsible_id")
    private BigDecimal chamberGemiResponsibleId;

    @Column(name = "co_name_nrm", length = 255)
    private String coNameNrm;

    @Column(name = "company_status_id")
    private UUID companyStatusId;

    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Column(name = "contact_mobile", length = 255)
    private String contactMobile;

    @Column(name = "contact_phone1", length = 255)
    private String contactPhone1;

    @Column(name = "contact_phone2", length = 255)
    private String contactPhone2;

    @Column(name = "contact_url", length = 255)
    private String contactUrl;

    @Column(name = "corporate_status_id")
    private UUID corporateStatusId;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "date_incorporated")
    private LocalDateTime dateIncorporated;

    @Column(name = "date_registered")
    private LocalDateTime dateRegistered;

    @Column(name = "gemi_number", length = 255)
    private String gemiNumber;

    @Column(name = "objective")
    private String objective;

    @Column(name = "receive_newsletter")
    private Boolean receiveNewsletter;

    @Column(name = "is_chamber_company", nullable = false)
    private Boolean isChamberCompany = true;

    @Column(name = "is_trades_company", nullable = false)
    private Boolean isTradesCompany = false;

    @Column(name = "show_business_guide", nullable = false)
    private Boolean showBusinessGuide = true;

    @Column(name = "business_location_id")
    private UUID businessLocationId;

    @Column(name = "has_active_profiles")
    private Boolean hasActiveProfiles;

    @Column(name = "is_proteas_data")
    private Boolean isProteasData;

    @Column(name = "responsible_name", length = 255)
    private String responsibleName;

    @Column(name = "address_country_id")
    private UUID addressCountryId;

    @Column(name = "address_zoom_level")
    private BigDecimal addressZoomLevel;

    @Column(name = "contact_phone3", length = 255)
    private String contactPhone3;

    @Column(name = "date_profession_started")
    private LocalDateTime dateProfessionStarted;

    @Column(name = "foundation_date")
    private LocalDateTime foundationDate;

    @Column(name = "me_criteria1_id")
    private BigDecimal meCriteria1Id;

    @Column(name = "me_criteria2_id")
    private BigDecimal meCriteria2Id;

    @Column(name = "me_criteria3_id")
    private BigDecimal meCriteria3Id;

    @Column(name = "member_dues")
    private LocalDateTime memberDues;

    @Column(name = "jb_uuid")
    private UUID jbUuid;

    @Column(name = "jb_description")
    private String jbDescription;

    @Column(name = "jb_number_employees")
    private Long jbNumberEmployees;

    @Column(name = "jb_motto", length = 255)
    private String jbMotto;

    @Column(name = "jb_email", length = 255)
    private String jbEmail;

    @Column(name = "jb_linked_in_url", length = 255)
    private String jbLinkedInUrl;

    @Column(name = "jb_facebook_url", length = 255)
    private String jbFacebookUrl;

    @Column(name = "jb_registration_status", length = 255)
    private String jbRegistrationStatus;

    @Column(name = "jb_logo_id", length = 255)
    private String jbLogoId;

    @Column(name = "jb_cover_id", length = 255)
    private String jbCoverId;

    @Column(name = "jb_location_id")
    private Integer jbLocationId;

    @Column(name = "jb_industry_id")
    private Integer jbIndustryId;

    @Column(name = "jb_isvalid")
    private Boolean jbIsvalid;

    @Column(name = "jb_activation_status", length = 255)
    private String jbActivationStatus;

}
