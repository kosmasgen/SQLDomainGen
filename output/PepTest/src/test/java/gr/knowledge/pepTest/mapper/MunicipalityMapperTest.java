package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.entity.Municipality;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class MunicipalityMapperTest {

    private MunicipalityMapper municipalityMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        municipalityMapper = new MunicipalityMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapMunicipalityToMunicipalityDto() {
        Municipality entity = createSampleMunicipalityEntity();
        MunicipalityDto expectedDto = createSampleMunicipalityDto();

        MunicipalityDto actualDto = municipalityMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapMunicipalityDtoToMunicipality() {
        MunicipalityDto dto = createSampleMunicipalityDto();
        Municipality expectedEntity = createSampleMunicipalityEntity();

        Municipality actualEntity = municipalityMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapMunicipalityListToMunicipalityDtoList() {
        List<Municipality> entityList = List.of(
                createSampleMunicipalityEntity(),
                createAnotherMunicipalityEntity()
        );
        List<MunicipalityDto> expectedDtoList = List.of(
                createSampleMunicipalityDto(),
                createAnotherMunicipalityDto()
        );

        List<MunicipalityDto> actualDtoList = municipalityMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapMunicipalityDtoListToMunicipalityList() {
        List<MunicipalityDto> dtoList = List.of(
                createSampleMunicipalityDto(),
                createAnotherMunicipalityDto()
        );
        List<Municipality> expectedEntityList = List.of(
                createSampleMunicipalityEntity(),
                createAnotherMunicipalityEntity()
        );

        List<Municipality> actualEntityList = municipalityMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForMunicipality() {
        Municipality originalEntity = createSampleMunicipalityEntity();
        Municipality actualEntity = createSampleMunicipalityEntity();
        MunicipalityDto patchDto = createPatchMunicipalityDto();
        Municipality patchEntity = municipalityMapper.toEntity(patchDto);

        municipalityMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberMunicipalityIdExpectedValue = patchEntity.getChamberMunicipalityId() != null ? patchEntity.getChamberMunicipalityId() : originalEntity.getChamberMunicipalityId();
        assertThat(actualEntity.getChamberMunicipalityId())
                .isEqualTo(chamberMunicipalityIdExpectedValue);

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

        Object cdExpectedValue = patchEntity.getCd() != null ? patchEntity.getCd() : originalEntity.getCd();
        assertThat(actualEntity.getCd())
                .isEqualTo(cdExpectedValue);

        Object isProteasDataExpectedValue = patchEntity.getIsProteasData() != null ? patchEntity.getIsProteasData() : originalEntity.getIsProteasData();
        assertThat(actualEntity.getIsProteasData())
                .isEqualTo(isProteasDataExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyMunicipalityDtoListForNullOrEmptyMunicipalityList() {
        assertThat(municipalityMapper.toDTOList(null)).isEmpty();
        assertThat(municipalityMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyMunicipalityListForNullOrEmptyMunicipalityDtoList() {
        assertThat(municipalityMapper.toEntityList(null)).isEmpty();
        assertThat(municipalityMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Municipality entity = createSampleMunicipalityEntity();
        Municipality expectedEntity = createSampleMunicipalityEntity();

        municipalityMapper.partialUpdate(entity, null);
        municipalityMapper.partialUpdate(null, createPatchMunicipalityDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Municipality fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Municipality createSampleMunicipalityEntity() {
        Municipality entity = new Municipality();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(100L);
        entity.setChamberMunicipalityId(100L);
        entity.setDescription("descriptionValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setCd("cdValue1");
        entity.setIsProteasData(true);

        return entity;
    }

    /**
     * Creates a populated Municipality fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Municipality createAnotherMunicipalityEntity() {
        Municipality entity = new Municipality();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(200L);
        entity.setChamberMunicipalityId(200L);
        entity.setDescription("descriptionValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setCd("cdValue2");
        entity.setIsProteasData(false);

        return entity;
    }

    /**
     * Creates a populated MunicipalityDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityDto createSampleMunicipalityDto() {
        MunicipalityDto dto = new MunicipalityDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(100L);
        dto.setChamberMunicipalityId(100L);
        dto.setDescription("descriptionValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setCd("cdValue1");
        dto.setIsProteasData(true);

        return dto;
    }

    /**
     * Creates a populated MunicipalityDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityDto createAnotherMunicipalityDto() {
        MunicipalityDto dto = new MunicipalityDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(200L);
        dto.setChamberMunicipalityId(200L);
        dto.setDescription("descriptionValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setCd("cdValue2");
        dto.setIsProteasData(false);

        return dto;
    }

    /**
     * Creates a populated MunicipalityDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityDto createPatchMunicipalityDto() {
        MunicipalityDto dto = new MunicipalityDto();
        dto.setChamberId(300L);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setCd("cdValue3");
        dto.setIsProteasData(true);

        return dto;
    }

}
