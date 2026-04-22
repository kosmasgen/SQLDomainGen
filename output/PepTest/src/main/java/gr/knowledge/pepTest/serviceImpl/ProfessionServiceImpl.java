package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.mapper.ProfessionMapper;
import gr.knowledge.pepTest.entity.Profession;
import gr.knowledge.pepTest.repository.ProfessionRepository;
import gr.knowledge.pepTest.service.ProfessionService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
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
     * Retrieves all professions records.
     * @return list of ProfessionDto
     */
    @Override
    public List<ProfessionDto> getAllProfessions() {
        log.info("Fetching all professions records.");
        return professionMapper.toDTOList(professionRepository.findAll());
    }

    /**
     * Retrieves a profession record by id.
     * @param id the profession id
     * @return ProfessionDto
     */
    @Override
    public ProfessionDto getProfessionById(UUID id) {
        log.info("Fetching profession with id: {}", id);

        Profession existingEntity = findProfessionByIdOrThrow(id);
        return professionMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new profession record.
     * @param dto input payload
     * @return created {@link ProfessionDto}
     */
    @Override
    public ProfessionDto createProfession(ProfessionDto dto) {
        log.info("Creating profession.");

        validateProfessionCreateUniqueConstraints(dto);

        Profession entity = professionMapper.toEntity(dto);
        Profession savedEntity = professionRepository.save(entity);

        return professionMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing profession record.
     *
     * @param id the profession id
     * @param dto input payload
     * @return updated {@link ProfessionDto}
     */
    @Override
    public ProfessionDto updateProfession(UUID id, ProfessionDto dto) {
        log.info("Updating profession with id: {}", id);

        Profession existingEntity = findProfessionByIdOrThrow(id);
        professionMapper.partialUpdate(existingEntity, dto);
        Profession savedEntity = professionRepository.save(existingEntity);

        return professionMapper.toDTO(savedEntity);
    }

    /**
     * Delete a profession record by id.
     * @param id the profession id
     */
    @Override
    public void deleteProfession(UUID id) {
        log.info("Deleting profession with id: {}", id);

        findProfessionByIdOrThrow(id);
        professionRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateProfessionCreateUniqueConstraints(ProfessionDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberProfessionId() != null && professionRepository.existsByChamberIdAndChamberProfessionId(dto.getChamberId(), dto.getChamberProfessionId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("Profession")
                    .message("Profession already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberProfessionId=" + dto.getChamberProfessionId())
                    .build();
        }
    }

    /**
     * Finds an existing profession record by id or throws an exception.
     * @param id the profession id
     * @return existing Profession entity
     */
    private Profession findProfessionByIdOrThrow(UUID id) {
        return professionRepository.findById(id)
                .orElseThrow(() -> createProfessionNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the profession entity.
     @param id the profession id
     @return runtime exception
     */
    private RuntimeException createProfessionNotFoundException(UUID id) {
        log.warn("Profession not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Profession")
                .message("Profession not found with id: " + id)
                .build();
    }

}
