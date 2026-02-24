package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.mapper.Producti18nMapper;
import gr.knowledge.pepTest.entity.Producti18n;
import gr.knowledge.pepTest.repository.Producti18nRepository;
import gr.knowledge.pepTest.service.Producti18nService;
import gr.knowledge.pepTest.entity.Producti18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Producti18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class Producti18nServiceImpl implements Producti18nService {

    private final Producti18nRepository producti18nRepository;
    private final Producti18nMapper producti18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link Producti18nDto}
     */
    @Override
    public List<Producti18nDto> getAllProducti18n() {
        log.info("Fetching all producti18n.");
        return producti18nMapper.toDTO(producti18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link Producti18nDto}
     */
    @Override
    public Producti18nDto getProducti18nById(Producti18nPK id) {
        log.info("Fetching producti18n with id: {}", id);
        Producti18n entity = producti18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producti18n not found with id: " + id));
        return producti18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link Producti18nDto}
     */
    @Override
    public Producti18nDto createProducti18n(Producti18nDto dto) {
        log.info("Creating producti18n.");
        Producti18n entity = producti18nMapper.toEntity(dto);
        Producti18n saved = producti18nRepository.save(entity);
        return producti18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link Producti18nDto}
     */
    @Override
    public Producti18nDto updateProducti18n(Producti18nPK id, Producti18nDto dto) {
        log.info("Updating producti18n with id: {}", id);
        producti18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producti18n not found with id: " + id));
        Producti18n entity = producti18nMapper.toEntity(dto);
        entity.setId(id);
        Producti18n saved = producti18nRepository.save(entity);
        return producti18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProducti18n(Producti18nPK id) {
        log.info("Deleting producti18n with id: {}", id);
        producti18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producti18n not found with id: " + id));
        producti18nRepository.deleteById(id);
    }
}
