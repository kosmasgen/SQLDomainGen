package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ChamberDepartmentMapperTest {

    private ChamberDepartmentMapper chamberDepartmentMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        chamberDepartmentMapper = new ChamberDepartmentMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapChamberDepartmentToChamberDepartmentDto() {
        ChamberDepartment entity = createSampleChamberDepartmentEntity();
        ChamberDepartmentDto expectedDto = createSampleChamberDepartmentDto();

        ChamberDepartmentDto actualDto = chamberDepartmentMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapChamberDepartmentDtoToChamberDepartment() {
        ChamberDepartmentDto dto = createSampleChamberDepartmentDto();
        ChamberDepartment expectedEntity = createSampleChamberDepartmentEntity();

        ChamberDepartment actualEntity = chamberDepartmentMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapChamberDepartmentListToChamberDepartmentDtoList() {
        List<ChamberDepartment> entityList = List.of(
                createSampleChamberDepartmentEntity(),
                createAnotherChamberDepartmentEntity()
        );
        List<ChamberDepartmentDto> expectedDtoList = List.of(
                createSampleChamberDepartmentDto(),
                createAnotherChamberDepartmentDto()
        );

        List<ChamberDepartmentDto> actualDtoList = chamberDepartmentMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapChamberDepartmentDtoListToChamberDepartmentList() {
        List<ChamberDepartmentDto> dtoList = List.of(
                createSampleChamberDepartmentDto(),
                createAnotherChamberDepartmentDto()
        );
        List<ChamberDepartment> expectedEntityList = List.of(
                createSampleChamberDepartmentEntity(),
                createAnotherChamberDepartmentEntity()
        );

        List<ChamberDepartment> actualEntityList = chamberDepartmentMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForChamberDepartment() {
        ChamberDepartment originalEntity = createSampleChamberDepartmentEntity();
        ChamberDepartment actualEntity = createSampleChamberDepartmentEntity();
        ChamberDepartmentDto patchDto = createPatchChamberDepartmentDto();
        ChamberDepartment patchEntity = chamberDepartmentMapper.toEntity(patchDto);

        chamberDepartmentMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberDepartmentIdExpectedValue = patchEntity.getChamberDepartmentId() != null ? patchEntity.getChamberDepartmentId() : originalEntity.getChamberDepartmentId();
        assertThat(actualEntity.getChamberDepartmentId())
                .isEqualTo(chamberDepartmentIdExpectedValue);

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

        Object cdExpectedValue = patchEntity.getCd() != null ? patchEntity.getCd() : originalEntity.getCd();
        assertThat(actualEntity.getCd())
                .isEqualTo(cdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChamberDepartmentDtoListForNullOrEmptyChamberDepartmentList() {
        assertThat(chamberDepartmentMapper.toDTOList(null)).isEmpty();
        assertThat(chamberDepartmentMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChamberDepartmentListForNullOrEmptyChamberDepartmentDtoList() {
        assertThat(chamberDepartmentMapper.toEntityList(null)).isEmpty();
        assertThat(chamberDepartmentMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ChamberDepartment entity = createSampleChamberDepartmentEntity();
        ChamberDepartment expectedEntity = createSampleChamberDepartmentEntity();

        chamberDepartmentMapper.partialUpdate(entity, null);
        chamberDepartmentMapper.partialUpdate(null, createPatchChamberDepartmentDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ChamberDepartment fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartment createSampleChamberDepartmentEntity() {
        ChamberDepartment entity = new ChamberDepartment();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberDepartmentId(10);
        entity.setChamberId(10);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setCd("cdValue1");

        return entity;
    }

    /**
     * Creates a populated ChamberDepartment fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartment createAnotherChamberDepartmentEntity() {
        ChamberDepartment entity = new ChamberDepartment();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberDepartmentId(20);
        entity.setChamberId(20);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setCd("cdValue2");

        return entity;
    }

    /**
     * Creates a populated ChamberDepartmentDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmentDto createSampleChamberDepartmentDto() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberDepartmentId(10);
        dto.setChamberId(10);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setCd("cdValue1");

        return dto;
    }

    /**
     * Creates a populated ChamberDepartmentDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmentDto createAnotherChamberDepartmentDto() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberDepartmentId(20);
        dto.setChamberId(20);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setCd("cdValue2");

        return dto;
    }

    /**
     * Creates a populated ChamberDepartmentDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmentDto createPatchChamberDepartmentDto() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();
        dto.setChamberDepartmentId(30);
        dto.setChamberId(30);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setCd("cdValue3");

        return dto;
    }

}
