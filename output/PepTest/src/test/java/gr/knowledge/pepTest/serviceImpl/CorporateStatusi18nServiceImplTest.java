package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CorporateStatusi18n;
import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.repository.CorporateStatusi18nRepository;
import gr.knowledge.pepTest.mapper.CorporateStatusi18nMapper;
import gr.knowledge.pepTest.entity.CorporateStatusi18nKey;
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
class CorporateStatusi18nServiceImplTest {

    @Mock
    private CorporateStatusi18nRepository corporateStatusi18nRepository;

    @Mock
    private CorporateStatusi18nMapper corporateStatusi18nMapper;

    @InjectMocks
    private CorporateStatusi18nServiceImpl corporateStatusi18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CorporateStatusi18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CorporateStatusi18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCorporateStatusi18nsIsCalled() {
        List<CorporateStatusi18n> entityList = List.of(createSampleCorporateStatusi18nEntity(), createAnotherCorporateStatusi18nEntity());
        List<CorporateStatusi18nDto> dtoList = List.of(createSampleCorporateStatusi18nDto(), createAnotherCorporateStatusi18nDto());

        given(corporateStatusi18nRepository.findAll()).willReturn(entityList);
        given(corporateStatusi18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CorporateStatusi18nDto> result = corporateStatusi18nService.getAllCorporateStatusi18ns();

        assertSame(dtoList, result);
        verify(corporateStatusi18nRepository).findAll();
        verify(corporateStatusi18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCorporateStatusi18nByIdIsCalled() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setLanguageId(languageId);

        CorporateStatusi18n corporateStatusi18n = createSampleCorporateStatusi18nEntity();
        CorporateStatusi18nDto corporateStatusi18nDto = createSampleCorporateStatusi18nDto();

        given(corporateStatusi18nRepository.findById(id)).willReturn(Optional.of(corporateStatusi18n));
        given(corporateStatusi18nMapper.toDTO(corporateStatusi18n)).willReturn(corporateStatusi18nDto);

        CorporateStatusi18nDto result = corporateStatusi18nService.getCorporateStatusi18nById(corporateStatusId, languageId);

        assertSame(corporateStatusi18nDto, result);
        verify(corporateStatusi18nRepository).findById(id);
        verify(corporateStatusi18nMapper).toDTO(corporateStatusi18n);
    }

    @Test
    void shouldThrowWhenGetCorporateStatusi18nByIdCannotFindEntity() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setLanguageId(languageId);

        given(corporateStatusi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusi18nService.getCorporateStatusi18nById(corporateStatusId, languageId));

        verify(corporateStatusi18nRepository).findById(id);
        verify(corporateStatusi18nMapper, never()).toDTO(any(CorporateStatusi18n.class));
    }

    @Test
    void shouldCreateCorporateStatusi18nWhenCreateCorporateStatusi18nIsCalled() {
        CorporateStatusi18nDto requestDto = createSampleCorporateStatusi18nDto();
        CorporateStatusi18n mappedEntity = createSampleCorporateStatusi18nEntity();
        CorporateStatusi18n savedEntity = createAnotherCorporateStatusi18nEntity();
        CorporateStatusi18nDto responseDto = createAnotherCorporateStatusi18nDto();

        given(corporateStatusi18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(corporateStatusi18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(corporateStatusi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CorporateStatusi18nDto result = corporateStatusi18nService.createCorporateStatusi18n(requestDto);

        assertSame(responseDto, result);
        verify(corporateStatusi18nMapper).toEntity(requestDto);
        verify(corporateStatusi18nRepository).save(mappedEntity);
        verify(corporateStatusi18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCorporateStatusi18nWhenEntityExists() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setLanguageId(languageId);

        CorporateStatusi18nDto requestDto = createPatchCorporateStatusi18nDto();
        CorporateStatusi18n existingEntity = createSampleCorporateStatusi18nEntity();
        CorporateStatusi18n savedEntity = createAnotherCorporateStatusi18nEntity();
        CorporateStatusi18nDto responseDto = createAnotherCorporateStatusi18nDto();

        given(corporateStatusi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(corporateStatusi18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(corporateStatusi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CorporateStatusi18nDto result = corporateStatusi18nService.updateCorporateStatusi18n(corporateStatusId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(corporateStatusi18nRepository).findById(id);
        verify(corporateStatusi18nMapper).partialUpdate(existingEntity, requestDto);
        verify(corporateStatusi18nRepository).save(existingEntity);
        verify(corporateStatusi18nMapper).toDTO(savedEntity);
        verify(corporateStatusi18nMapper, never()).toEntity(any(CorporateStatusi18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCorporateStatusi18nCannotFindEntity() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setLanguageId(languageId);

        CorporateStatusi18nDto requestDto = createPatchCorporateStatusi18nDto();

        given(corporateStatusi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusi18nService.updateCorporateStatusi18n(corporateStatusId, languageId, requestDto));

        verify(corporateStatusi18nRepository).findById(id);
        verify(corporateStatusi18nMapper, never()).partialUpdate(any(), any());
        verify(corporateStatusi18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCorporateStatusi18nWhenEntityExists() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setLanguageId(languageId);

        CorporateStatusi18n existingEntity = createSampleCorporateStatusi18nEntity();

        given(corporateStatusi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        corporateStatusi18nService.deleteCorporateStatusi18n(corporateStatusId, languageId);

        verify(corporateStatusi18nRepository).findById(id);
        verify(corporateStatusi18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCorporateStatusi18nCannotFindEntity() {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        id.setCorporateStatusId(corporateStatusId);
        id.setLanguageId(languageId);

        given(corporateStatusi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusi18nService.deleteCorporateStatusi18n(corporateStatusId, languageId));

        verify(corporateStatusi18nRepository).findById(id);
        verify(corporateStatusi18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CorporateStatusi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusi18n createSampleCorporateStatusi18nEntity() {
        CorporateStatusi18n entity = new CorporateStatusi18n();
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setChamberI18nId(1);
        entity.setGroupedDescription("groupedDescription-value-1");

        return entity;
    }

    /**
     * Creates a populated CorporateStatusi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatusi18n createAnotherCorporateStatusi18nEntity() {
        CorporateStatusi18n entity = new CorporateStatusi18n();
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setChamberI18nId(2);
        entity.setGroupedDescription("groupedDescription-value-2");

        return entity;
    }

    /**
     * Creates a populated CorporateStatusi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusi18nDto createSampleCorporateStatusi18nDto() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(1);
        dto.setGroupedDescription("groupedDescription-value-1");

        return dto;
    }

    /**
     * Creates a populated CorporateStatusi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusi18nDto createAnotherCorporateStatusi18nDto() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setChamberI18nId(2);
        dto.setGroupedDescription("groupedDescription-value-2");

        return dto;
    }

    /**
     * Creates a populated CorporateStatusi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusi18nDto createPatchCorporateStatusi18nDto() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(3);
        dto.setGroupedDescription("groupedDescription-value-3");

        return dto;
    }

}
