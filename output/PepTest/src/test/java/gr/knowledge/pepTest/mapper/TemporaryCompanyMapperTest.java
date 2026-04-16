package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.entity.TemporaryCompany;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class TemporaryCompanyMapperTest {

    private TemporaryCompanyMapper temporaryCompanyMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        temporaryCompanyMapper = new TemporaryCompanyMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyToTemporaryCompanyDto() {
        TemporaryCompany entity = createSampleTemporaryCompanyEntity();
        TemporaryCompanyDto expectedDto = createSampleTemporaryCompanyDto();

        TemporaryCompanyDto actualDto = temporaryCompanyMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyDtoToTemporaryCompany() {
        TemporaryCompanyDto dto = createSampleTemporaryCompanyDto();
        TemporaryCompany expectedEntity = createSampleTemporaryCompanyEntity();

        TemporaryCompany actualEntity = temporaryCompanyMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyListToTemporaryCompanyDtoList() {
        List<TemporaryCompany> entityList = List.of(
                createSampleTemporaryCompanyEntity(),
                createAnotherTemporaryCompanyEntity()
        );
        List<TemporaryCompanyDto> expectedDtoList = List.of(
                createSampleTemporaryCompanyDto(),
                createAnotherTemporaryCompanyDto()
        );

        List<TemporaryCompanyDto> actualDtoList = temporaryCompanyMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyDtoListToTemporaryCompanyList() {
        List<TemporaryCompanyDto> dtoList = List.of(
                createSampleTemporaryCompanyDto(),
                createAnotherTemporaryCompanyDto()
        );
        List<TemporaryCompany> expectedEntityList = List.of(
                createSampleTemporaryCompanyEntity(),
                createAnotherTemporaryCompanyEntity()
        );

        List<TemporaryCompany> actualEntityList = temporaryCompanyMapper.toEntityList(dtoList);

        assertThat(actualEntityList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedEntityList);
    }

    /**
     * Verifies that partialUpdate replaces every non-null mapped field,
     * preserves null-patched fields from the original entity,
     * and never changes primary key fields.
     */
    @Test
    void shouldApplyFullPartialUpdateForTemporaryCompany() {
        TemporaryCompany originalEntity = createSampleTemporaryCompanyEntity();
        TemporaryCompany actualEntity = createSampleTemporaryCompanyEntity();
        TemporaryCompanyDto patchDto = createPatchTemporaryCompanyDto();
        TemporaryCompany patchEntity = temporaryCompanyMapper.toEntity(patchDto);

        temporaryCompanyMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object versionExpectedValue = patchEntity.getVersion() != null ? patchEntity.getVersion() : originalEntity.getVersion();
        assertThat(actualEntity.getVersion())
                .isEqualTo(versionExpectedValue);

        Object addressCityExpectedValue = patchEntity.getAddressCity() != null ? patchEntity.getAddressCity() : originalEntity.getAddressCity();
        assertThat(actualEntity.getAddressCity())
                .isEqualTo(addressCityExpectedValue);

        Object addressCountryIdExpectedValue = patchEntity.getAddressCountryId() != null ? patchEntity.getAddressCountryId() : originalEntity.getAddressCountryId();
        assertThat(actualEntity.getAddressCountryId())
                .isEqualTo(addressCountryIdExpectedValue);

        Object addressLatitudeExpectedValue = patchEntity.getAddressLatitude() != null ? patchEntity.getAddressLatitude() : originalEntity.getAddressLatitude();
        assertThat(actualEntity.getAddressLatitude())
                .isEqualTo(addressLatitudeExpectedValue);

        Object addressLongitudeExpectedValue = patchEntity.getAddressLongitude() != null ? patchEntity.getAddressLongitude() : originalEntity.getAddressLongitude();
        assertThat(actualEntity.getAddressLongitude())
                .isEqualTo(addressLongitudeExpectedValue);

        Object addressMunicipalityAltExpectedValue = patchEntity.getAddressMunicipalityAlt() != null ? patchEntity.getAddressMunicipalityAlt() : originalEntity.getAddressMunicipalityAlt();
        assertThat(actualEntity.getAddressMunicipalityAlt())
                .isEqualTo(addressMunicipalityAltExpectedValue);

        Object addressMunicipalityPriIdExpectedValue = patchEntity.getAddressMunicipalityPriId() != null ? patchEntity.getAddressMunicipalityPriId() : originalEntity.getAddressMunicipalityPriId();
        assertThat(actualEntity.getAddressMunicipalityPriId())
                .isEqualTo(addressMunicipalityPriIdExpectedValue);

        Object addressMunicipalitySecIdExpectedValue = patchEntity.getAddressMunicipalitySecId() != null ? patchEntity.getAddressMunicipalitySecId() : originalEntity.getAddressMunicipalitySecId();
        assertThat(actualEntity.getAddressMunicipalitySecId())
                .isEqualTo(addressMunicipalitySecIdExpectedValue);

        Object addressPoBoxExpectedValue = patchEntity.getAddressPoBox() != null ? patchEntity.getAddressPoBox() : originalEntity.getAddressPoBox();
        assertThat(actualEntity.getAddressPoBox())
                .isEqualTo(addressPoBoxExpectedValue);

        Object addressPrefectureIdExpectedValue = patchEntity.getAddressPrefectureId() != null ? patchEntity.getAddressPrefectureId() : originalEntity.getAddressPrefectureId();
        assertThat(actualEntity.getAddressPrefectureId())
                .isEqualTo(addressPrefectureIdExpectedValue);

        Object addressRegionExpectedValue = patchEntity.getAddressRegion() != null ? patchEntity.getAddressRegion() : originalEntity.getAddressRegion();
        assertThat(actualEntity.getAddressRegion())
                .isEqualTo(addressRegionExpectedValue);

        Object addressStreetExpectedValue = patchEntity.getAddressStreet() != null ? patchEntity.getAddressStreet() : originalEntity.getAddressStreet();
        assertThat(actualEntity.getAddressStreet())
                .isEqualTo(addressStreetExpectedValue);

        Object addressStreetNumberExpectedValue = patchEntity.getAddressStreetNumber() != null ? patchEntity.getAddressStreetNumber() : originalEntity.getAddressStreetNumber();
        assertThat(actualEntity.getAddressStreetNumber())
                .isEqualTo(addressStreetNumberExpectedValue);

        Object addressZipCodeExpectedValue = patchEntity.getAddressZipCode() != null ? patchEntity.getAddressZipCode() : originalEntity.getAddressZipCode();
        assertThat(actualEntity.getAddressZipCode())
                .isEqualTo(addressZipCodeExpectedValue);

        Object addressZoomLevelExpectedValue = patchEntity.getAddressZoomLevel() != null ? patchEntity.getAddressZoomLevel() : originalEntity.getAddressZoomLevel();
        assertThat(actualEntity.getAddressZoomLevel())
                .isEqualTo(addressZoomLevelExpectedValue);

        Object addressIndicIdExpectedValue = patchEntity.getAddressIndicId() != null ? patchEntity.getAddressIndicId() : originalEntity.getAddressIndicId();
        assertThat(actualEntity.getAddressIndicId())
                .isEqualTo(addressIndicIdExpectedValue);

        Object afmExpectedValue = patchEntity.getAfm() != null ? patchEntity.getAfm() : originalEntity.getAfm();
        assertThat(actualEntity.getAfm())
                .isEqualTo(afmExpectedValue);

        Object amExpectedValue = patchEntity.getAm() != null ? patchEntity.getAm() : originalEntity.getAm();
        assertThat(actualEntity.getAm())
                .isEqualTo(amExpectedValue);

        Object armaeExpectedValue = patchEntity.getArmae() != null ? patchEntity.getArmae() : originalEntity.getArmae();
        assertThat(actualEntity.getArmae())
                .isEqualTo(armaeExpectedValue);

        Object boardDurExpectedValue = patchEntity.getBoardDur() != null ? patchEntity.getBoardDur() : originalEntity.getBoardDur();
        assertThat(actualEntity.getBoardDur())
                .isEqualTo(boardDurExpectedValue);

        Object branchTypeIdExpectedValue = patchEntity.getBranchTypeId() != null ? patchEntity.getBranchTypeId() : originalEntity.getBranchTypeId();
        assertThat(actualEntity.getBranchTypeId())
                .isEqualTo(branchTypeIdExpectedValue);

        Object cancelDateExpectedValue = patchEntity.getCancelDate() != null ? patchEntity.getCancelDate() : originalEntity.getCancelDate();
        assertThat(actualEntity.getCancelDate())
                .isEqualTo(cancelDateExpectedValue);

        Object cancelReasonIdExpectedValue = patchEntity.getCancelReasonId() != null ? patchEntity.getCancelReasonId() : originalEntity.getCancelReasonId();
        assertThat(actualEntity.getCancelReasonId())
                .isEqualTo(cancelReasonIdExpectedValue);

        Object cdExpectedValue = patchEntity.getCd() != null ? patchEntity.getCd() : originalEntity.getCd();
        assertThat(actualEntity.getCd())
                .isEqualTo(cdExpectedValue);

        Object chamberDepartmentIdExpectedValue = patchEntity.getChamberDepartmentId() != null ? patchEntity.getChamberDepartmentId() : originalEntity.getChamberDepartmentId();
        assertThat(actualEntity.getChamberDepartmentId())
                .isEqualTo(chamberDepartmentIdExpectedValue);

        Object chamberGemiResponsibleIdExpectedValue = patchEntity.getChamberGemiResponsibleId() != null ? patchEntity.getChamberGemiResponsibleId() : originalEntity.getChamberGemiResponsibleId();
        assertThat(actualEntity.getChamberGemiResponsibleId())
                .isEqualTo(chamberGemiResponsibleIdExpectedValue);

        Object chamberRegisteredIdExpectedValue = patchEntity.getChamberRegisteredId() != null ? patchEntity.getChamberRegisteredId() : originalEntity.getChamberRegisteredId();
        assertThat(actualEntity.getChamberRegisteredId())
                .isEqualTo(chamberRegisteredIdExpectedValue);

        Object coNameExpectedValue = patchEntity.getCoName() != null ? patchEntity.getCoName() : originalEntity.getCoName();
        assertThat(actualEntity.getCoName())
                .isEqualTo(coNameExpectedValue);

        Object coNameNrmExpectedValue = patchEntity.getCoNameNrm() != null ? patchEntity.getCoNameNrm() : originalEntity.getCoNameNrm();
        assertThat(actualEntity.getCoNameNrm())
                .isEqualTo(coNameNrmExpectedValue);

        Object comercRegCodeExpectedValue = patchEntity.getComercRegCode() != null ? patchEntity.getComercRegCode() : originalEntity.getComercRegCode();
        assertThat(actualEntity.getComercRegCode())
                .isEqualTo(comercRegCodeExpectedValue);

        Object companyStatusIdExpectedValue = patchEntity.getCompanyStatusId() != null ? patchEntity.getCompanyStatusId() : originalEntity.getCompanyStatusId();
        assertThat(actualEntity.getCompanyStatusId())
                .isEqualTo(companyStatusIdExpectedValue);

        Object contactEmailExpectedValue = patchEntity.getContactEmail() != null ? patchEntity.getContactEmail() : originalEntity.getContactEmail();
        assertThat(actualEntity.getContactEmail())
                .isEqualTo(contactEmailExpectedValue);

        Object contactFacebookExpectedValue = patchEntity.getContactFacebook() != null ? patchEntity.getContactFacebook() : originalEntity.getContactFacebook();
        assertThat(actualEntity.getContactFacebook())
                .isEqualTo(contactFacebookExpectedValue);

        Object contactFaxExpectedValue = patchEntity.getContactFax() != null ? patchEntity.getContactFax() : originalEntity.getContactFax();
        assertThat(actualEntity.getContactFax())
                .isEqualTo(contactFaxExpectedValue);

        Object contactMobileExpectedValue = patchEntity.getContactMobile() != null ? patchEntity.getContactMobile() : originalEntity.getContactMobile();
        assertThat(actualEntity.getContactMobile())
                .isEqualTo(contactMobileExpectedValue);

        Object contactPhone1ExpectedValue = patchEntity.getContactPhone1() != null ? patchEntity.getContactPhone1() : originalEntity.getContactPhone1();
        assertThat(actualEntity.getContactPhone1())
                .isEqualTo(contactPhone1ExpectedValue);

        Object contactPhone2ExpectedValue = patchEntity.getContactPhone2() != null ? patchEntity.getContactPhone2() : originalEntity.getContactPhone2();
        assertThat(actualEntity.getContactPhone2())
                .isEqualTo(contactPhone2ExpectedValue);

        Object contactPhone3ExpectedValue = patchEntity.getContactPhone3() != null ? patchEntity.getContactPhone3() : originalEntity.getContactPhone3();
        assertThat(actualEntity.getContactPhone3())
                .isEqualTo(contactPhone3ExpectedValue);

        Object contactPhoneAreaExpectedValue = patchEntity.getContactPhoneArea() != null ? patchEntity.getContactPhoneArea() : originalEntity.getContactPhoneArea();
        assertThat(actualEntity.getContactPhoneArea())
                .isEqualTo(contactPhoneAreaExpectedValue);

        Object contactTelexExpectedValue = patchEntity.getContactTelex() != null ? patchEntity.getContactTelex() : originalEntity.getContactTelex();
        assertThat(actualEntity.getContactTelex())
                .isEqualTo(contactTelexExpectedValue);

        Object contactTwitterExpectedValue = patchEntity.getContactTwitter() != null ? patchEntity.getContactTwitter() : originalEntity.getContactTwitter();
        assertThat(actualEntity.getContactTwitter())
                .isEqualTo(contactTwitterExpectedValue);

        Object contactUrlExpectedValue = patchEntity.getContactUrl() != null ? patchEntity.getContactUrl() : originalEntity.getContactUrl();
        assertThat(actualEntity.getContactUrl())
                .isEqualTo(contactUrlExpectedValue);

        Object corporateStatusIdExpectedValue = patchEntity.getCorporateStatusId() != null ? patchEntity.getCorporateStatusId() : originalEntity.getCorporateStatusId();
        assertThat(actualEntity.getCorporateStatusId())
                .isEqualTo(corporateStatusIdExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object dateGemiRegisteredExpectedValue = patchEntity.getDateGemiRegistered() != null ? patchEntity.getDateGemiRegistered() : originalEntity.getDateGemiRegistered();
        assertThat(actualEntity.getDateGemiRegistered())
                .isEqualTo(dateGemiRegisteredExpectedValue);

        Object dateIncorporatedExpectedValue = patchEntity.getDateIncorporated() != null ? patchEntity.getDateIncorporated() : originalEntity.getDateIncorporated();
        assertThat(actualEntity.getDateIncorporated())
                .isEqualTo(dateIncorporatedExpectedValue);

        Object dateProfessionStartedExpectedValue = patchEntity.getDateProfessionStarted() != null ? patchEntity.getDateProfessionStarted() : originalEntity.getDateProfessionStarted();
        assertThat(actualEntity.getDateProfessionStarted())
                .isEqualTo(dateProfessionStartedExpectedValue);

        Object dateRegisteredExpectedValue = patchEntity.getDateRegistered() != null ? patchEntity.getDateRegistered() : originalEntity.getDateRegistered();
        assertThat(actualEntity.getDateRegistered())
                .isEqualTo(dateRegisteredExpectedValue);

        Object disputeDateExpectedValue = patchEntity.getDisputeDate() != null ? patchEntity.getDisputeDate() : originalEntity.getDisputeDate();
        assertThat(actualEntity.getDisputeDate())
                .isEqualTo(disputeDateExpectedValue);

        Object disputeDecDateExpectedValue = patchEntity.getDisputeDecDate() != null ? patchEntity.getDisputeDecDate() : originalEntity.getDisputeDecDate();
        assertThat(actualEntity.getDisputeDecDate())
                .isEqualTo(disputeDecDateExpectedValue);

        Object disputeNumberExpectedValue = patchEntity.getDisputeNumber() != null ? patchEntity.getDisputeNumber() : originalEntity.getDisputeNumber();
        assertThat(actualEntity.getDisputeNumber())
                .isEqualTo(disputeNumberExpectedValue);

        Object edraExpectedValue = patchEntity.getEdra() != null ? patchEntity.getEdra() : originalEntity.getEdra();
        assertThat(actualEntity.getEdra())
                .isEqualTo(edraExpectedValue);

        Object email2ExpectedValue = patchEntity.getEmail2() != null ? patchEntity.getEmail2() : originalEntity.getEmail2();
        assertThat(actualEntity.getEmail2())
                .isEqualTo(email2ExpectedValue);

        Object email3ExpectedValue = patchEntity.getEmail3() != null ? patchEntity.getEmail3() : originalEntity.getEmail3();
        assertThat(actualEntity.getEmail3())
                .isEqualTo(email3ExpectedValue);

        Object email4ExpectedValue = patchEntity.getEmail4() != null ? patchEntity.getEmail4() : originalEntity.getEmail4();
        assertThat(actualEntity.getEmail4())
                .isEqualTo(email4ExpectedValue);

        Object endfirstfyExpectedValue = patchEntity.getEndfirstfy() != null ? patchEntity.getEndfirstfy() : originalEntity.getEndfirstfy();
        assertThat(actualEntity.getEndfirstfy())
                .isEqualTo(endfirstfyExpectedValue);

        Object euCommerceExpectedValue = patchEntity.getEuCommerce() != null ? patchEntity.getEuCommerce() : originalEntity.getEuCommerce();
        assertThat(actualEntity.getEuCommerce())
                .isEqualTo(euCommerceExpectedValue);

        Object expManagementDtExpectedValue = patchEntity.getExpManagementDt() != null ? patchEntity.getExpManagementDt() : originalEntity.getExpManagementDt();
        assertThat(actualEntity.getExpManagementDt())
                .isEqualTo(expManagementDtExpectedValue);

        Object expireDateExpectedValue = patchEntity.getExpireDate() != null ? patchEntity.getExpireDate() : originalEntity.getExpireDate();
        assertThat(actualEntity.getExpireDate())
                .isEqualTo(expireDateExpectedValue);

        Object financialYearIdExpectedValue = patchEntity.getFinancialYearId() != null ? patchEntity.getFinancialYearId() : originalEntity.getFinancialYearId();
        assertThat(actualEntity.getFinancialYearId())
                .isEqualTo(financialYearIdExpectedValue);

        Object foundationDateExpectedValue = patchEntity.getFoundationDate() != null ? patchEntity.getFoundationDate() : originalEntity.getFoundationDate();
        assertThat(actualEntity.getFoundationDate())
                .isEqualTo(foundationDateExpectedValue);

        Object gemhOtherPerCdExpectedValue = patchEntity.getGemhOtherPerCd() != null ? patchEntity.getGemhOtherPerCd() : originalEntity.getGemhOtherPerCd();
        assertThat(actualEntity.getGemhOtherPerCd())
                .isEqualTo(gemhOtherPerCdExpectedValue);

        Object gemiNumberExpectedValue = patchEntity.getGemiNumber() != null ? patchEntity.getGemiNumber() : originalEntity.getGemiNumber();
        assertThat(actualEntity.getGemiNumber())
                .isEqualTo(gemiNumberExpectedValue);

        Object hpExpectedValue = patchEntity.getHp() != null ? patchEntity.getHp() : originalEntity.getHp();
        assertThat(actualEntity.getHp())
                .isEqualTo(hpExpectedValue);

        Object indefiniteExpectedValue = patchEntity.getIndefinite() != null ? patchEntity.getIndefinite() : originalEntity.getIndefinite();
        assertThat(actualEntity.getIndefinite())
                .isEqualTo(indefiniteExpectedValue);

        Object lastStateChangeDateExpectedValue = patchEntity.getLastStateChangeDate() != null ? patchEntity.getLastStateChangeDate() : originalEntity.getLastStateChangeDate();
        assertThat(actualEntity.getLastStateChangeDate())
                .isEqualTo(lastStateChangeDateExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object licenceExpDtExpectedValue = patchEntity.getLicenceExpDt() != null ? patchEntity.getLicenceExpDt() : originalEntity.getLicenceExpDt();
        assertThat(actualEntity.getLicenceExpDt())
                .isEqualTo(licenceExpDtExpectedValue);

        Object licenceNoExpectedValue = patchEntity.getLicenceNo() != null ? patchEntity.getLicenceNo() : originalEntity.getLicenceNo();
        assertThat(actualEntity.getLicenceNo())
                .isEqualTo(licenceNoExpectedValue);

        Object mailAddressExpectedValue = patchEntity.getMailAddress() != null ? patchEntity.getMailAddress() : originalEntity.getMailAddress();
        assertThat(actualEntity.getMailAddress())
                .isEqualTo(mailAddressExpectedValue);

        Object mailNameExpectedValue = patchEntity.getMailName() != null ? patchEntity.getMailName() : originalEntity.getMailName();
        assertThat(actualEntity.getMailName())
                .isEqualTo(mailNameExpectedValue);

        Object meCriteria1IdExpectedValue = patchEntity.getMeCriteria1Id() != null ? patchEntity.getMeCriteria1Id() : originalEntity.getMeCriteria1Id();
        assertThat(actualEntity.getMeCriteria1Id())
                .isEqualTo(meCriteria1IdExpectedValue);

        Object meCriteria2IdExpectedValue = patchEntity.getMeCriteria2Id() != null ? patchEntity.getMeCriteria2Id() : originalEntity.getMeCriteria2Id();
        assertThat(actualEntity.getMeCriteria2Id())
                .isEqualTo(meCriteria2IdExpectedValue);

        Object memberExpectedValue = patchEntity.getMember() != null ? patchEntity.getMember() : originalEntity.getMember();
        assertThat(actualEntity.getMember())
                .isEqualTo(memberExpectedValue);

        Object memberDuesExpectedValue = patchEntity.getMemberDues() != null ? patchEntity.getMemberDues() : originalEntity.getMemberDues();
        assertThat(actualEntity.getMemberDues())
                .isEqualTo(memberDuesExpectedValue);

        Object nationalityIdExpectedValue = patchEntity.getNationalityId() != null ? patchEntity.getNationalityId() : originalEntity.getNationalityId();
        assertThat(actualEntity.getNationalityId())
                .isEqualTo(nationalityIdExpectedValue);

        Object nextamExpectedValue = patchEntity.getNextam() != null ? patchEntity.getNextam() : originalEntity.getNextam();
        assertThat(actualEntity.getNextam())
                .isEqualTo(nextamExpectedValue);

        Object objectiveExpectedValue = patchEntity.getObjective() != null ? patchEntity.getObjective() : originalEntity.getObjective();
        assertThat(actualEntity.getObjective())
                .isEqualTo(objectiveExpectedValue);

        Object oldamExpectedValue = patchEntity.getOldam() != null ? patchEntity.getOldam() : originalEntity.getOldam();
        assertThat(actualEntity.getOldam())
                .isEqualTo(oldamExpectedValue);

        Object pendencyExpectedValue = patchEntity.getPendency() != null ? patchEntity.getPendency() : originalEntity.getPendency();
        assertThat(actualEntity.getPendency())
                .isEqualTo(pendencyExpectedValue);

        Object pendingExpectedValue = patchEntity.getPending() != null ? patchEntity.getPending() : originalEntity.getPending();
        assertThat(actualEntity.getPending())
                .isEqualTo(pendingExpectedValue);

        Object previousamExpectedValue = patchEntity.getPreviousam() != null ? patchEntity.getPreviousam() : originalEntity.getPreviousam();
        assertThat(actualEntity.getPreviousam())
                .isEqualTo(previousamExpectedValue);

        Object recTypeExpectedValue = patchEntity.getRecType() != null ? patchEntity.getRecType() : originalEntity.getRecType();
        assertThat(actualEntity.getRecType())
                .isEqualTo(recTypeExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object registrationTypeIdExpectedValue = patchEntity.getRegistrationTypeId() != null ? patchEntity.getRegistrationTypeId() : originalEntity.getRegistrationTypeId();
        assertThat(actualEntity.getRegistrationTypeId())
                .isEqualTo(registrationTypeIdExpectedValue);

        Object saleTypeIdExpectedValue = patchEntity.getSaleTypeId() != null ? patchEntity.getSaleTypeId() : originalEntity.getSaleTypeId();
        assertThat(actualEntity.getSaleTypeId())
                .isEqualTo(saleTypeIdExpectedValue);

        Object startfirstfyExpectedValue = patchEntity.getStartfirstfy() != null ? patchEntity.getStartfirstfy() : originalEntity.getStartfirstfy();
        assertThat(actualEntity.getStartfirstfy())
                .isEqualTo(startfirstfyExpectedValue);

        Object subscrCatExpectedValue = patchEntity.getSubscrCat() != null ? patchEntity.getSubscrCat() : originalEntity.getSubscrCat();
        assertThat(actualEntity.getSubscrCat())
                .isEqualTo(subscrCatExpectedValue);

        Object taxServiceIdExpectedValue = patchEntity.getTaxServiceId() != null ? patchEntity.getTaxServiceId() : originalEntity.getTaxServiceId();
        assertThat(actualEntity.getTaxServiceId())
                .isEqualTo(taxServiceIdExpectedValue);

        Object userInsExpectedValue = patchEntity.getUserIns() != null ? patchEntity.getUserIns() : originalEntity.getUserIns();
        assertThat(actualEntity.getUserIns())
                .isEqualTo(userInsExpectedValue);

        Object userLastUpdExpectedValue = patchEntity.getUserLastUpd() != null ? patchEntity.getUserLastUpd() : originalEntity.getUserLastUpd();
        assertThat(actualEntity.getUserLastUpd())
                .isEqualTo(userLastUpdExpectedValue);

        Object voteDepartmentIdExpectedValue = patchEntity.getVoteDepartmentId() != null ? patchEntity.getVoteDepartmentId() : originalEntity.getVoteDepartmentId();
        assertThat(actualEntity.getVoteDepartmentId())
                .isEqualTo(voteDepartmentIdExpectedValue);

        Object votesExpectedValue = patchEntity.getVotes() != null ? patchEntity.getVotes() : originalEntity.getVotes();
        assertThat(actualEntity.getVotes())
                .isEqualTo(votesExpectedValue);

        Object managementDurExpectedValue = patchEntity.getManagementDur() != null ? patchEntity.getManagementDur() : originalEntity.getManagementDur();
        assertThat(actualEntity.getManagementDur())
                .isEqualTo(managementDurExpectedValue);

        Object receiveNewsletterExpectedValue = patchEntity.getReceiveNewsletter() != null ? patchEntity.getReceiveNewsletter() : originalEntity.getReceiveNewsletter();
        assertThat(actualEntity.getReceiveNewsletter())
                .isEqualTo(receiveNewsletterExpectedValue);

        Object gemiLastUpdatedExpectedValue = patchEntity.getGemiLastUpdated() != null ? patchEntity.getGemiLastUpdated() : originalEntity.getGemiLastUpdated();
        assertThat(actualEntity.getGemiLastUpdated())
                .isEqualTo(gemiLastUpdatedExpectedValue);

        Object gemiLastStateChangeDateExpectedValue = patchEntity.getGemiLastStateChangeDate() != null ? patchEntity.getGemiLastStateChangeDate() : originalEntity.getGemiLastStateChangeDate();
        assertThat(actualEntity.getGemiLastStateChangeDate())
                .isEqualTo(gemiLastStateChangeDateExpectedValue);

        Object gemiParentGemiNumberExpectedValue = patchEntity.getGemiParentGemiNumber() != null ? patchEntity.getGemiParentGemiNumber() : originalEntity.getGemiParentGemiNumber();
        assertThat(actualEntity.getGemiParentGemiNumber())
                .isEqualTo(gemiParentGemiNumberExpectedValue);

        Object gemiMunicipalityIdExpectedValue = patchEntity.getGemiMunicipalityId() != null ? patchEntity.getGemiMunicipalityId() : originalEntity.getGemiMunicipalityId();
        assertThat(actualEntity.getGemiMunicipalityId())
                .isEqualTo(gemiMunicipalityIdExpectedValue);

        Object gemiCityExpectedValue = patchEntity.getGemiCity() != null ? patchEntity.getGemiCity() : originalEntity.getGemiCity();
        assertThat(actualEntity.getGemiCity())
                .isEqualTo(gemiCityExpectedValue);

        Object gemiRegionExpectedValue = patchEntity.getGemiRegion() != null ? patchEntity.getGemiRegion() : originalEntity.getGemiRegion();
        assertThat(actualEntity.getGemiRegion())
                .isEqualTo(gemiRegionExpectedValue);

        Object gemiStreetExpectedValue = patchEntity.getGemiStreet() != null ? patchEntity.getGemiStreet() : originalEntity.getGemiStreet();
        assertThat(actualEntity.getGemiStreet())
                .isEqualTo(gemiStreetExpectedValue);

        Object gemiStreetNumberExpectedValue = patchEntity.getGemiStreetNumber() != null ? patchEntity.getGemiStreetNumber() : originalEntity.getGemiStreetNumber();
        assertThat(actualEntity.getGemiStreetNumber())
                .isEqualTo(gemiStreetNumberExpectedValue);

        Object gemiZipCodeExpectedValue = patchEntity.getGemiZipCode() != null ? patchEntity.getGemiZipCode() : originalEntity.getGemiZipCode();
        assertThat(actualEntity.getGemiZipCode())
                .isEqualTo(gemiZipCodeExpectedValue);

        Object gemiPhone1ExpectedValue = patchEntity.getGemiPhone1() != null ? patchEntity.getGemiPhone1() : originalEntity.getGemiPhone1();
        assertThat(actualEntity.getGemiPhone1())
                .isEqualTo(gemiPhone1ExpectedValue);

        Object gemiPhone2ExpectedValue = patchEntity.getGemiPhone2() != null ? patchEntity.getGemiPhone2() : originalEntity.getGemiPhone2();
        assertThat(actualEntity.getGemiPhone2())
                .isEqualTo(gemiPhone2ExpectedValue);

        Object gemiPhone3ExpectedValue = patchEntity.getGemiPhone3() != null ? patchEntity.getGemiPhone3() : originalEntity.getGemiPhone3();
        assertThat(actualEntity.getGemiPhone3())
                .isEqualTo(gemiPhone3ExpectedValue);

        Object gemiMobileExpectedValue = patchEntity.getGemiMobile() != null ? patchEntity.getGemiMobile() : originalEntity.getGemiMobile();
        assertThat(actualEntity.getGemiMobile())
                .isEqualTo(gemiMobileExpectedValue);

        Object gemiFaxExpectedValue = patchEntity.getGemiFax() != null ? patchEntity.getGemiFax() : originalEntity.getGemiFax();
        assertThat(actualEntity.getGemiFax())
                .isEqualTo(gemiFaxExpectedValue);

        Object gemiEmailExpectedValue = patchEntity.getGemiEmail() != null ? patchEntity.getGemiEmail() : originalEntity.getGemiEmail();
        assertThat(actualEntity.getGemiEmail())
                .isEqualTo(gemiEmailExpectedValue);

        Object gemiCreatedExpectedValue = patchEntity.getGemiCreated() != null ? patchEntity.getGemiCreated() : originalEntity.getGemiCreated();
        assertThat(actualEntity.getGemiCreated())
                .isEqualTo(gemiCreatedExpectedValue);

        Object gemiIdExpectedValue = patchEntity.getGemiId() != null ? patchEntity.getGemiId() : originalEntity.getGemiId();
        assertThat(actualEntity.getGemiId())
                .isEqualTo(gemiIdExpectedValue);

        Object gemiDateCreatedExpectedValue = patchEntity.getGemiDateCreated() != null ? patchEntity.getGemiDateCreated() : originalEntity.getGemiDateCreated();
        assertThat(actualEntity.getGemiDateCreated())
                .isEqualTo(gemiDateCreatedExpectedValue);

        Object articleExpectedValue = patchEntity.getArticle() != null ? patchEntity.getArticle() : originalEntity.getArticle();
        assertThat(actualEntity.getArticle())
                .isEqualTo(articleExpectedValue);

        Object showEmailExpectedValue = patchEntity.getShowEmail() != null ? patchEntity.getShowEmail() : originalEntity.getShowEmail();
        assertThat(actualEntity.getShowEmail())
                .isEqualTo(showEmailExpectedValue);

        Object gemiId2ExpectedValue = patchEntity.getGemiId2() != null ? patchEntity.getGemiId2() : originalEntity.getGemiId2();
        assertThat(actualEntity.getGemiId2())
                .isEqualTo(gemiId2ExpectedValue);

        Object voteDtExpectedValue = patchEntity.getVoteDt() != null ? patchEntity.getVoteDt() : originalEntity.getVoteDt();
        assertThat(actualEntity.getVoteDt())
                .isEqualTo(voteDtExpectedValue);

        Object voteFlagExpectedValue = patchEntity.getVoteFlag() != null ? patchEntity.getVoteFlag() : originalEntity.getVoteFlag();
        assertThat(actualEntity.getVoteFlag())
                .isEqualTo(voteFlagExpectedValue);

        Object voteEtairiaFlagExpectedValue = patchEntity.getVoteEtairiaFlag() != null ? patchEntity.getVoteEtairiaFlag() : originalEntity.getVoteEtairiaFlag();
        assertThat(actualEntity.getVoteEtairiaFlag())
                .isEqualTo(voteEtairiaFlagExpectedValue);

        Object gemiDateIncorporatedExpectedValue = patchEntity.getGemiDateIncorporated() != null ? patchEntity.getGemiDateIncorporated() : originalEntity.getGemiDateIncorporated();
        assertThat(actualEntity.getGemiDateIncorporated())
                .isEqualTo(gemiDateIncorporatedExpectedValue);

        Object meCriteria3IdExpectedValue = patchEntity.getMeCriteria3Id() != null ? patchEntity.getMeCriteria3Id() : originalEntity.getMeCriteria3Id();
        assertThat(actualEntity.getMeCriteria3Id())
                .isEqualTo(meCriteria3IdExpectedValue);

        Object dateInterruptionExpectedValue = patchEntity.getDateInterruption() != null ? patchEntity.getDateInterruption() : originalEntity.getDateInterruption();
        assertThat(actualEntity.getDateInterruption())
                .isEqualTo(dateInterruptionExpectedValue);

        Object cancelReasonDscrExpectedValue = patchEntity.getCancelReasonDscr() != null ? patchEntity.getCancelReasonDscr() : originalEntity.getCancelReasonDscr();
        assertThat(actualEntity.getCancelReasonDscr())
                .isEqualTo(cancelReasonDscrExpectedValue);

        Object bankruptDateExpectedValue = patchEntity.getBankruptDate() != null ? patchEntity.getBankruptDate() : originalEntity.getBankruptDate();
        assertThat(actualEntity.getBankruptDate())
                .isEqualTo(bankruptDateExpectedValue);

        Object startDtCorpStatusExpectedValue = patchEntity.getStartDtCorpStatus() != null ? patchEntity.getStartDtCorpStatus() : originalEntity.getStartDtCorpStatus();
        assertThat(actualEntity.getStartDtCorpStatus())
                .isEqualTo(startDtCorpStatusExpectedValue);

        Object endDtCorpStatusExpectedValue = patchEntity.getEndDtCorpStatus() != null ? patchEntity.getEndDtCorpStatus() : originalEntity.getEndDtCorpStatus();
        assertThat(actualEntity.getEndDtCorpStatus())
                .isEqualTo(endDtCorpStatusExpectedValue);

        Object bankruptNumberExpectedValue = patchEntity.getBankruptNumber() != null ? patchEntity.getBankruptNumber() : originalEntity.getBankruptNumber();
        assertThat(actualEntity.getBankruptNumber())
                .isEqualTo(bankruptNumberExpectedValue);

        Object lastChangeDateExpectedValue = patchEntity.getLastChangeDate() != null ? patchEntity.getLastChangeDate() : originalEntity.getLastChangeDate();
        assertThat(actualEntity.getLastChangeDate())
                .isEqualTo(lastChangeDateExpectedValue);

        Object nextCompanyIdExpectedValue = patchEntity.getNextCompanyId() != null ? patchEntity.getNextCompanyId() : originalEntity.getNextCompanyId();
        assertThat(actualEntity.getNextCompanyId())
                .isEqualTo(nextCompanyIdExpectedValue);

        Object parentCompanyIdExpectedValue = patchEntity.getParentCompanyId() != null ? patchEntity.getParentCompanyId() : originalEntity.getParentCompanyId();
        assertThat(actualEntity.getParentCompanyId())
                .isEqualTo(parentCompanyIdExpectedValue);

        Object previousCompanyIdExpectedValue = patchEntity.getPreviousCompanyId() != null ? patchEntity.getPreviousCompanyId() : originalEntity.getPreviousCompanyId();
        assertThat(actualEntity.getPreviousCompanyId())
                .isEqualTo(previousCompanyIdExpectedValue);

        Object transferFlagExpectedValue = patchEntity.getTransferFlag() != null ? patchEntity.getTransferFlag() : originalEntity.getTransferFlag();
        assertThat(actualEntity.getTransferFlag())
                .isEqualTo(transferFlagExpectedValue);

        Object transferAmExpectedValue = patchEntity.getTransferAm() != null ? patchEntity.getTransferAm() : originalEntity.getTransferAm();
        assertThat(actualEntity.getTransferAm())
                .isEqualTo(transferAmExpectedValue);

        Object proegOccupationIdExpectedValue = patchEntity.getProegOccupationId() != null ? patchEntity.getProegOccupationId() : originalEntity.getProegOccupationId();
        assertThat(actualEntity.getProegOccupationId())
                .isEqualTo(proegOccupationIdExpectedValue);

        Object proegSubscrAmntExpectedValue = patchEntity.getProegSubscrAmnt() != null ? patchEntity.getProegSubscrAmnt() : originalEntity.getProegSubscrAmnt();
        assertThat(actualEntity.getProegSubscrAmnt())
                .isEqualTo(proegSubscrAmntExpectedValue);

        Object proegSubscrYearExpectedValue = patchEntity.getProegSubscrYear() != null ? patchEntity.getProegSubscrYear() : originalEntity.getProegSubscrYear();
        assertThat(actualEntity.getProegSubscrYear())
                .isEqualTo(proegSubscrYearExpectedValue);

        Object proegSubscrDateExpectedValue = patchEntity.getProegSubscrDate() != null ? patchEntity.getProegSubscrDate() : originalEntity.getProegSubscrDate();
        assertThat(actualEntity.getProegSubscrDate())
                .isEqualTo(proegSubscrDateExpectedValue);

        Object proegSubscrNotesExpectedValue = patchEntity.getProegSubscrNotes() != null ? patchEntity.getProegSubscrNotes() : originalEntity.getProegSubscrNotes();
        assertThat(actualEntity.getProegSubscrNotes())
                .isEqualTo(proegSubscrNotesExpectedValue);

        Object migrCapitolExpectedValue = patchEntity.getMigrCapitol() != null ? patchEntity.getMigrCapitol() : originalEntity.getMigrCapitol();
        assertThat(actualEntity.getMigrCapitol())
                .isEqualTo(migrCapitolExpectedValue);

        Object migrCapitol2ExpectedValue = patchEntity.getMigrCapitol2() != null ? patchEntity.getMigrCapitol2() : originalEntity.getMigrCapitol2();
        assertThat(actualEntity.getMigrCapitol2())
                .isEqualTo(migrCapitol2ExpectedValue);

        Object migrManyChildrenFlagExpectedValue = patchEntity.getMigrManyChildrenFlag() != null ? patchEntity.getMigrManyChildrenFlag() : originalEntity.getMigrManyChildrenFlag();
        assertThat(actualEntity.getMigrManyChildrenFlag())
                .isEqualTo(migrManyChildrenFlagExpectedValue);

        Object migrAmeaFlagExpectedValue = patchEntity.getMigrAmeaFlag() != null ? patchEntity.getMigrAmeaFlag() : originalEntity.getMigrAmeaFlag();
        assertThat(actualEntity.getMigrAmeaFlag())
                .isEqualTo(migrAmeaFlagExpectedValue);

        Object migrYpokatFlagExpectedValue = patchEntity.getMigrYpokatFlag() != null ? patchEntity.getMigrYpokatFlag() : originalEntity.getMigrYpokatFlag();
        assertThat(actualEntity.getMigrYpokatFlag())
                .isEqualTo(migrYpokatFlagExpectedValue);

        Object migrThrasherFlagExpectedValue = patchEntity.getMigrThrasherFlag() != null ? patchEntity.getMigrThrasherFlag() : originalEntity.getMigrThrasherFlag();
        assertThat(actualEntity.getMigrThrasherFlag())
                .isEqualTo(migrThrasherFlagExpectedValue);

        Object migrLowCapitalFlagExpectedValue = patchEntity.getMigrLowCapitalFlag() != null ? patchEntity.getMigrLowCapitalFlag() : originalEntity.getMigrLowCapitalFlag();
        assertThat(actualEntity.getMigrLowCapitalFlag())
                .isEqualTo(migrLowCapitalFlagExpectedValue);

        Object migrSendTaxServFlagExpectedValue = patchEntity.getMigrSendTaxServFlag() != null ? patchEntity.getMigrSendTaxServFlag() : originalEntity.getMigrSendTaxServFlag();
        assertThat(actualEntity.getMigrSendTaxServFlag())
                .isEqualTo(migrSendTaxServFlagExpectedValue);

        Object printKatastFlagExpectedValue = patchEntity.getPrintKatastFlag() != null ? patchEntity.getPrintKatastFlag() : originalEntity.getPrintKatastFlag();
        assertThat(actualEntity.getPrintKatastFlag())
                .isEqualTo(printKatastFlagExpectedValue);

        Object subscrCalcDateExpectedValue = patchEntity.getSubscrCalcDate() != null ? patchEntity.getSubscrCalcDate() : originalEntity.getSubscrCalcDate();
        assertThat(actualEntity.getSubscrCalcDate())
                .isEqualTo(subscrCalcDateExpectedValue);

        Object showBusinessGuideExpectedValue = patchEntity.getShowBusinessGuide() != null ? patchEntity.getShowBusinessGuide() : originalEntity.getShowBusinessGuide();
        assertThat(actualEntity.getShowBusinessGuide())
                .isEqualTo(showBusinessGuideExpectedValue);

        Object field7060ExpectedValue = patchEntity.getField7060() != null ? patchEntity.getField7060() : originalEntity.getField7060();
        assertThat(actualEntity.getField7060())
                .isEqualTo(field7060ExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyDtoListForNullOrEmptyTemporaryCompanyList() {
        assertThat(temporaryCompanyMapper.toDTOList(null)).isEmpty();
        assertThat(temporaryCompanyMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyListForNullOrEmptyTemporaryCompanyDtoList() {
        assertThat(temporaryCompanyMapper.toEntityList(null)).isEmpty();
        assertThat(temporaryCompanyMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        TemporaryCompany entity = createSampleTemporaryCompanyEntity();
        TemporaryCompany expectedEntity = createSampleTemporaryCompanyEntity();

        temporaryCompanyMapper.partialUpdate(entity, null);
        temporaryCompanyMapper.partialUpdate(null, createPatchTemporaryCompanyDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated TemporaryCompany fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompany createSampleTemporaryCompanyEntity() {
        TemporaryCompany entity = new TemporaryCompany();
        entity.setId(new BigInteger("1000"));
        entity.setVersion(new BigInteger("1000"));
        entity.setAddressCity("addressCityValue1");
        entity.setAddressCountryId(new BigInteger("1000"));
        entity.setAddressLatitude("addressLatitudeValue1");
        entity.setAddressLongitude("addressLongitudeValue1");
        entity.setAddressMunicipalityAlt("addressMunicipalityAltValue1");
        entity.setAddressMunicipalityPriId(new BigInteger("1000"));
        entity.setAddressMunicipalitySecId(new BigInteger("1000"));
        entity.setAddressPoBox("addressPoBoxValue1");
        entity.setAddressPrefectureId(new BigInteger("1000"));
        entity.setAddressRegion("addressRegionValue1");
        entity.setAddressStreet("addressStreetValue1");
        entity.setAddressStreetNumber("addressStreetNumberValue1");
        entity.setAddressZipCode("addressZipCodeValue1");
        entity.setAddressZoomLevel(new BigInteger("1000"));
        entity.setAddressIndicId(new BigInteger("1000"));
        entity.setAfm("afmValue1");
        entity.setAm(new BigInteger("1000"));
        entity.setArmae("armaeValue1");
        entity.setBoardDur("boardDurValue1");
        entity.setBranchTypeId(new BigInteger("1000"));
        entity.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setCancelReasonId(new BigInteger("1000"));
        entity.setCd(new BigInteger("1000"));
        entity.setChamberDepartmentId(new BigInteger("1000"));
        entity.setChamberGemiResponsibleId(new BigInteger("1000"));
        entity.setChamberRegisteredId(new BigInteger("1000"));
        entity.setCoName("coNameValue1");
        entity.setCoNameNrm("coNameNrmValue1");
        entity.setComercRegCode(new BigInteger("1000"));
        entity.setCompanyStatusId(new BigInteger("1000"));
        entity.setContactEmail("contactEmailValue1");
        entity.setContactFacebook("contactFacebookValue1");
        entity.setContactFax("contactFaxValue1");
        entity.setContactMobile("contactMobileValue1");
        entity.setContactPhone1("contactPhone1Value1");
        entity.setContactPhone2("contactPhone2Value1");
        entity.setContactPhone3("contactPhone3Value1");
        entity.setContactPhoneArea("contactPhoneAreaValue1");
        entity.setContactTelex("contactTelexValue1");
        entity.setContactTwitter("contactTwitterValue1");
        entity.setContactUrl("contactUrlValue1");
        entity.setCorporateStatusId(new BigInteger("1000"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDateGemiRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDisputeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDisputeDecDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDisputeNumber("disputeNumberValue1");
        entity.setEdra("edraValue1");
        entity.setEmail2("email2Value1");
        entity.setEmail3("email3Value1");
        entity.setEmail4("email4Value1");
        entity.setEndfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setEuCommerce(new BigInteger("1000"));
        entity.setExpManagementDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setExpireDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setFinancialYearId(new BigInteger("1000"));
        entity.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setGemhOtherPerCd(new BigInteger("1000"));
        entity.setGemiNumber("gemiNumberValue1");
        entity.setHp("hpValue1");
        entity.setIndefinite(new BigInteger("1000"));
        entity.setLastStateChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLicenceExpDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLicenceNo("licenceNoValue1");
        entity.setMailAddress(new BigInteger("1000"));
        entity.setMailName("mailNameValue1");
        entity.setMeCriteria1Id(new BigInteger("1000"));
        entity.setMeCriteria2Id(new BigInteger("1000"));
        entity.setMember(new BigInteger("1000"));
        entity.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setNationalityId(new BigInteger("1000"));
        entity.setNextam(new BigInteger("1000"));
        entity.setObjective("objectiveValue1");
        entity.setOldam("oldamValue1");
        entity.setPendency("pendencyValue1");
        entity.setPending(new BigInteger("1000"));
        entity.setPreviousam(new BigInteger("1000"));
        entity.setRecType("recTypeValue1");
        entity.setRecdeleted(new BigInteger("1000"));
        entity.setRegistrationTypeId(new BigInteger("1000"));
        entity.setSaleTypeId(new BigInteger("1000"));
        entity.setStartfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setSubscrCat(new BigInteger("1000"));
        entity.setTaxServiceId(new BigInteger("1000"));
        entity.setUserIns("userInsValue1");
        entity.setUserLastUpd("userLastUpdValue1");
        entity.setVoteDepartmentId(new BigInteger("1000"));
        entity.setVotes(new BigInteger("1000"));
        entity.setManagementDur("managementDurValue1");
        entity.setReceiveNewsletter(new BigInteger("1000"));
        entity.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        entity.setGemiLastStateChangeDate(LocalDate.of(2024, 1, 11));
        entity.setGemiParentGemiNumber("gemiParentGemiNumberValue1");
        entity.setGemiMunicipalityId(new BigInteger("1000"));
        entity.setGemiCity("gemiCityValue1");
        entity.setGemiRegion("gemiRegionValue1");
        entity.setGemiStreet("gemiStreetValue1");
        entity.setGemiStreetNumber("gemiStreetNumberValue1");
        entity.setGemiZipCode("gemiZipCodeValue1");
        entity.setGemiPhone1("gemiPhone1Value1");
        entity.setGemiPhone2("gemiPhone2Value1");
        entity.setGemiPhone3("gemiPhone3Value1");
        entity.setGemiMobile("gemiMobileValue1");
        entity.setGemiFax("gemiFaxValue1");
        entity.setGemiEmail("gemiEmailValue1");
        entity.setGemiCreated(new BigInteger("1000"));
        entity.setGemiId(new BigInteger("1000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        entity.setArticle("articleValue1");
        entity.setShowEmail(new BigInteger("1000"));
        entity.setGemiId2(new BigInteger("1000"));
        entity.setVoteDt(LocalDate.of(2024, 1, 11));
        entity.setVoteFlag('A');
        entity.setVoteEtairiaFlag('A');
        entity.setGemiDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setMeCriteria3Id(new BigInteger("1000"));
        entity.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setCancelReasonDscr("cancelReasonDscrValue1");
        entity.setBankruptDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setStartDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setEndDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setBankruptNumber("bankruptNumberValue1");
        entity.setLastChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setNextCompanyId(new BigInteger("1000"));
        entity.setParentCompanyId(new BigInteger("1000"));
        entity.setPreviousCompanyId(new BigInteger("1000"));
        entity.setTransferFlag(new BigInteger("1000"));
        entity.setTransferAm(new BigInteger("1000"));
        entity.setProegOccupationId(new BigInteger("1000"));
        entity.setProegSubscrAmnt(new BigDecimal("100.50"));
        entity.setProegSubscrYear("proegSubscrYearValue1");
        entity.setProegSubscrDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setProegSubscrNotes("proegSubscrNotesValue1");
        entity.setMigrCapitol(new BigInteger("1000"));
        entity.setMigrCapitol2(new BigDecimal("100.50"));
        entity.setMigrManyChildrenFlag("migrManyChildrenFlagValue1");
        entity.setMigrAmeaFlag("migrAmeaFlagValue1");
        entity.setMigrYpokatFlag("migrYpokatFlagValue1");
        entity.setMigrThrasherFlag("migrThrasherFlagValue1");
        entity.setMigrLowCapitalFlag("migrLowCapitalFlagValue1");
        entity.setMigrSendTaxServFlag("migrSendTaxServFlagValue1");
        entity.setPrintKatastFlag(new BigInteger("1000"));
        entity.setSubscrCalcDate("subscrCalcDateValue1");
        entity.setShowBusinessGuide(new BigInteger("1000"));
        entity.setField7060(10);

        return entity;
    }

    /**
     * Creates a populated TemporaryCompany fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompany createAnotherTemporaryCompanyEntity() {
        TemporaryCompany entity = new TemporaryCompany();
        entity.setId(new BigInteger("2000"));
        entity.setVersion(new BigInteger("2000"));
        entity.setAddressCity("addressCityValue2");
        entity.setAddressCountryId(new BigInteger("2000"));
        entity.setAddressLatitude("addressLatitudeValue2");
        entity.setAddressLongitude("addressLongitudeValue2");
        entity.setAddressMunicipalityAlt("addressMunicipalityAltValue2");
        entity.setAddressMunicipalityPriId(new BigInteger("2000"));
        entity.setAddressMunicipalitySecId(new BigInteger("2000"));
        entity.setAddressPoBox("addressPoBoxValue2");
        entity.setAddressPrefectureId(new BigInteger("2000"));
        entity.setAddressRegion("addressRegionValue2");
        entity.setAddressStreet("addressStreetValue2");
        entity.setAddressStreetNumber("addressStreetNumberValue2");
        entity.setAddressZipCode("addressZipCodeValue2");
        entity.setAddressZoomLevel(new BigInteger("2000"));
        entity.setAddressIndicId(new BigInteger("2000"));
        entity.setAfm("afmValue2");
        entity.setAm(new BigInteger("2000"));
        entity.setArmae("armaeValue2");
        entity.setBoardDur("boardDurValue2");
        entity.setBranchTypeId(new BigInteger("2000"));
        entity.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setCancelReasonId(new BigInteger("2000"));
        entity.setCd(new BigInteger("2000"));
        entity.setChamberDepartmentId(new BigInteger("2000"));
        entity.setChamberGemiResponsibleId(new BigInteger("2000"));
        entity.setChamberRegisteredId(new BigInteger("2000"));
        entity.setCoName("coNameValue2");
        entity.setCoNameNrm("coNameNrmValue2");
        entity.setComercRegCode(new BigInteger("2000"));
        entity.setCompanyStatusId(new BigInteger("2000"));
        entity.setContactEmail("contactEmailValue2");
        entity.setContactFacebook("contactFacebookValue2");
        entity.setContactFax("contactFaxValue2");
        entity.setContactMobile("contactMobileValue2");
        entity.setContactPhone1("contactPhone1Value2");
        entity.setContactPhone2("contactPhone2Value2");
        entity.setContactPhone3("contactPhone3Value2");
        entity.setContactPhoneArea("contactPhoneAreaValue2");
        entity.setContactTelex("contactTelexValue2");
        entity.setContactTwitter("contactTwitterValue2");
        entity.setContactUrl("contactUrlValue2");
        entity.setCorporateStatusId(new BigInteger("2000"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDateGemiRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDisputeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDisputeDecDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDisputeNumber("disputeNumberValue2");
        entity.setEdra("edraValue2");
        entity.setEmail2("email2Value2");
        entity.setEmail3("email3Value2");
        entity.setEmail4("email4Value2");
        entity.setEndfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setEuCommerce(new BigInteger("2000"));
        entity.setExpManagementDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setExpireDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setFinancialYearId(new BigInteger("2000"));
        entity.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setGemhOtherPerCd(new BigInteger("2000"));
        entity.setGemiNumber("gemiNumberValue2");
        entity.setHp("hpValue2");
        entity.setIndefinite(new BigInteger("2000"));
        entity.setLastStateChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLicenceExpDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLicenceNo("licenceNoValue2");
        entity.setMailAddress(new BigInteger("2000"));
        entity.setMailName("mailNameValue2");
        entity.setMeCriteria1Id(new BigInteger("2000"));
        entity.setMeCriteria2Id(new BigInteger("2000"));
        entity.setMember(new BigInteger("2000"));
        entity.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setNationalityId(new BigInteger("2000"));
        entity.setNextam(new BigInteger("2000"));
        entity.setObjective("objectiveValue2");
        entity.setOldam("oldamValue2");
        entity.setPendency("pendencyValue2");
        entity.setPending(new BigInteger("2000"));
        entity.setPreviousam(new BigInteger("2000"));
        entity.setRecType("recTypeValue2");
        entity.setRecdeleted(new BigInteger("2000"));
        entity.setRegistrationTypeId(new BigInteger("2000"));
        entity.setSaleTypeId(new BigInteger("2000"));
        entity.setStartfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setSubscrCat(new BigInteger("2000"));
        entity.setTaxServiceId(new BigInteger("2000"));
        entity.setUserIns("userInsValue2");
        entity.setUserLastUpd("userLastUpdValue2");
        entity.setVoteDepartmentId(new BigInteger("2000"));
        entity.setVotes(new BigInteger("2000"));
        entity.setManagementDur("managementDurValue2");
        entity.setReceiveNewsletter(new BigInteger("2000"));
        entity.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        entity.setGemiLastStateChangeDate(LocalDate.of(2024, 2, 12));
        entity.setGemiParentGemiNumber("gemiParentGemiNumberValue2");
        entity.setGemiMunicipalityId(new BigInteger("2000"));
        entity.setGemiCity("gemiCityValue2");
        entity.setGemiRegion("gemiRegionValue2");
        entity.setGemiStreet("gemiStreetValue2");
        entity.setGemiStreetNumber("gemiStreetNumberValue2");
        entity.setGemiZipCode("gemiZipCodeValue2");
        entity.setGemiPhone1("gemiPhone1Value2");
        entity.setGemiPhone2("gemiPhone2Value2");
        entity.setGemiPhone3("gemiPhone3Value2");
        entity.setGemiMobile("gemiMobileValue2");
        entity.setGemiFax("gemiFaxValue2");
        entity.setGemiEmail("gemiEmailValue2");
        entity.setGemiCreated(new BigInteger("2000"));
        entity.setGemiId(new BigInteger("2000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        entity.setArticle("articleValue2");
        entity.setShowEmail(new BigInteger("2000"));
        entity.setGemiId2(new BigInteger("2000"));
        entity.setVoteDt(LocalDate.of(2024, 2, 12));
        entity.setVoteFlag('B');
        entity.setVoteEtairiaFlag('B');
        entity.setGemiDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setMeCriteria3Id(new BigInteger("2000"));
        entity.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setCancelReasonDscr("cancelReasonDscrValue2");
        entity.setBankruptDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setStartDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setEndDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setBankruptNumber("bankruptNumberValue2");
        entity.setLastChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setNextCompanyId(new BigInteger("2000"));
        entity.setParentCompanyId(new BigInteger("2000"));
        entity.setPreviousCompanyId(new BigInteger("2000"));
        entity.setTransferFlag(new BigInteger("2000"));
        entity.setTransferAm(new BigInteger("2000"));
        entity.setProegOccupationId(new BigInteger("2000"));
        entity.setProegSubscrAmnt(new BigDecimal("200.50"));
        entity.setProegSubscrYear("proegSubscrYearValue2");
        entity.setProegSubscrDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setProegSubscrNotes("proegSubscrNotesValue2");
        entity.setMigrCapitol(new BigInteger("2000"));
        entity.setMigrCapitol2(new BigDecimal("200.50"));
        entity.setMigrManyChildrenFlag("migrManyChildrenFlagValue2");
        entity.setMigrAmeaFlag("migrAmeaFlagValue2");
        entity.setMigrYpokatFlag("migrYpokatFlagValue2");
        entity.setMigrThrasherFlag("migrThrasherFlagValue2");
        entity.setMigrLowCapitalFlag("migrLowCapitalFlagValue2");
        entity.setMigrSendTaxServFlag("migrSendTaxServFlagValue2");
        entity.setPrintKatastFlag(new BigInteger("2000"));
        entity.setSubscrCalcDate("subscrCalcDateValue2");
        entity.setShowBusinessGuide(new BigInteger("2000"));
        entity.setField7060(20);

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyDto createSampleTemporaryCompanyDto() {
        TemporaryCompanyDto dto = new TemporaryCompanyDto();
        dto.setId(new BigInteger("1000"));
        dto.setVersion(new BigInteger("1000"));
        dto.setAddressCity("addressCityValue1");
        dto.setAddressCountryId(new BigInteger("1000"));
        dto.setAddressLatitude("addressLatitudeValue1");
        dto.setAddressLongitude("addressLongitudeValue1");
        dto.setAddressMunicipalityAlt("addressMunicipalityAltValue1");
        dto.setAddressMunicipalityPriId(new BigInteger("1000"));
        dto.setAddressMunicipalitySecId(new BigInteger("1000"));
        dto.setAddressPoBox("addressPoBoxValue1");
        dto.setAddressPrefectureId(new BigInteger("1000"));
        dto.setAddressRegion("addressRegionValue1");
        dto.setAddressStreet("addressStreetValue1");
        dto.setAddressStreetNumber("addressStreetNumberValue1");
        dto.setAddressZipCode("addressZipCodeValue1");
        dto.setAddressZoomLevel(new BigInteger("1000"));
        dto.setAddressIndicId(new BigInteger("1000"));
        dto.setAfm("afmValue1");
        dto.setAm(new BigInteger("1000"));
        dto.setArmae("armaeValue1");
        dto.setBoardDur("boardDurValue1");
        dto.setBranchTypeId(new BigInteger("1000"));
        dto.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setCancelReasonId(new BigInteger("1000"));
        dto.setCd(new BigInteger("1000"));
        dto.setChamberDepartmentId(new BigInteger("1000"));
        dto.setChamberGemiResponsibleId(new BigInteger("1000"));
        dto.setChamberRegisteredId(new BigInteger("1000"));
        dto.setCoName("coNameValue1");
        dto.setCoNameNrm("coNameNrmValue1");
        dto.setComercRegCode(new BigInteger("1000"));
        dto.setCompanyStatusId(new BigInteger("1000"));
        dto.setContactEmail("contactEmailValue1");
        dto.setContactFacebook("contactFacebookValue1");
        dto.setContactFax("contactFaxValue1");
        dto.setContactMobile("contactMobileValue1");
        dto.setContactPhone1("contactPhone1Value1");
        dto.setContactPhone2("contactPhone2Value1");
        dto.setContactPhone3("contactPhone3Value1");
        dto.setContactPhoneArea("contactPhoneAreaValue1");
        dto.setContactTelex("contactTelexValue1");
        dto.setContactTwitter("contactTwitterValue1");
        dto.setContactUrl("contactUrlValue1");
        dto.setCorporateStatusId(new BigInteger("1000"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDateGemiRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDisputeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDisputeDecDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDisputeNumber("disputeNumberValue1");
        dto.setEdra("edraValue1");
        dto.setEmail2("email2Value1");
        dto.setEmail3("email3Value1");
        dto.setEmail4("email4Value1");
        dto.setEndfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setEuCommerce(new BigInteger("1000"));
        dto.setExpManagementDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setExpireDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setFinancialYearId(new BigInteger("1000"));
        dto.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setGemhOtherPerCd(new BigInteger("1000"));
        dto.setGemiNumber("gemiNumberValue1");
        dto.setHp("hpValue1");
        dto.setIndefinite(new BigInteger("1000"));
        dto.setLastStateChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLicenceExpDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLicenceNo("licenceNoValue1");
        dto.setMailAddress(new BigInteger("1000"));
        dto.setMailName("mailNameValue1");
        dto.setMeCriteria1Id(new BigInteger("1000"));
        dto.setMeCriteria2Id(new BigInteger("1000"));
        dto.setMember(new BigInteger("1000"));
        dto.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setNationalityId(new BigInteger("1000"));
        dto.setNextam(new BigInteger("1000"));
        dto.setObjective("objectiveValue1");
        dto.setOldam("oldamValue1");
        dto.setPendency("pendencyValue1");
        dto.setPending(new BigInteger("1000"));
        dto.setPreviousam(new BigInteger("1000"));
        dto.setRecType("recTypeValue1");
        dto.setRecdeleted(new BigInteger("1000"));
        dto.setRegistrationTypeId(new BigInteger("1000"));
        dto.setSaleTypeId(new BigInteger("1000"));
        dto.setStartfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setSubscrCat(new BigInteger("1000"));
        dto.setTaxServiceId(new BigInteger("1000"));
        dto.setUserIns("userInsValue1");
        dto.setUserLastUpd("userLastUpdValue1");
        dto.setVoteDepartmentId(new BigInteger("1000"));
        dto.setVotes(new BigInteger("1000"));
        dto.setManagementDur("managementDurValue1");
        dto.setReceiveNewsletter(new BigInteger("1000"));
        dto.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        dto.setGemiLastStateChangeDate(LocalDate.of(2024, 1, 11));
        dto.setGemiParentGemiNumber("gemiParentGemiNumberValue1");
        dto.setGemiMunicipalityId(new BigInteger("1000"));
        dto.setGemiCity("gemiCityValue1");
        dto.setGemiRegion("gemiRegionValue1");
        dto.setGemiStreet("gemiStreetValue1");
        dto.setGemiStreetNumber("gemiStreetNumberValue1");
        dto.setGemiZipCode("gemiZipCodeValue1");
        dto.setGemiPhone1("gemiPhone1Value1");
        dto.setGemiPhone2("gemiPhone2Value1");
        dto.setGemiPhone3("gemiPhone3Value1");
        dto.setGemiMobile("gemiMobileValue1");
        dto.setGemiFax("gemiFaxValue1");
        dto.setGemiEmail("gemiEmailValue1");
        dto.setGemiCreated(new BigInteger("1000"));
        dto.setGemiId(new BigInteger("1000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        dto.setArticle("articleValue1");
        dto.setShowEmail(new BigInteger("1000"));
        dto.setGemiId2(new BigInteger("1000"));
        dto.setVoteDt(LocalDate.of(2024, 1, 11));
        dto.setVoteFlag('A');
        dto.setVoteEtairiaFlag('A');
        dto.setGemiDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setMeCriteria3Id(new BigInteger("1000"));
        dto.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setCancelReasonDscr("cancelReasonDscrValue1");
        dto.setBankruptDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setStartDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setEndDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setBankruptNumber("bankruptNumberValue1");
        dto.setLastChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setNextCompanyId(new BigInteger("1000"));
        dto.setParentCompanyId(new BigInteger("1000"));
        dto.setPreviousCompanyId(new BigInteger("1000"));
        dto.setTransferFlag(new BigInteger("1000"));
        dto.setTransferAm(new BigInteger("1000"));
        dto.setProegOccupationId(new BigInteger("1000"));
        dto.setProegSubscrAmnt(new BigDecimal("100.50"));
        dto.setProegSubscrYear("proegSubscrYearValue1");
        dto.setProegSubscrDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setProegSubscrNotes("proegSubscrNotesValue1");
        dto.setMigrCapitol(new BigInteger("1000"));
        dto.setMigrCapitol2(new BigDecimal("100.50"));
        dto.setMigrManyChildrenFlag("migrManyChildrenFlagValue1");
        dto.setMigrAmeaFlag("migrAmeaFlagValue1");
        dto.setMigrYpokatFlag("migrYpokatFlagValue1");
        dto.setMigrThrasherFlag("migrThrasherFlagValue1");
        dto.setMigrLowCapitalFlag("migrLowCapitalFlagValue1");
        dto.setMigrSendTaxServFlag("migrSendTaxServFlagValue1");
        dto.setPrintKatastFlag(new BigInteger("1000"));
        dto.setSubscrCalcDate("subscrCalcDateValue1");
        dto.setShowBusinessGuide(new BigInteger("1000"));
        dto.setField7060(10);

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyDto createAnotherTemporaryCompanyDto() {
        TemporaryCompanyDto dto = new TemporaryCompanyDto();
        dto.setId(new BigInteger("2000"));
        dto.setVersion(new BigInteger("2000"));
        dto.setAddressCity("addressCityValue2");
        dto.setAddressCountryId(new BigInteger("2000"));
        dto.setAddressLatitude("addressLatitudeValue2");
        dto.setAddressLongitude("addressLongitudeValue2");
        dto.setAddressMunicipalityAlt("addressMunicipalityAltValue2");
        dto.setAddressMunicipalityPriId(new BigInteger("2000"));
        dto.setAddressMunicipalitySecId(new BigInteger("2000"));
        dto.setAddressPoBox("addressPoBoxValue2");
        dto.setAddressPrefectureId(new BigInteger("2000"));
        dto.setAddressRegion("addressRegionValue2");
        dto.setAddressStreet("addressStreetValue2");
        dto.setAddressStreetNumber("addressStreetNumberValue2");
        dto.setAddressZipCode("addressZipCodeValue2");
        dto.setAddressZoomLevel(new BigInteger("2000"));
        dto.setAddressIndicId(new BigInteger("2000"));
        dto.setAfm("afmValue2");
        dto.setAm(new BigInteger("2000"));
        dto.setArmae("armaeValue2");
        dto.setBoardDur("boardDurValue2");
        dto.setBranchTypeId(new BigInteger("2000"));
        dto.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setCancelReasonId(new BigInteger("2000"));
        dto.setCd(new BigInteger("2000"));
        dto.setChamberDepartmentId(new BigInteger("2000"));
        dto.setChamberGemiResponsibleId(new BigInteger("2000"));
        dto.setChamberRegisteredId(new BigInteger("2000"));
        dto.setCoName("coNameValue2");
        dto.setCoNameNrm("coNameNrmValue2");
        dto.setComercRegCode(new BigInteger("2000"));
        dto.setCompanyStatusId(new BigInteger("2000"));
        dto.setContactEmail("contactEmailValue2");
        dto.setContactFacebook("contactFacebookValue2");
        dto.setContactFax("contactFaxValue2");
        dto.setContactMobile("contactMobileValue2");
        dto.setContactPhone1("contactPhone1Value2");
        dto.setContactPhone2("contactPhone2Value2");
        dto.setContactPhone3("contactPhone3Value2");
        dto.setContactPhoneArea("contactPhoneAreaValue2");
        dto.setContactTelex("contactTelexValue2");
        dto.setContactTwitter("contactTwitterValue2");
        dto.setContactUrl("contactUrlValue2");
        dto.setCorporateStatusId(new BigInteger("2000"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDateGemiRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDisputeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDisputeDecDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDisputeNumber("disputeNumberValue2");
        dto.setEdra("edraValue2");
        dto.setEmail2("email2Value2");
        dto.setEmail3("email3Value2");
        dto.setEmail4("email4Value2");
        dto.setEndfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setEuCommerce(new BigInteger("2000"));
        dto.setExpManagementDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setExpireDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setFinancialYearId(new BigInteger("2000"));
        dto.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setGemhOtherPerCd(new BigInteger("2000"));
        dto.setGemiNumber("gemiNumberValue2");
        dto.setHp("hpValue2");
        dto.setIndefinite(new BigInteger("2000"));
        dto.setLastStateChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLicenceExpDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLicenceNo("licenceNoValue2");
        dto.setMailAddress(new BigInteger("2000"));
        dto.setMailName("mailNameValue2");
        dto.setMeCriteria1Id(new BigInteger("2000"));
        dto.setMeCriteria2Id(new BigInteger("2000"));
        dto.setMember(new BigInteger("2000"));
        dto.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setNationalityId(new BigInteger("2000"));
        dto.setNextam(new BigInteger("2000"));
        dto.setObjective("objectiveValue2");
        dto.setOldam("oldamValue2");
        dto.setPendency("pendencyValue2");
        dto.setPending(new BigInteger("2000"));
        dto.setPreviousam(new BigInteger("2000"));
        dto.setRecType("recTypeValue2");
        dto.setRecdeleted(new BigInteger("2000"));
        dto.setRegistrationTypeId(new BigInteger("2000"));
        dto.setSaleTypeId(new BigInteger("2000"));
        dto.setStartfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setSubscrCat(new BigInteger("2000"));
        dto.setTaxServiceId(new BigInteger("2000"));
        dto.setUserIns("userInsValue2");
        dto.setUserLastUpd("userLastUpdValue2");
        dto.setVoteDepartmentId(new BigInteger("2000"));
        dto.setVotes(new BigInteger("2000"));
        dto.setManagementDur("managementDurValue2");
        dto.setReceiveNewsletter(new BigInteger("2000"));
        dto.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        dto.setGemiLastStateChangeDate(LocalDate.of(2024, 2, 12));
        dto.setGemiParentGemiNumber("gemiParentGemiNumberValue2");
        dto.setGemiMunicipalityId(new BigInteger("2000"));
        dto.setGemiCity("gemiCityValue2");
        dto.setGemiRegion("gemiRegionValue2");
        dto.setGemiStreet("gemiStreetValue2");
        dto.setGemiStreetNumber("gemiStreetNumberValue2");
        dto.setGemiZipCode("gemiZipCodeValue2");
        dto.setGemiPhone1("gemiPhone1Value2");
        dto.setGemiPhone2("gemiPhone2Value2");
        dto.setGemiPhone3("gemiPhone3Value2");
        dto.setGemiMobile("gemiMobileValue2");
        dto.setGemiFax("gemiFaxValue2");
        dto.setGemiEmail("gemiEmailValue2");
        dto.setGemiCreated(new BigInteger("2000"));
        dto.setGemiId(new BigInteger("2000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        dto.setArticle("articleValue2");
        dto.setShowEmail(new BigInteger("2000"));
        dto.setGemiId2(new BigInteger("2000"));
        dto.setVoteDt(LocalDate.of(2024, 2, 12));
        dto.setVoteFlag('B');
        dto.setVoteEtairiaFlag('B');
        dto.setGemiDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setMeCriteria3Id(new BigInteger("2000"));
        dto.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setCancelReasonDscr("cancelReasonDscrValue2");
        dto.setBankruptDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setStartDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setEndDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setBankruptNumber("bankruptNumberValue2");
        dto.setLastChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setNextCompanyId(new BigInteger("2000"));
        dto.setParentCompanyId(new BigInteger("2000"));
        dto.setPreviousCompanyId(new BigInteger("2000"));
        dto.setTransferFlag(new BigInteger("2000"));
        dto.setTransferAm(new BigInteger("2000"));
        dto.setProegOccupationId(new BigInteger("2000"));
        dto.setProegSubscrAmnt(new BigDecimal("200.50"));
        dto.setProegSubscrYear("proegSubscrYearValue2");
        dto.setProegSubscrDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setProegSubscrNotes("proegSubscrNotesValue2");
        dto.setMigrCapitol(new BigInteger("2000"));
        dto.setMigrCapitol2(new BigDecimal("200.50"));
        dto.setMigrManyChildrenFlag("migrManyChildrenFlagValue2");
        dto.setMigrAmeaFlag("migrAmeaFlagValue2");
        dto.setMigrYpokatFlag("migrYpokatFlagValue2");
        dto.setMigrThrasherFlag("migrThrasherFlagValue2");
        dto.setMigrLowCapitalFlag("migrLowCapitalFlagValue2");
        dto.setMigrSendTaxServFlag("migrSendTaxServFlagValue2");
        dto.setPrintKatastFlag(new BigInteger("2000"));
        dto.setSubscrCalcDate("subscrCalcDateValue2");
        dto.setShowBusinessGuide(new BigInteger("2000"));
        dto.setField7060(20);

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyDto createPatchTemporaryCompanyDto() {
        TemporaryCompanyDto dto = new TemporaryCompanyDto();
        dto.setVersion(new BigInteger("3000"));
        dto.setAddressLatitude("addressLatitudeValue3");
        dto.setAddressMunicipalityPriId(new BigInteger("3000"));
        dto.setAddressMunicipalitySecId(new BigInteger("3000"));
        dto.setAddressPoBox("addressPoBoxValue3");
        dto.setAddressRegion("addressRegionValue3");
        dto.setAddressStreet("addressStreetValue3");
        dto.setAddressStreetNumber("addressStreetNumberValue3");
        dto.setAddressZipCode("addressZipCodeValue3");
        dto.setAddressZoomLevel(new BigInteger("3000"));
        dto.setAddressIndicId(new BigInteger("3000"));
        dto.setAfm("afmValue3");
        dto.setAm(new BigInteger("3000"));
        dto.setArmae("armaeValue3");
        dto.setCancelReasonId(new BigInteger("3000"));
        dto.setCd(new BigInteger("3000"));
        dto.setChamberDepartmentId(new BigInteger("3000"));
        dto.setChamberGemiResponsibleId(new BigInteger("3000"));
        dto.setComercRegCode(new BigInteger("3000"));
        dto.setCompanyStatusId(new BigInteger("3000"));
        dto.setContactEmail("contactEmailValue3");
        dto.setContactFacebook("contactFacebookValue3");
        dto.setContactPhone2("contactPhone2Value3");
        dto.setContactPhone3("contactPhone3Value3");
        dto.setContactTelex("contactTelexValue3");
        dto.setContactUrl("contactUrlValue3");
        dto.setCorporateStatusId(new BigInteger("3000"));
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setDateProfessionStarted(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setDateRegistered(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setDisputeDecDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setDisputeNumber("disputeNumberValue3");
        dto.setEdra("edraValue3");
        dto.setEmail2("email2Value3");
        dto.setEmail4("email4Value3");
        dto.setEndfirstfy(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setExpManagementDt(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setExpireDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setGemhOtherPerCd(new BigInteger("3000"));
        dto.setIndefinite(new BigInteger("3000"));
        dto.setLastStateChangeDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setLicenceExpDt(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setMailAddress(new BigInteger("3000"));
        dto.setMailName("mailNameValue3");
        dto.setMeCriteria1Id(new BigInteger("3000"));
        dto.setMeCriteria2Id(new BigInteger("3000"));
        dto.setMember(new BigInteger("3000"));
        dto.setMemberDues(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setNationalityId(new BigInteger("3000"));
        dto.setNextam(new BigInteger("3000"));
        dto.setPendency("pendencyValue3");
        dto.setRecdeleted(new BigInteger("3000"));
        dto.setRegistrationTypeId(new BigInteger("3000"));
        dto.setSaleTypeId(new BigInteger("3000"));
        dto.setStartfirstfy(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setSubscrCat(new BigInteger("3000"));
        dto.setUserLastUpd("userLastUpdValue3");
        dto.setManagementDur("managementDurValue3");
        dto.setReceiveNewsletter(new BigInteger("3000"));
        dto.setGemiLastUpdated(LocalDate.of(2024, 3, 13));
        dto.setGemiMunicipalityId(new BigInteger("3000"));
        dto.setGemiCity("gemiCityValue3");
        dto.setGemiRegion("gemiRegionValue3");
        dto.setGemiStreet("gemiStreetValue3");
        dto.setGemiStreetNumber("gemiStreetNumberValue3");
        dto.setGemiZipCode("gemiZipCodeValue3");
        dto.setGemiPhone1("gemiPhone1Value3");
        dto.setGemiPhone2("gemiPhone2Value3");
        dto.setGemiMobile("gemiMobileValue3");
        dto.setGemiFax("gemiFaxValue3");
        dto.setGemiEmail("gemiEmailValue3");
        dto.setGemiCreated(new BigInteger("3000"));
        dto.setGemiId(new BigInteger("3000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 3, 13));
        dto.setArticle("articleValue3");
        dto.setGemiId2(new BigInteger("3000"));
        dto.setVoteDt(LocalDate.of(2024, 3, 13));
        dto.setVoteFlag('A');
        dto.setDateInterruption(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setCancelReasonDscr("cancelReasonDscrValue3");
        dto.setLastChangeDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setNextCompanyId(new BigInteger("3000"));
        dto.setParentCompanyId(new BigInteger("3000"));
        dto.setPreviousCompanyId(new BigInteger("3000"));
        dto.setTransferAm(new BigInteger("3000"));
        dto.setProegOccupationId(new BigInteger("3000"));
        dto.setProegSubscrYear("proegSubscrYearValue3");
        dto.setMigrCapitol(new BigInteger("3000"));
        dto.setMigrCapitol2(new BigDecimal("300.50"));
        dto.setMigrManyChildrenFlag("migrManyChildrenFlagValue3");
        dto.setMigrYpokatFlag("migrYpokatFlagValue3");
        dto.setMigrSendTaxServFlag("migrSendTaxServFlagValue3");
        dto.setSubscrCalcDate("subscrCalcDateValue3");
        dto.setField7060(30);

        return dto;
    }

}
