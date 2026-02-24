package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.UserGeodataDto;
import gr.knowledge.pepTest.mapper.UserGeodataMapper;
import gr.knowledge.pepTest.entity.UserGeodata;
import gr.knowledge.pepTest.repository.UserGeodataRepository;
import gr.knowledge.pepTest.service.UserGeodataService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code UserGeodata} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserGeodataServiceImpl implements UserGeodataService {

    private final UserGeodataRepository userGeodataRepository;
    private final UserGeodataMapper userGeodataMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link UserGeodataDto}
     */
    @Override
    public List<UserGeodataDto> getAllUserGeodata() {
        log.info("Fetching all user-geodata.");
        return userGeodataMapper.toDTO(userGeodataRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link UserGeodataDto}
     */
    @Override
    public UserGeodataDto getUserGeodataById(UUID id) {
        log.info("Fetching user-geodata with id: {}", id);
        UserGeodata entity = userGeodataRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserGeodata not found with id: " + id));
        return userGeodataMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link UserGeodataDto}
     */
    @Override
    public UserGeodataDto createUserGeodata(UserGeodataDto dto) {
        log.info("Creating user-geodata.");
        UserGeodata entity = userGeodataMapper.toEntity(dto);
        UserGeodata saved = userGeodataRepository.save(entity);
        return userGeodataMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link UserGeodataDto}
     */
    @Override
    public UserGeodataDto updateUserGeodata(UUID id, UserGeodataDto dto) {
        log.info("Updating user-geodata with id: {}", id);
        userGeodataRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserGeodata not found with id: " + id));
        UserGeodata entity = userGeodataMapper.toEntity(dto);
        entity.setId(id);
        UserGeodata saved = userGeodataRepository.save(entity);
        return userGeodataMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteUserGeodata(UUID id) {
        log.info("Deleting user-geodata with id: {}", id);
        userGeodataRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserGeodata not found with id: " + id));
        userGeodataRepository.deleteById(id);
    }
}
