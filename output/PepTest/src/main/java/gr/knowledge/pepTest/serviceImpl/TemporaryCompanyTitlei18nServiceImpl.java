package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitlei18nMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyTitlei18n;
import gr.knowledge.pepTest.repository.TemporaryCompanyTitlei18nRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyTitlei18nService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code TemporaryCompanyTitlei18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyTitlei18nServiceImpl implements TemporaryCompanyTitlei18nService {

    private final TemporaryCompanyTitlei18nRepository temporaryCompanyTitlei18nRepository;
    private final TemporaryCompanyTitlei18nMapper temporaryCompanyTitlei18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyTitlei18nDto}
     */
    @Override
    public List<TemporaryCompanyTitlei18nDto> getAllTemporaryCompanyTitlei18n() {
        log.info("Fetching all temporary-company-titlei18n.");
        return temporaryCompanyTitlei18nMapper.toDTO(temporaryCompanyTitlei18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyTitlei18nDto}
     */
    @Override
    public TemporaryCompanyTitlei18nDto getTemporaryCompanyTitlei18nById(Long id) {
        log.info("Fetching temporary-company-titlei18n with id: {}", id);
        TemporaryCompanyTitlei18n entity = temporaryCompanyTitlei18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyTitlei18n not found with id: " + id));
        return temporaryCompanyTitlei18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitlei18nDto}
     */
    @Override
    public TemporaryCompanyTitlei18nDto createTemporaryCompanyTitlei18n(TemporaryCompanyTitlei18nDto dto) {
        log.info("Creating temporary-company-titlei18n.");
        TemporaryCompanyTitlei18n entity = temporaryCompanyTitlei18nMapper.toEntity(dto);
        TemporaryCompanyTitlei18n saved = temporaryCompanyTitlei18nRepository.save(entity);
        return temporaryCompanyTitlei18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyTitlei18nDto}
     */
    @Override
    public TemporaryCompanyTitlei18nDto updateTemporaryCompanyTitlei18n(Long id, TemporaryCompanyTitlei18nDto dto) {
        log.info("Updating temporary-company-titlei18n with id: {}", id);
        temporaryCompanyTitlei18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyTitlei18n not found with id: " + id));
        TemporaryCompanyTitlei18n entity = temporaryCompanyTitlei18nMapper.toEntity(dto);
        entity.setId(id);
        TemporaryCompanyTitlei18n saved = temporaryCompanyTitlei18nRepository.save(entity);
        return temporaryCompanyTitlei18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteTemporaryCompanyTitlei18n(Long id) {
        log.info("Deleting temporary-company-titlei18n with id: {}", id);
        temporaryCompanyTitlei18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyTitlei18n not found with id: " + id));
        temporaryCompanyTitlei18nRepository.deleteById(id);
    }
}
