package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ChAppUserContactI18n;
import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.repository.ChAppUserContactI18nRepository;
import gr.knowledge.pepTest.mapper.ChAppUserContactI18nMapper;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nKey;
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
class ChAppUserContactI18nServiceImplTest {

    @Mock
    private ChAppUserContactI18nRepository chAppUserContactI18nRepository;

    @Mock
    private ChAppUserContactI18nMapper chAppUserContactI18nMapper;

    @InjectMocks
    private ChAppUserContactI18nServiceImpl chAppUserContactI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ChAppUserContactI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ChAppUserContactI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllChAppUserContactI18nsIsCalled() {
        List<ChAppUserContactI18n> entityList = List.of(createSampleChAppUserContactI18nEntity(), createAnotherChAppUserContactI18nEntity());
        List<ChAppUserContactI18nDto> dtoList = List.of(createSampleChAppUserContactI18nDto(), createAnotherChAppUserContactI18nDto());

        given(chAppUserContactI18nRepository.findAll()).willReturn(entityList);
        given(chAppUserContactI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ChAppUserContactI18nDto> result = chAppUserContactI18nService.getAllChAppUserContactI18ns();

        assertSame(dtoList, result);
        verify(chAppUserContactI18nRepository).findAll();
        verify(chAppUserContactI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetChAppUserContactI18nByIdIsCalled() {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        id.setChAppUserContactId(chAppUserContactId);
        id.setLanguageId(languageId);

        ChAppUserContactI18n chAppUserContactI18n = createSampleChAppUserContactI18nEntity();
        ChAppUserContactI18nDto chAppUserContactI18nDto = createSampleChAppUserContactI18nDto();

        given(chAppUserContactI18nRepository.findById(id)).willReturn(Optional.of(chAppUserContactI18n));
        given(chAppUserContactI18nMapper.toDTO(chAppUserContactI18n)).willReturn(chAppUserContactI18nDto);

        ChAppUserContactI18nDto result = chAppUserContactI18nService.getChAppUserContactI18nById(chAppUserContactId, languageId);

        assertSame(chAppUserContactI18nDto, result);
        verify(chAppUserContactI18nRepository).findById(id);
        verify(chAppUserContactI18nMapper).toDTO(chAppUserContactI18n);
    }

    @Test
    void shouldThrowWhenGetChAppUserContactI18nByIdCannotFindEntity() {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        id.setChAppUserContactId(chAppUserContactId);
        id.setLanguageId(languageId);

        given(chAppUserContactI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chAppUserContactI18nService.getChAppUserContactI18nById(chAppUserContactId, languageId));

        verify(chAppUserContactI18nRepository).findById(id);
        verify(chAppUserContactI18nMapper, never()).toDTO(any(ChAppUserContactI18n.class));
    }

    @Test
    void shouldCreateChAppUserContactI18nWhenCreateChAppUserContactI18nIsCalled() {
        ChAppUserContactI18nDto requestDto = createSampleChAppUserContactI18nDto();
        ChAppUserContactI18n mappedEntity = createSampleChAppUserContactI18nEntity();
        ChAppUserContactI18n savedEntity = createAnotherChAppUserContactI18nEntity();
        ChAppUserContactI18nDto responseDto = createAnotherChAppUserContactI18nDto();

        given(chAppUserContactI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(chAppUserContactI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(chAppUserContactI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChAppUserContactI18nDto result = chAppUserContactI18nService.createChAppUserContactI18n(requestDto);

        assertSame(responseDto, result);
        verify(chAppUserContactI18nMapper).toEntity(requestDto);
        verify(chAppUserContactI18nRepository).save(mappedEntity);
        verify(chAppUserContactI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateChAppUserContactI18nWhenEntityExists() {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        id.setChAppUserContactId(chAppUserContactId);
        id.setLanguageId(languageId);

        ChAppUserContactI18nDto requestDto = createPatchChAppUserContactI18nDto();
        ChAppUserContactI18n existingEntity = createSampleChAppUserContactI18nEntity();
        ChAppUserContactI18n savedEntity = createAnotherChAppUserContactI18nEntity();
        ChAppUserContactI18nDto responseDto = createAnotherChAppUserContactI18nDto();

        given(chAppUserContactI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(chAppUserContactI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(chAppUserContactI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChAppUserContactI18nDto result = chAppUserContactI18nService.updateChAppUserContactI18n(chAppUserContactId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(chAppUserContactI18nRepository).findById(id);
        verify(chAppUserContactI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(chAppUserContactI18nRepository).save(existingEntity);
        verify(chAppUserContactI18nMapper).toDTO(savedEntity);
        verify(chAppUserContactI18nMapper, never()).toEntity(any(ChAppUserContactI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateChAppUserContactI18nCannotFindEntity() {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        id.setChAppUserContactId(chAppUserContactId);
        id.setLanguageId(languageId);

        ChAppUserContactI18nDto requestDto = createPatchChAppUserContactI18nDto();

        given(chAppUserContactI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chAppUserContactI18nService.updateChAppUserContactI18n(chAppUserContactId, languageId, requestDto));

        verify(chAppUserContactI18nRepository).findById(id);
        verify(chAppUserContactI18nMapper, never()).partialUpdate(any(), any());
        verify(chAppUserContactI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteChAppUserContactI18nWhenEntityExists() {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        id.setChAppUserContactId(chAppUserContactId);
        id.setLanguageId(languageId);

        ChAppUserContactI18n existingEntity = createSampleChAppUserContactI18nEntity();

        given(chAppUserContactI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        chAppUserContactI18nService.deleteChAppUserContactI18n(chAppUserContactId, languageId);

        verify(chAppUserContactI18nRepository).findById(id);
        verify(chAppUserContactI18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteChAppUserContactI18nCannotFindEntity() {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nKey id = new ChAppUserContactI18nKey();
        id.setChAppUserContactId(chAppUserContactId);
        id.setLanguageId(languageId);

        given(chAppUserContactI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chAppUserContactI18nService.deleteChAppUserContactI18n(chAppUserContactId, languageId));

        verify(chAppUserContactI18nRepository).findById(id);
        verify(chAppUserContactI18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ChAppUserContactI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContactI18n createSampleChAppUserContactI18nEntity() {
        ChAppUserContactI18n entity = new ChAppUserContactI18n();
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setCity("city-value-1");
        entity.setStreet("street-value-1");
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated ChAppUserContactI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContactI18n createAnotherChAppUserContactI18nEntity() {
        ChAppUserContactI18n entity = new ChAppUserContactI18n();
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setCity("city-value-2");
        entity.setStreet("street-value-2");
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ChAppUserContactI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactI18nDto createSampleChAppUserContactI18nDto() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setCity("city-value-1");
        dto.setStreet("street-value-1");
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactI18nDto createAnotherChAppUserContactI18nDto() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setCity("city-value-2");
        dto.setStreet("street-value-2");
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactI18nDto createPatchChAppUserContactI18nDto() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setCity("city-value-3");
        dto.setStreet("street-value-3");
        dto.setRecdeleted(true);

        return dto;
    }

}
