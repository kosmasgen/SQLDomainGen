package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import gr.knowledge.pepTest.entity.Syncruns;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class SyncrunsMapperTest {

    private SyncrunsMapper syncrunsMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        syncrunsMapper = new SyncrunsMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapSyncrunsToSyncrunsDto() {
        Syncruns entity = createSampleSyncrunsEntity();
        SyncrunsDto expectedDto = createSampleSyncrunsDto();

        SyncrunsDto actualDto = syncrunsMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapSyncrunsDtoToSyncruns() {
        SyncrunsDto dto = createSampleSyncrunsDto();
        Syncruns expectedEntity = createSampleSyncrunsEntity();

        Syncruns actualEntity = syncrunsMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapSyncrunsListToSyncrunsDtoList() {
        List<Syncruns> entityList = List.of(
                createSampleSyncrunsEntity(),
                createAnotherSyncrunsEntity()
        );
        List<SyncrunsDto> expectedDtoList = List.of(
                createSampleSyncrunsDto(),
                createAnotherSyncrunsDto()
        );

        List<SyncrunsDto> actualDtoList = syncrunsMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapSyncrunsDtoListToSyncrunsList() {
        List<SyncrunsDto> dtoList = List.of(
                createSampleSyncrunsDto(),
                createAnotherSyncrunsDto()
        );
        List<Syncruns> expectedEntityList = List.of(
                createSampleSyncrunsEntity(),
                createAnotherSyncrunsEntity()
        );

        List<Syncruns> actualEntityList = syncrunsMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForSyncruns() {
        Syncruns originalEntity = createSampleSyncrunsEntity();
        Syncruns actualEntity = createSampleSyncrunsEntity();
        SyncrunsDto patchDto = createPatchSyncrunsDto();
        Syncruns patchEntity = syncrunsMapper.toEntity(patchDto);

        syncrunsMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object lastRunExpectedValue = patchEntity.getLastRun() != null ? patchEntity.getLastRun() : originalEntity.getLastRun();
        assertThat(actualEntity.getLastRun())
                .isEqualTo(lastRunExpectedValue);

        Object tradesLastRunExpectedValue = patchEntity.getTradesLastRun() != null ? patchEntity.getTradesLastRun() : originalEntity.getTradesLastRun();
        assertThat(actualEntity.getTradesLastRun())
                .isEqualTo(tradesLastRunExpectedValue);

        Object isRunningExpectedValue = patchEntity.getIsRunning() != null ? patchEntity.getIsRunning() : originalEntity.getIsRunning();
        assertThat(actualEntity.getIsRunning())
                .isEqualTo(isRunningExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptySyncrunsDtoListForNullOrEmptySyncrunsList() {
        assertThat(syncrunsMapper.toDTOList(null)).isEmpty();
        assertThat(syncrunsMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptySyncrunsListForNullOrEmptySyncrunsDtoList() {
        assertThat(syncrunsMapper.toEntityList(null)).isEmpty();
        assertThat(syncrunsMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Syncruns entity = createSampleSyncrunsEntity();
        Syncruns expectedEntity = createSampleSyncrunsEntity();

        syncrunsMapper.partialUpdate(entity, null);
        syncrunsMapper.partialUpdate(null, createPatchSyncrunsDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Syncruns fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Syncruns createSampleSyncrunsEntity() {
        Syncruns entity = new Syncruns();
        entity.setId(100L);
        entity.setLastRun(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setTradesLastRun(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setIsRunning(true);

        return entity;
    }

    /**
     * Creates a populated Syncruns fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Syncruns createAnotherSyncrunsEntity() {
        Syncruns entity = new Syncruns();
        entity.setId(200L);
        entity.setLastRun(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setTradesLastRun(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setIsRunning(false);

        return entity;
    }

    /**
     * Creates a populated SyncrunsDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private SyncrunsDto createSampleSyncrunsDto() {
        SyncrunsDto dto = new SyncrunsDto();
        dto.setId(100L);
        dto.setLastRun(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setTradesLastRun(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setIsRunning(true);

        return dto;
    }

    /**
     * Creates a populated SyncrunsDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private SyncrunsDto createAnotherSyncrunsDto() {
        SyncrunsDto dto = new SyncrunsDto();
        dto.setId(200L);
        dto.setLastRun(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setTradesLastRun(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setIsRunning(false);

        return dto;
    }

    /**
     * Creates a populated SyncrunsDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private SyncrunsDto createPatchSyncrunsDto() {
        SyncrunsDto dto = new SyncrunsDto();
        dto.setLastRun(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setIsRunning(true);

        return dto;
    }

}
