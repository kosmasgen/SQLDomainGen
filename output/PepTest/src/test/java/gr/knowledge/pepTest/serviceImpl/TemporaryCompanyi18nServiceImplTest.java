package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.TemporaryCompanyi18n;
import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.TemporaryCompanyi18nRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyi18nMapper;
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
class TemporaryCompanyi18nServiceImplTest {

    @Mock
    private TemporaryCompanyi18nRepository temporaryCompanyi18nRepository;

    @Mock
    private TemporaryCompanyi18nMapper temporaryCompanyi18nMapper;

    @InjectMocks
    private TemporaryCompanyi18nServiceImpl temporaryCompanyi18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for TemporaryCompanyi18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("TemporaryCompanyi18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllTemporaryCompanyi18nsIsCalled() {
        List<TemporaryCompanyi18n> entityList = List.of(createSampleTemporaryCompanyi18nEntity(), createAnotherTemporaryCompanyi18nEntity());
        List<TemporaryCompanyi18nDto> dtoList = List.of(createSampleTemporaryCompanyi18nDto(), createAnotherTemporaryCompanyi18nDto());

        given(temporaryCompanyi18nRepository.findAll()).willReturn(entityList);
        given(temporaryCompanyi18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<TemporaryCompanyi18nDto> result = temporaryCompanyi18nService.getAllTemporaryCompanyi18ns();

        assertSame(dtoList, result);
        verify(temporaryCompanyi18nRepository).findAll();
        verify(temporaryCompanyi18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetTemporaryCompanyi18nByIdIsCalled() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyi18n temporaryCompanyi18n = createSampleTemporaryCompanyi18nEntity();
        TemporaryCompanyi18nDto temporaryCompanyi18nDto = createSampleTemporaryCompanyi18nDto();

        given(temporaryCompanyi18nRepository.findById(id)).willReturn(Optional.of(temporaryCompanyi18n));
        given(temporaryCompanyi18nMapper.toDTO(temporaryCompanyi18n)).willReturn(temporaryCompanyi18nDto);

        TemporaryCompanyi18nDto result = temporaryCompanyi18nService.getTemporaryCompanyi18nById(id);

        assertSame(temporaryCompanyi18nDto, result);
        verify(temporaryCompanyi18nRepository).findById(id);
        verify(temporaryCompanyi18nMapper).toDTO(temporaryCompanyi18n);
    }

    @Test
    void shouldThrowWhenGetTemporaryCompanyi18nByIdCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyi18nService.getTemporaryCompanyi18nById(id));

        verify(temporaryCompanyi18nRepository).findById(id);
        verify(temporaryCompanyi18nMapper, never()).toDTO(any(TemporaryCompanyi18n.class));
    }

    @Test
    void shouldCreateTemporaryCompanyi18nWhenCreateTemporaryCompanyi18nIsCalled() {
        TemporaryCompanyi18nDto requestDto = createSampleTemporaryCompanyi18nDto();
        TemporaryCompanyi18n mappedEntity = createSampleTemporaryCompanyi18nEntity();
        TemporaryCompanyi18n savedEntity = createAnotherTemporaryCompanyi18nEntity();
        TemporaryCompanyi18nDto responseDto = createAnotherTemporaryCompanyi18nDto();

        given(temporaryCompanyi18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(temporaryCompanyi18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(temporaryCompanyi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyi18nDto result = temporaryCompanyi18nService.createTemporaryCompanyi18n(requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyi18nMapper).toEntity(requestDto);
        verify(temporaryCompanyi18nRepository).save(mappedEntity);
        verify(temporaryCompanyi18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateTemporaryCompanyi18nWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyi18nDto requestDto = createPatchTemporaryCompanyi18nDto();
        TemporaryCompanyi18n existingEntity = createSampleTemporaryCompanyi18nEntity();
        TemporaryCompanyi18n savedEntity = createAnotherTemporaryCompanyi18nEntity();
        TemporaryCompanyi18nDto responseDto = createAnotherTemporaryCompanyi18nDto();

        given(temporaryCompanyi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(temporaryCompanyi18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(temporaryCompanyi18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyi18nDto result = temporaryCompanyi18nService.updateTemporaryCompanyi18n(id, requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyi18nRepository).findById(id);
        verify(temporaryCompanyi18nMapper).partialUpdate(existingEntity, requestDto);
        verify(temporaryCompanyi18nRepository).save(existingEntity);
        verify(temporaryCompanyi18nMapper).toDTO(savedEntity);
        verify(temporaryCompanyi18nMapper, never()).toEntity(any(TemporaryCompanyi18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateTemporaryCompanyi18nCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyi18nDto requestDto = createPatchTemporaryCompanyi18nDto();

        given(temporaryCompanyi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyi18nService.updateTemporaryCompanyi18n(id, requestDto));

        verify(temporaryCompanyi18nRepository).findById(id);
        verify(temporaryCompanyi18nMapper, never()).partialUpdate(any(), any());
        verify(temporaryCompanyi18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteTemporaryCompanyi18nWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyi18n existingEntity = createSampleTemporaryCompanyi18nEntity();

        given(temporaryCompanyi18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        temporaryCompanyi18nService.deleteTemporaryCompanyi18n(id);

        verify(temporaryCompanyi18nRepository).findById(id);
        verify(temporaryCompanyi18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteTemporaryCompanyi18nCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyi18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyi18nService.deleteTemporaryCompanyi18n(id));

        verify(temporaryCompanyi18nRepository).findById(id);
        verify(temporaryCompanyi18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated TemporaryCompanyi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyi18n createSampleTemporaryCompanyi18nEntity() {
        TemporaryCompanyi18n entity = new TemporaryCompanyi18n();
        entity.setId(new BigInteger("1"));
        entity.setVersion(new BigInteger("1"));
        entity.setCity("city-value-1");
        entity.setCoName("coName-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setMailName("mailName-value-1");
        entity.setObjective("objective-value-1");
        entity.setRecdeleted(new BigInteger("1"));
        entity.setStreet("street-value-1");
        entity.setComments("comments-value-1");
        entity.setGemiId(new BigInteger("1"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 1));
        entity.setGemiCity("gemiCity-value-1");
        entity.setArticle("article-value-1");

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyi18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyi18n createAnotherTemporaryCompanyi18nEntity() {
        TemporaryCompanyi18n entity = new TemporaryCompanyi18n();
        entity.setId(new BigInteger("2"));
        entity.setVersion(new BigInteger("2"));
        entity.setCity("city-value-2");
        entity.setCoName("coName-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setMailName("mailName-value-2");
        entity.setObjective("objective-value-2");
        entity.setRecdeleted(new BigInteger("2"));
        entity.setStreet("street-value-2");
        entity.setComments("comments-value-2");
        entity.setGemiId(new BigInteger("2"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 2));
        entity.setGemiCity("gemiCity-value-2");
        entity.setArticle("article-value-2");

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyi18nDto createSampleTemporaryCompanyi18nDto() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();
        dto.setId(new BigInteger("1"));
        dto.setVersion(new BigInteger("1"));
        dto.setCity("city-value-1");
        dto.setCoName("coName-value-1");
        dto.setCompany(new TemporaryCompanyDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLanguage(new LanguagesDto());
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setMailName("mailName-value-1");
        dto.setObjective("objective-value-1");
        dto.setRecdeleted(new BigInteger("1"));
        dto.setStreet("street-value-1");
        dto.setComments("comments-value-1");
        dto.setGemiId(new BigInteger("1"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 1));
        dto.setGemiCity("gemiCity-value-1");
        dto.setArticle("article-value-1");

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyi18nDto createAnotherTemporaryCompanyi18nDto() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();
        dto.setId(new BigInteger("2"));
        dto.setVersion(new BigInteger("2"));
        dto.setCity("city-value-2");
        dto.setCoName("coName-value-2");
        dto.setCompany(new TemporaryCompanyDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLanguage(new LanguagesDto());
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setMailName("mailName-value-2");
        dto.setObjective("objective-value-2");
        dto.setRecdeleted(new BigInteger("2"));
        dto.setStreet("street-value-2");
        dto.setComments("comments-value-2");
        dto.setGemiId(new BigInteger("2"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 2));
        dto.setGemiCity("gemiCity-value-2");
        dto.setArticle("article-value-2");

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyi18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyi18nDto createPatchTemporaryCompanyi18nDto() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();
        dto.setVersion(new BigInteger("3"));
        dto.setCity("city-value-3");
        dto.setCoName("coName-value-3");
        dto.setCompany(new TemporaryCompanyDto());
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLanguage(new LanguagesDto());
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setMailName("mailName-value-3");
        dto.setObjective("objective-value-3");
        dto.setRecdeleted(new BigInteger("3"));
        dto.setStreet("street-value-3");
        dto.setComments("comments-value-3");
        dto.setGemiId(new BigInteger("3"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 3));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 3));
        dto.setGemiCity("gemiCity-value-3");
        dto.setArticle("article-value-3");

        return dto;
    }

}
