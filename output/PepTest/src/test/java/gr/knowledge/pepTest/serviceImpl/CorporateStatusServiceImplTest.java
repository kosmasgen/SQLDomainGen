package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.repository.CorporateStatusRepository;
import gr.knowledge.pepTest.mapper.CorporateStatusMapper;
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
class CorporateStatusServiceImplTest {

    @Mock
    private CorporateStatusRepository corporateStatusRepository;

    @Mock
    private CorporateStatusMapper corporateStatusMapper;

    @InjectMocks
    private CorporateStatusServiceImpl corporateStatusService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CorporateStatus.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CorporateStatus", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCorporateStatusesIsCalled() {
        List<CorporateStatus> entityList = List.of(createSampleCorporateStatusEntity(), createAnotherCorporateStatusEntity());
        List<CorporateStatusDto> dtoList = List.of(createSampleCorporateStatusDto(), createAnotherCorporateStatusDto());

        given(corporateStatusRepository.findAll()).willReturn(entityList);
        given(corporateStatusMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CorporateStatusDto> result = corporateStatusService.getAllCorporateStatuses();

        assertSame(dtoList, result);
        verify(corporateStatusRepository).findAll();
        verify(corporateStatusMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCorporateStatusByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatus corporateStatus = createSampleCorporateStatusEntity();
        CorporateStatusDto corporateStatusDto = createSampleCorporateStatusDto();

        given(corporateStatusRepository.findById(id)).willReturn(Optional.of(corporateStatus));
        given(corporateStatusMapper.toDTO(corporateStatus)).willReturn(corporateStatusDto);

        CorporateStatusDto result = corporateStatusService.getCorporateStatusById(id);

        assertSame(corporateStatusDto, result);
        verify(corporateStatusRepository).findById(id);
        verify(corporateStatusMapper).toDTO(corporateStatus);
    }

    @Test
    void shouldThrowWhenGetCorporateStatusByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(corporateStatusRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusService.getCorporateStatusById(id));

        verify(corporateStatusRepository).findById(id);
        verify(corporateStatusMapper, never()).toDTO(any(CorporateStatus.class));
    }

    @Test
    void shouldCreateCorporateStatusWhenCreateCorporateStatusIsCalled() {
        CorporateStatusDto requestDto = createSampleCorporateStatusDto();
        CorporateStatus mappedEntity = createSampleCorporateStatusEntity();
        CorporateStatus savedEntity = createAnotherCorporateStatusEntity();
        CorporateStatusDto responseDto = createAnotherCorporateStatusDto();

        given(corporateStatusMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(corporateStatusRepository.save(mappedEntity)).willReturn(savedEntity);
        given(corporateStatusMapper.toDTO(savedEntity)).willReturn(responseDto);

        CorporateStatusDto result = corporateStatusService.createCorporateStatus(requestDto);

        assertSame(responseDto, result);
        verify(corporateStatusMapper).toEntity(requestDto);
        verify(corporateStatusRepository).save(mappedEntity);
        verify(corporateStatusMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCorporateStatusWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusDto requestDto = createPatchCorporateStatusDto();
        CorporateStatus existingEntity = createSampleCorporateStatusEntity();
        CorporateStatus savedEntity = createAnotherCorporateStatusEntity();
        CorporateStatusDto responseDto = createAnotherCorporateStatusDto();

        given(corporateStatusRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(corporateStatusRepository.save(existingEntity)).willReturn(savedEntity);
        given(corporateStatusMapper.toDTO(savedEntity)).willReturn(responseDto);

        CorporateStatusDto result = corporateStatusService.updateCorporateStatus(id, requestDto);

        assertSame(responseDto, result);
        verify(corporateStatusRepository).findById(id);
        verify(corporateStatusMapper).partialUpdate(existingEntity, requestDto);
        verify(corporateStatusRepository).save(existingEntity);
        verify(corporateStatusMapper).toDTO(savedEntity);
        verify(corporateStatusMapper, never()).toEntity(any(CorporateStatusDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCorporateStatusCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatusDto requestDto = createPatchCorporateStatusDto();

        given(corporateStatusRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusService.updateCorporateStatus(id, requestDto));

        verify(corporateStatusRepository).findById(id);
        verify(corporateStatusMapper, never()).partialUpdate(any(), any());
        verify(corporateStatusRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCorporateStatusWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CorporateStatus existingEntity = createSampleCorporateStatusEntity();

        given(corporateStatusRepository.findById(id)).willReturn(Optional.of(existingEntity));

        corporateStatusService.deleteCorporateStatus(id);

        verify(corporateStatusRepository).findById(id);
        verify(corporateStatusRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCorporateStatusCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(corporateStatusRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> corporateStatusService.deleteCorporateStatus(id));

        verify(corporateStatusRepository).findById(id);
        verify(corporateStatusRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CorporateStatus fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatus createSampleCorporateStatusEntity() {
        CorporateStatus entity = new CorporateStatus();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberCorporateStatusId(1);
        entity.setChamberId(1);
        entity.setCd("cd-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated CorporateStatus fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CorporateStatus createAnotherCorporateStatusEntity() {
        CorporateStatus entity = new CorporateStatus();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberCorporateStatusId(2);
        entity.setChamberId(2);
        entity.setCd("cd-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated CorporateStatusDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusDto createSampleCorporateStatusDto() {
        CorporateStatusDto dto = new CorporateStatusDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberCorporateStatusId(1);
        dto.setChamberId(1);
        dto.setCd("cd-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated CorporateStatusDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusDto createAnotherCorporateStatusDto() {
        CorporateStatusDto dto = new CorporateStatusDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberCorporateStatusId(2);
        dto.setChamberId(2);
        dto.setCd("cd-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated CorporateStatusDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CorporateStatusDto createPatchCorporateStatusDto() {
        CorporateStatusDto dto = new CorporateStatusDto();
        dto.setChamberCorporateStatusId(3);
        dto.setChamberId(3);
        dto.setCd("cd-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
