package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.BgPoiI18n;
import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.BgPoiI18nRepository;
import gr.knowledge.pepTest.mapper.BgPoiI18nMapper;
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
class BgPoiI18nServiceImplTest {

    @Mock
    private BgPoiI18nRepository bgPoiI18nRepository;

    @Mock
    private BgPoiI18nMapper bgPoiI18nMapper;

    @InjectMocks
    private BgPoiI18nServiceImpl bgPoiI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for BgPoiI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("BgPoiI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllBgPoiI18nsIsCalled() {
        List<BgPoiI18n> entityList = List.of(createSampleBgPoiI18nEntity(), createAnotherBgPoiI18nEntity());
        List<BgPoiI18nDto> dtoList = List.of(createSampleBgPoiI18nDto(), createAnotherBgPoiI18nDto());

        given(bgPoiI18nRepository.findAll()).willReturn(entityList);
        given(bgPoiI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<BgPoiI18nDto> result = bgPoiI18nService.getAllBgPoiI18ns();

        assertSame(dtoList, result);
        verify(bgPoiI18nRepository).findAll();
        verify(bgPoiI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetBgPoiI18nByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoiI18n bgPoiI18n = createSampleBgPoiI18nEntity();
        BgPoiI18nDto bgPoiI18nDto = createSampleBgPoiI18nDto();

        given(bgPoiI18nRepository.findById(id)).willReturn(Optional.of(bgPoiI18n));
        given(bgPoiI18nMapper.toDTO(bgPoiI18n)).willReturn(bgPoiI18nDto);

        BgPoiI18nDto result = bgPoiI18nService.getBgPoiI18nById(id);

        assertSame(bgPoiI18nDto, result);
        verify(bgPoiI18nRepository).findById(id);
        verify(bgPoiI18nMapper).toDTO(bgPoiI18n);
    }

    @Test
    void shouldThrowWhenGetBgPoiI18nByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(bgPoiI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> bgPoiI18nService.getBgPoiI18nById(id));

        verify(bgPoiI18nRepository).findById(id);
        verify(bgPoiI18nMapper, never()).toDTO(any(BgPoiI18n.class));
    }

    @Test
    void shouldCreateBgPoiI18nWhenCreateBgPoiI18nIsCalled() {
        BgPoiI18nDto requestDto = createSampleBgPoiI18nDto();
        BgPoiI18n mappedEntity = createSampleBgPoiI18nEntity();
        BgPoiI18n savedEntity = createAnotherBgPoiI18nEntity();
        BgPoiI18nDto responseDto = createAnotherBgPoiI18nDto();

        given(bgPoiI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(bgPoiI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(bgPoiI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        BgPoiI18nDto result = bgPoiI18nService.createBgPoiI18n(requestDto);

        assertSame(responseDto, result);
        verify(bgPoiI18nMapper).toEntity(requestDto);
        verify(bgPoiI18nRepository).save(mappedEntity);
        verify(bgPoiI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateBgPoiI18nWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoiI18nDto requestDto = createPatchBgPoiI18nDto();
        BgPoiI18n existingEntity = createSampleBgPoiI18nEntity();
        BgPoiI18n savedEntity = createAnotherBgPoiI18nEntity();
        BgPoiI18nDto responseDto = createAnotherBgPoiI18nDto();

        given(bgPoiI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(bgPoiI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(bgPoiI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        BgPoiI18nDto result = bgPoiI18nService.updateBgPoiI18n(id, requestDto);

        assertSame(responseDto, result);
        verify(bgPoiI18nRepository).findById(id);
        verify(bgPoiI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(bgPoiI18nRepository).save(existingEntity);
        verify(bgPoiI18nMapper).toDTO(savedEntity);
        verify(bgPoiI18nMapper, never()).toEntity(any(BgPoiI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateBgPoiI18nCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoiI18nDto requestDto = createPatchBgPoiI18nDto();

        given(bgPoiI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> bgPoiI18nService.updateBgPoiI18n(id, requestDto));

        verify(bgPoiI18nRepository).findById(id);
        verify(bgPoiI18nMapper, never()).partialUpdate(any(), any());
        verify(bgPoiI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteBgPoiI18nWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoiI18n existingEntity = createSampleBgPoiI18nEntity();

        given(bgPoiI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        bgPoiI18nService.deleteBgPoiI18n(id);

        verify(bgPoiI18nRepository).findById(id);
        verify(bgPoiI18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteBgPoiI18nCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(bgPoiI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> bgPoiI18nService.deleteBgPoiI18n(id));

        verify(bgPoiI18nRepository).findById(id);
        verify(bgPoiI18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated BgPoiI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BgPoiI18n createSampleBgPoiI18nEntity() {
        BgPoiI18n entity = new BgPoiI18n();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setTitle("title-value-1");

        return entity;
    }

    /**
     * Creates a populated BgPoiI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BgPoiI18n createAnotherBgPoiI18nEntity() {
        BgPoiI18n entity = new BgPoiI18n();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setTitle("title-value-2");

        return entity;
    }

    /**
     * Creates a populated BgPoiI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BgPoiI18nDto createSampleBgPoiI18nDto() {
        BgPoiI18nDto dto = new BgPoiI18nDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setTitle("title-value-1");
        dto.setPoi(new BgPoiDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

    /**
     * Creates a populated BgPoiI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BgPoiI18nDto createAnotherBgPoiI18nDto() {
        BgPoiI18nDto dto = new BgPoiI18nDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setTitle("title-value-2");
        dto.setPoi(new BgPoiDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

    /**
     * Creates a populated BgPoiI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BgPoiI18nDto createPatchBgPoiI18nDto() {
        BgPoiI18nDto dto = new BgPoiI18nDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setTitle("title-value-3");
        dto.setPoi(new BgPoiDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

}
