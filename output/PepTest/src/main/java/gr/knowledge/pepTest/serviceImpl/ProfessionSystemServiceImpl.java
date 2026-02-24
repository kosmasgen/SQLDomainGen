package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.mapper.ProfessionSystemMapper;
import gr.knowledge.pepTest.entity.ProfessionSystem;
import gr.knowledge.pepTest.repository.ProfessionSystemRepository;
import gr.knowledge.pepTest.service.ProfessionSystemService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ProfessionSystem} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionSystemServiceImpl implements ProfessionSystemService {

    private final ProfessionSystemRepository professionSystemRepository;
    private final ProfessionSystemMapper professionSystemMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionSystemDto}
     */
    @Override
    public List<ProfessionSystemDto> getAllProfessionSystem() {
        log.info("Fetching all profession-system.");
        return professionSystemMapper.toDTO(professionSystemRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionSystemDto}
     */
    @Override
    public ProfessionSystemDto getProfessionSystemById(UUID id) {
        log.info("Fetching profession-system with id: {}", id);
        ProfessionSystem entity = professionSystemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionSystem not found with id: " + id));
        return professionSystemMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionSystemDto}
     */
    @Override
    public ProfessionSystemDto createProfessionSystem(ProfessionSystemDto dto) {
        log.info("Creating profession-system.");
        ProfessionSystem entity = professionSystemMapper.toEntity(dto);
        ProfessionSystem saved = professionSystemRepository.save(entity);
        return professionSystemMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionSystemDto}
     */
    @Override
    public ProfessionSystemDto updateProfessionSystem(UUID id, ProfessionSystemDto dto) {
        log.info("Updating profession-system with id: {}", id);
        professionSystemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionSystem not found with id: " + id));
        ProfessionSystem entity = professionSystemMapper.toEntity(dto);
        entity.setId(id);
        ProfessionSystem saved = professionSystemRepository.save(entity);
        return professionSystemMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProfessionSystem(UUID id) {
        log.info("Deleting profession-system with id: {}", id);
        professionSystemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionSystem not found with id: " + id));
        professionSystemRepository.deleteById(id);
    }
}
