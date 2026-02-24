package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.mapper.ProfessionKindi18nMapper;
import gr.knowledge.pepTest.entity.ProfessionKindi18n;
import gr.knowledge.pepTest.repository.ProfessionKindi18nRepository;
import gr.knowledge.pepTest.service.ProfessionKindi18nService;
import gr.knowledge.pepTest.entity.ProfessionKindi18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ProfessionKindi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionKindi18nServiceImpl implements ProfessionKindi18nService {

    private final ProfessionKindi18nRepository professionKindi18nRepository;
    private final ProfessionKindi18nMapper professionKindi18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionKindi18nDto}
     */
    @Override
    public List<ProfessionKindi18nDto> getAllProfessionKindi18n() {
        log.info("Fetching all profession-kindi18n.");
        return professionKindi18nMapper.toDTO(professionKindi18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionKindi18nDto}
     */
    @Override
    public ProfessionKindi18nDto getProfessionKindi18nById(ProfessionKindi18nPK id) {
        log.info("Fetching profession-kindi18n with id: {}", id);
        ProfessionKindi18n entity = professionKindi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionKindi18n not found with id: " + id));
        return professionKindi18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionKindi18nDto}
     */
    @Override
    public ProfessionKindi18nDto createProfessionKindi18n(ProfessionKindi18nDto dto) {
        log.info("Creating profession-kindi18n.");
        ProfessionKindi18n entity = professionKindi18nMapper.toEntity(dto);
        ProfessionKindi18n saved = professionKindi18nRepository.save(entity);
        return professionKindi18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionKindi18nDto}
     */
    @Override
    public ProfessionKindi18nDto updateProfessionKindi18n(ProfessionKindi18nPK id, ProfessionKindi18nDto dto) {
        log.info("Updating profession-kindi18n with id: {}", id);
        professionKindi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionKindi18n not found with id: " + id));
        ProfessionKindi18n entity = professionKindi18nMapper.toEntity(dto);
        entity.setId(id);
        ProfessionKindi18n saved = professionKindi18nRepository.save(entity);
        return professionKindi18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProfessionKindi18n(ProfessionKindi18nPK id) {
        log.info("Deleting profession-kindi18n with id: {}", id);
        professionKindi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionKindi18n not found with id: " + id));
        professionKindi18nRepository.deleteById(id);
    }
}
