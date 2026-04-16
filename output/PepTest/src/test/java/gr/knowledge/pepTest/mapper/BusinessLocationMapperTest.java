package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessLocationMapperTest {

    private BusinessLocationMapper businessLocationMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        businessLocationMapper = new BusinessLocationMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapBusinessLocationToBusinessLocationDto() {
        BusinessLocation entity = createSampleBusinessLocationEntity();
        BusinessLocationDto expectedDto = createSampleBusinessLocationDto();

        BusinessLocationDto actualDto = businessLocationMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapBusinessLocationDtoToBusinessLocation() {
        BusinessLocationDto dto = createSampleBusinessLocationDto();
        BusinessLocation expectedEntity = createSampleBusinessLocationEntity();

        BusinessLocation actualEntity = businessLocationMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapBusinessLocationListToBusinessLocationDtoList() {
        List<BusinessLocation> entityList = List.of(
                createSampleBusinessLocationEntity(),
                createAnotherBusinessLocationEntity()
        );
        List<BusinessLocationDto> expectedDtoList = List.of(
                createSampleBusinessLocationDto(),
                createAnotherBusinessLocationDto()
        );

        List<BusinessLocationDto> actualDtoList = businessLocationMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapBusinessLocationDtoListToBusinessLocationList() {
        List<BusinessLocationDto> dtoList = List.of(
                createSampleBusinessLocationDto(),
                createAnotherBusinessLocationDto()
        );
        List<BusinessLocation> expectedEntityList = List.of(
                createSampleBusinessLocationEntity(),
                createAnotherBusinessLocationEntity()
        );

        List<BusinessLocation> actualEntityList = businessLocationMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForBusinessLocation() {
        BusinessLocation originalEntity = createSampleBusinessLocationEntity();
        BusinessLocation actualEntity = createSampleBusinessLocationEntity();
        BusinessLocationDto patchDto = createPatchBusinessLocationDto();
        BusinessLocation patchEntity = businessLocationMapper.toEntity(patchDto);

        businessLocationMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object codeExpectedValue = patchEntity.getCode() != null ? patchEntity.getCode() : originalEntity.getCode();
        assertThat(actualEntity.getCode())
                .isEqualTo(codeExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object blobUriExpectedValue = patchEntity.getBlobUri() != null ? patchEntity.getBlobUri() : originalEntity.getBlobUri();
        assertThat(actualEntity.getBlobUri())
                .isEqualTo(blobUriExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBusinessLocationDtoListForNullOrEmptyBusinessLocationList() {
        assertThat(businessLocationMapper.toDTOList(null)).isEmpty();
        assertThat(businessLocationMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyBusinessLocationListForNullOrEmptyBusinessLocationDtoList() {
        assertThat(businessLocationMapper.toEntityList(null)).isEmpty();
        assertThat(businessLocationMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        BusinessLocation entity = createSampleBusinessLocationEntity();
        BusinessLocation expectedEntity = createSampleBusinessLocationEntity();

        businessLocationMapper.partialUpdate(entity, null);
        businessLocationMapper.partialUpdate(null, createPatchBusinessLocationDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated BusinessLocation fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocation createSampleBusinessLocationEntity() {
        BusinessLocation entity = new BusinessLocation();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setCode("codeValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setBlobUri("blobUriValue1");

        return entity;
    }

    /**
     * Creates a populated BusinessLocation fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocation createAnotherBusinessLocationEntity() {
        BusinessLocation entity = new BusinessLocation();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setCode("codeValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setBlobUri("blobUriValue2");

        return entity;
    }

    /**
     * Creates a populated BusinessLocationDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationDto createSampleBusinessLocationDto() {
        BusinessLocationDto dto = new BusinessLocationDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setCode("codeValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setBlobUri("blobUriValue1");

        return dto;
    }

    /**
     * Creates a populated BusinessLocationDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationDto createAnotherBusinessLocationDto() {
        BusinessLocationDto dto = new BusinessLocationDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setCode("codeValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setBlobUri("blobUriValue2");

        return dto;
    }

    /**
     * Creates a populated BusinessLocationDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationDto createPatchBusinessLocationDto() {
        BusinessLocationDto dto = new BusinessLocationDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setBlobUri("blobUriValue3");

        return dto;
    }

}
