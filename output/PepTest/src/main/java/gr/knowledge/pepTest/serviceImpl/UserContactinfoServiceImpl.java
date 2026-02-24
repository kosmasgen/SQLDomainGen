package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.UserContactinfoDto;
import gr.knowledge.pepTest.mapper.UserContactinfoMapper;
import gr.knowledge.pepTest.entity.UserContactinfo;
import gr.knowledge.pepTest.repository.UserContactinfoRepository;
import gr.knowledge.pepTest.service.UserContactinfoService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code UserContactinfo} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserContactinfoServiceImpl implements UserContactinfoService {

    private final UserContactinfoRepository userContactinfoRepository;
    private final UserContactinfoMapper userContactinfoMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link UserContactinfoDto}
     */
    @Override
    public List<UserContactinfoDto> getAllUserContactinfo() {
        log.info("Fetching all user-contactinfo.");
        return userContactinfoMapper.toDTO(userContactinfoRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link UserContactinfoDto}
     */
    @Override
    public UserContactinfoDto getUserContactinfoById(UUID id) {
        log.info("Fetching user-contactinfo with id: {}", id);
        UserContactinfo entity = userContactinfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserContactinfo not found with id: " + id));
        return userContactinfoMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link UserContactinfoDto}
     */
    @Override
    public UserContactinfoDto createUserContactinfo(UserContactinfoDto dto) {
        log.info("Creating user-contactinfo.");
        UserContactinfo entity = userContactinfoMapper.toEntity(dto);
        UserContactinfo saved = userContactinfoRepository.save(entity);
        return userContactinfoMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link UserContactinfoDto}
     */
    @Override
    public UserContactinfoDto updateUserContactinfo(UUID id, UserContactinfoDto dto) {
        log.info("Updating user-contactinfo with id: {}", id);
        userContactinfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserContactinfo not found with id: " + id));
        UserContactinfo entity = userContactinfoMapper.toEntity(dto);
        entity.setId(id);
        UserContactinfo saved = userContactinfoRepository.save(entity);
        return userContactinfoMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteUserContactinfo(UUID id) {
        log.info("Deleting user-contactinfo with id: {}", id);
        userContactinfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserContactinfo not found with id: " + id));
        userContactinfoRepository.deleteById(id);
    }
}
