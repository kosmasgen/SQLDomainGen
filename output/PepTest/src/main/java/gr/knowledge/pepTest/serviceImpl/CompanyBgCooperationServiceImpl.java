package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationMapper;
import gr.knowledge.pepTest.entity.CompanyBgCooperation;
import gr.knowledge.pepTest.repository.CompanyBgCooperationRepository;
import gr.knowledge.pepTest.service.CompanyBgCooperationService;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service implementation for {@code CompanyBgCooperation} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyBgCooperationServiceImpl implements CompanyBgCooperationService {

    private final CompanyBgCooperationRepository companyBgCooperationRepository;
    private final CompanyBgCooperationMapper companyBgCooperationMapper;

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyBgCooperationDto}
     */
    @Override
    public List<CompanyBgCooperationDto> getAllCompanyBgCooperation() {
        log.info("Fetching all company-bg-cooperation.");
        return companyBgCooperationMapper.toDTO(companyBgCooperationRepository.findAll());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyBgCooperationDto}
     */
    @Override
    public CompanyBgCooperationDto getCompanyBgCooperationById(UUID id) {
        log.info("Fetching company-bg-cooperation with id: {}", id);
        CompanyBgCooperation entity = companyBgCooperationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyBgCooperation not found with id: " + id));
        return companyBgCooperationMapper.toDTO(entity);
    }

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyBgCooperationDto}
     */
    @Override
    public CompanyBgCooperationDto createCompanyBgCooperation(CompanyBgCooperationDto dto) {
        log.info("Creating company-bg-cooperation.");
        CompanyBgCooperation entity = companyBgCooperationMapper.toEntity(dto);
        CompanyBgCooperation saved = companyBgCooperationRepository.save(entity);
        return companyBgCooperationMapper.toDTO(saved);
    }

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyBgCooperationDto}
     */
    @Override
    public CompanyBgCooperationDto updateCompanyBgCooperation(UUID id, CompanyBgCooperationDto dto) {
        log.info("Updating company-bg-cooperation with id: {}", id);
        companyBgCooperationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyBgCooperation not found with id: " + id));
        CompanyBgCooperation entity = companyBgCooperationMapper.toEntity(dto);
        entity.setId(id);
        CompanyBgCooperation saved = companyBgCooperationRepository.save(entity);
        return companyBgCooperationMapper.toDTO(saved);
    }

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    @Override
    public void deleteCompanyBgCooperation(UUID id) {
        log.info("Deleting company-bg-cooperation with id: {}", id);
        companyBgCooperationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyBgCooperation not found with id: " + id));
        companyBgCooperationRepository.deleteById(id);
    }
}
