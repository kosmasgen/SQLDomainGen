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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data transfer object for TemporaryCompany.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporaryCompanyDto {

    @NotNull
    private Long id;

    @NotNull
    private BigDecimal version;

    @Size(max = 255)
    private String addressCity;

    private BigDecimal addressCountryId;

    @Size(max = 255)
    private String addressLatitude;

    @Size(max = 255)
    private String addressLongitude;

    @Size(max = 255)
    private String addressMunicipalityAlt;

    private BigDecimal addressMunicipalityPriId;

    private BigDecimal addressMunicipalitySecId;

    @Size(max = 255)
    private String addressPoBox;

    private BigDecimal addressPrefectureId;

    @Size(max = 255)
    private String addressRegion;

    @Size(max = 255)
    private String addressStreet;

    @Size(max = 255)
    private String addressStreetNumber;

    @Size(max = 255)
    private String addressZipCode;

    private BigDecimal addressZoomLevel;

    private BigDecimal addressIndicId;

    @Size(max = 255)
    private String afm;

    private BigDecimal am;

    @Size(max = 255)
    private String armae;

    @Size(max = 255)
    private String boardDur;

    private BigDecimal branchTypeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelDate;

    private BigDecimal cancelReasonId;

    @NotNull
    private BigDecimal cd;

    private BigDecimal chamberDepartmentId;

    private BigDecimal chamberGemiResponsibleId;

    @NotNull
    private BigDecimal chamberRegisteredId;

    @NotNull
    @Size(max = 255)
    private String coName;

    @Size(max = 255)
    private String coNameNrm;

    private BigDecimal comercRegCode;

    private BigDecimal companyStatusId;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactEmail;

    @Size(max = 255)
    private String contactFacebook;

    @Size(max = 255)
    private String contactFax;

    @Size(max = 255)
    private String contactMobile;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone1;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone2;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone3;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhoneArea;

    @Size(max = 255)
    private String contactTelex;

    @Size(max = 255)
    private String contactTwitter;

    @Size(max = 255)
    private String contactUrl;

    @NotNull
    private BigDecimal corporateStatusId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateGemiRegistered;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateIncorporated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateProfessionStarted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateRegistered;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disputeDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime disputeDecDate;

    @Size(max = 255)
    private String disputeNumber;

    @Size(max = 255)
    private String edra;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String email2;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String email3;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String email4;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endfirstfy;

    private BigDecimal euCommerce;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expManagementDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireDate;

    private BigDecimal financialYearId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime foundationDate;

    private BigDecimal gemhOtherPerCd;

    @Size(max = 255)
    private String gemiNumber;

    @Size(max = 255)
    private String hp;

    private BigDecimal indefinite;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastStateChangeDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime licenceExpDt;

    @Size(max = 255)
    private String licenceNo;

    @NotNull
    private BigDecimal mailAddress;

    @Size(max = 255)
    private String mailName;

    private BigDecimal meCriteria1Id;

    private BigDecimal meCriteria2Id;

    private BigDecimal member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberDues;

    private BigDecimal nationalityId;

    private BigDecimal nextam;

    @Size(max = 255)
    private String objective;

    @Size(max = 255)
    private String oldam;

    @Size(max = 255)
    private String pendency;

    private BigDecimal pending;

    private BigDecimal previousam;

    @Size(max = 255)
    private String recType;

    @NotNull
    private BigDecimal recdeleted;

    private BigDecimal registrationTypeId;

    private BigDecimal saleTypeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startfirstfy;

    private BigDecimal subscrCat;

    private BigDecimal taxServiceId;

    @Size(max = 255)
    private String userIns;

    @Size(max = 255)
    private String userLastUpd;

    private BigDecimal voteDepartmentId;

    private BigDecimal votes;

    @Size(max = 255)
    private String managementDur;

    private BigDecimal receiveNewsletter;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastStateChangeDate;

    @Size(max = 255)
    private String gemiParentGemiNumber;

    private BigDecimal gemiMunicipalityId;

    @Size(max = 255)
    private String gemiCity;

    @Size(max = 255)
    private String gemiRegion;

    @Size(max = 255)
    private String gemiStreet;

    @Size(max = 255)
    private String gemiStreetNumber;

    @Size(max = 255)
    private String gemiZipCode;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String gemiPhone1;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String gemiPhone2;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String gemiPhone3;

    @Size(max = 255)
    private String gemiMobile;

    @Size(max = 255)
    private String gemiFax;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String gemiEmail;

    @NotNull
    private BigDecimal gemiCreated;

    private BigDecimal gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiDateCreated;

    @Size(max = 255)
    private String article;

    private BigDecimal showEmail;

    private BigDecimal gemiId2;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate voteDt;

    @Size(max = 255)
    private String voteFlag;

    @Size(max = 255)
    private String voteEtairiaFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gemiDateIncorporated;

    private BigDecimal meCriteria3Id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateInterruption;

    @Size(max = 255)
    private String cancelReasonDscr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bankruptDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDtCorpStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDtCorpStatus;

    @Size(max = 255)
    private String bankruptNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastChangeDate;

    private BigDecimal nextCompanyId;

    private BigDecimal parentCompanyId;

    private BigDecimal previousCompanyId;

    private BigDecimal transferFlag;

    private BigDecimal transferAm;

    private BigDecimal proegOccupationId;

    private BigDecimal proegSubscrAmnt;

    @Size(max = 255)
    private String proegSubscrYear;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime proegSubscrDate;

    @Size(max = 255)
    private String proegSubscrNotes;

    private BigDecimal migrCapitol;

    private BigDecimal migrCapitol2;

    @Size(max = 255)
    private String migrManyChildrenFlag;

    @Size(max = 255)
    private String migrAmeaFlag;

    @Size(max = 255)
    private String migrYpokatFlag;

    @Size(max = 255)
    private String migrThrasherFlag;

    @Size(max = 255)
    private String migrLowCapitalFlag;

    @Size(max = 255)
    private String migrSendTaxServFlag;

    @NotNull
    private BigDecimal printKatastFlag;

    @Size(max = 255)
    private String subscrCalcDate;

    @NotNull
    private BigDecimal showBusinessGuide;

}
