package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import gr.knowledge.pepTest.mapper.BgPoiI18nMapper;
import gr.knowledge.pepTest.entity.BgPoiI18n;
import gr.knowledge.pepTest.repository.BgPoiI18nRepository;
import gr.knowledge.pepTest.service.BgPoiI18nService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code BgPoiI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BgPoiI18nServiceImpl implements BgPoiI18nService {

    private final BgPoiI18nRepository bgPoiI18nRepository;
    private final BgPoiI18nMapper bgPoiI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link BgPoiI18nDto}
     */
    @Override
    public List<BgPoiI18nDto> getAllBgPoiI18n() {
        log.info("Fetching all bg-poi-i18n.");
        return bgPoiI18nMapper.toDTO(bgPoiI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link BgPoiI18nDto}
     */
    @Override
    public BgPoiI18nDto getBgPoiI18nById(UUID id) {
        log.info("Fetching bg-poi-i18n with id: {}", id);
        BgPoiI18n entity = bgPoiI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BgPoiI18n not found with id: " + id));
        return bgPoiI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link BgPoiI18nDto}
     */
    @Override
    public BgPoiI18nDto createBgPoiI18n(BgPoiI18nDto dto) {
        log.info("Creating bg-poi-i18n.");
        BgPoiI18n entity = bgPoiI18nMapper.toEntity(dto);
        BgPoiI18n saved = bgPoiI18nRepository.save(entity);
        return bgPoiI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link BgPoiI18nDto}
     */
    @Override
    public BgPoiI18nDto updateBgPoiI18n(UUID id, BgPoiI18nDto dto) {
        log.info("Updating bg-poi-i18n with id: {}", id);
        bgPoiI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BgPoiI18n not found with id: " + id));
        BgPoiI18n entity = bgPoiI18nMapper.toEntity(dto);
        entity.setId(id);
        BgPoiI18n saved = bgPoiI18nRepository.save(entity);
        return bgPoiI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteBgPoiI18n(UUID id) {
        log.info("Deleting bg-poi-i18n with id: {}", id);
        bgPoiI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BgPoiI18n not found with id: " + id));
        bgPoiI18nRepository.deleteById(id);
    }
}
