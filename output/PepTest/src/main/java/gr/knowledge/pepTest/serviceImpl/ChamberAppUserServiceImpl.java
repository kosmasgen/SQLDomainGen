package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.mapper.ChamberAppUserMapper;
import gr.knowledge.pepTest.entity.ChamberAppUser;
import gr.knowledge.pepTest.repository.ChamberAppUserRepository;
import gr.knowledge.pepTest.service.ChamberAppUserService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Chamber App User} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChamberAppUserServiceImpl implements ChamberAppUserService {

    private final ChamberAppUserRepository chamberAppUserRepository;
    private final ChamberAppUserMapper chamberAppUserMapper;

    /**
     * Retrieves all chamber app users records.
     * @return list of ChamberAppUserDto
     */
    @Override
    public List<ChamberAppUserDto> getAllChamberAppUsers() {
        log.info("Fetching all chamber app users records.");
        return chamberAppUserMapper.toDTOList(chamberAppUserRepository.findAll());
    }

    /**
     * Retrieves a chamber app user record by id.
     * @param id the chamber app user id
     * @return ChamberAppUserDto
     */
    @Override
    public ChamberAppUserDto getChamberAppUserById(UUID id) {
        log.info("Fetching chamber app user with id: {}", id);

        ChamberAppUser existingEntity = findChamberAppUserByIdOrThrow(id);
        return chamberAppUserMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new chamber app user record.
     * @param dto input payload
     * @return created {@link ChamberAppUserDto}
     */
    @Override
    public ChamberAppUserDto createChamberAppUser(ChamberAppUserDto dto) {
        log.info("Creating chamber app user.");

        ChamberAppUser entity = chamberAppUserMapper.toEntity(dto);
        ChamberAppUser savedEntity = chamberAppUserRepository.save(entity);

        return chamberAppUserMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing chamber app user record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the chamber app user id
     * @param dto input payload with partial fields
     * @return updated {@link ChamberAppUserDto}
     */
    @Override
    public ChamberAppUserDto updateChamberAppUser(UUID id, ChamberAppUserDto dto) {
        log.info("Updating chamber app user with id: {}", id);

        ChamberAppUser existingEntity = findChamberAppUserByIdOrThrow(id);
        chamberAppUserMapper.partialUpdate(existingEntity, dto);
        ChamberAppUser savedEntity = chamberAppUserRepository.save(existingEntity);

        return chamberAppUserMapper.toDTO(savedEntity);
    }

    /**
     * Delete a chamber app user record by id.
     * @param id the chamber app user id
     */
    @Override
    public void deleteChamberAppUser(UUID id) {
        log.info("Deleting chamber app user with id: {}", id);

        findChamberAppUserByIdOrThrow(id);
        chamberAppUserRepository.deleteById(id);
    }

    /**
     * Finds an existing chamber app user record by id or throws an exception.
     * @param id the chamber app user id
     * @return existing ChamberAppUser entity
     */
    private ChamberAppUser findChamberAppUserByIdOrThrow(UUID id) {
        return chamberAppUserRepository.findById(id)
                .orElseThrow(() -> createChamberAppUserNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the chamber app user entity.
     @param id the chamber app user id
     @return runtime exception
     */
    private RuntimeException createChamberAppUserNotFoundException(UUID id) {
        log.warn("ChamberAppUser not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChamberAppUser")
                .message("ChamberAppUser not found with id: " + id)
                .build();
    }

}
