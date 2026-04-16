package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CountryI18n;
import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.repository.CountryI18nRepository;
import gr.knowledge.pepTest.mapper.CountryI18nMapper;
import gr.knowledge.pepTest.entity.CountryI18nKey;
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
class CountryI18nServiceImplTest {

    @Mock
    private CountryI18nRepository countryI18nRepository;

    @Mock
    private CountryI18nMapper countryI18nMapper;

    @InjectMocks
    private CountryI18nServiceImpl countryI18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CountryI18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CountryI18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCountryI18nsIsCalled() {
        List<CountryI18n> entityList = List.of(createSampleCountryI18nEntity(), createAnotherCountryI18nEntity());
        List<CountryI18nDto> dtoList = List.of(createSampleCountryI18nDto(), createAnotherCountryI18nDto());

        given(countryI18nRepository.findAll()).willReturn(entityList);
        given(countryI18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CountryI18nDto> result = countryI18nService.getAllCountryI18ns();

        assertSame(dtoList, result);
        verify(countryI18nRepository).findAll();
        verify(countryI18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCountryI18nByIdIsCalled() {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryI18nKey id = new CountryI18nKey();
        id.setCountryId(countryId);
        id.setLanguageId(languageId);

        CountryI18n countryI18n = createSampleCountryI18nEntity();
        CountryI18nDto countryI18nDto = createSampleCountryI18nDto();

        given(countryI18nRepository.findById(id)).willReturn(Optional.of(countryI18n));
        given(countryI18nMapper.toDTO(countryI18n)).willReturn(countryI18nDto);

        CountryI18nDto result = countryI18nService.getCountryI18nById(countryId, languageId);

        assertSame(countryI18nDto, result);
        verify(countryI18nRepository).findById(id);
        verify(countryI18nMapper).toDTO(countryI18n);
    }

    @Test
    void shouldThrowWhenGetCountryI18nByIdCannotFindEntity() {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryI18nKey id = new CountryI18nKey();
        id.setCountryId(countryId);
        id.setLanguageId(languageId);

        given(countryI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> countryI18nService.getCountryI18nById(countryId, languageId));

        verify(countryI18nRepository).findById(id);
        verify(countryI18nMapper, never()).toDTO(any(CountryI18n.class));
    }

    @Test
    void shouldCreateCountryI18nWhenCreateCountryI18nIsCalled() {
        CountryI18nDto requestDto = createSampleCountryI18nDto();
        CountryI18n mappedEntity = createSampleCountryI18nEntity();
        CountryI18n savedEntity = createAnotherCountryI18nEntity();
        CountryI18nDto responseDto = createAnotherCountryI18nDto();

        given(countryI18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(countryI18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(countryI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CountryI18nDto result = countryI18nService.createCountryI18n(requestDto);

        assertSame(responseDto, result);
        verify(countryI18nMapper).toEntity(requestDto);
        verify(countryI18nRepository).save(mappedEntity);
        verify(countryI18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCountryI18nWhenEntityExists() {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryI18nKey id = new CountryI18nKey();
        id.setCountryId(countryId);
        id.setLanguageId(languageId);

        CountryI18nDto requestDto = createPatchCountryI18nDto();
        CountryI18n existingEntity = createSampleCountryI18nEntity();
        CountryI18n savedEntity = createAnotherCountryI18nEntity();
        CountryI18nDto responseDto = createAnotherCountryI18nDto();

        given(countryI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(countryI18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(countryI18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        CountryI18nDto result = countryI18nService.updateCountryI18n(countryId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(countryI18nRepository).findById(id);
        verify(countryI18nMapper).partialUpdate(existingEntity, requestDto);
        verify(countryI18nRepository).save(existingEntity);
        verify(countryI18nMapper).toDTO(savedEntity);
        verify(countryI18nMapper, never()).toEntity(any(CountryI18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCountryI18nCannotFindEntity() {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryI18nKey id = new CountryI18nKey();
        id.setCountryId(countryId);
        id.setLanguageId(languageId);

        CountryI18nDto requestDto = createPatchCountryI18nDto();

        given(countryI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> countryI18nService.updateCountryI18n(countryId, languageId, requestDto));

        verify(countryI18nRepository).findById(id);
        verify(countryI18nMapper, never()).partialUpdate(any(), any());
        verify(countryI18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCountryI18nWhenEntityExists() {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryI18nKey id = new CountryI18nKey();
        id.setCountryId(countryId);
        id.setLanguageId(languageId);

        CountryI18n existingEntity = createSampleCountryI18nEntity();

        given(countryI18nRepository.findById(id)).willReturn(Optional.of(existingEntity));

        countryI18nService.deleteCountryI18n(countryId, languageId);

        verify(countryI18nRepository).findById(id);
        verify(countryI18nRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCountryI18nCannotFindEntity() {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryI18nKey id = new CountryI18nKey();
        id.setCountryId(countryId);
        id.setLanguageId(languageId);

        given(countryI18nRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> countryI18nService.deleteCountryI18n(countryId, languageId));

        verify(countryI18nRepository).findById(id);
        verify(countryI18nRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CountryI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CountryI18n createSampleCountryI18nEntity() {
        CountryI18n entity = new CountryI18n();
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setChamberCountryI18nId(1);

        return entity;
    }

    /**
     * Creates a populated CountryI18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CountryI18n createAnotherCountryI18nEntity() {
        CountryI18n entity = new CountryI18n();
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setChamberCountryI18nId(2);

        return entity;
    }

    /**
     * Creates a populated CountryI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CountryI18nDto createSampleCountryI18nDto() {
        CountryI18nDto dto = new CountryI18nDto();
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberCountryI18nId(1);

        return dto;
    }

    /**
     * Creates a populated CountryI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CountryI18nDto createAnotherCountryI18nDto() {
        CountryI18nDto dto = new CountryI18nDto();
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setChamberCountryI18nId(2);

        return dto;
    }

    /**
     * Creates a populated CountryI18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CountryI18nDto createPatchCountryI18nDto() {
        CountryI18nDto dto = new CountryI18nDto();
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberCountryI18nId(3);

        return dto;
    }

}
