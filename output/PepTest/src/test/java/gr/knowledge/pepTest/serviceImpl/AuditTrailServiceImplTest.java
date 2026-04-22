package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.AuditTrail;
import gr.knowledge.pepTest.dto.AuditTrailDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.repository.AuditTrailRepository;
import gr.knowledge.pepTest.mapper.AuditTrailMapper;
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
class AuditTrailServiceImplTest {

    @Mock
    private AuditTrailRepository auditTrailRepository;

    @Mock
    private AuditTrailMapper auditTrailMapper;

    @InjectMocks
    private AuditTrailServiceImpl auditTrailService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for AuditTrail.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("AuditTrail", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllAuditTrailsIsCalled() {
        List<AuditTrail> entityList = List.of(createSampleAuditTrailEntity(), createAnotherAuditTrailEntity());
        List<AuditTrailDto> dtoList = List.of(createSampleAuditTrailDto(), createAnotherAuditTrailDto());

        given(auditTrailRepository.findAll()).willReturn(entityList);
        given(auditTrailMapper.toDTOList(entityList)).willReturn(dtoList);

        List<AuditTrailDto> result = auditTrailService.getAllAuditTrails();

        assertSame(dtoList, result);
        verify(auditTrailRepository).findAll();
        verify(auditTrailMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetAuditTrailByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        AuditTrail auditTrail = createSampleAuditTrailEntity();
        AuditTrailDto auditTrailDto = createSampleAuditTrailDto();

        given(auditTrailRepository.findById(id)).willReturn(Optional.of(auditTrail));
        given(auditTrailMapper.toDTO(auditTrail)).willReturn(auditTrailDto);

        AuditTrailDto result = auditTrailService.getAuditTrailById(id);

        assertSame(auditTrailDto, result);
        verify(auditTrailRepository).findById(id);
        verify(auditTrailMapper).toDTO(auditTrail);
    }

    @Test
    void shouldThrowWhenGetAuditTrailByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(auditTrailRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> auditTrailService.getAuditTrailById(id));

        verify(auditTrailRepository).findById(id);
        verify(auditTrailMapper, never()).toDTO(any(AuditTrail.class));
    }

    @Test
    void shouldCreateAuditTrailWhenCreateAuditTrailIsCalled() {
        AuditTrailDto requestDto = createSampleAuditTrailDto();
        AuditTrail mappedEntity = createSampleAuditTrailEntity();
        AuditTrail savedEntity = createAnotherAuditTrailEntity();
        AuditTrailDto responseDto = createAnotherAuditTrailDto();

        given(auditTrailMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(auditTrailRepository.save(mappedEntity)).willReturn(savedEntity);
        given(auditTrailMapper.toDTO(savedEntity)).willReturn(responseDto);

        AuditTrailDto result = auditTrailService.createAuditTrail(requestDto);

        assertSame(responseDto, result);
        verify(auditTrailMapper).toEntity(requestDto);
        verify(auditTrailRepository).save(mappedEntity);
        verify(auditTrailMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateAuditTrailWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        AuditTrailDto requestDto = createPatchAuditTrailDto();
        AuditTrail existingEntity = createSampleAuditTrailEntity();
        AuditTrail savedEntity = createAnotherAuditTrailEntity();
        AuditTrailDto responseDto = createAnotherAuditTrailDto();

        given(auditTrailRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(auditTrailRepository.save(existingEntity)).willReturn(savedEntity);
        given(auditTrailMapper.toDTO(savedEntity)).willReturn(responseDto);

        AuditTrailDto result = auditTrailService.updateAuditTrail(id, requestDto);

        assertSame(responseDto, result);
        verify(auditTrailRepository).findById(id);
        verify(auditTrailMapper).partialUpdate(existingEntity, requestDto);
        verify(auditTrailRepository).save(existingEntity);
        verify(auditTrailMapper).toDTO(savedEntity);
        verify(auditTrailMapper, never()).toEntity(any(AuditTrailDto.class));
    }

    @Test
    void shouldThrowWhenUpdateAuditTrailCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        AuditTrailDto requestDto = createPatchAuditTrailDto();

        given(auditTrailRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> auditTrailService.updateAuditTrail(id, requestDto));

        verify(auditTrailRepository).findById(id);
        verify(auditTrailMapper, never()).partialUpdate(any(), any());
        verify(auditTrailRepository, never()).save(any());
    }

    @Test
    void shouldDeleteAuditTrailWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        AuditTrail existingEntity = createSampleAuditTrailEntity();

        given(auditTrailRepository.findById(id)).willReturn(Optional.of(existingEntity));

        auditTrailService.deleteAuditTrail(id);

        verify(auditTrailRepository).findById(id);
        verify(auditTrailRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteAuditTrailCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(auditTrailRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> auditTrailService.deleteAuditTrail(id));

        verify(auditTrailRepository).findById(id);
        verify(auditTrailRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated AuditTrail fixture for service tests.
     *
     * @return populated entity fixture
     */
    private AuditTrail createSampleAuditTrailEntity() {
        AuditTrail entity = new AuditTrail();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setIp("ip-value-1");
        entity.setCompleteUri("completeUri-value-1");
        entity.setUriPath("uriPath-value-1");

        return entity;
    }

    /**
     * Creates a populated AuditTrail fixture for service tests.
     *
     * @return populated entity fixture
     */
    private AuditTrail createAnotherAuditTrailEntity() {
        AuditTrail entity = new AuditTrail();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setIp("ip-value-2");
        entity.setCompleteUri("completeUri-value-2");
        entity.setUriPath("uriPath-value-2");

        return entity;
    }

    /**
     * Creates a populated AuditTrailDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private AuditTrailDto createSampleAuditTrailDto() {
        AuditTrailDto dto = new AuditTrailDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setIp("ip-value-1");
        dto.setCompleteUri("completeUri-value-1");
        dto.setCompany(new CompanyDto());
        dto.setProfile(new CompanyProfileDto());
        dto.setUriPath("uriPath-value-1");
        dto.setCountry(new CountryDto());

        return dto;
    }

    /**
     * Creates a populated AuditTrailDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private AuditTrailDto createAnotherAuditTrailDto() {
        AuditTrailDto dto = new AuditTrailDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setIp("ip-value-2");
        dto.setCompleteUri("completeUri-value-2");
        dto.setCompany(new CompanyDto());
        dto.setProfile(new CompanyProfileDto());
        dto.setUriPath("uriPath-value-2");
        dto.setCountry(new CountryDto());

        return dto;
    }

    /**
     * Creates a populated AuditTrailDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private AuditTrailDto createPatchAuditTrailDto() {
        AuditTrailDto dto = new AuditTrailDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setIp("ip-value-3");
        dto.setCompleteUri("completeUri-value-3");
        dto.setCompany(new CompanyDto());
        dto.setProfile(new CompanyProfileDto());
        dto.setUriPath("uriPath-value-3");
        dto.setCountry(new CountryDto());

        return dto;
    }

}
