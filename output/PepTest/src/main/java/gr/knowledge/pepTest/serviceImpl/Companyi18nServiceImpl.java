package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.Companyi18nDto;
import gr.knowledge.pepTest.mapper.Companyi18nMapper;
import gr.knowledge.pepTest.entity.Companyi18n;
import gr.knowledge.pepTest.repository.Companyi18nRepository;
import gr.knowledge.pepTest.service.Companyi18nService;
import gr.knowledge.pepTest.entity.Companyi18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Companyi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class Companyi18nServiceImpl implements Companyi18nService {

    private final Companyi18nRepository companyi18nRepository;
    private final Companyi18nMapper companyi18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link Companyi18nDto}
     */
    @Override
    public List<Companyi18nDto> getAllCompanyi18n() {
        log.info("Fetching all companyi18n.");
        return companyi18nMapper.toDTO(companyi18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link Companyi18nDto}
     */
    @Override
    public Companyi18nDto getCompanyi18nById(Companyi18nPK id) {
        log.info("Fetching companyi18n with id: {}", id);
        Companyi18n entity = companyi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Companyi18n not found with id: " + id));
        return companyi18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link Companyi18nDto}
     */
    @Override
    public Companyi18nDto createCompanyi18n(Companyi18nDto dto) {
        log.info("Creating companyi18n.");
        Companyi18n entity = companyi18nMapper.toEntity(dto);
        Companyi18n saved = companyi18nRepository.save(entity);
        return companyi18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link Companyi18nDto}
     */
    @Override
    public Companyi18nDto updateCompanyi18n(Companyi18nPK id, Companyi18nDto dto) {
        log.info("Updating companyi18n with id: {}", id);
        companyi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Companyi18n not found with id: " + id));
        Companyi18n entity = companyi18nMapper.toEntity(dto);
        entity.setId(id);
        Companyi18n saved = companyi18nRepository.save(entity);
        return companyi18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyi18n(Companyi18nPK id) {
        log.info("Deleting companyi18n with id: {}", id);
        companyi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Companyi18n not found with id: " + id));
        companyi18nRepository.deleteById(id);
    }
}
