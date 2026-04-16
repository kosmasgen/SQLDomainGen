package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.entity.ProfessionKind;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ProfessionKindMapperTest {

    private ProfessionKindMapper professionKindMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        professionKindMapper = new ProfessionKindMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionKindToProfessionKindDto() {
        ProfessionKind entity = createSampleProfessionKindEntity();
        ProfessionKindDto expectedDto = createSampleProfessionKindDto();

        ProfessionKindDto actualDto = professionKindMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionKindDtoToProfessionKind() {
        ProfessionKindDto dto = createSampleProfessionKindDto();
        ProfessionKind expectedEntity = createSampleProfessionKindEntity();

        ProfessionKind actualEntity = professionKindMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProfessionKindListToProfessionKindDtoList() {
        List<ProfessionKind> entityList = List.of(
                createSampleProfessionKindEntity(),
                createAnotherProfessionKindEntity()
        );
        List<ProfessionKindDto> expectedDtoList = List.of(
                createSampleProfessionKindDto(),
                createAnotherProfessionKindDto()
        );

        List<ProfessionKindDto> actualDtoList = professionKindMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProfessionKindDtoListToProfessionKindList() {
        List<ProfessionKindDto> dtoList = List.of(
                createSampleProfessionKindDto(),
                createAnotherProfessionKindDto()
        );
        List<ProfessionKind> expectedEntityList = List.of(
                createSampleProfessionKindEntity(),
                createAnotherProfessionKindEntity()
        );

        List<ProfessionKind> actualEntityList = professionKindMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProfessionKind() {
        ProfessionKind originalEntity = createSampleProfessionKindEntity();
        ProfessionKind actualEntity = createSampleProfessionKindEntity();
        ProfessionKindDto patchDto = createPatchProfessionKindDto();
        ProfessionKind patchEntity = professionKindMapper.toEntity(patchDto);

        professionKindMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberProfKindIdExpectedValue = patchEntity.getChamberProfKindId() != null ? patchEntity.getChamberProfKindId() : originalEntity.getChamberProfKindId();
        assertThat(actualEntity.getChamberProfKindId())
                .isEqualTo(chamberProfKindIdExpectedValue);

        Object cdExpectedValue = patchEntity.getCd() != null ? patchEntity.getCd() : originalEntity.getCd();
        assertThat(actualEntity.getCd())
                .isEqualTo(cdExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionKindDtoListForNullOrEmptyProfessionKindList() {
        assertThat(professionKindMapper.toDTOList(null)).isEmpty();
        assertThat(professionKindMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionKindListForNullOrEmptyProfessionKindDtoList() {
        assertThat(professionKindMapper.toEntityList(null)).isEmpty();
        assertThat(professionKindMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ProfessionKind entity = createSampleProfessionKindEntity();
        ProfessionKind expectedEntity = createSampleProfessionKindEntity();

        professionKindMapper.partialUpdate(entity, null);
        professionKindMapper.partialUpdate(null, createPatchProfessionKindDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ProfessionKind fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKind createSampleProfessionKindEntity() {
        ProfessionKind entity = new ProfessionKind();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberProfKindId(10);
        entity.setCd("cdValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated ProfessionKind fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKind createAnotherProfessionKindEntity() {
        ProfessionKind entity = new ProfessionKind();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberProfKindId(20);
        entity.setCd("cdValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ProfessionKindDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindDto createSampleProfessionKindDto() {
        ProfessionKindDto dto = new ProfessionKindDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberProfKindId(10);
        dto.setCd("cdValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindDto createAnotherProfessionKindDto() {
        ProfessionKindDto dto = new ProfessionKindDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberProfKindId(20);
        dto.setCd("cdValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindDto createPatchProfessionKindDto() {
        ProfessionKindDto dto = new ProfessionKindDto();
        dto.setChamberId(30);
        dto.setChamberProfKindId(30);
        dto.setCd("cdValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
