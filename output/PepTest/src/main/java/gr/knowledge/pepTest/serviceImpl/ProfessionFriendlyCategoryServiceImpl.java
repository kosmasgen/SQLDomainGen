package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.mapper.ProfessionFriendlyCategoryMapper;
import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import gr.knowledge.pepTest.repository.ProfessionFriendlyCategoryRepository;
import gr.knowledge.pepTest.service.ProfessionFriendlyCategoryService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Profession Friendly Category} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionFriendlyCategoryServiceImpl implements ProfessionFriendlyCategoryService {

    private final ProfessionFriendlyCategoryRepository professionFriendlyCategoryRepository;
    private final ProfessionFriendlyCategoryMapper professionFriendlyCategoryMapper;

    /**
     * Retrieves all profession friendly categories records.
     * @return list of ProfessionFriendlyCategoryDto
     */
    @Override
    public List<ProfessionFriendlyCategoryDto> getAllProfessionFriendlyCategories() {
        log.info("Fetching all profession friendly categories records.");
        return professionFriendlyCategoryMapper.toDTOList(professionFriendlyCategoryRepository.findAll());
    }

    /**
     * Retrieves a profession friendly category record by id.
     * @param id the profession friendly category id
     * @return ProfessionFriendlyCategoryDto
     */
    @Override
    public ProfessionFriendlyCategoryDto getProfessionFriendlyCategoryById(String id) {
        log.info("Fetching profession friendly category with id: {}", id);

        ProfessionFriendlyCategory existingEntity = findProfessionFriendlyCategoryByIdOrThrow(id);
        return professionFriendlyCategoryMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new profession friendly category record.
     * @param dto input payload
     * @return created {@link ProfessionFriendlyCategoryDto}
     */
    @Override
    public ProfessionFriendlyCategoryDto createProfessionFriendlyCategory(ProfessionFriendlyCategoryDto dto) {
        log.info("Creating profession friendly category.");

        ProfessionFriendlyCategory entity = professionFriendlyCategoryMapper.toEntity(dto);
        ProfessionFriendlyCategory savedEntity = professionFriendlyCategoryRepository.save(entity);

        return professionFriendlyCategoryMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing profession friendly category record.
     *
     * @param id the profession friendly category id
     * @param dto input payload
     * @return updated {@link ProfessionFriendlyCategoryDto}
     */
    @Override
    public ProfessionFriendlyCategoryDto updateProfessionFriendlyCategory(String id, ProfessionFriendlyCategoryDto dto) {
        log.info("Updating profession friendly category with id: {}", id);

        ProfessionFriendlyCategory existingEntity = findProfessionFriendlyCategoryByIdOrThrow(id);
        professionFriendlyCategoryMapper.partialUpdate(existingEntity, dto);
        ProfessionFriendlyCategory savedEntity = professionFriendlyCategoryRepository.save(existingEntity);

        return professionFriendlyCategoryMapper.toDTO(savedEntity);
    }

    /**
     * Delete a profession friendly category record by id.
     * @param id the profession friendly category id
     */
    @Override
    public void deleteProfessionFriendlyCategory(String id) {
        log.info("Deleting profession friendly category with id: {}", id);

        findProfessionFriendlyCategoryByIdOrThrow(id);
        professionFriendlyCategoryRepository.deleteById(id);
    }

    /**
     * Finds an existing profession friendly category record by id or throws an exception.
     * @param id the profession friendly category id
     * @return existing ProfessionFriendlyCategory entity
     */
    private ProfessionFriendlyCategory findProfessionFriendlyCategoryByIdOrThrow(String id) {
        return professionFriendlyCategoryRepository.findById(id)
                .orElseThrow(() -> createProfessionFriendlyCategoryNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the profession friendly category entity.
     @param id the profession friendly category id
     @return runtime exception
     */
    private RuntimeException createProfessionFriendlyCategoryNotFoundException(String id) {
        log.warn("ProfessionFriendlyCategory not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionFriendlyCategory")
                .message("ProfessionFriendlyCategory not found with id: " + id)
                .build();
    }

}
