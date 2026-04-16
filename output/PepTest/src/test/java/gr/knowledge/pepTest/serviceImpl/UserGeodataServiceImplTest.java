package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.UserGeodata;
import gr.knowledge.pepTest.dto.UserGeodataDto;
import gr.knowledge.pepTest.repository.UserGeodataRepository;
import gr.knowledge.pepTest.mapper.UserGeodataMapper;
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
class UserGeodataServiceImplTest {

    @Mock
    private UserGeodataRepository userGeodataRepository;

    @Mock
    private UserGeodataMapper userGeodataMapper;

    @InjectMocks
    private UserGeodataServiceImpl userGeodataService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for UserGeodata.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("UserGeodata", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllUserGeodatasIsCalled() {
        List<UserGeodata> entityList = List.of(createSampleUserGeodataEntity(), createAnotherUserGeodataEntity());
        List<UserGeodataDto> dtoList = List.of(createSampleUserGeodataDto(), createAnotherUserGeodataDto());

        given(userGeodataRepository.findAll()).willReturn(entityList);
        given(userGeodataMapper.toDTOList(entityList)).willReturn(dtoList);

        List<UserGeodataDto> result = userGeodataService.getAllUserGeodatas();

        assertSame(dtoList, result);
        verify(userGeodataRepository).findAll();
        verify(userGeodataMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetUserGeodataByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserGeodata userGeodata = createSampleUserGeodataEntity();
        UserGeodataDto userGeodataDto = createSampleUserGeodataDto();

        given(userGeodataRepository.findById(id)).willReturn(Optional.of(userGeodata));
        given(userGeodataMapper.toDTO(userGeodata)).willReturn(userGeodataDto);

        UserGeodataDto result = userGeodataService.getUserGeodataById(id);

        assertSame(userGeodataDto, result);
        verify(userGeodataRepository).findById(id);
        verify(userGeodataMapper).toDTO(userGeodata);
    }

    @Test
    void shouldThrowWhenGetUserGeodataByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(userGeodataRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> userGeodataService.getUserGeodataById(id));

        verify(userGeodataRepository).findById(id);
        verify(userGeodataMapper, never()).toDTO(any(UserGeodata.class));
    }

    @Test
    void shouldCreateUserGeodataWhenCreateUserGeodataIsCalled() {
        UserGeodataDto requestDto = createSampleUserGeodataDto();
        UserGeodata mappedEntity = createSampleUserGeodataEntity();
        UserGeodata savedEntity = createAnotherUserGeodataEntity();
        UserGeodataDto responseDto = createAnotherUserGeodataDto();

        given(userGeodataMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(userGeodataRepository.save(mappedEntity)).willReturn(savedEntity);
        given(userGeodataMapper.toDTO(savedEntity)).willReturn(responseDto);

        UserGeodataDto result = userGeodataService.createUserGeodata(requestDto);

        assertSame(responseDto, result);
        verify(userGeodataMapper).toEntity(requestDto);
        verify(userGeodataRepository).save(mappedEntity);
        verify(userGeodataMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateUserGeodataWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserGeodataDto requestDto = createPatchUserGeodataDto();
        UserGeodata existingEntity = createSampleUserGeodataEntity();
        UserGeodata savedEntity = createAnotherUserGeodataEntity();
        UserGeodataDto responseDto = createAnotherUserGeodataDto();

        given(userGeodataRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(userGeodataRepository.save(existingEntity)).willReturn(savedEntity);
        given(userGeodataMapper.toDTO(savedEntity)).willReturn(responseDto);

        UserGeodataDto result = userGeodataService.updateUserGeodata(id, requestDto);

        assertSame(responseDto, result);
        verify(userGeodataRepository).findById(id);
        verify(userGeodataMapper).partialUpdate(existingEntity, requestDto);
        verify(userGeodataRepository).save(existingEntity);
        verify(userGeodataMapper).toDTO(savedEntity);
        verify(userGeodataMapper, never()).toEntity(any(UserGeodataDto.class));
    }

    @Test
    void shouldThrowWhenUpdateUserGeodataCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserGeodataDto requestDto = createPatchUserGeodataDto();

        given(userGeodataRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> userGeodataService.updateUserGeodata(id, requestDto));

        verify(userGeodataRepository).findById(id);
        verify(userGeodataMapper, never()).partialUpdate(any(), any());
        verify(userGeodataRepository, never()).save(any());
    }

    @Test
    void shouldDeleteUserGeodataWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserGeodata existingEntity = createSampleUserGeodataEntity();

        given(userGeodataRepository.findById(id)).willReturn(Optional.of(existingEntity));

        userGeodataService.deleteUserGeodata(id);

        verify(userGeodataRepository).findById(id);
        verify(userGeodataRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteUserGeodataCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(userGeodataRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> userGeodataService.deleteUserGeodata(id));

        verify(userGeodataRepository).findById(id);
        verify(userGeodataRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated UserGeodata fixture for service tests.
     *
     * @return populated entity fixture
     */
    private UserGeodata createSampleUserGeodataEntity() {
        UserGeodata entity = new UserGeodata();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setUsername("username-value-1");
        entity.setLatitude("latitude-value-1");
        entity.setLongitude("longitude-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated UserGeodata fixture for service tests.
     *
     * @return populated entity fixture
     */
    private UserGeodata createAnotherUserGeodataEntity() {
        UserGeodata entity = new UserGeodata();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setUsername("username-value-2");
        entity.setLatitude("latitude-value-2");
        entity.setLongitude("longitude-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated UserGeodataDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private UserGeodataDto createSampleUserGeodataDto() {
        UserGeodataDto dto = new UserGeodataDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setUsername("username-value-1");
        dto.setLatitude("latitude-value-1");
        dto.setLongitude("longitude-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated UserGeodataDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private UserGeodataDto createAnotherUserGeodataDto() {
        UserGeodataDto dto = new UserGeodataDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setUsername("username-value-2");
        dto.setLatitude("latitude-value-2");
        dto.setLongitude("longitude-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated UserGeodataDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private UserGeodataDto createPatchUserGeodataDto() {
        UserGeodataDto dto = new UserGeodataDto();
        dto.setChamberId(3);
        dto.setUsername("username-value-3");
        dto.setLatitude("latitude-value-3");
        dto.setLongitude("longitude-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
