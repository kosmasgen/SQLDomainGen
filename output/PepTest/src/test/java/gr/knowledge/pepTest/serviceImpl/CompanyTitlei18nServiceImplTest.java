package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyTitlei18n;
import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.CompanyTitlei18nRepository;
import gr.knowledge.pepTest.mapper.CompanyTitlei18nMapper;
import gr.knowledge.pepTest.entity.CompanyTitlei18nKey;
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
class CompanyTitlei18nServiceImplTest {

    @Mock
    private CompanyTitlei18nRepository companyTitlei18nRepository;

    @Mock
    private CompanyTitlei18nMapper companyTitlei18nMapper;

    @InjectMocks
    private CompanyTitlei18nServiceImpl companyTitlei18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyTitlei18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyTitlei18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyTitlei18nsIsCalled() {
        List<CompanyTitlei18n> entityList = List.of(createSampleCompanyTitlei18nEntity(), createAnotherCompanyTitlei18nEntity());
        List<CompanyTitlei18nDto> dtoList = List.of(createSampleCompanyTitlei18nDto(), createAnotherCompanyTitlei18nDto());

        given(companyTitlei18nRepository.findAll()).willReturn(entityList);
        given(companyTitlei18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyTitlei18nDto> result = companyTitlei18nService.getAllCompanyTitlei18ns();

        assertSame(dtoList, result);
        verify(companyTitlei18nRepository).findAll();
        verify(companyTitlei18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyTitlei18nByIdIsCalled() {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(companyTitleId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nKey expectedKey = new CompanyTitlei18nKey();
        expectedKey.setCompanyTitleId(companyTitleId);
        expectedKey.setLanguageId(languageId);
        expectedKey.setChamberI18nId(chamberI18nId);

        CompanyTitlei18n companyTitlei18n = createSampleCompanyTitlei18nEntity();
        CompanyTitlei18nDto companyTitlei18nDto = createSampleCompanyTitlei18nDto();

        given(companyTitlei18nRepository.findById(expectedKey)).willReturn(Optional.of(companyTitlei18n));
        given(companyTitlei18nMapper.toDTO(companyTitlei18n)).willReturn(companyTitlei18nDto);

        CompanyTitlei18nDto result = companyTitlei18nService.getCompanyTitlei18nById(companyTitleId, languageId, chamberI18nId);

        assertSame(companyTitlei18nDto, result);
        verify(companyTitlei18nRepository).findById(expectedKey);
        verify(companyTitlei18nMapper).toDTO(companyTitlei18n);
    }

    @Test
    void shouldThrowWhenGetCompanyTitlei18nByIdCannotFindEntity() {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(companyTitleId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nKey expectedKey = new CompanyTitlei18nKey();
        expectedKey.setCompanyTitleId(companyTitleId);
        expectedKey.setLanguageId(languageId);
        expectedKey.setChamberI18nId(chamberI18nId);

        given(companyTitlei18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyTitlei18nService.getCompanyTitlei18nById(companyTitleId, languageId, chamberI18nId));

        verify(companyTitlei18nRepository).findById(expectedKey);
        verify(companyTitlei18nMapper, never()).toDTO(any(CompanyTitlei18n.class));
    }

    @Test
    void shouldCreateCompanyTitlei18nWhenCreateCompanyTitlei18nIsCalled() {
        CompanyTitlei18nDto requestDto = createSampleCompanyTitlei18nDto();
        CompanyTitlei18n mappedEntity = createSampleCompanyTitlei18nEntity();
        CompanyTitlei18n savedEntity = createAnotherCompanyTitlei18nEntity();
        CompanyTitlei18nDto responseDto = createAnotherCompanyTitlei18nDto();

        given(companyTitlei18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyTitlei18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyTitlei18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyTitlei18nDto result = companyTitlei18nService.createCompanyTitlei18n(requestDto);

        assertSame(responseDto, result);
        verify(companyTitlei18nMapper).toEntity(requestDto);
        verify(companyTitlei18nRepository).save(mappedEntity);
        verify(companyTitlei18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyTitlei18nWhenEntityExists() {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(companyTitleId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nKey expectedKey = new CompanyTitlei18nKey();
        expectedKey.setCompanyTitleId(companyTitleId);
        expectedKey.setLanguageId(languageId);
        expectedKey.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nDto requestDto = createPatchCompanyTitlei18nDto();
        CompanyTitlei18n existingEntity = createSampleCompanyTitlei18nEntity();
        CompanyTitlei18n savedEntity = createAnotherCompanyTitlei18nEntity();
        CompanyTitlei18nDto responseDto = createAnotherCompanyTitlei18nDto();

        given(companyTitlei18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(companyTitlei18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyTitlei18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyTitlei18nDto result = companyTitlei18nService.updateCompanyTitlei18n(companyTitleId, languageId, chamberI18nId, requestDto);

        assertSame(responseDto, result);
        verify(companyTitlei18nRepository).findById(expectedKey);
        verify(companyTitlei18nMapper).partialUpdate(existingEntity, requestDto);
        verify(companyTitlei18nRepository).save(existingEntity);
        verify(companyTitlei18nMapper).toDTO(savedEntity);
        verify(companyTitlei18nMapper, never()).toEntity(any(CompanyTitlei18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyTitlei18nCannotFindEntity() {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(companyTitleId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nKey expectedKey = new CompanyTitlei18nKey();
        expectedKey.setCompanyTitleId(companyTitleId);
        expectedKey.setLanguageId(languageId);
        expectedKey.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nDto requestDto = createPatchCompanyTitlei18nDto();

        given(companyTitlei18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyTitlei18nService.updateCompanyTitlei18n(companyTitleId, languageId, chamberI18nId, requestDto));

        verify(companyTitlei18nRepository).findById(expectedKey);
        verify(companyTitlei18nMapper, never()).partialUpdate(any(), any());
        verify(companyTitlei18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyTitlei18nWhenEntityExists() {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(companyTitleId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nKey expectedKey = new CompanyTitlei18nKey();
        expectedKey.setCompanyTitleId(companyTitleId);
        expectedKey.setLanguageId(languageId);
        expectedKey.setChamberI18nId(chamberI18nId);

        CompanyTitlei18n existingEntity = createSampleCompanyTitlei18nEntity();

        given(companyTitlei18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        companyTitlei18nService.deleteCompanyTitlei18n(companyTitleId, languageId, chamberI18nId);

        verify(companyTitlei18nRepository).findById(expectedKey);
        verify(companyTitlei18nRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteCompanyTitlei18nCannotFindEntity() {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 1;

        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(companyTitleId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);

        CompanyTitlei18nKey expectedKey = new CompanyTitlei18nKey();
        expectedKey.setCompanyTitleId(companyTitleId);
        expectedKey.setLanguageId(languageId);
        expectedKey.setChamberI18nId(chamberI18nId);

        given(companyTitlei18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyTitlei18nService.deleteCompanyTitlei18n(companyTitleId, languageId, chamberI18nId));

        verify(companyTitlei18nRepository).findById(expectedKey);
        verify(companyTitlei18nRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated CompanyTitlei18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyTitlei18n createSampleCompanyTitlei18nEntity() {
        CompanyTitlei18n entity = new CompanyTitlei18n();
        entity.setTitle("title-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyTitlei18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyTitlei18n createAnotherCompanyTitlei18nEntity() {
        CompanyTitlei18n entity = new CompanyTitlei18n();
        entity.setTitle("title-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyTitlei18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitlei18nDto createSampleCompanyTitlei18nDto() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();
        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setChamberI18nId(1);
        dto.setId(id);

        dto.setCompanyTitle(new CompanyTitleDto());
        dto.setLanguage(new LanguagesDto());
        dto.setTitle("title-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CompanyTitlei18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitlei18nDto createAnotherCompanyTitlei18nDto() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();
        CompanyTitlei18nKey id = new CompanyTitlei18nKey();
        id.setCompanyTitleId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setChamberI18nId(2);
        dto.setId(id);

        dto.setCompanyTitle(new CompanyTitleDto());
        dto.setLanguage(new LanguagesDto());
        dto.setTitle("title-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CompanyTitlei18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyTitlei18nDto createPatchCompanyTitlei18nDto() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();
        dto.setCompanyTitle(new CompanyTitleDto());
        dto.setLanguage(new LanguagesDto());
        dto.setTitle("title-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
