package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.mapper.CompanyStatusMapper;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.repository.CompanyStatusRepository;
import gr.knowledge.pepTest.service.CompanyStatusService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyStatus} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyStatusServiceImpl implements CompanyStatusService {

    private final CompanyStatusRepository companyStatusRepository;
    private final CompanyStatusMapper companyStatusMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyStatusDto}
     */
    @Override
    public List<CompanyStatusDto> getAllCompanyStatus() {
        log.info("Fetching all company-status.");
        return companyStatusMapper.toDTO(companyStatusRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyStatusDto}
     */
    @Override
    public CompanyStatusDto getCompanyStatusById(UUID id) {
        log.info("Fetching company-status with id: {}", id);
        CompanyStatus entity = companyStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatus not found with id: " + id));
        return companyStatusMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyStatusDto}
     */
    @Override
    public CompanyStatusDto createCompanyStatus(CompanyStatusDto dto) {
        log.info("Creating company-status.");
        CompanyStatus entity = companyStatusMapper.toEntity(dto);
        CompanyStatus saved = companyStatusRepository.save(entity);
        return companyStatusMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyStatusDto}
     */
    @Override
    public CompanyStatusDto updateCompanyStatus(UUID id, CompanyStatusDto dto) {
        log.info("Updating company-status with id: {}", id);
        companyStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatus not found with id: " + id));
        CompanyStatus entity = companyStatusMapper.toEntity(dto);
        entity.setId(id);
        CompanyStatus saved = companyStatusRepository.save(entity);
        return companyStatusMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyStatus(UUID id) {
        log.info("Deleting company-status with id: {}", id);
        companyStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyStatus not found with id: " + id));
        companyStatusRepository.deleteById(id);
    }
}
