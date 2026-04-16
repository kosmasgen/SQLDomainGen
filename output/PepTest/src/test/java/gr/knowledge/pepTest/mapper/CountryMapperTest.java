package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.entity.Country;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CountryMapperTest {

    private CountryMapper countryMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        countryMapper = new CountryMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCountryToCountryDto() {
        Country entity = createSampleCountryEntity();
        CountryDto expectedDto = createSampleCountryDto();

        CountryDto actualDto = countryMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCountryDtoToCountry() {
        CountryDto dto = createSampleCountryDto();
        Country expectedEntity = createSampleCountryEntity();

        Country actualEntity = countryMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCountryListToCountryDtoList() {
        List<Country> entityList = List.of(
                createSampleCountryEntity(),
                createAnotherCountryEntity()
        );
        List<CountryDto> expectedDtoList = List.of(
                createSampleCountryDto(),
                createAnotherCountryDto()
        );

        List<CountryDto> actualDtoList = countryMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCountryDtoListToCountryList() {
        List<CountryDto> dtoList = List.of(
                createSampleCountryDto(),
                createAnotherCountryDto()
        );
        List<Country> expectedEntityList = List.of(
                createSampleCountryEntity(),
                createAnotherCountryEntity()
        );

        List<Country> actualEntityList = countryMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCountry() {
        Country originalEntity = createSampleCountryEntity();
        Country actualEntity = createSampleCountryEntity();
        CountryDto patchDto = createPatchCountryDto();
        Country patchEntity = countryMapper.toEntity(patchDto);

        countryMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object regionIdExpectedValue = patchEntity.getRegionId() != null ? patchEntity.getRegionId() : originalEntity.getRegionId();
        assertThat(actualEntity.getRegionId())
                .isEqualTo(regionIdExpectedValue);

        Object chamberCountryIdExpectedValue = patchEntity.getChamberCountryId() != null ? patchEntity.getChamberCountryId() : originalEntity.getChamberCountryId();
        assertThat(actualEntity.getChamberCountryId())
                .isEqualTo(chamberCountryIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCountryDtoListForNullOrEmptyCountryList() {
        assertThat(countryMapper.toDTOList(null)).isEmpty();
        assertThat(countryMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCountryListForNullOrEmptyCountryDtoList() {
        assertThat(countryMapper.toEntityList(null)).isEmpty();
        assertThat(countryMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Country entity = createSampleCountryEntity();
        Country expectedEntity = createSampleCountryEntity();

        countryMapper.partialUpdate(entity, null);
        countryMapper.partialUpdate(null, createPatchCountryDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Country fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Country createSampleCountryEntity() {
        Country entity = new Country();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);
        entity.setChamberId(10);
        entity.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberCountryId(10);

        return entity;
    }

    /**
     * Creates a populated Country fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Country createAnotherCountryEntity() {
        Country entity = new Country();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);
        entity.setChamberId(20);
        entity.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberCountryId(20);

        return entity;
    }

    /**
     * Creates a populated CountryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CountryDto createSampleCountryDto() {
        CountryDto dto = new CountryDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);
        dto.setChamberId(10);
        dto.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberCountryId(10);

        return dto;
    }

    /**
     * Creates a populated CountryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CountryDto createAnotherCountryDto() {
        CountryDto dto = new CountryDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);
        dto.setChamberId(20);
        dto.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberCountryId(20);

        return dto;
    }

    /**
     * Creates a populated CountryDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CountryDto createPatchCountryDto() {
        CountryDto dto = new CountryDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);
        dto.setChamberId(30);
        dto.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000003"));

        return dto;
    }

}
