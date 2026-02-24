package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.mapper.Professioni18nMapper;
import gr.knowledge.pepTest.entity.Professioni18n;
import gr.knowledge.pepTest.repository.Professioni18nRepository;
import gr.knowledge.pepTest.service.Professioni18nService;
import gr.knowledge.pepTest.entity.Professioni18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Professioni18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class Professioni18nServiceImpl implements Professioni18nService {

    private final Professioni18nRepository professioni18nRepository;
    private final Professioni18nMapper professioni18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link Professioni18nDto}
     */
    @Override
    public List<Professioni18nDto> getAllProfessioni18n() {
        log.info("Fetching all professioni18n.");
        return professioni18nMapper.toDTO(professioni18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link Professioni18nDto}
     */
    @Override
    public Professioni18nDto getProfessioni18nById(Professioni18nPK id) {
        log.info("Fetching professioni18n with id: {}", id);
        Professioni18n entity = professioni18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professioni18n not found with id: " + id));
        return professioni18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link Professioni18nDto}
     */
    @Override
    public Professioni18nDto createProfessioni18n(Professioni18nDto dto) {
        log.info("Creating professioni18n.");
        Professioni18n entity = professioni18nMapper.toEntity(dto);
        Professioni18n saved = professioni18nRepository.save(entity);
        return professioni18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link Professioni18nDto}
     */
    @Override
    public Professioni18nDto updateProfessioni18n(Professioni18nPK id, Professioni18nDto dto) {
        log.info("Updating professioni18n with id: {}", id);
        professioni18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professioni18n not found with id: " + id));
        Professioni18n entity = professioni18nMapper.toEntity(dto);
        entity.setId(id);
        Professioni18n saved = professioni18nRepository.save(entity);
        return professioni18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProfessioni18n(Professioni18nPK id) {
        log.info("Deleting professioni18n with id: {}", id);
        professioni18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professioni18n not found with id: " + id));
        professioni18nRepository.deleteById(id);
    }
}
