package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.repository.CountryRepository;
import gr.knowledge.pepTest.mapper.CountryMapper;
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
class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Country.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Country", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCountriesIsCalled() {
        List<Country> entityList = List.of(createSampleCountryEntity(), createAnotherCountryEntity());
        List<CountryDto> dtoList = List.of(createSampleCountryDto(), createAnotherCountryDto());

        given(countryRepository.findAll()).willReturn(entityList);
        given(countryMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CountryDto> result = countryService.getAllCountries();

        assertSame(dtoList, result);
        verify(countryRepository).findAll();
        verify(countryMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCountryByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Country country = createSampleCountryEntity();
        CountryDto countryDto = createSampleCountryDto();

        given(countryRepository.findById(id)).willReturn(Optional.of(country));
        given(countryMapper.toDTO(country)).willReturn(countryDto);

        CountryDto result = countryService.getCountryById(id);

        assertSame(countryDto, result);
        verify(countryRepository).findById(id);
        verify(countryMapper).toDTO(country);
    }

    @Test
    void shouldThrowWhenGetCountryByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(countryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> countryService.getCountryById(id));

        verify(countryRepository).findById(id);
        verify(countryMapper, never()).toDTO(any(Country.class));
    }

    @Test
    void shouldCreateCountryWhenCreateCountryIsCalled() {
        CountryDto requestDto = createSampleCountryDto();
        Country mappedEntity = createSampleCountryEntity();
        Country savedEntity = createAnotherCountryEntity();
        CountryDto responseDto = createAnotherCountryDto();

        given(countryMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(countryRepository.save(mappedEntity)).willReturn(savedEntity);
        given(countryMapper.toDTO(savedEntity)).willReturn(responseDto);

        CountryDto result = countryService.createCountry(requestDto);

        assertSame(responseDto, result);
        verify(countryMapper).toEntity(requestDto);
        verify(countryRepository).save(mappedEntity);
        verify(countryMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCountryWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryDto requestDto = createPatchCountryDto();
        Country existingEntity = createSampleCountryEntity();
        Country savedEntity = createAnotherCountryEntity();
        CountryDto responseDto = createAnotherCountryDto();

        given(countryRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(countryRepository.save(existingEntity)).willReturn(savedEntity);
        given(countryMapper.toDTO(savedEntity)).willReturn(responseDto);

        CountryDto result = countryService.updateCountry(id, requestDto);

        assertSame(responseDto, result);
        verify(countryRepository).findById(id);
        verify(countryMapper).partialUpdate(existingEntity, requestDto);
        verify(countryRepository).save(existingEntity);
        verify(countryMapper).toDTO(savedEntity);
        verify(countryMapper, never()).toEntity(any(CountryDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCountryCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CountryDto requestDto = createPatchCountryDto();

        given(countryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> countryService.updateCountry(id, requestDto));

        verify(countryRepository).findById(id);
        verify(countryMapper, never()).partialUpdate(any(), any());
        verify(countryRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCountryWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Country existingEntity = createSampleCountryEntity();

        given(countryRepository.findById(id)).willReturn(Optional.of(existingEntity));

        countryService.deleteCountry(id);

        verify(countryRepository).findById(id);
        verify(countryRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCountryCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(countryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> countryService.deleteCountry(id));

        verify(countryRepository).findById(id);
        verify(countryRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Country fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Country createSampleCountryEntity() {
        Country entity = new Country();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setChamberId(1);
        entity.setRegionId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberCountryId(1);

        return entity;
    }

    /**
     * Creates a populated Country fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Country createAnotherCountryEntity() {
        Country entity = new Country();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setChamberId(2);
        entity.setRegionId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberCountryId(2);

        return entity;
    }

    /**
     * Creates a populated CountryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CountryDto createSampleCountryDto() {
        CountryDto dto = new CountryDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberId(1);
        dto.setRegionId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberCountryId(1);

        return dto;
    }

    /**
     * Creates a populated CountryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CountryDto createAnotherCountryDto() {
        CountryDto dto = new CountryDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setChamberId(2);
        dto.setRegionId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberCountryId(2);

        return dto;
    }

    /**
     * Creates a populated CountryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CountryDto createPatchCountryDto() {
        CountryDto dto = new CountryDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberId(3);
        dto.setRegionId(UUID.fromString("323e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberCountryId(3);

        return dto;
    }

}
