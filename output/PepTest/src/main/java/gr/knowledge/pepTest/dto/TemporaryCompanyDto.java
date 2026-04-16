package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.math.BigDecimal;

/**
 * Data transfer object for TemporaryCompany.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemporaryCompanyDto {

    private BigInteger id;

    @NotNull
    private BigInteger version;

    @Size(max = 50)
    private String addressCity;

    private BigInteger addressCountryId;

    @Size(max = 255)
    private String addressLatitude;

    @Size(max = 255)
    private String addressLongitude;

    @Size(max = 255)
    private String addressMunicipalityAlt;

    private BigInteger addressMunicipalityPriId;

    private BigInteger addressMunicipalitySecId;

    @Size(max = 255)
    private String addressPoBox;

    private BigInteger addressPrefectureId;

    @Size(max = 50)
    private String addressRegion;

    @Size(max = 255)
    private String addressStreet;

    @Size(max = 255)
    private String addressStreetNumber;

    @Size(max = 12)
    private String addressZipCode;

    private BigInteger addressZoomLevel;

    private BigInteger addressIndicId;

    @Size(max = 9)
    private String afm;

    private BigInteger am;

    @Size(max = 50)
    private String armae;

    @Size(max = 15)
    private String boardDur;

    private BigInteger branchTypeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cancelDate;

    private BigInteger cancelReasonId;

    @NotNull
    private BigInteger cd;

    private BigInteger chamberDepartmentId;

    private BigInteger chamberGemiResponsibleId;

    @NotNull
    private BigInteger chamberRegisteredId;

    @NotNull
    @Size(max = 1000)
    private String coName;

    @Size(max = 1000)
    private String coNameNrm;

    private BigInteger comercRegCode;

    private BigInteger companyStatusId;

    @Size(max = 100)
    private String contactEmail;

    @Size(max = 255)
    private String contactFacebook;

    @Size(max = 20)
    private String contactFax;

    @Size(max = 50)
    private String contactMobile;

    @Size(max = 20)
    private String contactPhone1;

    @Size(max = 20)
    private String contactPhone2;

    @Size(max = 20)
    private String contactPhone3;

    @Size(max = 15)
    private String contactPhoneArea;

    @Size(max = 50)
    private String contactTelex;

    @Size(max = 255)
    private String contactTwitter;

    @Size(max = 256)
    private String contactUrl;

    @NotNull
    private BigInteger corporateStatusId;

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

    @Size(max = 30)
    private String disputeNumber;

    @Size(max = 30)
    private String edra;

    @Size(max = 100)
    private String email2;

    @Size(max = 100)
    private String email3;

    @Size(max = 100)
    private String email4;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endfirstfy;

    private BigInteger euCommerce;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expManagementDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireDate;

    private BigInteger financialYearId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime foundationDate;

    private BigInteger gemhOtherPerCd;

    @Size(max = 12)
    private String gemiNumber;

    @Size(max = 8)
    private String hp;

    private BigInteger indefinite;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastStateChangeDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime licenceExpDt;

    @Size(max = 25)
    private String licenceNo;

    @NotNull
    private BigInteger mailAddress;

    @Size(max = 60)
    private String mailName;

    private BigInteger meCriteria1Id;

    private BigInteger meCriteria2Id;

    private BigInteger member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberDues;

    private BigInteger nationalityId;

    private BigInteger nextam;

    private String objective;

    @Size(max = 10)
    private String oldam;

    @Size(max = 500)
    private String pendency;

    private BigInteger pending;

    private BigInteger previousam;

    @Size(max = 1)
    private String recType;

    @NotNull
    private BigInteger recdeleted;

    private BigInteger registrationTypeId;

    private BigInteger saleTypeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startfirstfy;

    private BigInteger subscrCat;

    private BigInteger taxServiceId;

    @Size(max = 255)
    private String userIns;

    @Size(max = 255)
    private String userLastUpd;

    private BigInteger voteDepartmentId;

    private BigInteger votes;

    @Size(max = 15)
    private String managementDur;

    private BigInteger receiveNewsletter;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiLastStateChangeDate;

    @Size(max = 12)
    private String gemiParentGemiNumber;

    private BigInteger gemiMunicipalityId;

    @Size(max = 50)
    private String gemiCity;

    @Size(max = 50)
    private String gemiRegion;

    @Size(max = 255)
    private String gemiStreet;

    @Size(max = 255)
    private String gemiStreetNumber;

    @Size(max = 12)
    private String gemiZipCode;

    @Size(max = 120)
    private String gemiPhone1;

    @Size(max = 15)
    private String gemiPhone2;

    @Size(max = 15)
    private String gemiPhone3;

    @Size(max = 50)
    private String gemiMobile;

    @Size(max = 120)
    private String gemiFax;

    @Size(max = 100)
    private String gemiEmail;

    @NotNull
    private BigInteger gemiCreated;

    private BigInteger gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gemiDateCreated;

    private String article;

    private BigInteger showEmail;

    private BigInteger gemiId2;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate voteDt;

    private Character voteFlag;

    private Character voteEtairiaFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gemiDateIncorporated;

    private BigInteger meCriteria3Id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateInterruption;

    @Size(max = 300)
    private String cancelReasonDscr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bankruptDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDtCorpStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDtCorpStatus;

    @Size(max = 30)
    private String bankruptNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastChangeDate;

    private BigInteger nextCompanyId;

    private BigInteger parentCompanyId;

    private BigInteger previousCompanyId;

    private BigInteger transferFlag;

    private BigInteger transferAm;

    private BigInteger proegOccupationId;

    private BigDecimal proegSubscrAmnt;

    @Size(max = 4)
    private String proegSubscrYear;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime proegSubscrDate;

    @Size(max = 300)
    private String proegSubscrNotes;

    private BigInteger migrCapitol;

    private BigDecimal migrCapitol2;

    @Size(max = 1)
    private String migrManyChildrenFlag;

    @Size(max = 1)
    private String migrAmeaFlag;

    @Size(max = 1)
    private String migrYpokatFlag;

    @Size(max = 1)
    private String migrThrasherFlag;

    @Size(max = 1)
    private String migrLowCapitalFlag;

    @Size(max = 1)
    private String migrSendTaxServFlag;

    @NotNull
    private BigInteger printKatastFlag;

    @Size(max = 255)
    private String subscrCalcDate;

    @NotNull
    private BigInteger showBusinessGuide;

    private Integer field7060;

}
