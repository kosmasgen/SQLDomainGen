package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyYpArticleI18n;
import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.CompanyYpArticleI18nRepository;
import gr.knowledge.pepTest.mapper.CompanyYpArticleI18nMapper;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nKey;
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
class CompanyYpArticleI18nServiceImplTest {

    @Mock
    private CompanyYpArticleI18nRepository companyYpArticleI18nRepository;

    @Mock
    private CompanyYpArticleI18nMapper companyYpArticleI18nMapper;

    @InjectMocks
    private CompanyYpArticleI18nServiceImpl companyYpArticleI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyYpArticleI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyYpArticleI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyYpArticleI18nsIsCalled() {
        List<CompanyYpArticleI18n> entityList = List.of(createSampleCompanyYpArticleI18nEntity(), createAnotherCompanyYpArticleI18nEntity());
        List<CompanyYpArticleI18nDto> dtoList = List.of(createSampleCompanyYpArticleI18nDto(), createAnotherCompanyYpArticleI18nDto());

        given(companyYpArticleI18nRepository.findAll()).willReturn(entityList);
        given(companyYpArticleI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyYpArticleI18nDto> result = companyYpArticleI18nService.getAllCompanyYpArticleI18ns();

        assertSame(dtoList, result);
        verify(companyYpArticleI18nRepository).findAll();
        verify(companyYpArticleI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyYpArticleI18nByIdIsCalled() {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(companyArticleId);
        id.setLanguageId(languageId);

        CompanyYpArticleI18nKey expectedKey = new CompanyYpArticleI18nKey();
        expectedKey.setCompanyArticleId(companyArticleId);
        expectedKey.setLanguageId(languageId);

        CompanyYpArticleI18n companyYpArticleI18n = createSampleCompanyYpArticleI18nEntity();
        CompanyYpArticleI18nDto companyYpArticleI18nDto = createSampleCompanyYpArticleI18nDto();

        given(companyYpArticleI18nRepository.findById(expectedKey)).willReturn(Optional.of(companyYpArticleI18n));
        given(companyYpArticleI18nMapper.toDTO(companyYpArticleI18n)).willReturn(companyYpArticleI18nDto);

        CompanyYpArticleI18nDto result = companyYpArticleI18nService.getCompanyYpArticleI18nById(companyArticleId, languageId);

        assertSame(companyYpArticleI18nDto, result);
        verify(companyYpArticleI18nRepository).findById(expectedKey);
        verify(companyYpArticleI18nMapper).toDTO(companyYpArticleI18n);
    }

    @Test
    void shouldThrowWhenGetCompanyYpArticleI18nByIdCannotFindEntity() {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(companyArticleId);
        id.setLanguageId(languageId);

        CompanyYpArticleI18nKey expectedKey = new CompanyYpArticleI18nKey();
        expectedKey.setCompanyArticleId(companyArticleId);
        expectedKey.setLanguageId(languageId);

        given(companyYpArticleI18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpArticleI18nService.getCompanyYpArticleI18nById(companyArticleId, languageId));

        verify(companyYpArticleI18nRepository).findById(expectedKey);
        verify(companyYpArticleI18nMapper, never()).toDTO(any(CompanyYpArticleI18n.class));
    }

    @Test
    void shouldCreateCompanyYpArticleI18nWhenCreateCompanyYpArticleI18nIsCalled() {
        CompanyYpArticleI18nDto requestDto = createSampleCompanyYpArticleI18nDto();
        CompanyYpArticleI18n mappedEntity = createSampleCompanyYpArticleI18nEntity();
        CompanyYpArticleI18n savedEntity = createAnotherCompanyYpArticleI18nEntity();
        CompanyYpArticleI18nDto responseDto = createAnotherCompanyYpArticleI18nDto();

        given(companyYpArticleI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyYpArticleI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyYpArticleI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpArticleI18nDto result = companyYpArticleI18nService.createCompanyYpArticleI18n(requestDto);

        assertSame(responseDto, result);
        verify(companyYpArticleI18nMapper).toEntity(requestDto);
        verify(companyYpArticleI18nRepository).save(mappedEntity);
        verify(companyYpArticleI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyYpArticleI18nWhenEntityExists() {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(companyArticleId);
        id.setLanguageId(languageId);

        CompanyYpArticleI18nKey expectedKey = new CompanyYpArticleI18nKey();
        expectedKey.setCompanyArticleId(companyArticleId);
        expectedKey.setLanguageId(languageId);

        CompanyYpArticleI18nDto requestDto = createPatchCompanyYpArticleI18nDto();
        CompanyYpArticleI18n existingEntity = createSampleCompanyYpArticleI18nEntity();
        CompanyYpArticleI18n savedEntity = createAnotherCompanyYpArticleI18nEntity();
        CompanyYpArticleI18nDto responseDto = createAnotherCompanyYpArticleI18nDto();

        given(companyYpArticleI18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(companyYpArticleI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyYpArticleI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyYpArticleI18nDto result = companyYpArticleI18nService.updateCompanyYpArticleI18n(companyArticleId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(companyYpArticleI18nRepository).findById(expectedKey);
        verify(companyYpArticleI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(companyYpArticleI18nRepository).save(existingEntity);
        verify(companyYpArticleI18nMapper).toDTO(savedEntity);
        verify(companyYpArticleI18nMapper, never()).toEntity(any(CompanyYpArticleI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyYpArticleI18nCannotFindEntity() {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(companyArticleId);
        id.setLanguageId(languageId);

        CompanyYpArticleI18nKey expectedKey = new CompanyYpArticleI18nKey();
        expectedKey.setCompanyArticleId(companyArticleId);
        expectedKey.setLanguageId(languageId);

        CompanyYpArticleI18nDto requestDto = createPatchCompanyYpArticleI18nDto();

        given(companyYpArticleI18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpArticleI18nService.updateCompanyYpArticleI18n(companyArticleId, languageId, requestDto));

        verify(companyYpArticleI18nRepository).findById(expectedKey);
        verify(companyYpArticleI18nMapper, never()).partialUpdate(any(), any());
        verify(companyYpArticleI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyYpArticleI18nWhenEntityExists() {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(companyArticleId);
        id.setLanguageId(languageId);

        CompanyYpArticleI18nKey expectedKey = new CompanyYpArticleI18nKey();
        expectedKey.setCompanyArticleId(companyArticleId);
        expectedKey.setLanguageId(languageId);

        CompanyYpArticleI18n existingEntity = createSampleCompanyYpArticleI18nEntity();

        given(companyYpArticleI18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        companyYpArticleI18nService.deleteCompanyYpArticleI18n(companyArticleId, languageId);

        verify(companyYpArticleI18nRepository).findById(expectedKey);
        verify(companyYpArticleI18nRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteCompanyYpArticleI18nCannotFindEntity() {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(companyArticleId);
        id.setLanguageId(languageId);

        CompanyYpArticleI18nKey expectedKey = new CompanyYpArticleI18nKey();
        expectedKey.setCompanyArticleId(companyArticleId);
        expectedKey.setLanguageId(languageId);

        given(companyYpArticleI18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyYpArticleI18nService.deleteCompanyYpArticleI18n(companyArticleId, languageId));

        verify(companyYpArticleI18nRepository).findById(expectedKey);
        verify(companyYpArticleI18nRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated CompanyYpArticleI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticleI18n createSampleCompanyYpArticleI18nEntity() {
        CompanyYpArticleI18n entity = new CompanyYpArticleI18n();
        entity.setTitle("title-value-1");
        entity.setHtml("html-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticleI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyYpArticleI18n createAnotherCompanyYpArticleI18nEntity() {
        CompanyYpArticleI18n entity = new CompanyYpArticleI18n();
        entity.setTitle("title-value-2");
        entity.setHtml("html-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyYpArticleI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleI18nDto createSampleCompanyYpArticleI18nDto() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();
        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyArticle(new CompanyYpArticleDto());
        dto.setTitle("title-value-1");
        dto.setHtml("html-value-1");
        dto.setLanguage(new LanguagesDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleI18nDto createAnotherCompanyYpArticleI18nDto() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();
        CompanyYpArticleI18nKey id = new CompanyYpArticleI18nKey();
        id.setCompanyArticleId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyArticle(new CompanyYpArticleDto());
        dto.setTitle("title-value-2");
        dto.setHtml("html-value-2");
        dto.setLanguage(new LanguagesDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CompanyYpArticleI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyYpArticleI18nDto createPatchCompanyYpArticleI18nDto() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();
        dto.setCompanyArticle(new CompanyYpArticleDto());
        dto.setTitle("title-value-3");
        dto.setHtml("html-value-3");
        dto.setLanguage(new LanguagesDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
