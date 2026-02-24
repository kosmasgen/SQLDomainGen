package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationI18nMapper;
import gr.knowledge.pepTest.entity.CompanyBgCooperationI18n;
import gr.knowledge.pepTest.repository.CompanyBgCooperationI18nRepository;
import gr.knowledge.pepTest.service.CompanyBgCooperationI18nService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyBgCooperationI18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyBgCooperationI18nServiceImpl implements CompanyBgCooperationI18nService {

    private final CompanyBgCooperationI18nRepository companyBgCooperationI18nRepository;
    private final CompanyBgCooperationI18nMapper companyBgCooperationI18nMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyBgCooperationI18nDto}
     */
    @Override
    public List<CompanyBgCooperationI18nDto> getAllCompanyBgCooperationI18n() {
        log.info("Fetching all company-bg-cooperation-i18n.");
        return companyBgCooperationI18nMapper.toDTO(companyBgCooperationI18nRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyBgCooperationI18nDto}
     */
    @Override
    public CompanyBgCooperationI18nDto getCompanyBgCooperationI18nById(UUID id) {
        log.info("Fetching company-bg-cooperation-i18n with id: {}", id);
        CompanyBgCooperationI18n entity = companyBgCooperationI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyBgCooperationI18n not found with id: " + id));
        return companyBgCooperationI18nMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyBgCooperationI18nDto}
     */
    @Override
    public CompanyBgCooperationI18nDto createCompanyBgCooperationI18n(CompanyBgCooperationI18nDto dto) {
        log.info("Creating company-bg-cooperation-i18n.");
        CompanyBgCooperationI18n entity = companyBgCooperationI18nMapper.toEntity(dto);
        CompanyBgCooperationI18n saved = companyBgCooperationI18nRepository.save(entity);
        return companyBgCooperationI18nMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyBgCooperationI18nDto}
     */
    @Override
    public CompanyBgCooperationI18nDto updateCompanyBgCooperationI18n(UUID id, CompanyBgCooperationI18nDto dto) {
        log.info("Updating company-bg-cooperation-i18n with id: {}", id);
        companyBgCooperationI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyBgCooperationI18n not found with id: " + id));
        CompanyBgCooperationI18n entity = companyBgCooperationI18nMapper.toEntity(dto);
        entity.setId(id);
        CompanyBgCooperationI18n saved = companyBgCooperationI18nRepository.save(entity);
        return companyBgCooperationI18nMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyBgCooperationI18n(UUID id) {
        log.info("Deleting company-bg-cooperation-i18n with id: {}", id);
        companyBgCooperationI18nRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyBgCooperationI18n not found with id: " + id));
        companyBgCooperationI18nRepository.deleteById(id);
    }
}
