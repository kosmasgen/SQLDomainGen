package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import gr.knowledge.pepTest.mapper.CompanyProfessionMapper;
import gr.knowledge.pepTest.entity.CompanyProfession;
import gr.knowledge.pepTest.repository.CompanyProfessionRepository;
import gr.knowledge.pepTest.service.CompanyProfessionService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyProfession} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyProfessionServiceImpl implements CompanyProfessionService {

    private final CompanyProfessionRepository companyProfessionRepository;
    private final CompanyProfessionMapper companyProfessionMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyProfessionDto}
     */
    @Override
    public List<CompanyProfessionDto> getAllCompanyProfession() {
        log.info("Fetching all company-profession.");
        return companyProfessionMapper.toDTO(companyProfessionRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyProfessionDto}
     */
    @Override
    public CompanyProfessionDto getCompanyProfessionById(UUID id) {
        log.info("Fetching company-profession with id: {}", id);
        CompanyProfession entity = companyProfessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfession not found with id: " + id));
        return companyProfessionMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyProfessionDto}
     */
    @Override
    public CompanyProfessionDto createCompanyProfession(CompanyProfessionDto dto) {
        log.info("Creating company-profession.");
        CompanyProfession entity = companyProfessionMapper.toEntity(dto);
        CompanyProfession saved = companyProfessionRepository.save(entity);
        return companyProfessionMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyProfessionDto}
     */
    @Override
    public CompanyProfessionDto updateCompanyProfession(UUID id, CompanyProfessionDto dto) {
        log.info("Updating company-profession with id: {}", id);
        companyProfessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfession not found with id: " + id));
        CompanyProfession entity = companyProfessionMapper.toEntity(dto);
        entity.setId(id);
        CompanyProfession saved = companyProfessionRepository.save(entity);
        return companyProfessionMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyProfession(UUID id) {
        log.info("Deleting company-profession with id: {}", id);
        companyProfessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfession not found with id: " + id));
        companyProfessionRepository.deleteById(id);
    }
}
