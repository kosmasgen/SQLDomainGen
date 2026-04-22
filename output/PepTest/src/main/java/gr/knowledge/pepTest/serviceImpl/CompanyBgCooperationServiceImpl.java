package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationMapper;
import gr.knowledge.pepTest.entity.CompanyBgCooperation;
import gr.knowledge.pepTest.repository.CompanyBgCooperationRepository;
import gr.knowledge.pepTest.service.CompanyBgCooperationService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Bg Cooperation} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyBgCooperationServiceImpl implements CompanyBgCooperationService {

    private final CompanyBgCooperationRepository companyBgCooperationRepository;
    private final CompanyBgCooperationMapper companyBgCooperationMapper;

    /**
     * Retrieves all company bg cooperations records.
     * @return list of CompanyBgCooperationDto
     */
    @Override
    public List<CompanyBgCooperationDto> getAllCompanyBgCooperations() {
        log.info("Fetching all company bg cooperations records.");
        return companyBgCooperationMapper.toDTOList(companyBgCooperationRepository.findAll());
    }

    /**
     * Retrieves a company bg cooperation record by id.
     * @param id the company bg cooperation id
     * @return CompanyBgCooperationDto
     */
    @Override
    public CompanyBgCooperationDto getCompanyBgCooperationById(UUID id) {
        log.info("Fetching company bg cooperation with id: {}", id);

        CompanyBgCooperation existingEntity = findCompanyBgCooperationByIdOrThrow(id);
        return companyBgCooperationMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company bg cooperation record.
     * @param dto input payload
     * @return created {@link CompanyBgCooperationDto}
     */
    @Override
    public CompanyBgCooperationDto createCompanyBgCooperation(CompanyBgCooperationDto dto) {
        log.info("Creating company bg cooperation.");

        CompanyBgCooperation entity = companyBgCooperationMapper.toEntity(dto);
        CompanyBgCooperation savedEntity = companyBgCooperationRepository.save(entity);

        return companyBgCooperationMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company bg cooperation record.
     *
     * @param id the company bg cooperation id
     * @param dto input payload
     * @return updated {@link CompanyBgCooperationDto}
     */
    @Override
    public CompanyBgCooperationDto updateCompanyBgCooperation(UUID id, CompanyBgCooperationDto dto) {
        log.info("Updating company bg cooperation with id: {}", id);

        CompanyBgCooperation existingEntity = findCompanyBgCooperationByIdOrThrow(id);
        companyBgCooperationMapper.partialUpdate(existingEntity, dto);
        CompanyBgCooperation savedEntity = companyBgCooperationRepository.save(existingEntity);

        return companyBgCooperationMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company bg cooperation record by id.
     * @param id the company bg cooperation id
     */
    @Override
    public void deleteCompanyBgCooperation(UUID id) {
        log.info("Deleting company bg cooperation with id: {}", id);

        findCompanyBgCooperationByIdOrThrow(id);
        companyBgCooperationRepository.deleteById(id);
    }

    /**
     * Finds an existing company bg cooperation record by id or throws an exception.
     * @param id the company bg cooperation id
     * @return existing CompanyBgCooperation entity
     */
    private CompanyBgCooperation findCompanyBgCooperationByIdOrThrow(UUID id) {
        return companyBgCooperationRepository.findById(id)
                .orElseThrow(() -> createCompanyBgCooperationNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company bg cooperation entity.
     @param id the company bg cooperation id
     @return runtime exception
     */
    private RuntimeException createCompanyBgCooperationNotFoundException(UUID id) {
        log.warn("CompanyBgCooperation not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyBgCooperation")
                .message("CompanyBgCooperation not found with id: " + id)
                .build();
    }

}
