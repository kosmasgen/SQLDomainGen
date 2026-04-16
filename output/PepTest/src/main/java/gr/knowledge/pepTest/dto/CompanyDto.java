package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import jakarta.validation.constraints.Size;
import java.math.BigInteger;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.dto.CountryDto;

/**
 * Data transfer object for Company.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {

    private UUID id;

    @Size(max = 9)
    private String afm;

    private BigInteger am;

    private BigInteger gemiId;

    @NotNull
    @Size(max = 1000)
    private String coName;

    private BigInteger chamberCompanyId;

    @NotNull
    private Integer chamberId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateInterruption;

    private BigInteger member;

    @Size(max = 1)
    private String recType;

    private Boolean recdeleted;

    @Size(max = 50)
    private String addressCity;

    @Size(max = 255)
    private String addressLatitude;

    @Size(max = 255)
    private String addressLongitude;

    private MunicipalityDto addressMunicipalityPri;

    @Size(max = 50)
    private String addressRegion;

    @Size(max = 255)
    private String addressStreet;

    @Size(max = 255)
    private String addressStreetNumber;

    @Size(max = 12)
    private String addressZipCode;

    private BigInteger branchTypeId;

    private ChamberDepartmentDto chamberDepartment;

    private BigInteger chamberGemiResponsibleId;

    @Size(max = 1000)
    private String coNameNrm;

    private CompanyStatusDto companyStatus;

    @Size(max = 100)
    private String contactEmail;

    @Size(max = 50)
    private String contactMobile;

    @Size(max = 20)
    private String contactPhone1;

    @Size(max = 20)
    private String contactPhone2;

    @Size(max = 100)
    private String contactUrl;

    private CorporateStatusDto corporateStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateIncorporated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateRegistered;

    @Size(max = 12)
    private String gemiNumber;

    private String objective;

    private Boolean receiveNewsletter;

    @NotNull
    private Boolean isChamberCompany;

    @NotNull
    private Boolean isTradesCompany;

    @NotNull
    private Boolean showBusinessGuide;

    private BusinessLocationDto businessLocation;

    private Boolean hasActiveProfiles;

    private Boolean isProteasData;

    @Size(max = 255)
    private String responsibleName;

    private CountryDto addressCountry;

    private BigInteger addressZoomLevel;

    @Size(max = 20)
    private String contactPhone3;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateProfessionStarted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime foundationDate;

    private BigInteger meCriteria1Id;

    private BigInteger meCriteria2Id;

    private BigInteger meCriteria3Id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberDues;

    private UUID jbUuid;

    private String jbDescription;

    private Long jbNumberEmployees;

    @Size(max = 255)
    private String jbMotto;

    @Size(max = 255)
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

    private String jbActivationStatus;

}
