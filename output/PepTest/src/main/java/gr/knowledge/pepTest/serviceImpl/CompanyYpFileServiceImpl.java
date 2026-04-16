package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import gr.knowledge.pepTest.mapper.CompanyYpFileMapper;
import gr.knowledge.pepTest.entity.CompanyYpFile;
import gr.knowledge.pepTest.repository.CompanyYpFileRepository;
import gr.knowledge.pepTest.service.CompanyYpFileService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company Yp File} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyYpFileServiceImpl implements CompanyYpFileService {

    private final CompanyYpFileRepository companyYpFileRepository;
    private final CompanyYpFileMapper companyYpFileMapper;

    /**
     * Retrieves all company yp files records.
     * @return list of CompanyYpFileDto
     */
    @Override
    public List<CompanyYpFileDto> getAllCompanyYpFiles() {
        log.info("Fetching all company yp files records.");
        return companyYpFileMapper.toDTOList(companyYpFileRepository.findAll());
    }

    /**
     * Retrieves a company yp file record by id.
     * @param id the company yp file id
     * @return CompanyYpFileDto
     */
    @Override
    public CompanyYpFileDto getCompanyYpFileById(UUID id) {
        log.info("Fetching company yp file with id: {}", id);

        CompanyYpFile existingEntity = findCompanyYpFileByIdOrThrow(id);
        return companyYpFileMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company yp file record.
     * @param dto input payload
     * @return created {@link CompanyYpFileDto}
     */
    @Override
    public CompanyYpFileDto createCompanyYpFile(CompanyYpFileDto dto) {
        log.info("Creating company yp file.");

        CompanyYpFile entity = companyYpFileMapper.toEntity(dto);
        CompanyYpFile savedEntity = companyYpFileRepository.save(entity);

        return companyYpFileMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company yp file record.
     * <p>
     * Only non null fields from the DTO are applied to the existing entity.
     * @param id the company yp file id
     * @param dto input payload with partial fields
     * @return updated {@link CompanyYpFileDto}
     */
    @Override
    public CompanyYpFileDto updateCompanyYpFile(UUID id, CompanyYpFileDto dto) {
        log.info("Updating company yp file with id: {}", id);

        CompanyYpFile existingEntity = findCompanyYpFileByIdOrThrow(id);
        companyYpFileMapper.partialUpdate(existingEntity, dto);
        CompanyYpFile savedEntity = companyYpFileRepository.save(existingEntity);

        return companyYpFileMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company yp file record by id.
     * @param id the company yp file id
     */
    @Override
    public void deleteCompanyYpFile(UUID id) {
        log.info("Deleting company yp file with id: {}", id);

        findCompanyYpFileByIdOrThrow(id);
        companyYpFileRepository.deleteById(id);
    }

    /**
     * Finds an existing company yp file record by id or throws an exception.
     * @param id the company yp file id
     * @return existing CompanyYpFile entity
     */
    private CompanyYpFile findCompanyYpFileByIdOrThrow(UUID id) {
        return companyYpFileRepository.findById(id)
                .orElseThrow(() -> createCompanyYpFileNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company yp file entity.
     @param id the company yp file id
     @return runtime exception
     */
    private RuntimeException createCompanyYpFileNotFoundException(UUID id) {
        log.warn("CompanyYpFile not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpFile")
                .message("CompanyYpFile not found with id: " + id)
                .build();
    }

}
