package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.mapper.ProfessionKindMapper;
import gr.knowledge.pepTest.entity.ProfessionKind;
import gr.knowledge.pepTest.repository.ProfessionKindRepository;
import gr.knowledge.pepTest.service.ProfessionKindService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ProfessionKind} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionKindServiceImpl implements ProfessionKindService {

    private final ProfessionKindRepository professionKindRepository;
    private final ProfessionKindMapper professionKindMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionKindDto}
     */
    @Override
    public List<ProfessionKindDto> getAllProfessionKind() {
        log.info("Fetching all profession-kind.");
        return professionKindMapper.toDTO(professionKindRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionKindDto}
     */
    @Override
    public ProfessionKindDto getProfessionKindById(UUID id) {
        log.info("Fetching profession-kind with id: {}", id);
        ProfessionKind entity = professionKindRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionKind not found with id: " + id));
        return professionKindMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionKindDto}
     */
    @Override
    public ProfessionKindDto createProfessionKind(ProfessionKindDto dto) {
        log.info("Creating profession-kind.");
        ProfessionKind entity = professionKindMapper.toEntity(dto);
        ProfessionKind saved = professionKindRepository.save(entity);
        return professionKindMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionKindDto}
     */
    @Override
    public ProfessionKindDto updateProfessionKind(UUID id, ProfessionKindDto dto) {
        log.info("Updating profession-kind with id: {}", id);
        professionKindRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionKind not found with id: " + id));
        ProfessionKind entity = professionKindMapper.toEntity(dto);
        entity.setId(id);
        ProfessionKind saved = professionKindRepository.save(entity);
        return professionKindMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProfessionKind(UUID id) {
        log.info("Deleting profession-kind with id: {}", id);
        professionKindRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionKind not found with id: " + id));
        professionKindRepository.deleteById(id);
    }
}
