package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.Municipality;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyProfileMapperTest {

    private CompanyProfileMapper companyProfileMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyProfileMapper = new CompanyProfileMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyProfileToCompanyProfileDto() {
        CompanyProfile entity = createSampleCompanyProfileEntity();
        CompanyProfileDto expectedDto = createSampleCompanyProfileDto();

        CompanyProfileDto actualDto = companyProfileMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyProfileDtoToCompanyProfile() {
        CompanyProfileDto dto = createSampleCompanyProfileDto();
        CompanyProfile expectedEntity = createSampleCompanyProfileEntity();

        CompanyProfile actualEntity = companyProfileMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyProfileListToCompanyProfileDtoList() {
        List<CompanyProfile> entityList = List.of(
                createSampleCompanyProfileEntity(),
                createAnotherCompanyProfileEntity()
        );
        List<CompanyProfileDto> expectedDtoList = List.of(
                createSampleCompanyProfileDto(),
                createAnotherCompanyProfileDto()
        );

        List<CompanyProfileDto> actualDtoList = companyProfileMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyProfileDtoListToCompanyProfileList() {
        List<CompanyProfileDto> dtoList = List.of(
                createSampleCompanyProfileDto(),
                createAnotherCompanyProfileDto()
        );
        List<CompanyProfile> expectedEntityList = List.of(
                createSampleCompanyProfileEntity(),
                createAnotherCompanyProfileEntity()
        );

        List<CompanyProfile> actualEntityList = companyProfileMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyProfile() {
        CompanyProfile originalEntity = createSampleCompanyProfileEntity();
        CompanyProfile actualEntity = createSampleCompanyProfileEntity();
        CompanyProfileDto patchDto = createPatchCompanyProfileDto();
        CompanyProfile patchEntity = companyProfileMapper.toEntity(patchDto);

        companyProfileMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object nameExpectedValue = patchEntity.getName() != null ? patchEntity.getName() : originalEntity.getName();
        assertThat(actualEntity.getName())
                .isEqualTo(nameExpectedValue);

        Object addressCityExpectedValue = patchEntity.getAddressCity() != null ? patchEntity.getAddressCity() : originalEntity.getAddressCity();
        assertThat(actualEntity.getAddressCity())
                .isEqualTo(addressCityExpectedValue);

        Object addressLatitudeExpectedValue = patchEntity.getAddressLatitude() != null ? patchEntity.getAddressLatitude() : originalEntity.getAddressLatitude();
        assertThat(actualEntity.getAddressLatitude())
                .isEqualTo(addressLatitudeExpectedValue);

        Object addressLongitudeExpectedValue = patchEntity.getAddressLongitude() != null ? patchEntity.getAddressLongitude() : originalEntity.getAddressLongitude();
        assertThat(actualEntity.getAddressLongitude())
                .isEqualTo(addressLongitudeExpectedValue);

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

        Object contactEmailExpectedValue = patchEntity.getContactEmail() != null ? patchEntity.getContactEmail() : originalEntity.getContactEmail();
        assertThat(actualEntity.getContactEmail())
                .isEqualTo(contactEmailExpectedValue);

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

        Object contactUrlExpectedValue = patchEntity.getContactUrl() != null ? patchEntity.getContactUrl() : originalEntity.getContactUrl();
        assertThat(actualEntity.getContactUrl())
                .isEqualTo(contactUrlExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object businessLocationExpectedValue = patchEntity.getBusinessLocation() != null ? patchEntity.getBusinessLocation() : originalEntity.getBusinessLocation();
        assertThat(actualEntity.getBusinessLocation())
                .usingRecursiveComparison()
                .isEqualTo(businessLocationExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object showBusinessGuideExpectedValue = patchEntity.getShowBusinessGuide() != null ? patchEntity.getShowBusinessGuide() : originalEntity.getShowBusinessGuide();
        assertThat(actualEntity.getShowBusinessGuide())
                .isEqualTo(showBusinessGuideExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyProfileDtoListForNullOrEmptyCompanyProfileList() {
        assertThat(companyProfileMapper.toDTOList(null)).isEmpty();
        assertThat(companyProfileMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyProfileListForNullOrEmptyCompanyProfileDtoList() {
        assertThat(companyProfileMapper.toEntityList(null)).isEmpty();
        assertThat(companyProfileMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyProfile entity = createSampleCompanyProfileEntity();
        CompanyProfile expectedEntity = createSampleCompanyProfileEntity();

        companyProfileMapper.partialUpdate(entity, null);
        companyProfileMapper.partialUpdate(null, createPatchCompanyProfileDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyProfile fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfile createSampleCompanyProfileEntity() {
        CompanyProfile entity = new CompanyProfile();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setName("nameValue1");
        entity.setAddressCity("addressCityValue1");
        entity.setAddressLatitude("addressLatitudeValue1");
        entity.setAddressLongitude("addressLongitudeValue1");
        entity.setAddressRegion("addressRegionValue1");
        entity.setAddressStreet("addressStreetValue1");
        entity.setAddressStreetNumber("addressStreetNumberValue1");
        entity.setAddressZipCode("addressZipCodeValue1");
        entity.setContactEmail("contactEmailValue1");
        entity.setContactMobile("contactMobileValue1");
        entity.setContactPhone1("contactPhone1Value1");
        entity.setContactPhone2("contactPhone2Value1");
        entity.setContactPhone3("contactPhone3Value1");
        entity.setContactUrl("contactUrlValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        BusinessLocation businessLocationFixture1 = new BusinessLocation();
        businessLocationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        businessLocationFixture1.setCode("codeValue1");
        businessLocationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setRecdeleted(true);
        businessLocationFixture1.setBlobUri("blobUriValue1");
        entity.setBusinessLocation(businessLocationFixture1);
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
        entity.setRecdeleted(true);
        entity.setShowBusinessGuide(true);

        return entity;
    }

    /**
     * Creates a populated CompanyProfile fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfile createAnotherCompanyProfileEntity() {
        CompanyProfile entity = new CompanyProfile();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setName("nameValue2");
        entity.setAddressCity("addressCityValue2");
        entity.setAddressLatitude("addressLatitudeValue2");
        entity.setAddressLongitude("addressLongitudeValue2");
        entity.setAddressRegion("addressRegionValue2");
        entity.setAddressStreet("addressStreetValue2");
        entity.setAddressStreetNumber("addressStreetNumberValue2");
        entity.setAddressZipCode("addressZipCodeValue2");
        entity.setContactEmail("contactEmailValue2");
        entity.setContactMobile("contactMobileValue2");
        entity.setContactPhone1("contactPhone1Value2");
        entity.setContactPhone2("contactPhone2Value2");
        entity.setContactPhone3("contactPhone3Value2");
        entity.setContactUrl("contactUrlValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        BusinessLocation businessLocationFixture2 = new BusinessLocation();
        businessLocationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        businessLocationFixture2.setCode("codeValue2");
        businessLocationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setRecdeleted(false);
        businessLocationFixture2.setBlobUri("blobUriValue2");
        entity.setBusinessLocation(businessLocationFixture2);
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
        entity.setRecdeleted(false);
        entity.setShowBusinessGuide(false);

        return entity;
    }

    /**
     * Creates a populated CompanyProfileDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileDto createSampleCompanyProfileDto() {
        CompanyProfileDto dto = new CompanyProfileDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setName("nameValue1");
        dto.setAddressCity("addressCityValue1");
        dto.setAddressLatitude("addressLatitudeValue1");
        dto.setAddressLongitude("addressLongitudeValue1");
        dto.setAddressRegion("addressRegionValue1");
        dto.setAddressStreet("addressStreetValue1");
        dto.setAddressStreetNumber("addressStreetNumberValue1");
        dto.setAddressZipCode("addressZipCodeValue1");
        dto.setContactEmail("contactEmailValue1");
        dto.setContactMobile("contactMobileValue1");
        dto.setContactPhone1("contactPhone1Value1");
        dto.setContactPhone2("contactPhone2Value1");
        dto.setContactPhone3("contactPhone3Value1");
        dto.setContactUrl("contactUrlValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        BusinessLocationDto businessLocationFixture1 = new BusinessLocationDto();
        businessLocationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        businessLocationFixture1.setCode("codeValue1");
        businessLocationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setRecdeleted(true);
        businessLocationFixture1.setBlobUri("blobUriValue1");
        dto.setBusinessLocation(businessLocationFixture1);
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
        dto.setRecdeleted(true);
        dto.setShowBusinessGuide(true);

        return dto;
    }

    /**
     * Creates a populated CompanyProfileDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileDto createAnotherCompanyProfileDto() {
        CompanyProfileDto dto = new CompanyProfileDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setName("nameValue2");
        dto.setAddressCity("addressCityValue2");
        dto.setAddressLatitude("addressLatitudeValue2");
        dto.setAddressLongitude("addressLongitudeValue2");
        dto.setAddressRegion("addressRegionValue2");
        dto.setAddressStreet("addressStreetValue2");
        dto.setAddressStreetNumber("addressStreetNumberValue2");
        dto.setAddressZipCode("addressZipCodeValue2");
        dto.setContactEmail("contactEmailValue2");
        dto.setContactMobile("contactMobileValue2");
        dto.setContactPhone1("contactPhone1Value2");
        dto.setContactPhone2("contactPhone2Value2");
        dto.setContactPhone3("contactPhone3Value2");
        dto.setContactUrl("contactUrlValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        BusinessLocationDto businessLocationFixture2 = new BusinessLocationDto();
        businessLocationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        businessLocationFixture2.setCode("codeValue2");
        businessLocationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setRecdeleted(false);
        businessLocationFixture2.setBlobUri("blobUriValue2");
        dto.setBusinessLocation(businessLocationFixture2);
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
        dto.setRecdeleted(false);
        dto.setShowBusinessGuide(false);

        return dto;
    }

    /**
     * Creates a populated CompanyProfileDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileDto createPatchCompanyProfileDto() {
        CompanyProfileDto dto = new CompanyProfileDto();
        dto.setAddressLatitude("addressLatitudeValue3");
        dto.setAddressRegion("addressRegionValue3");
        dto.setAddressStreet("addressStreetValue3");
        dto.setAddressStreetNumber("addressStreetNumberValue3");
        dto.setAddressZipCode("addressZipCodeValue3");
        dto.setContactEmail("contactEmailValue3");
        dto.setContactPhone2("contactPhone2Value3");
        dto.setContactPhone3("contactPhone3Value3");
        dto.setContactUrl("contactUrlValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        BusinessLocationDto businessLocationFixture3 = new BusinessLocationDto();
        businessLocationFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        businessLocationFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        businessLocationFixture3.setRecdeleted(true);
        businessLocationFixture3.setBlobUri("blobUriValue3");
        dto.setBusinessLocation(businessLocationFixture3);
        dto.setRecdeleted(true);

        return dto;
    }

}
