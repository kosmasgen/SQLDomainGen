package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationI18nMapper;
import gr.knowledge.pepTest.entity.CompanyBgCooperationI18n;
import gr.knowledge.pepTest.repository.CompanyBgCooperationI18nRepository;
import gr.knowledge.pepTest.service.CompanyBgCooperationI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Bg Cooperation I18n} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyBgCooperationI18nServiceImpl implements CompanyBgCooperationI18nService {

    private final CompanyBgCooperationI18nRepository companyBgCooperationI18nRepository;
    private final CompanyBgCooperationI18nMapper companyBgCooperationI18nMapper;

    /**
     * Retrieves all company bg cooperation i18ns records.
     * @return list of CompanyBgCooperationI18nDto
     */
    @Override
    public List<CompanyBgCooperationI18nDto> getAllCompanyBgCooperationI18ns() {
        log.info("Fetching all company bg cooperation i18ns records.");
        return companyBgCooperationI18nMapper.toDTOList(companyBgCooperationI18nRepository.findAll());
    }

    /**
     * Retrieves a company bg cooperation i18n record by id.
     * @param id the company bg cooperation i18n id
     * @return CompanyBgCooperationI18nDto
     */
    @Override
    public CompanyBgCooperationI18nDto getCompanyBgCooperationI18nById(UUID id) {
        log.info("Fetching company bg cooperation i18n with id: {}", id);

        CompanyBgCooperationI18n existingEntity = findCompanyBgCooperationI18nByIdOrThrow(id);
        return companyBgCooperationI18nMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company bg cooperation i18n record.
     * @param dto input payload
     * @return created {@link CompanyBgCooperationI18nDto}
     */
    @Override
    public CompanyBgCooperationI18nDto createCompanyBgCooperationI18n(CompanyBgCooperationI18nDto dto) {
        log.info("Creating company bg cooperation i18n.");

        CompanyBgCooperationI18n entity = companyBgCooperationI18nMapper.toEntity(dto);
        CompanyBgCooperationI18n savedEntity = companyBgCooperationI18nRepository.save(entity);

        return companyBgCooperationI18nMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company bg cooperation i18n record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company bg cooperation i18n id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyBgCooperationI18nDto}
     */
    @Override
    public CompanyBgCooperationI18nDto updateCompanyBgCooperationI18n(UUID id, CompanyBgCooperationI18nDto dto) {
        log.info("Updating company bg cooperation i18n with id: {}", id);

        CompanyBgCooperationI18n existingEntity = findCompanyBgCooperationI18nByIdOrThrow(id);
        companyBgCooperationI18nMapper.partialUpdate(existingEntity, dto);
        CompanyBgCooperationI18n savedEntity = companyBgCooperationI18nRepository.save(existingEntity);

        return companyBgCooperationI18nMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company bg cooperation i18n record by id.
     * @param id the company bg cooperation i18n id
     */
    @Override
    public void deleteCompanyBgCooperationI18n(UUID id) {
        log.info("Deleting company bg cooperation i18n with id: {}", id);

        findCompanyBgCooperationI18nByIdOrThrow(id);
        companyBgCooperationI18nRepository.deleteById(id);
    }

    /**
     * Finds an existing company bg cooperation i18n record by id or throws an exception.
     * @param id the company bg cooperation i18n id
     * @return existing CompanyBgCooperationI18n entity
     */
    private CompanyBgCooperationI18n findCompanyBgCooperationI18nByIdOrThrow(UUID id) {
        return companyBgCooperationI18nRepository.findById(id)
                .orElseThrow(() -> createCompanyBgCooperationI18nNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company bg cooperation i18n entity.
     @param id the company bg cooperation i18n id
     @return runtime exception
     */
    private RuntimeException createCompanyBgCooperationI18nNotFoundException(UUID id) {
        log.warn("CompanyBgCooperationI18n not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyBgCooperationI18n")
                .message("CompanyBgCooperationI18n not found with id: " + id)
                .build();
    }

}
