package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyProfileI18n;
import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.CompanyProfileI18nRepository;
import gr.knowledge.pepTest.mapper.CompanyProfileI18nMapper;
import gr.knowledge.pepTest.entity.CompanyProfileI18nKey;
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
class CompanyProfileI18nServiceImplTest {

    @Mock
    private CompanyProfileI18nRepository companyProfileI18nRepository;

    @Mock
    private CompanyProfileI18nMapper companyProfileI18nMapper;

    @InjectMocks
    private CompanyProfileI18nServiceImpl companyProfileI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyProfileI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyProfileI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyProfileI18nsIsCalled() {
        List<CompanyProfileI18n> entityList = List.of(createSampleCompanyProfileI18nEntity(), createAnotherCompanyProfileI18nEntity());
        List<CompanyProfileI18nDto> dtoList = List.of(createSampleCompanyProfileI18nDto(), createAnotherCompanyProfileI18nDto());

        given(companyProfileI18nRepository.findAll()).willReturn(entityList);
        given(companyProfileI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyProfileI18nDto> result = companyProfileI18nService.getAllCompanyProfileI18ns();

        assertSame(dtoList, result);
        verify(companyProfileI18nRepository).findAll();
        verify(companyProfileI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyProfileI18nByIdIsCalled() {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(companyProfileId);
        id.setLanguageId(languageId);

        CompanyProfileI18nKey expectedKey = new CompanyProfileI18nKey();
        expectedKey.setCompanyProfileId(companyProfileId);
        expectedKey.setLanguageId(languageId);

        CompanyProfileI18n companyProfileI18n = createSampleCompanyProfileI18nEntity();
        CompanyProfileI18nDto companyProfileI18nDto = createSampleCompanyProfileI18nDto();

        given(companyProfileI18nRepository.findById(expectedKey)).willReturn(Optional.of(companyProfileI18n));
        given(companyProfileI18nMapper.toDTO(companyProfileI18n)).willReturn(companyProfileI18nDto);

        CompanyProfileI18nDto result = companyProfileI18nService.getCompanyProfileI18nById(companyProfileId, languageId);

        assertSame(companyProfileI18nDto, result);
        verify(companyProfileI18nRepository).findById(expectedKey);
        verify(companyProfileI18nMapper).toDTO(companyProfileI18n);
    }

    @Test
    void shouldThrowWhenGetCompanyProfileI18nByIdCannotFindEntity() {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(companyProfileId);
        id.setLanguageId(languageId);

        CompanyProfileI18nKey expectedKey = new CompanyProfileI18nKey();
        expectedKey.setCompanyProfileId(companyProfileId);
        expectedKey.setLanguageId(languageId);

        given(companyProfileI18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfileI18nService.getCompanyProfileI18nById(companyProfileId, languageId));

        verify(companyProfileI18nRepository).findById(expectedKey);
        verify(companyProfileI18nMapper, never()).toDTO(any(CompanyProfileI18n.class));
    }

    @Test
    void shouldCreateCompanyProfileI18nWhenCreateCompanyProfileI18nIsCalled() {
        CompanyProfileI18nDto requestDto = createSampleCompanyProfileI18nDto();
        CompanyProfileI18n mappedEntity = createSampleCompanyProfileI18nEntity();
        CompanyProfileI18n savedEntity = createAnotherCompanyProfileI18nEntity();
        CompanyProfileI18nDto responseDto = createAnotherCompanyProfileI18nDto();

        given(companyProfileI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyProfileI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyProfileI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyProfileI18nDto result = companyProfileI18nService.createCompanyProfileI18n(requestDto);

        assertSame(responseDto, result);
        verify(companyProfileI18nMapper).toEntity(requestDto);
        verify(companyProfileI18nRepository).save(mappedEntity);
        verify(companyProfileI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyProfileI18nWhenEntityExists() {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(companyProfileId);
        id.setLanguageId(languageId);

        CompanyProfileI18nKey expectedKey = new CompanyProfileI18nKey();
        expectedKey.setCompanyProfileId(companyProfileId);
        expectedKey.setLanguageId(languageId);

        CompanyProfileI18nDto requestDto = createPatchCompanyProfileI18nDto();
        CompanyProfileI18n existingEntity = createSampleCompanyProfileI18nEntity();
        CompanyProfileI18n savedEntity = createAnotherCompanyProfileI18nEntity();
        CompanyProfileI18nDto responseDto = createAnotherCompanyProfileI18nDto();

        given(companyProfileI18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(companyProfileI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyProfileI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyProfileI18nDto result = companyProfileI18nService.updateCompanyProfileI18n(companyProfileId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(companyProfileI18nRepository).findById(expectedKey);
        verify(companyProfileI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(companyProfileI18nRepository).save(existingEntity);
        verify(companyProfileI18nMapper).toDTO(savedEntity);
        verify(companyProfileI18nMapper, never()).toEntity(any(CompanyProfileI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyProfileI18nCannotFindEntity() {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(companyProfileId);
        id.setLanguageId(languageId);

        CompanyProfileI18nKey expectedKey = new CompanyProfileI18nKey();
        expectedKey.setCompanyProfileId(companyProfileId);
        expectedKey.setLanguageId(languageId);

        CompanyProfileI18nDto requestDto = createPatchCompanyProfileI18nDto();

        given(companyProfileI18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfileI18nService.updateCompanyProfileI18n(companyProfileId, languageId, requestDto));

        verify(companyProfileI18nRepository).findById(expectedKey);
        verify(companyProfileI18nMapper, never()).partialUpdate(any(), any());
        verify(companyProfileI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyProfileI18nWhenEntityExists() {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(companyProfileId);
        id.setLanguageId(languageId);

        CompanyProfileI18nKey expectedKey = new CompanyProfileI18nKey();
        expectedKey.setCompanyProfileId(companyProfileId);
        expectedKey.setLanguageId(languageId);

        CompanyProfileI18n existingEntity = createSampleCompanyProfileI18nEntity();

        given(companyProfileI18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        companyProfileI18nService.deleteCompanyProfileI18n(companyProfileId, languageId);

        verify(companyProfileI18nRepository).findById(expectedKey);
        verify(companyProfileI18nRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteCompanyProfileI18nCannotFindEntity() {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(companyProfileId);
        id.setLanguageId(languageId);

        CompanyProfileI18nKey expectedKey = new CompanyProfileI18nKey();
        expectedKey.setCompanyProfileId(companyProfileId);
        expectedKey.setLanguageId(languageId);

        given(companyProfileI18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfileI18nService.deleteCompanyProfileI18n(companyProfileId, languageId));

        verify(companyProfileI18nRepository).findById(expectedKey);
        verify(companyProfileI18nRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated CompanyProfileI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfileI18n createSampleCompanyProfileI18nEntity() {
        CompanyProfileI18n entity = new CompanyProfileI18n();
        entity.setRecDeleted(true);
        entity.setName("name-value-1");
        entity.setAddressCity("addressCity-value-1");
        entity.setAddressRegion("addressRegion-value-1");
        entity.setAddressStreet("addressStreet-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setObjective("objective-value-1");

        return entity;
    }

    /**
     * Creates a populated CompanyProfileI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfileI18n createAnotherCompanyProfileI18nEntity() {
        CompanyProfileI18n entity = new CompanyProfileI18n();
        entity.setRecDeleted(false);
        entity.setName("name-value-2");
        entity.setAddressCity("addressCity-value-2");
        entity.setAddressRegion("addressRegion-value-2");
        entity.setAddressStreet("addressStreet-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setObjective("objective-value-2");

        return entity;
    }

    /**
     * Creates a populated CompanyProfileI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileI18nDto createSampleCompanyProfileI18nDto() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();
        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecDeleted(true);
        dto.setName("name-value-1");
        dto.setAddressCity("addressCity-value-1");
        dto.setAddressRegion("addressRegion-value-1");
        dto.setAddressStreet("addressStreet-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setObjective("objective-value-1");

        return dto;
    }

    /**
     * Creates a populated CompanyProfileI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileI18nDto createAnotherCompanyProfileI18nDto() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();
        CompanyProfileI18nKey id = new CompanyProfileI18nKey();
        id.setCompanyProfileId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecDeleted(false);
        dto.setName("name-value-2");
        dto.setAddressCity("addressCity-value-2");
        dto.setAddressRegion("addressRegion-value-2");
        dto.setAddressStreet("addressStreet-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setObjective("objective-value-2");

        return dto;
    }

    /**
     * Creates a populated CompanyProfileI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileI18nDto createPatchCompanyProfileI18nDto() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();
        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecDeleted(true);
        dto.setName("name-value-3");
        dto.setAddressCity("addressCity-value-3");
        dto.setAddressRegion("addressRegion-value-3");
        dto.setAddressStreet("addressStreet-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setObjective("objective-value-3");

        return dto;
    }

}
