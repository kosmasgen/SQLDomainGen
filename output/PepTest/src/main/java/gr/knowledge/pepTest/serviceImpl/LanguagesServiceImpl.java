package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.mapper.LanguagesMapper;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.repository.LanguagesRepository;
import gr.knowledge.pepTest.service.LanguagesService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Languages} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LanguagesServiceImpl implements LanguagesService {

    private final LanguagesRepository languagesRepository;
    private final LanguagesMapper languagesMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link LanguagesDto}
     */
    @Override
    public List<LanguagesDto> getAllLanguages() {
        log.info("Fetching all languages.");
        return languagesMapper.toDTO(languagesRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link LanguagesDto}
     */
    @Override
    public LanguagesDto getLanguagesById(UUID id) {
        log.info("Fetching languages with id: {}", id);
        Languages entity = languagesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Languages not found with id: " + id));
        return languagesMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link LanguagesDto}
     */
    @Override
    public LanguagesDto createLanguages(LanguagesDto dto) {
        log.info("Creating languages.");
        Languages entity = languagesMapper.toEntity(dto);
        Languages saved = languagesRepository.save(entity);
        return languagesMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link LanguagesDto}
     */
    @Override
    public LanguagesDto updateLanguages(UUID id, LanguagesDto dto) {
        log.info("Updating languages with id: {}", id);
        languagesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Languages not found with id: " + id));
        Languages entity = languagesMapper.toEntity(dto);
        entity.setId(id);
        Languages saved = languagesRepository.save(entity);
        return languagesMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteLanguages(UUID id) {
        log.info("Deleting languages with id: {}", id);
        languagesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Languages not found with id: " + id));
        languagesRepository.deleteById(id);
    }
}
