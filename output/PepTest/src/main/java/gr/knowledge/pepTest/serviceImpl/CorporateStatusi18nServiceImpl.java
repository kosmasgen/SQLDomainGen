package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.mapper.CorporateStatusi18nMapper;
import gr.knowledge.pepTest.entity.CorporateStatusi18n;
import gr.knowledge.pepTest.repository.CorporateStatusi18nRepository;
import gr.knowledge.pepTest.service.CorporateStatusi18nService;
import gr.knowledge.pepTest.entity.CorporateStatusi18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CorporateStatusi18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CorporateStatusi18nServiceImpl implements CorporateStatusi18nService {

    private final CorporateStatusi18nRepository corporateStatusi18nRepository;
    private final CorporateStatusi18nMapper corporateStatusi18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CorporateStatusi18nDto}
     */
    @Override
    public List<CorporateStatusi18nDto> getAllCorporateStatusi18n() {
        log.info("Fetching all corporate-statusi18n.");
        return corporateStatusi18nMapper.toDTO(corporateStatusi18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CorporateStatusi18nDto}
     */
    @Override
    public CorporateStatusi18nDto getCorporateStatusi18nById(CorporateStatusi18nPK id) {
        log.info("Fetching corporate-statusi18n with id: {}", id);
        CorporateStatusi18n entity = corporateStatusi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatusi18n not found with id: " + id));
        return corporateStatusi18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CorporateStatusi18nDto}
     */
    @Override
    public CorporateStatusi18nDto createCorporateStatusi18n(CorporateStatusi18nDto dto) {
        log.info("Creating corporate-statusi18n.");
        CorporateStatusi18n entity = corporateStatusi18nMapper.toEntity(dto);
        CorporateStatusi18n saved = corporateStatusi18nRepository.save(entity);
        return corporateStatusi18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CorporateStatusi18nDto}
     */
    @Override
    public CorporateStatusi18nDto updateCorporateStatusi18n(CorporateStatusi18nPK id, CorporateStatusi18nDto dto) {
        log.info("Updating corporate-statusi18n with id: {}", id);
        corporateStatusi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatusi18n not found with id: " + id));
        CorporateStatusi18n entity = corporateStatusi18nMapper.toEntity(dto);
        entity.setId(id);
        CorporateStatusi18n saved = corporateStatusi18nRepository.save(entity);
        return corporateStatusi18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCorporateStatusi18n(CorporateStatusi18nPK id) {
        log.info("Deleting corporate-statusi18n with id: {}", id);
        corporateStatusi18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatusi18n not found with id: " + id));
        corporateStatusi18nRepository.deleteById(id);
    }
}
