package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyBgCooperation;
import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.repository.CompanyBgCooperationRepository;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationMapper;
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
class CompanyBgCooperationServiceImplTest {

    @Mock
    private CompanyBgCooperationRepository companyBgCooperationRepository;

    @Mock
    private CompanyBgCooperationMapper companyBgCooperationMapper;

    @InjectMocks
    private CompanyBgCooperationServiceImpl companyBgCooperationService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyBgCooperation.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyBgCooperation", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyBgCooperationsIsCalled() {
        List<CompanyBgCooperation> entityList = List.of(createSampleCompanyBgCooperationEntity(), createAnotherCompanyBgCooperationEntity());
        List<CompanyBgCooperationDto> dtoList = List.of(createSampleCompanyBgCooperationDto(), createAnotherCompanyBgCooperationDto());

        given(companyBgCooperationRepository.findAll()).willReturn(entityList);
        given(companyBgCooperationMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyBgCooperationDto> result = companyBgCooperationService.getAllCompanyBgCooperations();

        assertSame(dtoList, result);
        verify(companyBgCooperationRepository).findAll();
        verify(companyBgCooperationMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyBgCooperationByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperation companyBgCooperation = createSampleCompanyBgCooperationEntity();
        CompanyBgCooperationDto companyBgCooperationDto = createSampleCompanyBgCooperationDto();

        given(companyBgCooperationRepository.findById(id)).willReturn(Optional.of(companyBgCooperation));
        given(companyBgCooperationMapper.toDTO(companyBgCooperation)).willReturn(companyBgCooperationDto);

        CompanyBgCooperationDto result = companyBgCooperationService.getCompanyBgCooperationById(id);

        assertSame(companyBgCooperationDto, result);
        verify(companyBgCooperationRepository).findById(id);
        verify(companyBgCooperationMapper).toDTO(companyBgCooperation);
    }

    @Test
    void shouldThrowWhenGetCompanyBgCooperationByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyBgCooperationRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyBgCooperationService.getCompanyBgCooperationById(id));

        verify(companyBgCooperationRepository).findById(id);
        verify(companyBgCooperationMapper, never()).toDTO(any(CompanyBgCooperation.class));
    }

    @Test
    void shouldCreateCompanyBgCooperationWhenCreateCompanyBgCooperationIsCalled() {
        CompanyBgCooperationDto requestDto = createSampleCompanyBgCooperationDto();
        CompanyBgCooperation mappedEntity = createSampleCompanyBgCooperationEntity();
        CompanyBgCooperation savedEntity = createAnotherCompanyBgCooperationEntity();
        CompanyBgCooperationDto responseDto = createAnotherCompanyBgCooperationDto();

        given(companyBgCooperationMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyBgCooperationRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyBgCooperationMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyBgCooperationDto result = companyBgCooperationService.createCompanyBgCooperation(requestDto);

        assertSame(responseDto, result);
        verify(companyBgCooperationMapper).toEntity(requestDto);
        verify(companyBgCooperationRepository).save(mappedEntity);
        verify(companyBgCooperationMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyBgCooperationWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperationDto requestDto = createPatchCompanyBgCooperationDto();
        CompanyBgCooperation existingEntity = createSampleCompanyBgCooperationEntity();
        CompanyBgCooperation savedEntity = createAnotherCompanyBgCooperationEntity();
        CompanyBgCooperationDto responseDto = createAnotherCompanyBgCooperationDto();

        given(companyBgCooperationRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyBgCooperationRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyBgCooperationMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyBgCooperationDto result = companyBgCooperationService.updateCompanyBgCooperation(id, requestDto);

        assertSame(responseDto, result);
        verify(companyBgCooperationRepository).findById(id);
        verify(companyBgCooperationMapper).partialUpdate(existingEntity, requestDto);
        verify(companyBgCooperationRepository).save(existingEntity);
        verify(companyBgCooperationMapper).toDTO(savedEntity);
        verify(companyBgCooperationMapper, never()).toEntity(any(CompanyBgCooperationDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyBgCooperationCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperationDto requestDto = createPatchCompanyBgCooperationDto();

        given(companyBgCooperationRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyBgCooperationService.updateCompanyBgCooperation(id, requestDto));

        verify(companyBgCooperationRepository).findById(id);
        verify(companyBgCooperationMapper, never()).partialUpdate(any(), any());
        verify(companyBgCooperationRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyBgCooperationWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyBgCooperation existingEntity = createSampleCompanyBgCooperationEntity();

        given(companyBgCooperationRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyBgCooperationService.deleteCompanyBgCooperation(id);

        verify(companyBgCooperationRepository).findById(id);
        verify(companyBgCooperationRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyBgCooperationCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyBgCooperationRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyBgCooperationService.deleteCompanyBgCooperation(id));

        verify(companyBgCooperationRepository).findById(id);
        verify(companyBgCooperationRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyBgCooperation fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperation createSampleCompanyBgCooperationEntity() {
        CompanyBgCooperation entity = new CompanyBgCooperation();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setCooperationStatus("cooperationStatus-value-1");

        return entity;
    }

    /**
     * Creates a populated CompanyBgCooperation fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyBgCooperation createAnotherCompanyBgCooperationEntity() {
        CompanyBgCooperation entity = new CompanyBgCooperation();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setCooperationStatus("cooperationStatus-value-2");

        return entity;
    }

    /**
     * Creates a populated CompanyBgCooperationDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationDto createSampleCompanyBgCooperationDto() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCooperationStatus("cooperationStatus-value-1");

        return dto;
    }

    /**
     * Creates a populated CompanyBgCooperationDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationDto createAnotherCompanyBgCooperationDto() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setCooperationStatus("cooperationStatus-value-2");

        return dto;
    }

    /**
     * Creates a populated CompanyBgCooperationDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyBgCooperationDto createPatchCompanyBgCooperationDto() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();
        dto.setChamberId(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCooperationStatus("cooperationStatus-value-3");

        return dto;
    }

}
