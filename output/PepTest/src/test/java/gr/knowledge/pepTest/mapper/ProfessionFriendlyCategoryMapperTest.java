package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ProfessionFriendlyCategoryMapperTest {

    private ProfessionFriendlyCategoryMapper professionFriendlyCategoryMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        professionFriendlyCategoryMapper = new ProfessionFriendlyCategoryMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionFriendlyCategoryToProfessionFriendlyCategoryDto() {
        ProfessionFriendlyCategory entity = createSampleProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategoryDto expectedDto = createSampleProfessionFriendlyCategoryDto();

        ProfessionFriendlyCategoryDto actualDto = professionFriendlyCategoryMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionFriendlyCategoryDtoToProfessionFriendlyCategory() {
        ProfessionFriendlyCategoryDto dto = createSampleProfessionFriendlyCategoryDto();
        ProfessionFriendlyCategory expectedEntity = createSampleProfessionFriendlyCategoryEntity();

        ProfessionFriendlyCategory actualEntity = professionFriendlyCategoryMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProfessionFriendlyCategoryListToProfessionFriendlyCategoryDtoList() {
        List<ProfessionFriendlyCategory> entityList = List.of(
                createSampleProfessionFriendlyCategoryEntity(),
                createAnotherProfessionFriendlyCategoryEntity()
        );
        List<ProfessionFriendlyCategoryDto> expectedDtoList = List.of(
                createSampleProfessionFriendlyCategoryDto(),
                createAnotherProfessionFriendlyCategoryDto()
        );

        List<ProfessionFriendlyCategoryDto> actualDtoList = professionFriendlyCategoryMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProfessionFriendlyCategoryDtoListToProfessionFriendlyCategoryList() {
        List<ProfessionFriendlyCategoryDto> dtoList = List.of(
                createSampleProfessionFriendlyCategoryDto(),
                createAnotherProfessionFriendlyCategoryDto()
        );
        List<ProfessionFriendlyCategory> expectedEntityList = List.of(
                createSampleProfessionFriendlyCategoryEntity(),
                createAnotherProfessionFriendlyCategoryEntity()
        );

        List<ProfessionFriendlyCategory> actualEntityList = professionFriendlyCategoryMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProfessionFriendlyCategory() {
        ProfessionFriendlyCategory originalEntity = createSampleProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategory actualEntity = createSampleProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategoryDto patchDto = createPatchProfessionFriendlyCategoryDto();
        ProfessionFriendlyCategory patchEntity = professionFriendlyCategoryMapper.toEntity(patchDto);

        professionFriendlyCategoryMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object descrExpectedValue = patchEntity.getDescr() != null ? patchEntity.getDescr() : originalEntity.getDescr();
        assertThat(actualEntity.getDescr())
                .isEqualTo(descrExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionFriendlyCategoryDtoListForNullOrEmptyProfessionFriendlyCategoryList() {
        assertThat(professionFriendlyCategoryMapper.toDTOList(null)).isEmpty();
        assertThat(professionFriendlyCategoryMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionFriendlyCategoryListForNullOrEmptyProfessionFriendlyCategoryDtoList() {
        assertThat(professionFriendlyCategoryMapper.toEntityList(null)).isEmpty();
        assertThat(professionFriendlyCategoryMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ProfessionFriendlyCategory entity = createSampleProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategory expectedEntity = createSampleProfessionFriendlyCategoryEntity();

        professionFriendlyCategoryMapper.partialUpdate(entity, null);
        professionFriendlyCategoryMapper.partialUpdate(null, createPatchProfessionFriendlyCategoryDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ProfessionFriendlyCategory fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionFriendlyCategory createSampleProfessionFriendlyCategoryEntity() {
        ProfessionFriendlyCategory entity = new ProfessionFriendlyCategory();
        entity.setId("idValue1");
        entity.setDescr("descrValue1");

        return entity;
    }

    /**
     * Creates a populated ProfessionFriendlyCategory fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionFriendlyCategory createAnotherProfessionFriendlyCategoryEntity() {
        ProfessionFriendlyCategory entity = new ProfessionFriendlyCategory();
        entity.setId("idValue2");
        entity.setDescr("descrValue2");

        return entity;
    }

    /**
     * Creates a populated ProfessionFriendlyCategoryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionFriendlyCategoryDto createSampleProfessionFriendlyCategoryDto() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();
        dto.setId("idValue1");
        dto.setDescr("descrValue1");

        return dto;
    }

    /**
     * Creates a populated ProfessionFriendlyCategoryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionFriendlyCategoryDto createAnotherProfessionFriendlyCategoryDto() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();
        dto.setId("idValue2");
        dto.setDescr("descrValue2");

        return dto;
    }

    /**
     * Creates a populated ProfessionFriendlyCategoryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionFriendlyCategoryDto createPatchProfessionFriendlyCategoryDto() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();
        dto.setDescr("descrValue3");

        return dto;
    }

}
