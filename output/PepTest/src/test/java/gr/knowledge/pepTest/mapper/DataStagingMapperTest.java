package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.DataStagingDto;
import gr.knowledge.pepTest.entity.DataStaging;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class DataStagingMapperTest {

    private DataStagingMapper dataStagingMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        dataStagingMapper = new DataStagingMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapDataStagingToDataStagingDto() {
        DataStaging entity = createSampleDataStagingEntity();
        DataStagingDto expectedDto = createSampleDataStagingDto();

        DataStagingDto actualDto = dataStagingMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapDataStagingDtoToDataStaging() {
        DataStagingDto dto = createSampleDataStagingDto();
        DataStaging expectedEntity = createSampleDataStagingEntity();

        DataStaging actualEntity = dataStagingMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapDataStagingListToDataStagingDtoList() {
        List<DataStaging> entityList = List.of(
                createSampleDataStagingEntity(),
                createAnotherDataStagingEntity()
        );
        List<DataStagingDto> expectedDtoList = List.of(
                createSampleDataStagingDto(),
                createAnotherDataStagingDto()
        );

        List<DataStagingDto> actualDtoList = dataStagingMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapDataStagingDtoListToDataStagingList() {
        List<DataStagingDto> dtoList = List.of(
                createSampleDataStagingDto(),
                createAnotherDataStagingDto()
        );
        List<DataStaging> expectedEntityList = List.of(
                createSampleDataStagingEntity(),
                createAnotherDataStagingEntity()
        );

        List<DataStaging> actualEntityList = dataStagingMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForDataStaging() {
        DataStaging originalEntity = createSampleDataStagingEntity();
        DataStaging actualEntity = createSampleDataStagingEntity();
        DataStagingDto patchDto = createPatchDataStagingDto();
        DataStaging patchEntity = dataStagingMapper.toEntity(patchDto);

        dataStagingMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object legacyTableNameExpectedValue = patchEntity.getLegacyTableName() != null ? patchEntity.getLegacyTableName() : originalEntity.getLegacyTableName();
        assertThat(actualEntity.getLegacyTableName())
                .isEqualTo(legacyTableNameExpectedValue);

        Object legacyRecordIdExpectedValue = patchEntity.getLegacyRecordId() != null ? patchEntity.getLegacyRecordId() : originalEntity.getLegacyRecordId();
        assertThat(actualEntity.getLegacyRecordId())
                .isEqualTo(legacyRecordIdExpectedValue);

        Object rawDataExpectedValue = patchEntity.getRawData() != null ? patchEntity.getRawData() : originalEntity.getRawData();
        assertThat(actualEntity.getRawData())
                .isEqualTo(rawDataExpectedValue);

        Object legacyUpdatedAtExpectedValue = patchEntity.getLegacyUpdatedAt() != null ? patchEntity.getLegacyUpdatedAt() : originalEntity.getLegacyUpdatedAt();
        assertThat(actualEntity.getLegacyUpdatedAt())
                .isEqualTo(legacyUpdatedAtExpectedValue);

        Object pulledAtExpectedValue = patchEntity.getPulledAt() != null ? patchEntity.getPulledAt() : originalEntity.getPulledAt();
        assertThat(actualEntity.getPulledAt())
                .isEqualTo(pulledAtExpectedValue);

        Object statusExpectedValue = patchEntity.getStatus() != null ? patchEntity.getStatus() : originalEntity.getStatus();
        assertThat(actualEntity.getStatus())
                .isEqualTo(statusExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyDataStagingDtoListForNullOrEmptyDataStagingList() {
        assertThat(dataStagingMapper.toDTOList(null)).isEmpty();
        assertThat(dataStagingMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyDataStagingListForNullOrEmptyDataStagingDtoList() {
        assertThat(dataStagingMapper.toEntityList(null)).isEmpty();
        assertThat(dataStagingMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        DataStaging entity = createSampleDataStagingEntity();
        DataStaging expectedEntity = createSampleDataStagingEntity();

        dataStagingMapper.partialUpdate(entity, null);
        dataStagingMapper.partialUpdate(null, createPatchDataStagingDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated DataStaging fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private DataStaging createSampleDataStagingEntity() {
        DataStaging entity = new DataStaging();
        entity.setId(100L);
        entity.setLegacyTableName("legacyTableNameValue1");
        entity.setLegacyRecordId("legacyRecordIdValue1");
        entity.setRawData("rawDataValue1");
        entity.setLegacyUpdatedAt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setPulledAt(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setStatus("statusValue1");

        return entity;
    }

    /**
     * Creates a populated DataStaging fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private DataStaging createAnotherDataStagingEntity() {
        DataStaging entity = new DataStaging();
        entity.setId(200L);
        entity.setLegacyTableName("legacyTableNameValue2");
        entity.setLegacyRecordId("legacyRecordIdValue2");
        entity.setRawData("rawDataValue2");
        entity.setLegacyUpdatedAt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setPulledAt(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setStatus("statusValue2");

        return entity;
    }

    /**
     * Creates a populated DataStagingDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private DataStagingDto createSampleDataStagingDto() {
        DataStagingDto dto = new DataStagingDto();
        dto.setId(100L);
        dto.setLegacyTableName("legacyTableNameValue1");
        dto.setLegacyRecordId("legacyRecordIdValue1");
        dto.setRawData("rawDataValue1");
        dto.setLegacyUpdatedAt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setPulledAt(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setStatus("statusValue1");

        return dto;
    }

    /**
     * Creates a populated DataStagingDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private DataStagingDto createAnotherDataStagingDto() {
        DataStagingDto dto = new DataStagingDto();
        dto.setId(200L);
        dto.setLegacyTableName("legacyTableNameValue2");
        dto.setLegacyRecordId("legacyRecordIdValue2");
        dto.setRawData("rawDataValue2");
        dto.setLegacyUpdatedAt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setPulledAt(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setStatus("statusValue2");

        return dto;
    }

    /**
     * Creates a populated DataStagingDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private DataStagingDto createPatchDataStagingDto() {
        DataStagingDto dto = new DataStagingDto();
        dto.setLegacyTableName("legacyTableNameValue3");
        dto.setLegacyRecordId("legacyRecordIdValue3");
        dto.setLegacyUpdatedAt(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setPulledAt(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
