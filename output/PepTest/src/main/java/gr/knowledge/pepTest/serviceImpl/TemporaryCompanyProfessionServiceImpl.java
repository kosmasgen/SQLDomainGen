package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import gr.knowledge.pepTest.mapper.TemporaryCompanyProfessionMapper;
import gr.knowledge.pepTest.entity.TemporaryCompanyProfession;
import gr.knowledge.pepTest.repository.TemporaryCompanyProfessionRepository;
import gr.knowledge.pepTest.service.TemporaryCompanyProfessionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code TemporaryCompanyProfession} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class TemporaryCompanyProfessionServiceImpl implements TemporaryCompanyProfessionService {

    private final TemporaryCompanyProfessionRepository temporaryCompanyProfessionRepository;
    private final TemporaryCompanyProfessionMapper temporaryCompanyProfessionMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link TemporaryCompanyProfessionDto}
     */
    @Override
    public List<TemporaryCompanyProfessionDto> getAllTemporaryCompanyProfession() {
        log.info("Fetching all temporary-company-profession.");
        return temporaryCompanyProfessionMapper.toDTO(temporaryCompanyProfessionRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link TemporaryCompanyProfessionDto}
     */
    @Override
    public TemporaryCompanyProfessionDto getTemporaryCompanyProfessionById(Long id) {
        log.info("Fetching temporary-company-profession with id: {}", id);
        TemporaryCompanyProfession entity = temporaryCompanyProfessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyProfession not found with id: " + id));
        return temporaryCompanyProfessionMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link TemporaryCompanyProfessionDto}
     */
    @Override
    public TemporaryCompanyProfessionDto createTemporaryCompanyProfession(TemporaryCompanyProfessionDto dto) {
        log.info("Creating temporary-company-profession.");
        TemporaryCompanyProfession entity = temporaryCompanyProfessionMapper.toEntity(dto);
        TemporaryCompanyProfession saved = temporaryCompanyProfessionRepository.save(entity);
        return temporaryCompanyProfessionMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link TemporaryCompanyProfessionDto}
     */
    @Override
    public TemporaryCompanyProfessionDto updateTemporaryCompanyProfession(Long id, TemporaryCompanyProfessionDto dto) {
        log.info("Updating temporary-company-profession with id: {}", id);
        temporaryCompanyProfessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyProfession not found with id: " + id));
        TemporaryCompanyProfession entity = temporaryCompanyProfessionMapper.toEntity(dto);
        entity.setId(id);
        TemporaryCompanyProfession saved = temporaryCompanyProfessionRepository.save(entity);
        return temporaryCompanyProfessionMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteTemporaryCompanyProfession(Long id) {
        log.info("Deleting temporary-company-profession with id: {}", id);
        temporaryCompanyProfessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TemporaryCompanyProfession not found with id: " + id));
        temporaryCompanyProfessionRepository.deleteById(id);
    }
}
