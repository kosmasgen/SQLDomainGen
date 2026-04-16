package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.entity.BgPoi;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class BgPoiMapperTest {

    private BgPoiMapper bgPoiMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        bgPoiMapper = new BgPoiMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapBgPoiToBgPoiDto() {
        BgPoi entity = createSampleBgPoiEntity();
        BgPoiDto expectedDto = createSampleBgPoiDto();

        BgPoiDto actualDto = bgPoiMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapBgPoiDtoToBgPoi() {
        BgPoiDto dto = createSampleBgPoiDto();
        BgPoi expectedEntity = createSampleBgPoiEntity();

        BgPoi actualEntity = bgPoiMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapBgPoiListToBgPoiDtoList() {
        List<BgPoi> entityList = List.of(
                createSampleBgPoiEntity(),
                createAnotherBgPoiEntity()
        );
        List<BgPoiDto> expectedDtoList = List.of(
                createSampleBgPoiDto(),
                createAnotherBgPoiDto()
        );

        List<BgPoiDto> actualDtoList = bgPoiMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapBgPoiDtoListToBgPoiList() {
        List<BgPoiDto> dtoList = List.of(
                createSampleBgPoiDto(),
                createAnotherBgPoiDto()
        );
        List<BgPoi> expectedEntityList = List.of(
                createSampleBgPoiEntity(),
                createAnotherBgPoiEntity()
        );

        List<BgPoi> actualEntityList = bgPoiMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForBgPoi() {
        BgPoi originalEntity = createSampleBgPoiEntity();
        BgPoi actualEntity = createSampleBgPoiEntity();
        BgPoiDto patchDto = createPatchBgPoiDto();
        BgPoi patchEntity = bgPoiMapper.toEntity(patchDto);

        bgPoiMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object latitudeExpectedValue = patchEntity.getLatitude() != null ? patchEntity.getLatitude() : originalEntity.getLatitude();
        assertThat(actualEntity.getLatitude())
                .isEqualTo(latitudeExpectedValue);

        Object longitudeExpectedValue = patchEntity.getLongitude() != null ? patchEntity.getLongitude() : originalEntity.getLongitude();
        assertThat(actualEntity.getLongitude())
                .isEqualTo(longitudeExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBgPoiDtoListForNullOrEmptyBgPoiList() {
        assertThat(bgPoiMapper.toDTOList(null)).isEmpty();
        assertThat(bgPoiMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBgPoiListForNullOrEmptyBgPoiDtoList() {
        assertThat(bgPoiMapper.toEntityList(null)).isEmpty();
        assertThat(bgPoiMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        BgPoi entity = createSampleBgPoiEntity();
        BgPoi expectedEntity = createSampleBgPoiEntity();

        bgPoiMapper.partialUpdate(entity, null);
        bgPoiMapper.partialUpdate(null, createPatchBgPoiDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated BgPoi fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BgPoi createSampleBgPoiEntity() {
        BgPoi entity = new BgPoi();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setLatitude("latitudeValue1");
        entity.setLongitude("longitudeValue1");

        return entity;
    }

    /**
     * Creates a populated BgPoi fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BgPoi createAnotherBgPoiEntity() {
        BgPoi entity = new BgPoi();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setLatitude("latitudeValue2");
        entity.setLongitude("longitudeValue2");

        return entity;
    }

    /**
     * Creates a populated BgPoiDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BgPoiDto createSampleBgPoiDto() {
        BgPoiDto dto = new BgPoiDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setLatitude("latitudeValue1");
        dto.setLongitude("longitudeValue1");

        return dto;
    }

    /**
     * Creates a populated BgPoiDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BgPoiDto createAnotherBgPoiDto() {
        BgPoiDto dto = new BgPoiDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setLatitude("latitudeValue2");
        dto.setLongitude("longitudeValue2");

        return dto;
    }

    /**
     * Creates a populated BgPoiDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BgPoiDto createPatchBgPoiDto() {
        BgPoiDto dto = new BgPoiDto();
        dto.setChamberId(30);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
