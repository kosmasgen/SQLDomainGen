package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Professioni18n;
import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.Professioni18nRepository;
import gr.knowledge.pepTest.mapper.Professioni18nMapper;
import gr.knowledge.pepTest.entity.Professioni18nKey;
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
class Professioni18nServiceImplTest {

    @Mock
    private Professioni18nRepository professioni18nRepository;

    @Mock
    private Professioni18nMapper professioni18nMapper;

    @InjectMocks
    private Professioni18nServiceImpl professioni18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Professioni18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Professioni18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProfessioni18nsIsCalled() {
        List<Professioni18n> entityList = List.of(createSampleProfessioni18nEntity(), createAnotherProfessioni18nEntity());
        List<Professioni18nDto> dtoList = List.of(createSampleProfessioni18nDto(), createAnotherProfessioni18nDto());

        given(professioni18nRepository.findAll()).willReturn(entityList);
        given(professioni18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<Professioni18nDto> result = professioni18nService.getAllProfessioni18ns();

        assertSame(dtoList, result);
        verify(professioni18nRepository).findAll();
        verify(professioni18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProfessioni18nByIdIsCalled() {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(professionId);
        id.setLanguageId(languageId);

        Professioni18nKey expectedKey = new Professioni18nKey();
        expectedKey.setProfessionId(professionId);
        expectedKey.setLanguageId(languageId);

        Professioni18n professioni18n = createSampleProfessioni18nEntity();
        Professioni18nDto professioni18nDto = createSampleProfessioni18nDto();

        given(professioni18nRepository.findById(expectedKey)).willReturn(Optional.of(professioni18n));
        given(professioni18nMapper.toDTO(professioni18n)).willReturn(professioni18nDto);

        Professioni18nDto result = professioni18nService.getProfessioni18nById(professionId, languageId);

        assertSame(professioni18nDto, result);
        verify(professioni18nRepository).findById(expectedKey);
        verify(professioni18nMapper).toDTO(professioni18n);
    }

    @Test
    void shouldThrowWhenGetProfessioni18nByIdCannotFindEntity() {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(professionId);
        id.setLanguageId(languageId);

        Professioni18nKey expectedKey = new Professioni18nKey();
        expectedKey.setProfessionId(professionId);
        expectedKey.setLanguageId(languageId);

        given(professioni18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> professioni18nService.getProfessioni18nById(professionId, languageId));

        verify(professioni18nRepository).findById(expectedKey);
        verify(professioni18nMapper, never()).toDTO(any(Professioni18n.class));
    }

    @Test
    void shouldCreateProfessioni18nWhenCreateProfessioni18nIsCalled() {
        Professioni18nDto requestDto = createSampleProfessioni18nDto();
        Professioni18n mappedEntity = createSampleProfessioni18nEntity();
        Professioni18n savedEntity = createAnotherProfessioni18nEntity();
        Professioni18nDto responseDto = createAnotherProfessioni18nDto();

        given(professioni18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(professioni18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(professioni18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        Professioni18nDto result = professioni18nService.createProfessioni18n(requestDto);

        assertSame(responseDto, result);
        verify(professioni18nMapper).toEntity(requestDto);
        verify(professioni18nRepository).save(mappedEntity);
        verify(professioni18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProfessioni18nWhenEntityExists() {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(professionId);
        id.setLanguageId(languageId);

        Professioni18nKey expectedKey = new Professioni18nKey();
        expectedKey.setProfessionId(professionId);
        expectedKey.setLanguageId(languageId);

        Professioni18nDto requestDto = createPatchProfessioni18nDto();
        Professioni18n existingEntity = createSampleProfessioni18nEntity();
        Professioni18n savedEntity = createAnotherProfessioni18nEntity();
        Professioni18nDto responseDto = createAnotherProfessioni18nDto();

        given(professioni18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(professioni18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(professioni18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        Professioni18nDto result = professioni18nService.updateProfessioni18n(professionId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(professioni18nRepository).findById(expectedKey);
        verify(professioni18nMapper).partialUpdate(existingEntity, requestDto);
        verify(professioni18nRepository).save(existingEntity);
        verify(professioni18nMapper).toDTO(savedEntity);
        verify(professioni18nMapper, never()).toEntity(any(Professioni18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProfessioni18nCannotFindEntity() {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(professionId);
        id.setLanguageId(languageId);

        Professioni18nKey expectedKey = new Professioni18nKey();
        expectedKey.setProfessionId(professionId);
        expectedKey.setLanguageId(languageId);

        Professioni18nDto requestDto = createPatchProfessioni18nDto();

        given(professioni18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> professioni18nService.updateProfessioni18n(professionId, languageId, requestDto));

        verify(professioni18nRepository).findById(expectedKey);
        verify(professioni18nMapper, never()).partialUpdate(any(), any());
        verify(professioni18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProfessioni18nWhenEntityExists() {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(professionId);
        id.setLanguageId(languageId);

        Professioni18nKey expectedKey = new Professioni18nKey();
        expectedKey.setProfessionId(professionId);
        expectedKey.setLanguageId(languageId);

        Professioni18n existingEntity = createSampleProfessioni18nEntity();

        given(professioni18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        professioni18nService.deleteProfessioni18n(professionId, languageId);

        verify(professioni18nRepository).findById(expectedKey);
        verify(professioni18nRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteProfessioni18nCannotFindEntity() {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(professionId);
        id.setLanguageId(languageId);

        Professioni18nKey expectedKey = new Professioni18nKey();
        expectedKey.setProfessionId(professionId);
        expectedKey.setLanguageId(languageId);

        given(professioni18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> professioni18nService.deleteProfessioni18n(professionId, languageId));

        verify(professioni18nRepository).findById(expectedKey);
        verify(professioni18nRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated Professioni18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Professioni18n createSampleProfessioni18nEntity() {
        Professioni18n entity = new Professioni18n();
        entity.setRecdeleted(true);
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setChamberI18nId(1);

        return entity;
    }

    /**
     * Creates a populated Professioni18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Professioni18n createAnotherProfessioni18nEntity() {
        Professioni18n entity = new Professioni18n();
        entity.setRecdeleted(false);
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setChamberI18nId(2);

        return entity;
    }

    /**
     * Creates a populated Professioni18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Professioni18nDto createSampleProfessioni18nDto() {
        Professioni18nDto dto = new Professioni18nDto();
        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setProfession(new ProfessionDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setChamberI18nId(1);

        return dto;
    }

    /**
     * Creates a populated Professioni18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Professioni18nDto createAnotherProfessioni18nDto() {
        Professioni18nDto dto = new Professioni18nDto();
        Professioni18nKey id = new Professioni18nKey();
        id.setProfessionId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setProfession(new ProfessionDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(false);
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setChamberI18nId(2);

        return dto;
    }

    /**
     * Creates a populated Professioni18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private Professioni18nDto createPatchProfessioni18nDto() {
        Professioni18nDto dto = new Professioni18nDto();
        dto.setProfession(new ProfessionDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setChamberI18nId(3);

        return dto;
    }

}
