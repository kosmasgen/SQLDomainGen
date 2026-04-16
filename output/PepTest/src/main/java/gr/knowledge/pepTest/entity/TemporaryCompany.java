package gr.knowledge.pepTest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "temporary_company")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryCompany {

    @Id
    @Column(name = "id", precision = 19, nullable = false)
    private BigInteger id;

    @Column(name = "version", precision = 19, nullable = false)
    private BigInteger version;

    @Column(name = "address_city", length = 50)
    private String addressCity;

    @Column(name = "address_country_id", precision = 19)
    private BigInteger addressCountryId;

    @Column(name = "address_latitude")
    private String addressLatitude;

    @Column(name = "address_longitude")
    private String addressLongitude;

    @Column(name = "address_municipality_alt")
    private String addressMunicipalityAlt;

    @Column(name = "address_municipality_pri_id", precision = 19)
    private BigInteger addressMunicipalityPriId;

    @Column(name = "address_municipality_sec_id", precision = 19)
    private BigInteger addressMunicipalitySecId;

    @Column(name = "address_po_box")
    private String addressPoBox;

    @Column(name = "address_prefecture_id", precision = 19)
    private BigInteger addressPrefectureId;

    @Column(name = "address_region", length = 50)
    private String addressRegion;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_street_number")
    private String addressStreetNumber;

    @Column(name = "address_zip_code", length = 12)
    private String addressZipCode;

    @Column(name = "address_zoom_level", precision = 10)
    private BigInteger addressZoomLevel;

    @Column(name = "address_indic_id", precision = 19)
    private BigInteger addressIndicId;

    @Column(name = "afm", length = 9)
    private String afm;

    @Column(name = "am", precision = 19)
    private BigInteger am;

    @Column(name = "armae", length = 50)
    private String armae;

    @Column(name = "board_dur", length = 15)
    private String boardDur;

    @Column(name = "branch_type_id", precision = 19)
    private BigInteger branchTypeId;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "cancel_reason_id", precision = 19)
    private BigInteger cancelReasonId;

    @Column(name = "cd", precision = 19, nullable = false)
    private BigInteger cd;

    @Column(name = "chamber_department_id", precision = 19)
    private BigInteger chamberDepartmentId;

    @Column(name = "chamber_gemi_responsible_id", precision = 19)
    private BigInteger chamberGemiResponsibleId;

    @Column(name = "chamber_registered_id", nullable = false)
    private BigInteger chamberRegisteredId;

    @Column(name = "co_name", length = 1000, nullable = false)
    private String coName;

    @Column(name = "co_name_nrm", length = 1000)
    private String coNameNrm;

    @Column(name = "comerc_reg_code", precision = 19)
    private BigInteger comercRegCode;

    @Column(name = "company_status_id", precision = 19)
    private BigInteger companyStatusId;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "contact_facebook")
    private String contactFacebook;

    @Column(name = "contact_fax", length = 20)
    private String contactFax;

    @Column(name = "contact_mobile", length = 50)
    private String contactMobile;

    @Column(name = "contact_phone1", length = 20)
    private String contactPhone1;

    @Column(name = "contact_phone2", length = 20)
    private String contactPhone2;

    @Column(name = "contact_phone3", length = 20)
    private String contactPhone3;

    @Column(name = "contact_phone_area", length = 15)
    private String contactPhoneArea;

    @Column(name = "contact_telex", length = 50)
    private String contactTelex;

    @Column(name = "contact_twitter")
    private String contactTwitter;

    @Column(name = "contact_url", length = 256)
    private String contactUrl;

    @Column(name = "corporate_status_id", precision = 19, nullable = false)
    private BigInteger corporateStatusId;

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

    @Column(name = "dispute_number", length = 30)
    private String disputeNumber;

    @Column(name = "edra", length = 30)
    private String edra;

    @Column(name = "email2", length = 100)
    private String email2;

    @Column(name = "email3", length = 100)
    private String email3;

    @Column(name = "email4", length = 100)
    private String email4;

    @Column(name = "endfirstfy")
    private LocalDateTime endfirstfy;

    @Column(name = "eu_commerce", precision = 1)
    private BigInteger euCommerce;

    @Column(name = "exp_management_dt")
    private LocalDateTime expManagementDt;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "financial_year_id", precision = 19)
    private BigInteger financialYearId;

    @Column(name = "foundation_date")
    private LocalDateTime foundationDate;

    @Column(name = "gemh_other_per_cd", precision = 19)
    private BigInteger gemhOtherPerCd;

    @Column(name = "gemi_number", length = 12)
    private String gemiNumber;

    @Column(name = "hp", length = 8)
    private String hp;

    @Column(name = "indefinite", precision = 1)
    private BigInteger indefinite;

    @Column(name = "last_state_change_date")
    private LocalDateTime lastStateChangeDate;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "licence_exp_dt")
    private LocalDateTime licenceExpDt;

    @Column(name = "licence_no", length = 25)
    private String licenceNo;

    @Column(name = "mail_address", precision = 1, nullable = false)
    private BigInteger mailAddress;

    @Column(name = "mail_name", length = 60)
    private String mailName;

    @Column(name = "me_criteria1_id", precision = 19)
    private BigInteger meCriteria1Id;

    @Column(name = "me_criteria2_id", precision = 19)
    private BigInteger meCriteria2Id;

    @Column(name = "member", precision = 1)
    private BigInteger member;

    @Column(name = "member_dues")
    private LocalDateTime memberDues;

    @Column(name = "nationality_id", precision = 19)
    private BigInteger nationalityId;

    @Column(name = "nextam", precision = 19)
    private BigInteger nextam;

    @Column(name = "objective", columnDefinition = "text")
    private String objective;

    @Column(name = "oldam", length = 10)
    private String oldam;

    @Column(name = "pendency", length = 500)
    private String pendency;

    @Column(name = "pending", precision = 1)
    private BigInteger pending;

    @Column(name = "previousam", precision = 19)
    private BigInteger previousam;

    @Column(name = "rec_type", length = 1)
    private String recType;

    @Column(name = "recdeleted", precision = 19, nullable = false)
    private BigInteger recdeleted;

    @Column(name = "registration_type_id", precision = 19)
    private BigInteger registrationTypeId;

    @Column(name = "sale_type_id", precision = 19)
    private BigInteger saleTypeId;

    @Column(name = "startfirstfy")
    private LocalDateTime startfirstfy;

    @Column(name = "subscr_cat", precision = 19)
    private BigInteger subscrCat;

    @Column(name = "tax_service_id", precision = 19)
    private BigInteger taxServiceId;

    @Column(name = "user_ins")
    private String userIns;

    @Column(name = "user_last_upd")
    private String userLastUpd;

    @Column(name = "vote_department_id", precision = 19)
    private BigInteger voteDepartmentId;

    @Column(name = "votes", precision = 19)
    private BigInteger votes;

    @Column(name = "management_dur", length = 15)
    private String managementDur;

    @Column(name = "receive_newsletter", precision = 1)
    private BigInteger receiveNewsletter;

    @Column(name = "gemi_last_updated")
    private LocalDate gemiLastUpdated;

    @Column(name = "gemi_last_state_change_date")
    private LocalDate gemiLastStateChangeDate;

    @Column(name = "gemi_parent_gemi_number", length = 12)
    private String gemiParentGemiNumber;

    @Column(name = "gemi_municipality_id", precision = 19)
    private BigInteger gemiMunicipalityId;

    @Column(name = "gemi_city", length = 50)
    private String gemiCity;

    @Column(name = "gemi_region", length = 50)
    private String gemiRegion;

    @Column(name = "gemi_street")
    private String gemiStreet;

    @Column(name = "gemi_street_number")
    private String gemiStreetNumber;

    @Column(name = "gemi_zip_code", length = 12)
    private String gemiZipCode;

    @Column(name = "gemi_phone1", length = 120)
    private String gemiPhone1;

    @Column(name = "gemi_phone2", length = 15)
    private String gemiPhone2;

    @Column(name = "gemi_phone3", length = 15)
    private String gemiPhone3;

    @Column(name = "gemi_mobile", length = 50)
    private String gemiMobile;

    @Column(name = "gemi_fax", length = 120)
    private String gemiFax;

    @Column(name = "gemi_email", length = 100)
    private String gemiEmail;

    @Column(name = "gemi_created", precision = 1, nullable = false)
    private BigInteger gemiCreated;

    @Column(name = "gemi_id", precision = 19)
    private BigInteger gemiId;

    @Column(name = "gemi_date_created")
    private LocalDate gemiDateCreated;

    @Column(name = "article", columnDefinition = "text")
    private String article;

    @Column(name = "show_email", precision = 1)
    private BigInteger showEmail;

    @Column(name = "gemi_id2", precision = 19)
    private BigInteger gemiId2;

    @Column(name = "vote_dt")
    private LocalDate voteDt;

    @Column(name = "vote_flag", columnDefinition = "char(1)")
    private Character voteFlag;

    @Column(name = "vote_etairia_flag", columnDefinition = "char(1)")
    private Character voteEtairiaFlag;

    @Column(name = "gemi_date_incorporated")
    private LocalDateTime gemiDateIncorporated;

    @Column(name = "me_criteria3_id", precision = 19)
    private BigInteger meCriteria3Id;

    @Column(name = "date_interruption")
    private LocalDateTime dateInterruption;

    @Column(name = "cancel_reason_dscr", length = 300)
    private String cancelReasonDscr;

    @Column(name = "bankrupt_date")
    private LocalDateTime bankruptDate;

    @Column(name = "start_dt_corp_status")
    private LocalDateTime startDtCorpStatus;

    @Column(name = "end_dt_corp_status")
    private LocalDateTime endDtCorpStatus;

    @Column(name = "bankrupt_number", length = 30)
    private String bankruptNumber;

    @Column(name = "last_change_date")
    private LocalDateTime lastChangeDate;

    @Column(name = "next_company_id", precision = 19)
    private BigInteger nextCompanyId;

    @Column(name = "parent_company_id", precision = 19)
    private BigInteger parentCompanyId;

    @Column(name = "previous_company_id", precision = 19)
    private BigInteger previousCompanyId;

    @Column(name = "transfer_flag", precision = 1)
    private BigInteger transferFlag;

    @Column(name = "transfer_am", precision = 19)
    private BigInteger transferAm;

    @Column(name = "proeg_occupation_id", precision = 19)
    private BigInteger proegOccupationId;

    @Column(name = "proeg_subscr_amnt", precision = 19, scale = 2)
    private BigDecimal proegSubscrAmnt;

    @Column(name = "proeg_subscr_year", length = 4)
    private String proegSubscrYear;

    @Column(name = "proeg_subscr_date")
    private LocalDateTime proegSubscrDate;

    @Column(name = "proeg_subscr_notes", length = 300)
    private String proegSubscrNotes;

    @Column(name = "migr_capitol")
    private BigInteger migrCapitol;

    @Column(name = "migr_capitol2", precision = 19, scale = 2)
    private BigDecimal migrCapitol2;

    @Column(name = "migr_many_children_flag", length = 1)
    private String migrManyChildrenFlag;

    @Column(name = "migr_amea_flag", length = 1)
    private String migrAmeaFlag;

    @Column(name = "migr_ypokat_flag", length = 1)
    private String migrYpokatFlag;

    @Column(name = "migr_thrasher_flag", length = 1)
    private String migrThrasherFlag;

    @Column(name = "migr_low_capital_flag", length = 1)
    private String migrLowCapitalFlag;

    @Column(name = "migr_send_tax_serv_flag", length = 1)
    private String migrSendTaxServFlag;

    @Column(name = "print_katast_flag", precision = 1, nullable = false)
    private BigInteger printKatastFlag;

    @Column(name = "subscr_calc_date")
    private String subscrCalcDate;

    @Column(name = "show_business_guide", precision = 1, nullable = false)
    private BigInteger showBusinessGuide;

    @Column(name = "7060")
    private Integer field7060;

}
