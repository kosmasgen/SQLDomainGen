package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.mapper.CompanyTitleMapper;
import gr.knowledge.pepTest.entity.CompanyTitle;
import gr.knowledge.pepTest.repository.CompanyTitleRepository;
import gr.knowledge.pepTest.service.CompanyTitleService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Title} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyTitleServiceImpl implements CompanyTitleService {

    private final CompanyTitleRepository companyTitleRepository;
    private final CompanyTitleMapper companyTitleMapper;

    /**
     * Retrieves all company titles records.
     * @return list of CompanyTitleDto
     */
    @Override
    public List<CompanyTitleDto> getAllCompanyTitles() {
        log.info("Fetching all company titles records.");
        return companyTitleMapper.toDTOList(companyTitleRepository.findAll());
    }

    /**
     * Retrieves a company title record by id.
     * @param id the company title id
     * @return CompanyTitleDto
     */
    @Override
    public CompanyTitleDto getCompanyTitleById(UUID id) {
        log.info("Fetching company title with id: {}", id);

        CompanyTitle existingEntity = findCompanyTitleByIdOrThrow(id);
        return companyTitleMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company title record.
     * @param dto input payload
     * @return created {@link CompanyTitleDto}
     */
    @Override
    public CompanyTitleDto createCompanyTitle(CompanyTitleDto dto) {
        log.info("Creating company title.");

        validateCompanyTitleCreateUniqueConstraints(dto);

        CompanyTitle entity = companyTitleMapper.toEntity(dto);
        CompanyTitle savedEntity = companyTitleRepository.save(entity);

        return companyTitleMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company title record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company title id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyTitleDto}
     */
    @Override
    public CompanyTitleDto updateCompanyTitle(UUID id, CompanyTitleDto dto) {
        log.info("Updating company title with id: {}", id);

        CompanyTitle existingEntity = findCompanyTitleByIdOrThrow(id);
        companyTitleMapper.partialUpdate(existingEntity, dto);
        CompanyTitle savedEntity = companyTitleRepository.save(existingEntity);

        return companyTitleMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company title record by id.
     * @param id the company title id
     */
    @Override
    public void deleteCompanyTitle(UUID id) {
        log.info("Deleting company title with id: {}", id);

        findCompanyTitleByIdOrThrow(id);
        companyTitleRepository.deleteById(id);
    }

    /**
     * Validates unique constraints for create operations.
     * @param dto input payload
     */
    private void validateCompanyTitleCreateUniqueConstraints(CompanyTitleDto dto) {

        if (dto == null) {
            return;
        }

        if (dto.getChamberId() != null && dto.getChamberTitleId() != null && companyTitleRepository.existsByChamberIdAndChamberTitleId(dto.getChamberId(), dto.getChamberTitleId())) {
            throw GeneratedRuntimeException.builder()
                    .code(ErrorCodes.BAD_REQUEST)
                    .entity("CompanyTitle")
                    .message("CompanyTitle already exists with " + "chamberId=" + dto.getChamberId() + ", " + "chamberTitleId=" + dto.getChamberTitleId())
                    .build();
        }
    }

    /**
     * Finds an existing company title record by id or throws an exception.
     * @param id the company title id
     * @return existing CompanyTitle entity
     */
    private CompanyTitle findCompanyTitleByIdOrThrow(UUID id) {
        return companyTitleRepository.findById(id)
                .orElseThrow(() -> createCompanyTitleNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company title entity.
     @param id the company title id
     @return runtime exception
     */
    private RuntimeException createCompanyTitleNotFoundException(UUID id) {
        log.warn("CompanyTitle not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyTitle")
                .message("CompanyTitle not found with id: " + id)
                .build();
    }

}
