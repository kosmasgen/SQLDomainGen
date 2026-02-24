package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "temporary_company")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version", nullable = false)
    private BigDecimal version;

    @Column(name = "address_city", length = 255)
    private String addressCity;

    @Column(name = "address_country_id")
    private BigDecimal addressCountryId;

    @Column(name = "address_latitude", length = 255)
    private String addressLatitude;

    @Column(name = "address_longitude", length = 255)
    private String addressLongitude;

    @Column(name = "address_municipality_alt", length = 255)
    private String addressMunicipalityAlt;

    @Column(name = "address_municipality_pri_id")
    private BigDecimal addressMunicipalityPriId;

    @Column(name = "address_municipality_sec_id")
    private BigDecimal addressMunicipalitySecId;

    @Column(name = "address_po_box", length = 255)
    private String addressPoBox;

    @Column(name = "address_prefecture_id")
    private BigDecimal addressPrefectureId;

    @Column(name = "address_region", length = 255)
    private String addressRegion;

    @Column(name = "address_street", length = 255)
    private String addressStreet;

    @Column(name = "address_street_number", length = 255)
    private String addressStreetNumber;

    @Column(name = "address_zip_code", length = 255)
    private String addressZipCode;

    @Column(name = "address_zoom_level")
    private BigDecimal addressZoomLevel;

    @Column(name = "address_indic_id")
    private BigDecimal addressIndicId;

    @Column(name = "afm", length = 255)
    private String afm;

    @Column(name = "am")
    private BigDecimal am;

    @Column(name = "armae", length = 255)
    private String armae;

    @Column(name = "board_dur", length = 255)
    private String boardDur;

    @Column(name = "branch_type_id")
    private BigDecimal branchTypeId;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "cancel_reason_id")
    private BigDecimal cancelReasonId;

    @Column(name = "cd", nullable = false)
    private BigDecimal cd;

    @Column(name = "chamber_department_id")
    private BigDecimal chamberDepartmentId;

    @Column(name = "chamber_gemi_responsible_id")
    private BigDecimal chamberGemiResponsibleId;

    @Column(name = "chamber_registered_id", nullable = false)
    private BigDecimal chamberRegisteredId;

    @Column(name = "co_name", length = 255, nullable = false)
    private String coName;

    @Column(name = "co_name_nrm", length = 255)
    private String coNameNrm;

    @Column(name = "comerc_reg_code")
    private BigDecimal comercRegCode;

    @Column(name = "company_status_id")
    private BigDecimal companyStatusId;

    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Column(name = "contact_facebook", length = 255)
    private String contactFacebook;

    @Column(name = "contact_fax", length = 255)
    private String contactFax;

    @Column(name = "contact_mobile", length = 255)
    private String contactMobile;

    @Column(name = "contact_phone1", length = 255)
    private String contactPhone1;

    @Column(name = "contact_phone2", length = 255)
    private String contactPhone2;

    @Column(name = "contact_phone3", length = 255)
    private String contactPhone3;

    @Column(name = "contact_phone_area", length = 255)
    private String contactPhoneArea;

    @Column(name = "contact_telex", length = 255)
    private String contactTelex;

    @Column(name = "contact_twitter", length = 255)
    private String contactTwitter;

    @Column(name = "contact_url", length = 255)
    private String contactUrl;

    @Column(name = "corporate_status_id", nullable = false)
    private BigDecimal corporateStatusId;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_gemi_registered")
    private LocalDateTime dateGemiRegistered;

    @Column(name = "date_incorporated")
    private LocalDateTime dateIncorporated;

    @Column(name = "date_profession_started")
    private LocalDateTime dateProfessionStarted;

    @Column(name = "date_registered")
    private LocalDateTime dateRegistered;

    @Column(name = "dispute_date")
    private LocalDateTime disputeDate;

    @Column(name = "dispute_dec_date")
    private LocalDateTime disputeDecDate;

    @Column(name = "dispute_number", length = 255)
    private String disputeNumber;

    @Column(name = "edra", length = 255)
    private String edra;

    @Column(name = "email2", length = 255)
    private String email2;

    @Column(name = "email3", length = 255)
    private String email3;

    @Column(name = "email4", length = 255)
    private String email4;

    @Column(name = "endfirstfy")
    private LocalDateTime endfirstfy;

    @Column(name = "eu_commerce")
    private BigDecimal euCommerce;

    @Column(name = "exp_management_dt")
    private LocalDateTime expManagementDt;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "financial_year_id")
    private BigDecimal financialYearId;

    @Column(name = "foundation_date")
    private LocalDateTime foundationDate;

    @Column(name = "gemh_other_per_cd")
    private BigDecimal gemhOtherPerCd;

    @Column(name = "gemi_number", length = 255)
    private String gemiNumber;

    @Column(name = "hp", length = 255)
    private String hp;

    @Column(name = "indefinite")
    private BigDecimal indefinite;

    @Column(name = "last_state_change_date")
    private LocalDateTime lastStateChangeDate;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "licence_exp_dt")
    private LocalDateTime licenceExpDt;

    @Column(name = "licence_no", length = 255)
    private String licenceNo;

    @Column(name = "mail_address", nullable = false)
    private BigDecimal mailAddress;

    @Column(name = "mail_name", length = 255)
    private String mailName;

    @Column(name = "me_criteria1_id")
    private BigDecimal meCriteria1Id;

    @Column(name = "me_criteria2_id")
    private BigDecimal meCriteria2Id;

    @Column(name = "member")
    private BigDecimal member;

    @Column(name = "member_dues")
    private LocalDateTime memberDues;

    @Column(name = "nationality_id")
    private BigDecimal nationalityId;

    @Column(name = "nextam")
    private BigDecimal nextam;

    @Column(name = "objective")
    private String objective;

    @Column(name = "oldam", length = 255)
    private String oldam;

    @Column(name = "pendency", length = 255)
    private String pendency;

    @Column(name = "pending")
    private BigDecimal pending;

    @Column(name = "previousam")
    private BigDecimal previousam;

    @Column(name = "rec_type", length = 255)
    private String recType;

    @Column(name = "recdeleted", nullable = false)
    private BigDecimal recdeleted;

    @Column(name = "registration_type_id")
    private BigDecimal registrationTypeId;

    @Column(name = "sale_type_id")
    private BigDecimal saleTypeId;

    @Column(name = "startfirstfy")
    private LocalDateTime startfirstfy;

    @Column(name = "subscr_cat")
    private BigDecimal subscrCat;

    @Column(name = "tax_service_id")
    private BigDecimal taxServiceId;

    @Column(name = "user_ins", length = 255)
    private String userIns;

    @Column(name = "user_last_upd", length = 255)
    private String userLastUpd;

    @Column(name = "vote_department_id")
    private BigDecimal voteDepartmentId;

    @Column(name = "votes")
    private BigDecimal votes;

    @Column(name = "management_dur", length = 255)
    private String managementDur;

    @Column(name = "receive_newsletter")
    private BigDecimal receiveNewsletter;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

    @Column(name = "gemi_last_state_change_date")
    private LocalDate gemiLastStateChangeDate;

    @Column(name = "gemi_parent_gemi_number", length = 255)
    private String gemiParentGemiNumber;

    @Column(name = "gemi_municipality_id")
    private BigDecimal gemiMunicipalityId;

    @Column(name = "gemi_city", length = 255)
    private String gemiCity;

    @Column(name = "gemi_region", length = 255)
    private String gemiRegion;

    @Column(name = "gemi_street", length = 255)
    private String gemiStreet;

    @Column(name = "gemi_street_number", length = 255)
    private String gemiStreetNumber;

    @Column(name = "gemi_zip_code", length = 255)
    private String gemiZipCode;

    @Column(name = "gemi_phone1", length = 255)
    private String gemiPhone1;

    @Column(name = "gemi_phone2", length = 255)
    private String gemiPhone2;

    @Column(name = "gemi_phone3", length = 255)
    private String gemiPhone3;

    @Column(name = "gemi_mobile", length = 255)
    private String gemiMobile;

    @Column(name = "gemi_fax", length = 255)
    private String gemiFax;

    @Column(name = "gemi_email", length = 255)
    private String gemiEmail;

    @Column(name = "gemi_created", nullable = false)
    private BigDecimal gemiCreated;

    @Column(name = "gemi_id")
    private BigDecimal gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "article")
    private String article;

    @Column(name = "show_email")
    private BigDecimal showEmail;

    @Column(name = "gemi_id2")
    private BigDecimal gemiId2;

    @Column(name = "vote_dt")
    private LocalDate voteDt;

    @Column(name = "vote_flag")
    private String voteFlag;

    @Column(name = "vote_etairia_flag")
    private String voteEtairiaFlag;

    @Column(name = "gemi_date_incorporated")
    private LocalDateTime gemiDateIncorporated;

    @Column(name = "me_criteria3_id")
    private BigDecimal meCriteria3Id;

    @Column(name = "date_interruption")
    private LocalDateTime dateInterruption;

    @Column(name = "cancel_reason_dscr", length = 255)
    private String cancelReasonDscr;

    @Column(name = "bankrupt_date")
    private LocalDateTime bankruptDate;

    @Column(name = "start_dt_corp_status")
    private LocalDateTime startDtCorpStatus;

    @Column(name = "end_dt_corp_status")
    private LocalDateTime endDtCorpStatus;

    @Column(name = "bankrupt_number", length = 255)
    private String bankruptNumber;

    @Column(name = "last_change_date")
    private LocalDateTime lastChangeDate;

    @Column(name = "next_company_id")
    private BigDecimal nextCompanyId;

    @Column(name = "parent_company_id")
    private BigDecimal parentCompanyId;

    @Column(name = "previous_company_id")
    private BigDecimal previousCompanyId;

    @Column(name = "transfer_flag")
    private BigDecimal transferFlag;

    @Column(name = "transfer_am")
    private BigDecimal transferAm;

    @Column(name = "proeg_occupation_id")
    private BigDecimal proegOccupationId;

    @Column(name = "proeg_subscr_amnt")
    private BigDecimal proegSubscrAmnt;

    @Column(name = "proeg_subscr_year", length = 255)
    private String proegSubscrYear;

    @Column(name = "proeg_subscr_date")
    private LocalDateTime proegSubscrDate;

    @Column(name = "proeg_subscr_notes", length = 255)
    private String proegSubscrNotes;

    @Column(name = "migr_capitol")
    private BigDecimal migrCapitol;

    @Column(name = "migr_capitol2")
    private BigDecimal migrCapitol2;

    @Column(name = "migr_many_children_flag", length = 255)
    private String migrManyChildrenFlag;

    @Column(name = "migr_amea_flag", length = 255)
    private String migrAmeaFlag;

    @Column(name = "migr_ypokat_flag", length = 255)
    private String migrYpokatFlag;

    @Column(name = "migr_thrasher_flag", length = 255)
    private String migrThrasherFlag;

    @Column(name = "migr_low_capital_flag", length = 255)
    private String migrLowCapitalFlag;

    @Column(name = "migr_send_tax_serv_flag", length = 255)
    private String migrSendTaxServFlag;

    @Column(name = "print_katast_flag", nullable = false)
    private BigDecimal printKatastFlag;

    @Column(name = "subscr_calc_date", length = 255)
    private String subscrCalcDate;

    @Column(name = "show_business_guide", nullable = false)
    private BigDecimal showBusinessGuide;

}
