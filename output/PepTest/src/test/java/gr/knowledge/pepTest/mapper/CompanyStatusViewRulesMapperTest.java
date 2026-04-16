package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CompanyStatusViewRules;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesKey;
import gr.knowledge.pepTest.entity.CompanyViewRules;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyStatusViewRulesMapperTest {

    private CompanyStatusViewRulesMapper companyStatusViewRulesMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyStatusViewRulesMapper = new CompanyStatusViewRulesMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyStatusViewRulesToCompanyStatusViewRulesDto() {
        CompanyStatusViewRules entity = createSampleCompanyStatusViewRulesEntity();
        CompanyStatusViewRulesDto expectedDto = createSampleCompanyStatusViewRulesDto();

        CompanyStatusViewRulesDto actualDto = companyStatusViewRulesMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyStatusViewRulesDtoToCompanyStatusViewRules() {
        CompanyStatusViewRulesDto dto = createSampleCompanyStatusViewRulesDto();
        CompanyStatusViewRules expectedEntity = createSampleCompanyStatusViewRulesEntity();

        CompanyStatusViewRules actualEntity = companyStatusViewRulesMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyStatusViewRulesListToCompanyStatusViewRulesDtoList() {
        List<CompanyStatusViewRules> entityList = List.of(
                createSampleCompanyStatusViewRulesEntity(),
                createAnotherCompanyStatusViewRulesEntity()
        );
        List<CompanyStatusViewRulesDto> expectedDtoList = List.of(
                createSampleCompanyStatusViewRulesDto(),
                createAnotherCompanyStatusViewRulesDto()
        );

        List<CompanyStatusViewRulesDto> actualDtoList = companyStatusViewRulesMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyStatusViewRulesDtoListToCompanyStatusViewRulesList() {
        List<CompanyStatusViewRulesDto> dtoList = List.of(
                createSampleCompanyStatusViewRulesDto(),
                createAnotherCompanyStatusViewRulesDto()
        );
        List<CompanyStatusViewRules> expectedEntityList = List.of(
                createSampleCompanyStatusViewRulesEntity(),
                createAnotherCompanyStatusViewRulesEntity()
        );

        List<CompanyStatusViewRules> actualEntityList = companyStatusViewRulesMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyStatusViewRules() {
        CompanyStatusViewRules originalEntity = createSampleCompanyStatusViewRulesEntity();
        CompanyStatusViewRules actualEntity = createSampleCompanyStatusViewRulesEntity();
        CompanyStatusViewRulesDto patchDto = createPatchCompanyStatusViewRulesDto();
        CompanyStatusViewRules patchEntity = companyStatusViewRulesMapper.toEntity(patchDto);

        companyStatusViewRulesMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object companyStatusExpectedValue = patchEntity.getCompanyStatus() != null ? patchEntity.getCompanyStatus() : originalEntity.getCompanyStatus();
        assertThat(actualEntity.getCompanyStatus())
                .usingRecursiveComparison()
                .isEqualTo(companyStatusExpectedValue);

        Object companyViewRulesExpectedValue = patchEntity.getCompanyViewRules() != null ? patchEntity.getCompanyViewRules() : originalEntity.getCompanyViewRules();
        assertThat(actualEntity.getCompanyViewRules())
                .usingRecursiveComparison()
                .isEqualTo(companyViewRulesExpectedValue);

        Object excludeCompaniesExpectedValue = patchEntity.getExcludeCompanies() != null ? patchEntity.getExcludeCompanies() : originalEntity.getExcludeCompanies();
        assertThat(actualEntity.getExcludeCompanies())
                .isEqualTo(excludeCompaniesExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyStatusViewRulesDtoListForNullOrEmptyCompanyStatusViewRulesList() {
        assertThat(companyStatusViewRulesMapper.toDTOList(null)).isEmpty();
        assertThat(companyStatusViewRulesMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyStatusViewRulesListForNullOrEmptyCompanyStatusViewRulesDtoList() {
        assertThat(companyStatusViewRulesMapper.toEntityList(null)).isEmpty();
        assertThat(companyStatusViewRulesMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyStatusViewRules entity = createSampleCompanyStatusViewRulesEntity();
        CompanyStatusViewRules expectedEntity = createSampleCompanyStatusViewRulesEntity();

        companyStatusViewRulesMapper.partialUpdate(entity, null);
        companyStatusViewRulesMapper.partialUpdate(null, createPatchCompanyStatusViewRulesDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyStatusViewRules fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusViewRules createSampleCompanyStatusViewRulesEntity() {
        CompanyStatusViewRules entity = new CompanyStatusViewRules();
        CompanyStatusViewRulesKey idFixture1 = new CompanyStatusViewRulesKey();
        entity.setId(idFixture1);
        CompanyStatus companyStatusFixture1 = new CompanyStatus();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        entity.setCompanyStatus(companyStatusFixture1);
        CompanyViewRules companyViewRulesFixture1 = new CompanyViewRules();
        companyViewRulesFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyViewRulesFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyViewRulesFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyViewRulesFixture1.setChamberId(100L);
        companyViewRulesFixture1.setShowMobilePhone(true);
        companyViewRulesFixture1.setShowPhones(true);
        companyViewRulesFixture1.setShowEmail(true);
        companyViewRulesFixture1.setShowAfm(true);
        companyViewRulesFixture1.setShowBusinessInformation(true);
        entity.setCompanyViewRules(companyViewRulesFixture1);
        entity.setExcludeCompanies(true);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return entity;
    }

    /**
     * Creates a populated CompanyStatusViewRules fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusViewRules createAnotherCompanyStatusViewRulesEntity() {
        CompanyStatusViewRules entity = new CompanyStatusViewRules();
        CompanyStatusViewRulesKey idFixture2 = new CompanyStatusViewRulesKey();
        entity.setId(idFixture2);
        CompanyStatus companyStatusFixture2 = new CompanyStatus();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        entity.setCompanyStatus(companyStatusFixture2);
        CompanyViewRules companyViewRulesFixture2 = new CompanyViewRules();
        companyViewRulesFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyViewRulesFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyViewRulesFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyViewRulesFixture2.setChamberId(200L);
        companyViewRulesFixture2.setShowMobilePhone(false);
        companyViewRulesFixture2.setShowPhones(false);
        companyViewRulesFixture2.setShowEmail(false);
        companyViewRulesFixture2.setShowAfm(false);
        companyViewRulesFixture2.setShowBusinessInformation(false);
        entity.setCompanyViewRules(companyViewRulesFixture2);
        entity.setExcludeCompanies(false);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return entity;
    }

    /**
     * Creates a populated CompanyStatusViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusViewRulesDto createSampleCompanyStatusViewRulesDto() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();
        CompanyStatusViewRulesKey idFixture1 = new CompanyStatusViewRulesKey();
        dto.setId(idFixture1);
        CompanyStatusDto companyStatusFixture1 = new CompanyStatusDto();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        dto.setCompanyStatus(companyStatusFixture1);
        CompanyViewRulesDto companyViewRulesFixture1 = new CompanyViewRulesDto();
        companyViewRulesFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyViewRulesFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyViewRulesFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyViewRulesFixture1.setChamberId(100L);
        companyViewRulesFixture1.setShowMobilePhone(true);
        companyViewRulesFixture1.setShowPhones(true);
        companyViewRulesFixture1.setShowEmail(true);
        companyViewRulesFixture1.setShowAfm(true);
        companyViewRulesFixture1.setShowBusinessInformation(true);
        dto.setCompanyViewRules(companyViewRulesFixture1);
        dto.setExcludeCompanies(true);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return dto;
    }

    /**
     * Creates a populated CompanyStatusViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusViewRulesDto createAnotherCompanyStatusViewRulesDto() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();
        CompanyStatusViewRulesKey idFixture2 = new CompanyStatusViewRulesKey();
        dto.setId(idFixture2);
        CompanyStatusDto companyStatusFixture2 = new CompanyStatusDto();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        dto.setCompanyStatus(companyStatusFixture2);
        CompanyViewRulesDto companyViewRulesFixture2 = new CompanyViewRulesDto();
        companyViewRulesFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyViewRulesFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyViewRulesFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyViewRulesFixture2.setChamberId(200L);
        companyViewRulesFixture2.setShowMobilePhone(false);
        companyViewRulesFixture2.setShowPhones(false);
        companyViewRulesFixture2.setShowEmail(false);
        companyViewRulesFixture2.setShowAfm(false);
        companyViewRulesFixture2.setShowBusinessInformation(false);
        dto.setCompanyViewRules(companyViewRulesFixture2);
        dto.setExcludeCompanies(false);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return dto;
    }

    /**
     * Creates a populated CompanyStatusViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusViewRulesDto createPatchCompanyStatusViewRulesDto() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();
        dto.setExcludeCompanies(true);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
