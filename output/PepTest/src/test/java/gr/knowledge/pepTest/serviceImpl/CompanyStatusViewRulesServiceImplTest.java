package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyStatusViewRules;
import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.repository.CompanyStatusViewRulesRepository;
import gr.knowledge.pepTest.mapper.CompanyStatusViewRulesMapper;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesKey;
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
class CompanyStatusViewRulesServiceImplTest {

    @Mock
    private CompanyStatusViewRulesRepository companyStatusViewRulesRepository;

    @Mock
    private CompanyStatusViewRulesMapper companyStatusViewRulesMapper;

    @InjectMocks
    private CompanyStatusViewRulesServiceImpl companyStatusViewRulesService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyStatusViewRules.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyStatusViewRules", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyStatusViewRulesesIsCalled() {
        List<CompanyStatusViewRules> entityList = List.of(createSampleCompanyStatusViewRulesEntity(), createAnotherCompanyStatusViewRulesEntity());
        List<CompanyStatusViewRulesDto> dtoList = List.of(createSampleCompanyStatusViewRulesDto(), createAnotherCompanyStatusViewRulesDto());

        given(companyStatusViewRulesRepository.findAll()).willReturn(entityList);
        given(companyStatusViewRulesMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyStatusViewRulesDto> result = companyStatusViewRulesService.getAllCompanyStatusViewRuleses();

        assertSame(dtoList, result);
        verify(companyStatusViewRulesRepository).findAll();
        verify(companyStatusViewRulesMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyStatusViewRulesByIdIsCalled() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(companyStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesKey expectedKey = new CompanyStatusViewRulesKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRules companyStatusViewRules = createSampleCompanyStatusViewRulesEntity();
        CompanyStatusViewRulesDto companyStatusViewRulesDto = createSampleCompanyStatusViewRulesDto();

        given(companyStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.of(companyStatusViewRules));
        given(companyStatusViewRulesMapper.toDTO(companyStatusViewRules)).willReturn(companyStatusViewRulesDto);

        CompanyStatusViewRulesDto result = companyStatusViewRulesService.getCompanyStatusViewRulesById(companyStatusId, companyViewRulesId);

        assertSame(companyStatusViewRulesDto, result);
        verify(companyStatusViewRulesRepository).findById(expectedKey);
        verify(companyStatusViewRulesMapper).toDTO(companyStatusViewRules);
    }

    @Test
    void shouldThrowWhenGetCompanyStatusViewRulesByIdCannotFindEntity() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(companyStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesKey expectedKey = new CompanyStatusViewRulesKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        given(companyStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusViewRulesService.getCompanyStatusViewRulesById(companyStatusId, companyViewRulesId));

        verify(companyStatusViewRulesRepository).findById(expectedKey);
        verify(companyStatusViewRulesMapper, never()).toDTO(any(CompanyStatusViewRules.class));
    }

    @Test
    void shouldCreateCompanyStatusViewRulesWhenCreateCompanyStatusViewRulesIsCalled() {
        CompanyStatusViewRulesDto requestDto = createSampleCompanyStatusViewRulesDto();
        CompanyStatusViewRules mappedEntity = createSampleCompanyStatusViewRulesEntity();
        CompanyStatusViewRules savedEntity = createAnotherCompanyStatusViewRulesEntity();
        CompanyStatusViewRulesDto responseDto = createAnotherCompanyStatusViewRulesDto();

        given(companyStatusViewRulesMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyStatusViewRulesRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyStatusViewRulesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyStatusViewRulesDto result = companyStatusViewRulesService.createCompanyStatusViewRules(requestDto);

        assertSame(responseDto, result);
        verify(companyStatusViewRulesMapper).toEntity(requestDto);
        verify(companyStatusViewRulesRepository).save(mappedEntity);
        verify(companyStatusViewRulesMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyStatusViewRulesWhenEntityExists() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(companyStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesKey expectedKey = new CompanyStatusViewRulesKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesDto requestDto = createPatchCompanyStatusViewRulesDto();
        CompanyStatusViewRules existingEntity = createSampleCompanyStatusViewRulesEntity();
        CompanyStatusViewRules savedEntity = createAnotherCompanyStatusViewRulesEntity();
        CompanyStatusViewRulesDto responseDto = createAnotherCompanyStatusViewRulesDto();

        given(companyStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(companyStatusViewRulesRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyStatusViewRulesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyStatusViewRulesDto result = companyStatusViewRulesService.updateCompanyStatusViewRules(companyStatusId, companyViewRulesId, requestDto);

        assertSame(responseDto, result);
        verify(companyStatusViewRulesRepository).findById(expectedKey);
        verify(companyStatusViewRulesMapper).partialUpdate(existingEntity, requestDto);
        verify(companyStatusViewRulesRepository).save(existingEntity);
        verify(companyStatusViewRulesMapper).toDTO(savedEntity);
        verify(companyStatusViewRulesMapper, never()).toEntity(any(CompanyStatusViewRulesDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyStatusViewRulesCannotFindEntity() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(companyStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesKey expectedKey = new CompanyStatusViewRulesKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesDto requestDto = createPatchCompanyStatusViewRulesDto();

        given(companyStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusViewRulesService.updateCompanyStatusViewRules(companyStatusId, companyViewRulesId, requestDto));

        verify(companyStatusViewRulesRepository).findById(expectedKey);
        verify(companyStatusViewRulesMapper, never()).partialUpdate(any(), any());
        verify(companyStatusViewRulesRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyStatusViewRulesWhenEntityExists() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(companyStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesKey expectedKey = new CompanyStatusViewRulesKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRules existingEntity = createSampleCompanyStatusViewRulesEntity();

        given(companyStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        companyStatusViewRulesService.deleteCompanyStatusViewRules(companyStatusId, companyViewRulesId);

        verify(companyStatusViewRulesRepository).findById(expectedKey);
        verify(companyStatusViewRulesRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteCompanyStatusViewRulesCannotFindEntity() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(companyStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);

        CompanyStatusViewRulesKey expectedKey = new CompanyStatusViewRulesKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setCompanyViewRulesId(companyViewRulesId);

        given(companyStatusViewRulesRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusViewRulesService.deleteCompanyStatusViewRules(companyStatusId, companyViewRulesId));

        verify(companyStatusViewRulesRepository).findById(expectedKey);
        verify(companyStatusViewRulesRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated CompanyStatusViewRules fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusViewRules createSampleCompanyStatusViewRulesEntity() {
        CompanyStatusViewRules entity = new CompanyStatusViewRules();
        entity.setExcludeCompanies(true);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CompanyStatusViewRules fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusViewRules createAnotherCompanyStatusViewRulesEntity() {
        CompanyStatusViewRules entity = new CompanyStatusViewRules();
        entity.setExcludeCompanies(false);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CompanyStatusViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusViewRulesDto createSampleCompanyStatusViewRulesDto() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();
        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setCompanyViewRulesId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());
        dto.setExcludeCompanies(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CompanyStatusViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusViewRulesDto createAnotherCompanyStatusViewRulesDto() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();
        CompanyStatusViewRulesKey id = new CompanyStatusViewRulesKey();
        id.setCompanyStatusId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setCompanyViewRulesId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());
        dto.setExcludeCompanies(false);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CompanyStatusViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusViewRulesDto createPatchCompanyStatusViewRulesDto() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();
        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());
        dto.setExcludeCompanies(true);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
