package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.mapper.CompanyTitleMapper;
import gr.knowledge.pepTest.entity.CompanyTitle;
import gr.knowledge.pepTest.repository.CompanyTitleRepository;
import gr.knowledge.pepTest.service.CompanyTitleService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyTitle} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyTitleServiceImpl implements CompanyTitleService {

    private final CompanyTitleRepository companyTitleRepository;
    private final CompanyTitleMapper companyTitleMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyTitleDto}
     */
    @Override
    public List<CompanyTitleDto> getAllCompanyTitle() {
        log.info("Fetching all company-title.");
        return companyTitleMapper.toDTO(companyTitleRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyTitleDto}
     */
    @Override
    public CompanyTitleDto getCompanyTitleById(UUID id) {
        log.info("Fetching company-title with id: {}", id);
        CompanyTitle entity = companyTitleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTitle not found with id: " + id));
        return companyTitleMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyTitleDto}
     */
    @Override
    public CompanyTitleDto createCompanyTitle(CompanyTitleDto dto) {
        log.info("Creating company-title.");
        CompanyTitle entity = companyTitleMapper.toEntity(dto);
        CompanyTitle saved = companyTitleRepository.save(entity);
        return companyTitleMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyTitleDto}
     */
    @Override
    public CompanyTitleDto updateCompanyTitle(UUID id, CompanyTitleDto dto) {
        log.info("Updating company-title with id: {}", id);
        companyTitleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTitle not found with id: " + id));
        CompanyTitle entity = companyTitleMapper.toEntity(dto);
        entity.setId(id);
        CompanyTitle saved = companyTitleRepository.save(entity);
        return companyTitleMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyTitle(UUID id) {
        log.info("Deleting company-title with id: {}", id);
        companyTitleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTitle not found with id: " + id));
        companyTitleRepository.deleteById(id);
    }
}
