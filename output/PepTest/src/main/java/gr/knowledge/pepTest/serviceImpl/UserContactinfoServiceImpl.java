package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.UserContactinfoDto;
import gr.knowledge.pepTest.mapper.UserContactinfoMapper;
import gr.knowledge.pepTest.entity.UserContactinfo;
import gr.knowledge.pepTest.repository.UserContactinfoRepository;
import gr.knowledge.pepTest.service.UserContactinfoService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code User Contactinfo} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserContactinfoServiceImpl implements UserContactinfoService {

    private final UserContactinfoRepository userContactinfoRepository;
    private final UserContactinfoMapper userContactinfoMapper;

    /**
     * Retrieves all user contactinfos records.
     * @return list of UserContactinfoDto
     */
    @Override
    public List<UserContactinfoDto> getAllUserContactinfos() {
        log.info("Fetching all user contactinfos records.");
        return userContactinfoMapper.toDTOList(userContactinfoRepository.findAll());
    }

    /**
     * Retrieves a user contactinfo record by id.
     * @param id the user contactinfo id
     * @return UserContactinfoDto
     */
    @Override
    public UserContactinfoDto getUserContactinfoById(UUID id) {
        log.info("Fetching user contactinfo with id: {}", id);

        UserContactinfo existingEntity = findUserContactinfoByIdOrThrow(id);
        return userContactinfoMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new user contactinfo record.
     * @param dto input payload
     * @return created {@link UserContactinfoDto}
     */
    @Override
    public UserContactinfoDto createUserContactinfo(UserContactinfoDto dto) {
        log.info("Creating user contactinfo.");

        UserContactinfo entity = userContactinfoMapper.toEntity(dto);
        UserContactinfo savedEntity = userContactinfoRepository.save(entity);

        return userContactinfoMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing user contactinfo record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the user contactinfo id
     * @param dto input payload with partial fields
     * @return updated {@link UserContactinfoDto}
     */
    @Override
    public UserContactinfoDto updateUserContactinfo(UUID id, UserContactinfoDto dto) {
        log.info("Updating user contactinfo with id: {}", id);

        UserContactinfo existingEntity = findUserContactinfoByIdOrThrow(id);
        userContactinfoMapper.partialUpdate(existingEntity, dto);
        UserContactinfo savedEntity = userContactinfoRepository.save(existingEntity);

        return userContactinfoMapper.toDTO(savedEntity);
    }

    /**
     * Delete a user contactinfo record by id.
     * @param id the user contactinfo id
     */
    @Override
    public void deleteUserContactinfo(UUID id) {
        log.info("Deleting user contactinfo with id: {}", id);

        findUserContactinfoByIdOrThrow(id);
        userContactinfoRepository.deleteById(id);
    }

    /**
     * Finds an existing user contactinfo record by id or throws an exception.
     * @param id the user contactinfo id
     * @return existing UserContactinfo entity
     */
    private UserContactinfo findUserContactinfoByIdOrThrow(UUID id) {
        return userContactinfoRepository.findById(id)
                .orElseThrow(() -> createUserContactinfoNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the user contactinfo entity.
     @param id the user contactinfo id
     @return runtime exception
     */
    private RuntimeException createUserContactinfoNotFoundException(UUID id) {
        log.warn("UserContactinfo not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("UserContactinfo")
                .message("UserContactinfo not found with id: " + id)
                .build();
    }

}
