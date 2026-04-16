package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.TemporaryCompany;
import gr.knowledge.pepTest.entity.TemporaryCompanyi18n;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class TemporaryCompanyi18nMapperTest {

    private TemporaryCompanyi18nMapper temporaryCompanyi18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        temporaryCompanyi18nMapper = new TemporaryCompanyi18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyi18nToTemporaryCompanyi18nDto() {
        TemporaryCompanyi18n entity = createSampleTemporaryCompanyi18nEntity();
        TemporaryCompanyi18nDto expectedDto = createSampleTemporaryCompanyi18nDto();

        TemporaryCompanyi18nDto actualDto = temporaryCompanyi18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyi18nDtoToTemporaryCompanyi18n() {
        TemporaryCompanyi18nDto dto = createSampleTemporaryCompanyi18nDto();
        TemporaryCompanyi18n expectedEntity = createSampleTemporaryCompanyi18nEntity();

        TemporaryCompanyi18n actualEntity = temporaryCompanyi18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyi18nListToTemporaryCompanyi18nDtoList() {
        List<TemporaryCompanyi18n> entityList = List.of(
                createSampleTemporaryCompanyi18nEntity(),
                createAnotherTemporaryCompanyi18nEntity()
        );
        List<TemporaryCompanyi18nDto> expectedDtoList = List.of(
                createSampleTemporaryCompanyi18nDto(),
                createAnotherTemporaryCompanyi18nDto()
        );

        List<TemporaryCompanyi18nDto> actualDtoList = temporaryCompanyi18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyi18nDtoListToTemporaryCompanyi18nList() {
        List<TemporaryCompanyi18nDto> dtoList = List.of(
                createSampleTemporaryCompanyi18nDto(),
                createAnotherTemporaryCompanyi18nDto()
        );
        List<TemporaryCompanyi18n> expectedEntityList = List.of(
                createSampleTemporaryCompanyi18nEntity(),
                createAnotherTemporaryCompanyi18nEntity()
        );

        List<TemporaryCompanyi18n> actualEntityList = temporaryCompanyi18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForTemporaryCompanyi18n() {
        TemporaryCompanyi18n originalEntity = createSampleTemporaryCompanyi18nEntity();
        TemporaryCompanyi18n actualEntity = createSampleTemporaryCompanyi18nEntity();
        TemporaryCompanyi18nDto patchDto = createPatchTemporaryCompanyi18nDto();
        TemporaryCompanyi18n patchEntity = temporaryCompanyi18nMapper.toEntity(patchDto);

        temporaryCompanyi18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object versionExpectedValue = patchEntity.getVersion() != null ? patchEntity.getVersion() : originalEntity.getVersion();
        assertThat(actualEntity.getVersion())
                .isEqualTo(versionExpectedValue);

        Object cityExpectedValue = patchEntity.getCity() != null ? patchEntity.getCity() : originalEntity.getCity();
        assertThat(actualEntity.getCity())
                .isEqualTo(cityExpectedValue);

        Object coNameExpectedValue = patchEntity.getCoName() != null ? patchEntity.getCoName() : originalEntity.getCoName();
        assertThat(actualEntity.getCoName())
                .isEqualTo(coNameExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object mailNameExpectedValue = patchEntity.getMailName() != null ? patchEntity.getMailName() : originalEntity.getMailName();
        assertThat(actualEntity.getMailName())
                .isEqualTo(mailNameExpectedValue);

        Object objectiveExpectedValue = patchEntity.getObjective() != null ? patchEntity.getObjective() : originalEntity.getObjective();
        assertThat(actualEntity.getObjective())
                .isEqualTo(objectiveExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object streetExpectedValue = patchEntity.getStreet() != null ? patchEntity.getStreet() : originalEntity.getStreet();
        assertThat(actualEntity.getStreet())
                .isEqualTo(streetExpectedValue);

        Object commentsExpectedValue = patchEntity.getComments() != null ? patchEntity.getComments() : originalEntity.getComments();
        assertThat(actualEntity.getComments())
                .isEqualTo(commentsExpectedValue);

        Object gemiIdExpectedValue = patchEntity.getGemiId() != null ? patchEntity.getGemiId() : originalEntity.getGemiId();
        assertThat(actualEntity.getGemiId())
                .isEqualTo(gemiIdExpectedValue);

        Object gemiDateCreatedExpectedValue = patchEntity.getGemiDateCreated() != null ? patchEntity.getGemiDateCreated() : originalEntity.getGemiDateCreated();
        assertThat(actualEntity.getGemiDateCreated())
                .isEqualTo(gemiDateCreatedExpectedValue);

        Object gemiLastUpdatedExpectedValue = patchEntity.getGemiLastUpdated() != null ? patchEntity.getGemiLastUpdated() : originalEntity.getGemiLastUpdated();
        assertThat(actualEntity.getGemiLastUpdated())
                .isEqualTo(gemiLastUpdatedExpectedValue);

        Object gemiCityExpectedValue = patchEntity.getGemiCity() != null ? patchEntity.getGemiCity() : originalEntity.getGemiCity();
        assertThat(actualEntity.getGemiCity())
                .isEqualTo(gemiCityExpectedValue);

        Object articleExpectedValue = patchEntity.getArticle() != null ? patchEntity.getArticle() : originalEntity.getArticle();
        assertThat(actualEntity.getArticle())
                .isEqualTo(articleExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyi18nDtoListForNullOrEmptyTemporaryCompanyi18nList() {
        assertThat(temporaryCompanyi18nMapper.toDTOList(null)).isEmpty();
        assertThat(temporaryCompanyi18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyi18nListForNullOrEmptyTemporaryCompanyi18nDtoList() {
        assertThat(temporaryCompanyi18nMapper.toEntityList(null)).isEmpty();
        assertThat(temporaryCompanyi18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        TemporaryCompanyi18n entity = createSampleTemporaryCompanyi18nEntity();
        TemporaryCompanyi18n expectedEntity = createSampleTemporaryCompanyi18nEntity();

        temporaryCompanyi18nMapper.partialUpdate(entity, null);
        temporaryCompanyi18nMapper.partialUpdate(null, createPatchTemporaryCompanyi18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated TemporaryCompanyi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyi18n createSampleTemporaryCompanyi18nEntity() {
        TemporaryCompanyi18n entity = new TemporaryCompanyi18n();
        entity.setId(new BigInteger("1000"));
        entity.setVersion(new BigInteger("1000"));
        entity.setCity("cityValue1");
        entity.setCoName("coNameValue1");
        TemporaryCompany companyFixture1 = new TemporaryCompany();
        companyFixture1.setId(new BigInteger("1000"));
        companyFixture1.setVersion(new BigInteger("1000"));
        companyFixture1.setAddressCity("addressCityValue1");
        companyFixture1.setAddressCountryId(new BigInteger("1000"));
        companyFixture1.setAddressLatitude("addressLatitudeValue1");
        companyFixture1.setAddressLongitude("addressLongitudeValue1");
        companyFixture1.setAddressMunicipalityAlt("addressMunicipalityAltValue1");
        companyFixture1.setAddressMunicipalityPriId(new BigInteger("1000"));
        companyFixture1.setAddressMunicipalitySecId(new BigInteger("1000"));
        companyFixture1.setAddressPoBox("addressPoBoxValue1");
        companyFixture1.setAddressPrefectureId(new BigInteger("1000"));
        companyFixture1.setAddressRegion("addressRegionValue1");
        companyFixture1.setAddressStreet("addressStreetValue1");
        companyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        companyFixture1.setAddressZipCode("addressZipCodeValue1");
        companyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        companyFixture1.setAddressIndicId(new BigInteger("1000"));
        companyFixture1.setAfm("afmValue1");
        companyFixture1.setAm(new BigInteger("1000"));
        companyFixture1.setArmae("armaeValue1");
        companyFixture1.setBoardDur("boardDurValue1");
        companyFixture1.setBranchTypeId(new BigInteger("1000"));
        companyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setCancelReasonId(new BigInteger("1000"));
        companyFixture1.setCd(new BigInteger("1000"));
        companyFixture1.setChamberDepartmentId(new BigInteger("1000"));
        companyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        companyFixture1.setChamberRegisteredId(new BigInteger("1000"));
        companyFixture1.setCoName("coNameValue1");
        companyFixture1.setCoNameNrm("coNameNrmValue1");
        companyFixture1.setComercRegCode(new BigInteger("1000"));
        companyFixture1.setCompanyStatusId(new BigInteger("1000"));
        companyFixture1.setContactEmail("contactEmailValue1");
        companyFixture1.setContactFacebook("contactFacebookValue1");
        companyFixture1.setContactFax("contactFaxValue1");
        companyFixture1.setContactMobile("contactMobileValue1");
        companyFixture1.setContactPhone1("contactPhone1Value1");
        companyFixture1.setContactPhone2("contactPhone2Value1");
        companyFixture1.setContactPhone3("contactPhone3Value1");
        companyFixture1.setContactPhoneArea("contactPhoneAreaValue1");
        companyFixture1.setContactTelex("contactTelexValue1");
        companyFixture1.setContactTwitter("contactTwitterValue1");
        companyFixture1.setContactUrl("contactUrlValue1");
        companyFixture1.setCorporateStatusId(new BigInteger("1000"));
        companyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateGemiRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDisputeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDisputeDecDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDisputeNumber("disputeNumberValue1");
        companyFixture1.setEdra("edraValue1");
        companyFixture1.setEmail2("email2Value1");
        companyFixture1.setEmail3("email3Value1");
        companyFixture1.setEmail4("email4Value1");
        companyFixture1.setEndfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setEuCommerce(new BigInteger("1000"));
        companyFixture1.setExpManagementDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setExpireDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setFinancialYearId(new BigInteger("1000"));
        companyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setGemhOtherPerCd(new BigInteger("1000"));
        companyFixture1.setGemiNumber("gemiNumberValue1");
        companyFixture1.setHp("hpValue1");
        companyFixture1.setIndefinite(new BigInteger("1000"));
        companyFixture1.setLastStateChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLicenceExpDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLicenceNo("licenceNoValue1");
        companyFixture1.setMailAddress(new BigInteger("1000"));
        companyFixture1.setMailName("mailNameValue1");
        companyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        companyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        companyFixture1.setMember(new BigInteger("1000"));
        companyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setNationalityId(new BigInteger("1000"));
        companyFixture1.setNextam(new BigInteger("1000"));
        companyFixture1.setObjective("objectiveValue1");
        companyFixture1.setOldam("oldamValue1");
        companyFixture1.setPendency("pendencyValue1");
        companyFixture1.setPending(new BigInteger("1000"));
        companyFixture1.setPreviousam(new BigInteger("1000"));
        companyFixture1.setRecType("recTypeValue1");
        companyFixture1.setRecdeleted(new BigInteger("1000"));
        companyFixture1.setRegistrationTypeId(new BigInteger("1000"));
        companyFixture1.setSaleTypeId(new BigInteger("1000"));
        companyFixture1.setStartfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setSubscrCat(new BigInteger("1000"));
        companyFixture1.setTaxServiceId(new BigInteger("1000"));
        companyFixture1.setUserIns("userInsValue1");
        companyFixture1.setUserLastUpd("userLastUpdValue1");
        companyFixture1.setVoteDepartmentId(new BigInteger("1000"));
        companyFixture1.setVotes(new BigInteger("1000"));
        companyFixture1.setManagementDur("managementDurValue1");
        companyFixture1.setReceiveNewsletter(new BigInteger("1000"));
        companyFixture1.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        companyFixture1.setGemiLastStateChangeDate(LocalDate.of(2024, 1, 11));
        companyFixture1.setGemiParentGemiNumber("gemiParentGemiNumberValue1");
        companyFixture1.setGemiMunicipalityId(new BigInteger("1000"));
        companyFixture1.setGemiCity("gemiCityValue1");
        companyFixture1.setGemiRegion("gemiRegionValue1");
        companyFixture1.setGemiStreet("gemiStreetValue1");
        companyFixture1.setGemiStreetNumber("gemiStreetNumberValue1");
        companyFixture1.setGemiZipCode("gemiZipCodeValue1");
        companyFixture1.setGemiPhone1("gemiPhone1Value1");
        companyFixture1.setGemiPhone2("gemiPhone2Value1");
        companyFixture1.setGemiPhone3("gemiPhone3Value1");
        companyFixture1.setGemiMobile("gemiMobileValue1");
        companyFixture1.setGemiFax("gemiFaxValue1");
        companyFixture1.setGemiEmail("gemiEmailValue1");
        companyFixture1.setGemiCreated(new BigInteger("1000"));
        companyFixture1.setGemiId(new BigInteger("1000"));
        companyFixture1.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        companyFixture1.setArticle("articleValue1");
        companyFixture1.setShowEmail(new BigInteger("1000"));
        companyFixture1.setGemiId2(new BigInteger("1000"));
        companyFixture1.setVoteDt(LocalDate.of(2024, 1, 11));
        companyFixture1.setVoteFlag('A');
        companyFixture1.setVoteEtairiaFlag('A');
        companyFixture1.setGemiDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        companyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setCancelReasonDscr("cancelReasonDscrValue1");
        companyFixture1.setBankruptDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setStartDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setEndDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setBankruptNumber("bankruptNumberValue1");
        companyFixture1.setLastChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setNextCompanyId(new BigInteger("1000"));
        companyFixture1.setParentCompanyId(new BigInteger("1000"));
        companyFixture1.setPreviousCompanyId(new BigInteger("1000"));
        companyFixture1.setTransferFlag(new BigInteger("1000"));
        companyFixture1.setTransferAm(new BigInteger("1000"));
        companyFixture1.setProegOccupationId(new BigInteger("1000"));
        companyFixture1.setProegSubscrAmnt(new BigDecimal("100.50"));
        companyFixture1.setProegSubscrYear("proegSubscrYearValue1");
        companyFixture1.setProegSubscrDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setProegSubscrNotes("proegSubscrNotesValue1");
        companyFixture1.setMigrCapitol(new BigInteger("1000"));
        companyFixture1.setMigrCapitol2(new BigDecimal("100.50"));
        companyFixture1.setMigrManyChildrenFlag("migrManyChildrenFlagValue1");
        companyFixture1.setMigrAmeaFlag("migrAmeaFlagValue1");
        companyFixture1.setMigrYpokatFlag("migrYpokatFlagValue1");
        companyFixture1.setMigrThrasherFlag("migrThrasherFlagValue1");
        companyFixture1.setMigrLowCapitalFlag("migrLowCapitalFlagValue1");
        companyFixture1.setMigrSendTaxServFlag("migrSendTaxServFlagValue1");
        companyFixture1.setPrintKatastFlag(new BigInteger("1000"));
        companyFixture1.setSubscrCalcDate("subscrCalcDateValue1");
        companyFixture1.setShowBusinessGuide(new BigInteger("1000"));
        companyFixture1.setField7060(10);
        entity.setCompany(companyFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setMailName("mailNameValue1");
        entity.setObjective("objectiveValue1");
        entity.setRecdeleted(new BigInteger("1000"));
        entity.setStreet("streetValue1");
        entity.setComments("commentsValue1");
        entity.setGemiId(new BigInteger("1000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        entity.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        entity.setGemiCity("gemiCityValue1");
        entity.setArticle("articleValue1");

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyi18n createAnotherTemporaryCompanyi18nEntity() {
        TemporaryCompanyi18n entity = new TemporaryCompanyi18n();
        entity.setId(new BigInteger("2000"));
        entity.setVersion(new BigInteger("2000"));
        entity.setCity("cityValue2");
        entity.setCoName("coNameValue2");
        TemporaryCompany companyFixture2 = new TemporaryCompany();
        companyFixture2.setId(new BigInteger("2000"));
        companyFixture2.setVersion(new BigInteger("2000"));
        companyFixture2.setAddressCity("addressCityValue2");
        companyFixture2.setAddressCountryId(new BigInteger("2000"));
        companyFixture2.setAddressLatitude("addressLatitudeValue2");
        companyFixture2.setAddressLongitude("addressLongitudeValue2");
        companyFixture2.setAddressMunicipalityAlt("addressMunicipalityAltValue2");
        companyFixture2.setAddressMunicipalityPriId(new BigInteger("2000"));
        companyFixture2.setAddressMunicipalitySecId(new BigInteger("2000"));
        companyFixture2.setAddressPoBox("addressPoBoxValue2");
        companyFixture2.setAddressPrefectureId(new BigInteger("2000"));
        companyFixture2.setAddressRegion("addressRegionValue2");
        companyFixture2.setAddressStreet("addressStreetValue2");
        companyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        companyFixture2.setAddressZipCode("addressZipCodeValue2");
        companyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        companyFixture2.setAddressIndicId(new BigInteger("2000"));
        companyFixture2.setAfm("afmValue2");
        companyFixture2.setAm(new BigInteger("2000"));
        companyFixture2.setArmae("armaeValue2");
        companyFixture2.setBoardDur("boardDurValue2");
        companyFixture2.setBranchTypeId(new BigInteger("2000"));
        companyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setCancelReasonId(new BigInteger("2000"));
        companyFixture2.setCd(new BigInteger("2000"));
        companyFixture2.setChamberDepartmentId(new BigInteger("2000"));
        companyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        companyFixture2.setChamberRegisteredId(new BigInteger("2000"));
        companyFixture2.setCoName("coNameValue2");
        companyFixture2.setCoNameNrm("coNameNrmValue2");
        companyFixture2.setComercRegCode(new BigInteger("2000"));
        companyFixture2.setCompanyStatusId(new BigInteger("2000"));
        companyFixture2.setContactEmail("contactEmailValue2");
        companyFixture2.setContactFacebook("contactFacebookValue2");
        companyFixture2.setContactFax("contactFaxValue2");
        companyFixture2.setContactMobile("contactMobileValue2");
        companyFixture2.setContactPhone1("contactPhone1Value2");
        companyFixture2.setContactPhone2("contactPhone2Value2");
        companyFixture2.setContactPhone3("contactPhone3Value2");
        companyFixture2.setContactPhoneArea("contactPhoneAreaValue2");
        companyFixture2.setContactTelex("contactTelexValue2");
        companyFixture2.setContactTwitter("contactTwitterValue2");
        companyFixture2.setContactUrl("contactUrlValue2");
        companyFixture2.setCorporateStatusId(new BigInteger("2000"));
        companyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateGemiRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDisputeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDisputeDecDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDisputeNumber("disputeNumberValue2");
        companyFixture2.setEdra("edraValue2");
        companyFixture2.setEmail2("email2Value2");
        companyFixture2.setEmail3("email3Value2");
        companyFixture2.setEmail4("email4Value2");
        companyFixture2.setEndfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setEuCommerce(new BigInteger("2000"));
        companyFixture2.setExpManagementDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setExpireDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setFinancialYearId(new BigInteger("2000"));
        companyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setGemhOtherPerCd(new BigInteger("2000"));
        companyFixture2.setGemiNumber("gemiNumberValue2");
        companyFixture2.setHp("hpValue2");
        companyFixture2.setIndefinite(new BigInteger("2000"));
        companyFixture2.setLastStateChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLicenceExpDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLicenceNo("licenceNoValue2");
        companyFixture2.setMailAddress(new BigInteger("2000"));
        companyFixture2.setMailName("mailNameValue2");
        companyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        companyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        companyFixture2.setMember(new BigInteger("2000"));
        companyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setNationalityId(new BigInteger("2000"));
        companyFixture2.setNextam(new BigInteger("2000"));
        companyFixture2.setObjective("objectiveValue2");
        companyFixture2.setOldam("oldamValue2");
        companyFixture2.setPendency("pendencyValue2");
        companyFixture2.setPending(new BigInteger("2000"));
        companyFixture2.setPreviousam(new BigInteger("2000"));
        companyFixture2.setRecType("recTypeValue2");
        companyFixture2.setRecdeleted(new BigInteger("2000"));
        companyFixture2.setRegistrationTypeId(new BigInteger("2000"));
        companyFixture2.setSaleTypeId(new BigInteger("2000"));
        companyFixture2.setStartfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setSubscrCat(new BigInteger("2000"));
        companyFixture2.setTaxServiceId(new BigInteger("2000"));
        companyFixture2.setUserIns("userInsValue2");
        companyFixture2.setUserLastUpd("userLastUpdValue2");
        companyFixture2.setVoteDepartmentId(new BigInteger("2000"));
        companyFixture2.setVotes(new BigInteger("2000"));
        companyFixture2.setManagementDur("managementDurValue2");
        companyFixture2.setReceiveNewsletter(new BigInteger("2000"));
        companyFixture2.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        companyFixture2.setGemiLastStateChangeDate(LocalDate.of(2024, 2, 12));
        companyFixture2.setGemiParentGemiNumber("gemiParentGemiNumberValue2");
        companyFixture2.setGemiMunicipalityId(new BigInteger("2000"));
        companyFixture2.setGemiCity("gemiCityValue2");
        companyFixture2.setGemiRegion("gemiRegionValue2");
        companyFixture2.setGemiStreet("gemiStreetValue2");
        companyFixture2.setGemiStreetNumber("gemiStreetNumberValue2");
        companyFixture2.setGemiZipCode("gemiZipCodeValue2");
        companyFixture2.setGemiPhone1("gemiPhone1Value2");
        companyFixture2.setGemiPhone2("gemiPhone2Value2");
        companyFixture2.setGemiPhone3("gemiPhone3Value2");
        companyFixture2.setGemiMobile("gemiMobileValue2");
        companyFixture2.setGemiFax("gemiFaxValue2");
        companyFixture2.setGemiEmail("gemiEmailValue2");
        companyFixture2.setGemiCreated(new BigInteger("2000"));
        companyFixture2.setGemiId(new BigInteger("2000"));
        companyFixture2.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        companyFixture2.setArticle("articleValue2");
        companyFixture2.setShowEmail(new BigInteger("2000"));
        companyFixture2.setGemiId2(new BigInteger("2000"));
        companyFixture2.setVoteDt(LocalDate.of(2024, 2, 12));
        companyFixture2.setVoteFlag('B');
        companyFixture2.setVoteEtairiaFlag('B');
        companyFixture2.setGemiDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        companyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setCancelReasonDscr("cancelReasonDscrValue2");
        companyFixture2.setBankruptDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setStartDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setEndDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setBankruptNumber("bankruptNumberValue2");
        companyFixture2.setLastChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setNextCompanyId(new BigInteger("2000"));
        companyFixture2.setParentCompanyId(new BigInteger("2000"));
        companyFixture2.setPreviousCompanyId(new BigInteger("2000"));
        companyFixture2.setTransferFlag(new BigInteger("2000"));
        companyFixture2.setTransferAm(new BigInteger("2000"));
        companyFixture2.setProegOccupationId(new BigInteger("2000"));
        companyFixture2.setProegSubscrAmnt(new BigDecimal("200.50"));
        companyFixture2.setProegSubscrYear("proegSubscrYearValue2");
        companyFixture2.setProegSubscrDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setProegSubscrNotes("proegSubscrNotesValue2");
        companyFixture2.setMigrCapitol(new BigInteger("2000"));
        companyFixture2.setMigrCapitol2(new BigDecimal("200.50"));
        companyFixture2.setMigrManyChildrenFlag("migrManyChildrenFlagValue2");
        companyFixture2.setMigrAmeaFlag("migrAmeaFlagValue2");
        companyFixture2.setMigrYpokatFlag("migrYpokatFlagValue2");
        companyFixture2.setMigrThrasherFlag("migrThrasherFlagValue2");
        companyFixture2.setMigrLowCapitalFlag("migrLowCapitalFlagValue2");
        companyFixture2.setMigrSendTaxServFlag("migrSendTaxServFlagValue2");
        companyFixture2.setPrintKatastFlag(new BigInteger("2000"));
        companyFixture2.setSubscrCalcDate("subscrCalcDateValue2");
        companyFixture2.setShowBusinessGuide(new BigInteger("2000"));
        companyFixture2.setField7060(20);
        entity.setCompany(companyFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setMailName("mailNameValue2");
        entity.setObjective("objectiveValue2");
        entity.setRecdeleted(new BigInteger("2000"));
        entity.setStreet("streetValue2");
        entity.setComments("commentsValue2");
        entity.setGemiId(new BigInteger("2000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        entity.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        entity.setGemiCity("gemiCityValue2");
        entity.setArticle("articleValue2");

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyi18nDto createSampleTemporaryCompanyi18nDto() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();
        dto.setId(new BigInteger("1000"));
        dto.setVersion(new BigInteger("1000"));
        dto.setCity("cityValue1");
        dto.setCoName("coNameValue1");
        TemporaryCompanyDto companyFixture1 = new TemporaryCompanyDto();
        companyFixture1.setId(new BigInteger("1000"));
        companyFixture1.setVersion(new BigInteger("1000"));
        companyFixture1.setAddressCity("addressCityValue1");
        companyFixture1.setAddressCountryId(new BigInteger("1000"));
        companyFixture1.setAddressLatitude("addressLatitudeValue1");
        companyFixture1.setAddressLongitude("addressLongitudeValue1");
        companyFixture1.setAddressMunicipalityAlt("addressMunicipalityAltValue1");
        companyFixture1.setAddressMunicipalityPriId(new BigInteger("1000"));
        companyFixture1.setAddressMunicipalitySecId(new BigInteger("1000"));
        companyFixture1.setAddressPoBox("addressPoBoxValue1");
        companyFixture1.setAddressPrefectureId(new BigInteger("1000"));
        companyFixture1.setAddressRegion("addressRegionValue1");
        companyFixture1.setAddressStreet("addressStreetValue1");
        companyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        companyFixture1.setAddressZipCode("addressZipCodeValue1");
        companyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        companyFixture1.setAddressIndicId(new BigInteger("1000"));
        companyFixture1.setAfm("afmValue1");
        companyFixture1.setAm(new BigInteger("1000"));
        companyFixture1.setArmae("armaeValue1");
        companyFixture1.setBoardDur("boardDurValue1");
        companyFixture1.setBranchTypeId(new BigInteger("1000"));
        companyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setCancelReasonId(new BigInteger("1000"));
        companyFixture1.setCd(new BigInteger("1000"));
        companyFixture1.setChamberDepartmentId(new BigInteger("1000"));
        companyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        companyFixture1.setChamberRegisteredId(new BigInteger("1000"));
        companyFixture1.setCoName("coNameValue1");
        companyFixture1.setCoNameNrm("coNameNrmValue1");
        companyFixture1.setComercRegCode(new BigInteger("1000"));
        companyFixture1.setCompanyStatusId(new BigInteger("1000"));
        companyFixture1.setContactEmail("contactEmailValue1");
        companyFixture1.setContactFacebook("contactFacebookValue1");
        companyFixture1.setContactFax("contactFaxValue1");
        companyFixture1.setContactMobile("contactMobileValue1");
        companyFixture1.setContactPhone1("contactPhone1Value1");
        companyFixture1.setContactPhone2("contactPhone2Value1");
        companyFixture1.setContactPhone3("contactPhone3Value1");
        companyFixture1.setContactPhoneArea("contactPhoneAreaValue1");
        companyFixture1.setContactTelex("contactTelexValue1");
        companyFixture1.setContactTwitter("contactTwitterValue1");
        companyFixture1.setContactUrl("contactUrlValue1");
        companyFixture1.setCorporateStatusId(new BigInteger("1000"));
        companyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateGemiRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDisputeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDisputeDecDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDisputeNumber("disputeNumberValue1");
        companyFixture1.setEdra("edraValue1");
        companyFixture1.setEmail2("email2Value1");
        companyFixture1.setEmail3("email3Value1");
        companyFixture1.setEmail4("email4Value1");
        companyFixture1.setEndfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setEuCommerce(new BigInteger("1000"));
        companyFixture1.setExpManagementDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setExpireDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setFinancialYearId(new BigInteger("1000"));
        companyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setGemhOtherPerCd(new BigInteger("1000"));
        companyFixture1.setGemiNumber("gemiNumberValue1");
        companyFixture1.setHp("hpValue1");
        companyFixture1.setIndefinite(new BigInteger("1000"));
        companyFixture1.setLastStateChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLicenceExpDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLicenceNo("licenceNoValue1");
        companyFixture1.setMailAddress(new BigInteger("1000"));
        companyFixture1.setMailName("mailNameValue1");
        companyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        companyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        companyFixture1.setMember(new BigInteger("1000"));
        companyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setNationalityId(new BigInteger("1000"));
        companyFixture1.setNextam(new BigInteger("1000"));
        companyFixture1.setObjective("objectiveValue1");
        companyFixture1.setOldam("oldamValue1");
        companyFixture1.setPendency("pendencyValue1");
        companyFixture1.setPending(new BigInteger("1000"));
        companyFixture1.setPreviousam(new BigInteger("1000"));
        companyFixture1.setRecType("recTypeValue1");
        companyFixture1.setRecdeleted(new BigInteger("1000"));
        companyFixture1.setRegistrationTypeId(new BigInteger("1000"));
        companyFixture1.setSaleTypeId(new BigInteger("1000"));
        companyFixture1.setStartfirstfy(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setSubscrCat(new BigInteger("1000"));
        companyFixture1.setTaxServiceId(new BigInteger("1000"));
        companyFixture1.setUserIns("userInsValue1");
        companyFixture1.setUserLastUpd("userLastUpdValue1");
        companyFixture1.setVoteDepartmentId(new BigInteger("1000"));
        companyFixture1.setVotes(new BigInteger("1000"));
        companyFixture1.setManagementDur("managementDurValue1");
        companyFixture1.setReceiveNewsletter(new BigInteger("1000"));
        companyFixture1.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        companyFixture1.setGemiLastStateChangeDate(LocalDate.of(2024, 1, 11));
        companyFixture1.setGemiParentGemiNumber("gemiParentGemiNumberValue1");
        companyFixture1.setGemiMunicipalityId(new BigInteger("1000"));
        companyFixture1.setGemiCity("gemiCityValue1");
        companyFixture1.setGemiRegion("gemiRegionValue1");
        companyFixture1.setGemiStreet("gemiStreetValue1");
        companyFixture1.setGemiStreetNumber("gemiStreetNumberValue1");
        companyFixture1.setGemiZipCode("gemiZipCodeValue1");
        companyFixture1.setGemiPhone1("gemiPhone1Value1");
        companyFixture1.setGemiPhone2("gemiPhone2Value1");
        companyFixture1.setGemiPhone3("gemiPhone3Value1");
        companyFixture1.setGemiMobile("gemiMobileValue1");
        companyFixture1.setGemiFax("gemiFaxValue1");
        companyFixture1.setGemiEmail("gemiEmailValue1");
        companyFixture1.setGemiCreated(new BigInteger("1000"));
        companyFixture1.setGemiId(new BigInteger("1000"));
        companyFixture1.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        companyFixture1.setArticle("articleValue1");
        companyFixture1.setShowEmail(new BigInteger("1000"));
        companyFixture1.setGemiId2(new BigInteger("1000"));
        companyFixture1.setVoteDt(LocalDate.of(2024, 1, 11));
        companyFixture1.setVoteFlag('A');
        companyFixture1.setVoteEtairiaFlag('A');
        companyFixture1.setGemiDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        companyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setCancelReasonDscr("cancelReasonDscrValue1");
        companyFixture1.setBankruptDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setStartDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setEndDtCorpStatus(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setBankruptNumber("bankruptNumberValue1");
        companyFixture1.setLastChangeDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setNextCompanyId(new BigInteger("1000"));
        companyFixture1.setParentCompanyId(new BigInteger("1000"));
        companyFixture1.setPreviousCompanyId(new BigInteger("1000"));
        companyFixture1.setTransferFlag(new BigInteger("1000"));
        companyFixture1.setTransferAm(new BigInteger("1000"));
        companyFixture1.setProegOccupationId(new BigInteger("1000"));
        companyFixture1.setProegSubscrAmnt(new BigDecimal("100.50"));
        companyFixture1.setProegSubscrYear("proegSubscrYearValue1");
        companyFixture1.setProegSubscrDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setProegSubscrNotes("proegSubscrNotesValue1");
        companyFixture1.setMigrCapitol(new BigInteger("1000"));
        companyFixture1.setMigrCapitol2(new BigDecimal("100.50"));
        companyFixture1.setMigrManyChildrenFlag("migrManyChildrenFlagValue1");
        companyFixture1.setMigrAmeaFlag("migrAmeaFlagValue1");
        companyFixture1.setMigrYpokatFlag("migrYpokatFlagValue1");
        companyFixture1.setMigrThrasherFlag("migrThrasherFlagValue1");
        companyFixture1.setMigrLowCapitalFlag("migrLowCapitalFlagValue1");
        companyFixture1.setMigrSendTaxServFlag("migrSendTaxServFlagValue1");
        companyFixture1.setPrintKatastFlag(new BigInteger("1000"));
        companyFixture1.setSubscrCalcDate("subscrCalcDateValue1");
        companyFixture1.setShowBusinessGuide(new BigInteger("1000"));
        companyFixture1.setField7060(10);
        dto.setCompany(companyFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setMailName("mailNameValue1");
        dto.setObjective("objectiveValue1");
        dto.setRecdeleted(new BigInteger("1000"));
        dto.setStreet("streetValue1");
        dto.setComments("commentsValue1");
        dto.setGemiId(new BigInteger("1000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        dto.setGemiLastUpdated(LocalDate.of(2024, 1, 11));
        dto.setGemiCity("gemiCityValue1");
        dto.setArticle("articleValue1");

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyi18nDto createAnotherTemporaryCompanyi18nDto() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();
        dto.setId(new BigInteger("2000"));
        dto.setVersion(new BigInteger("2000"));
        dto.setCity("cityValue2");
        dto.setCoName("coNameValue2");
        TemporaryCompanyDto companyFixture2 = new TemporaryCompanyDto();
        companyFixture2.setId(new BigInteger("2000"));
        companyFixture2.setVersion(new BigInteger("2000"));
        companyFixture2.setAddressCity("addressCityValue2");
        companyFixture2.setAddressCountryId(new BigInteger("2000"));
        companyFixture2.setAddressLatitude("addressLatitudeValue2");
        companyFixture2.setAddressLongitude("addressLongitudeValue2");
        companyFixture2.setAddressMunicipalityAlt("addressMunicipalityAltValue2");
        companyFixture2.setAddressMunicipalityPriId(new BigInteger("2000"));
        companyFixture2.setAddressMunicipalitySecId(new BigInteger("2000"));
        companyFixture2.setAddressPoBox("addressPoBoxValue2");
        companyFixture2.setAddressPrefectureId(new BigInteger("2000"));
        companyFixture2.setAddressRegion("addressRegionValue2");
        companyFixture2.setAddressStreet("addressStreetValue2");
        companyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        companyFixture2.setAddressZipCode("addressZipCodeValue2");
        companyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        companyFixture2.setAddressIndicId(new BigInteger("2000"));
        companyFixture2.setAfm("afmValue2");
        companyFixture2.setAm(new BigInteger("2000"));
        companyFixture2.setArmae("armaeValue2");
        companyFixture2.setBoardDur("boardDurValue2");
        companyFixture2.setBranchTypeId(new BigInteger("2000"));
        companyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setCancelReasonId(new BigInteger("2000"));
        companyFixture2.setCd(new BigInteger("2000"));
        companyFixture2.setChamberDepartmentId(new BigInteger("2000"));
        companyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        companyFixture2.setChamberRegisteredId(new BigInteger("2000"));
        companyFixture2.setCoName("coNameValue2");
        companyFixture2.setCoNameNrm("coNameNrmValue2");
        companyFixture2.setComercRegCode(new BigInteger("2000"));
        companyFixture2.setCompanyStatusId(new BigInteger("2000"));
        companyFixture2.setContactEmail("contactEmailValue2");
        companyFixture2.setContactFacebook("contactFacebookValue2");
        companyFixture2.setContactFax("contactFaxValue2");
        companyFixture2.setContactMobile("contactMobileValue2");
        companyFixture2.setContactPhone1("contactPhone1Value2");
        companyFixture2.setContactPhone2("contactPhone2Value2");
        companyFixture2.setContactPhone3("contactPhone3Value2");
        companyFixture2.setContactPhoneArea("contactPhoneAreaValue2");
        companyFixture2.setContactTelex("contactTelexValue2");
        companyFixture2.setContactTwitter("contactTwitterValue2");
        companyFixture2.setContactUrl("contactUrlValue2");
        companyFixture2.setCorporateStatusId(new BigInteger("2000"));
        companyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateGemiRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDisputeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDisputeDecDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDisputeNumber("disputeNumberValue2");
        companyFixture2.setEdra("edraValue2");
        companyFixture2.setEmail2("email2Value2");
        companyFixture2.setEmail3("email3Value2");
        companyFixture2.setEmail4("email4Value2");
        companyFixture2.setEndfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setEuCommerce(new BigInteger("2000"));
        companyFixture2.setExpManagementDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setExpireDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setFinancialYearId(new BigInteger("2000"));
        companyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setGemhOtherPerCd(new BigInteger("2000"));
        companyFixture2.setGemiNumber("gemiNumberValue2");
        companyFixture2.setHp("hpValue2");
        companyFixture2.setIndefinite(new BigInteger("2000"));
        companyFixture2.setLastStateChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLicenceExpDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLicenceNo("licenceNoValue2");
        companyFixture2.setMailAddress(new BigInteger("2000"));
        companyFixture2.setMailName("mailNameValue2");
        companyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        companyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        companyFixture2.setMember(new BigInteger("2000"));
        companyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setNationalityId(new BigInteger("2000"));
        companyFixture2.setNextam(new BigInteger("2000"));
        companyFixture2.setObjective("objectiveValue2");
        companyFixture2.setOldam("oldamValue2");
        companyFixture2.setPendency("pendencyValue2");
        companyFixture2.setPending(new BigInteger("2000"));
        companyFixture2.setPreviousam(new BigInteger("2000"));
        companyFixture2.setRecType("recTypeValue2");
        companyFixture2.setRecdeleted(new BigInteger("2000"));
        companyFixture2.setRegistrationTypeId(new BigInteger("2000"));
        companyFixture2.setSaleTypeId(new BigInteger("2000"));
        companyFixture2.setStartfirstfy(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setSubscrCat(new BigInteger("2000"));
        companyFixture2.setTaxServiceId(new BigInteger("2000"));
        companyFixture2.setUserIns("userInsValue2");
        companyFixture2.setUserLastUpd("userLastUpdValue2");
        companyFixture2.setVoteDepartmentId(new BigInteger("2000"));
        companyFixture2.setVotes(new BigInteger("2000"));
        companyFixture2.setManagementDur("managementDurValue2");
        companyFixture2.setReceiveNewsletter(new BigInteger("2000"));
        companyFixture2.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        companyFixture2.setGemiLastStateChangeDate(LocalDate.of(2024, 2, 12));
        companyFixture2.setGemiParentGemiNumber("gemiParentGemiNumberValue2");
        companyFixture2.setGemiMunicipalityId(new BigInteger("2000"));
        companyFixture2.setGemiCity("gemiCityValue2");
        companyFixture2.setGemiRegion("gemiRegionValue2");
        companyFixture2.setGemiStreet("gemiStreetValue2");
        companyFixture2.setGemiStreetNumber("gemiStreetNumberValue2");
        companyFixture2.setGemiZipCode("gemiZipCodeValue2");
        companyFixture2.setGemiPhone1("gemiPhone1Value2");
        companyFixture2.setGemiPhone2("gemiPhone2Value2");
        companyFixture2.setGemiPhone3("gemiPhone3Value2");
        companyFixture2.setGemiMobile("gemiMobileValue2");
        companyFixture2.setGemiFax("gemiFaxValue2");
        companyFixture2.setGemiEmail("gemiEmailValue2");
        companyFixture2.setGemiCreated(new BigInteger("2000"));
        companyFixture2.setGemiId(new BigInteger("2000"));
        companyFixture2.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        companyFixture2.setArticle("articleValue2");
        companyFixture2.setShowEmail(new BigInteger("2000"));
        companyFixture2.setGemiId2(new BigInteger("2000"));
        companyFixture2.setVoteDt(LocalDate.of(2024, 2, 12));
        companyFixture2.setVoteFlag('B');
        companyFixture2.setVoteEtairiaFlag('B');
        companyFixture2.setGemiDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        companyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setCancelReasonDscr("cancelReasonDscrValue2");
        companyFixture2.setBankruptDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setStartDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setEndDtCorpStatus(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setBankruptNumber("bankruptNumberValue2");
        companyFixture2.setLastChangeDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setNextCompanyId(new BigInteger("2000"));
        companyFixture2.setParentCompanyId(new BigInteger("2000"));
        companyFixture2.setPreviousCompanyId(new BigInteger("2000"));
        companyFixture2.setTransferFlag(new BigInteger("2000"));
        companyFixture2.setTransferAm(new BigInteger("2000"));
        companyFixture2.setProegOccupationId(new BigInteger("2000"));
        companyFixture2.setProegSubscrAmnt(new BigDecimal("200.50"));
        companyFixture2.setProegSubscrYear("proegSubscrYearValue2");
        companyFixture2.setProegSubscrDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setProegSubscrNotes("proegSubscrNotesValue2");
        companyFixture2.setMigrCapitol(new BigInteger("2000"));
        companyFixture2.setMigrCapitol2(new BigDecimal("200.50"));
        companyFixture2.setMigrManyChildrenFlag("migrManyChildrenFlagValue2");
        companyFixture2.setMigrAmeaFlag("migrAmeaFlagValue2");
        companyFixture2.setMigrYpokatFlag("migrYpokatFlagValue2");
        companyFixture2.setMigrThrasherFlag("migrThrasherFlagValue2");
        companyFixture2.setMigrLowCapitalFlag("migrLowCapitalFlagValue2");
        companyFixture2.setMigrSendTaxServFlag("migrSendTaxServFlagValue2");
        companyFixture2.setPrintKatastFlag(new BigInteger("2000"));
        companyFixture2.setSubscrCalcDate("subscrCalcDateValue2");
        companyFixture2.setShowBusinessGuide(new BigInteger("2000"));
        companyFixture2.setField7060(20);
        dto.setCompany(companyFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setMailName("mailNameValue2");
        dto.setObjective("objectiveValue2");
        dto.setRecdeleted(new BigInteger("2000"));
        dto.setStreet("streetValue2");
        dto.setComments("commentsValue2");
        dto.setGemiId(new BigInteger("2000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        dto.setGemiLastUpdated(LocalDate.of(2024, 2, 12));
        dto.setGemiCity("gemiCityValue2");
        dto.setArticle("articleValue2");

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyi18nDto createPatchTemporaryCompanyi18nDto() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();
        dto.setVersion(new BigInteger("3000"));
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setMailName("mailNameValue3");
        dto.setRecdeleted(new BigInteger("3000"));
        dto.setStreet("streetValue3");
        dto.setComments("commentsValue3");
        dto.setGemiId(new BigInteger("3000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 3, 13));
        dto.setGemiLastUpdated(LocalDate.of(2024, 3, 13));
        dto.setGemiCity("gemiCityValue3");
        dto.setArticle("articleValue3");

        return dto;
    }

}
