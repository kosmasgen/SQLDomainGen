package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.mapper.CompanyMapper;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.repository.CompanyRepository;
import gr.knowledge.pepTest.service.CompanyService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code Company} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyDto}
     */
    @Override
    public List<CompanyDto> getAllCompany() {
        log.info("Fetching all company.");
        return companyMapper.toDTO(companyRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyDto}
     */
    @Override
    public CompanyDto getCompanyById(UUID id) {
        log.info("Fetching company with id: {}", id);
        Company entity = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));
        return companyMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyDto}
     */
    @Override
    public CompanyDto createCompany(CompanyDto dto) {
        log.info("Creating company.");
        Company entity = companyMapper.toEntity(dto);
        Company saved = companyRepository.save(entity);
        return companyMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyDto}
     */
    @Override
    public CompanyDto updateCompany(UUID id, CompanyDto dto) {
        log.info("Updating company with id: {}", id);
        companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));
        Company entity = companyMapper.toEntity(dto);
        entity.setId(id);
        Company saved = companyRepository.save(entity);
        return companyMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompany(UUID id) {
        log.info("Deleting company with id: {}", id);
        companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));
        companyRepository.deleteById(id);
    }
}
