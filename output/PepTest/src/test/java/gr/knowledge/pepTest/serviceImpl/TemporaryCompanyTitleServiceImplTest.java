package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.TemporaryCompanyTitle;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.repository.TemporaryCompanyTitleRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitleMapper;
import java.math.BigInteger;
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
class TemporaryCompanyTitleServiceImplTest {

    @Mock
    private TemporaryCompanyTitleRepository temporaryCompanyTitleRepository;

    @Mock
    private TemporaryCompanyTitleMapper temporaryCompanyTitleMapper;

    @InjectMocks
    private TemporaryCompanyTitleServiceImpl temporaryCompanyTitleService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for TemporaryCompanyTitle.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("TemporaryCompanyTitle", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllTemporaryCompanyTitlesIsCalled() {
        List<TemporaryCompanyTitle> entityList = List.of(createSampleTemporaryCompanyTitleEntity(), createAnotherTemporaryCompanyTitleEntity());
        List<TemporaryCompanyTitleDto> dtoList = List.of(createSampleTemporaryCompanyTitleDto(), createAnotherTemporaryCompanyTitleDto());

        given(temporaryCompanyTitleRepository.findAll()).willReturn(entityList);
        given(temporaryCompanyTitleMapper.toDTOList(entityList)).willReturn(dtoList);

        List<TemporaryCompanyTitleDto> result = temporaryCompanyTitleService.getAllTemporaryCompanyTitles();

        assertSame(dtoList, result);
        verify(temporaryCompanyTitleRepository).findAll();
        verify(temporaryCompanyTitleMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetTemporaryCompanyTitleByIdIsCalled() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitle temporaryCompanyTitle = createSampleTemporaryCompanyTitleEntity();
        TemporaryCompanyTitleDto temporaryCompanyTitleDto = createSampleTemporaryCompanyTitleDto();

        given(temporaryCompanyTitleRepository.findById(id)).willReturn(Optional.of(temporaryCompanyTitle));
        given(temporaryCompanyTitleMapper.toDTO(temporaryCompanyTitle)).willReturn(temporaryCompanyTitleDto);

        TemporaryCompanyTitleDto result = temporaryCompanyTitleService.getTemporaryCompanyTitleById(id);

        assertSame(temporaryCompanyTitleDto, result);
        verify(temporaryCompanyTitleRepository).findById(id);
        verify(temporaryCompanyTitleMapper).toDTO(temporaryCompanyTitle);
    }

    @Test
    void shouldThrowWhenGetTemporaryCompanyTitleByIdCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyTitleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyTitleService.getTemporaryCompanyTitleById(id));

        verify(temporaryCompanyTitleRepository).findById(id);
        verify(temporaryCompanyTitleMapper, never()).toDTO(any(TemporaryCompanyTitle.class));
    }

    @Test
    void shouldCreateTemporaryCompanyTitleWhenCreateTemporaryCompanyTitleIsCalled() {
        TemporaryCompanyTitleDto requestDto = createSampleTemporaryCompanyTitleDto();
        TemporaryCompanyTitle mappedEntity = createSampleTemporaryCompanyTitleEntity();
        TemporaryCompanyTitle savedEntity = createAnotherTemporaryCompanyTitleEntity();
        TemporaryCompanyTitleDto responseDto = createAnotherTemporaryCompanyTitleDto();

        given(temporaryCompanyTitleMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(temporaryCompanyTitleRepository.save(mappedEntity)).willReturn(savedEntity);
        given(temporaryCompanyTitleMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyTitleDto result = temporaryCompanyTitleService.createTemporaryCompanyTitle(requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyTitleMapper).toEntity(requestDto);
        verify(temporaryCompanyTitleRepository).save(mappedEntity);
        verify(temporaryCompanyTitleMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateTemporaryCompanyTitleWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitleDto requestDto = createPatchTemporaryCompanyTitleDto();
        TemporaryCompanyTitle existingEntity = createSampleTemporaryCompanyTitleEntity();
        TemporaryCompanyTitle savedEntity = createAnotherTemporaryCompanyTitleEntity();
        TemporaryCompanyTitleDto responseDto = createAnotherTemporaryCompanyTitleDto();

        given(temporaryCompanyTitleRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(temporaryCompanyTitleRepository.save(existingEntity)).willReturn(savedEntity);
        given(temporaryCompanyTitleMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyTitleDto result = temporaryCompanyTitleService.updateTemporaryCompanyTitle(id, requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyTitleRepository).findById(id);
        verify(temporaryCompanyTitleMapper).partialUpdate(existingEntity, requestDto);
        verify(temporaryCompanyTitleRepository).save(existingEntity);
        verify(temporaryCompanyTitleMapper).toDTO(savedEntity);
        verify(temporaryCompanyTitleMapper, never()).toEntity(any(TemporaryCompanyTitleDto.class));
    }

    @Test
    void shouldThrowWhenUpdateTemporaryCompanyTitleCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitleDto requestDto = createPatchTemporaryCompanyTitleDto();

        given(temporaryCompanyTitleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyTitleService.updateTemporaryCompanyTitle(id, requestDto));

        verify(temporaryCompanyTitleRepository).findById(id);
        verify(temporaryCompanyTitleMapper, never()).partialUpdate(any(), any());
        verify(temporaryCompanyTitleRepository, never()).save(any());
    }

    @Test
    void shouldDeleteTemporaryCompanyTitleWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyTitle existingEntity = createSampleTemporaryCompanyTitleEntity();

        given(temporaryCompanyTitleRepository.findById(id)).willReturn(Optional.of(existingEntity));

        temporaryCompanyTitleService.deleteTemporaryCompanyTitle(id);

        verify(temporaryCompanyTitleRepository).findById(id);
        verify(temporaryCompanyTitleRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteTemporaryCompanyTitleCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyTitleRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyTitleService.deleteTemporaryCompanyTitle(id));

        verify(temporaryCompanyTitleRepository).findById(id);
        verify(temporaryCompanyTitleRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated TemporaryCompanyTitle fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitle createSampleTemporaryCompanyTitleEntity() {
        TemporaryCompanyTitle entity = new TemporaryCompanyTitle();
        entity.setId(new BigInteger("1"));
        entity.setVersion(new BigInteger("1"));
        entity.setCompanyId(new BigInteger("1"));
        entity.setCompanyPreregistrationId(new BigInteger("1"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setFromDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setOrderSeq(new BigInteger("1"));
        entity.setRecdeleted(new BigInteger("1"));
        entity.setTitle("title-value-1");
        entity.setTitleLatin("titleLatin-value-1");
        entity.setTitleNrm("titleNrm-value-1");
        entity.setTitleStatusId(new BigInteger("1"));
        entity.setToDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setGemiId(new BigInteger("1"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 1));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitle fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyTitle createAnotherTemporaryCompanyTitleEntity() {
        TemporaryCompanyTitle entity = new TemporaryCompanyTitle();
        entity.setId(new BigInteger("2"));
        entity.setVersion(new BigInteger("2"));
        entity.setCompanyId(new BigInteger("2"));
        entity.setCompanyPreregistrationId(new BigInteger("2"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setFromDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setOrderSeq(new BigInteger("2"));
        entity.setRecdeleted(new BigInteger("2"));
        entity.setTitle("title-value-2");
        entity.setTitleLatin("titleLatin-value-2");
        entity.setTitleNrm("titleNrm-value-2");
        entity.setTitleStatusId(new BigInteger("2"));
        entity.setToDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setGemiId(new BigInteger("2"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 2));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyTitleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitleDto createSampleTemporaryCompanyTitleDto() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();
        dto.setId(new BigInteger("1"));
        dto.setVersion(new BigInteger("1"));
        dto.setCompanyId(new BigInteger("1"));
        dto.setCompanyPreregistrationId(new BigInteger("1"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setOrderSeq(new BigInteger("1"));
        dto.setRecdeleted(new BigInteger("1"));
        dto.setTitle("title-value-1");
        dto.setTitleLatin("titleLatin-value-1");
        dto.setTitleNrm("titleNrm-value-1");
        dto.setTitleStatusId(new BigInteger("1"));
        dto.setToDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setGemiId(new BigInteger("1"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 1));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitleDto createAnotherTemporaryCompanyTitleDto() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();
        dto.setId(new BigInteger("2"));
        dto.setVersion(new BigInteger("2"));
        dto.setCompanyId(new BigInteger("2"));
        dto.setCompanyPreregistrationId(new BigInteger("2"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setOrderSeq(new BigInteger("2"));
        dto.setRecdeleted(new BigInteger("2"));
        dto.setTitle("title-value-2");
        dto.setTitleLatin("titleLatin-value-2");
        dto.setTitleNrm("titleNrm-value-2");
        dto.setTitleStatusId(new BigInteger("2"));
        dto.setToDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setGemiId(new BigInteger("2"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 2));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyTitleDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyTitleDto createPatchTemporaryCompanyTitleDto() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();
        dto.setVersion(new BigInteger("3"));
        dto.setCompanyId(new BigInteger("3"));
        dto.setCompanyPreregistrationId(new BigInteger("3"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setOrderSeq(new BigInteger("3"));
        dto.setRecdeleted(new BigInteger("3"));
        dto.setTitle("title-value-3");
        dto.setTitleLatin("titleLatin-value-3");
        dto.setTitleNrm("titleNrm-value-3");
        dto.setTitleStatusId(new BigInteger("3"));
        dto.setToDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setGemiId(new BigInteger("3"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 3));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 3));

        return dto;
    }

}
