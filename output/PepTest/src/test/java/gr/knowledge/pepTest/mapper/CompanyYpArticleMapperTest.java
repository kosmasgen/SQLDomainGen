package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CompanyYpArticle;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.Municipality;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyYpArticleMapperTest {

    private CompanyYpArticleMapper companyYpArticleMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyYpArticleMapper = new CompanyYpArticleMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyYpArticleToCompanyYpArticleDto() {
        CompanyYpArticle entity = createSampleCompanyYpArticleEntity();
        CompanyYpArticleDto expectedDto = createSampleCompanyYpArticleDto();

        CompanyYpArticleDto actualDto = companyYpArticleMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyYpArticleDtoToCompanyYpArticle() {
        CompanyYpArticleDto dto = createSampleCompanyYpArticleDto();
        CompanyYpArticle expectedEntity = createSampleCompanyYpArticleEntity();

        CompanyYpArticle actualEntity = companyYpArticleMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyYpArticleListToCompanyYpArticleDtoList() {
        List<CompanyYpArticle> entityList = List.of(
                createSampleCompanyYpArticleEntity(),
                createAnotherCompanyYpArticleEntity()
        );
        List<CompanyYpArticleDto> expectedDtoList = List.of(
                createSampleCompanyYpArticleDto(),
                createAnotherCompanyYpArticleDto()
        );

        List<CompanyYpArticleDto> actualDtoList = companyYpArticleMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyYpArticleDtoListToCompanyYpArticleList() {
        List<CompanyYpArticleDto> dtoList = List.of(
                createSampleCompanyYpArticleDto(),
                createAnotherCompanyYpArticleDto()
        );
        List<CompanyYpArticle> expectedEntityList = List.of(
                createSampleCompanyYpArticleEntity(),
                createAnotherCompanyYpArticleEntity()
        );

        List<CompanyYpArticle> actualEntityList = companyYpArticleMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyYpArticle() {
        CompanyYpArticle originalEntity = createSampleCompanyYpArticleEntity();
        CompanyYpArticle actualEntity = createSampleCompanyYpArticleEntity();
        CompanyYpArticleDto patchDto = createPatchCompanyYpArticleDto();
        CompanyYpArticle patchEntity = companyYpArticleMapper.toEntity(patchDto);

        companyYpArticleMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object titleExpectedValue = patchEntity.getTitle() != null ? patchEntity.getTitle() : originalEntity.getTitle();
        assertThat(actualEntity.getTitle())
                .isEqualTo(titleExpectedValue);

        Object htmlExpectedValue = patchEntity.getHtml() != null ? patchEntity.getHtml() : originalEntity.getHtml();
        assertThat(actualEntity.getHtml())
                .isEqualTo(htmlExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object orderSeqExpectedValue = patchEntity.getOrderSeq() != null ? patchEntity.getOrderSeq() : originalEntity.getOrderSeq();
        assertThat(actualEntity.getOrderSeq())
                .isEqualTo(orderSeqExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object isPublishedExpectedValue = patchEntity.getIsPublished() != null ? patchEntity.getIsPublished() : originalEntity.getIsPublished();
        assertThat(actualEntity.getIsPublished())
                .isEqualTo(isPublishedExpectedValue);

        Object companyProfileExpectedValue = patchEntity.getCompanyProfile() != null ? patchEntity.getCompanyProfile() : originalEntity.getCompanyProfile();
        assertThat(actualEntity.getCompanyProfile())
                .usingRecursiveComparison()
                .isEqualTo(companyProfileExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyYpArticleDtoListForNullOrEmptyCompanyYpArticleList() {
        assertThat(companyYpArticleMapper.toDTOList(null)).isEmpty();
        assertThat(companyYpArticleMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyYpArticleListForNullOrEmptyCompanyYpArticleDtoList() {
        assertThat(companyYpArticleMapper.toEntityList(null)).isEmpty();
        assertThat(companyYpArticleMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyYpArticle entity = createSampleCompanyYpArticleEntity();
        CompanyYpArticle expectedEntity = createSampleCompanyYpArticleEntity();

        companyYpArticleMapper.partialUpdate(entity, null);
        companyYpArticleMapper.partialUpdate(null, createPatchCompanyYpArticleDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyYpArticle fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticle createSampleCompanyYpArticleEntity() {
        CompanyYpArticle entity = new CompanyYpArticle();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        Company companyFixture1 = new Company();
        companyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyFixture1.setAfm("afmValue1");
        companyFixture1.setAm(new BigInteger("1000"));
        companyFixture1.setGemiId(new BigInteger("1000"));
        companyFixture1.setCoName("coNameValue1");
        companyFixture1.setChamberCompanyId(new BigInteger("1000"));
        companyFixture1.setChamberId(10);
        companyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setMember(new BigInteger("1000"));
        companyFixture1.setRecType("recTypeValue1");
        companyFixture1.setRecdeleted(true);
        companyFixture1.setAddressCity("addressCityValue1");
        companyFixture1.setAddressLatitude("addressLatitudeValue1");
        companyFixture1.setAddressLongitude("addressLongitudeValue1");
        companyFixture1.setAddressRegion("addressRegionValue1");
        companyFixture1.setAddressStreet("addressStreetValue1");
        companyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        companyFixture1.setAddressZipCode("addressZipCodeValue1");
        companyFixture1.setBranchTypeId(new BigInteger("1000"));
        companyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        companyFixture1.setCoNameNrm("coNameNrmValue1");
        companyFixture1.setContactEmail("contactEmailValue1");
        companyFixture1.setContactMobile("contactMobileValue1");
        companyFixture1.setContactPhone1("contactPhone1Value1");
        companyFixture1.setContactPhone2("contactPhone2Value1");
        companyFixture1.setContactUrl("contactUrlValue1");
        companyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setGemiNumber("gemiNumberValue1");
        companyFixture1.setObjective("objectiveValue1");
        companyFixture1.setReceiveNewsletter(true);
        companyFixture1.setIsChamberCompany(true);
        companyFixture1.setIsTradesCompany(true);
        companyFixture1.setShowBusinessGuide(true);
        companyFixture1.setHasActiveProfiles(true);
        companyFixture1.setIsProteasData(true);
        companyFixture1.setResponsibleName("responsibleNameValue1");
        companyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        companyFixture1.setContactPhone3("contactPhone3Value1");
        companyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        companyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        companyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        companyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyFixture1.setJbDescription("jbDescriptionValue1");
        companyFixture1.setJbNumberEmployees(100L);
        companyFixture1.setJbMotto("jbMottoValue1");
        companyFixture1.setJbEmail("jbEmailValue1");
        companyFixture1.setJbLinkedInUrl("jbLinkedInUrlValue1");
        companyFixture1.setJbFacebookUrl("jbFacebookUrlValue1");
        companyFixture1.setJbRegistrationStatus("jbRegistrationStatusValue1");
        companyFixture1.setJbLogoId("jbLogoIdValue1");
        companyFixture1.setJbCoverId("jbCoverIdValue1");
        companyFixture1.setJbLocationId(10);
        companyFixture1.setJbIndustryId(10);
        companyFixture1.setJbIsvalid(true);
        companyFixture1.setJbActivationStatus("jbActivationStatusValue1");
        entity.setCompany(companyFixture1);
        entity.setTitle("titleValue1");
        entity.setHtml("htmlValue1");
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setOrderSeq(10);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setIsPublished(true);
        CompanyProfile companyProfileFixture1 = new CompanyProfile();
        companyProfileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyProfileFixture1.setName("nameValue1");
        companyProfileFixture1.setAddressCity("addressCityValue1");
        companyProfileFixture1.setAddressLatitude("addressLatitudeValue1");
        companyProfileFixture1.setAddressLongitude("addressLongitudeValue1");
        companyProfileFixture1.setAddressRegion("addressRegionValue1");
        companyProfileFixture1.setAddressStreet("addressStreetValue1");
        companyProfileFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        companyProfileFixture1.setAddressZipCode("addressZipCodeValue1");
        companyProfileFixture1.setContactEmail("contactEmailValue1");
        companyProfileFixture1.setContactMobile("contactMobileValue1");
        companyProfileFixture1.setContactPhone1("contactPhone1Value1");
        companyProfileFixture1.setContactPhone2("contactPhone2Value1");
        companyProfileFixture1.setContactPhone3("contactPhone3Value1");
        companyProfileFixture1.setContactUrl("contactUrlValue1");
        companyProfileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyProfileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyProfileFixture1.setRecdeleted(true);
        companyProfileFixture1.setShowBusinessGuide(true);
        entity.setCompanyProfile(companyProfileFixture1);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticle fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticle createAnotherCompanyYpArticleEntity() {
        CompanyYpArticle entity = new CompanyYpArticle();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        Company companyFixture2 = new Company();
        companyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyFixture2.setAfm("afmValue2");
        companyFixture2.setAm(new BigInteger("2000"));
        companyFixture2.setGemiId(new BigInteger("2000"));
        companyFixture2.setCoName("coNameValue2");
        companyFixture2.setChamberCompanyId(new BigInteger("2000"));
        companyFixture2.setChamberId(20);
        companyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setMember(new BigInteger("2000"));
        companyFixture2.setRecType("recTypeValue2");
        companyFixture2.setRecdeleted(false);
        companyFixture2.setAddressCity("addressCityValue2");
        companyFixture2.setAddressLatitude("addressLatitudeValue2");
        companyFixture2.setAddressLongitude("addressLongitudeValue2");
        companyFixture2.setAddressRegion("addressRegionValue2");
        companyFixture2.setAddressStreet("addressStreetValue2");
        companyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        companyFixture2.setAddressZipCode("addressZipCodeValue2");
        companyFixture2.setBranchTypeId(new BigInteger("2000"));
        companyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        companyFixture2.setCoNameNrm("coNameNrmValue2");
        companyFixture2.setContactEmail("contactEmailValue2");
        companyFixture2.setContactMobile("contactMobileValue2");
        companyFixture2.setContactPhone1("contactPhone1Value2");
        companyFixture2.setContactPhone2("contactPhone2Value2");
        companyFixture2.setContactUrl("contactUrlValue2");
        companyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setGemiNumber("gemiNumberValue2");
        companyFixture2.setObjective("objectiveValue2");
        companyFixture2.setReceiveNewsletter(false);
        companyFixture2.setIsChamberCompany(false);
        companyFixture2.setIsTradesCompany(false);
        companyFixture2.setShowBusinessGuide(false);
        companyFixture2.setHasActiveProfiles(false);
        companyFixture2.setIsProteasData(false);
        companyFixture2.setResponsibleName("responsibleNameValue2");
        companyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        companyFixture2.setContactPhone3("contactPhone3Value2");
        companyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        companyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        companyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        companyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyFixture2.setJbDescription("jbDescriptionValue2");
        companyFixture2.setJbNumberEmployees(200L);
        companyFixture2.setJbMotto("jbMottoValue2");
        companyFixture2.setJbEmail("jbEmailValue2");
        companyFixture2.setJbLinkedInUrl("jbLinkedInUrlValue2");
        companyFixture2.setJbFacebookUrl("jbFacebookUrlValue2");
        companyFixture2.setJbRegistrationStatus("jbRegistrationStatusValue2");
        companyFixture2.setJbLogoId("jbLogoIdValue2");
        companyFixture2.setJbCoverId("jbCoverIdValue2");
        companyFixture2.setJbLocationId(20);
        companyFixture2.setJbIndustryId(20);
        companyFixture2.setJbIsvalid(false);
        companyFixture2.setJbActivationStatus("jbActivationStatusValue2");
        entity.setCompany(companyFixture2);
        entity.setTitle("titleValue2");
        entity.setHtml("htmlValue2");
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setOrderSeq(20);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setIsPublished(false);
        CompanyProfile companyProfileFixture2 = new CompanyProfile();
        companyProfileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyProfileFixture2.setName("nameValue2");
        companyProfileFixture2.setAddressCity("addressCityValue2");
        companyProfileFixture2.setAddressLatitude("addressLatitudeValue2");
        companyProfileFixture2.setAddressLongitude("addressLongitudeValue2");
        companyProfileFixture2.setAddressRegion("addressRegionValue2");
        companyProfileFixture2.setAddressStreet("addressStreetValue2");
        companyProfileFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        companyProfileFixture2.setAddressZipCode("addressZipCodeValue2");
        companyProfileFixture2.setContactEmail("contactEmailValue2");
        companyProfileFixture2.setContactMobile("contactMobileValue2");
        companyProfileFixture2.setContactPhone1("contactPhone1Value2");
        companyProfileFixture2.setContactPhone2("contactPhone2Value2");
        companyProfileFixture2.setContactPhone3("contactPhone3Value2");
        companyProfileFixture2.setContactUrl("contactUrlValue2");
        companyProfileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyProfileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyProfileFixture2.setRecdeleted(false);
        companyProfileFixture2.setShowBusinessGuide(false);
        entity.setCompanyProfile(companyProfileFixture2);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticleDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleDto createSampleCompanyYpArticleDto() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        CompanyDto companyFixture1 = new CompanyDto();
        companyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyFixture1.setAfm("afmValue1");
        companyFixture1.setAm(new BigInteger("1000"));
        companyFixture1.setGemiId(new BigInteger("1000"));
        companyFixture1.setCoName("coNameValue1");
        companyFixture1.setChamberCompanyId(new BigInteger("1000"));
        companyFixture1.setChamberId(10);
        companyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setMember(new BigInteger("1000"));
        companyFixture1.setRecType("recTypeValue1");
        companyFixture1.setRecdeleted(true);
        companyFixture1.setAddressCity("addressCityValue1");
        companyFixture1.setAddressLatitude("addressLatitudeValue1");
        companyFixture1.setAddressLongitude("addressLongitudeValue1");
        companyFixture1.setAddressRegion("addressRegionValue1");
        companyFixture1.setAddressStreet("addressStreetValue1");
        companyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        companyFixture1.setAddressZipCode("addressZipCodeValue1");
        companyFixture1.setBranchTypeId(new BigInteger("1000"));
        companyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        companyFixture1.setCoNameNrm("coNameNrmValue1");
        companyFixture1.setContactEmail("contactEmailValue1");
        companyFixture1.setContactMobile("contactMobileValue1");
        companyFixture1.setContactPhone1("contactPhone1Value1");
        companyFixture1.setContactPhone2("contactPhone2Value1");
        companyFixture1.setContactUrl("contactUrlValue1");
        companyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setGemiNumber("gemiNumberValue1");
        companyFixture1.setObjective("objectiveValue1");
        companyFixture1.setReceiveNewsletter(true);
        companyFixture1.setIsChamberCompany(true);
        companyFixture1.setIsTradesCompany(true);
        companyFixture1.setShowBusinessGuide(true);
        companyFixture1.setHasActiveProfiles(true);
        companyFixture1.setIsProteasData(true);
        companyFixture1.setResponsibleName("responsibleNameValue1");
        companyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        companyFixture1.setContactPhone3("contactPhone3Value1");
        companyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        companyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        companyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        companyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyFixture1.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyFixture1.setJbDescription("jbDescriptionValue1");
        companyFixture1.setJbNumberEmployees(100L);
        companyFixture1.setJbMotto("jbMottoValue1");
        companyFixture1.setJbEmail("jbEmailValue1");
        companyFixture1.setJbLinkedInUrl("jbLinkedInUrlValue1");
        companyFixture1.setJbFacebookUrl("jbFacebookUrlValue1");
        companyFixture1.setJbRegistrationStatus("jbRegistrationStatusValue1");
        companyFixture1.setJbLogoId("jbLogoIdValue1");
        companyFixture1.setJbCoverId("jbCoverIdValue1");
        companyFixture1.setJbLocationId(10);
        companyFixture1.setJbIndustryId(10);
        companyFixture1.setJbIsvalid(true);
        companyFixture1.setJbActivationStatus("jbActivationStatusValue1");
        dto.setCompany(companyFixture1);
        dto.setTitle("titleValue1");
        dto.setHtml("htmlValue1");
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setOrderSeq(10);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setIsPublished(true);
        CompanyProfileDto companyProfileFixture1 = new CompanyProfileDto();
        companyProfileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyProfileFixture1.setName("nameValue1");
        companyProfileFixture1.setAddressCity("addressCityValue1");
        companyProfileFixture1.setAddressLatitude("addressLatitudeValue1");
        companyProfileFixture1.setAddressLongitude("addressLongitudeValue1");
        companyProfileFixture1.setAddressRegion("addressRegionValue1");
        companyProfileFixture1.setAddressStreet("addressStreetValue1");
        companyProfileFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        companyProfileFixture1.setAddressZipCode("addressZipCodeValue1");
        companyProfileFixture1.setContactEmail("contactEmailValue1");
        companyProfileFixture1.setContactMobile("contactMobileValue1");
        companyProfileFixture1.setContactPhone1("contactPhone1Value1");
        companyProfileFixture1.setContactPhone2("contactPhone2Value1");
        companyProfileFixture1.setContactPhone3("contactPhone3Value1");
        companyProfileFixture1.setContactUrl("contactUrlValue1");
        companyProfileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyProfileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyProfileFixture1.setRecdeleted(true);
        companyProfileFixture1.setShowBusinessGuide(true);
        dto.setCompanyProfile(companyProfileFixture1);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleDto createAnotherCompanyYpArticleDto() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        CompanyDto companyFixture2 = new CompanyDto();
        companyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyFixture2.setAfm("afmValue2");
        companyFixture2.setAm(new BigInteger("2000"));
        companyFixture2.setGemiId(new BigInteger("2000"));
        companyFixture2.setCoName("coNameValue2");
        companyFixture2.setChamberCompanyId(new BigInteger("2000"));
        companyFixture2.setChamberId(20);
        companyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setMember(new BigInteger("2000"));
        companyFixture2.setRecType("recTypeValue2");
        companyFixture2.setRecdeleted(false);
        companyFixture2.setAddressCity("addressCityValue2");
        companyFixture2.setAddressLatitude("addressLatitudeValue2");
        companyFixture2.setAddressLongitude("addressLongitudeValue2");
        companyFixture2.setAddressRegion("addressRegionValue2");
        companyFixture2.setAddressStreet("addressStreetValue2");
        companyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        companyFixture2.setAddressZipCode("addressZipCodeValue2");
        companyFixture2.setBranchTypeId(new BigInteger("2000"));
        companyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        companyFixture2.setCoNameNrm("coNameNrmValue2");
        companyFixture2.setContactEmail("contactEmailValue2");
        companyFixture2.setContactMobile("contactMobileValue2");
        companyFixture2.setContactPhone1("contactPhone1Value2");
        companyFixture2.setContactPhone2("contactPhone2Value2");
        companyFixture2.setContactUrl("contactUrlValue2");
        companyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setGemiNumber("gemiNumberValue2");
        companyFixture2.setObjective("objectiveValue2");
        companyFixture2.setReceiveNewsletter(false);
        companyFixture2.setIsChamberCompany(false);
        companyFixture2.setIsTradesCompany(false);
        companyFixture2.setShowBusinessGuide(false);
        companyFixture2.setHasActiveProfiles(false);
        companyFixture2.setIsProteasData(false);
        companyFixture2.setResponsibleName("responsibleNameValue2");
        companyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        companyFixture2.setContactPhone3("contactPhone3Value2");
        companyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        companyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        companyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        companyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyFixture2.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyFixture2.setJbDescription("jbDescriptionValue2");
        companyFixture2.setJbNumberEmployees(200L);
        companyFixture2.setJbMotto("jbMottoValue2");
        companyFixture2.setJbEmail("jbEmailValue2");
        companyFixture2.setJbLinkedInUrl("jbLinkedInUrlValue2");
        companyFixture2.setJbFacebookUrl("jbFacebookUrlValue2");
        companyFixture2.setJbRegistrationStatus("jbRegistrationStatusValue2");
        companyFixture2.setJbLogoId("jbLogoIdValue2");
        companyFixture2.setJbCoverId("jbCoverIdValue2");
        companyFixture2.setJbLocationId(20);
        companyFixture2.setJbIndustryId(20);
        companyFixture2.setJbIsvalid(false);
        companyFixture2.setJbActivationStatus("jbActivationStatusValue2");
        dto.setCompany(companyFixture2);
        dto.setTitle("titleValue2");
        dto.setHtml("htmlValue2");
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setOrderSeq(20);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setIsPublished(false);
        CompanyProfileDto companyProfileFixture2 = new CompanyProfileDto();
        companyProfileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyProfileFixture2.setName("nameValue2");
        companyProfileFixture2.setAddressCity("addressCityValue2");
        companyProfileFixture2.setAddressLatitude("addressLatitudeValue2");
        companyProfileFixture2.setAddressLongitude("addressLongitudeValue2");
        companyProfileFixture2.setAddressRegion("addressRegionValue2");
        companyProfileFixture2.setAddressStreet("addressStreetValue2");
        companyProfileFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        companyProfileFixture2.setAddressZipCode("addressZipCodeValue2");
        companyProfileFixture2.setContactEmail("contactEmailValue2");
        companyProfileFixture2.setContactMobile("contactMobileValue2");
        companyProfileFixture2.setContactPhone1("contactPhone1Value2");
        companyProfileFixture2.setContactPhone2("contactPhone2Value2");
        companyProfileFixture2.setContactPhone3("contactPhone3Value2");
        companyProfileFixture2.setContactUrl("contactUrlValue2");
        companyProfileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyProfileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyProfileFixture2.setRecdeleted(false);
        companyProfileFixture2.setShowBusinessGuide(false);
        dto.setCompanyProfile(companyProfileFixture2);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleDto createPatchCompanyYpArticleDto() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();
        dto.setChamberId(30);
        dto.setHtml("htmlValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setIsPublished(true);
        CompanyProfileDto companyProfileFixture3 = new CompanyProfileDto();
        companyProfileFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        companyProfileFixture3.setAddressLatitude("addressLatitudeValue3");
        companyProfileFixture3.setAddressRegion("addressRegionValue3");
        companyProfileFixture3.setAddressStreet("addressStreetValue3");
        companyProfileFixture3.setAddressStreetNumber("addressStreetNumberValue3");
        companyProfileFixture3.setAddressZipCode("addressZipCodeValue3");
        companyProfileFixture3.setContactEmail("contactEmailValue3");
        companyProfileFixture3.setContactPhone2("contactPhone2Value3");
        companyProfileFixture3.setContactPhone3("contactPhone3Value3");
        companyProfileFixture3.setContactUrl("contactUrlValue3");
        companyProfileFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        companyProfileFixture3.setRecdeleted(true);
        dto.setCompanyProfile(companyProfileFixture3);

        return dto;
    }

}
