package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitleMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyTitle;
import gr.knowledge.pepTest.repository.TemporaryCompanyTitleRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyTitleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code TemporaryCompanyTitle} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyTitleServiceImpl implements TemporaryCompanyTitleService {

    private final TemporaryCompanyTitleRepository temporaryCompanyTitleRepository;
    private final TemporaryCompanyTitleMapper temporaryCompanyTitleMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyTitleDto}
     */
    @Override
    public List<TemporaryCompanyTitleDto> getAllTemporaryCompanyTitle() {
        log.info("Fetching all temporary-company-title.");
        return temporaryCompanyTitleMapper.toDTO(temporaryCompanyTitleRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyTitleDto}
     */
    @Override
    public TemporaryCompanyTitleDto getTemporaryCompanyTitleById(Long id) {
        log.info("Fetching temporary-company-title with id: {}", id);
        TemporaryCompanyTitle entity = temporaryCompanyTitleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyTitle not found with id: " + id));
        return temporaryCompanyTitleMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyTitleDto}
     */
    @Override
    public TemporaryCompanyTitleDto createTemporaryCompanyTitle(TemporaryCompanyTitleDto dto) {
        log.info("Creating temporary-company-title.");
        TemporaryCompanyTitle entity = temporaryCompanyTitleMapper.toEntity(dto);
        TemporaryCompanyTitle saved = temporaryCompanyTitleRepository.save(entity);
        return temporaryCompanyTitleMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyTitleDto}
     */
    @Override
    public TemporaryCompanyTitleDto updateTemporaryCompanyTitle(Long id, TemporaryCompanyTitleDto dto) {
        log.info("Updating temporary-company-title with id: {}", id);
        temporaryCompanyTitleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyTitle not found with id: " + id));
        TemporaryCompanyTitle entity = temporaryCompanyTitleMapper.toEntity(dto);
        entity.setId(id);
        TemporaryCompanyTitle saved = temporaryCompanyTitleRepository.save(entity);
        return temporaryCompanyTitleMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteTemporaryCompanyTitle(Long id) {
        log.info("Deleting temporary-company-title with id: {}", id);
        temporaryCompanyTitleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyTitle not found with id: " + id));
        temporaryCompanyTitleRepository.deleteById(id);
    }
}
