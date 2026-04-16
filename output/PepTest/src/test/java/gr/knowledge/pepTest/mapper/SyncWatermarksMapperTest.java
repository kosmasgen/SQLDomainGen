package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import gr.knowledge.pepTest.entity.SyncWatermarks;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class SyncWatermarksMapperTest {

    private SyncWatermarksMapper syncWatermarksMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        syncWatermarksMapper = new SyncWatermarksMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapSyncWatermarksToSyncWatermarksDto() {
        SyncWatermarks entity = createSampleSyncWatermarksEntity();
        SyncWatermarksDto expectedDto = createSampleSyncWatermarksDto();

        SyncWatermarksDto actualDto = syncWatermarksMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapSyncWatermarksDtoToSyncWatermarks() {
        SyncWatermarksDto dto = createSampleSyncWatermarksDto();
        SyncWatermarks expectedEntity = createSampleSyncWatermarksEntity();

        SyncWatermarks actualEntity = syncWatermarksMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapSyncWatermarksListToSyncWatermarksDtoList() {
        List<SyncWatermarks> entityList = List.of(
                createSampleSyncWatermarksEntity(),
                createAnotherSyncWatermarksEntity()
        );
        List<SyncWatermarksDto> expectedDtoList = List.of(
                createSampleSyncWatermarksDto(),
                createAnotherSyncWatermarksDto()
        );

        List<SyncWatermarksDto> actualDtoList = syncWatermarksMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapSyncWatermarksDtoListToSyncWatermarksList() {
        List<SyncWatermarksDto> dtoList = List.of(
                createSampleSyncWatermarksDto(),
                createAnotherSyncWatermarksDto()
        );
        List<SyncWatermarks> expectedEntityList = List.of(
                createSampleSyncWatermarksEntity(),
                createAnotherSyncWatermarksEntity()
        );

        List<SyncWatermarks> actualEntityList = syncWatermarksMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForSyncWatermarks() {
        SyncWatermarks originalEntity = createSampleSyncWatermarksEntity();
        SyncWatermarks actualEntity = createSampleSyncWatermarksEntity();
        SyncWatermarksDto patchDto = createPatchSyncWatermarksDto();
        SyncWatermarks patchEntity = syncWatermarksMapper.toEntity(patchDto);

        syncWatermarksMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object tableNameExpectedValue = patchEntity.getTableName() != null ? patchEntity.getTableName() : originalEntity.getTableName();
        assertThat(actualEntity.getTableName())
                .isEqualTo(tableNameExpectedValue);

        Object lastSyncTimestampExpectedValue = patchEntity.getLastSyncTimestamp() != null ? patchEntity.getLastSyncTimestamp() : originalEntity.getLastSyncTimestamp();
        assertThat(actualEntity.getLastSyncTimestamp())
                .isEqualTo(lastSyncTimestampExpectedValue);

        Object updatedAtExpectedValue = patchEntity.getUpdatedAt() != null ? patchEntity.getUpdatedAt() : originalEntity.getUpdatedAt();
        assertThat(actualEntity.getUpdatedAt())
                .isEqualTo(updatedAtExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptySyncWatermarksDtoListForNullOrEmptySyncWatermarksList() {
        assertThat(syncWatermarksMapper.toDTOList(null)).isEmpty();
        assertThat(syncWatermarksMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptySyncWatermarksListForNullOrEmptySyncWatermarksDtoList() {
        assertThat(syncWatermarksMapper.toEntityList(null)).isEmpty();
        assertThat(syncWatermarksMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        SyncWatermarks entity = createSampleSyncWatermarksEntity();
        SyncWatermarks expectedEntity = createSampleSyncWatermarksEntity();

        syncWatermarksMapper.partialUpdate(entity, null);
        syncWatermarksMapper.partialUpdate(null, createPatchSyncWatermarksDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated SyncWatermarks fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private SyncWatermarks createSampleSyncWatermarksEntity() {
        SyncWatermarks entity = new SyncWatermarks();
        entity.setId(100L);
        entity.setTableName("tableNameValue1");
        entity.setLastSyncTimestamp(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setUpdatedAt(LocalDateTime.of(2024, 1, 11, 9, 6));

        return entity;
    }

    /**
     * Creates a populated SyncWatermarks fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private SyncWatermarks createAnotherSyncWatermarksEntity() {
        SyncWatermarks entity = new SyncWatermarks();
        entity.setId(200L);
        entity.setTableName("tableNameValue2");
        entity.setLastSyncTimestamp(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setUpdatedAt(LocalDateTime.of(2024, 2, 12, 10, 7));

        return entity;
    }

    /**
     * Creates a populated SyncWatermarksDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private SyncWatermarksDto createSampleSyncWatermarksDto() {
        SyncWatermarksDto dto = new SyncWatermarksDto();
        dto.setId(100L);
        dto.setTableName("tableNameValue1");
        dto.setLastSyncTimestamp(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setUpdatedAt(LocalDateTime.of(2024, 1, 11, 9, 6));

        return dto;
    }

    /**
     * Creates a populated SyncWatermarksDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private SyncWatermarksDto createAnotherSyncWatermarksDto() {
        SyncWatermarksDto dto = new SyncWatermarksDto();
        dto.setId(200L);
        dto.setTableName("tableNameValue2");
        dto.setLastSyncTimestamp(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setUpdatedAt(LocalDateTime.of(2024, 2, 12, 10, 7));

        return dto;
    }

    /**
     * Creates a populated SyncWatermarksDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private SyncWatermarksDto createPatchSyncWatermarksDto() {
        SyncWatermarksDto dto = new SyncWatermarksDto();

        return dto;
    }

}
