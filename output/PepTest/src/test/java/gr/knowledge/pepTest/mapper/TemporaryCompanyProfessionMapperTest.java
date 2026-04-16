package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import gr.knowledge.pepTest.entity.TemporaryCompanyProfession;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class TemporaryCompanyProfessionMapperTest {

    private TemporaryCompanyProfessionMapper temporaryCompanyProfessionMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        temporaryCompanyProfessionMapper = new TemporaryCompanyProfessionMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyProfessionToTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfession entity = createSampleTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfessionDto expectedDto = createSampleTemporaryCompanyProfessionDto();

        TemporaryCompanyProfessionDto actualDto = temporaryCompanyProfessionMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapTemporaryCompanyProfessionDtoToTemporaryCompanyProfession() {
        TemporaryCompanyProfessionDto dto = createSampleTemporaryCompanyProfessionDto();
        TemporaryCompanyProfession expectedEntity = createSampleTemporaryCompanyProfessionEntity();

        TemporaryCompanyProfession actualEntity = temporaryCompanyProfessionMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyProfessionListToTemporaryCompanyProfessionDtoList() {
        List<TemporaryCompanyProfession> entityList = List.of(
                createSampleTemporaryCompanyProfessionEntity(),
                createAnotherTemporaryCompanyProfessionEntity()
        );
        List<TemporaryCompanyProfessionDto> expectedDtoList = List.of(
                createSampleTemporaryCompanyProfessionDto(),
                createAnotherTemporaryCompanyProfessionDto()
        );

        List<TemporaryCompanyProfessionDto> actualDtoList = temporaryCompanyProfessionMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapTemporaryCompanyProfessionDtoListToTemporaryCompanyProfessionList() {
        List<TemporaryCompanyProfessionDto> dtoList = List.of(
                createSampleTemporaryCompanyProfessionDto(),
                createAnotherTemporaryCompanyProfessionDto()
        );
        List<TemporaryCompanyProfession> expectedEntityList = List.of(
                createSampleTemporaryCompanyProfessionEntity(),
                createAnotherTemporaryCompanyProfessionEntity()
        );

        List<TemporaryCompanyProfession> actualEntityList = temporaryCompanyProfessionMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForTemporaryCompanyProfession() {
        TemporaryCompanyProfession originalEntity = createSampleTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfession actualEntity = createSampleTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfessionDto patchDto = createPatchTemporaryCompanyProfessionDto();
        TemporaryCompanyProfession patchEntity = temporaryCompanyProfessionMapper.toEntity(patchDto);

        temporaryCompanyProfessionMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object versionExpectedValue = patchEntity.getVersion() != null ? patchEntity.getVersion() : originalEntity.getVersion();
        assertThat(actualEntity.getVersion())
                .isEqualTo(versionExpectedValue);

        Object companyIdExpectedValue = patchEntity.getCompanyId() != null ? patchEntity.getCompanyId() : originalEntity.getCompanyId();
        assertThat(actualEntity.getCompanyId())
                .isEqualTo(companyIdExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object fromDateExpectedValue = patchEntity.getFromDate() != null ? patchEntity.getFromDate() : originalEntity.getFromDate();
        assertThat(actualEntity.getFromDate())
                .isEqualTo(fromDateExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object professionIdExpectedValue = patchEntity.getProfessionId() != null ? patchEntity.getProfessionId() : originalEntity.getProfessionId();
        assertThat(actualEntity.getProfessionId())
                .isEqualTo(professionIdExpectedValue);

        Object professionKindIdExpectedValue = patchEntity.getProfessionKindId() != null ? patchEntity.getProfessionKindId() : originalEntity.getProfessionKindId();
        assertThat(actualEntity.getProfessionKindId())
                .isEqualTo(professionKindIdExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object toDateExpectedValue = patchEntity.getToDate() != null ? patchEntity.getToDate() : originalEntity.getToDate();
        assertThat(actualEntity.getToDate())
                .isEqualTo(toDateExpectedValue);

        Object gemiIdExpectedValue = patchEntity.getGemiId() != null ? patchEntity.getGemiId() : originalEntity.getGemiId();
        assertThat(actualEntity.getGemiId())
                .isEqualTo(gemiIdExpectedValue);

        Object gemiDateCreatedExpectedValue = patchEntity.getGemiDateCreated() != null ? patchEntity.getGemiDateCreated() : originalEntity.getGemiDateCreated();
        assertThat(actualEntity.getGemiDateCreated())
                .isEqualTo(gemiDateCreatedExpectedValue);

        Object gemiLastUpdatedExpectedValue = patchEntity.getGemiLastUpdated() != null ? patchEntity.getGemiLastUpdated() : originalEntity.getGemiLastUpdated();
        assertThat(actualEntity.getGemiLastUpdated())
                .isEqualTo(gemiLastUpdatedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyProfessionDtoListForNullOrEmptyTemporaryCompanyProfessionList() {
        assertThat(temporaryCompanyProfessionMapper.toDTOList(null)).isEmpty();
        assertThat(temporaryCompanyProfessionMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyTemporaryCompanyProfessionListForNullOrEmptyTemporaryCompanyProfessionDtoList() {
        assertThat(temporaryCompanyProfessionMapper.toEntityList(null)).isEmpty();
        assertThat(temporaryCompanyProfessionMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        TemporaryCompanyProfession entity = createSampleTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfession expectedEntity = createSampleTemporaryCompanyProfessionEntity();

        temporaryCompanyProfessionMapper.partialUpdate(entity, null);
        temporaryCompanyProfessionMapper.partialUpdate(null, createPatchTemporaryCompanyProfessionDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated TemporaryCompanyProfession fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyProfession createSampleTemporaryCompanyProfessionEntity() {
        TemporaryCompanyProfession entity = new TemporaryCompanyProfession();
        entity.setId(new BigInteger("1000"));
        entity.setVersion(new BigInteger("1000"));
        entity.setCompanyId(new BigInteger("1000"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setProfessionId(new BigInteger("1000"));
        entity.setProfessionKindId(new BigInteger("1000"));
        entity.setRecdeleted(new BigInteger("1000"));
        entity.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setGemiId(new BigInteger("1000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        entity.setGemiLastUpdated(LocalDate.of(2024, 1, 11));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyProfession fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyProfession createAnotherTemporaryCompanyProfessionEntity() {
        TemporaryCompanyProfession entity = new TemporaryCompanyProfession();
        entity.setId(new BigInteger("2000"));
        entity.setVersion(new BigInteger("2000"));
        entity.setCompanyId(new BigInteger("2000"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setProfessionId(new BigInteger("2000"));
        entity.setProfessionKindId(new BigInteger("2000"));
        entity.setRecdeleted(new BigInteger("2000"));
        entity.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setGemiId(new BigInteger("2000"));
        entity.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        entity.setGemiLastUpdated(LocalDate.of(2024, 2, 12));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyProfessionDto createSampleTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();
        dto.setId(new BigInteger("1000"));
        dto.setVersion(new BigInteger("1000"));
        dto.setCompanyId(new BigInteger("1000"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setFromDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setProfessionId(new BigInteger("1000"));
        dto.setProfessionKindId(new BigInteger("1000"));
        dto.setRecdeleted(new BigInteger("1000"));
        dto.setToDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setGemiId(new BigInteger("1000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 1, 11));
        dto.setGemiLastUpdated(LocalDate.of(2024, 1, 11));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyProfessionDto createAnotherTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();
        dto.setId(new BigInteger("2000"));
        dto.setVersion(new BigInteger("2000"));
        dto.setCompanyId(new BigInteger("2000"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setFromDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setProfessionId(new BigInteger("2000"));
        dto.setProfessionKindId(new BigInteger("2000"));
        dto.setRecdeleted(new BigInteger("2000"));
        dto.setToDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setGemiId(new BigInteger("2000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 2, 12));
        dto.setGemiLastUpdated(LocalDate.of(2024, 2, 12));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyProfessionDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyProfessionDto createPatchTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();
        dto.setVersion(new BigInteger("3000"));
        dto.setCompanyId(new BigInteger("3000"));
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setFromDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setProfessionId(new BigInteger("3000"));
        dto.setRecdeleted(new BigInteger("3000"));
        dto.setToDate(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setGemiId(new BigInteger("3000"));
        dto.setGemiDateCreated(LocalDate.of(2024, 3, 13));
        dto.setGemiLastUpdated(LocalDate.of(2024, 3, 13));

        return dto;
    }

}
