package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.mapper.ProfessionKindMapper;
import gr.knowledge.pepTest.entity.ProfessionKind;
import gr.knowledge.pepTest.repository.ProfessionKindRepository;
import gr.knowledge.pepTest.service.ProfessionKindService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Profession Kind} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProfessionKindServiceImpl implements ProfessionKindService {

    private final ProfessionKindRepository professionKindRepository;
    private final ProfessionKindMapper professionKindMapper;

    /**
     * Retrieves all profession kinds records.
     * @return list of ProfessionKindDto
     */
    @Override
    public List<ProfessionKindDto> getAllProfessionKinds() {
        log.info("Fetching all profession kinds records.");
        return professionKindMapper.toDTOList(professionKindRepository.findAll());
    }

    /**
     * Retrieves a profession kind record by id.
     * @param id the profession kind id
     * @return ProfessionKindDto
     */
    @Override
    public ProfessionKindDto getProfessionKindById(UUID id) {
        log.info("Fetching profession kind with id: {}", id);

        ProfessionKind existingEntity = findProfessionKindByIdOrThrow(id);
        return professionKindMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new profession kind record.
     * @param dto input payload
     * @return created {@link ProfessionKindDto}
     */
    @Override
    public ProfessionKindDto createProfessionKind(ProfessionKindDto dto) {
        log.info("Creating profession kind.");

        validateProfessionKindCreateUniqueConstraints(dto);

        ProfessionKind entity = professionKindMapper.toEntity(dto);
        ProfessionKind savedEntity = professionKindRepository.save(entity);

        return professionKindMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing profession kind record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the profession kind id
     * @param dto input payload with partial fields
     * @return updated {@link ProfessionKindDto}
     */
    @Override
    public ProfessionKindDto updateProfessionKind(UUID id, ProfessionKindDto dto) {
        log.info("Updating profession kind with id: {}", id);

        ProfessionKind existingEntity = findProfessionKindByIdOrThrow(id);
        professionKindMapper.partialUpdate(existingEntity, dto);
        ProfessionKind savedEntity = professionKindRepository.save(existingEntity);

        return professionKindMapper.toDTO(savedEntity);
    }

    /**
     * Delete a profession kind record by id.
     * @param id the profession kind id
     */
    @Override
    public void deleteProfessionKind(UUID id) {
        log.info("Deleting profession kind with id: {}", id);

        findProfessionKindByIdOrThrow(id);
        professionKindRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateProfessionKindCreateUniqueConstraints(ProfessionKindDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberProfKindId() != null && professionKindRepository.existsByChamberIdAndChamberProfKindId(dto.getChamberId(), dto.getChamberProfKindId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("ProfessionKind")
                    .message("ProfessionKind already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberProfKindId=" + dto.getChamberProfKindId())
                    .build();
        }
    }

    /**
     * Finds an existing profession kind record by id or throws an exception.
     * @param id the profession kind id
     * @return existing ProfessionKind entity
     */
    private ProfessionKind findProfessionKindByIdOrThrow(UUID id) {
        return professionKindRepository.findById(id)
                .orElseThrow(() -> createProfessionKindNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the profession kind entity.
     @param id the profession kind id
     @return runtime exception
     */
    private RuntimeException createProfessionKindNotFoundException(UUID id) {
        log.warn("ProfessionKind not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionKind")
                .message("ProfessionKind not found with id: " + id)
                .build();
    }

}
