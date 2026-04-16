package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyFavourites;
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

class CompanyFavouritesMapperTest {

    private CompanyFavouritesMapper companyFavouritesMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyFavouritesMapper = new CompanyFavouritesMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyFavouritesToCompanyFavouritesDto() {
        CompanyFavourites entity = createSampleCompanyFavouritesEntity();
        CompanyFavouritesDto expectedDto = createSampleCompanyFavouritesDto();

        CompanyFavouritesDto actualDto = companyFavouritesMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyFavouritesDtoToCompanyFavourites() {
        CompanyFavouritesDto dto = createSampleCompanyFavouritesDto();
        CompanyFavourites expectedEntity = createSampleCompanyFavouritesEntity();

        CompanyFavourites actualEntity = companyFavouritesMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyFavouritesListToCompanyFavouritesDtoList() {
        List<CompanyFavourites> entityList = List.of(
                createSampleCompanyFavouritesEntity(),
                createAnotherCompanyFavouritesEntity()
        );
        List<CompanyFavouritesDto> expectedDtoList = List.of(
                createSampleCompanyFavouritesDto(),
                createAnotherCompanyFavouritesDto()
        );

        List<CompanyFavouritesDto> actualDtoList = companyFavouritesMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyFavouritesDtoListToCompanyFavouritesList() {
        List<CompanyFavouritesDto> dtoList = List.of(
                createSampleCompanyFavouritesDto(),
                createAnotherCompanyFavouritesDto()
        );
        List<CompanyFavourites> expectedEntityList = List.of(
                createSampleCompanyFavouritesEntity(),
                createAnotherCompanyFavouritesEntity()
        );

        List<CompanyFavourites> actualEntityList = companyFavouritesMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyFavourites() {
        CompanyFavourites originalEntity = createSampleCompanyFavouritesEntity();
        CompanyFavourites actualEntity = createSampleCompanyFavouritesEntity();
        CompanyFavouritesDto patchDto = createPatchCompanyFavouritesDto();
        CompanyFavourites patchEntity = companyFavouritesMapper.toEntity(patchDto);

        companyFavouritesMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object companyExpectedValue = patchEntity.getCompany() != null ? patchEntity.getCompany() : originalEntity.getCompany();
        assertThat(actualEntity.getCompany())
                .usingRecursiveComparison()
                .isEqualTo(companyExpectedValue);

        Object favouriteCompanyExpectedValue = patchEntity.getFavouriteCompany() != null ? patchEntity.getFavouriteCompany() : originalEntity.getFavouriteCompany();
        assertThat(actualEntity.getFavouriteCompany())
                .usingRecursiveComparison()
                .isEqualTo(favouriteCompanyExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object notesExpectedValue = patchEntity.getNotes() != null ? patchEntity.getNotes() : originalEntity.getNotes();
        assertThat(actualEntity.getNotes())
                .isEqualTo(notesExpectedValue);

        Object favouriteProfileExpectedValue = patchEntity.getFavouriteProfile() != null ? patchEntity.getFavouriteProfile() : originalEntity.getFavouriteProfile();
        assertThat(actualEntity.getFavouriteProfile())
                .usingRecursiveComparison()
                .isEqualTo(favouriteProfileExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyFavouritesDtoListForNullOrEmptyCompanyFavouritesList() {
        assertThat(companyFavouritesMapper.toDTOList(null)).isEmpty();
        assertThat(companyFavouritesMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyFavouritesListForNullOrEmptyCompanyFavouritesDtoList() {
        assertThat(companyFavouritesMapper.toEntityList(null)).isEmpty();
        assertThat(companyFavouritesMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyFavourites entity = createSampleCompanyFavouritesEntity();
        CompanyFavourites expectedEntity = createSampleCompanyFavouritesEntity();

        companyFavouritesMapper.partialUpdate(entity, null);
        companyFavouritesMapper.partialUpdate(null, createPatchCompanyFavouritesDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyFavourites fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyFavourites createSampleCompanyFavouritesEntity() {
        CompanyFavourites entity = new CompanyFavourites();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
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
        Company favouriteCompanyFixture1 = new Company();
        favouriteCompanyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        favouriteCompanyFixture1.setAfm("afmValue1");
        favouriteCompanyFixture1.setAm(new BigInteger("1000"));
        favouriteCompanyFixture1.setGemiId(new BigInteger("1000"));
        favouriteCompanyFixture1.setCoName("coNameValue1");
        favouriteCompanyFixture1.setChamberCompanyId(new BigInteger("1000"));
        favouriteCompanyFixture1.setChamberId(10);
        favouriteCompanyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setMember(new BigInteger("1000"));
        favouriteCompanyFixture1.setRecType("recTypeValue1");
        favouriteCompanyFixture1.setRecdeleted(true);
        favouriteCompanyFixture1.setAddressCity("addressCityValue1");
        favouriteCompanyFixture1.setAddressLatitude("addressLatitudeValue1");
        favouriteCompanyFixture1.setAddressLongitude("addressLongitudeValue1");
        favouriteCompanyFixture1.setAddressRegion("addressRegionValue1");
        favouriteCompanyFixture1.setAddressStreet("addressStreetValue1");
        favouriteCompanyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        favouriteCompanyFixture1.setAddressZipCode("addressZipCodeValue1");
        favouriteCompanyFixture1.setBranchTypeId(new BigInteger("1000"));
        favouriteCompanyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        favouriteCompanyFixture1.setCoNameNrm("coNameNrmValue1");
        favouriteCompanyFixture1.setContactEmail("contactEmailValue1");
        favouriteCompanyFixture1.setContactMobile("contactMobileValue1");
        favouriteCompanyFixture1.setContactPhone1("contactPhone1Value1");
        favouriteCompanyFixture1.setContactPhone2("contactPhone2Value1");
        favouriteCompanyFixture1.setContactUrl("contactUrlValue1");
        favouriteCompanyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setGemiNumber("gemiNumberValue1");
        favouriteCompanyFixture1.setObjective("objectiveValue1");
        favouriteCompanyFixture1.setReceiveNewsletter(true);
        favouriteCompanyFixture1.setIsChamberCompany(true);
        favouriteCompanyFixture1.setIsTradesCompany(true);
        favouriteCompanyFixture1.setShowBusinessGuide(true);
        favouriteCompanyFixture1.setHasActiveProfiles(true);
        favouriteCompanyFixture1.setIsProteasData(true);
        favouriteCompanyFixture1.setResponsibleName("responsibleNameValue1");
        favouriteCompanyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        favouriteCompanyFixture1.setContactPhone3("contactPhone3Value1");
        favouriteCompanyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        favouriteCompanyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        favouriteCompanyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        favouriteCompanyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        favouriteCompanyFixture1.setJbDescription("jbDescriptionValue1");
        favouriteCompanyFixture1.setJbNumberEmployees(100L);
        favouriteCompanyFixture1.setJbMotto("jbMottoValue1");
        favouriteCompanyFixture1.setJbEmail("jbEmailValue1");
        favouriteCompanyFixture1.setJbLinkedInUrl("jbLinkedInUrlValue1");
        favouriteCompanyFixture1.setJbFacebookUrl("jbFacebookUrlValue1");
        favouriteCompanyFixture1.setJbRegistrationStatus("jbRegistrationStatusValue1");
        favouriteCompanyFixture1.setJbLogoId("jbLogoIdValue1");
        favouriteCompanyFixture1.setJbCoverId("jbCoverIdValue1");
        favouriteCompanyFixture1.setJbLocationId(10);
        favouriteCompanyFixture1.setJbIndustryId(10);
        favouriteCompanyFixture1.setJbIsvalid(true);
        favouriteCompanyFixture1.setJbActivationStatus("jbActivationStatusValue1");
        entity.setFavouriteCompany(favouriteCompanyFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setNotes("notesValue1");
        CompanyProfile favouriteProfileFixture1 = new CompanyProfile();
        favouriteProfileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        favouriteProfileFixture1.setName("nameValue1");
        favouriteProfileFixture1.setAddressCity("addressCityValue1");
        favouriteProfileFixture1.setAddressLatitude("addressLatitudeValue1");
        favouriteProfileFixture1.setAddressLongitude("addressLongitudeValue1");
        favouriteProfileFixture1.setAddressRegion("addressRegionValue1");
        favouriteProfileFixture1.setAddressStreet("addressStreetValue1");
        favouriteProfileFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        favouriteProfileFixture1.setAddressZipCode("addressZipCodeValue1");
        favouriteProfileFixture1.setContactEmail("contactEmailValue1");
        favouriteProfileFixture1.setContactMobile("contactMobileValue1");
        favouriteProfileFixture1.setContactPhone1("contactPhone1Value1");
        favouriteProfileFixture1.setContactPhone2("contactPhone2Value1");
        favouriteProfileFixture1.setContactPhone3("contactPhone3Value1");
        favouriteProfileFixture1.setContactUrl("contactUrlValue1");
        favouriteProfileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteProfileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteProfileFixture1.setRecdeleted(true);
        favouriteProfileFixture1.setShowBusinessGuide(true);
        entity.setFavouriteProfile(favouriteProfileFixture1);

        return entity;
    }

    /**
     * Creates a populated CompanyFavourites fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyFavourites createAnotherCompanyFavouritesEntity() {
        CompanyFavourites entity = new CompanyFavourites();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
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
        Company favouriteCompanyFixture2 = new Company();
        favouriteCompanyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        favouriteCompanyFixture2.setAfm("afmValue2");
        favouriteCompanyFixture2.setAm(new BigInteger("2000"));
        favouriteCompanyFixture2.setGemiId(new BigInteger("2000"));
        favouriteCompanyFixture2.setCoName("coNameValue2");
        favouriteCompanyFixture2.setChamberCompanyId(new BigInteger("2000"));
        favouriteCompanyFixture2.setChamberId(20);
        favouriteCompanyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setMember(new BigInteger("2000"));
        favouriteCompanyFixture2.setRecType("recTypeValue2");
        favouriteCompanyFixture2.setRecdeleted(false);
        favouriteCompanyFixture2.setAddressCity("addressCityValue2");
        favouriteCompanyFixture2.setAddressLatitude("addressLatitudeValue2");
        favouriteCompanyFixture2.setAddressLongitude("addressLongitudeValue2");
        favouriteCompanyFixture2.setAddressRegion("addressRegionValue2");
        favouriteCompanyFixture2.setAddressStreet("addressStreetValue2");
        favouriteCompanyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        favouriteCompanyFixture2.setAddressZipCode("addressZipCodeValue2");
        favouriteCompanyFixture2.setBranchTypeId(new BigInteger("2000"));
        favouriteCompanyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        favouriteCompanyFixture2.setCoNameNrm("coNameNrmValue2");
        favouriteCompanyFixture2.setContactEmail("contactEmailValue2");
        favouriteCompanyFixture2.setContactMobile("contactMobileValue2");
        favouriteCompanyFixture2.setContactPhone1("contactPhone1Value2");
        favouriteCompanyFixture2.setContactPhone2("contactPhone2Value2");
        favouriteCompanyFixture2.setContactUrl("contactUrlValue2");
        favouriteCompanyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setGemiNumber("gemiNumberValue2");
        favouriteCompanyFixture2.setObjective("objectiveValue2");
        favouriteCompanyFixture2.setReceiveNewsletter(false);
        favouriteCompanyFixture2.setIsChamberCompany(false);
        favouriteCompanyFixture2.setIsTradesCompany(false);
        favouriteCompanyFixture2.setShowBusinessGuide(false);
        favouriteCompanyFixture2.setHasActiveProfiles(false);
        favouriteCompanyFixture2.setIsProteasData(false);
        favouriteCompanyFixture2.setResponsibleName("responsibleNameValue2");
        favouriteCompanyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        favouriteCompanyFixture2.setContactPhone3("contactPhone3Value2");
        favouriteCompanyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        favouriteCompanyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        favouriteCompanyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        favouriteCompanyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        favouriteCompanyFixture2.setJbDescription("jbDescriptionValue2");
        favouriteCompanyFixture2.setJbNumberEmployees(200L);
        favouriteCompanyFixture2.setJbMotto("jbMottoValue2");
        favouriteCompanyFixture2.setJbEmail("jbEmailValue2");
        favouriteCompanyFixture2.setJbLinkedInUrl("jbLinkedInUrlValue2");
        favouriteCompanyFixture2.setJbFacebookUrl("jbFacebookUrlValue2");
        favouriteCompanyFixture2.setJbRegistrationStatus("jbRegistrationStatusValue2");
        favouriteCompanyFixture2.setJbLogoId("jbLogoIdValue2");
        favouriteCompanyFixture2.setJbCoverId("jbCoverIdValue2");
        favouriteCompanyFixture2.setJbLocationId(20);
        favouriteCompanyFixture2.setJbIndustryId(20);
        favouriteCompanyFixture2.setJbIsvalid(false);
        favouriteCompanyFixture2.setJbActivationStatus("jbActivationStatusValue2");
        entity.setFavouriteCompany(favouriteCompanyFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setNotes("notesValue2");
        CompanyProfile favouriteProfileFixture2 = new CompanyProfile();
        favouriteProfileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        favouriteProfileFixture2.setName("nameValue2");
        favouriteProfileFixture2.setAddressCity("addressCityValue2");
        favouriteProfileFixture2.setAddressLatitude("addressLatitudeValue2");
        favouriteProfileFixture2.setAddressLongitude("addressLongitudeValue2");
        favouriteProfileFixture2.setAddressRegion("addressRegionValue2");
        favouriteProfileFixture2.setAddressStreet("addressStreetValue2");
        favouriteProfileFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        favouriteProfileFixture2.setAddressZipCode("addressZipCodeValue2");
        favouriteProfileFixture2.setContactEmail("contactEmailValue2");
        favouriteProfileFixture2.setContactMobile("contactMobileValue2");
        favouriteProfileFixture2.setContactPhone1("contactPhone1Value2");
        favouriteProfileFixture2.setContactPhone2("contactPhone2Value2");
        favouriteProfileFixture2.setContactPhone3("contactPhone3Value2");
        favouriteProfileFixture2.setContactUrl("contactUrlValue2");
        favouriteProfileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteProfileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteProfileFixture2.setRecdeleted(false);
        favouriteProfileFixture2.setShowBusinessGuide(false);
        entity.setFavouriteProfile(favouriteProfileFixture2);

        return entity;
    }

    /**
     * Creates a populated CompanyFavouritesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyFavouritesDto createSampleCompanyFavouritesDto() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
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
        CompanyDto favouriteCompanyFixture1 = new CompanyDto();
        favouriteCompanyFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        favouriteCompanyFixture1.setAfm("afmValue1");
        favouriteCompanyFixture1.setAm(new BigInteger("1000"));
        favouriteCompanyFixture1.setGemiId(new BigInteger("1000"));
        favouriteCompanyFixture1.setCoName("coNameValue1");
        favouriteCompanyFixture1.setChamberCompanyId(new BigInteger("1000"));
        favouriteCompanyFixture1.setChamberId(10);
        favouriteCompanyFixture1.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setMember(new BigInteger("1000"));
        favouriteCompanyFixture1.setRecType("recTypeValue1");
        favouriteCompanyFixture1.setRecdeleted(true);
        favouriteCompanyFixture1.setAddressCity("addressCityValue1");
        favouriteCompanyFixture1.setAddressLatitude("addressLatitudeValue1");
        favouriteCompanyFixture1.setAddressLongitude("addressLongitudeValue1");
        favouriteCompanyFixture1.setAddressRegion("addressRegionValue1");
        favouriteCompanyFixture1.setAddressStreet("addressStreetValue1");
        favouriteCompanyFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        favouriteCompanyFixture1.setAddressZipCode("addressZipCodeValue1");
        favouriteCompanyFixture1.setBranchTypeId(new BigInteger("1000"));
        favouriteCompanyFixture1.setChamberGemiResponsibleId(new BigInteger("1000"));
        favouriteCompanyFixture1.setCoNameNrm("coNameNrmValue1");
        favouriteCompanyFixture1.setContactEmail("contactEmailValue1");
        favouriteCompanyFixture1.setContactMobile("contactMobileValue1");
        favouriteCompanyFixture1.setContactPhone1("contactPhone1Value1");
        favouriteCompanyFixture1.setContactPhone2("contactPhone2Value1");
        favouriteCompanyFixture1.setContactUrl("contactUrlValue1");
        favouriteCompanyFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setGemiNumber("gemiNumberValue1");
        favouriteCompanyFixture1.setObjective("objectiveValue1");
        favouriteCompanyFixture1.setReceiveNewsletter(true);
        favouriteCompanyFixture1.setIsChamberCompany(true);
        favouriteCompanyFixture1.setIsTradesCompany(true);
        favouriteCompanyFixture1.setShowBusinessGuide(true);
        favouriteCompanyFixture1.setHasActiveProfiles(true);
        favouriteCompanyFixture1.setIsProteasData(true);
        favouriteCompanyFixture1.setResponsibleName("responsibleNameValue1");
        favouriteCompanyFixture1.setAddressZoomLevel(new BigInteger("1000"));
        favouriteCompanyFixture1.setContactPhone3("contactPhone3Value1");
        favouriteCompanyFixture1.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setMeCriteria1Id(new BigInteger("1000"));
        favouriteCompanyFixture1.setMeCriteria2Id(new BigInteger("1000"));
        favouriteCompanyFixture1.setMeCriteria3Id(new BigInteger("1000"));
        favouriteCompanyFixture1.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteCompanyFixture1.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        favouriteCompanyFixture1.setJbDescription("jbDescriptionValue1");
        favouriteCompanyFixture1.setJbNumberEmployees(100L);
        favouriteCompanyFixture1.setJbMotto("jbMottoValue1");
        favouriteCompanyFixture1.setJbEmail("jbEmailValue1");
        favouriteCompanyFixture1.setJbLinkedInUrl("jbLinkedInUrlValue1");
        favouriteCompanyFixture1.setJbFacebookUrl("jbFacebookUrlValue1");
        favouriteCompanyFixture1.setJbRegistrationStatus("jbRegistrationStatusValue1");
        favouriteCompanyFixture1.setJbLogoId("jbLogoIdValue1");
        favouriteCompanyFixture1.setJbCoverId("jbCoverIdValue1");
        favouriteCompanyFixture1.setJbLocationId(10);
        favouriteCompanyFixture1.setJbIndustryId(10);
        favouriteCompanyFixture1.setJbIsvalid(true);
        favouriteCompanyFixture1.setJbActivationStatus("jbActivationStatusValue1");
        dto.setFavouriteCompany(favouriteCompanyFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setNotes("notesValue1");
        CompanyProfileDto favouriteProfileFixture1 = new CompanyProfileDto();
        favouriteProfileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        favouriteProfileFixture1.setName("nameValue1");
        favouriteProfileFixture1.setAddressCity("addressCityValue1");
        favouriteProfileFixture1.setAddressLatitude("addressLatitudeValue1");
        favouriteProfileFixture1.setAddressLongitude("addressLongitudeValue1");
        favouriteProfileFixture1.setAddressRegion("addressRegionValue1");
        favouriteProfileFixture1.setAddressStreet("addressStreetValue1");
        favouriteProfileFixture1.setAddressStreetNumber("addressStreetNumberValue1");
        favouriteProfileFixture1.setAddressZipCode("addressZipCodeValue1");
        favouriteProfileFixture1.setContactEmail("contactEmailValue1");
        favouriteProfileFixture1.setContactMobile("contactMobileValue1");
        favouriteProfileFixture1.setContactPhone1("contactPhone1Value1");
        favouriteProfileFixture1.setContactPhone2("contactPhone2Value1");
        favouriteProfileFixture1.setContactPhone3("contactPhone3Value1");
        favouriteProfileFixture1.setContactUrl("contactUrlValue1");
        favouriteProfileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteProfileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        favouriteProfileFixture1.setRecdeleted(true);
        favouriteProfileFixture1.setShowBusinessGuide(true);
        dto.setFavouriteProfile(favouriteProfileFixture1);

        return dto;
    }

    /**
     * Creates a populated CompanyFavouritesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyFavouritesDto createAnotherCompanyFavouritesDto() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
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
        CompanyDto favouriteCompanyFixture2 = new CompanyDto();
        favouriteCompanyFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        favouriteCompanyFixture2.setAfm("afmValue2");
        favouriteCompanyFixture2.setAm(new BigInteger("2000"));
        favouriteCompanyFixture2.setGemiId(new BigInteger("2000"));
        favouriteCompanyFixture2.setCoName("coNameValue2");
        favouriteCompanyFixture2.setChamberCompanyId(new BigInteger("2000"));
        favouriteCompanyFixture2.setChamberId(20);
        favouriteCompanyFixture2.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setMember(new BigInteger("2000"));
        favouriteCompanyFixture2.setRecType("recTypeValue2");
        favouriteCompanyFixture2.setRecdeleted(false);
        favouriteCompanyFixture2.setAddressCity("addressCityValue2");
        favouriteCompanyFixture2.setAddressLatitude("addressLatitudeValue2");
        favouriteCompanyFixture2.setAddressLongitude("addressLongitudeValue2");
        favouriteCompanyFixture2.setAddressRegion("addressRegionValue2");
        favouriteCompanyFixture2.setAddressStreet("addressStreetValue2");
        favouriteCompanyFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        favouriteCompanyFixture2.setAddressZipCode("addressZipCodeValue2");
        favouriteCompanyFixture2.setBranchTypeId(new BigInteger("2000"));
        favouriteCompanyFixture2.setChamberGemiResponsibleId(new BigInteger("2000"));
        favouriteCompanyFixture2.setCoNameNrm("coNameNrmValue2");
        favouriteCompanyFixture2.setContactEmail("contactEmailValue2");
        favouriteCompanyFixture2.setContactMobile("contactMobileValue2");
        favouriteCompanyFixture2.setContactPhone1("contactPhone1Value2");
        favouriteCompanyFixture2.setContactPhone2("contactPhone2Value2");
        favouriteCompanyFixture2.setContactUrl("contactUrlValue2");
        favouriteCompanyFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setGemiNumber("gemiNumberValue2");
        favouriteCompanyFixture2.setObjective("objectiveValue2");
        favouriteCompanyFixture2.setReceiveNewsletter(false);
        favouriteCompanyFixture2.setIsChamberCompany(false);
        favouriteCompanyFixture2.setIsTradesCompany(false);
        favouriteCompanyFixture2.setShowBusinessGuide(false);
        favouriteCompanyFixture2.setHasActiveProfiles(false);
        favouriteCompanyFixture2.setIsProteasData(false);
        favouriteCompanyFixture2.setResponsibleName("responsibleNameValue2");
        favouriteCompanyFixture2.setAddressZoomLevel(new BigInteger("2000"));
        favouriteCompanyFixture2.setContactPhone3("contactPhone3Value2");
        favouriteCompanyFixture2.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setMeCriteria1Id(new BigInteger("2000"));
        favouriteCompanyFixture2.setMeCriteria2Id(new BigInteger("2000"));
        favouriteCompanyFixture2.setMeCriteria3Id(new BigInteger("2000"));
        favouriteCompanyFixture2.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteCompanyFixture2.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        favouriteCompanyFixture2.setJbDescription("jbDescriptionValue2");
        favouriteCompanyFixture2.setJbNumberEmployees(200L);
        favouriteCompanyFixture2.setJbMotto("jbMottoValue2");
        favouriteCompanyFixture2.setJbEmail("jbEmailValue2");
        favouriteCompanyFixture2.setJbLinkedInUrl("jbLinkedInUrlValue2");
        favouriteCompanyFixture2.setJbFacebookUrl("jbFacebookUrlValue2");
        favouriteCompanyFixture2.setJbRegistrationStatus("jbRegistrationStatusValue2");
        favouriteCompanyFixture2.setJbLogoId("jbLogoIdValue2");
        favouriteCompanyFixture2.setJbCoverId("jbCoverIdValue2");
        favouriteCompanyFixture2.setJbLocationId(20);
        favouriteCompanyFixture2.setJbIndustryId(20);
        favouriteCompanyFixture2.setJbIsvalid(false);
        favouriteCompanyFixture2.setJbActivationStatus("jbActivationStatusValue2");
        dto.setFavouriteCompany(favouriteCompanyFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setNotes("notesValue2");
        CompanyProfileDto favouriteProfileFixture2 = new CompanyProfileDto();
        favouriteProfileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        favouriteProfileFixture2.setName("nameValue2");
        favouriteProfileFixture2.setAddressCity("addressCityValue2");
        favouriteProfileFixture2.setAddressLatitude("addressLatitudeValue2");
        favouriteProfileFixture2.setAddressLongitude("addressLongitudeValue2");
        favouriteProfileFixture2.setAddressRegion("addressRegionValue2");
        favouriteProfileFixture2.setAddressStreet("addressStreetValue2");
        favouriteProfileFixture2.setAddressStreetNumber("addressStreetNumberValue2");
        favouriteProfileFixture2.setAddressZipCode("addressZipCodeValue2");
        favouriteProfileFixture2.setContactEmail("contactEmailValue2");
        favouriteProfileFixture2.setContactMobile("contactMobileValue2");
        favouriteProfileFixture2.setContactPhone1("contactPhone1Value2");
        favouriteProfileFixture2.setContactPhone2("contactPhone2Value2");
        favouriteProfileFixture2.setContactPhone3("contactPhone3Value2");
        favouriteProfileFixture2.setContactUrl("contactUrlValue2");
        favouriteProfileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteProfileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        favouriteProfileFixture2.setRecdeleted(false);
        favouriteProfileFixture2.setShowBusinessGuide(false);
        dto.setFavouriteProfile(favouriteProfileFixture2);

        return dto;
    }

    /**
     * Creates a populated CompanyFavouritesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyFavouritesDto createPatchCompanyFavouritesDto() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setNotes("notesValue3");

        return dto;
    }

}
