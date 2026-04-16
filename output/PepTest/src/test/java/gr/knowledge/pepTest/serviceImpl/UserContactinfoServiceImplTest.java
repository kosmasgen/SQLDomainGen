package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.UserContactinfo;
import gr.knowledge.pepTest.dto.UserContactinfoDto;
import gr.knowledge.pepTest.repository.UserContactinfoRepository;
import gr.knowledge.pepTest.mapper.UserContactinfoMapper;
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
class UserContactinfoServiceImplTest {

    @Mock
    private UserContactinfoRepository userContactinfoRepository;

    @Mock
    private UserContactinfoMapper userContactinfoMapper;

    @InjectMocks
    private UserContactinfoServiceImpl userContactinfoService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for UserContactinfo.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("UserContactinfo", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllUserContactinfosIsCalled() {
        List<UserContactinfo> entityList = List.of(createSampleUserContactinfoEntity(), createAnotherUserContactinfoEntity());
        List<UserContactinfoDto> dtoList = List.of(createSampleUserContactinfoDto(), createAnotherUserContactinfoDto());

        given(userContactinfoRepository.findAll()).willReturn(entityList);
        given(userContactinfoMapper.toDTOList(entityList)).willReturn(dtoList);

        List<UserContactinfoDto> result = userContactinfoService.getAllUserContactinfos();

        assertSame(dtoList, result);
        verify(userContactinfoRepository).findAll();
        verify(userContactinfoMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetUserContactinfoByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserContactinfo userContactinfo = createSampleUserContactinfoEntity();
        UserContactinfoDto userContactinfoDto = createSampleUserContactinfoDto();

        given(userContactinfoRepository.findById(id)).willReturn(Optional.of(userContactinfo));
        given(userContactinfoMapper.toDTO(userContactinfo)).willReturn(userContactinfoDto);

        UserContactinfoDto result = userContactinfoService.getUserContactinfoById(id);

        assertSame(userContactinfoDto, result);
        verify(userContactinfoRepository).findById(id);
        verify(userContactinfoMapper).toDTO(userContactinfo);
    }

    @Test
    void shouldThrowWhenGetUserContactinfoByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(userContactinfoRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> userContactinfoService.getUserContactinfoById(id));

        verify(userContactinfoRepository).findById(id);
        verify(userContactinfoMapper, never()).toDTO(any(UserContactinfo.class));
    }

    @Test
    void shouldCreateUserContactinfoWhenCreateUserContactinfoIsCalled() {
        UserContactinfoDto requestDto = createSampleUserContactinfoDto();
        UserContactinfo mappedEntity = createSampleUserContactinfoEntity();
        UserContactinfo savedEntity = createAnotherUserContactinfoEntity();
        UserContactinfoDto responseDto = createAnotherUserContactinfoDto();

        given(userContactinfoMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(userContactinfoRepository.save(mappedEntity)).willReturn(savedEntity);
        given(userContactinfoMapper.toDTO(savedEntity)).willReturn(responseDto);

        UserContactinfoDto result = userContactinfoService.createUserContactinfo(requestDto);

        assertSame(responseDto, result);
        verify(userContactinfoMapper).toEntity(requestDto);
        verify(userContactinfoRepository).save(mappedEntity);
        verify(userContactinfoMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateUserContactinfoWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserContactinfoDto requestDto = createPatchUserContactinfoDto();
        UserContactinfo existingEntity = createSampleUserContactinfoEntity();
        UserContactinfo savedEntity = createAnotherUserContactinfoEntity();
        UserContactinfoDto responseDto = createAnotherUserContactinfoDto();

        given(userContactinfoRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(userContactinfoRepository.save(existingEntity)).willReturn(savedEntity);
        given(userContactinfoMapper.toDTO(savedEntity)).willReturn(responseDto);

        UserContactinfoDto result = userContactinfoService.updateUserContactinfo(id, requestDto);

        assertSame(responseDto, result);
        verify(userContactinfoRepository).findById(id);
        verify(userContactinfoMapper).partialUpdate(existingEntity, requestDto);
        verify(userContactinfoRepository).save(existingEntity);
        verify(userContactinfoMapper).toDTO(savedEntity);
        verify(userContactinfoMapper, never()).toEntity(any(UserContactinfoDto.class));
    }

    @Test
    void shouldThrowWhenUpdateUserContactinfoCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserContactinfoDto requestDto = createPatchUserContactinfoDto();

        given(userContactinfoRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> userContactinfoService.updateUserContactinfo(id, requestDto));

        verify(userContactinfoRepository).findById(id);
        verify(userContactinfoMapper, never()).partialUpdate(any(), any());
        verify(userContactinfoRepository, never()).save(any());
    }

    @Test
    void shouldDeleteUserContactinfoWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        UserContactinfo existingEntity = createSampleUserContactinfoEntity();

        given(userContactinfoRepository.findById(id)).willReturn(Optional.of(existingEntity));

        userContactinfoService.deleteUserContactinfo(id);

        verify(userContactinfoRepository).findById(id);
        verify(userContactinfoRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteUserContactinfoCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(userContactinfoRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> userContactinfoService.deleteUserContactinfo(id));

        verify(userContactinfoRepository).findById(id);
        verify(userContactinfoRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated UserContactinfo fixture for service tests.
     *
     * @return populated entity fixture
     */
    private UserContactinfo createSampleUserContactinfoEntity() {
        UserContactinfo entity = new UserContactinfo();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setUsername("username-value-1");
        entity.setEmail("email-value-1");
        entity.setPhone("phone-value-1");
        entity.setMobile("mobile-value-1");
        entity.setContactUrl("contactUrl-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated UserContactinfo fixture for service tests.
     *
     * @return populated entity fixture
     */
    private UserContactinfo createAnotherUserContactinfoEntity() {
        UserContactinfo entity = new UserContactinfo();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setUsername("username-value-2");
        entity.setEmail("email-value-2");
        entity.setPhone("phone-value-2");
        entity.setMobile("mobile-value-2");
        entity.setContactUrl("contactUrl-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated UserContactinfoDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private UserContactinfoDto createSampleUserContactinfoDto() {
        UserContactinfoDto dto = new UserContactinfoDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setUsername("username-value-1");
        dto.setEmail("email-value-1");
        dto.setPhone("phone-value-1");
        dto.setMobile("mobile-value-1");
        dto.setContactUrl("contactUrl-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated UserContactinfoDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private UserContactinfoDto createAnotherUserContactinfoDto() {
        UserContactinfoDto dto = new UserContactinfoDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setUsername("username-value-2");
        dto.setEmail("email-value-2");
        dto.setPhone("phone-value-2");
        dto.setMobile("mobile-value-2");
        dto.setContactUrl("contactUrl-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated UserContactinfoDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private UserContactinfoDto createPatchUserContactinfoDto() {
        UserContactinfoDto dto = new UserContactinfoDto();
        dto.setChamberId(3);
        dto.setUsername("username-value-3");
        dto.setEmail("email-value-3");
        dto.setPhone("phone-value-3");
        dto.setMobile("mobile-value-3");
        dto.setContactUrl("contactUrl-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
