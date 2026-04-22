package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CorporateStatusViewRules;
import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.repository.CorporateStatusViewRulesRepository;
import gr.knowledge.pepTest.mapper.CorporateStatusViewRulesMapper;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesKey;
import java.util.UUID;
import java.time.LocalDateTime;

import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CorporateStatusViewRulesServiceImplTest {

    @Mock
    private CorporateStatusViewRulesRepository corporateStatusViewRulesRepository;

    @Mock
    private CorporateStatusViewRulesMapper corporateStatusViewRulesMapper;

    @InjectMocks
    private CorporateStatusViewRulesServiceImpl corporateStatusViewRulesService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CorporateStatusViewRules.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CorporateStatusViewRules", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCorporateStatusViewRulesesIsCalled() {
        List<CorporateStatusViewRules> entityList = List.of(createSampleCorporateStatusViewRulesEntity(), createAnotherCorporateStatusViewRulesEntity());
        List<CorporateStatusViewRulesDto> dtoList = List.of(createSampleCorporateStatusViewRulesDto(), createAnotherCorporateStatusViewRulesDto());

        given(corporateStatusViewRulesRepository.findAll()).willReturn(entityList);
        given(corporateStatusViewRulesMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CorporateStatusViewRulesDto> result = corporateStatusViewRulesService.getAllCorporateStatusViewRuleses();

        assertSame(dtoList, result);
        verify(corporateStatusViewRulesRepository).findAll();
        verify(corporateStatusViewRulesMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCorporateStatusViewRulesByIdIsCalled() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesKey expectedKey = new CorporateStatusViewRulesKey();
        expectedKey.setCorporateStatusId(corporateStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRules corporateStatusViewRules = createSampleCorporateStatusViewRulesEntity();
        CorporateStatusViewRulesDto corporateStatusViewRulesDto = createSampleCorporateStatusViewRulesDto();

        given(corporateStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.of(corporateStatusViewRules));
        given(corporateStatusViewRulesMapper.toDTO(corporateStatusViewRules)).willReturn(corporateStatusViewRulesDto);

        CorporateStatusViewRulesDto result = corporateStatusViewRulesService.getCorporateStatusViewRulesById(corporateStatusId, companyViewRulesId);

        assertSame(corporateStatusViewRulesDto, result);
        verify(corporateStatusViewRulesRepository).findById(expectedKey);
        verify(corporateStatusViewRulesMapper).toDTO(corporateStatusViewRules);
    }

    @Test
    void shouldThrowWhenGetCorporateStatusViewRulesByIdCannotFindEntity() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesKey expectedKey = new CorporateStatusViewRulesKey();
        expectedKey.setCorporateStatusId(corporateStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        given(corporateStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusViewRulesService.getCorporateStatusViewRulesById(corporateStatusId, companyViewRulesId));

        verify(corporateStatusViewRulesRepository).findById(expectedKey);
        verify(corporateStatusViewRulesMapper, never()).toDTO(any(CorporateStatusViewRules.class));
    }

    @Test
    void shouldCreateCorporateStatusViewRulesWhenCreateCorporateStatusViewRulesIsCalled() {
        CorporateStatusViewRulesDto requestDto = createSampleCorporateStatusViewRulesDto();
        CorporateStatusViewRules mappedEntity = createSampleCorporateStatusViewRulesEntity();
        CorporateStatusViewRules savedEntity = createAnotherCorporateStatusViewRulesEntity();
        CorporateStatusViewRulesDto responseDto = createAnotherCorporateStatusViewRulesDto();

        given(corporateStatusViewRulesMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(corporateStatusViewRulesRepository.save(mappedEntity)).willReturn(savedEntity);
        given(corporateStatusViewRulesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CorporateStatusViewRulesDto result = corporateStatusViewRulesService.createCorporateStatusViewRules(requestDto);

        assertSame(responseDto, result);
        verify(corporateStatusViewRulesMapper).toEntity(requestDto);
        verify(corporateStatusViewRulesRepository).save(mappedEntity);
        verify(corporateStatusViewRulesMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCorporateStatusViewRulesWhenEntityExists() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesKey expectedKey = new CorporateStatusViewRulesKey();
        expectedKey.setCorporateStatusId(corporateStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesDto requestDto = createPatchCorporateStatusViewRulesDto();
        CorporateStatusViewRules existingEntity = createSampleCorporateStatusViewRulesEntity();
        CorporateStatusViewRules savedEntity = createAnotherCorporateStatusViewRulesEntity();
        CorporateStatusViewRulesDto responseDto = createAnotherCorporateStatusViewRulesDto();

        given(corporateStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(corporateStatusViewRulesRepository.save(existingEntity)).willReturn(savedEntity);
        given(corporateStatusViewRulesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CorporateStatusViewRulesDto result = corporateStatusViewRulesService.updateCorporateStatusViewRules(corporateStatusId, companyViewRulesId, requestDto);

        assertSame(responseDto, result);
        verify(corporateStatusViewRulesRepository).findById(expectedKey);
        verify(corporateStatusViewRulesMapper).partialUpdate(existingEntity, requestDto);
        verify(corporateStatusViewRulesRepository).save(existingEntity);
        verify(corporateStatusViewRulesMapper).toDTO(savedEntity);
        verify(corporateStatusViewRulesMapper, never()).toEntity(any(CorporateStatusViewRulesDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCorporateStatusViewRulesCannotFindEntity() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesKey expectedKey = new CorporateStatusViewRulesKey();
        expectedKey.setCorporateStatusId(corporateStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesDto requestDto = createPatchCorporateStatusViewRulesDto();

        given(corporateStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusViewRulesService.updateCorporateStatusViewRules(corporateStatusId, companyViewRulesId, requestDto));

        verify(corporateStatusViewRulesRepository).findById(expectedKey);
        verify(corporateStatusViewRulesMapper, never()).partialUpdate(any(), any());
        verify(corporateStatusViewRulesRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCorporateStatusViewRulesWhenEntityExists() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesKey expectedKey = new CorporateStatusViewRulesKey();
        expectedKey.setCorporateStatusId(corporateStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRules existingEntity = createSampleCorporateStatusViewRulesEntity();

        given(corporateStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        corporateStatusViewRulesService.deleteCorporateStatusViewRules(corporateStatusId, companyViewRulesId);

        verify(corporateStatusViewRulesRepository).findById(expectedKey);
        verify(corporateStatusViewRulesRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteCorporateStatusViewRulesCannotFindEntity() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CorporateStatusViewRulesKey expectedKey = new CorporateStatusViewRulesKey();
        expectedKey.setCorporateStatusId(corporateStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        given(corporateStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusViewRulesService.deleteCorporateStatusViewRules(corporateStatusId, companyViewRulesId));

        verify(corporateStatusViewRulesRepository).findById(expectedKey);
        verify(corporateStatusViewRulesRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated CorporateStatusViewRules fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusViewRules createSampleCorporateStatusViewRulesEntity() {
        CorporateStatusViewRules entity = new CorporateStatusViewRules();
        entity.setExcludeCompanies(true);
        entity.setShowContactInfo(true);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CorporateStatusViewRules fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusViewRules createAnotherCorporateStatusViewRulesEntity() {
        CorporateStatusViewRules entity = new CorporateStatusViewRules();
        entity.setExcludeCompanies(false);
        entity.setShowContactInfo(false);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CorporateStatusViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusViewRulesDto createSampleCorporateStatusViewRulesDto() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();
        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setCompanyViewRulesId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCorporateStatus(new CorporateStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());
        dto.setExcludeCompanies(true);
        dto.setShowContactInfo(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CorporateStatusViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusViewRulesDto createAnotherCorporateStatusViewRulesDto() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();
        CorporateStatusViewRulesKey id = new CorporateStatusViewRulesKey();
        id.setCorporateStatusId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setCompanyViewRulesId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCorporateStatus(new CorporateStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());
        dto.setExcludeCompanies(false);
        dto.setShowContactInfo(false);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CorporateStatusViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusViewRulesDto createPatchCorporateStatusViewRulesDto() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();
        dto.setCorporateStatus(new CorporateStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());
        dto.setExcludeCompanies(true);
        dto.setShowContactInfo(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
