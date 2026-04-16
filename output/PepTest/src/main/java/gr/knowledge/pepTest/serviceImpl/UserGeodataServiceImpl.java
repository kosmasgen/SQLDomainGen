package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.UserGeodataDto;
import gr.knowledge.pepTest.mapper.UserGeodataMapper;
import gr.knowledge.pepTest.entity.UserGeodata;
import gr.knowledge.pepTest.repository.UserGeodataRepository;
import gr.knowledge.pepTest.service.UserGeodataService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code User Geodata} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserGeodataServiceImpl implements UserGeodataService {

    private final UserGeodataRepository userGeodataRepository;
    private final UserGeodataMapper userGeodataMapper;

    /**
     * Retrieves all user geodatas records.
     * @return list of UserGeodataDto
     */
    @Override
    public List<UserGeodataDto> getAllUserGeodatas() {
        log.info("Fetching all user geodatas records.");
        return userGeodataMapper.toDTOList(userGeodataRepository.findAll());
    }

    /**
     * Retrieves a user geodata record by id.
     * @param id the user geodata id
     * @return UserGeodataDto
     */
    @Override
    public UserGeodataDto getUserGeodataById(UUID id) {
        log.info("Fetching user geodata with id: {}", id);

        UserGeodata existingEntity = findUserGeodataByIdOrThrow(id);
        return userGeodataMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new user geodata record.
     * @param dto input payload
     * @return created {@link UserGeodataDto}
     */
    @Override
    public UserGeodataDto createUserGeodata(UserGeodataDto dto) {
        log.info("Creating user geodata.");

        UserGeodata entity = userGeodataMapper.toEntity(dto);
        UserGeodata savedEntity = userGeodataRepository.save(entity);

        return userGeodataMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing user geodata record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the user geodata id
     * @param dto input payload with partial fields
     * @return updated {@link UserGeodataDto}
     */
    @Override
    public UserGeodataDto updateUserGeodata(UUID id, UserGeodataDto dto) {
        log.info("Updating user geodata with id: {}", id);

        UserGeodata existingEntity = findUserGeodataByIdOrThrow(id);
        userGeodataMapper.partialUpdate(existingEntity, dto);
        UserGeodata savedEntity = userGeodataRepository.save(existingEntity);

        return userGeodataMapper.toDTO(savedEntity);
    }

    /**
     * Delete a user geodata record by id.
     * @param id the user geodata id
     */
    @Override
    public void deleteUserGeodata(UUID id) {
        log.info("Deleting user geodata with id: {}", id);

        findUserGeodataByIdOrThrow(id);
        userGeodataRepository.deleteById(id);
    }

    /**
     * Finds an existing user geodata record by id or throws an exception.
     * @param id the user geodata id
     * @return existing UserGeodata entity
     */
    private UserGeodata findUserGeodataByIdOrThrow(UUID id) {
        return userGeodataRepository.findById(id)
                .orElseThrow(() -> createUserGeodataNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the user geodata entity.
     @param id the user geodata id
     @return runtime exception
     */
    private RuntimeException createUserGeodataNotFoundException(UUID id) {
        log.warn("UserGeodata not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("UserGeodata")
                .message("UserGeodata not found with id: " + id)
                .build();
    }

}
