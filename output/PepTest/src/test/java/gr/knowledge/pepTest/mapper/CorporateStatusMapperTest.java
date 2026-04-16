package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.entity.CorporateStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CorporateStatusMapperTest {

    private CorporateStatusMapper corporateStatusMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        corporateStatusMapper = new CorporateStatusMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCorporateStatusToCorporateStatusDto() {
        CorporateStatus entity = createSampleCorporateStatusEntity();
        CorporateStatusDto expectedDto = createSampleCorporateStatusDto();

        CorporateStatusDto actualDto = corporateStatusMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCorporateStatusDtoToCorporateStatus() {
        CorporateStatusDto dto = createSampleCorporateStatusDto();
        CorporateStatus expectedEntity = createSampleCorporateStatusEntity();

        CorporateStatus actualEntity = corporateStatusMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCorporateStatusListToCorporateStatusDtoList() {
        List<CorporateStatus> entityList = List.of(
                createSampleCorporateStatusEntity(),
                createAnotherCorporateStatusEntity()
        );
        List<CorporateStatusDto> expectedDtoList = List.of(
                createSampleCorporateStatusDto(),
                createAnotherCorporateStatusDto()
        );

        List<CorporateStatusDto> actualDtoList = corporateStatusMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCorporateStatusDtoListToCorporateStatusList() {
        List<CorporateStatusDto> dtoList = List.of(
                createSampleCorporateStatusDto(),
                createAnotherCorporateStatusDto()
        );
        List<CorporateStatus> expectedEntityList = List.of(
                createSampleCorporateStatusEntity(),
                createAnotherCorporateStatusEntity()
        );

        List<CorporateStatus> actualEntityList = corporateStatusMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCorporateStatus() {
        CorporateStatus originalEntity = createSampleCorporateStatusEntity();
        CorporateStatus actualEntity = createSampleCorporateStatusEntity();
        CorporateStatusDto patchDto = createPatchCorporateStatusDto();
        CorporateStatus patchEntity = corporateStatusMapper.toEntity(patchDto);

        corporateStatusMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberCorporateStatusIdExpectedValue = patchEntity.getChamberCorporateStatusId() != null ? patchEntity.getChamberCorporateStatusId() : originalEntity.getChamberCorporateStatusId();
        assertThat(actualEntity.getChamberCorporateStatusId())
                .isEqualTo(chamberCorporateStatusIdExpectedValue);

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

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
    void shouldReturnEmptyCorporateStatusDtoListForNullOrEmptyCorporateStatusList() {
        assertThat(corporateStatusMapper.toDTOList(null)).isEmpty();
        assertThat(corporateStatusMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCorporateStatusListForNullOrEmptyCorporateStatusDtoList() {
        assertThat(corporateStatusMapper.toEntityList(null)).isEmpty();
        assertThat(corporateStatusMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CorporateStatus entity = createSampleCorporateStatusEntity();
        CorporateStatus expectedEntity = createSampleCorporateStatusEntity();

        corporateStatusMapper.partialUpdate(entity, null);
        corporateStatusMapper.partialUpdate(null, createPatchCorporateStatusDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CorporateStatus fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatus createSampleCorporateStatusEntity() {
        CorporateStatus entity = new CorporateStatus();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberCorporateStatusId(10);
        entity.setChamberId(10);
        entity.setCd("cdValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CorporateStatus fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatus createAnotherCorporateStatusEntity() {
        CorporateStatus entity = new CorporateStatus();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberCorporateStatusId(20);
        entity.setChamberId(20);
        entity.setCd("cdValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CorporateStatusDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusDto createSampleCorporateStatusDto() {
        CorporateStatusDto dto = new CorporateStatusDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberCorporateStatusId(10);
        dto.setChamberId(10);
        dto.setCd("cdValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CorporateStatusDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusDto createAnotherCorporateStatusDto() {
        CorporateStatusDto dto = new CorporateStatusDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberCorporateStatusId(20);
        dto.setChamberId(20);
        dto.setCd("cdValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CorporateStatusDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusDto createPatchCorporateStatusDto() {
        CorporateStatusDto dto = new CorporateStatusDto();
        dto.setChamberCorporateStatusId(30);
        dto.setChamberId(30);
        dto.setCd("cdValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
