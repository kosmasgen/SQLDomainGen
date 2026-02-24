package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.mapper.ChamberAppUserMapper;
import gr.knowledge.pepTest.entity.ChamberAppUser;
import gr.knowledge.pepTest.repository.ChamberAppUserRepository;
import gr.knowledge.pepTest.service.ChamberAppUserService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ChamberAppUser} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChamberAppUserServiceImpl implements ChamberAppUserService {

    private final ChamberAppUserRepository chamberAppUserRepository;
    private final ChamberAppUserMapper chamberAppUserMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChamberAppUserDto}
     */
    @Override
    public List<ChamberAppUserDto> getAllChamberAppUser() {
        log.info("Fetching all chamber-app-user.");
        return chamberAppUserMapper.toDTO(chamberAppUserRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChamberAppUserDto}
     */
    @Override
    public ChamberAppUserDto getChamberAppUserById(UUID id) {
        log.info("Fetching chamber-app-user with id: {}", id);
        ChamberAppUser entity = chamberAppUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberAppUser not found with id: " + id));
        return chamberAppUserMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChamberAppUserDto}
     */
    @Override
    public ChamberAppUserDto createChamberAppUser(ChamberAppUserDto dto) {
        log.info("Creating chamber-app-user.");
        ChamberAppUser entity = chamberAppUserMapper.toEntity(dto);
        ChamberAppUser saved = chamberAppUserRepository.save(entity);
        return chamberAppUserMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChamberAppUserDto}
     */
    @Override
    public ChamberAppUserDto updateChamberAppUser(UUID id, ChamberAppUserDto dto) {
        log.info("Updating chamber-app-user with id: {}", id);
        chamberAppUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberAppUser not found with id: " + id));
        ChamberAppUser entity = chamberAppUserMapper.toEntity(dto);
        entity.setId(id);
        ChamberAppUser saved = chamberAppUserRepository.save(entity);
        return chamberAppUserMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteChamberAppUser(UUID id) {
        log.info("Deleting chamber-app-user with id: {}", id);
        chamberAppUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChamberAppUser not found with id: " + id));
        chamberAppUserRepository.deleteById(id);
    }
}
