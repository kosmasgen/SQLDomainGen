package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.mapper.ChAppUserContactMapper;
import gr.knowledge.pepTest.entity.ChAppUserContact;
import gr.knowledge.pepTest.repository.ChAppUserContactRepository;
import gr.knowledge.pepTest.service.ChAppUserContactService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Ch App User Contact} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ChAppUserContactServiceImpl implements ChAppUserContactService {

    private final ChAppUserContactRepository chAppUserContactRepository;
    private final ChAppUserContactMapper chAppUserContactMapper;

    /**
     * Retrieves all ch app user contacts records.
     * @return list of ChAppUserContactDto
     */
    @Override
    public List<ChAppUserContactDto> getAllChAppUserContacts() {
        log.info("Fetching all ch app user contacts records.");
        return chAppUserContactMapper.toDTOList(chAppUserContactRepository.findAll());
    }

    /**
     * Retrieves a ch app user contact record by id.
     * @param id the ch app user contact id
     * @return ChAppUserContactDto
     */
    @Override
    public ChAppUserContactDto getChAppUserContactById(UUID id) {
        log.info("Fetching ch app user contact with id: {}", id);

        ChAppUserContact existingEntity = findChAppUserContactByIdOrThrow(id);
        return chAppUserContactMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new ch app user contact record.
     * @param dto input payload
     * @return created {@link ChAppUserContactDto}
     */
    @Override
    public ChAppUserContactDto createChAppUserContact(ChAppUserContactDto dto) {
        log.info("Creating ch app user contact.");

        ChAppUserContact entity = chAppUserContactMapper.toEntity(dto);
        ChAppUserContact savedEntity = chAppUserContactRepository.save(entity);

        return chAppUserContactMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing ch app user contact record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the ch app user contact id
     * @param dto input payload with partial fields
     * @return updated {@link ChAppUserContactDto}
     */
    @Override
    public ChAppUserContactDto updateChAppUserContact(UUID id, ChAppUserContactDto dto) {
        log.info("Updating ch app user contact with id: {}", id);

        ChAppUserContact existingEntity = findChAppUserContactByIdOrThrow(id);
        chAppUserContactMapper.partialUpdate(existingEntity, dto);
        ChAppUserContact savedEntity = chAppUserContactRepository.save(existingEntity);

        return chAppUserContactMapper.toDTO(savedEntity);
    }

    /**
     * Delete a ch app user contact record by id.
     * @param id the ch app user contact id
     */
    @Override
    public void deleteChAppUserContact(UUID id) {
        log.info("Deleting ch app user contact with id: {}", id);

        findChAppUserContactByIdOrThrow(id);
        chAppUserContactRepository.deleteById(id);
    }

    /**
     * Finds an existing ch app user contact record by id or throws an exception.
     * @param id the ch app user contact id
     * @return existing ChAppUserContact entity
     */
    private ChAppUserContact findChAppUserContactByIdOrThrow(UUID id) {
        return chAppUserContactRepository.findById(id)
                .orElseThrow(() -> createChAppUserContactNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the ch app user contact entity.
     @param id the ch app user contact id
     @return runtime exception
     */
    private RuntimeException createChAppUserContactNotFoundException(UUID id) {
        log.warn("ChAppUserContact not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChAppUserContact")
                .message("ChAppUserContact not found with id: " + id)
                .build();
    }

}
