package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.ProfessionKind;
import gr.knowledge.pepTest.entity.ProfessionKindi18n;
import gr.knowledge.pepTest.entity.ProfessionKindi18nKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ProfessionKindi18nMapperTest {

    private ProfessionKindi18nMapper professionKindi18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        professionKindi18nMapper = new ProfessionKindi18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionKindi18nToProfessionKindi18nDto() {
        ProfessionKindi18n entity = createSampleProfessionKindi18nEntity();
        ProfessionKindi18nDto expectedDto = createSampleProfessionKindi18nDto();

        ProfessionKindi18nDto actualDto = professionKindi18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionKindi18nDtoToProfessionKindi18n() {
        ProfessionKindi18nDto dto = createSampleProfessionKindi18nDto();
        ProfessionKindi18n expectedEntity = createSampleProfessionKindi18nEntity();

        ProfessionKindi18n actualEntity = professionKindi18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProfessionKindi18nListToProfessionKindi18nDtoList() {
        List<ProfessionKindi18n> entityList = List.of(
                createSampleProfessionKindi18nEntity(),
                createAnotherProfessionKindi18nEntity()
        );
        List<ProfessionKindi18nDto> expectedDtoList = List.of(
                createSampleProfessionKindi18nDto(),
                createAnotherProfessionKindi18nDto()
        );

        List<ProfessionKindi18nDto> actualDtoList = professionKindi18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProfessionKindi18nDtoListToProfessionKindi18nList() {
        List<ProfessionKindi18nDto> dtoList = List.of(
                createSampleProfessionKindi18nDto(),
                createAnotherProfessionKindi18nDto()
        );
        List<ProfessionKindi18n> expectedEntityList = List.of(
                createSampleProfessionKindi18nEntity(),
                createAnotherProfessionKindi18nEntity()
        );

        List<ProfessionKindi18n> actualEntityList = professionKindi18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProfessionKindi18n() {
        ProfessionKindi18n originalEntity = createSampleProfessionKindi18nEntity();
        ProfessionKindi18n actualEntity = createSampleProfessionKindi18nEntity();
        ProfessionKindi18nDto patchDto = createPatchProfessionKindi18nDto();
        ProfessionKindi18n patchEntity = professionKindi18nMapper.toEntity(patchDto);

        professionKindi18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object professionKindExpectedValue = patchEntity.getProfessionKind() != null ? patchEntity.getProfessionKind() : originalEntity.getProfessionKind();
        assertThat(actualEntity.getProfessionKind())
                .usingRecursiveComparison()
                .isEqualTo(professionKindExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object chamberI18nIdExpectedValue = patchEntity.getChamberI18nId() != null ? patchEntity.getChamberI18nId() : originalEntity.getChamberI18nId();
        assertThat(actualEntity.getChamberI18nId())
                .isEqualTo(chamberI18nIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionKindi18nDtoListForNullOrEmptyProfessionKindi18nList() {
        assertThat(professionKindi18nMapper.toDTOList(null)).isEmpty();
        assertThat(professionKindi18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionKindi18nListForNullOrEmptyProfessionKindi18nDtoList() {
        assertThat(professionKindi18nMapper.toEntityList(null)).isEmpty();
        assertThat(professionKindi18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ProfessionKindi18n entity = createSampleProfessionKindi18nEntity();
        ProfessionKindi18n expectedEntity = createSampleProfessionKindi18nEntity();

        professionKindi18nMapper.partialUpdate(entity, null);
        professionKindi18nMapper.partialUpdate(null, createPatchProfessionKindi18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ProfessionKindi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKindi18n createSampleProfessionKindi18nEntity() {
        ProfessionKindi18n entity = new ProfessionKindi18n();
        ProfessionKindi18nKey idFixture1 = new ProfessionKindi18nKey();
        entity.setId(idFixture1);
        ProfessionKind professionKindFixture1 = new ProfessionKind();
        professionKindFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionKindFixture1.setChamberId(10);
        professionKindFixture1.setChamberProfKindId(10);
        professionKindFixture1.setCd("cdValue1");
        professionKindFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setRecdeleted(true);
        entity.setProfessionKind(professionKindFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setRecdeleted(true);
        entity.setDescription("descriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setChamberI18nId(10);

        return entity;
    }

    /**
     * Creates a populated ProfessionKindi18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKindi18n createAnotherProfessionKindi18nEntity() {
        ProfessionKindi18n entity = new ProfessionKindi18n();
        ProfessionKindi18nKey idFixture2 = new ProfessionKindi18nKey();
        entity.setId(idFixture2);
        ProfessionKind professionKindFixture2 = new ProfessionKind();
        professionKindFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionKindFixture2.setChamberId(20);
        professionKindFixture2.setChamberProfKindId(20);
        professionKindFixture2.setCd("cdValue2");
        professionKindFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setRecdeleted(false);
        entity.setProfessionKind(professionKindFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setRecdeleted(false);
        entity.setDescription("descriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setChamberI18nId(20);

        return entity;
    }

    /**
     * Creates a populated ProfessionKindi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindi18nDto createSampleProfessionKindi18nDto() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();
        ProfessionKindi18nKey idFixture1 = new ProfessionKindi18nKey();
        dto.setId(idFixture1);
        ProfessionKindDto professionKindFixture1 = new ProfessionKindDto();
        professionKindFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        professionKindFixture1.setChamberId(10);
        professionKindFixture1.setChamberProfKindId(10);
        professionKindFixture1.setCd("cdValue1");
        professionKindFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        professionKindFixture1.setRecdeleted(true);
        dto.setProfessionKind(professionKindFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setRecdeleted(true);
        dto.setDescription("descriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setChamberI18nId(10);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindi18nDto createAnotherProfessionKindi18nDto() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();
        ProfessionKindi18nKey idFixture2 = new ProfessionKindi18nKey();
        dto.setId(idFixture2);
        ProfessionKindDto professionKindFixture2 = new ProfessionKindDto();
        professionKindFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        professionKindFixture2.setChamberId(20);
        professionKindFixture2.setChamberProfKindId(20);
        professionKindFixture2.setCd("cdValue2");
        professionKindFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        professionKindFixture2.setRecdeleted(false);
        dto.setProfessionKind(professionKindFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setRecdeleted(false);
        dto.setDescription("descriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setChamberI18nId(20);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindi18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindi18nDto createPatchProfessionKindi18nDto() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();
        ProfessionKindDto professionKindFixture3 = new ProfessionKindDto();
        professionKindFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        professionKindFixture3.setChamberId(30);
        professionKindFixture3.setChamberProfKindId(30);
        professionKindFixture3.setCd("cdValue3");
        professionKindFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        professionKindFixture3.setRecdeleted(true);
        dto.setProfessionKind(professionKindFixture3);
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setChamberI18nId(30);

        return dto;
    }

}
