package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyFavourites;
import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import gr.knowledge.pepTest.repository.CompanyFavouritesRepository;
import gr.knowledge.pepTest.mapper.CompanyFavouritesMapper;
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
class CompanyFavouritesServiceImplTest {

    @Mock
    private CompanyFavouritesRepository companyFavouritesRepository;

    @Mock
    private CompanyFavouritesMapper companyFavouritesMapper;

    @InjectMocks
    private CompanyFavouritesServiceImpl companyFavouritesService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyFavourites.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyFavourites", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyFavouritesesIsCalled() {
        List<CompanyFavourites> entityList = List.of(createSampleCompanyFavouritesEntity(), createAnotherCompanyFavouritesEntity());
        List<CompanyFavouritesDto> dtoList = List.of(createSampleCompanyFavouritesDto(), createAnotherCompanyFavouritesDto());

        given(companyFavouritesRepository.findAll()).willReturn(entityList);
        given(companyFavouritesMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyFavouritesDto> result = companyFavouritesService.getAllCompanyFavouriteses();

        assertSame(dtoList, result);
        verify(companyFavouritesRepository).findAll();
        verify(companyFavouritesMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyFavouritesByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFavourites companyFavourites = createSampleCompanyFavouritesEntity();
        CompanyFavouritesDto companyFavouritesDto = createSampleCompanyFavouritesDto();

        given(companyFavouritesRepository.findById(id)).willReturn(Optional.of(companyFavourites));
        given(companyFavouritesMapper.toDTO(companyFavourites)).willReturn(companyFavouritesDto);

        CompanyFavouritesDto result = companyFavouritesService.getCompanyFavouritesById(id);

        assertSame(companyFavouritesDto, result);
        verify(companyFavouritesRepository).findById(id);
        verify(companyFavouritesMapper).toDTO(companyFavourites);
    }

    @Test
    void shouldThrowWhenGetCompanyFavouritesByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyFavouritesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyFavouritesService.getCompanyFavouritesById(id));

        verify(companyFavouritesRepository).findById(id);
        verify(companyFavouritesMapper, never()).toDTO(any(CompanyFavourites.class));
    }

    @Test
    void shouldCreateCompanyFavouritesWhenCreateCompanyFavouritesIsCalled() {
        CompanyFavouritesDto requestDto = createSampleCompanyFavouritesDto();
        CompanyFavourites mappedEntity = createSampleCompanyFavouritesEntity();
        CompanyFavourites savedEntity = createAnotherCompanyFavouritesEntity();
        CompanyFavouritesDto responseDto = createAnotherCompanyFavouritesDto();

        given(companyFavouritesMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyFavouritesRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyFavouritesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyFavouritesDto result = companyFavouritesService.createCompanyFavourites(requestDto);

        assertSame(responseDto, result);
        verify(companyFavouritesMapper).toEntity(requestDto);
        verify(companyFavouritesRepository).save(mappedEntity);
        verify(companyFavouritesMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyFavouritesWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFavouritesDto requestDto = createPatchCompanyFavouritesDto();
        CompanyFavourites existingEntity = createSampleCompanyFavouritesEntity();
        CompanyFavourites savedEntity = createAnotherCompanyFavouritesEntity();
        CompanyFavouritesDto responseDto = createAnotherCompanyFavouritesDto();

        given(companyFavouritesRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyFavouritesRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyFavouritesMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyFavouritesDto result = companyFavouritesService.updateCompanyFavourites(id, requestDto);

        assertSame(responseDto, result);
        verify(companyFavouritesRepository).findById(id);
        verify(companyFavouritesMapper).partialUpdate(existingEntity, requestDto);
        verify(companyFavouritesRepository).save(existingEntity);
        verify(companyFavouritesMapper).toDTO(savedEntity);
        verify(companyFavouritesMapper, never()).toEntity(any(CompanyFavouritesDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyFavouritesCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFavouritesDto requestDto = createPatchCompanyFavouritesDto();

        given(companyFavouritesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyFavouritesService.updateCompanyFavourites(id, requestDto));

        verify(companyFavouritesRepository).findById(id);
        verify(companyFavouritesMapper, never()).partialUpdate(any(), any());
        verify(companyFavouritesRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyFavouritesWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyFavourites existingEntity = createSampleCompanyFavouritesEntity();

        given(companyFavouritesRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyFavouritesService.deleteCompanyFavourites(id);

        verify(companyFavouritesRepository).findById(id);
        verify(companyFavouritesRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyFavouritesCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyFavouritesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyFavouritesService.deleteCompanyFavourites(id));

        verify(companyFavouritesRepository).findById(id);
        verify(companyFavouritesRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyFavourites fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyFavourites createSampleCompanyFavouritesEntity() {
        CompanyFavourites entity = new CompanyFavourites();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setNotes("notes-value-1");

        return entity;
    }

    /**
     * Creates a populated CompanyFavourites fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyFavourites createAnotherCompanyFavouritesEntity() {
        CompanyFavourites entity = new CompanyFavourites();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setNotes("notes-value-2");

        return entity;
    }

    /**
     * Creates a populated CompanyFavouritesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyFavouritesDto createSampleCompanyFavouritesDto() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setNotes("notes-value-1");

        return dto;
    }

    /**
     * Creates a populated CompanyFavouritesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyFavouritesDto createAnotherCompanyFavouritesDto() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setNotes("notes-value-2");

        return dto;
    }

    /**
     * Creates a populated CompanyFavouritesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyFavouritesDto createPatchCompanyFavouritesDto() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setNotes("notes-value-3");

        return dto;
    }

}
