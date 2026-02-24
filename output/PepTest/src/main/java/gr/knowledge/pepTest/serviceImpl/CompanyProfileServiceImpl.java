package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.mapper.CompanyProfileMapper;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.repository.CompanyProfileRepository;
import gr.knowledge.pepTest.service.CompanyProfileService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyProfile} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final CompanyProfileRepository companyProfileRepository;
    private final CompanyProfileMapper companyProfileMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyProfileDto}
     */
    @Override
    public List<CompanyProfileDto> getAllCompanyProfile() {
        log.info("Fetching all company-profile.");
        return companyProfileMapper.toDTO(companyProfileRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyProfileDto}
     */
    @Override
    public CompanyProfileDto getCompanyProfileById(UUID id) {
        log.info("Fetching company-profile with id: {}", id);
        CompanyProfile entity = companyProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfile not found with id: " + id));
        return companyProfileMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyProfileDto}
     */
    @Override
    public CompanyProfileDto createCompanyProfile(CompanyProfileDto dto) {
        log.info("Creating company-profile.");
        CompanyProfile entity = companyProfileMapper.toEntity(dto);
        CompanyProfile saved = companyProfileRepository.save(entity);
        return companyProfileMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyProfileDto}
     */
    @Override
    public CompanyProfileDto updateCompanyProfile(UUID id, CompanyProfileDto dto) {
        log.info("Updating company-profile with id: {}", id);
        companyProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfile not found with id: " + id));
        CompanyProfile entity = companyProfileMapper.toEntity(dto);
        entity.setId(id);
        CompanyProfile saved = companyProfileRepository.save(entity);
        return companyProfileMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyProfile(UUID id) {
        log.info("Deleting company-profile with id: {}", id);
        companyProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyProfile not found with id: " + id));
        companyProfileRepository.deleteById(id);
    }
}
