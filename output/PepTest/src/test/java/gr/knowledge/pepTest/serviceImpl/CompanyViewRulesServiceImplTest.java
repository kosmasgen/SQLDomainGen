package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyViewRules;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.repository.CompanyViewRulesRepository;
import gr.knowledge.pepTest.mapper.CompanyViewRulesMapper;
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
class CompanyViewRulesServiceImplTest {

    @Mock
    private CompanyViewRulesRepository companyViewRulesRepository;

    @Mock
    private CompanyViewRulesMapper companyViewRulesMapper;

    @InjectMocks
    private CompanyViewRulesServiceImpl companyViewRulesService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyViewRules.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyViewRules", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyViewRulesesIsCalled() {
        List<CompanyViewRules> entityList = List.of(createSampleCompanyViewRulesEntity(), createAnotherCompanyViewRulesEntity());
        List<CompanyViewRulesDto> dtoList = List.of(createSampleCompanyViewRulesDto(), createAnotherCompanyViewRulesDto());

        given(companyViewRulesRepository.findAll()).willReturn(entityList);
        given(companyViewRulesMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyViewRulesDto> result = companyViewRulesService.getAllCompanyViewRuleses();

        assertSame(dtoList, result);
        verify(companyViewRulesRepository).findAll();
        verify(companyViewRulesMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyViewRulesByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyViewRules companyViewRules = createSampleCompanyViewRulesEntity();
        CompanyViewRulesDto companyViewRulesDto = createSampleCompanyViewRulesDto();

        given(companyViewRulesRepository.findById(id)).willReturn(Optional.of(companyViewRules));
        given(companyViewRulesMapper.toDTO(companyViewRules)).willReturn(companyViewRulesDto);

        CompanyViewRulesDto result = companyViewRulesService.getCompanyViewRulesById(id);

        assertSame(companyViewRulesDto, result);
        verify(companyViewRulesRepository).findById(id);
        verify(companyViewRulesMapper).toDTO(companyViewRules);
    }

    @Test
    void shouldThrowWhenGetCompanyViewRulesByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyViewRulesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyViewRulesService.getCompanyViewRulesById(id));

        verify(companyViewRulesRepository).findById(id);
        verify(companyViewRulesMapper, never()).toDTO(any(CompanyViewRules.class));
    }

    @Test
    void shouldCreateCompanyViewRulesWhenCreateCompanyViewRulesIsCalled() {
        CompanyViewRulesDto requestDto = createSampleCompanyViewRulesDto();
        CompanyViewRules mappedEntity = createSampleCompanyViewRulesEntity();
        CompanyViewRules savedEntity = createAnotherCompanyViewRulesEntity();
        CompanyViewRulesDto responseDto = createAnotherCompanyViewRulesDto();

        given(companyViewRulesMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyViewRulesRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyViewRulesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyViewRulesDto result = companyViewRulesService.createCompanyViewRules(requestDto);

        assertSame(responseDto, result);
        verify(companyViewRulesMapper).toEntity(requestDto);
        verify(companyViewRulesRepository).save(mappedEntity);
        verify(companyViewRulesMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyViewRulesWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyViewRulesDto requestDto = createPatchCompanyViewRulesDto();
        CompanyViewRules existingEntity = createSampleCompanyViewRulesEntity();
        CompanyViewRules savedEntity = createAnotherCompanyViewRulesEntity();
        CompanyViewRulesDto responseDto = createAnotherCompanyViewRulesDto();

        given(companyViewRulesRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyViewRulesRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyViewRulesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyViewRulesDto result = companyViewRulesService.updateCompanyViewRules(id, requestDto);

        assertSame(responseDto, result);
        verify(companyViewRulesRepository).findById(id);
        verify(companyViewRulesMapper).partialUpdate(existingEntity, requestDto);
        verify(companyViewRulesRepository).save(existingEntity);
        verify(companyViewRulesMapper).toDTO(savedEntity);
        verify(companyViewRulesMapper, never()).toEntity(any(CompanyViewRulesDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyViewRulesCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyViewRulesDto requestDto = createPatchCompanyViewRulesDto();

        given(companyViewRulesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyViewRulesService.updateCompanyViewRules(id, requestDto));

        verify(companyViewRulesRepository).findById(id);
        verify(companyViewRulesMapper, never()).partialUpdate(any(), any());
        verify(companyViewRulesRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyViewRulesWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyViewRules existingEntity = createSampleCompanyViewRulesEntity();

        given(companyViewRulesRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyViewRulesService.deleteCompanyViewRules(id);

        verify(companyViewRulesRepository).findById(id);
        verify(companyViewRulesRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyViewRulesCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyViewRulesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyViewRulesService.deleteCompanyViewRules(id));

        verify(companyViewRulesRepository).findById(id);
        verify(companyViewRulesRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyViewRules fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyViewRules createSampleCompanyViewRulesEntity() {
        CompanyViewRules entity = new CompanyViewRules();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setChamberId(1L);
        entity.setShowMobilePhone(true);
        entity.setShowPhones(true);
        entity.setShowEmail(true);
        entity.setShowAfm(true);
        entity.setShowBusinessInformation(true);

        return entity;
    }

    /**
     * Creates a populated CompanyViewRules fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyViewRules createAnotherCompanyViewRulesEntity() {
        CompanyViewRules entity = new CompanyViewRules();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setChamberId(2L);
        entity.setShowMobilePhone(false);
        entity.setShowPhones(false);
        entity.setShowEmail(false);
        entity.setShowAfm(false);
        entity.setShowBusinessInformation(false);

        return entity;
    }

    /**
     * Creates a populated CompanyViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyViewRulesDto createSampleCompanyViewRulesDto() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setChamberId(1L);
        dto.setShowMobilePhone(true);
        dto.setShowPhones(true);
        dto.setShowEmail(true);
        dto.setShowAfm(true);
        dto.setShowBusinessInformation(true);

        return dto;
    }

    /**
     * Creates a populated CompanyViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyViewRulesDto createAnotherCompanyViewRulesDto() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setChamberId(2L);
        dto.setShowMobilePhone(false);
        dto.setShowPhones(false);
        dto.setShowEmail(false);
        dto.setShowAfm(false);
        dto.setShowBusinessInformation(false);

        return dto;
    }

    /**
     * Creates a populated CompanyViewRulesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyViewRulesDto createPatchCompanyViewRulesDto() {
        CompanyViewRulesDto dto = new CompanyViewRulesDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setChamberId(3L);
        dto.setShowMobilePhone(true);
        dto.setShowPhones(true);
        dto.setShowEmail(true);
        dto.setShowAfm(true);
        dto.setShowBusinessInformation(true);

        return dto;
    }

}
