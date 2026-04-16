package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.Languages;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class LanguagesMapperTest {

    private LanguagesMapper languagesMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        languagesMapper = new LanguagesMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapLanguagesToLanguagesDto() {
        Languages entity = createSampleLanguagesEntity();
        LanguagesDto expectedDto = createSampleLanguagesDto();

        LanguagesDto actualDto = languagesMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapLanguagesDtoToLanguages() {
        LanguagesDto dto = createSampleLanguagesDto();
        Languages expectedEntity = createSampleLanguagesEntity();

        Languages actualEntity = languagesMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapLanguagesListToLanguagesDtoList() {
        List<Languages> entityList = List.of(
                createSampleLanguagesEntity(),
                createAnotherLanguagesEntity()
        );
        List<LanguagesDto> expectedDtoList = List.of(
                createSampleLanguagesDto(),
                createAnotherLanguagesDto()
        );

        List<LanguagesDto> actualDtoList = languagesMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapLanguagesDtoListToLanguagesList() {
        List<LanguagesDto> dtoList = List.of(
                createSampleLanguagesDto(),
                createAnotherLanguagesDto()
        );
        List<Languages> expectedEntityList = List.of(
                createSampleLanguagesEntity(),
                createAnotherLanguagesEntity()
        );

        List<Languages> actualEntityList = languagesMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForLanguages() {
        Languages originalEntity = createSampleLanguagesEntity();
        Languages actualEntity = createSampleLanguagesEntity();
        LanguagesDto patchDto = createPatchLanguagesDto();
        Languages patchEntity = languagesMapper.toEntity(patchDto);

        languagesMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object cdExpectedValue = patchEntity.getCd() != null ? patchEntity.getCd() : originalEntity.getCd();
        assertThat(actualEntity.getCd())
                .isEqualTo(cdExpectedValue);

        Object descrExpectedValue = patchEntity.getDescr() != null ? patchEntity.getDescr() : originalEntity.getDescr();
        assertThat(actualEntity.getDescr())
                .isEqualTo(descrExpectedValue);

        Object chamberLanguageIdExpectedValue = patchEntity.getChamberLanguageId() != null ? patchEntity.getChamberLanguageId() : originalEntity.getChamberLanguageId();
        assertThat(actualEntity.getChamberLanguageId())
                .isEqualTo(chamberLanguageIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyLanguagesDtoListForNullOrEmptyLanguagesList() {
        assertThat(languagesMapper.toDTOList(null)).isEmpty();
        assertThat(languagesMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyLanguagesListForNullOrEmptyLanguagesDtoList() {
        assertThat(languagesMapper.toEntityList(null)).isEmpty();
        assertThat(languagesMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Languages entity = createSampleLanguagesEntity();
        Languages expectedEntity = createSampleLanguagesEntity();

        languagesMapper.partialUpdate(entity, null);
        languagesMapper.partialUpdate(null, createPatchLanguagesDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Languages fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Languages createSampleLanguagesEntity() {
        Languages entity = new Languages();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setCd("cdValue1");
        entity.setDescr("descrValue1");
        entity.setChamberLanguageId(10);

        return entity;
    }

    /**
     * Creates a populated Languages fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Languages createAnotherLanguagesEntity() {
        Languages entity = new Languages();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setCd("cdValue2");
        entity.setDescr("descrValue2");
        entity.setChamberLanguageId(20);

        return entity;
    }

    /**
     * Creates a populated LanguagesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private LanguagesDto createSampleLanguagesDto() {
        LanguagesDto dto = new LanguagesDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setCd("cdValue1");
        dto.setDescr("descrValue1");
        dto.setChamberLanguageId(10);

        return dto;
    }

    /**
     * Creates a populated LanguagesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private LanguagesDto createAnotherLanguagesDto() {
        LanguagesDto dto = new LanguagesDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setCd("cdValue2");
        dto.setDescr("descrValue2");
        dto.setChamberLanguageId(20);

        return dto;
    }

    /**
     * Creates a populated LanguagesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private LanguagesDto createPatchLanguagesDto() {
        LanguagesDto dto = new LanguagesDto();
        dto.setChamberId(30);
        dto.setCd("cdValue3");
        dto.setDescr("descrValue3");

        return dto;
    }

}
