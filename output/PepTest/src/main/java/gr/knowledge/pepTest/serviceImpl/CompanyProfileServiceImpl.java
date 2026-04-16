package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.mapper.CompanyProfileMapper;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.repository.CompanyProfileRepository;
import gr.knowledge.pepTest.service.CompanyProfileService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Profile} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final CompanyProfileRepository companyProfileRepository;
    private final CompanyProfileMapper companyProfileMapper;

    /**
     * Retrieves all company profiles records.
     * @return list of CompanyProfileDto
     */
    @Override
    public List<CompanyProfileDto> getAllCompanyProfiles() {
        log.info("Fetching all company profiles records.");
        return companyProfileMapper.toDTOList(companyProfileRepository.findAll());
    }

    /**
     * Retrieves a company profile record by id.
     * @param id the company profile id
     * @return CompanyProfileDto
     */
    @Override
    public CompanyProfileDto getCompanyProfileById(UUID id) {
        log.info("Fetching company profile with id: {}", id);

        CompanyProfile existingEntity = findCompanyProfileByIdOrThrow(id);
        return companyProfileMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company profile record.
     * @param dto input payload
     * @return created {@link CompanyProfileDto}
     */
    @Override
    public CompanyProfileDto createCompanyProfile(CompanyProfileDto dto) {
        log.info("Creating company profile.");

        CompanyProfile entity = companyProfileMapper.toEntity(dto);
        CompanyProfile savedEntity = companyProfileRepository.save(entity);

        return companyProfileMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company profile record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company profile id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyProfileDto}
     */
    @Override
    public CompanyProfileDto updateCompanyProfile(UUID id, CompanyProfileDto dto) {
        log.info("Updating company profile with id: {}", id);

        CompanyProfile existingEntity = findCompanyProfileByIdOrThrow(id);
        companyProfileMapper.partialUpdate(existingEntity, dto);
        CompanyProfile savedEntity = companyProfileRepository.save(existingEntity);

        return companyProfileMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company profile record by id.
     * @param id the company profile id
     */
    @Override
    public void deleteCompanyProfile(UUID id) {
        log.info("Deleting company profile with id: {}", id);

        findCompanyProfileByIdOrThrow(id);
        companyProfileRepository.deleteById(id);
    }

    /**
     * Finds an existing company profile record by id or throws an exception.
     * @param id the company profile id
     * @return existing CompanyProfile entity
     */
    private CompanyProfile findCompanyProfileByIdOrThrow(UUID id) {
        return companyProfileRepository.findById(id)
                .orElseThrow(() -> createCompanyProfileNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company profile entity.
     @param id the company profile id
     @return runtime exception
     */
    private RuntimeException createCompanyProfileNotFoundException(UUID id) {
        log.warn("CompanyProfile not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyProfile")
                .message("CompanyProfile not found with id: " + id)
                .build();
    }

}
