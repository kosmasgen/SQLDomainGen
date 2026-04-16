package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.MunicipalityI18n;
import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.repository.MunicipalityI18nRepository;
import gr.knowledge.pepTest.mapper.MunicipalityI18nMapper;
import gr.knowledge.pepTest.entity.MunicipalityI18nKey;
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
class MunicipalityI18nServiceImplTest {

    @Mock
    private MunicipalityI18nRepository municipalityI18nRepository;

    @Mock
    private MunicipalityI18nMapper municipalityI18nMapper;

    @InjectMocks
    private MunicipalityI18nServiceImpl municipalityI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for MunicipalityI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("MunicipalityI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllMunicipalityI18nsIsCalled() {
        List<MunicipalityI18n> entityList = List.of(createSampleMunicipalityI18nEntity(), createAnotherMunicipalityI18nEntity());
        List<MunicipalityI18nDto> dtoList = List.of(createSampleMunicipalityI18nDto(), createAnotherMunicipalityI18nDto());

        given(municipalityI18nRepository.findAll()).willReturn(entityList);
        given(municipalityI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<MunicipalityI18nDto> result = municipalityI18nService.getAllMunicipalityI18ns();

        assertSame(dtoList, result);
        verify(municipalityI18nRepository).findAll();
        verify(municipalityI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetMunicipalityI18nByIdIsCalled() {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        id.setMunicipalityId(municipalityId);
        id.setLanguageId(languageId);

        MunicipalityI18n municipalityI18n = createSampleMunicipalityI18nEntity();
        MunicipalityI18nDto municipalityI18nDto = createSampleMunicipalityI18nDto();

        given(municipalityI18nRepository.findById(id)).willReturn(Optional.of(municipalityI18n));
        given(municipalityI18nMapper.toDTO(municipalityI18n)).willReturn(municipalityI18nDto);

        MunicipalityI18nDto result = municipalityI18nService.getMunicipalityI18nById(municipalityId, languageId);

        assertSame(municipalityI18nDto, result);
        verify(municipalityI18nRepository).findById(id);
        verify(municipalityI18nMapper).toDTO(municipalityI18n);
    }

    @Test
    void shouldThrowWhenGetMunicipalityI18nByIdCannotFindEntity() {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        id.setMunicipalityId(municipalityId);
        id.setLanguageId(languageId);

        given(municipalityI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> municipalityI18nService.getMunicipalityI18nById(municipalityId, languageId));

        verify(municipalityI18nRepository).findById(id);
        verify(municipalityI18nMapper, never()).toDTO(any(MunicipalityI18n.class));
    }

    @Test
    void shouldCreateMunicipalityI18nWhenCreateMunicipalityI18nIsCalled() {
        MunicipalityI18nDto requestDto = createSampleMunicipalityI18nDto();
        MunicipalityI18n mappedEntity = createSampleMunicipalityI18nEntity();
        MunicipalityI18n savedEntity = createAnotherMunicipalityI18nEntity();
        MunicipalityI18nDto responseDto = createAnotherMunicipalityI18nDto();

        given(municipalityI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(municipalityI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(municipalityI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        MunicipalityI18nDto result = municipalityI18nService.createMunicipalityI18n(requestDto);

        assertSame(responseDto, result);
        verify(municipalityI18nMapper).toEntity(requestDto);
        verify(municipalityI18nRepository).save(mappedEntity);
        verify(municipalityI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateMunicipalityI18nWhenEntityExists() {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        id.setMunicipalityId(municipalityId);
        id.setLanguageId(languageId);

        MunicipalityI18nDto requestDto = createPatchMunicipalityI18nDto();
        MunicipalityI18n existingEntity = createSampleMunicipalityI18nEntity();
        MunicipalityI18n savedEntity = createAnotherMunicipalityI18nEntity();
        MunicipalityI18nDto responseDto = createAnotherMunicipalityI18nDto();

        given(municipalityI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(municipalityI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(municipalityI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        MunicipalityI18nDto result = municipalityI18nService.updateMunicipalityI18n(municipalityId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(municipalityI18nRepository).findById(id);
        verify(municipalityI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(municipalityI18nRepository).save(existingEntity);
        verify(municipalityI18nMapper).toDTO(savedEntity);
        verify(municipalityI18nMapper, never()).toEntity(any(MunicipalityI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateMunicipalityI18nCannotFindEntity() {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        id.setMunicipalityId(municipalityId);
        id.setLanguageId(languageId);

        MunicipalityI18nDto requestDto = createPatchMunicipalityI18nDto();

        given(municipalityI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> municipalityI18nService.updateMunicipalityI18n(municipalityId, languageId, requestDto));

        verify(municipalityI18nRepository).findById(id);
        verify(municipalityI18nMapper, never()).partialUpdate(any(), any());
        verify(municipalityI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteMunicipalityI18nWhenEntityExists() {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        id.setMunicipalityId(municipalityId);
        id.setLanguageId(languageId);

        MunicipalityI18n existingEntity = createSampleMunicipalityI18nEntity();

        given(municipalityI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        municipalityI18nService.deleteMunicipalityI18n(municipalityId, languageId);

        verify(municipalityI18nRepository).findById(id);
        verify(municipalityI18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteMunicipalityI18nCannotFindEntity() {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nKey id = new MunicipalityI18nKey();
        id.setMunicipalityId(municipalityId);
        id.setLanguageId(languageId);

        given(municipalityI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> municipalityI18nService.deleteMunicipalityI18n(municipalityId, languageId));

        verify(municipalityI18nRepository).findById(id);
        verify(municipalityI18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated MunicipalityI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private MunicipalityI18n createSampleMunicipalityI18nEntity() {
        MunicipalityI18n entity = new MunicipalityI18n();
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setChamberI18nId(1);

        return entity;
    }

    /**
     * Creates a populated MunicipalityI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private MunicipalityI18n createAnotherMunicipalityI18nEntity() {
        MunicipalityI18n entity = new MunicipalityI18n();
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setChamberI18nId(2);

        return entity;
    }

    /**
     * Creates a populated MunicipalityI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityI18nDto createSampleMunicipalityI18nDto() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(1);

        return dto;
    }

    /**
     * Creates a populated MunicipalityI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityI18nDto createAnotherMunicipalityI18nDto() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setChamberI18nId(2);

        return dto;
    }

    /**
     * Creates a populated MunicipalityI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityI18nDto createPatchMunicipalityI18nDto() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(3);

        return dto;
    }

}
