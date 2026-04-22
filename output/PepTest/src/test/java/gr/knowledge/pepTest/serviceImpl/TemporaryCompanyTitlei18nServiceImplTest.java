package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.TemporaryCompanyTitlei18n;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.TemporaryCompanyTitlei18nRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitlei18nMapper;
import java.math.BigInteger;
import java.util.UUID;
import java.time.LocalDate;
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
class TemporaryCompanyTitlei18nServiceImplTest {

    @Mock
    private TemporaryCompanyTitlei18nRepository temporaryCompanyTitlei18nRepository;

    @Mock
    private TemporaryCompanyTitlei18nMapper temporaryCompanyTitlei18nMapper;

    @InjectMocks
    private TemporaryCompanyTitlei18nServiceImpl temporaryCompanyTitlei18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for TemporaryCompanyTitlei18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("TemporaryCompanyTitlei18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllTemporaryCompanyTitlei18nsIsCalled() {
        List<TemporaryCompanyTitlei18n> entityList = List.of(createSampleTemporaryCompanyTitlei18nEntity(), createAnotherTemporaryCompanyTitlei18nEntity());
        List<TemporaryCompanyTitlei18nDto> dtoList = List.of(createSampleTemporaryCompanyTitlei18nDto(), createAnotherTemporaryCompanyTitlei18nDto());

        given(temporaryCompanyTitlei18nRepository.findAll()).willReturn(entityList);
        given(temporaryCompanyTitlei18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<TemporaryCompanyTitlei18nDto> result = temporaryCompanyTitlei18nService.getAllTemporaryCompanyTitlei18ns();

        assertSame(dtoList, result);
        verify(temporaryCompanyTitlei18nRepository).findAll();
        verify(temporaryCompanyTitlei18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetTemporaryCompanyTitlei18nByIdIsCalled() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitlei18n temporaryCompanyTitlei18n = createSampleTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18nDto temporaryCompanyTitlei18nDto = createSampleTemporaryCompanyTitlei18nDto();

        given(temporaryCompanyTitlei18nRepository.findById(id)).willReturn(Optional.of(temporaryCompanyTitlei18n));
        given(temporaryCompanyTitlei18nMapper.toDTO(temporaryCompanyTitlei18n)).willReturn(temporaryCompanyTitlei18nDto);

        TemporaryCompanyTitlei18nDto result = temporaryCompanyTitlei18nService.getTemporaryCompanyTitlei18nById(id);

        assertSame(temporaryCompanyTitlei18nDto, result);
        verify(temporaryCompanyTitlei18nRepository).findById(id);
        verify(temporaryCompanyTitlei18nMapper).toDTO(temporaryCompanyTitlei18n);
    }

    @Test
    void shouldThrowWhenGetTemporaryCompanyTitlei18nByIdCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyTitlei18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyTitlei18nService.getTemporaryCompanyTitlei18nById(id));

        verify(temporaryCompanyTitlei18nRepository).findById(id);
        verify(temporaryCompanyTitlei18nMapper, never()).toDTO(any(TemporaryCompanyTitlei18n.class));
    }

    @Test
    void shouldCreateTemporaryCompanyTitlei18nWhenCreateTemporaryCompanyTitlei18nIsCalled() {
        TemporaryCompanyTitlei18nDto requestDto = createSampleTemporaryCompanyTitlei18nDto();
        TemporaryCompanyTitlei18n mappedEntity = createSampleTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18n savedEntity = createAnotherTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18nDto responseDto = createAnotherTemporaryCompanyTitlei18nDto();

        given(temporaryCompanyTitlei18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(temporaryCompanyTitlei18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(temporaryCompanyTitlei18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyTitlei18nDto result = temporaryCompanyTitlei18nService.createTemporaryCompanyTitlei18n(requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyTitlei18nMapper).toEntity(requestDto);
        verify(temporaryCompanyTitlei18nRepository).save(mappedEntity);
        verify(temporaryCompanyTitlei18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateTemporaryCompanyTitlei18nWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitlei18nDto requestDto = createPatchTemporaryCompanyTitlei18nDto();
        TemporaryCompanyTitlei18n existingEntity = createSampleTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18n savedEntity = createAnotherTemporaryCompanyTitlei18nEntity();
        TemporaryCompanyTitlei18nDto responseDto = createAnotherTemporaryCompanyTitlei18nDto();

        given(temporaryCompanyTitlei18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(temporaryCompanyTitlei18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(temporaryCompanyTitlei18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyTitlei18nDto result = temporaryCompanyTitlei18nService.updateTemporaryCompanyTitlei18n(id, requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyTitlei18nRepository).findById(id);
        verify(temporaryCompanyTitlei18nMapper).partialUpdate(existingEntity, requestDto);
        verify(temporaryCompanyTitlei18nRepository).save(existingEntity);
        verify(temporaryCompanyTitlei18nMapper).toDTO(savedEntity);
        verify(temporaryCompanyTitlei18nMapper, never()).toEntity(any(TemporaryCompanyTitlei18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateTemporaryCompanyTitlei18nCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitlei18nDto requestDto = createPatchTemporaryCompanyTitlei18nDto();

        given(temporaryCompanyTitlei18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyTitlei18nService.updateTemporaryCompanyTitlei18n(id, requestDto));

        verify(temporaryCompanyTitlei18nRepository).findById(id);
        verify(temporaryCompanyTitlei18nMapper, never()).partialUpdate(any(), any());
        verify(temporaryCompanyTitlei18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteTemporaryCompanyTitlei18nWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitlei18n existingEntity = createSampleTemporaryCompanyTitlei18nEntity();

        given(temporaryCompanyTitlei18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        temporaryCompanyTitlei18nService.deleteTemporaryCompanyTitlei18n(id);

        verify(temporaryCompanyTitlei18nRepository).findById(id);
        verify(temporaryCompanyTitlei18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteTemporaryCompanyTitlei18nCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyTitlei18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyTitlei18nService.deleteTemporaryCompanyTitlei18n(id));

        verify(temporaryCompanyTitlei18nRepository).findById(id);
        verify(temporaryCompanyTitlei18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitlei18n createSampleTemporaryCompanyTitlei18nEntity() {
        TemporaryCompanyTitlei18n entity = new TemporaryCompanyTitlei18n();
        entity.setId(new BigInteger("1"));
        entity.setVersion(new BigInteger("1"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(new BigInteger("1"));
        entity.setTitle("title-value-1");
        entity.setGemiId(new BigInteger("1"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 1));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitlei18n createAnotherTemporaryCompanyTitlei18nEntity() {
        TemporaryCompanyTitlei18n entity = new TemporaryCompanyTitlei18n();
        entity.setId(new BigInteger("2"));
        entity.setVersion(new BigInteger("2"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(new BigInteger("2"));
        entity.setTitle("title-value-2");
        entity.setGemiId(new BigInteger("2"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 2));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitlei18nDto createSampleTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();
        dto.setId(new BigInteger("1"));
        dto.setVersion(new BigInteger("1"));
        dto.setCompanyTitle(new TemporaryCompanyTitleDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLanguage(new LanguagesDto());
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("1"));
        dto.setTitle("title-value-1");
        dto.setGemiId(new BigInteger("1"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 1));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitlei18nDto createAnotherTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();
        dto.setId(new BigInteger("2"));
        dto.setVersion(new BigInteger("2"));
        dto.setCompanyTitle(new TemporaryCompanyTitleDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLanguage(new LanguagesDto());
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("2"));
        dto.setTitle("title-value-2");
        dto.setGemiId(new BigInteger("2"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 2));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitlei18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitlei18nDto createPatchTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();
        dto.setVersion(new BigInteger("3"));
        dto.setCompanyTitle(new TemporaryCompanyTitleDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLanguage(new LanguagesDto());
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(new BigInteger("3"));
        dto.setTitle("title-value-3");
        dto.setGemiId(new BigInteger("3"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 3));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 3));

        return dto;
    }

}
