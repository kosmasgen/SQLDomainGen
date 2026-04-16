package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.entity.CompanyProfileI18n;
import gr.knowledge.pepTest.entity.CompanyProfileI18nKey;
import gr.knowledge.pepTest.entity.CompanyStatus;
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

class CompanyProfileI18nMapperTest {

    private CompanyProfileI18nMapper companyProfileI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyProfileI18nMapper = new CompanyProfileI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyProfileI18nToCompanyProfileI18nDto() {
        CompanyProfileI18n entity = createSampleCompanyProfileI18nEntity();
        CompanyProfileI18nDto expectedDto = createSampleCompanyProfileI18nDto();

        CompanyProfileI18nDto actualDto = companyProfileI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyProfileI18nDtoToCompanyProfileI18n() {
        CompanyProfileI18nDto dto = createSampleCompanyProfileI18nDto();
        CompanyProfileI18n expectedEntity = createSampleCompanyProfileI18nEntity();

        CompanyProfileI18n actualEntity = companyProfileI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyProfileI18nListToCompanyProfileI18nDtoList() {
        List<CompanyProfileI18n> entityList = List.of(
                createSampleCompanyProfileI18nEntity(),
                createAnotherCompanyProfileI18nEntity()
        );
        List<CompanyProfileI18nDto> expectedDtoList = List.of(
                createSampleCompanyProfileI18nDto(),
                createAnotherCompanyProfileI18nDto()
        );

        List<CompanyProfileI18nDto> actualDtoList = companyProfileI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyProfileI18nDtoListToCompanyProfileI18nList() {
        List<CompanyProfileI18nDto> dtoList = List.of(
                createSampleCompanyProfileI18nDto(),
                createAnotherCompanyProfileI18nDto()
        );
        List<CompanyProfileI18n> expectedEntityList = List.of(
                createSampleCompanyProfileI18nEntity(),
                createAnotherCompanyProfileI18nEntity()
        );

        List<CompanyProfileI18n> actualEntityList = companyProfileI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyProfileI18n() {
        CompanyProfileI18n originalEntity = createSampleCompanyProfileI18nEntity();
        CompanyProfileI18n actualEntity = createSampleCompanyProfileI18nEntity();
        CompanyProfileI18nDto patchDto = createPatchCompanyProfileI18nDto();
        CompanyProfileI18n patchEntity = companyProfileI18nMapper.toEntity(patchDto);

        companyProfileI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object companyProfileExpectedValue = patchEntity.getCompanyProfile() != null ? patchEntity.getCompanyProfile() : originalEntity.getCompanyProfile();
        assertThat(actualEntity.getCompanyProfile())
                .usingRecursiveComparison()
                .isEqualTo(companyProfileExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object recDeletedExpectedValue = patchEntity.getRecDeleted() != null ? patchEntity.getRecDeleted() : originalEntity.getRecDeleted();
        assertThat(actualEntity.getRecDeleted())
                .isEqualTo(recDeletedExpectedValue);

        Object nameExpectedValue = patchEntity.getName() != null ? patchEntity.getName() : originalEntity.getName();
        assertThat(actualEntity.getName())
                .isEqualTo(nameExpectedValue);

        Object addressCityExpectedValue = patchEntity.getAddressCity() != null ? patchEntity.getAddressCity() : originalEntity.getAddressCity();
        assertThat(actualEntity.getAddressCity())
                .isEqualTo(addressCityExpectedValue);

        Object addressRegionExpectedValue = patchEntity.getAddressRegion() != null ? patchEntity.getAddressRegion() : originalEntity.getAddressRegion();
        assertThat(actualEntity.getAddressRegion())
                .isEqualTo(addressRegionExpectedValue);

        Object addressStreetExpectedValue = patchEntity.getAddressStreet() != null ? patchEntity.getAddressStreet() : originalEntity.getAddressStreet();
        assertThat(actualEntity.getAddressStreet())
                .isEqualTo(addressStreetExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object objectiveExpectedValue = patchEntity.getObjective() != null ? patchEntity.getObjective() : originalEntity.getObjective();
        assertThat(actualEntity.getObjective())
                .isEqualTo(objectiveExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyProfileI18nDtoListForNullOrEmptyCompanyProfileI18nList() {
        assertThat(companyProfileI18nMapper.toDTOList(null)).isEmpty();
        assertThat(companyProfileI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyProfileI18nListForNullOrEmptyCompanyProfileI18nDtoList() {
        assertThat(companyProfileI18nMapper.toEntityList(null)).isEmpty();
        assertThat(companyProfileI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyProfileI18n entity = createSampleCompanyProfileI18nEntity();
        CompanyProfileI18n expectedEntity = createSampleCompanyProfileI18nEntity();

        companyProfileI18nMapper.partialUpdate(entity, null);
        companyProfileI18nMapper.partialUpdate(null, createPatchCompanyProfileI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyProfileI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfileI18n createSampleCompanyProfileI18nEntity() {
        CompanyProfileI18n entity = new CompanyProfileI18n();
        CompanyProfileI18nKey idFixture1 = new CompanyProfileI18nKey();
        entity.setId(idFixture1);
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
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setRecDeleted(true);
        entity.setName("nameValue1");
        entity.setAddressCity("addressCityValue1");
        entity.setAddressRegion("addressRegionValue1");
        entity.setAddressStreet("addressStreetValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setObjective("objectiveValue1");

        return entity;
    }

    /**
     * Creates a populated CompanyProfileI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfileI18n createAnotherCompanyProfileI18nEntity() {
        CompanyProfileI18n entity = new CompanyProfileI18n();
        CompanyProfileI18nKey idFixture2 = new CompanyProfileI18nKey();
        entity.setId(idFixture2);
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
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setRecDeleted(false);
        entity.setName("nameValue2");
        entity.setAddressCity("addressCityValue2");
        entity.setAddressRegion("addressRegionValue2");
        entity.setAddressStreet("addressStreetValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setObjective("objectiveValue2");

        return entity;
    }

    /**
     * Creates a populated CompanyProfileI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileI18nDto createSampleCompanyProfileI18nDto() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();
        CompanyProfileI18nKey idFixture1 = new CompanyProfileI18nKey();
        dto.setId(idFixture1);
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
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setRecDeleted(true);
        dto.setName("nameValue1");
        dto.setAddressCity("addressCityValue1");
        dto.setAddressRegion("addressRegionValue1");
        dto.setAddressStreet("addressStreetValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setObjective("objectiveValue1");

        return dto;
    }

    /**
     * Creates a populated CompanyProfileI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileI18nDto createAnotherCompanyProfileI18nDto() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();
        CompanyProfileI18nKey idFixture2 = new CompanyProfileI18nKey();
        dto.setId(idFixture2);
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
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setRecDeleted(false);
        dto.setName("nameValue2");
        dto.setAddressCity("addressCityValue2");
        dto.setAddressRegion("addressRegionValue2");
        dto.setAddressStreet("addressStreetValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setObjective("objectiveValue2");

        return dto;
    }

    /**
     * Creates a populated CompanyProfileI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileI18nDto createPatchCompanyProfileI18nDto() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();
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
        dto.setRecDeleted(true);
        dto.setAddressRegion("addressRegionValue3");
        dto.setAddressStreet("addressStreetValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
