package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BgPoi;
import gr.knowledge.pepTest.entity.BgPoiI18n;
import gr.knowledge.pepTest.entity.Languages;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class BgPoiI18nMapperTest {

    private BgPoiI18nMapper bgPoiI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        bgPoiI18nMapper = new BgPoiI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapBgPoiI18nToBgPoiI18nDto() {
        BgPoiI18n entity = createSampleBgPoiI18nEntity();
        BgPoiI18nDto expectedDto = createSampleBgPoiI18nDto();

        BgPoiI18nDto actualDto = bgPoiI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapBgPoiI18nDtoToBgPoiI18n() {
        BgPoiI18nDto dto = createSampleBgPoiI18nDto();
        BgPoiI18n expectedEntity = createSampleBgPoiI18nEntity();

        BgPoiI18n actualEntity = bgPoiI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapBgPoiI18nListToBgPoiI18nDtoList() {
        List<BgPoiI18n> entityList = List.of(
                createSampleBgPoiI18nEntity(),
                createAnotherBgPoiI18nEntity()
        );
        List<BgPoiI18nDto> expectedDtoList = List.of(
                createSampleBgPoiI18nDto(),
                createAnotherBgPoiI18nDto()
        );

        List<BgPoiI18nDto> actualDtoList = bgPoiI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapBgPoiI18nDtoListToBgPoiI18nList() {
        List<BgPoiI18nDto> dtoList = List.of(
                createSampleBgPoiI18nDto(),
                createAnotherBgPoiI18nDto()
        );
        List<BgPoiI18n> expectedEntityList = List.of(
                createSampleBgPoiI18nEntity(),
                createAnotherBgPoiI18nEntity()
        );

        List<BgPoiI18n> actualEntityList = bgPoiI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForBgPoiI18n() {
        BgPoiI18n originalEntity = createSampleBgPoiI18nEntity();
        BgPoiI18n actualEntity = createSampleBgPoiI18nEntity();
        BgPoiI18nDto patchDto = createPatchBgPoiI18nDto();
        BgPoiI18n patchEntity = bgPoiI18nMapper.toEntity(patchDto);

        bgPoiI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object titleExpectedValue = patchEntity.getTitle() != null ? patchEntity.getTitle() : originalEntity.getTitle();
        assertThat(actualEntity.getTitle())
                .isEqualTo(titleExpectedValue);

        Object poiExpectedValue = patchEntity.getPoi() != null ? patchEntity.getPoi() : originalEntity.getPoi();
        assertThat(actualEntity.getPoi())
                .usingRecursiveComparison()
                .isEqualTo(poiExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBgPoiI18nDtoListForNullOrEmptyBgPoiI18nList() {
        assertThat(bgPoiI18nMapper.toDTOList(null)).isEmpty();
        assertThat(bgPoiI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBgPoiI18nListForNullOrEmptyBgPoiI18nDtoList() {
        assertThat(bgPoiI18nMapper.toEntityList(null)).isEmpty();
        assertThat(bgPoiI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        BgPoiI18n entity = createSampleBgPoiI18nEntity();
        BgPoiI18n expectedEntity = createSampleBgPoiI18nEntity();

        bgPoiI18nMapper.partialUpdate(entity, null);
        bgPoiI18nMapper.partialUpdate(null, createPatchBgPoiI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated BgPoiI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BgPoiI18n createSampleBgPoiI18nEntity() {
        BgPoiI18n entity = new BgPoiI18n();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setTitle("titleValue1");
        BgPoi poiFixture1 = new BgPoi();
        poiFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        poiFixture1.setChamberId(10);
        poiFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        poiFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        poiFixture1.setRecdeleted(true);
        poiFixture1.setLatitude("latitudeValue1");
        poiFixture1.setLongitude("longitudeValue1");
        entity.setPoi(poiFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);

        return entity;
    }

    /**
     * Creates a populated BgPoiI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BgPoiI18n createAnotherBgPoiI18nEntity() {
        BgPoiI18n entity = new BgPoiI18n();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setTitle("titleValue2");
        BgPoi poiFixture2 = new BgPoi();
        poiFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        poiFixture2.setChamberId(20);
        poiFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        poiFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        poiFixture2.setRecdeleted(false);
        poiFixture2.setLatitude("latitudeValue2");
        poiFixture2.setLongitude("longitudeValue2");
        entity.setPoi(poiFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);

        return entity;
    }

    /**
     * Creates a populated BgPoiI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BgPoiI18nDto createSampleBgPoiI18nDto() {
        BgPoiI18nDto dto = new BgPoiI18nDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setTitle("titleValue1");
        BgPoiDto poiFixture1 = new BgPoiDto();
        poiFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        poiFixture1.setChamberId(10);
        poiFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        poiFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        poiFixture1.setRecdeleted(true);
        poiFixture1.setLatitude("latitudeValue1");
        poiFixture1.setLongitude("longitudeValue1");
        dto.setPoi(poiFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);

        return dto;
    }

    /**
     * Creates a populated BgPoiI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BgPoiI18nDto createAnotherBgPoiI18nDto() {
        BgPoiI18nDto dto = new BgPoiI18nDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setTitle("titleValue2");
        BgPoiDto poiFixture2 = new BgPoiDto();
        poiFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        poiFixture2.setChamberId(20);
        poiFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        poiFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        poiFixture2.setRecdeleted(false);
        poiFixture2.setLatitude("latitudeValue2");
        poiFixture2.setLongitude("longitudeValue2");
        dto.setPoi(poiFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);

        return dto;
    }

    /**
     * Creates a populated BgPoiI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BgPoiI18nDto createPatchBgPoiI18nDto() {
        BgPoiI18nDto dto = new BgPoiI18nDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        BgPoiDto poiFixture3 = new BgPoiDto();
        poiFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        poiFixture3.setChamberId(30);
        poiFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        poiFixture3.setRecdeleted(true);
        dto.setPoi(poiFixture3);

        return dto;
    }

}
