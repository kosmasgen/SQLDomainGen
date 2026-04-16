package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import gr.knowledge.pepTest.mapper.CompanyArticleFileMapper;
import gr.knowledge.pepTest.entity.CompanyArticleFile;
import gr.knowledge.pepTest.repository.CompanyArticleFileRepository;
import gr.knowledge.pepTest.service.CompanyArticleFileService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Article File} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyArticleFileServiceImpl implements CompanyArticleFileService {

    private final CompanyArticleFileRepository companyArticleFileRepository;
    private final CompanyArticleFileMapper companyArticleFileMapper;

    /**
     * Retrieves all company article files records.
     * @return list of CompanyArticleFileDto
     */
    @Override
    public List<CompanyArticleFileDto> getAllCompanyArticleFiles() {
        log.info("Fetching all company article files records.");
        return companyArticleFileMapper.toDTOList(companyArticleFileRepository.findAll());
    }

    /**
     * Retrieves a company article file record by id.
     * @param id the company article file id
     * @return CompanyArticleFileDto
     */
    @Override
    public CompanyArticleFileDto getCompanyArticleFileById(UUID id) {
        log.info("Fetching company article file with id: {}", id);

        CompanyArticleFile existingEntity = findCompanyArticleFileByIdOrThrow(id);
        return companyArticleFileMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company article file record.
     * @param dto input payload
     * @return created {@link CompanyArticleFileDto}
     */
    @Override
    public CompanyArticleFileDto createCompanyArticleFile(CompanyArticleFileDto dto) {
        log.info("Creating company article file.");

        CompanyArticleFile entity = companyArticleFileMapper.toEntity(dto);
        CompanyArticleFile savedEntity = companyArticleFileRepository.save(entity);

        return companyArticleFileMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company article file record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company article file id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyArticleFileDto}
     */
    @Override
    public CompanyArticleFileDto updateCompanyArticleFile(UUID id, CompanyArticleFileDto dto) {
        log.info("Updating company article file with id: {}", id);

        CompanyArticleFile existingEntity = findCompanyArticleFileByIdOrThrow(id);
        companyArticleFileMapper.partialUpdate(existingEntity, dto);
        CompanyArticleFile savedEntity = companyArticleFileRepository.save(existingEntity);

        return companyArticleFileMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company article file record by id.
     * @param id the company article file id
     */
    @Override
    public void deleteCompanyArticleFile(UUID id) {
        log.info("Deleting company article file with id: {}", id);

        findCompanyArticleFileByIdOrThrow(id);
        companyArticleFileRepository.deleteById(id);
    }

    /**
     * Finds an existing company article file record by id or throws an exception.
     * @param id the company article file id
     * @return existing CompanyArticleFile entity
     */
    private CompanyArticleFile findCompanyArticleFileByIdOrThrow(UUID id) {
        return companyArticleFileRepository.findById(id)
                .orElseThrow(() -> createCompanyArticleFileNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company article file entity.
     @param id the company article file id
     @return runtime exception
     */
    private RuntimeException createCompanyArticleFileNotFoundException(UUID id) {
        log.warn("CompanyArticleFile not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyArticleFile")
                .message("CompanyArticleFile not found with id: " + id)
                .build();
    }

}
