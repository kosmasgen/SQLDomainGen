package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.BgPoi;
import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.repository.BgPoiRepository;
import gr.knowledge.pepTest.mapper.BgPoiMapper;
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
class BgPoiServiceImplTest {

    @Mock
    private BgPoiRepository bgPoiRepository;

    @Mock
    private BgPoiMapper bgPoiMapper;

    @InjectMocks
    private BgPoiServiceImpl bgPoiService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for BgPoi.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("BgPoi", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllBgPoisIsCalled() {
        List<BgPoi> entityList = List.of(createSampleBgPoiEntity(), createAnotherBgPoiEntity());
        List<BgPoiDto> dtoList = List.of(createSampleBgPoiDto(), createAnotherBgPoiDto());

        given(bgPoiRepository.findAll()).willReturn(entityList);
        given(bgPoiMapper.toDTOList(entityList)).willReturn(dtoList);

        List<BgPoiDto> result = bgPoiService.getAllBgPois();

        assertSame(dtoList, result);
        verify(bgPoiRepository).findAll();
        verify(bgPoiMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetBgPoiByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoi bgPoi = createSampleBgPoiEntity();
        BgPoiDto bgPoiDto = createSampleBgPoiDto();

        given(bgPoiRepository.findById(id)).willReturn(Optional.of(bgPoi));
        given(bgPoiMapper.toDTO(bgPoi)).willReturn(bgPoiDto);

        BgPoiDto result = bgPoiService.getBgPoiById(id);

        assertSame(bgPoiDto, result);
        verify(bgPoiRepository).findById(id);
        verify(bgPoiMapper).toDTO(bgPoi);
    }

    @Test
    void shouldThrowWhenGetBgPoiByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(bgPoiRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> bgPoiService.getBgPoiById(id));

        verify(bgPoiRepository).findById(id);
        verify(bgPoiMapper, never()).toDTO(any(BgPoi.class));
    }

    @Test
    void shouldCreateBgPoiWhenCreateBgPoiIsCalled() {
        BgPoiDto requestDto = createSampleBgPoiDto();
        BgPoi mappedEntity = createSampleBgPoiEntity();
        BgPoi savedEntity = createAnotherBgPoiEntity();
        BgPoiDto responseDto = createAnotherBgPoiDto();

        given(bgPoiMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(bgPoiRepository.save(mappedEntity)).willReturn(savedEntity);
        given(bgPoiMapper.toDTO(savedEntity)).willReturn(responseDto);

        BgPoiDto result = bgPoiService.createBgPoi(requestDto);

        assertSame(responseDto, result);
        verify(bgPoiMapper).toEntity(requestDto);
        verify(bgPoiRepository).save(mappedEntity);
        verify(bgPoiMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateBgPoiWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoiDto requestDto = createPatchBgPoiDto();
        BgPoi existingEntity = createSampleBgPoiEntity();
        BgPoi savedEntity = createAnotherBgPoiEntity();
        BgPoiDto responseDto = createAnotherBgPoiDto();

        given(bgPoiRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(bgPoiRepository.save(existingEntity)).willReturn(savedEntity);
        given(bgPoiMapper.toDTO(savedEntity)).willReturn(responseDto);

        BgPoiDto result = bgPoiService.updateBgPoi(id, requestDto);

        assertSame(responseDto, result);
        verify(bgPoiRepository).findById(id);
        verify(bgPoiMapper).partialUpdate(existingEntity, requestDto);
        verify(bgPoiRepository).save(existingEntity);
        verify(bgPoiMapper).toDTO(savedEntity);
        verify(bgPoiMapper, never()).toEntity(any(BgPoiDto.class));
    }

    @Test
    void shouldThrowWhenUpdateBgPoiCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoiDto requestDto = createPatchBgPoiDto();

        given(bgPoiRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> bgPoiService.updateBgPoi(id, requestDto));

        verify(bgPoiRepository).findById(id);
        verify(bgPoiMapper, never()).partialUpdate(any(), any());
        verify(bgPoiRepository, never()).save(any());
    }

    @Test
    void shouldDeleteBgPoiWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BgPoi existingEntity = createSampleBgPoiEntity();

        given(bgPoiRepository.findById(id)).willReturn(Optional.of(existingEntity));

        bgPoiService.deleteBgPoi(id);

        verify(bgPoiRepository).findById(id);
        verify(bgPoiRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteBgPoiCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(bgPoiRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> bgPoiService.deleteBgPoi(id));

        verify(bgPoiRepository).findById(id);
        verify(bgPoiRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated BgPoi fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BgPoi createSampleBgPoiEntity() {
        BgPoi entity = new BgPoi();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setLatitude("latitude-value-1");
        entity.setLongitude("longitude-value-1");

        return entity;
    }

    /**
     * Creates a populated BgPoi fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BgPoi createAnotherBgPoiEntity() {
        BgPoi entity = new BgPoi();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setLatitude("latitude-value-2");
        entity.setLongitude("longitude-value-2");

        return entity;
    }

    /**
     * Creates a populated BgPoiDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BgPoiDto createSampleBgPoiDto() {
        BgPoiDto dto = new BgPoiDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setLatitude("latitude-value-1");
        dto.setLongitude("longitude-value-1");

        return dto;
    }

    /**
     * Creates a populated BgPoiDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BgPoiDto createAnotherBgPoiDto() {
        BgPoiDto dto = new BgPoiDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setLatitude("latitude-value-2");
        dto.setLongitude("longitude-value-2");

        return dto;
    }

    /**
     * Creates a populated BgPoiDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BgPoiDto createPatchBgPoiDto() {
        BgPoiDto dto = new BgPoiDto();
        dto.setChamberId(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setLatitude("latitude-value-3");
        dto.setLongitude("longitude-value-3");

        return dto;
    }

}
