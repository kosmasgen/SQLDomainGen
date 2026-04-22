package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ChamberAppUser;
import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.repository.ChamberAppUserRepository;
import gr.knowledge.pepTest.mapper.ChamberAppUserMapper;
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
class ChamberAppUserServiceImplTest {

    @Mock
    private ChamberAppUserRepository chamberAppUserRepository;

    @Mock
    private ChamberAppUserMapper chamberAppUserMapper;

    @InjectMocks
    private ChamberAppUserServiceImpl chamberAppUserService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ChamberAppUser.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ChamberAppUser", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllChamberAppUsersIsCalled() {
        List<ChamberAppUser> entityList = List.of(createSampleChamberAppUserEntity(), createAnotherChamberAppUserEntity());
        List<ChamberAppUserDto> dtoList = List.of(createSampleChamberAppUserDto(), createAnotherChamberAppUserDto());

        given(chamberAppUserRepository.findAll()).willReturn(entityList);
        given(chamberAppUserMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ChamberAppUserDto> result = chamberAppUserService.getAllChamberAppUsers();

        assertSame(dtoList, result);
        verify(chamberAppUserRepository).findAll();
        verify(chamberAppUserMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetChamberAppUserByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberAppUser chamberAppUser = createSampleChamberAppUserEntity();
        ChamberAppUserDto chamberAppUserDto = createSampleChamberAppUserDto();

        given(chamberAppUserRepository.findById(id)).willReturn(Optional.of(chamberAppUser));
        given(chamberAppUserMapper.toDTO(chamberAppUser)).willReturn(chamberAppUserDto);

        ChamberAppUserDto result = chamberAppUserService.getChamberAppUserById(id);

        assertSame(chamberAppUserDto, result);
        verify(chamberAppUserRepository).findById(id);
        verify(chamberAppUserMapper).toDTO(chamberAppUser);
    }

    @Test
    void shouldThrowWhenGetChamberAppUserByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(chamberAppUserRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chamberAppUserService.getChamberAppUserById(id));

        verify(chamberAppUserRepository).findById(id);
        verify(chamberAppUserMapper, never()).toDTO(any(ChamberAppUser.class));
    }

    @Test
    void shouldCreateChamberAppUserWhenCreateChamberAppUserIsCalled() {
        ChamberAppUserDto requestDto = createSampleChamberAppUserDto();
        ChamberAppUser mappedEntity = createSampleChamberAppUserEntity();
        ChamberAppUser savedEntity = createAnotherChamberAppUserEntity();
        ChamberAppUserDto responseDto = createAnotherChamberAppUserDto();

        given(chamberAppUserMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(chamberAppUserRepository.save(mappedEntity)).willReturn(savedEntity);
        given(chamberAppUserMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChamberAppUserDto result = chamberAppUserService.createChamberAppUser(requestDto);

        assertSame(responseDto, result);
        verify(chamberAppUserMapper).toEntity(requestDto);
        verify(chamberAppUserRepository).save(mappedEntity);
        verify(chamberAppUserMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateChamberAppUserWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberAppUserDto requestDto = createPatchChamberAppUserDto();
        ChamberAppUser existingEntity = createSampleChamberAppUserEntity();
        ChamberAppUser savedEntity = createAnotherChamberAppUserEntity();
        ChamberAppUserDto responseDto = createAnotherChamberAppUserDto();

        given(chamberAppUserRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(chamberAppUserRepository.save(existingEntity)).willReturn(savedEntity);
        given(chamberAppUserMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChamberAppUserDto result = chamberAppUserService.updateChamberAppUser(id, requestDto);

        assertSame(responseDto, result);
        verify(chamberAppUserRepository).findById(id);
        verify(chamberAppUserMapper).partialUpdate(existingEntity, requestDto);
        verify(chamberAppUserRepository).save(existingEntity);
        verify(chamberAppUserMapper).toDTO(savedEntity);
        verify(chamberAppUserMapper, never()).toEntity(any(ChamberAppUserDto.class));
    }

    @Test
    void shouldThrowWhenUpdateChamberAppUserCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberAppUserDto requestDto = createPatchChamberAppUserDto();

        given(chamberAppUserRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chamberAppUserService.updateChamberAppUser(id, requestDto));

        verify(chamberAppUserRepository).findById(id);
        verify(chamberAppUserMapper, never()).partialUpdate(any(), any());
        verify(chamberAppUserRepository, never()).save(any());
    }

    @Test
    void shouldDeleteChamberAppUserWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberAppUser existingEntity = createSampleChamberAppUserEntity();

        given(chamberAppUserRepository.findById(id)).willReturn(Optional.of(existingEntity));

        chamberAppUserService.deleteChamberAppUser(id);

        verify(chamberAppUserRepository).findById(id);
        verify(chamberAppUserRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteChamberAppUserCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(chamberAppUserRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chamberAppUserService.deleteChamberAppUser(id));

        verify(chamberAppUserRepository).findById(id);
        verify(chamberAppUserRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ChamberAppUser fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChamberAppUser createSampleChamberAppUserEntity() {
        ChamberAppUser entity = new ChamberAppUser();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setChamberId(1);
        entity.setChamberAppId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setRecdeleted(true);
        entity.setProfileId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setPersonId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));

        return entity;
    }

    /**
     * Creates a populated ChamberAppUser fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChamberAppUser createAnotherChamberAppUserEntity() {
        ChamberAppUser entity = new ChamberAppUser();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setChamberId(2);
        entity.setChamberAppId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setRecdeleted(false);
        entity.setProfileId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setPersonId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));

        return entity;
    }

    /**
     * Creates a populated ChamberAppUserDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberAppUserDto createSampleChamberAppUserDto() {
        ChamberAppUserDto dto = new ChamberAppUserDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setChamberId(1);
        dto.setChamberAppId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(true);
        dto.setProfileId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setPersonId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));

        return dto;
    }

    /**
     * Creates a populated ChamberAppUserDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberAppUserDto createAnotherChamberAppUserDto() {
        ChamberAppUserDto dto = new ChamberAppUserDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setChamberId(2);
        dto.setChamberAppId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(false);
        dto.setProfileId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setPersonId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));

        return dto;
    }

    /**
     * Creates a populated ChamberAppUserDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberAppUserDto createPatchChamberAppUserDto() {
        ChamberAppUserDto dto = new ChamberAppUserDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setChamberId(3);
        dto.setChamberAppId(UUID.fromString("323e4567-e89b-12d3-a456-426614174000"));
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(true);
        dto.setProfileId(UUID.fromString("323e4567-e89b-12d3-a456-426614174000"));
        dto.setPersonId(UUID.fromString("323e4567-e89b-12d3-a456-426614174000"));

        return dto;
    }

}
