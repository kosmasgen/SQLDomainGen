package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyBgCooperation;
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

class CompanyBgCooperationMapperTest {

    private CompanyBgCooperationMapper companyBgCooperationMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyBgCooperationMapper = new CompanyBgCooperationMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyBgCooperationToCompanyBgCooperationDto() {
        CompanyBgCooperation entity = createSampleCompanyBgCooperationEntity();
        CompanyBgCooperationDto expectedDto = createSampleCompanyBgCooperationDto();

        CompanyBgCooperationDto actualDto = companyBgCooperationMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyBgCooperationDtoToCompanyBgCooperation() {
        CompanyBgCooperationDto dto = createSampleCompanyBgCooperationDto();
        CompanyBgCooperation expectedEntity = createSampleCompanyBgCooperationEntity();

        CompanyBgCooperation actualEntity = companyBgCooperationMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyBgCooperationListToCompanyBgCooperationDtoList() {
        List<CompanyBgCooperation> entityList = List.of(
                createSampleCompanyBgCooperationEntity(),
                createAnotherCompanyBgCooperationEntity()
        );
        List<CompanyBgCooperationDto> expectedDtoList = List.of(
                createSampleCompanyBgCooperationDto(),
                createAnotherCompanyBgCooperationDto()
        );

        List<CompanyBgCooperationDto> actualDtoList = companyBgCooperationMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyBgCooperationDtoListToCompanyBgCooperationList() {
        List<CompanyBgCooperationDto> dtoList = List.of(
                createSampleCompanyBgCooperationDto(),
                createAnotherCompanyBgCooperationDto()
        );
        List<CompanyBgCooperation> expectedEntityList = List.of(
                createSampleCompanyBgCooperationEntity(),
                createAnotherCompanyBgCooperationEntity()
        );

        List<CompanyBgCooperation> actualEntityList = companyBgCooperationMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyBgCooperation() {
        CompanyBgCooperation originalEntity = createSampleCompanyBgCooperationEntity();
        CompanyBgCooperation actualEntity = createSampleCompanyBgCooperationEntity();
        CompanyBgCooperationDto patchDto = createPatchCompanyBgCooperationDto();
        CompanyBgCooperation patchEntity = companyBgCooperationMapper.toEntity(patchDto);

        companyBgCooperationMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object coopCompanyExpectedValue = patchEntity.getCoopCompany() != null ? patchEntity.getCoopCompany() : originalEntity.getCoopCompany();
        assertThat(actualEntity.getCoopCompany())
                .usingRecursiveComparison()
                .isEqualTo(coopCompanyExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object cooperationStatusExpectedValue = patchEntity.getCooperationStatus() != null ? patchEntity.getCooperationStatus() : originalEntity.getCooperationStatus();
        assertThat(actualEntity.getCooperationStatus())
                .isEqualTo(cooperationStatusExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyBgCooperationDtoListForNullOrEmptyCompanyBgCooperationList() {
        assertThat(companyBgCooperationMapper.toDTOList(null)).isEmpty();
        assertThat(companyBgCooperationMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyBgCooperationListForNullOrEmptyCompanyBgCooperationDtoList() {
        assertThat(companyBgCooperationMapper.toEntityList(null)).isEmpty();
        assertThat(companyBgCooperationMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyBgCooperation entity = createSampleCompanyBgCooperationEntity();
        CompanyBgCooperation expectedEntity = createSampleCompanyBgCooperationEntity();

        companyBgCooperationMapper.partialUpdate(entity, null);
        companyBgCooperationMapper.partialUpdate(null, createPatchCompanyBgCooperationDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyBgCooperation fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperation createSampleCompanyBgCooperationEntity() {
        CompanyBgCooperation entity = new CompanyBgCooperation();
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
        Company coopCompanyFixture1 = new Company();
        coopCompanyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        coopCompanyFixture1.setAfm("afmValue1");
        coopCompanyFixture1.setAm(new BigInteger("1000"));
        coopCompanyFixture1.setGemiId(new BigInteger("1000"));
        coopCompanyFixture1.setCoName("coNameValue1");
        coopCompanyFixture1.setChamberCompanyId(new BigInteger("1000"));
        coopCompanyFixture1.setChamberId(10);
        coopCompanyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setMember(new BigInteger("1000"));
        coopCompanyFixture1.setRecType("recTypeValue1");
        coopCompanyFixture1.setRecdeleted(true);
        coopCompanyFixture1.setAddressCity("addressCityValue1");
        coopCompanyFixture1.setAddressLatitude("addressLatitudeValue1");
        coopCompanyFixture1.setAddressLongitude("addressLongitudeValue1");
        coopCompanyFixture1.setAddressRegion("addressRegionValue1");
        coopCompanyFixture1.setAddressStreet("addressStreetValue1");
        coopCompanyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        coopCompanyFixture1.setAddressZipCode("addressZipCodeValue1");
        coopCompanyFixture1.setBranchTypeId(new BigInteger("1000"));
        coopCompanyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        coopCompanyFixture1.setCoNameNrm("coNameNrmValue1");
        coopCompanyFixture1.setContactEmail("contactEmailValue1");
        coopCompanyFixture1.setContactMobile("contactMobileValue1");
        coopCompanyFixture1.setContactPhone1("contactPhone1Value1");
        coopCompanyFixture1.setContactPhone2("contactPhone2Value1");
        coopCompanyFixture1.setContactUrl("contactUrlValue1");
        coopCompanyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setGemiNumber("gemiNumberValue1");
        coopCompanyFixture1.setObjective("objectiveValue1");
        coopCompanyFixture1.setReceiveNewsletter(true);
        coopCompanyFixture1.setIsChamberCompany(true);
        coopCompanyFixture1.setIsTradesCompany(true);
        coopCompanyFixture1.setShowBusinessGuide(true);
        coopCompanyFixture1.setHasActiveProfiles(true);
        coopCompanyFixture1.setIsProteasData(true);
        coopCompanyFixture1.setResponsibleName("responsibleNameValue1");
        coopCompanyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        coopCompanyFixture1.setContactPhone3("contactPhone3Value1");
        coopCompanyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        coopCompanyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        coopCompanyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        coopCompanyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        coopCompanyFixture1.setJbDescription("jbDescriptionValue1");
        coopCompanyFixture1.setJbNumberEmployees(100L);
        coopCompanyFixture1.setJbMotto("jbMottoValue1");
        coopCompanyFixture1.setJbEmail("jbEmailValue1");
        coopCompanyFixture1.setJbLinkedInUrl("jbLinkedInUrlValue1");
        coopCompanyFixture1.setJbFacebookUrl("jbFacebookUrlValue1");
        coopCompanyFixture1.setJbRegistrationStatus("jbRegistrationStatusValue1");
        coopCompanyFixture1.setJbLogoId("jbLogoIdValue1");
        coopCompanyFixture1.setJbCoverId("jbCoverIdValue1");
        coopCompanyFixture1.setJbLocationId(10);
        coopCompanyFixture1.setJbIndustryId(10);
        coopCompanyFixture1.setJbIsvalid(true);
        coopCompanyFixture1.setJbActivationStatus("jbActivationStatusValue1");
        entity.setCoopCompany(coopCompanyFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setCooperationStatus("cooperationStatusValue1");

        return entity;
    }

    /**
     * Creates a populated CompanyBgCooperation fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperation createAnotherCompanyBgCooperationEntity() {
        CompanyBgCooperation entity = new CompanyBgCooperation();
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
        Company coopCompanyFixture2 = new Company();
        coopCompanyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        coopCompanyFixture2.setAfm("afmValue2");
        coopCompanyFixture2.setAm(new BigInteger("2000"));
        coopCompanyFixture2.setGemiId(new BigInteger("2000"));
        coopCompanyFixture2.setCoName("coNameValue2");
        coopCompanyFixture2.setChamberCompanyId(new BigInteger("2000"));
        coopCompanyFixture2.setChamberId(20);
        coopCompanyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setMember(new BigInteger("2000"));
        coopCompanyFixture2.setRecType("recTypeValue2");
        coopCompanyFixture2.setRecdeleted(false);
        coopCompanyFixture2.setAddressCity("addressCityValue2");
        coopCompanyFixture2.setAddressLatitude("addressLatitudeValue2");
        coopCompanyFixture2.setAddressLongitude("addressLongitudeValue2");
        coopCompanyFixture2.setAddressRegion("addressRegionValue2");
        coopCompanyFixture2.setAddressStreet("addressStreetValue2");
        coopCompanyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        coopCompanyFixture2.setAddressZipCode("addressZipCodeValue2");
        coopCompanyFixture2.setBranchTypeId(new BigInteger("2000"));
        coopCompanyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        coopCompanyFixture2.setCoNameNrm("coNameNrmValue2");
        coopCompanyFixture2.setContactEmail("contactEmailValue2");
        coopCompanyFixture2.setContactMobile("contactMobileValue2");
        coopCompanyFixture2.setContactPhone1("contactPhone1Value2");
        coopCompanyFixture2.setContactPhone2("contactPhone2Value2");
        coopCompanyFixture2.setContactUrl("contactUrlValue2");
        coopCompanyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setGemiNumber("gemiNumberValue2");
        coopCompanyFixture2.setObjective("objectiveValue2");
        coopCompanyFixture2.setReceiveNewsletter(false);
        coopCompanyFixture2.setIsChamberCompany(false);
        coopCompanyFixture2.setIsTradesCompany(false);
        coopCompanyFixture2.setShowBusinessGuide(false);
        coopCompanyFixture2.setHasActiveProfiles(false);
        coopCompanyFixture2.setIsProteasData(false);
        coopCompanyFixture2.setResponsibleName("responsibleNameValue2");
        coopCompanyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        coopCompanyFixture2.setContactPhone3("contactPhone3Value2");
        coopCompanyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        coopCompanyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        coopCompanyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        coopCompanyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        coopCompanyFixture2.setJbDescription("jbDescriptionValue2");
        coopCompanyFixture2.setJbNumberEmployees(200L);
        coopCompanyFixture2.setJbMotto("jbMottoValue2");
        coopCompanyFixture2.setJbEmail("jbEmailValue2");
        coopCompanyFixture2.setJbLinkedInUrl("jbLinkedInUrlValue2");
        coopCompanyFixture2.setJbFacebookUrl("jbFacebookUrlValue2");
        coopCompanyFixture2.setJbRegistrationStatus("jbRegistrationStatusValue2");
        coopCompanyFixture2.setJbLogoId("jbLogoIdValue2");
        coopCompanyFixture2.setJbCoverId("jbCoverIdValue2");
        coopCompanyFixture2.setJbLocationId(20);
        coopCompanyFixture2.setJbIndustryId(20);
        coopCompanyFixture2.setJbIsvalid(false);
        coopCompanyFixture2.setJbActivationStatus("jbActivationStatusValue2");
        entity.setCoopCompany(coopCompanyFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setCooperationStatus("cooperationStatusValue2");

        return entity;
    }

    /**
     * Creates a populated CompanyBgCooperationDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationDto createSampleCompanyBgCooperationDto() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();
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
        CompanyDto coopCompanyFixture1 = new CompanyDto();
        coopCompanyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        coopCompanyFixture1.setAfm("afmValue1");
        coopCompanyFixture1.setAm(new BigInteger("1000"));
        coopCompanyFixture1.setGemiId(new BigInteger("1000"));
        coopCompanyFixture1.setCoName("coNameValue1");
        coopCompanyFixture1.setChamberCompanyId(new BigInteger("1000"));
        coopCompanyFixture1.setChamberId(10);
        coopCompanyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setMember(new BigInteger("1000"));
        coopCompanyFixture1.setRecType("recTypeValue1");
        coopCompanyFixture1.setRecdeleted(true);
        coopCompanyFixture1.setAddressCity("addressCityValue1");
        coopCompanyFixture1.setAddressLatitude("addressLatitudeValue1");
        coopCompanyFixture1.setAddressLongitude("addressLongitudeValue1");
        coopCompanyFixture1.setAddressRegion("addressRegionValue1");
        coopCompanyFixture1.setAddressStreet("addressStreetValue1");
        coopCompanyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        coopCompanyFixture1.setAddressZipCode("addressZipCodeValue1");
        coopCompanyFixture1.setBranchTypeId(new BigInteger("1000"));
        coopCompanyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        coopCompanyFixture1.setCoNameNrm("coNameNrmValue1");
        coopCompanyFixture1.setContactEmail("contactEmailValue1");
        coopCompanyFixture1.setContactMobile("contactMobileValue1");
        coopCompanyFixture1.setContactPhone1("contactPhone1Value1");
        coopCompanyFixture1.setContactPhone2("contactPhone2Value1");
        coopCompanyFixture1.setContactUrl("contactUrlValue1");
        coopCompanyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setGemiNumber("gemiNumberValue1");
        coopCompanyFixture1.setObjective("objectiveValue1");
        coopCompanyFixture1.setReceiveNewsletter(true);
        coopCompanyFixture1.setIsChamberCompany(true);
        coopCompanyFixture1.setIsTradesCompany(true);
        coopCompanyFixture1.setShowBusinessGuide(true);
        coopCompanyFixture1.setHasActiveProfiles(true);
        coopCompanyFixture1.setIsProteasData(true);
        coopCompanyFixture1.setResponsibleName("responsibleNameValue1");
        coopCompanyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        coopCompanyFixture1.setContactPhone3("contactPhone3Value1");
        coopCompanyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        coopCompanyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        coopCompanyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        coopCompanyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        coopCompanyFixture1.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        coopCompanyFixture1.setJbDescription("jbDescriptionValue1");
        coopCompanyFixture1.setJbNumberEmployees(100L);
        coopCompanyFixture1.setJbMotto("jbMottoValue1");
        coopCompanyFixture1.setJbEmail("jbEmailValue1");
        coopCompanyFixture1.setJbLinkedInUrl("jbLinkedInUrlValue1");
        coopCompanyFixture1.setJbFacebookUrl("jbFacebookUrlValue1");
        coopCompanyFixture1.setJbRegistrationStatus("jbRegistrationStatusValue1");
        coopCompanyFixture1.setJbLogoId("jbLogoIdValue1");
        coopCompanyFixture1.setJbCoverId("jbCoverIdValue1");
        coopCompanyFixture1.setJbLocationId(10);
        coopCompanyFixture1.setJbIndustryId(10);
        coopCompanyFixture1.setJbIsvalid(true);
        coopCompanyFixture1.setJbActivationStatus("jbActivationStatusValue1");
        dto.setCoopCompany(coopCompanyFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setCooperationStatus("cooperationStatusValue1");

        return dto;
    }

    /**
     * Creates a populated CompanyBgCooperationDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationDto createAnotherCompanyBgCooperationDto() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();
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
        CompanyDto coopCompanyFixture2 = new CompanyDto();
        coopCompanyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        coopCompanyFixture2.setAfm("afmValue2");
        coopCompanyFixture2.setAm(new BigInteger("2000"));
        coopCompanyFixture2.setGemiId(new BigInteger("2000"));
        coopCompanyFixture2.setCoName("coNameValue2");
        coopCompanyFixture2.setChamberCompanyId(new BigInteger("2000"));
        coopCompanyFixture2.setChamberId(20);
        coopCompanyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setMember(new BigInteger("2000"));
        coopCompanyFixture2.setRecType("recTypeValue2");
        coopCompanyFixture2.setRecdeleted(false);
        coopCompanyFixture2.setAddressCity("addressCityValue2");
        coopCompanyFixture2.setAddressLatitude("addressLatitudeValue2");
        coopCompanyFixture2.setAddressLongitude("addressLongitudeValue2");
        coopCompanyFixture2.setAddressRegion("addressRegionValue2");
        coopCompanyFixture2.setAddressStreet("addressStreetValue2");
        coopCompanyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        coopCompanyFixture2.setAddressZipCode("addressZipCodeValue2");
        coopCompanyFixture2.setBranchTypeId(new BigInteger("2000"));
        coopCompanyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        coopCompanyFixture2.setCoNameNrm("coNameNrmValue2");
        coopCompanyFixture2.setContactEmail("contactEmailValue2");
        coopCompanyFixture2.setContactMobile("contactMobileValue2");
        coopCompanyFixture2.setContactPhone1("contactPhone1Value2");
        coopCompanyFixture2.setContactPhone2("contactPhone2Value2");
        coopCompanyFixture2.setContactUrl("contactUrlValue2");
        coopCompanyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setGemiNumber("gemiNumberValue2");
        coopCompanyFixture2.setObjective("objectiveValue2");
        coopCompanyFixture2.setReceiveNewsletter(false);
        coopCompanyFixture2.setIsChamberCompany(false);
        coopCompanyFixture2.setIsTradesCompany(false);
        coopCompanyFixture2.setShowBusinessGuide(false);
        coopCompanyFixture2.setHasActiveProfiles(false);
        coopCompanyFixture2.setIsProteasData(false);
        coopCompanyFixture2.setResponsibleName("responsibleNameValue2");
        coopCompanyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        coopCompanyFixture2.setContactPhone3("contactPhone3Value2");
        coopCompanyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        coopCompanyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        coopCompanyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        coopCompanyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        coopCompanyFixture2.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        coopCompanyFixture2.setJbDescription("jbDescriptionValue2");
        coopCompanyFixture2.setJbNumberEmployees(200L);
        coopCompanyFixture2.setJbMotto("jbMottoValue2");
        coopCompanyFixture2.setJbEmail("jbEmailValue2");
        coopCompanyFixture2.setJbLinkedInUrl("jbLinkedInUrlValue2");
        coopCompanyFixture2.setJbFacebookUrl("jbFacebookUrlValue2");
        coopCompanyFixture2.setJbRegistrationStatus("jbRegistrationStatusValue2");
        coopCompanyFixture2.setJbLogoId("jbLogoIdValue2");
        coopCompanyFixture2.setJbCoverId("jbCoverIdValue2");
        coopCompanyFixture2.setJbLocationId(20);
        coopCompanyFixture2.setJbIndustryId(20);
        coopCompanyFixture2.setJbIsvalid(false);
        coopCompanyFixture2.setJbActivationStatus("jbActivationStatusValue2");
        dto.setCoopCompany(coopCompanyFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setCooperationStatus("cooperationStatusValue2");

        return dto;
    }

    /**
     * Creates a populated CompanyBgCooperationDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationDto createPatchCompanyBgCooperationDto() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();
        dto.setChamberId(30);
        CompanyDto coopCompanyFixture3 = new CompanyDto();
        coopCompanyFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        coopCompanyFixture3.setAfm("afmValue3");
        coopCompanyFixture3.setAm(new BigInteger("3000"));
        coopCompanyFixture3.setGemiId(new BigInteger("3000"));
        coopCompanyFixture3.setChamberId(30);
        coopCompanyFixture3.setDateInterruption(LocalDateTime.of(2024, 3, 13, 11, 8));
        coopCompanyFixture3.setMember(new BigInteger("3000"));
        coopCompanyFixture3.setRecdeleted(true);
        coopCompanyFixture3.setAddressLatitude("addressLatitudeValue3");
        coopCompanyFixture3.setAddressRegion("addressRegionValue3");
        coopCompanyFixture3.setAddressStreet("addressStreetValue3");
        coopCompanyFixture3.setAddressStreetNumber("addressStreetNumberValue3");
        coopCompanyFixture3.setAddressZipCode("addressZipCodeValue3");
        coopCompanyFixture3.setChamberGemiResponsibleId(new BigInteger("3000"));
        coopCompanyFixture3.setContactEmail("contactEmailValue3");
        coopCompanyFixture3.setContactPhone2("contactPhone2Value3");
        coopCompanyFixture3.setContactUrl("contactUrlValue3");
        coopCompanyFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        coopCompanyFixture3.setDateRegistered(LocalDateTime.of(2024, 3, 13, 11, 8));
        coopCompanyFixture3.setReceiveNewsletter(true);
        coopCompanyFixture3.setIsChamberCompany(true);
        coopCompanyFixture3.setIsTradesCompany(true);
        coopCompanyFixture3.setHasActiveProfiles(true);
        coopCompanyFixture3.setIsProteasData(true);
        coopCompanyFixture3.setAddressZoomLevel(new BigInteger("3000"));
        coopCompanyFixture3.setContactPhone3("contactPhone3Value3");
        coopCompanyFixture3.setDateProfessionStarted(LocalDateTime.of(2024, 3, 13, 11, 8));
        coopCompanyFixture3.setMeCriteria1Id(new BigInteger("3000"));
        coopCompanyFixture3.setMeCriteria2Id(new BigInteger("3000"));
        coopCompanyFixture3.setMemberDues(LocalDateTime.of(2024, 3, 13, 11, 8));
        coopCompanyFixture3.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        coopCompanyFixture3.setJbDescription("jbDescriptionValue3");
        coopCompanyFixture3.setJbNumberEmployees(300L);
        coopCompanyFixture3.setJbMotto("jbMottoValue3");
        coopCompanyFixture3.setJbLinkedInUrl("jbLinkedInUrlValue3");
        coopCompanyFixture3.setJbRegistrationStatus("jbRegistrationStatusValue3");
        coopCompanyFixture3.setJbCoverId("jbCoverIdValue3");
        coopCompanyFixture3.setJbIndustryId(30);
        coopCompanyFixture3.setJbIsvalid(true);
        dto.setCoopCompany(coopCompanyFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
