package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.mapper.ProfessionSystemMapper;
import gr.knowledge.pepTest.entity.ProfessionSystem;
import gr.knowledge.pepTest.repository.ProfessionSystemRepository;
import gr.knowledge.pepTest.service.ProfessionSystemService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Profession System} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionSystemServiceImpl implements ProfessionSystemService {

    private final ProfessionSystemRepository professionSystemRepository;
    private final ProfessionSystemMapper professionSystemMapper;

    /**
     * Retrieves all profession systems records.
     * @return list of ProfessionSystemDto
     */
    @Override
    public List<ProfessionSystemDto> getAllProfessionSystems() {
        log.info("Fetching all profession systems records.");
        return professionSystemMapper.toDTOList(professionSystemRepository.findAll());
    }

    /**
     * Retrieves a profession system record by id.
     * @param id the profession system id
     * @return ProfessionSystemDto
     */
    @Override
    public ProfessionSystemDto getProfessionSystemById(UUID id) {
        log.info("Fetching profession system with id: {}", id);

        ProfessionSystem existingEntity = findProfessionSystemByIdOrThrow(id);
        return professionSystemMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new profession system record.
     * @param dto input payload
     * @return created {@link ProfessionSystemDto}
     */
    @Override
    public ProfessionSystemDto createProfessionSystem(ProfessionSystemDto dto) {
        log.info("Creating profession system.");

        validateProfessionSystemCreateUniqueConstraints(dto);

        ProfessionSystem entity = professionSystemMapper.toEntity(dto);
        ProfessionSystem savedEntity = professionSystemRepository.save(entity);

        return professionSystemMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing profession system record.
     *
     * @param id the profession system id
     * @param dto input payload
     * @return updated {@link ProfessionSystemDto}
     */
    @Override
    public ProfessionSystemDto updateProfessionSystem(UUID id, ProfessionSystemDto dto) {
        log.info("Updating profession system with id: {}", id);

        ProfessionSystem existingEntity = findProfessionSystemByIdOrThrow(id);
        professionSystemMapper.partialUpdate(existingEntity, dto);
        ProfessionSystem savedEntity = professionSystemRepository.save(existingEntity);

        return professionSystemMapper.toDTO(savedEntity);
    }

    /**
     * Delete a profession system record by id.
     * @param id the profession system id
     */
    @Override
    public void deleteProfessionSystem(UUID id) {
        log.info("Deleting profession system with id: {}", id);

        findProfessionSystemByIdOrThrow(id);
        professionSystemRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateProfessionSystemCreateUniqueConstraints(ProfessionSystemDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberProfSystemId() != null && professionSystemRepository.existsByChamberIdAndChamberProfSystemId(dto.getChamberId(), dto.getChamberProfSystemId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ProfessionSystem")
                    .message("ProfessionSystem already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberProfSystemId=" + dto.getChamberProfSystemId())
                    .build();
        }
    }

    /**
     * Finds an existing profession system record by id or throws an exception.
     * @param id the profession system id
     * @return existing ProfessionSystem entity
     */
    private ProfessionSystem findProfessionSystemByIdOrThrow(UUID id) {
        return professionSystemRepository.findById(id)
                .orElseThrow(() -> createProfessionSystemNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the profession system entity.
     @param id the profession system id
     @return runtime exception
     */
    private RuntimeException createProfessionSystemNotFoundException(UUID id) {
        log.warn("ProfessionSystem not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionSystem")
                .message("ProfessionSystem not found with id: " + id)
                .build();
    }

}
