package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.mapper.ChAppUserContactI18nMapper;
import gr.knowledge.pepTest.entity.ChAppUserContactI18n;
import gr.knowledge.pepTest.repository.ChAppUserContactI18nRepository;
import gr.knowledge.pepTest.service.ChAppUserContactI18nService;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ChAppUserContactI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChAppUserContactI18nServiceImpl implements ChAppUserContactI18nService {

    private final ChAppUserContactI18nRepository chAppUserContactI18nRepository;
    private final ChAppUserContactI18nMapper chAppUserContactI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ChAppUserContactI18nDto}
     */
    @Override
    public List<ChAppUserContactI18nDto> getAllChAppUserContactI18n() {
        log.info("Fetching all ch-app-user-contact-i18n.");
        return chAppUserContactI18nMapper.toDTO(chAppUserContactI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ChAppUserContactI18nDto}
     */
    @Override
    public ChAppUserContactI18nDto getChAppUserContactI18nById(ChAppUserContactI18nPK id) {
        log.info("Fetching ch-app-user-contact-i18n with id: {}", id);
        ChAppUserContactI18n entity = chAppUserContactI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChAppUserContactI18n not found with id: " + id));
        return chAppUserContactI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ChAppUserContactI18nDto}
     */
    @Override
    public ChAppUserContactI18nDto createChAppUserContactI18n(ChAppUserContactI18nDto dto) {
        log.info("Creating ch-app-user-contact-i18n.");
        ChAppUserContactI18n entity = chAppUserContactI18nMapper.toEntity(dto);
        ChAppUserContactI18n saved = chAppUserContactI18nRepository.save(entity);
        return chAppUserContactI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ChAppUserContactI18nDto}
     */
    @Override
    public ChAppUserContactI18nDto updateChAppUserContactI18n(ChAppUserContactI18nPK id, ChAppUserContactI18nDto dto) {
        log.info("Updating ch-app-user-contact-i18n with id: {}", id);
        chAppUserContactI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChAppUserContactI18n not found with id: " + id));
        ChAppUserContactI18n entity = chAppUserContactI18nMapper.toEntity(dto);
        entity.setId(id);
        ChAppUserContactI18n saved = chAppUserContactI18nRepository.save(entity);
        return chAppUserContactI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteChAppUserContactI18n(ChAppUserContactI18nPK id) {
        log.info("Deleting ch-app-user-contact-i18n with id: {}", id);
        chAppUserContactI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ChAppUserContactI18n not found with id: " + id));
        chAppUserContactI18nRepository.deleteById(id);
    }
}
