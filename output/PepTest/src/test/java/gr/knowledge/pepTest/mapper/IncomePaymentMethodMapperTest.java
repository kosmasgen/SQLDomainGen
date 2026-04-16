package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.entity.IncomePaymentMethod;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class IncomePaymentMethodMapperTest {

    private IncomePaymentMethodMapper incomePaymentMethodMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        incomePaymentMethodMapper = new IncomePaymentMethodMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapIncomePaymentMethodToIncomePaymentMethodDto() {
        IncomePaymentMethod entity = createSampleIncomePaymentMethodEntity();
        IncomePaymentMethodDto expectedDto = createSampleIncomePaymentMethodDto();

        IncomePaymentMethodDto actualDto = incomePaymentMethodMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapIncomePaymentMethodDtoToIncomePaymentMethod() {
        IncomePaymentMethodDto dto = createSampleIncomePaymentMethodDto();
        IncomePaymentMethod expectedEntity = createSampleIncomePaymentMethodEntity();

        IncomePaymentMethod actualEntity = incomePaymentMethodMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapIncomePaymentMethodListToIncomePaymentMethodDtoList() {
        List<IncomePaymentMethod> entityList = List.of(
                createSampleIncomePaymentMethodEntity(),
                createAnotherIncomePaymentMethodEntity()
        );
        List<IncomePaymentMethodDto> expectedDtoList = List.of(
                createSampleIncomePaymentMethodDto(),
                createAnotherIncomePaymentMethodDto()
        );

        List<IncomePaymentMethodDto> actualDtoList = incomePaymentMethodMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapIncomePaymentMethodDtoListToIncomePaymentMethodList() {
        List<IncomePaymentMethodDto> dtoList = List.of(
                createSampleIncomePaymentMethodDto(),
                createAnotherIncomePaymentMethodDto()
        );
        List<IncomePaymentMethod> expectedEntityList = List.of(
                createSampleIncomePaymentMethodEntity(),
                createAnotherIncomePaymentMethodEntity()
        );

        List<IncomePaymentMethod> actualEntityList = incomePaymentMethodMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForIncomePaymentMethod() {
        IncomePaymentMethod originalEntity = createSampleIncomePaymentMethodEntity();
        IncomePaymentMethod actualEntity = createSampleIncomePaymentMethodEntity();
        IncomePaymentMethodDto patchDto = createPatchIncomePaymentMethodDto();
        IncomePaymentMethod patchEntity = incomePaymentMethodMapper.toEntity(patchDto);

        incomePaymentMethodMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberPayMethodIdExpectedValue = patchEntity.getChamberPayMethodId() != null ? patchEntity.getChamberPayMethodId() : originalEntity.getChamberPayMethodId();
        assertThat(actualEntity.getChamberPayMethodId())
                .isEqualTo(chamberPayMethodIdExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

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
    void shouldReturnEmptyIncomePaymentMethodDtoListForNullOrEmptyIncomePaymentMethodList() {
        assertThat(incomePaymentMethodMapper.toDTOList(null)).isEmpty();
        assertThat(incomePaymentMethodMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyIncomePaymentMethodListForNullOrEmptyIncomePaymentMethodDtoList() {
        assertThat(incomePaymentMethodMapper.toEntityList(null)).isEmpty();
        assertThat(incomePaymentMethodMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        IncomePaymentMethod entity = createSampleIncomePaymentMethodEntity();
        IncomePaymentMethod expectedEntity = createSampleIncomePaymentMethodEntity();

        incomePaymentMethodMapper.partialUpdate(entity, null);
        incomePaymentMethodMapper.partialUpdate(null, createPatchIncomePaymentMethodDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated IncomePaymentMethod fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomePaymentMethod createSampleIncomePaymentMethodEntity() {
        IncomePaymentMethod entity = new IncomePaymentMethod();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberPayMethodId(10);
        entity.setDescription("descriptionValue1");
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(10);

        return entity;
    }

    /**
     * Creates a populated IncomePaymentMethod fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private IncomePaymentMethod createAnotherIncomePaymentMethodEntity() {
        IncomePaymentMethod entity = new IncomePaymentMethod();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberPayMethodId(20);
        entity.setDescription("descriptionValue2");
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(20);

        return entity;
    }

    /**
     * Creates a populated IncomePaymentMethodDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomePaymentMethodDto createSampleIncomePaymentMethodDto() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberPayMethodId(10);
        dto.setDescription("descriptionValue1");
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(10);

        return dto;
    }

    /**
     * Creates a populated IncomePaymentMethodDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomePaymentMethodDto createAnotherIncomePaymentMethodDto() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberPayMethodId(20);
        dto.setDescription("descriptionValue2");
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(20);

        return dto;
    }

    /**
     * Creates a populated IncomePaymentMethodDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private IncomePaymentMethodDto createPatchIncomePaymentMethodDto() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();
        dto.setChamberId(30);
        dto.setChamberPayMethodId(30);
        dto.setRecdeleted(30);

        return dto;
    }

}
