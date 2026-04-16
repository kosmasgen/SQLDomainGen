package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberAppUser;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
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

class ChamberAppUserMapperTest {

    private ChamberAppUserMapper chamberAppUserMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        chamberAppUserMapper = new ChamberAppUserMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapChamberAppUserToChamberAppUserDto() {
        ChamberAppUser entity = createSampleChamberAppUserEntity();
        ChamberAppUserDto expectedDto = createSampleChamberAppUserDto();

        ChamberAppUserDto actualDto = chamberAppUserMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapChamberAppUserDtoToChamberAppUser() {
        ChamberAppUserDto dto = createSampleChamberAppUserDto();
        ChamberAppUser expectedEntity = createSampleChamberAppUserEntity();

        ChamberAppUser actualEntity = chamberAppUserMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapChamberAppUserListToChamberAppUserDtoList() {
        List<ChamberAppUser> entityList = List.of(
                createSampleChamberAppUserEntity(),
                createAnotherChamberAppUserEntity()
        );
        List<ChamberAppUserDto> expectedDtoList = List.of(
                createSampleChamberAppUserDto(),
                createAnotherChamberAppUserDto()
        );

        List<ChamberAppUserDto> actualDtoList = chamberAppUserMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapChamberAppUserDtoListToChamberAppUserList() {
        List<ChamberAppUserDto> dtoList = List.of(
                createSampleChamberAppUserDto(),
                createAnotherChamberAppUserDto()
        );
        List<ChamberAppUser> expectedEntityList = List.of(
                createSampleChamberAppUserEntity(),
                createAnotherChamberAppUserEntity()
        );

        List<ChamberAppUser> actualEntityList = chamberAppUserMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForChamberAppUser() {
        ChamberAppUser originalEntity = createSampleChamberAppUserEntity();
        ChamberAppUser actualEntity = createSampleChamberAppUserEntity();
        ChamberAppUserDto patchDto = createPatchChamberAppUserDto();
        ChamberAppUser patchEntity = chamberAppUserMapper.toEntity(patchDto);

        chamberAppUserMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberAppIdExpectedValue = patchEntity.getChamberAppId() != null ? patchEntity.getChamberAppId() : originalEntity.getChamberAppId();
        assertThat(actualEntity.getChamberAppId())
                .isEqualTo(chamberAppIdExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object profileIdExpectedValue = patchEntity.getProfileId() != null ? patchEntity.getProfileId() : originalEntity.getProfileId();
        assertThat(actualEntity.getProfileId())
                .isEqualTo(profileIdExpectedValue);

        Object personIdExpectedValue = patchEntity.getPersonId() != null ? patchEntity.getPersonId() : originalEntity.getPersonId();
        assertThat(actualEntity.getPersonId())
                .isEqualTo(personIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChamberAppUserDtoListForNullOrEmptyChamberAppUserList() {
        assertThat(chamberAppUserMapper.toDTOList(null)).isEmpty();
        assertThat(chamberAppUserMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChamberAppUserListForNullOrEmptyChamberAppUserDtoList() {
        assertThat(chamberAppUserMapper.toEntityList(null)).isEmpty();
        assertThat(chamberAppUserMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ChamberAppUser entity = createSampleChamberAppUserEntity();
        ChamberAppUser expectedEntity = createSampleChamberAppUserEntity();

        chamberAppUserMapper.partialUpdate(entity, null);
        chamberAppUserMapper.partialUpdate(null, createPatchChamberAppUserDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ChamberAppUser fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChamberAppUser createSampleChamberAppUserEntity() {
        ChamberAppUser entity = new ChamberAppUser();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setChamberId(10);
        entity.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
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
        entity.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        return entity;
    }

    /**
     * Creates a populated ChamberAppUser fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChamberAppUser createAnotherChamberAppUserEntity() {
        ChamberAppUser entity = new ChamberAppUser();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setChamberId(20);
        entity.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
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
        entity.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000002"));

        return entity;
    }

    /**
     * Creates a populated ChamberAppUserDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberAppUserDto createSampleChamberAppUserDto() {
        ChamberAppUserDto dto = new ChamberAppUserDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setChamberId(10);
        dto.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
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
        dto.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        return dto;
    }

    /**
     * Creates a populated ChamberAppUserDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberAppUserDto createAnotherChamberAppUserDto() {
        ChamberAppUserDto dto = new ChamberAppUserDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setChamberId(20);
        dto.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
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
        dto.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000002"));

        return dto;
    }

    /**
     * Creates a populated ChamberAppUserDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberAppUserDto createPatchChamberAppUserDto() {
        ChamberAppUserDto dto = new ChamberAppUserDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setChamberId(30);
        dto.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setRecdeleted(true);
        dto.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000003"));

        return dto;
    }

}
