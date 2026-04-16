package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.entity.AuditTrail;
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

class AuditTrailMapperTest {

    private AuditTrailMapper auditTrailMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        auditTrailMapper = new AuditTrailMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapAuditTrailToAuditTrailDto() {
        AuditTrail entity = createSampleAuditTrailEntity();
        AuditTrailDto expectedDto = createSampleAuditTrailDto();

        AuditTrailDto actualDto = auditTrailMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapAuditTrailDtoToAuditTrail() {
        AuditTrailDto dto = createSampleAuditTrailDto();
        AuditTrail expectedEntity = createSampleAuditTrailEntity();

        AuditTrail actualEntity = auditTrailMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapAuditTrailListToAuditTrailDtoList() {
        List<AuditTrail> entityList = List.of(
                createSampleAuditTrailEntity(),
                createAnotherAuditTrailEntity()
        );
        List<AuditTrailDto> expectedDtoList = List.of(
                createSampleAuditTrailDto(),
                createAnotherAuditTrailDto()
        );

        List<AuditTrailDto> actualDtoList = auditTrailMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapAuditTrailDtoListToAuditTrailList() {
        List<AuditTrailDto> dtoList = List.of(
                createSampleAuditTrailDto(),
                createAnotherAuditTrailDto()
        );
        List<AuditTrail> expectedEntityList = List.of(
                createSampleAuditTrailEntity(),
                createAnotherAuditTrailEntity()
        );

        List<AuditTrail> actualEntityList = auditTrailMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForAuditTrail() {
        AuditTrail originalEntity = createSampleAuditTrailEntity();
        AuditTrail actualEntity = createSampleAuditTrailEntity();
        AuditTrailDto patchDto = createPatchAuditTrailDto();
        AuditTrail patchEntity = auditTrailMapper.toEntity(patchDto);

        auditTrailMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object ipExpectedValue = patchEntity.getIp() != null ? patchEntity.getIp() : originalEntity.getIp();
        assertThat(actualEntity.getIp())
                .isEqualTo(ipExpectedValue);

        Object completeUriExpectedValue = patchEntity.getCompleteUri() != null ? patchEntity.getCompleteUri() : originalEntity.getCompleteUri();
        assertThat(actualEntity.getCompleteUri())
                .isEqualTo(completeUriExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object profileExpectedValue = patchEntity.getProfile() != null ? patchEntity.getProfile() : originalEntity.getProfile();
        assertThat(actualEntity.getProfile())
                .usingRecursiveComparison()
                .isEqualTo(profileExpectedValue);

        Object uriPathExpectedValue = patchEntity.getUriPath() != null ? patchEntity.getUriPath() : originalEntity.getUriPath();
        assertThat(actualEntity.getUriPath())
                .isEqualTo(uriPathExpectedValue);

        Object countryExpectedValue = patchEntity.getCountry() != null ? patchEntity.getCountry() : originalEntity.getCountry();
        assertThat(actualEntity.getCountry())
                .usingRecursiveComparison()
                .isEqualTo(countryExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyAuditTrailDtoListForNullOrEmptyAuditTrailList() {
        assertThat(auditTrailMapper.toDTOList(null)).isEmpty();
        assertThat(auditTrailMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyAuditTrailListForNullOrEmptyAuditTrailDtoList() {
        assertThat(auditTrailMapper.toEntityList(null)).isEmpty();
        assertThat(auditTrailMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        AuditTrail entity = createSampleAuditTrailEntity();
        AuditTrail expectedEntity = createSampleAuditTrailEntity();

        auditTrailMapper.partialUpdate(entity, null);
        auditTrailMapper.partialUpdate(null, createPatchAuditTrailDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated AuditTrail fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private AuditTrail createSampleAuditTrailEntity() {
        AuditTrail entity = new AuditTrail();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setIp("ipValue1");
        entity.setCompleteUri("completeUriValue1");
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
        entity.setUriPath("uriPathValue1");
        Country countryFixture1 = new Country();
        countryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setRecdeleted(true);
        countryFixture1.setChamberId(10);
        countryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setChamberCountryId(10);
        entity.setCountry(countryFixture1);

        return entity;
    }

    /**
     * Creates a populated AuditTrail fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private AuditTrail createAnotherAuditTrailEntity() {
        AuditTrail entity = new AuditTrail();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setIp("ipValue2");
        entity.setCompleteUri("completeUriValue2");
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
        entity.setUriPath("uriPathValue2");
        Country countryFixture2 = new Country();
        countryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setRecdeleted(false);
        countryFixture2.setChamberId(20);
        countryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setChamberCountryId(20);
        entity.setCountry(countryFixture2);

        return entity;
    }

    /**
     * Creates a populated AuditTrailDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private AuditTrailDto createSampleAuditTrailDto() {
        AuditTrailDto dto = new AuditTrailDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setIp("ipValue1");
        dto.setCompleteUri("completeUriValue1");
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
        dto.setUriPath("uriPathValue1");
        CountryDto countryFixture1 = new CountryDto();
        countryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setRecdeleted(true);
        countryFixture1.setChamberId(10);
        countryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setChamberCountryId(10);
        dto.setCountry(countryFixture1);

        return dto;
    }

    /**
     * Creates a populated AuditTrailDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private AuditTrailDto createAnotherAuditTrailDto() {
        AuditTrailDto dto = new AuditTrailDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setIp("ipValue2");
        dto.setCompleteUri("completeUriValue2");
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
        dto.setUriPath("uriPathValue2");
        CountryDto countryFixture2 = new CountryDto();
        countryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setRecdeleted(false);
        countryFixture2.setChamberId(20);
        countryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setChamberCountryId(20);
        dto.setCountry(countryFixture2);

        return dto;
    }

    /**
     * Creates a populated AuditTrailDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private AuditTrailDto createPatchAuditTrailDto() {
        AuditTrailDto dto = new AuditTrailDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setIp("ipValue3");
        dto.setCompleteUri("completeUriValue3");
        CountryDto countryFixture3 = new CountryDto();
        countryFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        countryFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        countryFixture3.setRecdeleted(true);
        countryFixture3.setChamberId(30);
        countryFixture3.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setCountry(countryFixture3);

        return dto;
    }

}
