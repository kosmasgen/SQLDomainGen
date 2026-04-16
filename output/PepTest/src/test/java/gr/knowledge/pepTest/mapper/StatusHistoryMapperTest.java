package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.StatusHistoryDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.entity.StatusHistory;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class StatusHistoryMapperTest {

    private StatusHistoryMapper statusHistoryMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        statusHistoryMapper = new StatusHistoryMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapStatusHistoryToStatusHistoryDto() {
        StatusHistory entity = createSampleStatusHistoryEntity();
        StatusHistoryDto expectedDto = createSampleStatusHistoryDto();

        StatusHistoryDto actualDto = statusHistoryMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapStatusHistoryDtoToStatusHistory() {
        StatusHistoryDto dto = createSampleStatusHistoryDto();
        StatusHistory expectedEntity = createSampleStatusHistoryEntity();

        StatusHistory actualEntity = statusHistoryMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapStatusHistoryListToStatusHistoryDtoList() {
        List<StatusHistory> entityList = List.of(
                createSampleStatusHistoryEntity(),
                createAnotherStatusHistoryEntity()
        );
        List<StatusHistoryDto> expectedDtoList = List.of(
                createSampleStatusHistoryDto(),
                createAnotherStatusHistoryDto()
        );

        List<StatusHistoryDto> actualDtoList = statusHistoryMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapStatusHistoryDtoListToStatusHistoryList() {
        List<StatusHistoryDto> dtoList = List.of(
                createSampleStatusHistoryDto(),
                createAnotherStatusHistoryDto()
        );
        List<StatusHistory> expectedEntityList = List.of(
                createSampleStatusHistoryEntity(),
                createAnotherStatusHistoryEntity()
        );

        List<StatusHistory> actualEntityList = statusHistoryMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForStatusHistory() {
        StatusHistory originalEntity = createSampleStatusHistoryEntity();
        StatusHistory actualEntity = createSampleStatusHistoryEntity();
        StatusHistoryDto patchDto = createPatchStatusHistoryDto();
        StatusHistory patchEntity = statusHistoryMapper.toEntity(patchDto);

        statusHistoryMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberStatusHistoryIdExpectedValue = patchEntity.getChamberStatusHistoryId() != null ? patchEntity.getChamberStatusHistoryId() : originalEntity.getChamberStatusHistoryId();
        assertThat(actualEntity.getChamberStatusHistoryId())
                .isEqualTo(chamberStatusHistoryIdExpectedValue);

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object companyStatusExpectedValue = patchEntity.getCompanyStatus() != null ? patchEntity.getCompanyStatus() : originalEntity.getCompanyStatus();
        assertThat(actualEntity.getCompanyStatus())
                .usingRecursiveComparison()
                .isEqualTo(companyStatusExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object notesExpectedValue = patchEntity.getNotes() != null ? patchEntity.getNotes() : originalEntity.getNotes();
        assertThat(actualEntity.getNotes())
                .isEqualTo(notesExpectedValue);

        Object regDtExpectedValue = patchEntity.getRegDt() != null ? patchEntity.getRegDt() : originalEntity.getRegDt();
        assertThat(actualEntity.getRegDt())
                .isEqualTo(regDtExpectedValue);

        Object startDtExpectedValue = patchEntity.getStartDt() != null ? patchEntity.getStartDt() : originalEntity.getStartDt();
        assertThat(actualEntity.getStartDt())
                .isEqualTo(startDtExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object gemiIdExpectedValue = patchEntity.getGemiId() != null ? patchEntity.getGemiId() : originalEntity.getGemiId();
        assertThat(actualEntity.getGemiId())
                .isEqualTo(gemiIdExpectedValue);

        Object gemiDateCreatedExpectedValue = patchEntity.getGemiDateCreated() != null ? patchEntity.getGemiDateCreated() : originalEntity.getGemiDateCreated();
        assertThat(actualEntity.getGemiDateCreated())
                .isEqualTo(gemiDateCreatedExpectedValue);

        Object gemiLastUpdatedExpectedValue = patchEntity.getGemiLastUpdated() != null ? patchEntity.getGemiLastUpdated() : originalEntity.getGemiLastUpdated();
        assertThat(actualEntity.getGemiLastUpdated())
                .isEqualTo(gemiLastUpdatedExpectedValue);

        Object actionNoExpectedValue = patchEntity.getActionNo() != null ? patchEntity.getActionNo() : originalEntity.getActionNo();
        assertThat(actualEntity.getActionNo())
                .isEqualTo(actionNoExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyStatusHistoryDtoListForNullOrEmptyStatusHistoryList() {
        assertThat(statusHistoryMapper.toDTOList(null)).isEmpty();
        assertThat(statusHistoryMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyStatusHistoryListForNullOrEmptyStatusHistoryDtoList() {
        assertThat(statusHistoryMapper.toEntityList(null)).isEmpty();
        assertThat(statusHistoryMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        StatusHistory entity = createSampleStatusHistoryEntity();
        StatusHistory expectedEntity = createSampleStatusHistoryEntity();

        statusHistoryMapper.partialUpdate(entity, null);
        statusHistoryMapper.partialUpdate(null, createPatchStatusHistoryDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated StatusHistory fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private StatusHistory createSampleStatusHistoryEntity() {
        StatusHistory entity = new StatusHistory();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberStatusHistoryId(new BigInteger("1000"));
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
        CompanyStatus companyStatusFixture1 = new CompanyStatus();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        entity.setCompanyStatus(companyStatusFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setNotes("notesValue1");
        entity.setRegDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setStartDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setGemiId(new BigInteger("1000"));
        entity.setGemiDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setGemiLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setActionNo("actionNoValue1");

        return entity;
    }

    /**
     * Creates a populated StatusHistory fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private StatusHistory createAnotherStatusHistoryEntity() {
        StatusHistory entity = new StatusHistory();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberStatusHistoryId(new BigInteger("2000"));
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
        CompanyStatus companyStatusFixture2 = new CompanyStatus();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        entity.setCompanyStatus(companyStatusFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setNotes("notesValue2");
        entity.setRegDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setStartDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setGemiId(new BigInteger("2000"));
        entity.setGemiDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setGemiLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setActionNo("actionNoValue2");

        return entity;
    }

    /**
     * Creates a populated StatusHistoryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private StatusHistoryDto createSampleStatusHistoryDto() {
        StatusHistoryDto dto = new StatusHistoryDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberStatusHistoryId(new BigInteger("1000"));
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
        CompanyStatusDto companyStatusFixture1 = new CompanyStatusDto();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        dto.setCompanyStatus(companyStatusFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setNotes("notesValue1");
        dto.setRegDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setStartDt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setGemiId(new BigInteger("1000"));
        dto.setGemiDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setGemiLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setActionNo("actionNoValue1");

        return dto;
    }

    /**
     * Creates a populated StatusHistoryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private StatusHistoryDto createAnotherStatusHistoryDto() {
        StatusHistoryDto dto = new StatusHistoryDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberStatusHistoryId(new BigInteger("2000"));
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
        CompanyStatusDto companyStatusFixture2 = new CompanyStatusDto();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        dto.setCompanyStatus(companyStatusFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setNotes("notesValue2");
        dto.setRegDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setStartDt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setGemiId(new BigInteger("2000"));
        dto.setGemiDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setGemiLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setActionNo("actionNoValue2");

        return dto;
    }

    /**
     * Creates a populated StatusHistoryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private StatusHistoryDto createPatchStatusHistoryDto() {
        StatusHistoryDto dto = new StatusHistoryDto();
        dto.setChamberId(30);
        dto.setChamberStatusHistoryId(new BigInteger("3000"));
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setNotes("notesValue3");
        dto.setRegDt(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setGemiId(new BigInteger("3000"));
        dto.setGemiDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setGemiLastUpdated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setActionNo("actionNoValue3");

        return dto;
    }

}
