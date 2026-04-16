package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ProfessionKindi18n;
import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.repository.ProfessionKindi18nRepository;
import gr.knowledge.pepTest.mapper.ProfessionKindi18nMapper;
import gr.knowledge.pepTest.entity.ProfessionKindi18nKey;
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
class ProfessionKindi18nServiceImplTest {

    @Mock
    private ProfessionKindi18nRepository professionKindi18nRepository;

    @Mock
    private ProfessionKindi18nMapper professionKindi18nMapper;

    @InjectMocks
    private ProfessionKindi18nServiceImpl professionKindi18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ProfessionKindi18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ProfessionKindi18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProfessionKindi18nsIsCalled() {
        List<ProfessionKindi18n> entityList = List.of(createSampleProfessionKindi18nEntity(), createAnotherProfessionKindi18nEntity());
        List<ProfessionKindi18nDto> dtoList = List.of(createSampleProfessionKindi18nDto(), createAnotherProfessionKindi18nDto());

        given(professionKindi18nRepository.findAll()).willReturn(entityList);
        given(professionKindi18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ProfessionKindi18nDto> result = professionKindi18nService.getAllProfessionKindi18ns();

        assertSame(dtoList, result);
        verify(professionKindi18nRepository).findAll();
        verify(professionKindi18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProfessionKindi18nByIdIsCalled() {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        id.setProfessionKindId(professionKindId);
        id.setLanguageId(languageId);

        ProfessionKindi18n professionKindi18n = createSampleProfessionKindi18nEntity();
        ProfessionKindi18nDto professionKindi18nDto = createSampleProfessionKindi18nDto();

        given(professionKindi18nRepository.findById(id)).willReturn(Optional.of(professionKindi18n));
        given(professionKindi18nMapper.toDTO(professionKindi18n)).willReturn(professionKindi18nDto);

        ProfessionKindi18nDto result = professionKindi18nService.getProfessionKindi18nById(professionKindId, languageId);

        assertSame(professionKindi18nDto, result);
        verify(professionKindi18nRepository).findById(id);
        verify(professionKindi18nMapper).toDTO(professionKindi18n);
    }

    @Test
    void shouldThrowWhenGetProfessionKindi18nByIdCannotFindEntity() {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        id.setProfessionKindId(professionKindId);
        id.setLanguageId(languageId);

        given(professionKindi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionKindi18nService.getProfessionKindi18nById(professionKindId, languageId));

        verify(professionKindi18nRepository).findById(id);
        verify(professionKindi18nMapper, never()).toDTO(any(ProfessionKindi18n.class));
    }

    @Test
    void shouldCreateProfessionKindi18nWhenCreateProfessionKindi18nIsCalled() {
        ProfessionKindi18nDto requestDto = createSampleProfessionKindi18nDto();
        ProfessionKindi18n mappedEntity = createSampleProfessionKindi18nEntity();
        ProfessionKindi18n savedEntity = createAnotherProfessionKindi18nEntity();
        ProfessionKindi18nDto responseDto = createAnotherProfessionKindi18nDto();

        given(professionKindi18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(professionKindi18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(professionKindi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionKindi18nDto result = professionKindi18nService.createProfessionKindi18n(requestDto);

        assertSame(responseDto, result);
        verify(professionKindi18nMapper).toEntity(requestDto);
        verify(professionKindi18nRepository).save(mappedEntity);
        verify(professionKindi18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProfessionKindi18nWhenEntityExists() {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        id.setProfessionKindId(professionKindId);
        id.setLanguageId(languageId);

        ProfessionKindi18nDto requestDto = createPatchProfessionKindi18nDto();
        ProfessionKindi18n existingEntity = createSampleProfessionKindi18nEntity();
        ProfessionKindi18n savedEntity = createAnotherProfessionKindi18nEntity();
        ProfessionKindi18nDto responseDto = createAnotherProfessionKindi18nDto();

        given(professionKindi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(professionKindi18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(professionKindi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionKindi18nDto result = professionKindi18nService.updateProfessionKindi18n(professionKindId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(professionKindi18nRepository).findById(id);
        verify(professionKindi18nMapper).partialUpdate(existingEntity, requestDto);
        verify(professionKindi18nRepository).save(existingEntity);
        verify(professionKindi18nMapper).toDTO(savedEntity);
        verify(professionKindi18nMapper, never()).toEntity(any(ProfessionKindi18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProfessionKindi18nCannotFindEntity() {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        id.setProfessionKindId(professionKindId);
        id.setLanguageId(languageId);

        ProfessionKindi18nDto requestDto = createPatchProfessionKindi18nDto();

        given(professionKindi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionKindi18nService.updateProfessionKindi18n(professionKindId, languageId, requestDto));

        verify(professionKindi18nRepository).findById(id);
        verify(professionKindi18nMapper, never()).partialUpdate(any(), any());
        verify(professionKindi18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProfessionKindi18nWhenEntityExists() {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        id.setProfessionKindId(professionKindId);
        id.setLanguageId(languageId);

        ProfessionKindi18n existingEntity = createSampleProfessionKindi18nEntity();

        given(professionKindi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        professionKindi18nService.deleteProfessionKindi18n(professionKindId, languageId);

        verify(professionKindi18nRepository).findById(id);
        verify(professionKindi18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteProfessionKindi18nCannotFindEntity() {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nKey id = new ProfessionKindi18nKey();
        id.setProfessionKindId(professionKindId);
        id.setLanguageId(languageId);

        given(professionKindi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionKindi18nService.deleteProfessionKindi18n(professionKindId, languageId));

        verify(professionKindi18nRepository).findById(id);
        verify(professionKindi18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ProfessionKindi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKindi18n createSampleProfessionKindi18nEntity() {
        ProfessionKindi18n entity = new ProfessionKindi18n();
        entity.setRecdeleted(true);
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setChamberI18nId(1);

        return entity;
    }

    /**
     * Creates a populated ProfessionKindi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKindi18n createAnotherProfessionKindi18nEntity() {
        ProfessionKindi18n entity = new ProfessionKindi18n();
        entity.setRecdeleted(false);
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setChamberI18nId(2);

        return entity;
    }

    /**
     * Creates a populated ProfessionKindi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindi18nDto createSampleProfessionKindi18nDto() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();
        dto.setRecdeleted(true);
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setChamberI18nId(1);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindi18nDto createAnotherProfessionKindi18nDto() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();
        dto.setRecdeleted(false);
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setChamberI18nId(2);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindi18nDto createPatchProfessionKindi18nDto() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();
        dto.setRecdeleted(true);
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setChamberI18nId(3);

        return dto;
    }

}
