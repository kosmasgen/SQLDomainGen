package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.CountryI18n;
import gr.knowledge.pepTest.entity.CountryI18nKey;
import gr.knowledge.pepTest.entity.Languages;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CountryI18nMapperTest {

    private CountryI18nMapper countryI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        countryI18nMapper = new CountryI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCountryI18nToCountryI18nDto() {
        CountryI18n entity = createSampleCountryI18nEntity();
        CountryI18nDto expectedDto = createSampleCountryI18nDto();

        CountryI18nDto actualDto = countryI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCountryI18nDtoToCountryI18n() {
        CountryI18nDto dto = createSampleCountryI18nDto();
        CountryI18n expectedEntity = createSampleCountryI18nEntity();

        CountryI18n actualEntity = countryI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCountryI18nListToCountryI18nDtoList() {
        List<CountryI18n> entityList = List.of(
                createSampleCountryI18nEntity(),
                createAnotherCountryI18nEntity()
        );
        List<CountryI18nDto> expectedDtoList = List.of(
                createSampleCountryI18nDto(),
                createAnotherCountryI18nDto()
        );

        List<CountryI18nDto> actualDtoList = countryI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCountryI18nDtoListToCountryI18nList() {
        List<CountryI18nDto> dtoList = List.of(
                createSampleCountryI18nDto(),
                createAnotherCountryI18nDto()
        );
        List<CountryI18n> expectedEntityList = List.of(
                createSampleCountryI18nEntity(),
                createAnotherCountryI18nEntity()
        );

        List<CountryI18n> actualEntityList = countryI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCountryI18n() {
        CountryI18n originalEntity = createSampleCountryI18nEntity();
        CountryI18n actualEntity = createSampleCountryI18nEntity();
        CountryI18nDto patchDto = createPatchCountryI18nDto();
        CountryI18n patchEntity = countryI18nMapper.toEntity(patchDto);

        countryI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object countryExpectedValue = patchEntity.getCountry() != null ? patchEntity.getCountry() : originalEntity.getCountry();
        assertThat(actualEntity.getCountry())
                .usingRecursiveComparison()
                .isEqualTo(countryExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object chamberCountryI18nIdExpectedValue = patchEntity.getChamberCountryI18nId() != null ? patchEntity.getChamberCountryI18nId() : originalEntity.getChamberCountryI18nId();
        assertThat(actualEntity.getChamberCountryI18nId())
                .isEqualTo(chamberCountryI18nIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCountryI18nDtoListForNullOrEmptyCountryI18nList() {
        assertThat(countryI18nMapper.toDTOList(null)).isEmpty();
        assertThat(countryI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCountryI18nListForNullOrEmptyCountryI18nDtoList() {
        assertThat(countryI18nMapper.toEntityList(null)).isEmpty();
        assertThat(countryI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CountryI18n entity = createSampleCountryI18nEntity();
        CountryI18n expectedEntity = createSampleCountryI18nEntity();

        countryI18nMapper.partialUpdate(entity, null);
        countryI18nMapper.partialUpdate(null, createPatchCountryI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CountryI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CountryI18n createSampleCountryI18nEntity() {
        CountryI18n entity = new CountryI18n();
        CountryI18nKey idFixture1 = new CountryI18nKey();
        entity.setId(idFixture1);
        Country countryFixture1 = new Country();
        countryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setRecdeleted(true);
        countryFixture1.setChamberId(10);
        countryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setChamberCountryId(10);
        entity.setCountry(countryFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setDescription("descriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setChamberCountryI18nId(10);

        return entity;
    }

    /**
     * Creates a populated CountryI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CountryI18n createAnotherCountryI18nEntity() {
        CountryI18n entity = new CountryI18n();
        CountryI18nKey idFixture2 = new CountryI18nKey();
        entity.setId(idFixture2);
        Country countryFixture2 = new Country();
        countryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setRecdeleted(false);
        countryFixture2.setChamberId(20);
        countryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setChamberCountryId(20);
        entity.setCountry(countryFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setDescription("descriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setChamberCountryI18nId(20);

        return entity;
    }

    /**
     * Creates a populated CountryI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CountryI18nDto createSampleCountryI18nDto() {
        CountryI18nDto dto = new CountryI18nDto();
        CountryI18nKey idFixture1 = new CountryI18nKey();
        dto.setId(idFixture1);
        CountryDto countryFixture1 = new CountryDto();
        countryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        countryFixture1.setRecdeleted(true);
        countryFixture1.setChamberId(10);
        countryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        countryFixture1.setChamberCountryId(10);
        dto.setCountry(countryFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setDescription("descriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setChamberCountryI18nId(10);

        return dto;
    }

    /**
     * Creates a populated CountryI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CountryI18nDto createAnotherCountryI18nDto() {
        CountryI18nDto dto = new CountryI18nDto();
        CountryI18nKey idFixture2 = new CountryI18nKey();
        dto.setId(idFixture2);
        CountryDto countryFixture2 = new CountryDto();
        countryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        countryFixture2.setRecdeleted(false);
        countryFixture2.setChamberId(20);
        countryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        countryFixture2.setChamberCountryId(20);
        dto.setCountry(countryFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setDescription("descriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setChamberCountryI18nId(20);

        return dto;
    }

    /**
     * Creates a populated CountryI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CountryI18nDto createPatchCountryI18nDto() {
        CountryI18nDto dto = new CountryI18nDto();
        CountryDto countryFixture3 = new CountryDto();
        countryFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        countryFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        countryFixture3.setRecdeleted(true);
        countryFixture3.setChamberId(30);
        countryFixture3.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setCountry(countryFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
