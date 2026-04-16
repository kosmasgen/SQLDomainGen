package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.entity.CompanyStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyStatusMapperTest {

    private CompanyStatusMapper companyStatusMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyStatusMapper = new CompanyStatusMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyStatusToCompanyStatusDto() {
        CompanyStatus entity = createSampleCompanyStatusEntity();
        CompanyStatusDto expectedDto = createSampleCompanyStatusDto();

        CompanyStatusDto actualDto = companyStatusMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyStatusDtoToCompanyStatus() {
        CompanyStatusDto dto = createSampleCompanyStatusDto();
        CompanyStatus expectedEntity = createSampleCompanyStatusEntity();

        CompanyStatus actualEntity = companyStatusMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyStatusListToCompanyStatusDtoList() {
        List<CompanyStatus> entityList = List.of(
                createSampleCompanyStatusEntity(),
                createAnotherCompanyStatusEntity()
        );
        List<CompanyStatusDto> expectedDtoList = List.of(
                createSampleCompanyStatusDto(),
                createAnotherCompanyStatusDto()
        );

        List<CompanyStatusDto> actualDtoList = companyStatusMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyStatusDtoListToCompanyStatusList() {
        List<CompanyStatusDto> dtoList = List.of(
                createSampleCompanyStatusDto(),
                createAnotherCompanyStatusDto()
        );
        List<CompanyStatus> expectedEntityList = List.of(
                createSampleCompanyStatusEntity(),
                createAnotherCompanyStatusEntity()
        );

        List<CompanyStatus> actualEntityList = companyStatusMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyStatus() {
        CompanyStatus originalEntity = createSampleCompanyStatusEntity();
        CompanyStatus actualEntity = createSampleCompanyStatusEntity();
        CompanyStatusDto patchDto = createPatchCompanyStatusDto();
        CompanyStatus patchEntity = companyStatusMapper.toEntity(patchDto);

        companyStatusMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object chamberCompanyStatusIdExpectedValue = patchEntity.getChamberCompanyStatusId() != null ? patchEntity.getChamberCompanyStatusId() : originalEntity.getChamberCompanyStatusId();
        assertThat(actualEntity.getChamberCompanyStatusId())
                .isEqualTo(chamberCompanyStatusIdExpectedValue);

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
    void shouldReturnEmptyCompanyStatusDtoListForNullOrEmptyCompanyStatusList() {
        assertThat(companyStatusMapper.toDTOList(null)).isEmpty();
        assertThat(companyStatusMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyStatusListForNullOrEmptyCompanyStatusDtoList() {
        assertThat(companyStatusMapper.toEntityList(null)).isEmpty();
        assertThat(companyStatusMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyStatus entity = createSampleCompanyStatusEntity();
        CompanyStatus expectedEntity = createSampleCompanyStatusEntity();

        companyStatusMapper.partialUpdate(entity, null);
        companyStatusMapper.partialUpdate(null, createPatchCompanyStatusDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyStatus fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatus createSampleCompanyStatusEntity() {
        CompanyStatus entity = new CompanyStatus();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberId(10);
        entity.setChamberCompanyStatusId(10);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyStatus fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatus createAnotherCompanyStatusEntity() {
        CompanyStatus entity = new CompanyStatus();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberId(20);
        entity.setChamberCompanyStatusId(20);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyStatusDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusDto createSampleCompanyStatusDto() {
        CompanyStatusDto dto = new CompanyStatusDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberId(10);
        dto.setChamberCompanyStatusId(10);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CompanyStatusDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusDto createAnotherCompanyStatusDto() {
        CompanyStatusDto dto = new CompanyStatusDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberId(20);
        dto.setChamberCompanyStatusId(20);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CompanyStatusDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusDto createPatchCompanyStatusDto() {
        CompanyStatusDto dto = new CompanyStatusDto();
        dto.setChamberId(30);
        dto.setChamberCompanyStatusId(30);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
