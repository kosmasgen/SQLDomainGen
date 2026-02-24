package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.mapper.ChAppUserContactMapper;
import gr.knowledge.pepTest.entity.ChAppUserContact;
import gr.knowledge.pepTest.repository.ChAppUserContactRepository;
import gr.knowledge.pepTest.service.ChAppUserContactService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ChAppUserContact} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChAppUserContactServiceImpl implements ChAppUserContactService {

    private final ChAppUserContactRepository chAppUserContactRepository;
    private final ChAppUserContactMapper chAppUserContactMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChAppUserContactDto}
     */
    @Override
    public List<ChAppUserContactDto> getAllChAppUserContact() {
        log.info("Fetching all ch-app-user-contact.");
        return chAppUserContactMapper.toDTO(chAppUserContactRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChAppUserContactDto}
     */
    @Override
    public ChAppUserContactDto getChAppUserContactById(UUID id) {
        log.info("Fetching ch-app-user-contact with id: {}", id);
        ChAppUserContact entity = chAppUserContactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChAppUserContact not found with id: " + id));
        return chAppUserContactMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChAppUserContactDto}
     */
    @Override
    public ChAppUserContactDto createChAppUserContact(ChAppUserContactDto dto) {
        log.info("Creating ch-app-user-contact.");
        ChAppUserContact entity = chAppUserContactMapper.toEntity(dto);
        ChAppUserContact saved = chAppUserContactRepository.save(entity);
        return chAppUserContactMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChAppUserContactDto}
     */
    @Override
    public ChAppUserContactDto updateChAppUserContact(UUID id, ChAppUserContactDto dto) {
        log.info("Updating ch-app-user-contact with id: {}", id);
        chAppUserContactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChAppUserContact not found with id: " + id));
        ChAppUserContact entity = chAppUserContactMapper.toEntity(dto);
        entity.setId(id);
        ChAppUserContact saved = chAppUserContactRepository.save(entity);
        return chAppUserContactMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteChAppUserContact(UUID id) {
        log.info("Deleting ch-app-user-contact with id: {}", id);
        chAppUserContactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChAppUserContact not found with id: " + id));
        chAppUserContactRepository.deleteById(id);
    }
}
