package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyProfession;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.entity.Profession;
import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import gr.knowledge.pepTest.entity.ProfessionKind;
import gr.knowledge.pepTest.entity.ProfessionSystem;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyProfessionMapperTest {

    private CompanyProfessionMapper companyProfessionMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyProfessionMapper = new CompanyProfessionMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyProfessionToCompanyProfessionDto() {
        CompanyProfession entity = createSampleCompanyProfessionEntity();
        CompanyProfessionDto expectedDto = createSampleCompanyProfessionDto();

        CompanyProfessionDto actualDto = companyProfessionMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyProfessionDtoToCompanyProfession() {
        CompanyProfessionDto dto = createSampleCompanyProfessionDto();
        CompanyProfession expectedEntity = createSampleCompanyProfessionEntity();

        CompanyProfession actualEntity = companyProfessionMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyProfessionListToCompanyProfessionDtoList() {
        List<CompanyProfession> entityList = List.of(
                createSampleCompanyProfessionEntity(),
                createAnotherCompanyProfessionEntity()
        );
        List<CompanyProfessionDto> expectedDtoList = List.of(
                createSampleCompanyProfessionDto(),
                createAnotherCompanyProfessionDto()
        );

        List<CompanyProfessionDto> actualDtoList = companyProfessionMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyProfessionDtoListToCompanyProfessionList() {
        List<CompanyProfessionDto> dtoList = List.of(
                createSampleCompanyProfessionDto(),
                createAnotherCompanyProfessionDto()
        );
        List<CompanyProfession> expectedEntityList = List.of(
                createSampleCompanyProfessionEntity(),
                createAnotherCompanyProfessionEntity()
        );

        List<CompanyProfession> actualEntityList = companyProfessionMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyProfession() {
        CompanyProfession originalEntity = createSampleCompanyProfessionEntity();
        CompanyProfession actualEntity = createSampleCompanyProfessionEntity();
        CompanyProfessionDto patchDto = createPatchCompanyProfessionDto();
        CompanyProfession patchEntity = companyProfessionMapper.toEntity(patchDto);

        companyProfessionMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberCompanyProfessionIdExpectedValue = patchEntity.getChamberCompanyProfessionId() != null ? patchEntity.getChamberCompanyProfessionId() : originalEntity.getChamberCompanyProfessionId();
        assertThat(actualEntity.getChamberCompanyProfessionId())
                .isEqualTo(chamberCompanyProfessionIdExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object professionExpectedValue = patchEntity.getProfession() != null ? patchEntity.getProfession() : originalEntity.getProfession();
        assertThat(actualEntity.getProfession())
                .usingRecursiveComparison()
                .isEqualTo(professionExpectedValue);

        Object professionKindExpectedValue = patchEntity.getProfessionKind() != null ? patchEntity.getProfessionKind() : originalEntity.getProfessionKind();
        assertThat(actualEntity.getProfessionKind())
                .usingRecursiveComparison()
                .isEqualTo(professionKindExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object fromDateExpectedValue = patchEntity.getFromDate() != null ? patchEntity.getFromDate() : originalEntity.getFromDate();
        assertThat(actualEntity.getFromDate())
                .isEqualTo(fromDateExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object toDateExpectedValue = patchEntity.getToDate() != null ? patchEntity.getToDate() : originalEntity.getToDate();
        assertThat(actualEntity.getToDate())
                .isEqualTo(toDateExpectedValue);

        Object profileExpectedValue = patchEntity.getProfile() != null ? patchEntity.getProfile() : originalEntity.getProfile();
        assertThat(actualEntity.getProfile())
                .usingRecursiveComparison()
                .isEqualTo(profileExpectedValue);

        Object gemiIdExpectedValue = patchEntity.getGemiId() != null ? patchEntity.getGemiId() : originalEntity.getGemiId();
        assertThat(actualEntity.getGemiId())
                .isEqualTo(gemiIdExpectedValue);

        Object gemiDateCreatedExpectedValue = patchEntity.getGemiDateCreated() != null ? patchEntity.getGemiDateCreated() : originalEntity.getGemiDateCreated();
        assertThat(actualEntity.getGemiDateCreated())
                .isEqualTo(gemiDateCreatedExpectedValue);

        Object gemiLastUpdatedExpectedValue = patchEntity.getGemiLastUpdated() != null ? patchEntity.getGemiLastUpdated() : originalEntity.getGemiLastUpdated();
        assertThat(actualEntity.getGemiLastUpdated())
                .isEqualTo(gemiLastUpdatedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyProfessionDtoListForNullOrEmptyCompanyProfessionList() {
        assertThat(companyProfessionMapper.toDTOList(null)).isEmpty();
        assertThat(companyProfessionMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyProfessionListForNullOrEmptyCompanyProfessionDtoList() {
        assertThat(companyProfessionMapper.toEntityList(null)).isEmpty();
        assertThat(companyProfessionMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyProfession entity = createSampleCompanyProfessionEntity();
        CompanyProfession expectedEntity = createSampleCompanyProfessionEntity();

        companyProfessionMapper.partialUpdate(entity, null);
        companyProfessionMapper.partialUpdate(null, createPatchCompanyProfessionDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyProfession fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfession createSampleCompanyProfessionEntity() {
        CompanyProfession entity = new CompanyProfession();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberCompanyProfessionId(10);
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
        Profession professionFixture1 = new Profession();
        professionFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionFixture1.setChamberId(10);
        professionFixture1.setChamberProfessionId(10);
        professionFixture1.setCode("codeValue1");
        professionFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setRecdeleted(true);
        professionFixture1.setProteasId(new BigInteger("1000"));
        entity.setProfession(professionFixture1);
        ProfessionKind professionKindFixture1 = new ProfessionKind();
        professionKindFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionKindFixture1.setChamberId(10);
        professionKindFixture1.setChamberProfKindId(10);
        professionKindFixture1.setCd("cdValue1");
        professionKindFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setRecdeleted(true);
        entity.setProfessionKind(professionKindFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        CompanyProfile profileFixture1 = new CompanyProfile();
        profileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        profileFixture1.setName("nameValue1");
        profileFixture1.setAddressCity("addressCityValue1");
        profileFixture1.setAddressLatitude("addressLatitudeValue1");
        profileFixture1.setAddressLongitude("addressLongitudeValue1");
        profileFixture1.setAddressRegion("addressRegionValue1");
        profileFixture1.setAddressStreet("addressStreetValue1");
        profileFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        profileFixture1.setAddressZipCode("addressZipCodeValue1");
        profileFixture1.setContactEmail("contactEmailValue1");
        profileFixture1.setContactMobile("contactMobileValue1");
        profileFixture1.setContactPhone1("contactPhone1Value1");
        profileFixture1.setContactPhone2("contactPhone2Value1");
        profileFixture1.setContactPhone3("contactPhone3Value1");
        profileFixture1.setContactUrl("contactUrlValue1");
        profileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        profileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        profileFixture1.setRecdeleted(true);
        profileFixture1.setShowBusinessGuide(true);
        entity.setProfile(profileFixture1);
        entity.setGemiId(new BigInteger("1000"));
        entity.setGemiDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setGemiLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return entity;
    }

    /**
     * Creates a populated CompanyProfession fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfession createAnotherCompanyProfessionEntity() {
        CompanyProfession entity = new CompanyProfession();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberCompanyProfessionId(20);
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
        Profession professionFixture2 = new Profession();
        professionFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionFixture2.setChamberId(20);
        professionFixture2.setChamberProfessionId(20);
        professionFixture2.setCode("codeValue2");
        professionFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setRecdeleted(false);
        professionFixture2.setProteasId(new BigInteger("2000"));
        entity.setProfession(professionFixture2);
        ProfessionKind professionKindFixture2 = new ProfessionKind();
        professionKindFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionKindFixture2.setChamberId(20);
        professionKindFixture2.setChamberProfKindId(20);
        professionKindFixture2.setCd("cdValue2");
        professionKindFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setRecdeleted(false);
        entity.setProfessionKind(professionKindFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        CompanyProfile profileFixture2 = new CompanyProfile();
        profileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        profileFixture2.setName("nameValue2");
        profileFixture2.setAddressCity("addressCityValue2");
        profileFixture2.setAddressLatitude("addressLatitudeValue2");
        profileFixture2.setAddressLongitude("addressLongitudeValue2");
        profileFixture2.setAddressRegion("addressRegionValue2");
        profileFixture2.setAddressStreet("addressStreetValue2");
        profileFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        profileFixture2.setAddressZipCode("addressZipCodeValue2");
        profileFixture2.setContactEmail("contactEmailValue2");
        profileFixture2.setContactMobile("contactMobileValue2");
        profileFixture2.setContactPhone1("contactPhone1Value2");
        profileFixture2.setContactPhone2("contactPhone2Value2");
        profileFixture2.setContactPhone3("contactPhone3Value2");
        profileFixture2.setContactUrl("contactUrlValue2");
        profileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        profileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        profileFixture2.setRecdeleted(false);
        profileFixture2.setShowBusinessGuide(false);
        entity.setProfile(profileFixture2);
        entity.setGemiId(new BigInteger("2000"));
        entity.setGemiDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setGemiLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return entity;
    }

    /**
     * Creates a populated CompanyProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfessionDto createSampleCompanyProfessionDto() {
        CompanyProfessionDto dto = new CompanyProfessionDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberCompanyProfessionId(10);
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
        ProfessionDto professionFixture1 = new ProfessionDto();
        professionFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionFixture1.setChamberId(10);
        professionFixture1.setChamberProfessionId(10);
        professionFixture1.setCode("codeValue1");
        professionFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionFixture1.setRecdeleted(true);
        professionFixture1.setProteasId(new BigInteger("1000"));
        dto.setProfession(professionFixture1);
        ProfessionKindDto professionKindFixture1 = new ProfessionKindDto();
        professionKindFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionKindFixture1.setChamberId(10);
        professionKindFixture1.setChamberProfKindId(10);
        professionKindFixture1.setCd("cdValue1");
        professionKindFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setRecdeleted(true);
        dto.setProfessionKind(professionKindFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        CompanyProfileDto profileFixture1 = new CompanyProfileDto();
        profileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        profileFixture1.setName("nameValue1");
        profileFixture1.setAddressCity("addressCityValue1");
        profileFixture1.setAddressLatitude("addressLatitudeValue1");
        profileFixture1.setAddressLongitude("addressLongitudeValue1");
        profileFixture1.setAddressRegion("addressRegionValue1");
        profileFixture1.setAddressStreet("addressStreetValue1");
        profileFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        profileFixture1.setAddressZipCode("addressZipCodeValue1");
        profileFixture1.setContactEmail("contactEmailValue1");
        profileFixture1.setContactMobile("contactMobileValue1");
        profileFixture1.setContactPhone1("contactPhone1Value1");
        profileFixture1.setContactPhone2("contactPhone2Value1");
        profileFixture1.setContactPhone3("contactPhone3Value1");
        profileFixture1.setContactUrl("contactUrlValue1");
        profileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        profileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        profileFixture1.setRecdeleted(true);
        profileFixture1.setShowBusinessGuide(true);
        dto.setProfile(profileFixture1);
        dto.setGemiId(new BigInteger("1000"));
        dto.setGemiDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setGemiLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return dto;
    }

    /**
     * Creates a populated CompanyProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfessionDto createAnotherCompanyProfessionDto() {
        CompanyProfessionDto dto = new CompanyProfessionDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberCompanyProfessionId(20);
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
        ProfessionDto professionFixture2 = new ProfessionDto();
        professionFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionFixture2.setChamberId(20);
        professionFixture2.setChamberProfessionId(20);
        professionFixture2.setCode("codeValue2");
        professionFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionFixture2.setRecdeleted(false);
        professionFixture2.setProteasId(new BigInteger("2000"));
        dto.setProfession(professionFixture2);
        ProfessionKindDto professionKindFixture2 = new ProfessionKindDto();
        professionKindFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionKindFixture2.setChamberId(20);
        professionKindFixture2.setChamberProfKindId(20);
        professionKindFixture2.setCd("cdValue2");
        professionKindFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setRecdeleted(false);
        dto.setProfessionKind(professionKindFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        CompanyProfileDto profileFixture2 = new CompanyProfileDto();
        profileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        profileFixture2.setName("nameValue2");
        profileFixture2.setAddressCity("addressCityValue2");
        profileFixture2.setAddressLatitude("addressLatitudeValue2");
        profileFixture2.setAddressLongitude("addressLongitudeValue2");
        profileFixture2.setAddressRegion("addressRegionValue2");
        profileFixture2.setAddressStreet("addressStreetValue2");
        profileFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        profileFixture2.setAddressZipCode("addressZipCodeValue2");
        profileFixture2.setContactEmail("contactEmailValue2");
        profileFixture2.setContactMobile("contactMobileValue2");
        profileFixture2.setContactPhone1("contactPhone1Value2");
        profileFixture2.setContactPhone2("contactPhone2Value2");
        profileFixture2.setContactPhone3("contactPhone3Value2");
        profileFixture2.setContactUrl("contactUrlValue2");
        profileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        profileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        profileFixture2.setRecdeleted(false);
        profileFixture2.setShowBusinessGuide(false);
        dto.setProfile(profileFixture2);
        dto.setGemiId(new BigInteger("2000"));
        dto.setGemiDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setGemiLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return dto;
    }

    /**
     * Creates a populated CompanyProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfessionDto createPatchCompanyProfessionDto() {
        CompanyProfessionDto dto = new CompanyProfessionDto();
        dto.setChamberId(30);
        ProfessionDto professionFixture3 = new ProfessionDto();
        professionFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        professionFixture3.setChamberId(30);
        professionFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        professionFixture3.setRecdeleted(true);
        dto.setProfession(professionFixture3);
        ProfessionKindDto professionKindFixture3 = new ProfessionKindDto();
        professionKindFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        professionKindFixture3.setChamberId(30);
        professionKindFixture3.setChamberProfKindId(30);
        professionKindFixture3.setCd("cdValue3");
        professionKindFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        professionKindFixture3.setRecdeleted(true);
        dto.setProfessionKind(professionKindFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setFromDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setToDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setGemiId(new BigInteger("3000"));
        dto.setGemiDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setGemiLastUpdated(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
