package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.repository.CompanyStatusRepository;
import gr.knowledge.pepTest.mapper.CompanyStatusMapper;
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
class CompanyStatusServiceImplTest {

    @Mock
    private CompanyStatusRepository companyStatusRepository;

    @Mock
    private CompanyStatusMapper companyStatusMapper;

    @InjectMocks
    private CompanyStatusServiceImpl companyStatusService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyStatus.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyStatus", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyStatusesIsCalled() {
        List<CompanyStatus> entityList = List.of(createSampleCompanyStatusEntity(), createAnotherCompanyStatusEntity());
        List<CompanyStatusDto> dtoList = List.of(createSampleCompanyStatusDto(), createAnotherCompanyStatusDto());

        given(companyStatusRepository.findAll()).willReturn(entityList);
        given(companyStatusMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyStatusDto> result = companyStatusService.getAllCompanyStatuses();

        assertSame(dtoList, result);
        verify(companyStatusRepository).findAll();
        verify(companyStatusMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyStatusByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatus companyStatus = createSampleCompanyStatusEntity();
        CompanyStatusDto companyStatusDto = createSampleCompanyStatusDto();

        given(companyStatusRepository.findById(id)).willReturn(Optional.of(companyStatus));
        given(companyStatusMapper.toDTO(companyStatus)).willReturn(companyStatusDto);

        CompanyStatusDto result = companyStatusService.getCompanyStatusById(id);

        assertSame(companyStatusDto, result);
        verify(companyStatusRepository).findById(id);
        verify(companyStatusMapper).toDTO(companyStatus);
    }

    @Test
    void shouldThrowWhenGetCompanyStatusByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyStatusRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusService.getCompanyStatusById(id));

        verify(companyStatusRepository).findById(id);
        verify(companyStatusMapper, never()).toDTO(any(CompanyStatus.class));
    }

    @Test
    void shouldCreateCompanyStatusWhenCreateCompanyStatusIsCalled() {
        CompanyStatusDto requestDto = createSampleCompanyStatusDto();
        CompanyStatus mappedEntity = createSampleCompanyStatusEntity();
        CompanyStatus savedEntity = createAnotherCompanyStatusEntity();
        CompanyStatusDto responseDto = createAnotherCompanyStatusDto();

        given(companyStatusMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyStatusRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyStatusMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyStatusDto result = companyStatusService.createCompanyStatus(requestDto);

        assertSame(responseDto, result);
        verify(companyStatusMapper).toEntity(requestDto);
        verify(companyStatusRepository).save(mappedEntity);
        verify(companyStatusMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyStatusWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusDto requestDto = createPatchCompanyStatusDto();
        CompanyStatus existingEntity = createSampleCompanyStatusEntity();
        CompanyStatus savedEntity = createAnotherCompanyStatusEntity();
        CompanyStatusDto responseDto = createAnotherCompanyStatusDto();

        given(companyStatusRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyStatusRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyStatusMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyStatusDto result = companyStatusService.updateCompanyStatus(id, requestDto);

        assertSame(responseDto, result);
        verify(companyStatusRepository).findById(id);
        verify(companyStatusMapper).partialUpdate(existingEntity, requestDto);
        verify(companyStatusRepository).save(existingEntity);
        verify(companyStatusMapper).toDTO(savedEntity);
        verify(companyStatusMapper, never()).toEntity(any(CompanyStatusDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyStatusCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatusDto requestDto = createPatchCompanyStatusDto();

        given(companyStatusRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusService.updateCompanyStatus(id, requestDto));

        verify(companyStatusRepository).findById(id);
        verify(companyStatusMapper, never()).partialUpdate(any(), any());
        verify(companyStatusRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyStatusWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyStatus existingEntity = createSampleCompanyStatusEntity();

        given(companyStatusRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyStatusService.deleteCompanyStatus(id);

        verify(companyStatusRepository).findById(id);
        verify(companyStatusRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyStatusCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyStatusRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyStatusService.deleteCompanyStatus(id));

        verify(companyStatusRepository).findById(id);
        verify(companyStatusRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyStatus fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatus createSampleCompanyStatusEntity() {
        CompanyStatus entity = new CompanyStatus();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberCompanyStatusId(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CompanyStatus fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyStatus createAnotherCompanyStatusEntity() {
        CompanyStatus entity = new CompanyStatus();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberCompanyStatusId(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CompanyStatusDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusDto createSampleCompanyStatusDto() {
        CompanyStatusDto dto = new CompanyStatusDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberCompanyStatusId(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CompanyStatusDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusDto createAnotherCompanyStatusDto() {
        CompanyStatusDto dto = new CompanyStatusDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberCompanyStatusId(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CompanyStatusDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyStatusDto createPatchCompanyStatusDto() {
        CompanyStatusDto dto = new CompanyStatusDto();
        dto.setChamberId(3);
        dto.setChamberCompanyStatusId(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
