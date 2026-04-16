package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.entity.CompanyViewRules;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyViewRulesMapperTest {

    private CompanyViewRulesMapper companyViewRulesMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyViewRulesMapper = new CompanyViewRulesMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyViewRulesToCompanyViewRulesDto() {
        CompanyViewRules entity = createSampleCompanyViewRulesEntity();
        CompanyViewRulesDto expectedDto = createSampleCompanyViewRulesDto();

        CompanyViewRulesDto actualDto = companyViewRulesMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyViewRulesDtoToCompanyViewRules() {
        CompanyViewRulesDto dto = createSampleCompanyViewRulesDto();
        CompanyViewRules expectedEntity = createSampleCompanyViewRulesEntity();

        CompanyViewRules actualEntity = companyViewRulesMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyViewRulesListToCompanyViewRulesDtoList() {
        List<CompanyViewRules> entityList = List.of(
                createSampleCompanyViewRulesEntity(),
                createAnotherCompanyViewRulesEntity()
        );
        List<CompanyViewRulesDto> expectedDtoList = List.of(
                createSampleCompanyViewRulesDto(),
                createAnotherCompanyViewRulesDto()
        );

        List<CompanyViewRulesDto> actualDtoList = companyViewRulesMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyViewRulesDtoListToCompanyViewRulesList() {
        List<CompanyViewRulesDto> dtoList = List.of(
                createSampleCompanyViewRulesDto(),
                createAnotherCompanyViewRulesDto()
        );
        List<CompanyViewRules> expectedEntityList = List.of(
                createSampleCompanyViewRulesEntity(),
                createAnotherCompanyViewRulesEntity()
        );

        List<CompanyViewRules> actualEntityList = companyViewRulesMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyViewRules() {
        CompanyViewRules originalEntity = createSampleCompanyViewRulesEntity();
        CompanyViewRules actualEntity = createSampleCompanyViewRulesEntity();
        CompanyViewRulesDto patchDto = createPatchCompanyViewRulesDto();
        CompanyViewRules patchEntity = companyViewRulesMapper.toEntity(patchDto);

        companyViewRulesMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object showMobilePhoneExpectedValue = patchEntity.getShowMobilePhone() != null ? patchEntity.getShowMobilePhone() : originalEntity.getShowMobilePhone();
        assertThat(actualEntity.getShowMobilePhone())
                .isEqualTo(showMobilePhoneExpectedValue);

        Object showPhonesExpectedValue = patchEntity.getShowPhones() != null ? patchEntity.getShowPhones() : originalEntity.getShowPhones();
        assertThat(actualEntity.getShowPhones())
                .isEqualTo(showPhonesExpectedValue);

        Object showEmailExpectedValue = patchEntity.getShowEmail() != null ? patchEntity.getShowEmail() : originalEntity.getShowEmail();
        assertThat(actualEntity.getShowEmail())
                .isEqualTo(showEmailExpectedValue);

        Object showAfmExpectedValue = patchEntity.getShowAfm() != null ? patchEntity.getShowAfm() : originalEntity.getShowAfm();
        assertThat(actualEntity.getShowAfm())
                .isEqualTo(showAfmExpectedValue);

        Object showBusinessInformationExpectedValue = patchEntity.getShowBusinessInformation() != null ? patchEntity.getShowBusinessInformation() : originalEntity.getShowBusinessInformation();
        assertThat(actualEntity.getShowBusinessInformation())
                .isEqualTo(showBusinessInformationExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyViewRulesDtoListForNullOrEmptyCompanyViewRulesList() {
        assertThat(companyViewRulesMapper.toDTOList(null)).isEmpty();
        assertThat(companyViewRulesMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyViewRulesListForNullOrEmptyCompanyViewRulesDtoList() {
        assertThat(companyViewRulesMapper.toEntityList(null)).isEmpty();
        assertThat(companyViewRulesMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyViewRules entity = createSampleCompanyViewRulesEntity();
        CompanyViewRules expectedEntity = createSampleCompanyViewRulesEntity();

        companyViewRulesMapper.partialUpdate(entity, null);
        companyViewRulesMapper.partialUpdate(null, createPatchCompanyViewRulesDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyViewRules fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyViewRules createSampleCompanyViewRulesEntity() {
        CompanyViewRules entity = new CompanyViewRules();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setChamberId(100L);
        entity.setShowMobilePhone(true);
        entity.setShowPhones(true);
        entity.setShowEmail(true);
        entity.setShowAfm(true);
        entity.setShowBusinessInformation(true);

        return entity;
    }

    /**
     * Creates a populated CompanyViewRules fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyViewRules createAnotherCompanyViewRulesEntity() {
        CompanyViewRules entity = new CompanyViewRules();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setChamberId(200L);
        entity.setShowMobilePhone(false);
        entity.setShowPhones(false);
        entity.setShowEmail(false);
        entity.setShowAfm(false);
        entity.setShowBusinessInformation(false);

        return entity;
    }

    /**
     * Creates a populated CompanyViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyViewRulesDto createSampleCompanyViewRulesDto() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setChamberId(100L);
        dto.setShowMobilePhone(true);
        dto.setShowPhones(true);
        dto.setShowEmail(true);
        dto.setShowAfm(true);
        dto.setShowBusinessInformation(true);

        return dto;
    }

    /**
     * Creates a populated CompanyViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyViewRulesDto createAnotherCompanyViewRulesDto() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setChamberId(200L);
        dto.setShowMobilePhone(false);
        dto.setShowPhones(false);
        dto.setShowEmail(false);
        dto.setShowAfm(false);
        dto.setShowBusinessInformation(false);

        return dto;
    }

    /**
     * Creates a populated CompanyViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyViewRulesDto createPatchCompanyViewRulesDto() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setChamberId(300L);
        dto.setShowMobilePhone(true);
        dto.setShowAfm(true);
        dto.setShowBusinessInformation(true);

        return dto;
    }

}
