package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.entity.CompanyViewRules;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.CorporateStatusViewRules;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CorporateStatusViewRulesMapperTest {

    private CorporateStatusViewRulesMapper corporateStatusViewRulesMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        corporateStatusViewRulesMapper = new CorporateStatusViewRulesMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCorporateStatusViewRulesToCorporateStatusViewRulesDto() {
        CorporateStatusViewRules entity = createSampleCorporateStatusViewRulesEntity();
        CorporateStatusViewRulesDto expectedDto = createSampleCorporateStatusViewRulesDto();

        CorporateStatusViewRulesDto actualDto = corporateStatusViewRulesMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCorporateStatusViewRulesDtoToCorporateStatusViewRules() {
        CorporateStatusViewRulesDto dto = createSampleCorporateStatusViewRulesDto();
        CorporateStatusViewRules expectedEntity = createSampleCorporateStatusViewRulesEntity();

        CorporateStatusViewRules actualEntity = corporateStatusViewRulesMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCorporateStatusViewRulesListToCorporateStatusViewRulesDtoList() {
        List<CorporateStatusViewRules> entityList = List.of(
                createSampleCorporateStatusViewRulesEntity(),
                createAnotherCorporateStatusViewRulesEntity()
        );
        List<CorporateStatusViewRulesDto> expectedDtoList = List.of(
                createSampleCorporateStatusViewRulesDto(),
                createAnotherCorporateStatusViewRulesDto()
        );

        List<CorporateStatusViewRulesDto> actualDtoList = corporateStatusViewRulesMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCorporateStatusViewRulesDtoListToCorporateStatusViewRulesList() {
        List<CorporateStatusViewRulesDto> dtoList = List.of(
                createSampleCorporateStatusViewRulesDto(),
                createAnotherCorporateStatusViewRulesDto()
        );
        List<CorporateStatusViewRules> expectedEntityList = List.of(
                createSampleCorporateStatusViewRulesEntity(),
                createAnotherCorporateStatusViewRulesEntity()
        );

        List<CorporateStatusViewRules> actualEntityList = corporateStatusViewRulesMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCorporateStatusViewRules() {
        CorporateStatusViewRules originalEntity = createSampleCorporateStatusViewRulesEntity();
        CorporateStatusViewRules actualEntity = createSampleCorporateStatusViewRulesEntity();
        CorporateStatusViewRulesDto patchDto = createPatchCorporateStatusViewRulesDto();
        CorporateStatusViewRules patchEntity = corporateStatusViewRulesMapper.toEntity(patchDto);

        corporateStatusViewRulesMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object corporateStatusExpectedValue = patchEntity.getCorporateStatus() != null ? patchEntity.getCorporateStatus() : originalEntity.getCorporateStatus();
        assertThat(actualEntity.getCorporateStatus())
                .usingRecursiveComparison()
                .isEqualTo(corporateStatusExpectedValue);

        Object companyViewRulesExpectedValue = patchEntity.getCompanyViewRules() != null ? patchEntity.getCompanyViewRules() : originalEntity.getCompanyViewRules();
        assertThat(actualEntity.getCompanyViewRules())
                .usingRecursiveComparison()
                .isEqualTo(companyViewRulesExpectedValue);

        Object excludeCompaniesExpectedValue = patchEntity.getExcludeCompanies() != null ? patchEntity.getExcludeCompanies() : originalEntity.getExcludeCompanies();
        assertThat(actualEntity.getExcludeCompanies())
                .isEqualTo(excludeCompaniesExpectedValue);

        Object showContactInfoExpectedValue = patchEntity.getShowContactInfo() != null ? patchEntity.getShowContactInfo() : originalEntity.getShowContactInfo();
        assertThat(actualEntity.getShowContactInfo())
                .isEqualTo(showContactInfoExpectedValue);

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
    void shouldReturnEmptyCorporateStatusViewRulesDtoListForNullOrEmptyCorporateStatusViewRulesList() {
        assertThat(corporateStatusViewRulesMapper.toDTOList(null)).isEmpty();
        assertThat(corporateStatusViewRulesMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCorporateStatusViewRulesListForNullOrEmptyCorporateStatusViewRulesDtoList() {
        assertThat(corporateStatusViewRulesMapper.toEntityList(null)).isEmpty();
        assertThat(corporateStatusViewRulesMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CorporateStatusViewRules entity = createSampleCorporateStatusViewRulesEntity();
        CorporateStatusViewRules expectedEntity = createSampleCorporateStatusViewRulesEntity();

        corporateStatusViewRulesMapper.partialUpdate(entity, null);
        corporateStatusViewRulesMapper.partialUpdate(null, createPatchCorporateStatusViewRulesDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CorporateStatusViewRules fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusViewRules createSampleCorporateStatusViewRulesEntity() {
        CorporateStatusViewRules entity = new CorporateStatusViewRules();
        CorporateStatusViewRulesKey idFixture1 = new CorporateStatusViewRulesKey();
        entity.setId(idFixture1);
        CorporateStatus corporateStatusFixture1 = new CorporateStatus();
        corporateStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        corporateStatusFixture1.setChamberCorporateStatusId(10);
        corporateStatusFixture1.setChamberId(10);
        corporateStatusFixture1.setCd("cdValue1");
        corporateStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setRecdeleted(true);
        entity.setCorporateStatus(corporateStatusFixture1);
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
        entity.setShowContactInfo(true);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return entity;
    }

    /**
     * Creates a populated CorporateStatusViewRules fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusViewRules createAnotherCorporateStatusViewRulesEntity() {
        CorporateStatusViewRules entity = new CorporateStatusViewRules();
        CorporateStatusViewRulesKey idFixture2 = new CorporateStatusViewRulesKey();
        entity.setId(idFixture2);
        CorporateStatus corporateStatusFixture2 = new CorporateStatus();
        corporateStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        corporateStatusFixture2.setChamberCorporateStatusId(20);
        corporateStatusFixture2.setChamberId(20);
        corporateStatusFixture2.setCd("cdValue2");
        corporateStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setRecdeleted(false);
        entity.setCorporateStatus(corporateStatusFixture2);
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
        entity.setShowContactInfo(false);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return entity;
    }

    /**
     * Creates a populated CorporateStatusViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusViewRulesDto createSampleCorporateStatusViewRulesDto() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();
        CorporateStatusViewRulesKey idFixture1 = new CorporateStatusViewRulesKey();
        dto.setId(idFixture1);
        CorporateStatusDto corporateStatusFixture1 = new CorporateStatusDto();
        corporateStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        corporateStatusFixture1.setChamberCorporateStatusId(10);
        corporateStatusFixture1.setChamberId(10);
        corporateStatusFixture1.setCd("cdValue1");
        corporateStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setRecdeleted(true);
        dto.setCorporateStatus(corporateStatusFixture1);
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
        dto.setShowContactInfo(true);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return dto;
    }

    /**
     * Creates a populated CorporateStatusViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusViewRulesDto createAnotherCorporateStatusViewRulesDto() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();
        CorporateStatusViewRulesKey idFixture2 = new CorporateStatusViewRulesKey();
        dto.setId(idFixture2);
        CorporateStatusDto corporateStatusFixture2 = new CorporateStatusDto();
        corporateStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        corporateStatusFixture2.setChamberCorporateStatusId(20);
        corporateStatusFixture2.setChamberId(20);
        corporateStatusFixture2.setCd("cdValue2");
        corporateStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setRecdeleted(false);
        dto.setCorporateStatus(corporateStatusFixture2);
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
        dto.setShowContactInfo(false);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return dto;
    }

    /**
     * Creates a populated CorporateStatusViewRulesDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusViewRulesDto createPatchCorporateStatusViewRulesDto() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();
        CorporateStatusDto corporateStatusFixture3 = new CorporateStatusDto();
        corporateStatusFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        corporateStatusFixture3.setChamberCorporateStatusId(30);
        corporateStatusFixture3.setChamberId(30);
        corporateStatusFixture3.setCd("cdValue3");
        corporateStatusFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        corporateStatusFixture3.setRecdeleted(true);
        dto.setCorporateStatus(corporateStatusFixture3);
        dto.setExcludeCompanies(true);
        dto.setShowContactInfo(true);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
