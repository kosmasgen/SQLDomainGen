package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.mapper.ProfessionFriendlyCategoryMapper;
import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import gr.knowledge.pepTest.repository.ProfessionFriendlyCategoryRepository;
import gr.knowledge.pepTest.service.ProfessionFriendlyCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code ProfessionFriendlyCategory} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionFriendlyCategoryServiceImpl implements ProfessionFriendlyCategoryService {

    private final ProfessionFriendlyCategoryRepository professionFriendlyCategoryRepository;
    private final ProfessionFriendlyCategoryMapper professionFriendlyCategoryMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProfessionFriendlyCategoryDto}
     */
    @Override
    public List<ProfessionFriendlyCategoryDto> getAllProfessionFriendlyCategory() {
        log.info("Fetching all profession-friendly-category.");
        return professionFriendlyCategoryMapper.toDTO(professionFriendlyCategoryRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProfessionFriendlyCategoryDto}
     */
    @Override
    public ProfessionFriendlyCategoryDto getProfessionFriendlyCategoryById(String id) {
        log.info("Fetching profession-friendly-category with id: {}", id);
        ProfessionFriendlyCategory entity = professionFriendlyCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionFriendlyCategory not found with id: " + id));
        return professionFriendlyCategoryMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProfessionFriendlyCategoryDto}
     */
    @Override
    public ProfessionFriendlyCategoryDto createProfessionFriendlyCategory(ProfessionFriendlyCategoryDto dto) {
        log.info("Creating profession-friendly-category.");
        ProfessionFriendlyCategory entity = professionFriendlyCategoryMapper.toEntity(dto);
        ProfessionFriendlyCategory saved = professionFriendlyCategoryRepository.save(entity);
        return professionFriendlyCategoryMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProfessionFriendlyCategoryDto}
     */
    @Override
    public ProfessionFriendlyCategoryDto updateProfessionFriendlyCategory(String id, ProfessionFriendlyCategoryDto dto) {
        log.info("Updating profession-friendly-category with id: {}", id);
        professionFriendlyCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionFriendlyCategory not found with id: " + id));
        ProfessionFriendlyCategory entity = professionFriendlyCategoryMapper.toEntity(dto);
        entity.setId(id);
        ProfessionFriendlyCategory saved = professionFriendlyCategoryRepository.save(entity);
        return professionFriendlyCategoryMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteProfessionFriendlyCategory(String id) {
        log.info("Deleting profession-friendly-category with id: {}", id);
        professionFriendlyCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProfessionFriendlyCategory not found with id: " + id));
        professionFriendlyCategoryRepository.deleteById(id);
    }
}
