package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data transfer object for Company.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {

    @NotNull
    private UUID id;

    @Size(max = 255)
    private String afm;

    private BigDecimal am;

    private BigDecimal gemiId;

    @NotNull
    @Size(max = 255)
    private String coName;

    private BigDecimal chamberCompanyId;

    @NotNull
    private Integer chamberId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateInterruption;

    private BigDecimal member;

    @Size(max = 255)
    private String recType;

    private Boolean recDeleted;

    @Size(max = 255)
    private String addressCity;

    @Size(max = 255)
    private String addressLatitude;

    @Size(max = 255)
    private String addressLongitude;

    private UUID addressMunicipalityPriId;

    @Size(max = 255)
    private String addressRegion;

    @Size(max = 255)
    private String addressStreet;

    @Size(max = 255)
    private String addressStreetNumber;

    @Size(max = 255)
    private String addressZipCode;

    private BigDecimal branchTypeId;

    private UUID chamberDepartmentId;

    private BigDecimal chamberGemiResponsibleId;

    @Size(max = 255)
    private String coNameNrm;

    private UUID companyStatusId;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactEmail;

    @Size(max = 255)
    private String contactMobile;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone1;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone2;

    @Size(max = 255)
    private String contactUrl;

    private UUID corporateStatusId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateIncorporated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateRegistered;

    @Size(max = 255)
    private String gemiNumber;

    @Size(max = 255)
    private String objective;

    private Boolean receiveNewsletter;

    @NotNull
    private Boolean isChamberCompany;

    @NotNull
    private Boolean isTradesCompany;

    @NotNull
    private Boolean showBusinessGuide;

    private UUID businessLocationId;

    private Boolean hasActiveProfiles;

    private Boolean isProteasData;

    @Size(max = 255)
    private String responsibleName;

    private UUID addressCountryId;

    private BigDecimal addressZoomLevel;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone3;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateProfessionStarted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime foundationDate;

    private BigDecimal meCriteria1Id;

    private BigDecimal meCriteria2Id;

    private BigDecimal meCriteria3Id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberDues;

    private UUID jbUuid;

    @Size(max = 255)
    private String jbDescription;

    private Long jbNumberEmployees;

    @Size(max = 255)
    private String jbMotto;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String jbEmail;

    @Size(max = 255)
    private String jbLinkedInUrl;

    @Size(max = 255)
    private String jbFacebookUrl;

    @Size(max = 255)
    private String jbRegistrationStatus;

    @Size(max = 255)
    private String jbLogoId;

    @Size(max = 255)
    private String jbCoverId;

    private Integer jbLocationId;

    private Integer jbIndustryId;

    private Boolean jbIsvalid;

    @Size(max = 255)
    private String jbActivationStatus;

}
