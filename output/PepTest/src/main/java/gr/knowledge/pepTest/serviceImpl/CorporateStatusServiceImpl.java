package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.mapper.CorporateStatusMapper;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.repository.CorporateStatusRepository;
import gr.knowledge.pepTest.service.CorporateStatusService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CorporateStatus} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CorporateStatusServiceImpl implements CorporateStatusService {

    private final CorporateStatusRepository corporateStatusRepository;
    private final CorporateStatusMapper corporateStatusMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CorporateStatusDto}
     */
    @Override
    public List<CorporateStatusDto> getAllCorporateStatus() {
        log.info("Fetching all corporate-status.");
        return corporateStatusMapper.toDTO(corporateStatusRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CorporateStatusDto}
     */
    @Override
    public CorporateStatusDto getCorporateStatusById(UUID id) {
        log.info("Fetching corporate-status with id: {}", id);
        CorporateStatus entity = corporateStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatus not found with id: " + id));
        return corporateStatusMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CorporateStatusDto}
     */
    @Override
    public CorporateStatusDto createCorporateStatus(CorporateStatusDto dto) {
        log.info("Creating corporate-status.");
        CorporateStatus entity = corporateStatusMapper.toEntity(dto);
        CorporateStatus saved = corporateStatusRepository.save(entity);
        return corporateStatusMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CorporateStatusDto}
     */
    @Override
    public CorporateStatusDto updateCorporateStatus(UUID id, CorporateStatusDto dto) {
        log.info("Updating corporate-status with id: {}", id);
        corporateStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatus not found with id: " + id));
        CorporateStatus entity = corporateStatusMapper.toEntity(dto);
        entity.setId(id);
        CorporateStatus saved = corporateStatusRepository.save(entity);
        return corporateStatusMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCorporateStatus(UUID id) {
        log.info("Deleting corporate-status with id: {}", id);
        corporateStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CorporateStatus not found with id: " + id));
        corporateStatusRepository.deleteById(id);
    }
}
