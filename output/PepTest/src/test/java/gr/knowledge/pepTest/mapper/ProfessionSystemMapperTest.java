package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.entity.ProfessionSystem;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ProfessionSystemMapperTest {

    private ProfessionSystemMapper professionSystemMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        professionSystemMapper = new ProfessionSystemMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionSystemToProfessionSystemDto() {
        ProfessionSystem entity = createSampleProfessionSystemEntity();
        ProfessionSystemDto expectedDto = createSampleProfessionSystemDto();

        ProfessionSystemDto actualDto = professionSystemMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapProfessionSystemDtoToProfessionSystem() {
        ProfessionSystemDto dto = createSampleProfessionSystemDto();
        ProfessionSystem expectedEntity = createSampleProfessionSystemEntity();

        ProfessionSystem actualEntity = professionSystemMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapProfessionSystemListToProfessionSystemDtoList() {
        List<ProfessionSystem> entityList = List.of(
                createSampleProfessionSystemEntity(),
                createAnotherProfessionSystemEntity()
        );
        List<ProfessionSystemDto> expectedDtoList = List.of(
                createSampleProfessionSystemDto(),
                createAnotherProfessionSystemDto()
        );

        List<ProfessionSystemDto> actualDtoList = professionSystemMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapProfessionSystemDtoListToProfessionSystemList() {
        List<ProfessionSystemDto> dtoList = List.of(
                createSampleProfessionSystemDto(),
                createAnotherProfessionSystemDto()
        );
        List<ProfessionSystem> expectedEntityList = List.of(
                createSampleProfessionSystemEntity(),
                createAnotherProfessionSystemEntity()
        );

        List<ProfessionSystem> actualEntityList = professionSystemMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForProfessionSystem() {
        ProfessionSystem originalEntity = createSampleProfessionSystemEntity();
        ProfessionSystem actualEntity = createSampleProfessionSystemEntity();
        ProfessionSystemDto patchDto = createPatchProfessionSystemDto();
        ProfessionSystem patchEntity = professionSystemMapper.toEntity(patchDto);

        professionSystemMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberProfSystemIdExpectedValue = patchEntity.getChamberProfSystemId() != null ? patchEntity.getChamberProfSystemId() : originalEntity.getChamberProfSystemId();
        assertThat(actualEntity.getChamberProfSystemId())
                .isEqualTo(chamberProfSystemIdExpectedValue);

        Object cdExpectedValue = patchEntity.getCd() != null ? patchEntity.getCd() : originalEntity.getCd();
        assertThat(actualEntity.getCd())
                .isEqualTo(cdExpectedValue);

        Object descriptionExpectedValue = patchEntity.getDescription() != null ? patchEntity.getDescription() : originalEntity.getDescription();
        assertThat(actualEntity.getDescription())
                .isEqualTo(descriptionExpectedValue);

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
    void shouldReturnEmptyProfessionSystemDtoListForNullOrEmptyProfessionSystemList() {
        assertThat(professionSystemMapper.toDTOList(null)).isEmpty();
        assertThat(professionSystemMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyProfessionSystemListForNullOrEmptyProfessionSystemDtoList() {
        assertThat(professionSystemMapper.toEntityList(null)).isEmpty();
        assertThat(professionSystemMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ProfessionSystem entity = createSampleProfessionSystemEntity();
        ProfessionSystem expectedEntity = createSampleProfessionSystemEntity();

        professionSystemMapper.partialUpdate(entity, null);
        professionSystemMapper.partialUpdate(null, createPatchProfessionSystemDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ProfessionSystem fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionSystem createSampleProfessionSystemEntity() {
        ProfessionSystem entity = new ProfessionSystem();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberProfSystemId(10);
        entity.setCd("cdValue1");
        entity.setDescription("descriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated ProfessionSystem fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ProfessionSystem createAnotherProfessionSystemEntity() {
        ProfessionSystem entity = new ProfessionSystem();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberProfSystemId(20);
        entity.setCd("cdValue2");
        entity.setDescription("descriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ProfessionSystemDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionSystemDto createSampleProfessionSystemDto() {
        ProfessionSystemDto dto = new ProfessionSystemDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberProfSystemId(10);
        dto.setCd("cdValue1");
        dto.setDescription("descriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ProfessionSystemDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionSystemDto createAnotherProfessionSystemDto() {
        ProfessionSystemDto dto = new ProfessionSystemDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberProfSystemId(20);
        dto.setCd("cdValue2");
        dto.setDescription("descriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ProfessionSystemDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ProfessionSystemDto createPatchProfessionSystemDto() {
        ProfessionSystemDto dto = new ProfessionSystemDto();
        dto.setChamberId(30);
        dto.setCd("cdValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
