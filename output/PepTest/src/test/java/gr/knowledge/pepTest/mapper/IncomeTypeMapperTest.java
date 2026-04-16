package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.entity.IncomeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class IncomeTypeMapperTest {

    private IncomeTypeMapper incomeTypeMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        incomeTypeMapper = new IncomeTypeMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapIncomeTypeToIncomeTypeDto() {
        IncomeType entity = createSampleIncomeTypeEntity();
        IncomeTypeDto expectedDto = createSampleIncomeTypeDto();

        IncomeTypeDto actualDto = incomeTypeMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapIncomeTypeDtoToIncomeType() {
        IncomeTypeDto dto = createSampleIncomeTypeDto();
        IncomeType expectedEntity = createSampleIncomeTypeEntity();

        IncomeType actualEntity = incomeTypeMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapIncomeTypeListToIncomeTypeDtoList() {
        List<IncomeType> entityList = List.of(
                createSampleIncomeTypeEntity(),
                createAnotherIncomeTypeEntity()
        );
        List<IncomeTypeDto> expectedDtoList = List.of(
                createSampleIncomeTypeDto(),
                createAnotherIncomeTypeDto()
        );

        List<IncomeTypeDto> actualDtoList = incomeTypeMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapIncomeTypeDtoListToIncomeTypeList() {
        List<IncomeTypeDto> dtoList = List.of(
                createSampleIncomeTypeDto(),
                createAnotherIncomeTypeDto()
        );
        List<IncomeType> expectedEntityList = List.of(
                createSampleIncomeTypeEntity(),
                createAnotherIncomeTypeEntity()
        );

        List<IncomeType> actualEntityList = incomeTypeMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForIncomeType() {
        IncomeType originalEntity = createSampleIncomeTypeEntity();
        IncomeType actualEntity = createSampleIncomeTypeEntity();
        IncomeTypeDto patchDto = createPatchIncomeTypeDto();
        IncomeType patchEntity = incomeTypeMapper.toEntity(patchDto);

        incomeTypeMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberTypeIdExpectedValue = patchEntity.getChamberTypeId() != null ? patchEntity.getChamberTypeId() : originalEntity.getChamberTypeId();
        assertThat(actualEntity.getChamberTypeId())
                .isEqualTo(chamberTypeIdExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyIncomeTypeDtoListForNullOrEmptyIncomeTypeList() {
        assertThat(incomeTypeMapper.toDTOList(null)).isEmpty();
        assertThat(incomeTypeMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyIncomeTypeListForNullOrEmptyIncomeTypeDtoList() {
        assertThat(incomeTypeMapper.toEntityList(null)).isEmpty();
        assertThat(incomeTypeMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        IncomeType entity = createSampleIncomeTypeEntity();
        IncomeType expectedEntity = createSampleIncomeTypeEntity();

        incomeTypeMapper.partialUpdate(entity, null);
        incomeTypeMapper.partialUpdate(null, createPatchIncomeTypeDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated IncomeType fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomeType createSampleIncomeTypeEntity() {
        IncomeType entity = new IncomeType();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberTypeId(10);
        entity.setDescription("descriptionValue1");
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return entity;
    }

    /**
     * Creates a populated IncomeType fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomeType createAnotherIncomeTypeEntity() {
        IncomeType entity = new IncomeType();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberTypeId(20);
        entity.setDescription("descriptionValue2");
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return entity;
    }

    /**
     * Creates a populated IncomeTypeDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeTypeDto createSampleIncomeTypeDto() {
        IncomeTypeDto dto = new IncomeTypeDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberTypeId(10);
        dto.setDescription("descriptionValue1");
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return dto;
    }

    /**
     * Creates a populated IncomeTypeDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeTypeDto createAnotherIncomeTypeDto() {
        IncomeTypeDto dto = new IncomeTypeDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberTypeId(20);
        dto.setDescription("descriptionValue2");
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return dto;
    }

    /**
     * Creates a populated IncomeTypeDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomeTypeDto createPatchIncomeTypeDto() {
        IncomeTypeDto dto = new IncomeTypeDto();
        dto.setChamberId(30);
        dto.setRecdeleted(true);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
