package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.mapper.CorporateStatusMapper;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.repository.CorporateStatusRepository;
import gr.knowledge.pepTest.service.CorporateStatusService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Corporate Status} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CorporateStatusServiceImpl implements CorporateStatusService {

    private final CorporateStatusRepository corporateStatusRepository;
    private final CorporateStatusMapper corporateStatusMapper;

    /**
     * Retrieves all corporate statuses records.
     * @return list of CorporateStatusDto
     */
    @Override
    public List<CorporateStatusDto> getAllCorporateStatuses() {
        log.info("Fetching all corporate statuses records.");
        return corporateStatusMapper.toDTOList(corporateStatusRepository.findAll());
    }

    /**
     * Retrieves a corporate status record by id.
     * @param id the corporate status id
     * @return CorporateStatusDto
     */
    @Override
    public CorporateStatusDto getCorporateStatusById(UUID id) {
        log.info("Fetching corporate status with id: {}", id);

        CorporateStatus existingEntity = findCorporateStatusByIdOrThrow(id);
        return corporateStatusMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new corporate status record.
     * @param dto input payload
     * @return created {@link CorporateStatusDto}
     */
    @Override
    public CorporateStatusDto createCorporateStatus(CorporateStatusDto dto) {
        log.info("Creating corporate status.");

        validateCorporateStatusCreateUniqueConstraints(dto);

        CorporateStatus entity = corporateStatusMapper.toEntity(dto);
        CorporateStatus savedEntity = corporateStatusRepository.save(entity);

        return corporateStatusMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing corporate status record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the corporate status id
     * @param dto input payload with partial fields
     * @return updated {@link CorporateStatusDto}
     */
    @Override
    public CorporateStatusDto updateCorporateStatus(UUID id, CorporateStatusDto dto) {
        log.info("Updating corporate status with id: {}", id);

        CorporateStatus existingEntity = findCorporateStatusByIdOrThrow(id);
        corporateStatusMapper.partialUpdate(existingEntity, dto);
        CorporateStatus savedEntity = corporateStatusRepository.save(existingEntity);

        return corporateStatusMapper.toDTO(savedEntity);
    }

    /**
     * Delete a corporate status record by id.
     * @param id the corporate status id
     */
    @Override
    public void deleteCorporateStatus(UUID id) {
        log.info("Deleting corporate status with id: {}", id);

        findCorporateStatusByIdOrThrow(id);
        corporateStatusRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCorporateStatusCreateUniqueConstraints(CorporateStatusDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberCorporateStatusId() != null && corporateStatusRepository.existsByChamberIdAndChamberCorporateStatusId(dto.getChamberId(), dto.getChamberCorporateStatusId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CorporateStatus")
                    .message("CorporateStatus already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberCorporateStatusId=" + dto.getChamberCorporateStatusId())
                    .build();
        }
    }

    /**
     * Finds an existing corporate status record by id or throws an exception.
     * @param id the corporate status id
     * @return existing CorporateStatus entity
     */
    private CorporateStatus findCorporateStatusByIdOrThrow(UUID id) {
        return corporateStatusRepository.findById(id)
                .orElseThrow(() -> createCorporateStatusNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the corporate status entity.
     @param id the corporate status id
     @return runtime exception
     */
    private RuntimeException createCorporateStatusNotFoundException(UUID id) {
        log.warn("CorporateStatus not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CorporateStatus")
                .message("CorporateStatus not found with id: " + id)
                .build();
    }

}
