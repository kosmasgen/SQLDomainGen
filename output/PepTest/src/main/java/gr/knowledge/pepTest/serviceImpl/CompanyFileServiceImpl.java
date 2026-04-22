package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.mapper.CompanyFileMapper;
import gr.knowledge.pepTest.entity.CompanyFile;
import gr.knowledge.pepTest.repository.CompanyFileRepository;
import gr.knowledge.pepTest.service.CompanyFileService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import java.util.UUID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service implementation for {@code Company File} domain operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CompanyFileServiceImpl implements CompanyFileService {

    private final CompanyFileRepository companyFileRepository;
    private final CompanyFileMapper companyFileMapper;

    /**
     * Retrieves all company files records.
     * @return list of CompanyFileDto
     */
    @Override
    public List<CompanyFileDto> getAllCompanyFiles() {
        log.info("Fetching all company files records.");
        return companyFileMapper.toDTOList(companyFileRepository.findAll());
    }

    /**
     * Retrieves a company file record by id.
     * @param id the company file id
     * @return CompanyFileDto
     */
    @Override
    public CompanyFileDto getCompanyFileById(UUID id) {
        log.info("Fetching company file with id: {}", id);

        CompanyFile existingEntity = findCompanyFileByIdOrThrow(id);
        return companyFileMapper.toDTO(existingEntity);
    }

    /**
     * Creates a new company file record.
     * @param dto input payload
     * @return created {@link CompanyFileDto}
     */
    @Override
    public CompanyFileDto createCompanyFile(CompanyFileDto dto) {
        log.info("Creating company file.");

        CompanyFile entity = companyFileMapper.toEntity(dto);
        CompanyFile savedEntity = companyFileRepository.save(entity);

        return companyFileMapper.toDTO(savedEntity);
    }

    /**
     * Updates an existing company file record.
     *
     * @param id the company file id
     * @param dto input payload
     * @return updated {@link CompanyFileDto}
     */
    @Override
    public CompanyFileDto updateCompanyFile(UUID id, CompanyFileDto dto) {
        log.info("Updating company file with id: {}", id);

        CompanyFile existingEntity = findCompanyFileByIdOrThrow(id);
        companyFileMapper.partialUpdate(existingEntity, dto);
        CompanyFile savedEntity = companyFileRepository.save(existingEntity);

        return companyFileMapper.toDTO(savedEntity);
    }

    /**
     * Delete a company file record by id.
     * @param id the company file id
     */
    @Override
    public void deleteCompanyFile(UUID id) {
        log.info("Deleting company file with id: {}", id);

        findCompanyFileByIdOrThrow(id);
        companyFileRepository.deleteById(id);
    }

    /**
     * Finds an existing company file record by id or throws an exception.
     * @param id the company file id
     * @return existing CompanyFile entity
     */
    private CompanyFile findCompanyFileByIdOrThrow(UUID id) {
        return companyFileRepository.findById(id)
                .orElseThrow(() -> createCompanyFileNotFoundException(id));
    }

    /**
     Creates a NOT FOUND exception for the company file entity.
     @param id the company file id
     @return runtime exception
     */
    private RuntimeException createCompanyFileNotFoundException(UUID id) {
        log.warn("CompanyFile not found with id: {}", id);

        return GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyFile")
                .message("CompanyFile not found with id: " + id)
                .build();
    }

}
