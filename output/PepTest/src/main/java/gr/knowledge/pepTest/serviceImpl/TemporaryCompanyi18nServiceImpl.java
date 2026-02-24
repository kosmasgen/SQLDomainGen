package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyi18nMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyi18n;
import gr.knowledge.pepTest.repository.TemporaryCompanyi18nRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyi18nService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code TemporaryCompanyi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyi18nServiceImpl implements TemporaryCompanyi18nService {

    private final TemporaryCompanyi18nRepository temporaryCompanyi18nRepository;
    private final TemporaryCompanyi18nMapper temporaryCompanyi18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyi18nDto}
     */
    @Override
    public List<TemporaryCompanyi18nDto> getAllTemporaryCompanyi18n() {
        log.info("Fetching all temporary-companyi18n.");
        return temporaryCompanyi18nMapper.toDTO(temporaryCompanyi18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyi18nDto}
     */
    @Override
    public TemporaryCompanyi18nDto getTemporaryCompanyi18nById(Long id) {
        log.info("Fetching temporary-companyi18n with id: {}", id);
        TemporaryCompanyi18n entity = temporaryCompanyi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyi18n not found with id: " + id));
        return temporaryCompanyi18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyi18nDto}
     */
    @Override
    public TemporaryCompanyi18nDto createTemporaryCompanyi18n(TemporaryCompanyi18nDto dto) {
        log.info("Creating temporary-companyi18n.");
        TemporaryCompanyi18n entity = temporaryCompanyi18nMapper.toEntity(dto);
        TemporaryCompanyi18n saved = temporaryCompanyi18nRepository.save(entity);
        return temporaryCompanyi18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyi18nDto}
     */
    @Override
    public TemporaryCompanyi18nDto updateTemporaryCompanyi18n(Long id, TemporaryCompanyi18nDto dto) {
        log.info("Updating temporary-companyi18n with id: {}", id);
        temporaryCompanyi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyi18n not found with id: " + id));
        TemporaryCompanyi18n entity = temporaryCompanyi18nMapper.toEntity(dto);
        entity.setId(id);
        TemporaryCompanyi18n saved = temporaryCompanyi18nRepository.save(entity);
        return temporaryCompanyi18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteTemporaryCompanyi18n(Long id) {
        log.info("Deleting temporary-companyi18n with id: {}", id);
        temporaryCompanyi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyi18n not found with id: " + id));
        temporaryCompanyi18nRepository.deleteById(id);
    }
}
