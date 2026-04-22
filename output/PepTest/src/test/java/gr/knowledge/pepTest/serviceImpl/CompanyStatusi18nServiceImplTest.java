package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyStatusi18n;
import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.CompanyStatusi18nRepository;
import gr.knowledge.pepTest.mapper.CompanyStatusi18nMapper;
import gr.knowledge.pepTest.entity.CompanyStatusi18nKey;
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
class CompanyStatusi18nServiceImplTest {

    @Mock
    private CompanyStatusi18nRepository companyStatusi18nRepository;

    @Mock
    private CompanyStatusi18nMapper companyStatusi18nMapper;

    @InjectMocks
    private CompanyStatusi18nServiceImpl companyStatusi18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyStatusi18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyStatusi18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyStatusi18nsIsCalled() {
        List<CompanyStatusi18n> entityList = List.of(createSampleCompanyStatusi18nEntity(), createAnotherCompanyStatusi18nEntity());
        List<CompanyStatusi18nDto> dtoList = List.of(createSampleCompanyStatusi18nDto(), createAnotherCompanyStatusi18nDto());

        given(companyStatusi18nRepository.findAll()).willReturn(entityList);
        given(companyStatusi18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyStatusi18nDto> result = companyStatusi18nService.getAllCompanyStatusi18ns();

        assertSame(dtoList, result);
        verify(companyStatusi18nRepository).findAll();
        verify(companyStatusi18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyStatusi18nByIdIsCalled() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(companyStatusId);
        id.setLanguageId(languageId);

        CompanyStatusi18nKey expectedKey = new CompanyStatusi18nKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setLanguageId(languageId);

        CompanyStatusi18n companyStatusi18n = createSampleCompanyStatusi18nEntity();
        CompanyStatusi18nDto companyStatusi18nDto = createSampleCompanyStatusi18nDto();

        given(companyStatusi18nRepository.findById(expectedKey)).willReturn(Optional.of(companyStatusi18n));
        given(companyStatusi18nMapper.toDTO(companyStatusi18n)).willReturn(companyStatusi18nDto);

        CompanyStatusi18nDto result = companyStatusi18nService.getCompanyStatusi18nById(companyStatusId, languageId);

        assertSame(companyStatusi18nDto, result);
        verify(companyStatusi18nRepository).findById(expectedKey);
        verify(companyStatusi18nMapper).toDTO(companyStatusi18n);
    }

    @Test
    void shouldThrowWhenGetCompanyStatusi18nByIdCannotFindEntity() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(companyStatusId);
        id.setLanguageId(languageId);

        CompanyStatusi18nKey expectedKey = new CompanyStatusi18nKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setLanguageId(languageId);

        given(companyStatusi18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusi18nService.getCompanyStatusi18nById(companyStatusId, languageId));

        verify(companyStatusi18nRepository).findById(expectedKey);
        verify(companyStatusi18nMapper, never()).toDTO(any(CompanyStatusi18n.class));
    }

    @Test
    void shouldCreateCompanyStatusi18nWhenCreateCompanyStatusi18nIsCalled() {
        CompanyStatusi18nDto requestDto = createSampleCompanyStatusi18nDto();
        CompanyStatusi18n mappedEntity = createSampleCompanyStatusi18nEntity();
        CompanyStatusi18n savedEntity = createAnotherCompanyStatusi18nEntity();
        CompanyStatusi18nDto responseDto = createAnotherCompanyStatusi18nDto();

        given(companyStatusi18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyStatusi18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyStatusi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyStatusi18nDto result = companyStatusi18nService.createCompanyStatusi18n(requestDto);

        assertSame(responseDto, result);
        verify(companyStatusi18nMapper).toEntity(requestDto);
        verify(companyStatusi18nRepository).save(mappedEntity);
        verify(companyStatusi18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyStatusi18nWhenEntityExists() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(companyStatusId);
        id.setLanguageId(languageId);

        CompanyStatusi18nKey expectedKey = new CompanyStatusi18nKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setLanguageId(languageId);

        CompanyStatusi18nDto requestDto = createPatchCompanyStatusi18nDto();
        CompanyStatusi18n existingEntity = createSampleCompanyStatusi18nEntity();
        CompanyStatusi18n savedEntity = createAnotherCompanyStatusi18nEntity();
        CompanyStatusi18nDto responseDto = createAnotherCompanyStatusi18nDto();

        given(companyStatusi18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(companyStatusi18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyStatusi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyStatusi18nDto result = companyStatusi18nService.updateCompanyStatusi18n(companyStatusId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(companyStatusi18nRepository).findById(expectedKey);
        verify(companyStatusi18nMapper).partialUpdate(existingEntity, requestDto);
        verify(companyStatusi18nRepository).save(existingEntity);
        verify(companyStatusi18nMapper).toDTO(savedEntity);
        verify(companyStatusi18nMapper, never()).toEntity(any(CompanyStatusi18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyStatusi18nCannotFindEntity() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(companyStatusId);
        id.setLanguageId(languageId);

        CompanyStatusi18nKey expectedKey = new CompanyStatusi18nKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setLanguageId(languageId);

        CompanyStatusi18nDto requestDto = createPatchCompanyStatusi18nDto();

        given(companyStatusi18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusi18nService.updateCompanyStatusi18n(companyStatusId, languageId, requestDto));

        verify(companyStatusi18nRepository).findById(expectedKey);
        verify(companyStatusi18nMapper, never()).partialUpdate(any(), any());
        verify(companyStatusi18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyStatusi18nWhenEntityExists() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(companyStatusId);
        id.setLanguageId(languageId);

        CompanyStatusi18nKey expectedKey = new CompanyStatusi18nKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setLanguageId(languageId);

        CompanyStatusi18n existingEntity = createSampleCompanyStatusi18nEntity();

        given(companyStatusi18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        companyStatusi18nService.deleteCompanyStatusi18n(companyStatusId, languageId);

        verify(companyStatusi18nRepository).findById(expectedKey);
        verify(companyStatusi18nRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteCompanyStatusi18nCannotFindEntity() {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(companyStatusId);
        id.setLanguageId(languageId);

        CompanyStatusi18nKey expectedKey = new CompanyStatusi18nKey();
        expectedKey.setCompanyStatusId(companyStatusId);
        expectedKey.setLanguageId(languageId);

        given(companyStatusi18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusi18nService.deleteCompanyStatusi18n(companyStatusId, languageId));

        verify(companyStatusi18nRepository).findById(expectedKey);
        verify(companyStatusi18nRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated CompanyStatusi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusi18n createSampleCompanyStatusi18nEntity() {
        CompanyStatusi18n entity = new CompanyStatusi18n();
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setChamberI18nId(1);

        return entity;
    }

    /**
     * Creates a populated CompanyStatusi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatusi18n createAnotherCompanyStatusi18nEntity() {
        CompanyStatusi18n entity = new CompanyStatusi18n();
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setChamberI18nId(2);

        return entity;
    }

    /**
     * Creates a populated CompanyStatusi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusi18nDto createSampleCompanyStatusi18nDto() {
        CompanyStatusi18nDto dto = new CompanyStatusi18nDto();
        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(1);

        return dto;
    }

    /**
     * Creates a populated CompanyStatusi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusi18nDto createAnotherCompanyStatusi18nDto() {
        CompanyStatusi18nDto dto = new CompanyStatusi18nDto();
        CompanyStatusi18nKey id = new CompanyStatusi18nKey();
        id.setCompanyStatusId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setChamberI18nId(2);

        return dto;
    }

    /**
     * Creates a populated CompanyStatusi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusi18nDto createPatchCompanyStatusi18nDto() {
        CompanyStatusi18nDto dto = new CompanyStatusi18nDto();
        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(3);

        return dto;
    }

}
