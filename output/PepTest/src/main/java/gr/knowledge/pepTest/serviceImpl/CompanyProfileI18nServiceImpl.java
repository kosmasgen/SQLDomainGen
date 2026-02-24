package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.mapper.CompanyProfileI18nMapper;
import gr.knowledge.pepTest.entity.CompanyProfileI18n;
import gr.knowledge.pepTest.repository.CompanyProfileI18nRepository;
import gr.knowledge.pepTest.service.CompanyProfileI18nService;
import gr.knowledge.pepTest.entity.CompanyProfileI18nPK;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyProfileI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyProfileI18nServiceImpl implements CompanyProfileI18nService {

    private final CompanyProfileI18nRepository companyProfileI18nRepository;
    private final CompanyProfileI18nMapper companyProfileI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyProfileI18nDto}
     */
    @Override
    public List<CompanyProfileI18nDto> getAllCompanyProfileI18n() {
        log.info("Fetching all company-profile-i18n.");
        return companyProfileI18nMapper.toDTO(companyProfileI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyProfileI18nDto}
     */
    @Override
    public CompanyProfileI18nDto getCompanyProfileI18nById(CompanyProfileI18nPK id) {
        log.info("Fetching company-profile-i18n with id: {}", id);
        CompanyProfileI18n entity = companyProfileI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfileI18n not found with id: " + id));
        return companyProfileI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyProfileI18nDto}
     */
    @Override
    public CompanyProfileI18nDto createCompanyProfileI18n(CompanyProfileI18nDto dto) {
        log.info("Creating company-profile-i18n.");
        CompanyProfileI18n entity = companyProfileI18nMapper.toEntity(dto);
        CompanyProfileI18n saved = companyProfileI18nRepository.save(entity);
        return companyProfileI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyProfileI18nDto}
     */
    @Override
    public CompanyProfileI18nDto updateCompanyProfileI18n(CompanyProfileI18nPK id, CompanyProfileI18nDto dto) {
        log.info("Updating company-profile-i18n with id: {}", id);
        companyProfileI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfileI18n not found with id: " + id));
        CompanyProfileI18n entity = companyProfileI18nMapper.toEntity(dto);
        entity.setId(id);
        CompanyProfileI18n saved = companyProfileI18nRepository.save(entity);
        return companyProfileI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyProfileI18n(CompanyProfileI18nPK id) {
        log.info("Deleting company-profile-i18n with id: {}", id);
        companyProfileI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfileI18n not found with id: " + id));
        companyProfileI18nRepository.deleteById(id);
    }
}
