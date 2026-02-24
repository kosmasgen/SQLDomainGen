package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.mapper.ProfessionMapper;
import gr.knowledge.pepTest.entity.Profession;
import gr.knowledge.pepTest.repository.ProfessionRepository;
import gr.knowledge.pepTest.service.ProfessionService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Profession} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository professionRepository;
    private final ProfessionMapper professionMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionDto}
     */
    @Override
    public List<ProfessionDto> getAllProfession() {
        log.info("Fetching all profession.");
        return professionMapper.toDTO(professionRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionDto}
     */
    @Override
    public ProfessionDto getProfessionById(UUID id) {
        log.info("Fetching profession with id: {}", id);
        Profession entity = professionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profession not found with id: " + id));
        return professionMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionDto}
     */
    @Override
    public ProfessionDto createProfession(ProfessionDto dto) {
        log.info("Creating profession.");
        Profession entity = professionMapper.toEntity(dto);
        Profession saved = professionRepository.save(entity);
        return professionMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionDto}
     */
    @Override
    public ProfessionDto updateProfession(UUID id, ProfessionDto dto) {
        log.info("Updating profession with id: {}", id);
        professionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profession not found with id: " + id));
        Profession entity = professionMapper.toEntity(dto);
        entity.setId(id);
        Profession saved = professionRepository.save(entity);
        return professionMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProfession(UUID id) {
        log.info("Deleting profession with id: {}", id);
        professionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profession not found with id: " + id));
        professionRepository.deleteById(id);
    }
}
