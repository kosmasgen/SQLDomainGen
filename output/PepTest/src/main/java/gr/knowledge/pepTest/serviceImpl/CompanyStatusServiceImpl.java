package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.mapper.CompanyStatusMapper;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.repository.CompanyStatusRepository;
import gr.knowledge.pepTest.service.CompanyStatusService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Status} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyStatusServiceImpl implements CompanyStatusService {

    private final CompanyStatusRepository companyStatusRepository;
    private final CompanyStatusMapper companyStatusMapper;

    /**
     * Retrieves all company statuses records.
     * @return list of CompanyStatusDto
     */
    @Override
    public List<CompanyStatusDto> getAllCompanyStatuses() {
        log.info("Fetching all company statuses records.");
        return companyStatusMapper.toDTOList(companyStatusRepository.findAll());
    }

    /**
     * Retrieves a company status record by id.
     * @param id the company status id
     * @return CompanyStatusDto
     */
    @Override
    public CompanyStatusDto getCompanyStatusById(UUID id) {
        log.info("Fetching company status with id: {}", id);

        CompanyStatus existingEntity = findCompanyStatusByIdOrThrow(id);
        return companyStatusMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company status record.
     * @param dto input payload
     * @return created {@link CompanyStatusDto}
     */
    @Override
    public CompanyStatusDto createCompanyStatus(CompanyStatusDto dto) {
        log.info("Creating company status.");

        validateCompanyStatusCreateUniqueConstraints(dto);

        CompanyStatus entity = companyStatusMapper.toEntity(dto);
        CompanyStatus savedEntity = companyStatusRepository.save(entity);

        return companyStatusMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company status record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company status id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyStatusDto}
     */
    @Override
    public CompanyStatusDto updateCompanyStatus(UUID id, CompanyStatusDto dto) {
        log.info("Updating company status with id: {}", id);

        CompanyStatus existingEntity = findCompanyStatusByIdOrThrow(id);
        companyStatusMapper.partialUpdate(existingEntity, dto);
        CompanyStatus savedEntity = companyStatusRepository.save(existingEntity);

        return companyStatusMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company status record by id.
     * @param id the company status id
     */
    @Override
    public void deleteCompanyStatus(UUID id) {
        log.info("Deleting company status with id: {}", id);

        findCompanyStatusByIdOrThrow(id);
        companyStatusRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyStatusCreateUniqueConstraints(CompanyStatusDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberCompanyStatusId() != null && companyStatusRepository.existsByChamberIdAndChamberCompanyStatusId(dto.getChamberId(), dto.getChamberCompanyStatusId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyStatus")
                    .message("CompanyStatus already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberCompanyStatusId=" + dto.getChamberCompanyStatusId())
                    .build();
        }
    }

    /**
     * Finds an existing company status record by id or throws an exception.
     * @param id the company status id
     * @return existing CompanyStatus entity
     */
    private CompanyStatus findCompanyStatusByIdOrThrow(UUID id) {
        return companyStatusRepository.findById(id)
                .orElseThrow(() -> createCompanyStatusNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company status entity.
     @param id the company status id
     @return runtime exception
     */
    private RuntimeException createCompanyStatusNotFoundException(UUID id) {
        log.warn("CompanyStatus not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyStatus")
                .message("CompanyStatus not found with id: " + id)
                .build();
    }

}
